package com.operational.platform.analyze.component.smt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.operational.platform.analyze.component.ToJsonParser;
import com.operational.platform.analyze.component.smt.bean.Career;
import com.operational.platform.analyze.component.smt.bean.InvestCase;
import com.operational.platform.analyze.component.smt.bean.Investor;
import com.operational.platform.common.bean.MQCrawlJob;
import com.operational.platform.common.constant.ExceptionTypeEnum;
import com.operational.platform.common.exception.CommonException;
import com.operational.platform.common.exception.RequestException;
import com.operational.platform.common.util.Downloader;
import com.operational.platform.common.util.StrUtil;
import com.operational.platform.common.util.XHttpClient;
import com.operational.platform.dbservice.model.AttackPage;
import com.operational.platform.dbservice.service.AttackPageService;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by lihuajun on 2017/9/28.
 */
@Component("investorParser")
public class InvestorParser extends ToJsonParser {

    private final String PIC_ROOT = "C:\\workspace\\operational-platform\\analyze\\pics\\";
    private final Random r = new Random();
    private final Integer sleepTime = 2000;
    @Autowired
    private AttackPageService attackPageService;

    private List<String> cookies = new ArrayList<String>() {{
        //add("JSESSIONID=A65F934B502BF50A73758251B3B45A22; APP3_0Client=smtApp; quickLogonKey=18857107097$020EA7AC6CE1ED064320F99286B0C601");
        add("JSESSIONID=52690298E7F588DC5476E2C6F4E2B50E; firstEnterUrlInSession=http%3A//pe.pedata.cn/addUserInfoMember.action; VisitorCapacity=1; USER_LOGIN_ID=59594077-8D22-4786-B16F-6C0C75B88A64; USER_LOGIN_NAME_KEY=13480138720; IS_CS_KEY=true; USER_LOGIN_NAME=13480138720; USER_LOGIN_LANGUAGE=zh_CN; USER_CLIENT_ID=\"\"; operatorId=31183; pageReferrInSession=http%3A//pe.pedata.cn/addUserInfoMember.action; request_locale=zh_CN");
    }};
    private List<String> iosUids = new ArrayList<String>() {{
        add("7B75CB76-F2F2-46AA-775B-F1AD9461C3A7");
        add("7B75C477-F0F1-45AA-665C-F1BD9461C2A7");
        add("7B75C487-F3F1-45BA-926C-F1BD9461C2A8");
    }};
    private List<String> iosIdfas = new ArrayList<String>() {{
        add("0443D612-A352-4553-98D8-77682DA24F0C");
        add("0443D611-A340-4443-78D8-99682DA24F0B");
        add("0443D613-A362-4223-87D8-88682DA24F0C");
    }};

    @Override
    public void exe(MQCrawlJob crawlJob) {

        if (StringUtils.isEmpty(crawlJob.getListPage()))
            return;
        if (crawlJob.getListPage().trim().equals("[]"))
            return;

        init();
        String listPage = crawlJob.getListPage();
        if (StringUtils.isEmpty(listPage)) {
            LOGGER.error("列表页为空[taskid={},pointid={},pageIndex={},listPage={}]", crawlJob.getTaskid(), crawlJob.getPointid(), crawlJob.getPageIndex(), crawlJob.getListPage());
            return;
        }

        if (listPage.trim().equals("[]"))
            return;

        JSONArray investors = JSON.parseArray(listPage.trim());
        for (int i = 0; i < investors.size(); i++) {

            try {
                JSONObject jsonObject = investors.getJSONObject(i);
                String personId = jsonObject.getString("personId");
                AttackPage attackPage = new AttackPage();
                attackPage.setLink(personId);
                attackPage.setPointid(crawlJob.getPointid());
                List<AttackPage> list = attackPageService.listByPointAndLink(attackPage);

                if (list.size() > 0)
                    continue;

                Investor investor = getInvestor(personId);//获取投资人基本信息
                investor.setCareerList(getCareers(investor));//获取投资人职业生涯信息
                investor.setCaseList(getCases(investor));//获取投资人投资案例信息
                filterCase(investor);

                attackPage.setTitle(investor.getNameCn());
                attackPage.setBelong("smt");
                attackPage.setFlag(1);
                attackPage.setAttr(JSON.toJSONString(investor));
                attackPage.setCategory("移动端投资人");
                attackPage.setMd5(StrUtil.md5(attackPage.getAttr()));

                attackPageService.save(attackPage);

            } catch (Exception e) {
                LOGGER.error("error:", e);
            }
        }
    }

    private void init() {
        httpGet = new HttpGet();
        httpPost = new HttpPost();
        httpGet.setHeader("Cookie", getRandom(cookies));
        httpPost.setHeader("Cookie", getRandom(cookies));
    }

    private Investor getInvestor(String personId) throws URISyntaxException, RequestException, CommonException {

        Investor investor = new Investor();
        httpGet.setURI(new URI("https://app.pedata.cn/PEDATA_APP_BACK/person/personDetail4?personId=" + personId + "&_=1506554198980"));
        String response = XHttpClient.doRequest(httpGet);
        JSONObject jsonObject = JSON.parseObject(response);
        if (!jsonObject.getBoolean("success"))
            throw new CommonException(ExceptionTypeEnum.Get_Investor_Info_ERROR);

        JSONObject result = jsonObject.getJSONObject("result");

        investor.setPersonId(personId);
        investor.setNameCn(result.getString("personNameCn"));
        investor.setNameEn(result.getString("personNameEn"));

        String photoUrl = result.getString("personPhoto");
        try {
            String suffix = photoUrl.substring(photoUrl.lastIndexOf("."));
            String fileName = System.currentTimeMillis() + suffix;
            int rInt = r.nextInt(21);
            String filePath = PIC_ROOT + rInt + File.separator + fileName;
            Downloader.file(photoUrl, filePath);
            investor.setPhoto("http://www.qheeshow.com/pics/" + rInt + "/" + fileName);
        } catch (Exception e) {
            LOGGER.error("下载投资人照片失败[投资人id={},照片url=]", investor.getPersonId(), photoUrl, e);
            investor.setPhoto(photoUrl);
        }
        String[] packInfo = result.getString("packInfo").split("::");
        investor.setCompany(packInfo[0]);
        investor.setPosition(packInfo[1]);
        investor.setInvestCount(result.getInteger("investCount"));
        String[] recentCase = result.getString("recentCase").split("::");
        investor.setRecentCaseName(recentCase[0]);
        investor.setRecentCaseId(recentCase[1]);
        investor.setInvestTrend(result.getString("investTrend"));
        investor.setProfile(result.getString("personDesc"));
        investor.setTel(result.getString("personTel"));
        investor.setEmail(result.getString("personMail"));

        return investor;
    }

    private List<Career> getCareers(Investor investor) {
        List<Career> careers = new ArrayList<>();
        try {
            httpGet.setURI(new URI("https://app.pedata.cn/PEDATA_APP_BACK/percar/list4?personId=" + investor.getPersonId() + "&_=1506554199066"));
            String response = XHttpClient.doRequest(httpGet);
            JSONObject jsonObject = JSON.parseObject(response);
            if (!jsonObject.getBoolean("success"))
                throw new CommonException(ExceptionTypeEnum.Get_Investor_Career_ERROR);

            JSONArray array = jsonObject.getJSONArray("result");

            for (int i = 0; i < array.size(); i++) {
                JSONObject object = array.getJSONObject(i);
                Career career = new Career();
                career.setCompany(object.getString("companyName"));
                career.setStartTime(object.getString("personCareerStart"));
                career.setEndTime(object.getString("personCareerEnd"));
                career.setPositionCn(object.getString("positionCn"));
                career.setIsOnDuty(object.getString("personCareerOnduty"));

                careers.add(career);
            }

        } catch (Exception e) {
            LOGGER.error("获取投资人[personId={},nameCn={}]职业生涯错误", investor.getPersonId(), investor.getNameCn(), e);
        }
        return careers;
    }

    private List<InvestCase> getCases(Investor investor) {
        List<InvestCase> cases = new ArrayList<>();
        int pageIndex = 1;
        try {
            httpPost.setURI(new URI("https://app.pedata.cn/PEDATA_APP_BACK/person/personInvestList?platform=ios&app_name=smt_app&platversion=4.0.1&device_info=iPhone11.0&device_version=iPhone6&ios_uid=" + getRandom(iosUids) + "&ios_idfa=" + getRandom(iosIdfas)));
            List<NameValuePair> params = new ArrayList<NameValuePair>() {{
                add(new BasicNameValuePair("limit", "10"));
                add(new BasicNameValuePair("personId", investor.getPersonId()));
                add(new BasicNameValuePair("start", "0"));
                add(new BasicNameValuePair("page", "1"));
            }};
            httpPost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
            String response = XHttpClient.doRequest(httpPost);
            JSONObject jsonObject = JSON.parseObject(response);
            if (!jsonObject.getBoolean("success"))
                throw new CommonException(ExceptionTypeEnum.Get_Investor_Case_ERROR);

            cases.addAll(JSON.parseArray(jsonObject.getString("result"), InvestCase.class));

            int total = jsonObject.getIntValue("total");
            for (pageIndex = 2; pageIndex <= (total % 10 == 0 ? (total / 10) : (total / 10 + 1)); pageIndex++) {
                try {
                    Thread.sleep(sleepTime);
                    params.set(params.size() - 2, new BasicNameValuePair("start", String.valueOf((pageIndex - 1) * 10)));
                    params.set(params.size() - 1, new BasicNameValuePair("page", String.valueOf(pageIndex)));
                    httpPost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
                    response = XHttpClient.doRequest(httpPost);
                    jsonObject = JSON.parseObject(response);
                    if (!jsonObject.getBoolean("success"))
                        throw new CommonException(ExceptionTypeEnum.Get_Investor_Case_ERROR);

                    cases.addAll(JSON.parseArray(jsonObject.getString("result"), InvestCase.class));
                } catch (Exception e) {
                    LOGGER.error("获取投资人[personId={},nameCn={},pageIndex={}]投资案例错误", investor.getPersonId(), investor.getNameCn(), pageIndex, e);
                }
            }
        } catch (Exception e) {
            LOGGER.error("获取投资人[personId={},nameCn={},pageIndex={}]投资案例错误", investor.getPersonId(), investor.getNameCn(), pageIndex, e);
        }
        return cases;
    }

    private String getRandom(List<String> list) {

        Random r = new Random();

        return list.get(r.nextInt(list.size()));

    }

    private void filterCase(Investor investor) {

        if (investor.getCaseList() == null || investor.getCaseList().size() == 0)
            return;

        for (InvestCase investCase : investor.getCaseList()) {

            String photoUrl = investCase.getEpLogo();
            try {
                String suffix = photoUrl.substring(photoUrl.lastIndexOf("."));
                String fileName = System.currentTimeMillis() + suffix;
                int rInt = r.nextInt(21);
                String filePath = PIC_ROOT + rInt + File.separator + fileName;
                Downloader.file(photoUrl, filePath);
                investCase.setEpLogo("http://www.qheeshow.com/pics/" + rInt + "/" + fileName);
            } catch (Exception e) {
                LOGGER.error("下载投资案例的logo失败[投资人id={},投资案例id={},logo={}]", investor.getPersonId(), investCase.getInvestId(), photoUrl, e);
                investCase.setEpLogo(photoUrl);
            }
        }
    }

}

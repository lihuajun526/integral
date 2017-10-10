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
        add("quickLogonKey=13802271587$CF138B01DABBD084DB1F4D27C6F2986B;JSESSIONID=8F6D34C4D176EDFE3AEFE03715EC2489;APP3_0Client=smtApp;");
        //add("quickLogonKey=13480138720$D7FFB59857129B266D5A3BC853C08265;JSESSIONID=2FFD56182ED4927BA52C196EC4372A0C;APP3_0Client=smtApp;");
        //add("quickLogonKey=13424192457$2DD52B43875D03D9BBF7211A4F1074DD;JSESSIONID=50262BFBF789E40442424E2AC0A2CCBD;APP3_0Client=smtApp;");
        //add("quickLogonKey=13148376469$33FCA19C09BFB92C02FC213A06AD0712;JSESSIONID=A4BDB3CC22E328A34F45421B25444A94;APP3_0Client=smtApp;");
        // 13994443294 Password@123
        //add("quickLogonKey=9c4d3706223d4641abb1e3f99cbca1e3$771E24A64B9C525EE6CE4A5999E64913;JSESSIONID=7067AB9C118DADC4D9413506A8AB1740;APP3_0Client=smtApp;");
        // 15235204743 Password@123
        //add("quickLogonKey=b9afc0c4fe7442de88ecf44b89903fc7$5A31DBD582F4B7E512ED360C3C0B0C0A;JSESSIONID=447657F88E4DE4700907AE30770FB413;APP3_0Client=smtApp;");
        // 17081430696 Password@123
        //add("quickLogonKey=125f575461bb48a5bb0a2b2a89da0854$EAEC4903AAD5E8094D2A04346A174E2A;JSESSIONID=05F24D0E267CAB0BE8F13CB4FF9062A4;APP3_0Client=smtApp;");
        // 13738047929 Password@123
        //add("quickLogonKey=2f1f163596e941daaa869a13257ba2e0$CCFAFA69AAC6D3D50A6AB47DAAE722D3;JSESSIONID=B6819C15864569BC417C82F0E407E403;APP3_0Client=smtApp;");
        // 18516090513 Password@123
        //add("quickLogonKey=a438bf270d37491f87e819b7e1b6dd6e$EC6CF446B3714F5CC1C5CDEA403721F5;JSESSIONID=8663B5B98E92B23E601050405F4D3E82;APP3_0Client=smtApp;");
        // 13113656998 2012111zhang
        add("quickLogonKey=13113656998$36A838636E0328B88F0488FC69D6D228;JSESSIONID=DF16D09E7C7A3868D37418E1F3C81814;APP3_0Client=smtApp;");
        // 13603528142 2012111zhang
        add("quickLogonKey=13603528142$55DAEC35E3E8ABBB0896F1A8C8375467;JSESSIONID=6B5D6B6AF11EE9D5A25AD3BBE0E423DB;APP3_0Client=smtApp;");
        // 13935206503 2012111zhang
        add("quickLogonKey=13935206503$A428C3A4CA88C6F465FF70C1FA574B2D;JSESSIONID=14E538D766375A73B71E33F448798FBB;APP3_0Client=smtApp;");
    }};
    private List<String> iosUids = new ArrayList<String>() {{
        add("7B75CB76-F2F2-46AA-775B-F1AD9461C3A7");
        add("7B75C477-F0F1-45AA-665C-F1BD9461C2A7");
        add("7B75C487-F3F1-45BA-926C-F1BD9461C2A8");
        add("7A75C487-D3F1-45BA-926C-F1BD9461C2A8");
        add("6B75C487-D3F1-45BA-926C-F1BD9461C2A8");
        add("5B75C487-D4F1-45BA-926C-F1BD9461C2A8");
        add("4B75C487-C4F1-45BA-926C-F1BD9461C2A8");
        add("3B75C487-A4F1-45BA-926C-F1BD9461C2A8");
        add("2B75C487-D4F1-45BA-926C-F2BD9461C2A8");
        add("1B75C487-D4F1-45BA-926C-F0BD9461C2A8");
    }};
    private List<String> iosIdfas = new ArrayList<String>() {{
        add("0443D612-A352-4553-98D8-77682DA24F0C");
        add("0443D611-A340-4443-78D8-99682DA24F0B");
        add("0443D613-A362-4223-87D8-88682DA24F0C");
        add("0543D613-A362-4223-87D8-88682DA24F0C");
        add("0643D613-B362-4223-87D8-88682DA24F0C");
        add("0743D613-C362-4223-87D8-88682DA24F0C");
        add("0843D613-D362-4223-87D8-88682DA24F0C");
        add("0943D613-E362-4223-87D8-88682DA24F0C");
        add("0413D613-F362-4223-87D8-88682DA24F0C");
        add("0423D613-G362-4223-87D8-88682DA24F0C");
    }};

    @Override
    public void exe(MQCrawlJob crawlJob) throws CommonException {

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
        }
    }

    private void init() {
        httpGet = new HttpGet();
        httpPost = new HttpPost();
        httpGet.setHeader("Cookie", getRandom(cookies));
        httpPost.setHeader("Cookie", getRandom(cookies));
    }

    private Investor getInvestor(String personId) throws CommonException {

        try {
            Investor investor = new Investor();
            httpGet.setURI(new URI("https://app.pedata.cn/PEDATA_APP_BACK/person/personDetail4?personId=" + personId + "&_=1506554198980"));
            String response = XHttpClient.doRequest(httpGet);
            JSONObject jsonObject = JSON.parseObject(response);
            if (!jsonObject.getBoolean("success")) {
                LOGGER.error("请求获取投资人[id={}]信息失败", personId);
                throw new CommonException(ExceptionTypeEnum.Get_Investor_Info_ERROR);
            }

            JSONObject result = jsonObject.getJSONObject("result");

            investor.setPersonId(personId);
            investor.setNameCn(result.getString("personNameCn"));
            investor.setNameEn(result.getString("personNameEn"));

            String photoUrl = result.getString("personPhoto");
            if (!StringUtils.isEmpty(photoUrl)) {
                try {
                    String suffix = photoUrl.substring(photoUrl.lastIndexOf("."));
                    String fileName = System.currentTimeMillis() + suffix;
                    int rInt = r.nextInt(42);
                    String filePath = PIC_ROOT + rInt + File.separator + fileName;
                    Downloader.file(photoUrl, filePath);
                    investor.setPhoto("http://www.qheeshow.com/pics/" + rInt + "/" + fileName);
                } catch (Exception e) {
                    LOGGER.error("下载投资人照片失败[投资人id={},照片url=]", investor.getPersonId(), photoUrl, e);
                    investor.setPhoto(photoUrl);
                }
            }
            String sPackInfo = result.getString("packInfo");
            if (!StringUtils.isEmpty(sPackInfo)) {
                String[] packInfo = sPackInfo.split("::");
                if (packInfo.length > 0)
                    investor.setCompany(packInfo[0]);
                if (packInfo.length > 1)
                    investor.setPosition(packInfo[1]);
            }
            investor.setInvestCount(result.getInteger("investCount"));
            String sRecentCase = result.getString("recentCase");
            if (!StringUtils.isEmpty(sRecentCase)) {
                String[] recentCase = sRecentCase.split("::");
                if (recentCase.length > 0)
                    investor.setRecentCaseName(recentCase[0]);
                if (recentCase.length > 1)
                    investor.setRecentCaseId(recentCase[1]);
            }

            investor.setInvestTrend(result.getString("investTrend"));
            investor.setProfile(result.getString("personDesc"));
            investor.setTel(result.getString("personTel"));
            investor.setEmail(result.getString("personMail"));

            return investor;
        } catch (Exception e) {
            LOGGER.error("解析投资人[id={}]信息失败", personId, e);
            throw new CommonException(ExceptionTypeEnum.Get_Investor_Info_ERROR);
        }
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
                Thread.sleep(1500);
                String suffix = photoUrl.substring(photoUrl.lastIndexOf("."));
                String fileName = System.currentTimeMillis() + suffix;
                int rInt = r.nextInt(42);
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

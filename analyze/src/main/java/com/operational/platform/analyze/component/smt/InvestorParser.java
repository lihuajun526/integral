package com.operational.platform.analyze.component.smt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.operational.platform.analyze.component.ToJsonParser;
import com.operational.platform.analyze.component.smt.bean.Career;
import com.operational.platform.analyze.component.smt.bean.InvestCase;
import com.operational.platform.analyze.component.smt.bean.Investor;
import com.operational.platform.analyze.exception.RequestLimitException;
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
    private final Integer sleepTime = 1200;
    private int count;
    @Autowired
    private AttackPageService attackPageService;

    private List<String> cookies = new ArrayList<String>() {{
        //add("quickLogonKey=327b48d47b1d4499b0dce39edd655398$436DC11901677FF37C36F86D0908B6E5;JSESSIONID=22DD85DC6A498F7C1E3AA0DD15E302A2;APP3_0Client=smtApp;");
        //add("quickLogonKey=5c61ac042cf4408fa6565b7040872a04$49DEB94BD715D372C551EF46AB97D0F7;JSESSIONID=6DF2CA406F708FBECB43752F78A65C86;APP3_0Client=smtApp;");
        //add("quickLogonKey=27f4b2892e0e47debc1d67d5cd66f3cb$6FD7091D8BD4D48CB33753733249791F;JSESSIONID=7C7D2C7FAB24FE3BD886839C2CA8BDC4;APP3_0Client=smtApp;");
        add("quickLogonKey=92664c89df9b4db2835cd65e26331aa5$EC251A83CB79F5BC4C780D67E5D1FFAA;JSESSIONID=BA70ABBD2C866BE1E371D044B520164A;APP3_0Client=smtApp;");
        add("quickLogonKey=c82ad633c2594dc6b85e654c9026d9eb$D3793FEBAECBE1721F883B52038A5EB1;JSESSIONID=5F774D55CB34196F1B05AE46CC2853D3;APP3_0Client=smtApp;");
        add("quickLogonKey=7c71defc7b304e2499f5380c01b9b243$707CD76CE4A8BF9F92DF09EB069908ED;JSESSIONID=AFC85CCB5A4FEAE6C591D3173771C9D5;APP3_0Client=smtApp;");
        add("quickLogonKey=e9ebc2e33c6040caaebfc98aaa2ac666$9B48E9054477ED2BC1A5A60686C2B612;JSESSIONID=4F7EA7E1220900ED87598F81DBCA4A48;APP3_0Client=smtApp;");
        add("quickLogonKey=e118f9f3480740fc88fb49ed99e5e854$8AD92830B3CB6F9EEE2460367707715F;JSESSIONID=49CBA38AC7903848DB691055B88E47FF;APP3_0Client=smtApp;");
        add("quickLogonKey=82c234d4e0c84775840a1bf246aa0d72$987A12981E86B237B64B8FBFE7877076;JSESSIONID=EF50F37E07E623E89F943582AC3A9A0A;APP3_0Client=smtApp;");
        add("quickLogonKey=3d64ce12eb194695b4893fae15f11b1a$63CDCD0E54760D7E8D777BE9C4514323;JSESSIONID=D907F5FB72B0DC9BB28BD2B2CF205CAD;APP3_0Client=smtApp;");
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
    public void exe(MQCrawlJob crawlJob) throws CommonException, RequestLimitException {

        if (StringUtils.isEmpty(crawlJob.getListPage()))
            return;
        if (crawlJob.getListPage().trim().equals("[]"))
            return;

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
            if (list.size() > 0) {
                Investor investor = new Investor();
                investor.setAttr(crawlJob.getAttr());
                attackPage.setAttr(JSON.toJSONString(investor));
                attackPage.setMd5(StrUtil.md5(attackPage.getAttr()));

                attackPageService.save(attackPage);
                continue;
            }

            if ((count++ % 200) == 0) {
                init();
            }

            Investor investor = getInvestor(personId);//获取投资人基本信息
            investor.setCareerList(getCareers(investor));//获取投资人职业生涯信息
            investor.setCaseList(getCases(investor));//获取投资人投资案例信息
            //filterCase(investor);
            investor.setAttr(crawlJob.getAttr());

            attackPage.setTitle(investor.getNameCn());
            attackPage.setBelong("smt");
            attackPage.setFlag(1);
            attackPage.setAttr(JSON.toJSONString(investor));
            attackPage.setCategory("移动端投资人");
            attackPage.setMd5(StrUtil.md5(attackPage.getAttr()));

            attackPageService.save(attackPage);
        }
    }

    private void init() throws RequestLimitException {
        httpGet = new HttpGet();
        httpPost = new HttpPost();
        if ((count / 200) == cookies.size())
            throw new RequestLimitException();

        httpGet.setHeader("Cookie", cookies.get(count / 200));
        httpPost.setHeader("Cookie", cookies.get(count / 200));
    }

    private Investor getInvestor(String personId) throws CommonException {

        try {
            Thread.sleep(sleepTime);
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
            investor.setPhoto(result.getString("personPhoto"));
            /*String photoUrl = result.getString("personPhoto");
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
            }*/
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
            Thread.sleep(1000);
            httpGet.setURI(new URI("https://app.pedata.cn/PEDATA_APP_BACK/percar/list4?personId=" + investor.getPersonId() + "&_=1506554199066"));
            String response = XHttpClient.doRequest(httpGet);
            JSONObject jsonObject = JSON.parseObject(response);
            if (!jsonObject.getBoolean("success"))
                throw new CommonException(ExceptionTypeEnum.Get_Investor_Career_ERROR);

            JSONArray array = jsonObject.getJSONArray("result");

            for (int i = 0; i < array.size(); i++) {
                Thread.sleep(500);
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
            Thread.sleep(1000);
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
                    Thread.sleep(500);
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

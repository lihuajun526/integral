package com.operational.platform.analyze.component.smt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.operational.platform.analyze.component.ToJsonParser;
import com.operational.platform.analyze.component.smt.bean.*;
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
@Component("orgParser")
public class OrgParser extends ToJsonParser {

    private final String PIC_ROOT = "C:\\workspace\\operational-platform\\analyze\\pics\\";
    private final Random r = new Random();
    private final Integer sleepTime = 2000;
    @Autowired
    private AttackPageService attackPageService;

    private List<String> cookies = new ArrayList<String>() {{
        //add("quickLogonKey=13802271587$CF138B01DABBD084DB1F4D27C6F2986B;JSESSIONID=8F6D34C4D176EDFE3AEFE03715EC2489;APP3_0Client=smtApp;");
        //add("quickLogonKey=13480138720$D7FFB59857129B266D5A3BC853C08265;JSESSIONID=2FFD56182ED4927BA52C196EC4372A0C;APP3_0Client=smtApp;");
        //add("quickLogonKey=13424192457$2DD52B43875D03D9BBF7211A4F1074DD;JSESSIONID=50262BFBF789E40442424E2AC0A2CCBD;APP3_0Client=smtApp;");
        //add("quickLogonKey=13148376469$33FCA19C09BFB92C02FC213A06AD0712;JSESSIONID=A4BDB3CC22E328A34F45421B25444A94;APP3_0Client=smtApp;");
        //*add("quickLogonKey=9c4d3706223d4641abb1e3f99cbca1e3$771E24A64B9C525EE6CE4A5999E64913;JSESSIONID=7067AB9C118DADC4D9413506A8AB1740;APP3_0Client=smtApp;");
        //*add("quickLogonKey=b9afc0c4fe7442de88ecf44b89903fc7$5A31DBD582F4B7E512ED360C3C0B0C0A;JSESSIONID=447657F88E4DE4700907AE30770FB413;APP3_0Client=smtApp;");
        //add("quickLogonKey=125f575461bb48a5bb0a2b2a89da0854$EAEC4903AAD5E8094D2A04346A174E2A;JSESSIONID=05F24D0E267CAB0BE8F13CB4FF9062A4;APP3_0Client=smtApp;");
        //add("quickLogonKey=2f1f163596e941daaa869a13257ba2e0$CCFAFA69AAC6D3D50A6AB47DAAE722D3;JSESSIONID=B6819C15864569BC417C82F0E407E403;APP3_0Client=smtApp;");
        //add("quickLogonKey=a438bf270d37491f87e819b7e1b6dd6e$EC6CF446B3714F5CC1C5CDEA403721F5;JSESSIONID=8663B5B98E92B23E601050405F4D3E82;APP3_0Client=smtApp;");
        //*add("quickLogonKey=13113656998$36A838636E0328B88F0488FC69D6D228;JSESSIONID=DF16D09E7C7A3868D37418E1F3C81814;APP3_0Client=smtApp;");
        //*add("quickLogonKey=13603528142$55DAEC35E3E8ABBB0896F1A8C8375467;JSESSIONID=6B5D6B6AF11EE9D5A25AD3BBE0E423DB;APP3_0Client=smtApp;");
        //add("quickLogonKey=13935206503$A428C3A4CA88C6F465FF70C1FA574B2D;JSESSIONID=14E538D766375A73B71E33F448798FBB;APP3_0Client=smtApp;");
        add("quickLogonKey=cc70f88754c64d61bcc03754f4273735$3B11DE49CC270A15AFCAF4B93EDB10A9;JSESSIONID=FB4072FD1C7DDE7132639DC3B7002261;APP3_0Client=smtApp;");
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

        JSONArray orgs = JSON.parseArray(listPage.trim());
        for (int i = 0; i < orgs.size(); i++) {
            JSONObject jsonObject = orgs.getJSONObject(i);
            String orgId = jsonObject.getString("orgId");
            AttackPage attackPage = new AttackPage();
            attackPage.setLink(orgId);
            attackPage.setPointid(crawlJob.getPointid());
            attackPage.setBelong("smt");
            attackPage.setFlag(1);
            attackPage.setCategory("移动端投资机构");

            List<AttackPage> list = attackPageService.listByPointAndLink(attackPage);
            if (list.size() > 0) {
                Organise organise = new Organise();
                organise.setAttr(crawlJob.getAttr());
                attackPage.setAttr(JSON.toJSONString(organise));
                attackPage.setMd5(StrUtil.md5(attackPage.getAttr()));

                attackPageService.save(attackPage);
                continue;
            }

            Organise organise = getOrg(orgId);//获取机构基本信息
            organise.setCaseList(getCases(organise));//获取机构投资案例信息
            //filterCase(organise);
            organise.setExitCaseList(getExitCases(organise));
            //filterExitCase(organise);
            organise.setOrgContactList(getContact(organise));
            organise.setManagerList(listManagers(organise));
            //filterManager(organise);
            organise.setFundList(getFunds(organise));
            organise.setStatistics(getStatistics(organise));
            organise.setInvestTrend(getInvestTrend(organise));
            organise.setExitTrend(getExitTrend(organise));
            organise.setAttr(crawlJob.getAttr());

            attackPage.setTitle(organise.getOrgNameCn());
            attackPage.setAttr(JSON.toJSONString(organise));
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

    private Organise getOrg(String orgId) throws CommonException {

        try {
            Thread.sleep(sleepTime);
            Organise organise = new Organise();
            httpGet.setURI(new URI("https://app.pedata.cn/PEDATA_APP_BACK/org/orgDetail4?encodeOrgId=" + orgId + "&_=1507600502887"));
            String response = XHttpClient.doRequest(httpGet);
            JSONObject jsonObject = JSON.parseObject(response);
            if (!jsonObject.getBoolean("success")) {
                LOGGER.error("请求获取机构[id={}]信息失败{}", orgId, jsonObject.getString("msg"));
                throw new CommonException(ExceptionTypeEnum.Get_Org_Info_ERROR);
            }

            JSONObject result = jsonObject.getJSONObject("result");

            organise.setOrgId(orgId);
            organise.setOrgNameCn(result.getString("orgNameCn"));
            organise.setFundCount(result.getInteger("fundCount"));
            organise.setInvestTotalCouont(result.getInteger("investTotalCouont"));
            organise.setOrgCapitalType(result.getString("orgCapitalType"));
            organise.setOrgDesc(result.getString("orgDesc"));
            organise.setOrgHeadquartersPlace(result.getString("orgHeadquartersPlace"));
            organise.setOrgManageCapital(result.getString("orgManageCapital"));
            organise.setOrgManageCapitalCurrency(result.getString("orgManageCapitalCurrency"));
            organise.setOrgSetupDate(result.getString("orgSetupDate"));
            organise.setOrgShortnameCn(result.getString("orgShortnameCn"));
            organise.setOrgType(result.getString("orgType"));
            organise.setOrgWeb(result.getString("orgWeb"));

            /*String photoUrl = result.getString("orgLogo");
            if (!StringUtils.isEmpty(photoUrl)) {
                try {
                    String suffix = photoUrl.substring(photoUrl.lastIndexOf(".")).toLowerCase();
                    String fileName = System.currentTimeMillis() + suffix;
                    int rInt = r.nextInt(42);
                    String filePath = PIC_ROOT + rInt + File.separator + fileName;
                    Downloader.file(photoUrl, filePath);
                    organise.setOrgLogo("http://www.qheeshow.com/pics/" + rInt + "/" + fileName);
                } catch (Exception e) {
                    LOGGER.error("下载机构logo失败[机构id={},照片url=]", organise.getOrgId(), photoUrl, e);
                    organise.setOrgLogo(photoUrl);
                }
            }*/
            organise.setOrgLogo(result.getString("orgLogo"));
            return organise;
        } catch (Exception e) {
            LOGGER.error("解析机构[id={}]信息失败", orgId, e);
            throw new CommonException(ExceptionTypeEnum.Get_Investor_Info_ERROR);
        }
    }

    private List<InvestCase> getCases(Organise organise) throws CommonException {
        List<InvestCase> cases = new ArrayList<>();
        int pageIndex = 1;
        try {
            Thread.sleep(sleepTime);
            httpPost.setURI(new URI("https://app.pedata.cn/PEDATA_APP_BACK/org/orgInvestList?platform=ios&app_name=smt_app&platversion=4.0.2&device_info=iPhone11.0.1&device_version=iPhone6&ios_uid=" + getRandom(iosUids) + "&ios_idfa=" + getRandom(iosIdfas)));
            List<NameValuePair> params = new ArrayList<NameValuePair>() {{
                add(new BasicNameValuePair("limit", "10"));
                add(new BasicNameValuePair("orgId", organise.getOrgId()));
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
                    LOGGER.error("获取机构[orgId={},nameCn={},pageIndex={}]投资案例错误", organise.getOrgId(), organise.getOrgNameCn(), pageIndex, e);
                    throw new CommonException(ExceptionTypeEnum.Get_Investor_Case_ERROR);
                }
            }
        } catch (Exception e) {
            LOGGER.error("获取机构[orgId={},nameCn={},pageIndex={}]投资案例错误", organise.getOrgId(), organise.getOrgNameCn(), pageIndex, e);
            throw new CommonException(ExceptionTypeEnum.Get_Investor_Case_ERROR);
        }
        return cases;
    }

    private List<ExitCase> getExitCases(Organise organise) throws CommonException {
        List<ExitCase> cases = new ArrayList<>();
        int pageIndex = 1;
        try {
            Thread.sleep(sleepTime);
            httpPost.setURI(new URI("https://app.pedata.cn/PEDATA_APP_BACK/org/orgExitList?platform=ios&app_name=smt_app&platversion=4.0.2&device_info=iPhone11.0.1&device_version=iPhone6&ios_uid=" + getRandom(iosUids) + "&ios_idfa=" + getRandom(iosIdfas)));
            List<NameValuePair> params = new ArrayList<NameValuePair>() {{
                add(new BasicNameValuePair("limit", "10"));
                add(new BasicNameValuePair("orgId", organise.getOrgId()));
                add(new BasicNameValuePair("start", "0"));
                add(new BasicNameValuePair("page", "1"));
            }};
            httpPost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
            String response = XHttpClient.doRequest(httpPost);
            JSONObject jsonObject = JSON.parseObject(response);
            if (!jsonObject.getBoolean("success"))
                throw new CommonException(ExceptionTypeEnum.Get_Exit_Case_ERROR);

            cases.addAll(JSON.parseArray(jsonObject.getString("result"), ExitCase.class));

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
                        throw new CommonException(ExceptionTypeEnum.Get_Exit_Case_ERROR);

                    cases.addAll(JSON.parseArray(jsonObject.getString("result"), ExitCase.class));
                } catch (Exception e) {
                    LOGGER.error("获取机构[orgId={},nameCn={},pageIndex={}]退出案例错误", organise.getOrgId(), organise.getOrgNameCn(), pageIndex, e);
                    throw new CommonException(ExceptionTypeEnum.Get_Exit_Case_ERROR);
                }
            }
        } catch (Exception e) {
            LOGGER.error("获取机构[orgId={},nameCn={},pageIndex={}]退出案例错误", organise.getOrgId(), organise.getOrgNameCn(), pageIndex, e);
            throw new CommonException(ExceptionTypeEnum.Get_Exit_Case_ERROR);
        }
        return cases;
    }

    private String getRandom(List<String> list) {

        Random r = new Random();

        return list.get(r.nextInt(list.size()));

    }

    private void filterCase(Organise organise) {

        if (organise.getCaseList() == null || organise.getCaseList().size() == 0)
            return;

        for (InvestCase investCase : organise.getCaseList()) {

            String photoUrl = investCase.getEpLogo();
            try {
                Thread.sleep(1500);
                String suffix = photoUrl.substring(photoUrl.lastIndexOf(".")).toLowerCase();
                String fileName = System.currentTimeMillis() + suffix;
                int rInt = r.nextInt(42);
                String filePath = PIC_ROOT + rInt + File.separator + fileName;
                Downloader.file(photoUrl, filePath);
                investCase.setEpLogo("http://www.qheeshow.com/pics/" + rInt + "/" + fileName);
            } catch (Exception e) {
                LOGGER.error("下载投资案例的logo失败[投资机构id={},投资案例id={},logo={}]", organise.getOrgId(), investCase.getInvestId(), photoUrl, e);
                investCase.setEpLogo(photoUrl);
            }
        }
    }

    private void filterExitCase(Organise organise) {

        if (organise.getExitCaseList() == null || organise.getExitCaseList().size() == 0)
            return;

        for (ExitCase exitCase : organise.getExitCaseList()) {

            String photoUrl = exitCase.getEpLogo();
            try {
                Thread.sleep(1500);
                String suffix = photoUrl.substring(photoUrl.lastIndexOf(".")).toLowerCase();
                String fileName = System.currentTimeMillis() + suffix;
                int rInt = r.nextInt(42);
                String filePath = PIC_ROOT + rInt + File.separator + fileName;
                Downloader.file(photoUrl, filePath);
                exitCase.setEpLogo("http://www.qheeshow.com/pics/" + rInt + "/" + fileName);
            } catch (Exception e) {
                LOGGER.error("下载退出案例的logo失败[投资机构id={},退出案例id={},logo={}]", organise.getOrgId(), exitCase.getExitId(), photoUrl, e);
                exitCase.setEpLogo(photoUrl);
            }
        }
    }

    private List<OrgContact> getContact(Organise organise) throws CommonException {
        try {
            Thread.sleep(sleepTime);
            httpGet.setURI(new URI("https://app.pedata.cn/PEDATA_APP_BACK/eventContact/getList?eventId=" + organise.getOrgId() + "&eventType=1%2C5&entityType=org&_=1507600503100"));

            String response = XHttpClient.doRequest(httpGet);
            JSONObject jsonObject = JSON.parseObject(response);
            if (!jsonObject.getBoolean("success"))
                throw new CommonException(ExceptionTypeEnum.Get_Contact_ERROR);

            return JSON.parseArray(jsonObject.getString("result"), OrgContact.class);
        } catch (Exception e) {
            LOGGER.error("获取机构[orgId={},nameCn={}]联系方式错误", organise.getOrgId(), organise.getOrgNameCn(), e);
            throw new CommonException(ExceptionTypeEnum.Get_Contact_ERROR);
        }
    }

    private List<Manager> listManagers(Organise organise) throws CommonException {

        List<Manager> managerList = new ArrayList<>();
        int pageIndex = 1;
        try {
            Thread.sleep(sleepTime);
            httpPost.setURI(new URI("https://app.pedata.cn/PEDATA_APP_BACK/org/orgManageList?platform=ios&app_name=smt_app&platversion=4.0.2&device_info=iPhone11.0.1&device_version=iPhone6&ios_uid=" + getRandom(iosUids) + "&ios_idfa=" + getRandom(iosIdfas)));
            List<NameValuePair> params = new ArrayList<NameValuePair>() {{
                add(new BasicNameValuePair("limit", "10"));
                add(new BasicNameValuePair("orgId", organise.getOrgId()));
                add(new BasicNameValuePair("start", "0"));
                add(new BasicNameValuePair("page", "1"));
            }};
            httpPost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
            String response = XHttpClient.doRequest(httpPost);
            JSONObject jsonObject = JSON.parseObject(response);
            if (!jsonObject.getBoolean("success"))
                throw new CommonException(ExceptionTypeEnum.Get_Managers_ERROR);

            managerList.addAll(JSON.parseArray(jsonObject.getString("result"), Manager.class));

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
                        throw new CommonException(ExceptionTypeEnum.Get_Managers_ERROR);

                    managerList.addAll(JSON.parseArray(jsonObject.getString("result"), Manager.class));
                } catch (Exception e) {
                    LOGGER.error("获取管理团队[orgId={},nameCn={},pageIndex={}]信息错误", organise.getOrgId(), organise.getOrgNameCn(), pageIndex, e);
                }
            }
        } catch (Exception e) {
            LOGGER.error("获取管理团队[orgId={},nameCn={},pageIndex={}]信息错误", organise.getOrgId(), organise.getOrgNameCn(), pageIndex, e);
            throw new CommonException(ExceptionTypeEnum.Get_Contact_ERROR);
        }

        return managerList;
    }

    private void filterManager(Organise organise) {

        if (organise.getManagerList() == null || organise.getManagerList().size() == 0)
            return;

        for (Manager manager : organise.getManagerList()) {

            String photoUrl = manager.getPersonPhoto();
            try {
                Thread.sleep(1500);
                String suffix = photoUrl.substring(photoUrl.lastIndexOf(".")).toLowerCase();
                String fileName = System.currentTimeMillis() + suffix;
                int rInt = r.nextInt(42);
                String filePath = PIC_ROOT + rInt + File.separator + fileName;
                Downloader.file(photoUrl, filePath);
                manager.setPersonPhoto("http://www.qheeshow.com/pics/" + rInt + "/" + fileName);
            } catch (Exception e) {
                LOGGER.error("下载管理团队照片失败[投资机构id={},name={},photo={}]", organise.getOrgId(), manager.getPersonNameCn(), photoUrl, e);
                manager.setPersonPhoto(photoUrl);
            }
        }
    }

    private List<Fund> getFunds(Organise organise) throws CommonException {
        List<Fund> funds = new ArrayList<>();

        int pageIndex = 1;
        try {
            Thread.sleep(sleepTime);
            httpPost.setURI(new URI("https://app.pedata.cn/PEDATA_APP_BACK/org/orgFundList?platform=ios&app_name=smt_app&platversion=4.0.2&device_info=iPhone11.0.1&device_version=iPhone6&ios_uid=" + getRandom(iosUids) + "&ios_idfa=" + getRandom(iosIdfas)));
            List<NameValuePair> params = new ArrayList<NameValuePair>() {{
                add(new BasicNameValuePair("limit", "10"));
                add(new BasicNameValuePair("orgId", organise.getOrgId()));
                add(new BasicNameValuePair("start", "0"));
                add(new BasicNameValuePair("page", "1"));
            }};
            httpPost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
            String response = XHttpClient.doRequest(httpPost);
            JSONObject jsonObject = JSON.parseObject(response);
            if (!jsonObject.getBoolean("success"))
                throw new CommonException(ExceptionTypeEnum.Get_Funds_ERROR);

            funds.addAll(JSON.parseArray(jsonObject.getString("result"), Fund.class));

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
                        throw new CommonException(ExceptionTypeEnum.Get_Funds_ERROR);

                    funds.addAll(JSON.parseArray(jsonObject.getString("result"), Fund.class));
                } catch (Exception e) {
                    LOGGER.error("获取基金[orgId={},nameCn={},pageIndex={}]错误", organise.getOrgId(), organise.getOrgNameCn(), pageIndex, e);
                }
            }
        } catch (Exception e) {
            LOGGER.error("获取基金[orgId={},nameCn={},pageIndex={}]错误", organise.getOrgId(), organise.getOrgNameCn(), pageIndex, e);
            throw new CommonException(ExceptionTypeEnum.Get_Funds_ERROR);
        }
        return funds;
    }

    private Statistics getStatistics(Organise organise) throws CommonException {
        try {
            Thread.sleep(sleepTime);
            httpGet.setURI(new URI("https://app.pedata.cn/PEDATA_APP_BACK/org/investStrategy?encodeOrgId=" + organise.getOrgId() + "&_=1507600503207"));

            String response = XHttpClient.doRequest(httpGet);
            JSONObject jsonObject = JSON.parseObject(response);
            if (!jsonObject.getBoolean("success"))
                throw new CommonException(ExceptionTypeEnum.Get_Statistics_ERROR);

            return JSON.parseObject(response, Statistics.class);
        } catch (Exception e) {
            LOGGER.error("获取统计信息[orgId={},nameCn={}]错误", organise.getOrgId(), organise.getOrgNameCn(), e);
            throw new CommonException(ExceptionTypeEnum.Get_Statistics_ERROR);
        }
    }

    private InvestTrend getInvestTrend(Organise organise) throws CommonException {
        try {
            Thread.sleep(sleepTime);
            httpGet.setURI(new URI("https://app.pedata.cn/PEDATA_APP_BACK/org/statInvest?encodeOrgId=" + organise.getOrgId() + "&_=1507600503211"));

            String response = XHttpClient.doRequest(httpGet);
            JSONObject jsonObject = JSON.parseObject(response);
            if (!jsonObject.getBoolean("success"))
                throw new CommonException(ExceptionTypeEnum.Get_Invest_Trend_ERROR);

            return JSON.parseObject(response, InvestTrend.class);
        } catch (Exception e) {
            LOGGER.error("获取投资趋势信息[orgId={},nameCn={}]错误", organise.getOrgId(), organise.getOrgNameCn(), e);
            throw new CommonException(ExceptionTypeEnum.Get_Invest_Trend_ERROR);
        }
    }

    private ExitTrend getExitTrend(Organise organise) throws CommonException {
        try {
            Thread.sleep(sleepTime);
            httpGet.setURI(new URI("https://app.pedata.cn/PEDATA_APP_BACK/org/statExit?encodeOrgId=" + organise.getOrgId() + "&_=1507600503470"));

            String response = XHttpClient.doRequest(httpGet);
            JSONObject jsonObject = JSON.parseObject(response);
            if (!jsonObject.getBoolean("success"))
                throw new CommonException(ExceptionTypeEnum.Get_Exit_Trend_ERROR);

            return JSON.parseObject(response, ExitTrend.class);
        } catch (Exception e) {
            LOGGER.error("获取退出趋势信息[orgId={},nameCn={}]错误", organise.getOrgId(), organise.getOrgNameCn(), e);
            throw new CommonException(ExceptionTypeEnum.Get_Exit_Trend_ERROR);
        }
    }
}

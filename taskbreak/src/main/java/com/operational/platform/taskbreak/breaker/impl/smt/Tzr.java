package com.operational.platform.taskbreak.breaker.impl.smt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.operational.platform.common.constant.ExceptionTypeEnum;
import com.operational.platform.common.exception.CommonException;
import com.operational.platform.taskbreak.bean.BreakTask;
import com.operational.platform.taskbreak.bean.ListPage;
import com.operational.platform.taskbreak.breaker.ABreaker;
import com.operational.platform.common.util.XHttpClient;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by lihuajun on 2017/9/27.
 */
@Component("tzr")
public class Tzr extends ABreaker {

    private int sleepTime = 3000;

    @Override
    public void exe(BreakTask breakTask, Map<String, String> attr) throws CommonException {

        List<String> cookies = new ArrayList<String>() {{
            add("quickLogonKey=cc70f88754c64d61bcc03754f4273735$3B11DE49CC270A15AFCAF4B93EDB10A9;JSESSIONID=FB4072FD1C7DDE7132639DC3B7002261;APP3_0Client=smtApp;");
            add("quickLogonKey=e118f9f3480740fc88fb49ed99e5e854$8AD92830B3CB6F9EEE2460367707715F;JSESSIONID=49CBA38AC7903848DB691055B88E47FF;APP3_0Client=smtApp;");
            add("quickLogonKey=e9ebc2e33c6040caaebfc98aaa2ac666$9B48E9054477ED2BC1A5A60686C2B612;JSESSIONID=4F7EA7E1220900ED87598F81DBCA4A48;APP3_0Client=smtApp;");
            add("quickLogonKey=7c71defc7b304e2499f5380c01b9b243$707CD76CE4A8BF9F92DF09EB069908ED;JSESSIONID=AFC85CCB5A4FEAE6C591D3173771C9D5;APP3_0Client=smtApp;");
        }};
        List<String> iosUids = new ArrayList<String>() {{
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
        List<String> iosIdfas = new ArrayList<String>() {{
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

        String url = "https://app.pedata.cn/PEDATA_APP_BACK/person/list?platform=ios&app_name=smt_app&platversion=4.0.1&device_info=iPhone11.0&device_version=iPhone6&ios_uid=%s&ios_idfa=%s";

        String[] str1 = attr.get("investorType").split("-");
        String[] str2 = attr.get("area").split("-");
        String[] str3 = attr.get("industry").split("-");

        HttpPost httpPost = new HttpPost(String.format(url, getRandom(iosUids), getRandom(iosIdfas)));
        httpPost.setHeader("Cookie", getRandom(cookies));
        List<NameValuePair> params = new ArrayList<NameValuePair>() {{
            if (!str1[0].trim().equals("0")) {
                add(new BasicNameValuePair("personType", str1[0].trim()));
            }
            if (!str2[0].trim().equals("0")) {
                add(new BasicNameValuePair("district", str2[0].trim()));
            }
            if (!str3[0].trim().equals("0")) {
                add(new BasicNameValuePair("tagId", str3[0].trim()));
            }
            add(new BasicNameValuePair("limit", "10"));
            add(new BasicNameValuePair("start", "0"));
        }};
        int pageIndex = 1;
        try {
            Thread.sleep(sleepTime);
            httpPost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
            String response = XHttpClient.doRequest(httpPost);
            JSONObject jsonObject = JSON.parseObject(response);
            if (!jsonObject.getBoolean("success")) {
                LOGGER.error("请求[页数={},投资人类型={},投资人所在地={},投资行业={}]错误", pageIndex, str1[1], str2[1], str3[1]);
                throw new CommonException(ExceptionTypeEnum.Get_Investor_List_ERROR);
            }
            if (jsonObject.getIntValue("total") == 0) {
                LOGGER.debug("[页数={},投资人类型={},投资人所在地={},投资行业={}]记录数为0", pageIndex, str1[1], str2[1], str3[1]);
                return;
            }
            ListPage listPage = new ListPage();
            listPage.getAttr().put("投资人类型", str1[1]);
            listPage.getAttr().put("投资人所在地", str2[1]);
            listPage.getAttr().put("投资行业", str3[1]);
            listPage.setPageIndex(pageIndex);
            listPage.setContent(jsonObject.getString("result"));
            saveToMq(listPage, breakTask);

            int total = jsonObject.getIntValue("total");
            pageIndex++;
            for (; pageIndex <= (total % 10 == 0 ? (total / 10) : (total / 10 + 1)); pageIndex++) {
                Thread.sleep(sleepTime);
                params.set(params.size() - 1, new BasicNameValuePair("start", String.valueOf((pageIndex - 1) * 10)));
                httpPost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
                response = XHttpClient.doRequest(httpPost);
                jsonObject = JSON.parseObject(response);
                if (!jsonObject.getBoolean("success")) {
                    LOGGER.error("请求[页数={},投资人类型={},投资人所在地={},投资行业={}]错误", pageIndex, str1[1], str2[1], str3[1]);
                    throw new CommonException(ExceptionTypeEnum.Get_Investor_List_ERROR);
                }
                listPage = new ListPage();
                listPage.getAttr().put("投资人类型", str1[1]);
                listPage.getAttr().put("投资人所在地", str2[1]);
                listPage.getAttr().put("投资行业", str3[1]);
                listPage.setPageIndex(pageIndex);
                listPage.setContent(jsonObject.getString("result"));
                saveToMq(listPage, breakTask);
            }

        } catch (Exception e) {
            LOGGER.error("请求[页数={},投资人类型={},投资人所在地={},投资行业={}]错误", pageIndex, str1[1], str2[1], str3[1], e);
            throw new CommonException(ExceptionTypeEnum.Get_Investor_List_ERROR);
        }
    }

    private String getRandom(List<String> list) {

        Random r = new Random();

        return list.get(r.nextInt(list.size()));

    }

}

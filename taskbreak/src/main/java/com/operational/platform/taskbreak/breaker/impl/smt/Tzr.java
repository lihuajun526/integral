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
            //add("quickLogonKey=13802271587$CF138B01DABBD084DB1F4D27C6F2986B;JSESSIONID=8F6D34C4D176EDFE3AEFE03715EC2489;APP3_0Client=smtApp;");
            //add("quickLogonKey=13480138720$D7FFB59857129B266D5A3BC853C08265;JSESSIONID=2FFD56182ED4927BA52C196EC4372A0C;APP3_0Client=smtApp;");
            //add("quickLogonKey=13424192457$2DD52B43875D03D9BBF7211A4F1074DD;JSESSIONID=50262BFBF789E40442424E2AC0A2CCBD;APP3_0Client=smtApp;");
            //add("quickLogonKey=13148376469$33FCA19C09BFB92C02FC213A06AD0712;JSESSIONID=A4BDB3CC22E328A34F45421B25444A94;APP3_0Client=smtApp;");
            //add("quickLogonKey=9c4d3706223d4641abb1e3f99cbca1e3$771E24A64B9C525EE6CE4A5999E64913;JSESSIONID=7067AB9C118DADC4D9413506A8AB1740;APP3_0Client=smtApp;");
            //add("quickLogonKey=b9afc0c4fe7442de88ecf44b89903fc7$5A31DBD582F4B7E512ED360C3C0B0C0A;JSESSIONID=447657F88E4DE4700907AE30770FB413;APP3_0Client=smtApp;");
            //add("quickLogonKey=125f575461bb48a5bb0a2b2a89da0854$EAEC4903AAD5E8094D2A04346A174E2A;JSESSIONID=05F24D0E267CAB0BE8F13CB4FF9062A4;APP3_0Client=smtApp;");
            //add("quickLogonKey=2f1f163596e941daaa869a13257ba2e0$CCFAFA69AAC6D3D50A6AB47DAAE722D3;JSESSIONID=B6819C15864569BC417C82F0E407E403;APP3_0Client=smtApp;");
            //add("quickLogonKey=a438bf270d37491f87e819b7e1b6dd6e$EC6CF446B3714F5CC1C5CDEA403721F5;JSESSIONID=8663B5B98E92B23E601050405F4D3E82;APP3_0Client=smtApp;");
            //add("quickLogonKey=13113656998$36A838636E0328B88F0488FC69D6D228;JSESSIONID=DF16D09E7C7A3868D37418E1F3C81814;APP3_0Client=smtApp;");
            //add("quickLogonKey=13603528142$55DAEC35E3E8ABBB0896F1A8C8375467;JSESSIONID=6B5D6B6AF11EE9D5A25AD3BBE0E423DB;APP3_0Client=smtApp;");
            //add("quickLogonKey=13935206503$A428C3A4CA88C6F465FF70C1FA574B2D;JSESSIONID=14E538D766375A73B71E33F448798FBB;APP3_0Client=smtApp;");
            add("quickLogonKey=cc70f88754c64d61bcc03754f4273735$3B11DE49CC270A15AFCAF4B93EDB10A9;JSESSIONID=FB4072FD1C7DDE7132639DC3B7002261;APP3_0Client=smtApp;");
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
                throw new CommonException(ExceptionTypeEnum.Get_Investor_List_ERROR);
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

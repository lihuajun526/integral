package com.operational.platform.taskbreak.breaker.impl.smt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
import java.util.Random;

/**
 * Created by lihuajun on 2017/9/27.
 */
@Component("tzr")
public class Tzr extends ABreaker {

    private int sleepTime = 3000;

    @Override
    protected List<ListPage> getListPage() {

        List<String> cookies = new ArrayList<String>() {{
            add("quickLogonKey=13148376469$020EA7AC6CE1ED064320F99286B0C601;JSESSIONID=F0787F1981D86A93A84938A32C6093AA;APP3_0Client=smtApp;");
            //add("JSESSIONID=52690298E7F588DC5476E2C6F4E2B50E; firstEnterUrlInSession=http%3A//pe.pedata.cn/addUserInfoMember.action; VisitorCapacity=1; USER_LOGIN_ID=59594077-8D22-4786-B16F-6C0C75B88A64; USER_LOGIN_NAME_KEY=13480138720; IS_CS_KEY=true; USER_LOGIN_NAME=13480138720; USER_LOGIN_LANGUAGE=zh_CN; USER_CLIENT_ID=\"\"; operatorId=31183; pageReferrInSession=http%3A//pe.pedata.cn/addUserInfoMember.action; request_locale=zh_CN");
        }};
        List<String> iosUids = new ArrayList<String>() {{
            add("7B75CB76-F2F2-46AA-885B-F1AD9461C3A7");
            add("7B75C477-F0F1-45AA-835C-F1BD9461C2A7");
            add("7B75C487-F3F1-45BA-836C-F1BD9461C2A8");
        }};
        List<String> iosIdfas = new ArrayList<String>() {{
            add("0443D612-A352-4553-89D8-77682DA24F0C");
            add("0443D611-A340-4443-89D8-99682DA24F0B");
            add("0443D613-A362-4223-89D8-88682DA24F0C");
        }};

        String[] investorTypes = "1050-天使投资人#1052-机构投资人#2499-机构非投资人员#1053-分析师#1051-企业家/创业者#1049-律师#1054-会计师#1055-媒体记者#0-全部".split("#");
        String[] areas = "237-北京市#238-天津市#543-河北省#282-山西省#616-内蒙古#413-辽宁省#181-吉林省#624-黑龙江省#239-上海市#542-江苏省#73-浙江省#276-安徽省#382-福建省#532-江西省#508-山东省#638-河南省#115-湖北省#37-湖南省#488-广东省#66-广西省#492-海南省#240-重庆市#503-四川省#497-贵州省#723-云南省#166-西藏#304-陕西省#25-甘肃省#139-青海省#364-宁夏#123-新疆#241-台湾#234-香港#235-澳门#0-全部".split("#");
        String[] industrys = "1-移动互联网#112-汽车交通#13-医疗健康#27-文化娱乐#101-O2O#28-大数据#109-企业服务#111-云服务#31-教育#107-传媒#3-本地生活#14-旅游服务#5-社交网络#6-体育#15-游戏#8-P2P服务#17-可穿戴设备#23-工具软件#24-互联网金融#30-房产服务#39-金融#40-IT服务#12-装修装潢#102-B2C#103-食品饮料#104-环保#105-先进制造#106-传统硬件#108-新材料#110-电子设备#116-企业信息化#117-新能源#118-物联网#119-B2B#121-智能硬件#124-物流配送#125-影视动漫#128-母婴#129-C2C#130-招聘求职#148-家政服务#149-现代农业#172-生鲜#166-政府引导基金#176-VR/AR#181-人工智能#185-网络社区#186-机械制造#188-生物医药#16-广告营销#206-新三板#19-3D打印#0-全部".split("#");

        String url = "https://app.pedata.cn/PEDATA_APP_BACK/person/list?platform=ios&app_name=smt_app&platversion=4.0.1&device_info=iPhone11.0&device_version=iPhone6&ios_uid=%s&ios_idfa=%s";
        List<ListPage> listPages = new ArrayList<>();
        for (String investorType : investorTypes) {
            String[] str1 = investorType.split("-");
            for (String area : areas) {
                String[] str2 = area.split("-");
                for (String industry : industrys) {
                    String[] str3 = industry.split("-");
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
                            continue;
                        }
                        if (jsonObject.getIntValue("total") == 0) {
                            LOGGER.info("[页数={},投资人类型={},投资人所在地={},投资行业={}]记录数为0", pageIndex, str1[1], str2[1], str3[1]);
                            continue;
                        }
                        ListPage listPage = new ListPage();
                        listPage.getAttr().put("投资人类型", str1[1]);
                        listPage.getAttr().put("投资人所在地", str2[1]);
                        listPage.getAttr().put("投资行业", str3[1]);
                        listPage.setPageIndex(pageIndex);
                        listPage.setContent(jsonObject.getString("result"));
                        listPages.add(listPage);

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
                                continue;
                            }
                            listPage = new ListPage();
                            listPage.getAttr().put("投资人类型", str1[1]);
                            listPage.getAttr().put("投资人所在地", str2[1]);
                            listPage.getAttr().put("投资行业", str3[1]);
                            listPage.setPageIndex(pageIndex);
                            listPage.setContent(jsonObject.getString("result"));
                            listPages.add(listPage);
                        }

                    } catch (Exception e) {
                        LOGGER.error("请求[页数={},投资人类型={},投资人所在地={},投资行业={}]错误", pageIndex, str1[1], str2[1], str3[1], e);
                    }
                }
            }
        }

        return listPages;
    }

    private String getRandom(List<String> list) {

        Random r = new Random();

        return list.get(r.nextInt(list.size()));

    }

}

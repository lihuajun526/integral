package com.operational.platform.taskbreak.breaker.impl.smt;

import com.alibaba.fastjson.JSON;
import com.operational.platform.common.constant.ExceptionTypeEnum;
import com.operational.platform.common.exception.CommonException;
import com.operational.platform.common.exception.RequestException;
import com.operational.platform.common.util.XHttpClient;
import com.operational.platform.dbservice.model.AttackPage;
import com.operational.platform.dbservice.service.AttackPageService;
import com.operational.platform.taskbreak.bean.BreakTask;
import com.operational.platform.taskbreak.bean.Investor;
import com.operational.platform.taskbreak.breaker.ABreaker;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by lihuajun on 2017/9/27.
 */
@Component("pcTzr")
public class PcTzr extends ABreaker {

    private int sleepTime = 4000;
    @Autowired
    private AttackPageService attackPageService;

    private String cookie = "JSESSIONID=5652F5EC2FBEAEB7D7ED2473BE2A5562; firstEnterUrlInSession=http%3A//pe.pedata.cn/addUserInfoMember.action; VisitorCapacity=1; USER_LOGIN_ID=27f4b2892e0e47debc1d67d5cd66f3cb; USER_LOGIN_NAME_KEY=27f4b2892e0e47debc1d67d5cd66f3cb; IS_CS_KEY=true; USER_LOGIN_NAME=13738546518; USER_LOGIN_LANGUAGE=zh_CN; USER_CLIENT_ID=\"\"; operatorId=31183; pageReferrInSession=http%3A//pe.pedata.cn/addUserInfoMember.action; request_locale=zh_CN";

    @Override
    public void exe(BreakTask breakTask, Map<String, String> attr) throws CommonException {

        List<String> cookies = new ArrayList<String>() {{
            add(cookie);
        }};

        String[] str1 = attr.get("investorType").split("-");
        String[] str2 = attr.get("area").split("-");
        String[] str3 = attr.get("industry").split("-");

        String url = "http://pe.pedata.cn/getListPerson.action";

        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Cookie", getRandom(cookies));
        List<NameValuePair> params = initParam();
        if (!str1[0].equals("0"))
            params.add(new BasicNameValuePair("param.personType", str1[0]));
        if (!str2[0].equals("0"))
            params.add(new BasicNameValuePair("param.headquartersPlace", str2[0]));
        if (!str3[0].equals("0"))
            params.add(new BasicNameValuePair("param.epTag", str3[0]));
        params.add(new BasicNameValuePair("param.currentPage", "1"));
        int pageIndex = 1;
        try {
            Thread.sleep(sleepTime);
            httpPost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
            String response = XHttpClient.doRequest(httpPost);
            Document doc = Jsoup.parse(response);

            validate(doc);

            if (isEmpty(doc, attr))
                return;

            List<Investor> investorList = listInvestors(doc);
            saveAll(investorList, attr);

            int pageCount = getPageCount(doc, attr);pageIndex++;
            for (; pageIndex <= pageCount; pageIndex++) {
                try {
                    Thread.sleep(sleepTime);
                    params.set(params.size() - 1, new BasicNameValuePair("param.currentPage", String.valueOf(pageIndex)));
                    httpPost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
                    response = XHttpClient.doRequest(httpPost);
                    doc = Jsoup.parse(response);
                    validate(doc);
                    investorList = listInvestors(doc);
                    saveAll(investorList, attr);
                } catch (Exception e) {
                    LOGGER.error("请求[页数={},投资人类型={},投资人所在地={},投资行业={}]错误", pageIndex, str1[1], str2[1], str3[1], e);
                    continue;
                }
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

    private List<NameValuePair> initParam() {
        String[] sParams = "param.quick=&param.orderBy=desc&param.orderByField=invest_date_latest&param.showInfo=true&param.search_type_check=ownerCheck%2CconditionsUl%2CconditionsUl2%2CconditionsUl3%2C&param.person_name=&param.person_birth_date_begin=&param.person_birth_date_end=&param.investCount=&param.exitCount=&param.org_manage_capital_begin=&param.org_manage_capital_end=&param.orgBackground=&param.org_record=&param.org_setup_date_begin=&param.org_setup_date_end=&param.person_career_onduty=&param.person_career_team_status=&param.epValue__start=&param.epValue__end=&param.epSetupDate_begin=&param.epSetupDate_end=&param.invest_money_begin=&param.invest_money_end=&param.invest_stake_start=&param.invest_stake_end=&param.invest_enterprise_start=&param.invest_enterprise_end=&param.invest_date_begin=&param.invest_date_end=&param.column=0&param.column=1&param.column=2&param.column=3&param.column=4&param.column=5&param.column=6&param.column=7&param.column=8".split("&");
        List<NameValuePair> params = new ArrayList<NameValuePair>() {{
            for (String str : sParams) {
                String[] strs = str.split("=");
                if (strs.length == 1)
                    add(new BasicNameValuePair(strs[0], ""));
                else
                    add(new BasicNameValuePair(strs[0], strs[1]));
            }
        }};
        return params;
    }

    private List<Investor> listInvestors(Document doc) {
        List<Investor> list = new ArrayList<>();

        Elements elements = doc.select("div.leftTableDivID>table.table>tbody>tr");
        for (Element element : elements) {
            Investor investor = new Investor();
            if (element.select("td>a").size() == 0) {
                investor.setName(element.select("td").get(0).attr("title"));
                list.add(investor);
                continue;
            }
            Element e = element.select("td>a").get(0);
            investor.setName(e.text().trim());
            investor.setLink("http://pe.pedata.cn/" + e.attr("href"));

            list.add(investor);
        }

        elements = doc.select("div#rightBodyDivID>div>table>tbody>tr");
        for (int i = 0; i < elements.size(); i++) {
            Investor investor = list.get(i);
            Element e = elements.get(i);
            investor.setCompany(e.select("td:eq(1)").get(0).attr("title").trim());
            investor.setPosition(e.select("td:eq(2)").get(0).text().trim());
            investor.setAge(e.select("td:eq(3)").get(0).text().trim());
            investor.setTel(e.select("td:eq(4)").get(0).text().trim());
            investor.setEmail(e.select("td:eq(5)").get(0).attr("title").trim());
            investor.setRecentInvestTime(e.select("td:eq(6)").get(0).text().trim());
        }

        return list;
    }

    private void saveAll(List<Investor> investorList, Map<String, String> attr) throws InterruptedException, RequestException, URISyntaxException {

        String[] str1 = attr.get("investorType").split("-");
        String[] str2 = attr.get("area").split("-");
        String[] str3 = attr.get("industry").split("-");

        HttpGet httpGet = new HttpGet();
        httpGet.setHeader("Cookie", cookie);
        for (Investor investor : investorList) {

            if (StringUtils.isEmpty(investor.getLink()))
                continue;

            investor.getAttr().put("投资人类型", str1[1]);
            investor.getAttr().put("投资人所在地", str2[1]);
            investor.getAttr().put("投资行业", str3[1]);

            AttackPage attackPage = new AttackPage();
            attackPage.setLink(investor.getLink());
            attackPage.setPointid(44);
            attackPage.setTitle(investor.getName());
            attackPage.setCategory("pc端投资人");
            attackPage.setBelong("smt");

            /*List<AttackPage> list = attackPageService.listByPointAndLink(attackPage);
            if (list.size() > 0) {
                attackPage.setAttr(JSON.toJSONString(investor));
            } else {
                Thread.sleep(sleepTime);
                httpGet.setURI(new URI(investor.getLink()));
                String content = XHttpClient.doRequest(httpGet);

                try {
                    validate(Jsoup.parse(content));
                    investor.setContent(content);
                } catch (Exception e) {
                    LOGGER.error("请求详细页失败[url={}]", investor.getLink());
                }

                attackPage.setAttr(JSON.toJSONString(investor));
            }*/

            attackPage.setAttr(JSON.toJSONString(investor));
            attackPageService.save(attackPage);
        }
    }

    private boolean isEmpty(Document doc, Map<String, String> attr) throws CommonException {
        String[] str1 = attr.get("investorType").split("-");
        String[] str2 = attr.get("area").split("-");
        String[] str3 = attr.get("industry").split("-");
        try {
            int count = doc.select("div.leftTableDivID>table.table>tbody>tr").size();
            if (count == 0)
                return true;
        } catch (Exception e) {
            LOGGER.error("获取列表页是否为空失败[投资人类型={},投资人所在地={},投资行业={}]", str1[1], str2[1], str3[1], e);
            throw new CommonException(ExceptionTypeEnum.Get_List_Is_Empty_ERROR);
        }
        return false;
    }

    private int getPageCount(Document doc, Map<String, String> attr) throws CommonException {

        String[] str1 = attr.get("investorType").split("-");
        String[] str2 = attr.get("area").split("-");
        String[] str3 = attr.get("industry").split("-");
        try {
            String str = doc.select("div.page").text();
            str = str.split("条记录")[0];
            str = str.replaceAll("&nbsp;", "").replaceAll("总共", "").replaceAll(" ", "").trim();
            int count = Integer.valueOf(str);
            if (count % 25 == 0)
                return count / 25;
            else
                return (count / 25) + 1;
        } catch (Exception e) {
            LOGGER.error("获取列表页数失败[投资人类型={},投资人所在地={},投资行业={}]", str1[1], str2[1], str3[1], e);
            throw new CommonException(ExceptionTypeEnum.Get_Page_Count_ERROR);
        }
    }

    private void validate(Document doc) throws CommonException {
        if (doc.select("body>div.error_all2").size() > 0) {
            throw new CommonException(ExceptionTypeEnum.Get_Investor_List_ERROR);
        }
    }
}

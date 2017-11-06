package com.operational.platform.operate.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.operational.platform.common.util.XHttpClient;
import com.operational.platform.dbservice.model.AttackPage;
import com.operational.platform.dbservice.service.AttackPageService;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.util.*;

/**
 * Created by lihuajun on 16-7-19.
 */
@Component
public class TimerCrawlQQUser {

    @Autowired
    private AttackPageService attackPageService;

    private static final Logger LOGGER = LoggerFactory.getLogger(TimerCrawlQQUser.class);

    public void execute() {

        String[] provinces = "13-河北#14-山西#15-内蒙古#21-辽宁#22-吉林#23-黑龙江#32-江苏#34-安徽#35-福建#36-江西#37-山东#41-河南#43-湖南#45-广西#46-海南#51-四川#52-贵州#53-云南#54-西藏#61-陕西#62-甘肃#63-青海#64-宁夏#65-新疆".split("#");
        String province = provinces[new Random().nextInt(provinces.length)].split("-")[0];
        try {
            HttpPost httpPost = new HttpPost("http://cgi.find.qq.com/qqfind/buddy/search_v3");
            httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) QQ/8.9.5.22062 Chrome/43.0.2357.134 Safari/537.36 QBCore/3.43.716.400 QQBrowser/9.0.2524.400");
            httpPost.setHeader("Cookie", "RK=gdnjqWhfmW; pt2gguin=o3198493143; ptcz=19c0c40506b2aa0e1ab5df02f2df887f141f7eaa41ac0bb92cc23abeeb29e5f9; _qpsvr_localtk=tk3866; pgv_pvid=4756510416; uin=o3198493143; skey=ZvTA1O2aJp; itkn=2061529490");
            httpPost.setHeader("Referer","http://find.qq.com/index.html?version=1&im_version=5545&width=910&height=610&search_target=0");
            List<NameValuePair> params = new ArrayList<NameValuePair>() {{
                add(new BasicNameValuePair("num", "20"));
                add(new BasicNameValuePair("page", "0"));
                add(new BasicNameValuePair("sessionid", "0"));
                add(new BasicNameValuePair("keyword", ""));
                add(new BasicNameValuePair("agerg", "12"));
                add(new BasicNameValuePair("sex", "1"));
                add(new BasicNameValuePair("firston", "1"));
                add(new BasicNameValuePair("video", "0"));
                add(new BasicNameValuePair("country", "1"));
                add(new BasicNameValuePair("province", province));
                add(new BasicNameValuePair("city", "1"));
                add(new BasicNameValuePair("district", "0"));
                add(new BasicNameValuePair("hcountry", "0"));
                add(new BasicNameValuePair("hprovince", "0"));
                add(new BasicNameValuePair("hcity", "0"));
                add(new BasicNameValuePair("hdistrict", "0"));
                add(new BasicNameValuePair("online", "1"));
                add(new BasicNameValuePair("ldw", "1541646935"));
            }};
            httpPost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
            String result = XHttpClient.doRequest(httpPost);
            JSONArray jsonArray = JSONObject.parseObject(result).getJSONObject("result").getJSONObject("buddy").getJSONArray("info_list");

            for(int j=0;j<jsonArray.size();j++){
                JSONObject jsonObject = jsonArray.getJSONObject(j);

                AttackPage attackPage = new AttackPage();
                attackPage.setPointid(33);
                attackPage.setLink(jsonObject.getString("uin"));
                if(attackPageService.listByPointAndLink(attackPage).size()>0)
                    continue;

                Map<String,Object> map = new HashMap<>();
                map.put("country",jsonObject.getString("country"));
                map.put("face",jsonObject.getIntValue("face"));
                map.put("stat",jsonObject.getIntValue("stat"));
                map.put("distance",jsonObject.getIntValue("distance"));
                map.put("province",jsonObject.getString("province"));
                map.put("gender",jsonObject.getIntValue("gender"));
                map.put("headimg",jsonObject.getString("url"));
                map.put("city",jsonObject.getString("city"));
                map.put("age",jsonObject.getIntValue("age"));

                attackPage.setTitle(URLEncoder.encode(jsonObject.getString("nick"),"utf-8"));
                attackPage.setPointLink("http://cgi.find.qq.com/qqfind/buddy/search_v3");
                attackPage.setBelong("qq");
                attackPage.setCount(0);
                attackPage.setAttr(JSON.toJSONString(map));

                attackPageService.save(attackPage);
            }

        } catch (Exception e) {
            LOGGER.error("error:", e);
        }
    }
}

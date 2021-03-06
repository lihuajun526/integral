package com.operational.platform.spider.component.post.builder.impl.qqsearch;

import com.operational.platform.spider.component.post.builder.PostBuilder;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;

/**
 * Created by lihuajun on 2017/9/8.
 */
public class Builder implements PostBuilder {

    private final static Logger LOGGER = LoggerFactory.getLogger(Builder.class);

    @Override
    public void setParams(List<NameValuePair> params) {

        String[] datas1 = "33-浙江#13-河北#14-山西#15-内蒙古#21-辽宁#22-吉林#23-黑龙江#32-江苏#34-安徽#35-福建#36-江西#37-山东#41-河南#42-湖北#43-湖南#44-广东#45-广西#46-海南#50-重庆#51-四川#52-贵州#53-云南#54-西藏#61-陕西#62-甘肃#63-青海#64-宁夏#65-新疆#71-台湾#81-香港#82-澳门".split("#");
        Random r = new Random();
        int i = r.nextInt(datas1.length);
        int j = r.nextInt(datas1.length);
        int iR = r.nextInt(2);

        //所在地区
        params.add(new BasicNameValuePair("province", datas1[i].split("-")[0]));
        params.add(new BasicNameValuePair("page", String.valueOf(iR)));
        params.add(new BasicNameValuePair("num", "30"));
        params.add(new BasicNameValuePair("city", String.valueOf(r.nextInt(10))));
        //家乡
        /*params.add(new BasicNameValuePair("hprovince", datas1[j].split("-")[0]));
        params.add(new BasicNameValuePair("hcity", String.valueOf(r.nextInt(10))));*/

        //LOGGER.info(datas1[i].split("-")[0] + "#" + iR);
    }
}

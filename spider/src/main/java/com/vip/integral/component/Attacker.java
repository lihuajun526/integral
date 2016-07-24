package com.vip.integral.component;

import com.vip.integral.bean.AttackPage;
import com.vip.integral.exception.RequestException;
import com.vip.integral.model.AttackParam;
import com.vip.integral.util.XHttpClient;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihuajun on 16-7-21.
 */
public abstract class Attacker {

    private static final Logger LOGGER = LoggerFactory.getLogger(Attacker.class);

    //攻击参数
    protected AttackParam attackParam;
    //攻击页面
    protected AttackPage attackPage;
    //response
    protected String response;
    //Document
    protected Document document;
    //公共参数
    protected Map<String, String> pubParams = new HashMap<>();
    //攻击者行为
    protected String action;

    //初始化
    protected void init() throws RequestException {
        HttpGet httpGet = new HttpGet(attackPage.getPageLink().getLink());
        response = XHttpClient.doRequest(httpGet, attackParam.getCharset());
        action = attackParam.getAction();
        initPubParam(attackParam.getData());
        document = Jsoup.parse(response);
    }

    private void initPubParam(String data) {
        String[] strs = data.split("&");
        for (String str : strs) {
            String[] temp = str.split("=");
            pubParams.put(temp[0].trim(), temp[1].trim());
        }
    }

    /**
     * 初始化表单数据
     *
     * @param data
     */
    protected List<NameValuePair> initForm(String data) {

        List<NameValuePair> params = new ArrayList<>();

        String[] strs = data.split("&");

        for (String str : strs) {

            String[] temps = str.split("=");

            if (temps[0].startsWith("$")) {//从公共参数中取值
                String key = temps[0].replaceAll("$", "");
                if (null != pubParams.get(key))
                    params.add(new BasicNameValuePair(key.trim(), pubParams.get(key)));
                else
                    LOGGER.error("没有在公共参数中找到[" + key + "]的值");
            } else {
                if (temps.length == 2) {
                    params.add(new BasicNameValuePair(temps[0].trim(), temps[1].trim()));
                } else {
                    params.add(new BasicNameValuePair(temps[0].trim(), ""));
                }
            }
        }

        return params;
    }

    protected String initUrl(String url) {
        String[] ss = url.split("\\?");
        String params = ss[1];
        StringBuffer sbUrl = new StringBuffer(ss[0]).append("?");
        String[] strs = params.split("&");
        for (String str : strs) {
            if (str.startsWith("$")) {
                String[] temp = str.split("=");
                String key = temp[0].replaceAll("$", "").trim();
                sbUrl.append(key).append("=").append(pubParams.get(key)).append("&");
            } else {
                sbUrl.append(str).append("&");
            }
        }
        return sbUrl.toString();
    }

    public void setAttackParam(AttackParam attackParam) {
        this.attackParam = attackParam;
    }
}

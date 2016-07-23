package com.vip.integral.component;

import com.vip.integral.bean.AttackPage;
import com.vip.integral.exception.RequestException;
import com.vip.integral.model.AttackParam;
import com.vip.integral.util.XHttpClient;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihuajun on 16-7-21.
 */
public abstract class Attacker {

    //攻击参数
    protected AttackParam attackParam;
    //攻击的页面
    protected AttackPage attackPage;
    //response
    private String response;
    //攻击者行为
    protected String action;

    //初始化
    public void init() throws RequestException {
        HttpGet httpGet = new HttpGet(attackPage.getPageLink().getLink());
        response = XHttpClient.doRequest(httpGet, attackParam.getCharset());
        action = attackParam.getAction();
    }

    /**
     * 初始化表单
     *
     * @param data
     */
    public List<NameValuePair> initForm(String data) {

        List<NameValuePair> params = new ArrayList<>();

        String[] strs = data.split("&");

        for (String str : strs) {
            String[] temps = str.split("=");
            if (temps.length == 2) {
                params.add(new BasicNameValuePair(temps[0].trim(), temps[1].trim()));
            } else {
                params.add(new BasicNameValuePair(temps[0].trim(), ""));
            }
        }

        return params;
    }

    public void setAttackParam(AttackParam attackParam) {
        this.attackParam = attackParam;
    }

    public void setAttackPage(AttackPage attackPage) {
        this.attackPage = attackPage;
    }

}

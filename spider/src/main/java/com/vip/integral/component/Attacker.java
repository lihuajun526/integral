package com.vip.integral.component;

import com.vip.integral.bean.AttackPage;
import com.vip.integral.exception.RequestException;
import com.vip.integral.model.AttackParam;
import com.vip.integral.util.XHttpClient;
import org.apache.http.client.methods.HttpGet;

/**
 * Created by lihuajun on 16-7-21.
 */
public abstract class Attacker {

    //攻击参数
    private AttackParam attackParam;
    //攻击的页面
    private AttackPage attackPage;
    //response
    private String response;
    //攻击者行为
    protected String action;


    //初始化
    public void init() throws RequestException {
        HttpGet httpGet = new HttpGet(attackPage.getPageLink().getLink());
        response = XHttpClient.doRequest(httpGet, attackPage.getPageLink().getCharset());
        action = attackParam.getAction();
    }

    public AttackParam getAttackParam() {
        return attackParam;
    }

    public void setAttackParam(AttackParam attackParam) {
        this.attackParam = attackParam;
    }

    public AttackPage getAttackPage() {
        return attackPage;
    }

    public void setAttackPage(AttackPage attackPage) {
        this.attackPage = attackPage;
    }
}

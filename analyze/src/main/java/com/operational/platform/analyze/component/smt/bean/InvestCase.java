package com.operational.platform.analyze.component.smt.bean;

import java.util.List;

/**
 * Created by lihuajun on 2017/9/28.
 */
public class InvestCase {

/**
 * "investId":"24621237",
 "investDate":"2016-01-16",
 "roundName":"战略投资",
 "investInvestorMoney":"2216.666667",
 "investInvestorMoneyCurrency":"RMB",
 "moneyIsreal":"1",
 "moneyIsopen":"1",
 "epNameCn":"北京京东金融科技控股有限公司",
 "epShortnameCn":"京东金融",
 "epLogo":"https://pic.pedata.cn/pedata-app/logo/ep/7197/9A86D8734B1B8DC8ADB3C2DA52DB4D7E.jpg",
 "tags":[
 "金融服务"
 ],
 "packInfo":"战略投资 | 22.17亿RMB(估)"
 */
    private String investId;//案例id
    private String investDate;//投资日期
    private String roundName;//轮次
    private String investInvestorMoney;//投资金额
    private String investInvestorMoneyCurrency;//币种
    private String moneyIsreal;//是否真实
    private String moneyIsopen;//是否公开
    private String epNameCn;//公司中文名
    private String epNameEn;//公司英文名
    private String epShortnameCn;//公司简称
    private String epLogo;//logo
    private List<String> tags;//案例标签
    private String packInfo;

    public String getInvestId() {
        return investId;
    }

    public void setInvestId(String investId) {
        this.investId = investId;
    }

    public String getInvestDate() {
        return investDate;
    }

    public void setInvestDate(String investDate) {
        this.investDate = investDate;
    }

    public String getRoundName() {
        return roundName;
    }

    public void setRoundName(String roundName) {
        this.roundName = roundName;
    }

    public String getInvestInvestorMoney() {
        return investInvestorMoney;
    }

    public void setInvestInvestorMoney(String investInvestorMoney) {
        this.investInvestorMoney = investInvestorMoney;
    }

    public String getInvestInvestorMoneyCurrency() {
        return investInvestorMoneyCurrency;
    }

    public void setInvestInvestorMoneyCurrency(String investInvestorMoneyCurrency) {
        this.investInvestorMoneyCurrency = investInvestorMoneyCurrency;
    }

    public String getMoneyIsreal() {
        return moneyIsreal;
    }

    public void setMoneyIsreal(String moneyIsreal) {
        this.moneyIsreal = moneyIsreal;
    }

    public String getMoneyIsopen() {
        return moneyIsopen;
    }

    public void setMoneyIsopen(String moneyIsopen) {
        this.moneyIsopen = moneyIsopen;
    }

    public String getEpNameCn() {
        return epNameCn;
    }

    public void setEpNameCn(String epNameCn) {
        this.epNameCn = epNameCn;
    }

    public String getEpNameEn() {
        return epNameEn;
    }

    public void setEpNameEn(String epNameEn) {
        this.epNameEn = epNameEn;
    }

    public String getEpShortnameCn() {
        return epShortnameCn;
    }

    public void setEpShortnameCn(String epShortnameCn) {
        this.epShortnameCn = epShortnameCn;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getPackInfo() {
        return packInfo;
    }

    public void setPackInfo(String packInfo) {
        this.packInfo = packInfo;
    }

    public String getEpLogo() {
        return epLogo;
    }

    public void setEpLogo(String epLogo) {
        this.epLogo = epLogo;
    }
}

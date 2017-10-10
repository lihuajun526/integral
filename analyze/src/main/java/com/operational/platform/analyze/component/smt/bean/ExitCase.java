package com.operational.platform.analyze.component.smt.bean;

import java.util.List;

/**
 * Created by lihuajun on 2017/10/10.
 */
public class ExitCase {

    /**
     * "exitId":"2104172",
     * "exitDate":"2010-06-01",
     * "exitMoney":"50.4",
     * "exitMoneyCurrency":"RMB",
     * "exitReturnMultiple":"3.690144",
     * "epNameCn":"广州盈正信息技术有限公司",
     * "epShortnameCn":"3G游游网",
     * "epLogo":"https://pic.pedata.cn//Attachment/Logo/c/201212/7c7d03d7-5666-45b5-bbd0-2b185e7629d2.jpg",
     * "tags":[
     * "游戏",
     * "移动互联网"
     * ],
     * "packInfo":"退出金额：5.04千万RMB | 3.69倍"
     */
    private String exitId;
    private String exitDate;
    private String exitMoney;
    private String exitMoneyCurrency;
    private String exitReturnMultiple;
    private String epNameCn;
    private String epShortnameCn;
    private String epLogo;
    private List<String> tags;//案例标签
    private String packInfo;

    public String getExitId() {
        return exitId;
    }

    public void setExitId(String exitId) {
        this.exitId = exitId;
    }

    public String getExitDate() {
        return exitDate;
    }

    public void setExitDate(String exitDate) {
        this.exitDate = exitDate;
    }

    public String getExitMoney() {
        return exitMoney;
    }

    public void setExitMoney(String exitMoney) {
        this.exitMoney = exitMoney;
    }

    public String getExitMoneyCurrency() {
        return exitMoneyCurrency;
    }

    public void setExitMoneyCurrency(String exitMoneyCurrency) {
        this.exitMoneyCurrency = exitMoneyCurrency;
    }

    public String getExitReturnMultiple() {
        return exitReturnMultiple;
    }

    public void setExitReturnMultiple(String exitReturnMultiple) {
        this.exitReturnMultiple = exitReturnMultiple;
    }

    public String getEpNameCn() {
        return epNameCn;
    }

    public void setEpNameCn(String epNameCn) {
        this.epNameCn = epNameCn;
    }

    public String getEpShortnameCn() {
        return epShortnameCn;
    }

    public void setEpShortnameCn(String epShortnameCn) {
        this.epShortnameCn = epShortnameCn;
    }

    public String getEpLogo() {
        return epLogo;
    }

    public void setEpLogo(String epLogo) {
        this.epLogo = epLogo;
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
}

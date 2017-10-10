package com.operational.platform.analyze.component.smt.bean;

/**
 * Created by lihuajun on 2017/10/10.
 */
public class Fund {

    /**
     * "fundId":"116223",
     "fundNameCn":"招商和腾基金II",
     "fundShortnameCn":"招商和腾基金II",
     "hqPlace":"上海市",
     "fundType":"创业基金",
     "fundRaiseStatus":"已募完",
     "packInfo":"上海市 | 创业基金 | 已募完"
     */
    private String fundId;
    private String fundNameCn;
    private String fundShortnameCn;
    private String hqPlace;
    private String fundType;
    private String fundRaiseStatus;
    private String packInfo;

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    public String getFundNameCn() {
        return fundNameCn;
    }

    public void setFundNameCn(String fundNameCn) {
        this.fundNameCn = fundNameCn;
    }

    public String getFundShortnameCn() {
        return fundShortnameCn;
    }

    public void setFundShortnameCn(String fundShortnameCn) {
        this.fundShortnameCn = fundShortnameCn;
    }

    public String getHqPlace() {
        return hqPlace;
    }

    public void setHqPlace(String hqPlace) {
        this.hqPlace = hqPlace;
    }

    public String getFundType() {
        return fundType;
    }

    public void setFundType(String fundType) {
        this.fundType = fundType;
    }

    public String getFundRaiseStatus() {
        return fundRaiseStatus;
    }

    public void setFundRaiseStatus(String fundRaiseStatus) {
        this.fundRaiseStatus = fundRaiseStatus;
    }

    public String getPackInfo() {
        return packInfo;
    }

    public void setPackInfo(String packInfo) {
        this.packInfo = packInfo;
    }
}

package com.operational.platform.analyze.component.smt.bean;

/**
 * Created by lihuajun on 2017/9/28.
 * 职业生涯
 */
public class Career {

    private String startTime;//开始时间
    private String endTime;//结束时间
    private String company;//公司
    private String positionCn;//职位
    private String isOnDuty;//是否在职：1在职，0离职

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPositionCn() {
        return positionCn;
    }

    public void setPositionCn(String positionCn) {
        this.positionCn = positionCn;
    }

    public String getIsOnDuty() {
        return isOnDuty;
    }

    public void setIsOnDuty(String isOnDuty) {
        this.isOnDuty = isOnDuty;
    }
}

package com.operational.platform.analyze.component.smt.bean;

import java.util.List;

/**
 * Created by lihuajun on 2017/10/10.
 */
public class Statistics {

    private Boolean success;
    private List<List<String>> districtList;
    private List<List<String>> industryList;
    private List<List<String>> stageList;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<List<String>> getDistrictList() {
        return districtList;
    }

    public void setDistrictList(List<List<String>> districtList) {
        this.districtList = districtList;
    }

    public List<List<String>> getIndustryList() {
        return industryList;
    }

    public void setIndustryList(List<List<String>> industryList) {
        this.industryList = industryList;
    }

    public List<List<String>> getStageList() {
        return stageList;
    }

    public void setStageList(List<List<String>> stageList) {
        this.stageList = stageList;
    }
}

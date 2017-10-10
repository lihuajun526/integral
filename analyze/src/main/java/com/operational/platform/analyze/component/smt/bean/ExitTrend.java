package com.operational.platform.analyze.component.smt.bean;

import java.util.List;

/**
 * Created by lihuajun on 2017/10/10.
 */
public class ExitTrend {

    private Boolean success;
    private List<List<String>> data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<List<String>> getData() {
        return data;
    }

    public void setData(List<List<String>> data) {
        this.data = data;
    }
}

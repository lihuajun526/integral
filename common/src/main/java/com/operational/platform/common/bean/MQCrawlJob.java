package com.operational.platform.common.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lihuajun on 2017/9/27.
 */
public class MQCrawlJob {

    private String taskid;
    private Integer pointid;
    private boolean listPageEmpty = true;
    private String listPage;
    private Integer pageIndex;
    private Map<String,String> attr;

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    public Integer getPointid() {
        return pointid;
    }

    public void setPointid(Integer pointid) {
        this.pointid = pointid;
    }

    public String getListPage() {
        return listPage;
    }

    public void setListPage(String listPage) {
        this.listPage = listPage;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public boolean isListPageEmpty() {
        return listPageEmpty;
    }

    public void setListPageEmpty(boolean listPageEmpty) {
        this.listPageEmpty = listPageEmpty;
    }

    public Map<String, String> getAttr() {
        return attr;
    }

    public void setAttr(Map<String, String> attr) {
        this.attr = attr;
    }
}

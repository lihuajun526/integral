package com.operational.platform.common.bean;

/**
 * Created by lihuajun on 2017/9/27.
 */
public class MQCrawlJob {

    private String taskid;
    private Integer pointid;
    private boolean isListPageEmpty = true;
    private String listPage;
    private Integer pageIndex;

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
        return isListPageEmpty;
    }

    public void setIsListPageEmpty(boolean isListPageEmpty) {
        this.isListPageEmpty = isListPageEmpty;
    }
}

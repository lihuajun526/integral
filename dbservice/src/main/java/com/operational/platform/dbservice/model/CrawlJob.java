package com.operational.platform.dbservice.model;

import java.util.Date;

public class CrawlJob {
    private Integer id;

    private String taskid;

    private Integer pointid;

    private Boolean isListPageEmpty;

    private Integer pageIndex;

    private Date updateTime;

    private Date createTime;

    private String listPage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid == null ? null : taskid.trim();
    }

    public Integer getPointid() {
        return pointid;
    }

    public void setPointid(Integer pointid) {
        this.pointid = pointid;
    }

    public Boolean getIsListPageEmpty() {
        return isListPageEmpty;
    }

    public void setIsListPageEmpty(Boolean isListPageEmpty) {
        this.isListPageEmpty = isListPageEmpty;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getListPage() {
        return listPage;
    }

    public void setListPage(String listPage) {
        this.listPage = listPage == null ? null : listPage.trim();
    }
}
package com.vip.integral.model;

import java.util.Date;

public class Favour {
    private Integer id;

    private Integer crawlPointId;

    private String favourUrlRule;

    private String favourUrlRuleType;

    private String favourUrlClassPath;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCrawlPointId() {
        return crawlPointId;
    }

    public void setCrawlPointId(Integer crawlPointId) {
        this.crawlPointId = crawlPointId;
    }

    public String getFavourUrlRule() {
        return favourUrlRule;
    }

    public void setFavourUrlRule(String favourUrlRule) {
        this.favourUrlRule = favourUrlRule == null ? null : favourUrlRule.trim();
    }

    public String getFavourUrlRuleType() {
        return favourUrlRuleType;
    }

    public void setFavourUrlRuleType(String favourUrlRuleType) {
        this.favourUrlRuleType = favourUrlRuleType == null ? null : favourUrlRuleType.trim();
    }

    public String getFavourUrlClassPath() {
        return favourUrlClassPath;
    }

    public void setFavourUrlClassPath(String favourUrlClassPath) {
        this.favourUrlClassPath = favourUrlClassPath == null ? null : favourUrlClassPath.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
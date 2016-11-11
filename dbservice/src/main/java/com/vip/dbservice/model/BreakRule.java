package com.vip.dbservice.model;

import java.util.Date;

public class BreakRule {
    private Integer id;

    private Integer crawlPointId;

    private Integer level;

    private String breakName;

    private String breakRule;

    private Date creatTime;

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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getBreakName() {
        return breakName;
    }

    public void setBreakName(String breakName) {
        this.breakName = breakName == null ? null : breakName.trim();
    }

    public String getBreakRule() {
        return breakRule;
    }

    public void setBreakRule(String breakRule) {
        this.breakRule = breakRule == null ? null : breakRule.trim();
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
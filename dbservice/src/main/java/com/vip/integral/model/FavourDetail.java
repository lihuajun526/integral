package com.vip.integral.model;

import java.util.Date;

public class FavourDetail {
    private Integer id;

    private Integer crawlPointId;

    private String attrRule;

    private String levelRule;

    private Integer level;

    private String script;

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

    public String getAttrRule() {
        return attrRule;
    }

    public void setAttrRule(String attrRule) {
        this.attrRule = attrRule == null ? null : attrRule.trim();
    }

    public String getLevelRule() {
        return levelRule;
    }

    public void setLevelRule(String levelRule) {
        this.levelRule = levelRule == null ? null : levelRule.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script == null ? null : script.trim();
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
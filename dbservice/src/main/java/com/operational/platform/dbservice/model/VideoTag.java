package com.operational.platform.dbservice.model;

import java.util.Date;

public class VideoTag {
    private Integer id;

    private Integer userid;

    private Integer videoid;

    private String tag;

    private Integer isCheck = 1;

    private Date createTime;

    private Date updateTime;

    public Integer getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(Integer isCheck) {
        this.isCheck = isCheck;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getVideoid() {
        return videoid;
    }

    public void setVideoid(Integer videoid) {
        this.videoid = videoid;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
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
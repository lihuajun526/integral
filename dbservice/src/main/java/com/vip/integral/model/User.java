package com.vip.integral.model;

import java.util.Date;

/**
 * Created by lihuajun on 16-7-6.
 */
public class User {

    private Integer id;
    private String openid;
    private String nickname;
    //值为1时是男性，值为2时是女性，值为0时是未知
    private Integer sex;
    private String language;
    private String city;
    private String province;
    private String country;
    private String headimgurl;
    private Date subscribeTime;
    //0:取消关注,1:正常
    private Integer status;
    private Integer integral;
    //推广记录，id1:status#id2:status
    private String spreadRecord;
    //推广时间
    private Date populateTime;
    private Date createTime;
    private Date modifyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getSpreadRecord() {
        return spreadRecord;
    }

    public void setSpreadRecord(String spreadRecord) {
        this.spreadRecord = spreadRecord;
    }

    public Date getPopulateTime() {
        return populateTime;
    }

    public void setPopulateTime(Date populateTime) {
        this.populateTime = populateTime;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public Date getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(Date subscribeTime) {
        this.subscribeTime = subscribeTime;
    }
}

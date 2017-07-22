package com.operational.platform.dbservice.model;

import java.util.Date;

public class User {
    private Integer id;

    private String nickname;

    private Integer sex;

    private String language;

    private String city;

    private String province;

    private String country;

    private String headimgurl;

    private Date subscribeTime;

    private Integer status;

    private Integer integral;

    private Date populateTime;

    private String privilege;

    private String openid;

    private String appOpenid;

    private String unionid;

    private String vipAccessToken;

    private Date vipAccessTokenExpires;

    private Date createTime;

    private Date modifyTime;

    private String spreadRecord;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
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
        this.language = language == null ? null : language.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl == null ? null : headimgurl.trim();
    }

    public Date getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(Date subscribeTime) {
        this.subscribeTime = subscribeTime;
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

    public Date getPopulateTime() {
        return populateTime;
    }

    public void setPopulateTime(Date populateTime) {
        this.populateTime = populateTime;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege == null ? null : privilege.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getAppOpenid() {
        return appOpenid;
    }

    public void setAppOpenid(String appOpenid) {
        this.appOpenid = appOpenid == null ? null : appOpenid.trim();
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid == null ? null : unionid.trim();
    }

    public String getVipAccessToken() {
        return vipAccessToken;
    }

    public void setVipAccessToken(String vipAccessToken) {
        this.vipAccessToken = vipAccessToken == null ? null : vipAccessToken.trim();
    }

    public Date getVipAccessTokenExpires() {
        return vipAccessTokenExpires;
    }

    public void setVipAccessTokenExpires(Date vipAccessTokenExpires) {
        this.vipAccessTokenExpires = vipAccessTokenExpires;
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
        this.spreadRecord = spreadRecord == null ? null : spreadRecord.trim();
    }
}
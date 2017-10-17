package com.operational.platform.taskbreak.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lihuajun on 2017/10/17.
 */
public class Investor {

    private String link;
    private String name;
    private String company;
    private String position;
    private String age;
    private String tel;
    private String email;
    private String recentInvestTime;
    private String content;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRecentInvestTime() {
        return recentInvestTime;
    }

    public void setRecentInvestTime(String recentInvestTime) {
        this.recentInvestTime = recentInvestTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private Map<String, String> attr = new HashMap<>();

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getAttr() {
        return attr;
    }

    public void setAttr(Map<String, String> attr) {
        this.attr = attr;
    }
}

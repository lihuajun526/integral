package com.operational.platform.analyze.component.smt.bean;

import java.util.List;
import java.util.Map;

/**
 * Created by lihuajun on 2017/9/28.
 */
public class Investor {

    /**
     * "personNameCn":"沈南鹏",
     * "personNameEn":"Neil Shen",
     * "personPhoto":"https://pic.pedata.cn//person/201703/658b929af19b4ebebc986aab29010636.png",
     * "packInfo":"红杉中国::全球执行合伙人",
     * "investCount":"45",
     * "recentCase":"明码科技::233172721",
     * "investTrend":"移动互联网,互联网金融,B2C,电子设备,社交网络,房产服务,医疗健康",
     */
    private String personId;//投资人在源网站的id
    private String nameCn;//中文名
    private String nameEn;//英文名
    private String photo;//头像
    private String company;//公司
    private String position;//职位
    private Integer investCount;//投资次数
    private String recentCaseName;//最近投资案例名称
    private String recentCaseId;//最近投资案例id
    private String investTrend;//投资领域
    private String profile;//个人简介
    private String tel;//联系电话
    private String email;//电子邮箱
    private List<Career> careerList;//职业生涯
    private List<InvestCase> caseList;//投资案例
    private Map<String,String> attr;

    public Map<String, String> getAttr() {
        return attr;
    }

    public void setAttr(Map<String, String> attr) {
        this.attr = attr;
    }

    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

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

    public Integer getInvestCount() {
        return investCount;
    }

    public void setInvestCount(Integer investCount) {
        this.investCount = investCount;
    }

    public String getRecentCaseName() {
        return recentCaseName;
    }

    public void setRecentCaseName(String recentCaseName) {
        this.recentCaseName = recentCaseName;
    }

    public String getRecentCaseId() {
        return recentCaseId;
    }

    public void setRecentCaseId(String recentCaseId) {
        this.recentCaseId = recentCaseId;
    }

    public String getInvestTrend() {
        return investTrend;
    }

    public void setInvestTrend(String investTrend) {
        this.investTrend = investTrend;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
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

    public List<Career> getCareerList() {
        return careerList;
    }

    public void setCareerList(List<Career> careerList) {
        this.careerList = careerList;
    }

    public List<InvestCase> getCaseList() {
        return caseList;
    }

    public void setCaseList(List<InvestCase> caseList) {
        this.caseList = caseList;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }
}

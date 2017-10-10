package com.operational.platform.analyze.component.smt.bean;

import java.util.List;

/**
 * Created by lihuajun on 2017/10/10.
 */
public class Organise {

    /**
     * "orgSetupDate":"2007-01-01",
     * "orgNameCn":"招商和腾创投",
     * "orgShortnameCn":"招商和腾",
     * "orgHeadquartersPlace":"上海市",
     * "orgType":"VC",
     * "orgCapitalType":"合资",
     * "fundCount":1,
     * "investTotalCouont":19,
     * "orgManageCapital":"113.0",
     * "orgManageCapitalCurrency":"USD",
     * "orgWeb":"http://www.cmhjpartners.com",
     * "orgDesc":"招商和腾创投具有丰富的国际国内投资经营经验、成功的投资业绩、深厚的人脉网络以及差异化的投资策略。 招商和腾创投在中国大陆的投资活动超过14年，获得卓越的投资业绩包括那些最著名的风投背景公司，例如阿里巴巴、百度、AAC声学、科园半导体、中国网通、分众传媒、华友世纪、中芯国际以及展讯通信等。 招商和腾创投的团队整体累计拥有47年的投资经验及44年的运营管理经验，并与企业家、产业孵化园、科技园、大学、各行业协会、政府机构及业界同仁保持着良好的合作关系。 在其主要有限合伙投资人香港招商局集团的支持下，招商和腾创投将通过其自有的网络和经验以及香港招商局集团的庞大资源给被投资公司带来巨大的价值。",
     * "orgLogo":
     */
    private String orgId;
    private String orgSetupDate;
    private String orgNameCn;
    private String orgShortnameCn;
    private String orgHeadquartersPlace;
    private String orgType;
    private String orgCapitalType;
    private Integer fundCount;
    private Integer investTotalCouont;
    private String orgManageCapital;
    private String orgManageCapitalCurrency;
    private String orgWeb;
    private String orgDesc;
    private String orgLogo;
    private OrgContact orgContact;
    private List<InvestCase> caseList;
    private List<ExitCase> exitCaseList;
    private List<Manager> managerList;
    private List<Fund> fundList;
    private Statistics statistics;
    private InvestTrend investTrend;
    private ExitTrend exitTrend;

    public InvestTrend getInvestTrend() {
        return investTrend;
    }

    public void setInvestTrend(InvestTrend investTrend) {
        this.investTrend = investTrend;
    }

    public ExitTrend getExitTrend() {
        return exitTrend;
    }

    public void setExitTrend(ExitTrend exitTrend) {
        this.exitTrend = exitTrend;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public List<Fund> getFundList() {
        return fundList;
    }

    public void setFundList(List<Fund> fundList) {
        this.fundList = fundList;
    }

    public List<Manager> getManagerList() {
        return managerList;
    }

    public void setManagerList(List<Manager> managerList) {
        this.managerList = managerList;
    }

    public OrgContact getOrgContact() {
        return orgContact;
    }

    public void setOrgContact(OrgContact orgContact) {
        this.orgContact = orgContact;
    }

    public List<ExitCase> getExitCaseList() {
        return exitCaseList;
    }

    public void setExitCaseList(List<ExitCase> exitCaseList) {
        this.exitCaseList = exitCaseList;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgSetupDate() {
        return orgSetupDate;
    }

    public void setOrgSetupDate(String orgSetupDate) {
        this.orgSetupDate = orgSetupDate;
    }

    public String getOrgNameCn() {
        return orgNameCn;
    }

    public void setOrgNameCn(String orgNameCn) {
        this.orgNameCn = orgNameCn;
    }

    public String getOrgShortnameCn() {
        return orgShortnameCn;
    }

    public void setOrgShortnameCn(String orgShortnameCn) {
        this.orgShortnameCn = orgShortnameCn;
    }

    public String getOrgHeadquartersPlace() {
        return orgHeadquartersPlace;
    }

    public void setOrgHeadquartersPlace(String orgHeadquartersPlace) {
        this.orgHeadquartersPlace = orgHeadquartersPlace;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getOrgCapitalType() {
        return orgCapitalType;
    }

    public void setOrgCapitalType(String orgCapitalType) {
        this.orgCapitalType = orgCapitalType;
    }

    public Integer getFundCount() {
        return fundCount;
    }

    public void setFundCount(Integer fundCount) {
        this.fundCount = fundCount;
    }

    public Integer getInvestTotalCouont() {
        return investTotalCouont;
    }

    public void setInvestTotalCouont(Integer investTotalCouont) {
        this.investTotalCouont = investTotalCouont;
    }

    public String getOrgManageCapital() {
        return orgManageCapital;
    }

    public void setOrgManageCapital(String orgManageCapital) {
        this.orgManageCapital = orgManageCapital;
    }

    public String getOrgManageCapitalCurrency() {
        return orgManageCapitalCurrency;
    }

    public void setOrgManageCapitalCurrency(String orgManageCapitalCurrency) {
        this.orgManageCapitalCurrency = orgManageCapitalCurrency;
    }

    public String getOrgWeb() {
        return orgWeb;
    }

    public void setOrgWeb(String orgWeb) {
        this.orgWeb = orgWeb;
    }

    public String getOrgDesc() {
        return orgDesc;
    }

    public void setOrgDesc(String orgDesc) {
        this.orgDesc = orgDesc;
    }

    public String getOrgLogo() {
        return orgLogo;
    }

    public void setOrgLogo(String orgLogo) {
        this.orgLogo = orgLogo;
    }

    public List<InvestCase> getCaseList() {
        return caseList;
    }

    public void setCaseList(List<InvestCase> caseList) {
        this.caseList = caseList;
    }
}

package com.operational.platform.analyze.component.smt.bean;

/**
 * Created by lihuajun on 2017/10/10.
 */
public class Manager {

    /**
     * "personId":"2155028",
     "personNameCn":"李雪刚",
     "personNameEn":"George Li",
     "personPhoto":"https://pic.pedata.cn//Logo/265cb2f6-9ade-4e38-bc45-3b99f3cfa61a.jpg",
     "descInfo":"李雪刚先生现任招商和腾创投主管合伙人。李先生拥有17年风险投资经历以及丰富的经营经验。李先生是2001年成立的招商富鑫资产管理公司（基金I 的管理公司）创始人之一，并任董事兼总裁。在此之前，李雪刚先生担任香港招商局科技集团的董事兼副总经理，分管该公司在香港及大陆企业的投资与管理，管理资产价值超过10亿港币。李雪刚先生还曾担任香港招商局集团的企业管理部副总经理，负责集团在香港和大陆的累计5.3亿港币直接投资，内部收益率达到17%。李雪刚先生本科毕业于中国四川大学，并获得美国亚利桑那州立大学MBA学位。",
     "personCareerPositionCn":"主管合伙人"
     */

    private String personId;
    private String personNameCn;
    private String personNameEn;
    private String personPhoto;
    private String descInfo;
    private String personCareerPositionCn;

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonNameCn() {
        return personNameCn;
    }

    public void setPersonNameCn(String personNameCn) {
        this.personNameCn = personNameCn;
    }

    public String getPersonNameEn() {
        return personNameEn;
    }

    public void setPersonNameEn(String personNameEn) {
        this.personNameEn = personNameEn;
    }

    public String getPersonPhoto() {
        return personPhoto;
    }

    public void setPersonPhoto(String personPhoto) {
        this.personPhoto = personPhoto;
    }

    public String getDescInfo() {
        return descInfo;
    }

    public void setDescInfo(String descInfo) {
        this.descInfo = descInfo;
    }

    public String getPersonCareerPositionCn() {
        return personCareerPositionCn;
    }

    public void setPersonCareerPositionCn(String personCareerPositionCn) {
        this.personCareerPositionCn = personCareerPositionCn;
    }
}

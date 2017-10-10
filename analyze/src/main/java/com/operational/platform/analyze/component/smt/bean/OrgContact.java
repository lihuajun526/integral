package com.operational.platform.analyze.component.smt.bean;

/**
 * Created by lihuajun on 2017/10/10.
 */
public class OrgContact {

    /**
     * "eventContactType":"中国",
     "contactPersonName":"Teresa Qiu",
     "eventContactTel":"86-21-5396-5500",
     "eventContactFax":"86-21-5396-5530",
     "eventContactEmail":"tqiu@cmhjpartners.com",
     "eventContactZipcode":"200021",
     "eventContactAddress":"上海市卢湾区淮海中路222号力宝广场803室"
     */
    private String eventContactType;
    private String contactPersonName;
    private String eventContactTel;
    private String eventContactFax;
    private String eventContactEmail;
    private String eventContactZipcode;
    private String eventContactAddress;

    public String getEventContactType() {
        return eventContactType;
    }

    public void setEventContactType(String eventContactType) {
        this.eventContactType = eventContactType;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getEventContactTel() {
        return eventContactTel;
    }

    public void setEventContactTel(String eventContactTel) {
        this.eventContactTel = eventContactTel;
    }

    public String getEventContactFax() {
        return eventContactFax;
    }

    public void setEventContactFax(String eventContactFax) {
        this.eventContactFax = eventContactFax;
    }

    public String getEventContactEmail() {
        return eventContactEmail;
    }

    public void setEventContactEmail(String eventContactEmail) {
        this.eventContactEmail = eventContactEmail;
    }

    public String getEventContactZipcode() {
        return eventContactZipcode;
    }

    public void setEventContactZipcode(String eventContactZipcode) {
        this.eventContactZipcode = eventContactZipcode;
    }

    public String getEventContactAddress() {
        return eventContactAddress;
    }

    public void setEventContactAddress(String eventContactAddress) {
        this.eventContactAddress = eventContactAddress;
    }
}

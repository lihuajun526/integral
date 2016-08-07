package com.vip.integral.attack.qzone.bean;

/**
 * Created by lihuajun on 2016/8/7.
 */
public class Result{
    private Integer code;
    private Integer subcode;
    private String message;
    private QQUserInfo data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getSubcode() {
        return subcode;
    }

    public void setSubcode(Integer subcode) {
        this.subcode = subcode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public QQUserInfo getData() {
        return data;
    }

    public void setData(QQUserInfo data) {
        this.data = data;
    }
}

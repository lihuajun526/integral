package com.operational.platform.common.bean;

/**
 * Created by lihuajun on 2017/8/4.
 */
public class Article {
    private String title;
    private String description;
    private String picurl;
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String toString() {

        StringBuffer sb = new StringBuffer("<item>");
        sb.append("<Title><![CDATA[").append(this.getTitle()).append("]]></Title>");
        sb.append("<Description><![CDATA[").append(this.getDescription()).append("]]></Description>");
        sb.append("<PicUrl><![CDATA[").append(this.getPicurl()).append("]]></PicUrl>");
        sb.append("<Url><![CDATA[").append(this.getUrl()).append("]]></Url>");
        sb.append("</item>");
        return sb.toString();
    }
}

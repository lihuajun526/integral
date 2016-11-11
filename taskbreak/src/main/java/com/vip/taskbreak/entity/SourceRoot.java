package com.vip.taskbreak.entity;

/**
 * @author: Zhou Xuanang
 * @Date: 13:50 2016/11/1.
 */
public class SourceRoot {
    private String sourceName;// 数据源名称
    private Integer crawlPointId;// id
    private String url;// 起始url

    public String getSourceName() {

        return sourceName;
    }

    public void setSourceName(String sourceName) {

        this.sourceName = sourceName;
    }

    public Integer getCrawlPointId() {

        return crawlPointId;
    }

    public void setCrawlPointId(Integer crawlPointId) {

        this.crawlPointId = crawlPointId;
    }

    public String getUrl() {

        return url;
    }

    public void setUrl(String url) {

        this.url = url;
    }
}

package com.operational.platform.dbservice.model;

import java.util.Date;
import java.util.List;

public class VideoGood {
    private Integer id;

    private String url;

    private Integer suggestid;

    private String title;

    private String score;

    private String image;

    private String description;

    private String meta;

    private String wimage;

    private String titleImage;

    private Date createTime;

    private Date updateTime;

    private List<DbVideoRelation> videoRelations;

    private List<DbShortComment> shortComments;

    private List<DbVideoImage> videoImages;

    private List<DbVideoTag> videoTags;

    private List<DbVideoPerson> videoPersons;

    public String getTitleImage() {
        return titleImage;
    }

    public void setTitleImage(String titleImage) {
        this.titleImage = titleImage;
    }

    public String getWimage() {
        return wimage;
    }

    public void setWimage(String wimage) {
        this.wimage = wimage;
    }

    public List<DbVideoRelation> getVideoRelations() {
        return videoRelations;
    }

    public void setVideoRelations(List<DbVideoRelation> videoRelations) {
        this.videoRelations = videoRelations;
    }

    public List<DbShortComment> getShortComments() {
        return shortComments;
    }

    public void setShortComments(List<DbShortComment> shortComments) {
        this.shortComments = shortComments;
    }

    public List<DbVideoImage> getVideoImages() {
        return videoImages;
    }

    public void setVideoImages(List<DbVideoImage> videoImages) {
        this.videoImages = videoImages;
    }

    public List<DbVideoTag> getVideoTags() {
        return videoTags;
    }

    public void setVideoTags(List<DbVideoTag> videoTags) {
        this.videoTags = videoTags;
    }

    public List<DbVideoPerson> getVideoPersons() {
        return videoPersons;
    }

    public void setVideoPersons(List<DbVideoPerson> videoPersons) {
        this.videoPersons = videoPersons;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSuggestid() {
        return suggestid;
    }

    public void setSuggestid(Integer suggestid) {
        this.suggestid = suggestid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score == null ? null : score.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta == null ? null : meta.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
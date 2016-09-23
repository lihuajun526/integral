package com.vip.spider.attack.tengxun.bean;

import java.util.List;

/**
 * Created by lihuajun on 16-8-2.
 */
public class TxItem {

    private Response response;
    private List<Data> data;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public class Response {
        private Integer code;
        private String msg;
        private Integer dext;

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public Integer getDext() {
            return dext;
        }

        public void setDext(Integer dext) {
            this.dext = dext;
        }
    }

    public class Data {
        private String id;
        private String title;
        private String url;
        private String dateline;
        private String site;
        private String art_type;
        private String source;
        private Integer comment_id;
        private Integer comment_count;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDateline() {
            return dateline;
        }

        public void setDateline(String dateline) {
            this.dateline = dateline;
        }

        public String getSite() {
            return site;
        }

        public void setSite(String site) {
            this.site = site;
        }

        public String getArt_type() {
            return art_type;
        }

        public void setArt_type(String art_type) {
            this.art_type = art_type;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public Integer getComment_id() {
            return comment_id;
        }

        public void setComment_id(Integer comment_id) {
            this.comment_id = comment_id;
        }

        public Integer getComment_count() {
            return comment_count;
        }

        public void setComment_count(Integer comment_count) {
            this.comment_count = comment_count;
        }
    }
}

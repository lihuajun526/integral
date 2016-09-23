package com.vip.spider.attack.tengxun.bean;

import java.util.List;

/**
 * Created by lihuajun on 2016/8/13.
 */
public class TxxwComment {

    private Integer errCode;
    private Data data;

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {
        private Long rootid;
        private Long total;
        private Integer reqnum;
        private Integer retnum;
        private List<Commentid> commentid;

        public Long getRootid() {
            return rootid;
        }

        public void setRootid(Long rootid) {
            this.rootid = rootid;
        }

        public Long getTotal() {
            return total;
        }

        public void setTotal(Long total) {
            this.total = total;
        }

        public Integer getReqnum() {
            return reqnum;
        }

        public void setReqnum(Integer reqnum) {
            this.reqnum = reqnum;
        }

        public Integer getRetnum() {
            return retnum;
        }

        public void setRetnum(Integer retnum) {
            this.retnum = retnum;
        }

        public List<Commentid> getCommentid() {
            return commentid;
        }

        public void setCommentid(List<Commentid> commentid) {
            this.commentid = commentid;
        }

        public class Commentid {
            private String id;
            private String rootid;
            private Long targetid;
            private String parent;
            private Long time;
            private String content;
            private String title;
            private String isdeleted;
            private String userid;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getRootid() {
                return rootid;
            }

            public void setRootid(String rootid) {
                this.rootid = rootid;
            }

            public Long getTargetid() {
                return targetid;
            }

            public void setTargetid(Long targetid) {
                this.targetid = targetid;
            }

            public String getParent() {
                return parent;
            }

            public void setParent(String parent) {
                this.parent = parent;
            }

            public Long getTime() {
                return time;
            }

            public void setTime(Long time) {
                this.time = time;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getIsdeleted() {
                return isdeleted;
            }

            public void setIsdeleted(String isdeleted) {
                this.isdeleted = isdeleted;
            }

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }
        }
    }

}

package com.vip.spider.attack.tengxun.bean;

/**
 * Created by lihuajun on 2016/8/12.
 */
public class TxxwCommentResult {

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
        private String parent;
        private String commentid;
        private Long time;
        private String content;
        private String userid;
        private String rootid;
        private Userinfo userinfo;

        public String getParent() {
            return parent;
        }

        public void setParent(String parent) {
            this.parent = parent;
        }

        public String getCommentid() {
            return commentid;
        }

        public void setCommentid(String commentid) {
            this.commentid = commentid;
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

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getRootid() {
            return rootid;
        }

        public void setRootid(String rootid) {
            this.rootid = rootid;
        }

        public Userinfo getUserinfo() {
            return userinfo;
        }

        public void setUserinfo(Userinfo userinfo) {
            this.userinfo = userinfo;
        }

        public class Userinfo {
            private String nick;
            private String head;
            private Integer usertype;
            private String region;

            public String getNick() {
                return nick;
            }

            public void setNick(String nick) {
                this.nick = nick;
            }

            public String getHead() {
                return head;
            }

            public void setHead(String head) {
                this.head = head;
            }

            public Integer getUsertype() {
                return usertype;
            }

            public void setUsertype(Integer usertype) {
                this.usertype = usertype;
            }

            public String getRegion() {
                return region;
            }

            public void setRegion(String region) {
                this.region = region;
            }
        }
    }

}

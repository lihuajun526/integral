package com.vip.spider.attack.aqy.bean;

import java.util.List;

/**
 * Created by lihuajun on 16-8-1.
 */
public class AqyComment {

    private String code;
    private String msg;
    private Data data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {
        private List<Comment> comments;
        private Integer count;

        public List<Comment> getComments() {
            return comments;
        }

        public void setComments(List<Comment> comments) {
            this.comments = comments;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public class Comment {
            private String contentId;
            private Integer type;
            private String title;
            private String content;
            private List<Reply> replyList;
            private UserInfo userInfo;

            public UserInfo getUserInfo() {
                return userInfo;
            }

            public void setUserInfo(UserInfo userInfo) {
                this.userInfo = userInfo;
            }

            public class UserInfo {
                private String uid;
                private Integer uidType;

                public String getUid() {
                    return uid;
                }

                public void setUid(String uid) {
                    this.uid = uid;
                }

                public Integer getUidType() {
                    return uidType;
                }

                public void setUidType(Integer uidType) {
                    this.uidType = uidType;
                }
            }

            public String getContentId() {
                return contentId;
            }

            public void setContentId(String contentId) {
                this.contentId = contentId;
            }

            public Integer getType() {
                return type;
            }

            public void setType(Integer type) {
                this.type = type;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public List<Reply> getReplyList() {
                return replyList;
            }

            public void setReplyList(List<Reply> replyList) {
                this.replyList = replyList;
            }

            //回复
            public class Reply {
                private String id;
                private String content;
                private Replyer userInfo;

                public Replyer getUserInfo() {
                    return userInfo;
                }

                public void setUserInfo(Replyer userInfo) {
                    this.userInfo = userInfo;
                }

                public class Replyer {
                    private String uid;
                    private Integer uidType;

                    public String getUid() {
                        return uid;
                    }

                    public void setUid(String uid) {
                        this.uid = uid;
                    }

                    public Integer getUidType() {
                        return uidType;
                    }

                    public void setUidType(Integer uidType) {
                        this.uidType = uidType;
                    }
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

            }
        }

    }
}







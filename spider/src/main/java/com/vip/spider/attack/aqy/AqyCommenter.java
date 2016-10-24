package com.vip.spider.attack.aqy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vip.spider.attack.aqy.bean.AqyComment;
import com.vip.spider.bean.Comment;
import com.vip.spider.component.Commenter;
import com.vip.spider.constant.ExceptionTypeEnum;
import com.vip.spider.util.XHttpClient;
import com.vip.spider.util.cookie.FilterCookies;
import com.vip.spider.util.cookie.HttpCookieEx;
import com.vip.spider.exception.CommentException;
import com.vip.spider.exception.GetCommentException;
import com.vip.spider.exception.RequestException;
import com.vip.spider.util.cookie.CookieHelper;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 爱奇艺评论者
 * Created by lihuajun on 16-7-16.
 */
public class AqyCommenter extends Commenter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AqyCommenter.class);

    private String accept = "*/*";
    private String origin = "http://www.iqiyi.com";
    private String userAgent = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.86 Safari/537.36";
    private Element element;
    private String uid;

    @Override public void init() throws RequestException, UnsupportedEncodingException {

        //if (pubParams.size() == 0)
        super.init();

        //收集公共参数
        //设置categoryid
        element = document.getElementById("data-vip-remindbox");
        String categoryid = element.attr("data-Boss-categoryid");
        pubParams.put("categoryid", categoryid);
        //设置albumid
        element = document.getElementById("videoShopGuideWrap");
        String albumid = element.attr("data-shop-albumid");
        pubParams.put("albumid", albumid);
        //设置tvid
        element = document.getElementById("qitancommonarea");
        String tvid = element.attr("data-qitancomment-tvid");
        pubParams.put("tvid", tvid);
        //设置qitanid
        String qitanid = element.attr("data-qitancomment-qitanid");
        pubParams.put("qitanid", qitanid);

        String cookies1 = JSON.parseObject(attackParam.getCookies()).getString("comment");
        initPubParam(cookies1, ";");

        String uid = JSON.parseObject(URLDecoder.decode(pubParams.get("P00002"), "utf-8")).getString("uid");
        //设置uid
        pubParams.put("uid", uid);
        this.uid = uid;

    }

    @Override public Comment comment() throws CommentException {

        Comment comment = null;

        try {
            //初始化
            this.init();
            String requestUrl = "http://api.t.iqiyi.com/qx_api/comment/publish";
            HttpPost httpPost = new HttpPost(requestUrl);
            //设置header
            httpPost.setHeader("Accept", accept);
            httpPost.setHeader("Origin", origin);
            httpPost.setHeader("User-Agent", userAgent);
            httpPost.setHeader("Referer", attackPage.getLink());

            //设置表单参数
            List<NameValuePair> params = initForm(
                    "is_video_page,play_order,qypid,qitan_comment_type,appid,categoryid,picid,antiCsrf");
            params.add(new BasicNameValuePair("albumid", pubParams.get("albumid")));
            params.add(new BasicNameValuePair("tvid", pubParams.get("tvid")));
            params.add(new BasicNameValuePair("qitanid", pubParams.get("qitanid")));
            params.add(new BasicNameValuePair("nosync", "weibo,qzone,renren"));
            //设置title
            String title = element.attr("data-qitancomment-title");
            params.add(new BasicNameValuePair("title", title));
            //设置sync_src
            params.add(new BasicNameValuePair("sync_src", title));
            //设置playurl
            params.add(new BasicNameValuePair("playurl", attackPage.getLink()));
            //设置current_url
            params.add(new BasicNameValuePair("current_url", attackPage.getLink()));
            //设置text
            params.add(new BasicNameValuePair("text", JSON.parseObject(action).getString("comment")));
            //设置tv_year
            String tv_year = element.attr("data-qitancomment-tvyear");
            params.add(new BasicNameValuePair("tv_year", tv_year));
            httpPost.setEntity(new UrlEncodedFormEntity(params, attackParam.getCharset()));
            //设置cookie
            String commentCookie = JSON.parseObject(attackParam.getCookies()).getString("comment");
            List<HttpCookieEx> cookieList = new ArrayList<>();
            cookieList.addAll(FilterCookies.filter(commentCookie));
            CookieHelper.setCookies2(requestUrl, httpPost, cookieList);
            //攻击
            Thread.sleep(6000);
            String result = XHttpClient.doRequest(httpPost, attackParam.getCharset());
            JSONObject jsonObject = JSON.parseObject(result);
            String code = jsonObject.getString("code");

            if ("A00000".equals(code)) {
                LOGGER.info("评论成功[{}]", attackPage.getLink());
                try {
                    String getCommentsUrl =
                            "http://api.t.iqiyi.com/qx_api/comment/get_video_comments?albumid=" + pubParams.get("albumid")
                                    + "&categoryid=" + pubParams.get("categoryid")
                                    + "&cb=&escape=true&is_video_page=true&need_reply=true&need_subject=true&need_total=1&page=1&page_size=10&page_size_reply=3&qitan_comment_type=1&qitancallback=&qitanid="
                                    + pubParams.get("qitanid")
                                    + "&qypid=01010011010000000000&reply_sort=hot&sort=add_time&tvid=" + pubParams.get("tvid");
                    LOGGER.info("获取评论数据的链接[{}]", getCommentsUrl);
                    Thread.sleep(6000);
                    String commentsResult = XHttpClient.doRequest(new HttpGet(getCommentsUrl), attackParam.getCharset());
                    AqyComment aqyComment = JSONObject.parseObject(commentsResult, AqyComment.class);
                    comment = new Comment();
                    for (AqyComment.Data.Comment o : aqyComment.getData().getComments()) {
                        if (o.getUserInfo().getUid().equals(uid)) {
                            comment.setId(o.getContentId());
                            break;
                        }
                    }
                    if (comment.getId() == null) {
                        LOGGER.info("没找到评论");
                        throw new GetCommentException(ExceptionTypeEnum.GET_COMMENT_ERROR);
                    }
                } catch (Exception e) {
                    throw new GetCommentException(ExceptionTypeEnum.GET_COMMENT_ERROR);
                }
            } else {
                LOGGER.info("由于{},评论失败[{}]", result, attackPage.getLink());
                throw new CommentException(ExceptionTypeEnum.COMMENT_ERROR.code, result);
            }
        } catch (Exception e) {
            if (e instanceof CommentException)
                throw (CommentException) e;
            else {
                LOGGER.error("评论失败:", e);
                throw new CommentException(ExceptionTypeEnum.COMMENT_ERROR.code, e.getMessage());
            }
        }
        return comment;
    }

    @Override public Comment reply(Comment comment) {

        Comment reply = null;

        try {
            this.init();
            String requestUrl =
                    "http://api.t.iqiyi.com/qx_api/comment/reply?$albumid=511275500&$antiCsrf=03415e7be8af74727c631dc8c7cb036e&$categoryid=1&cb=&contentid="
                            + comment.getId()
                            + "&is_video_page=true&qitan_comment_type=1&qitancallback=&qypid=01010011010000000000&replyid=&t=0.18211309275626575&text="
                            + JSON.parseObject(action).getString("reply") + "&$tvid=511275500&$uid=1266687801";
            requestUrl = initUrl(requestUrl);
            LOGGER.info("回复的url:{}", requestUrl);
            HttpGet httpGet = new HttpGet(requestUrl);
            //设置cookie
            String commentCookie = JSON.parseObject(attackParam.getCookies()).getString("reply");
            List<HttpCookieEx> cookieList = new ArrayList<>();
            cookieList.addAll(FilterCookies.filter(commentCookie));
            CookieHelper.setCookies2(requestUrl, httpGet, cookieList);
            //攻击
            String result = XHttpClient.doRequest(httpGet, attackParam.getCharset());
            JSONObject jsonObject = JSON.parseObject(result);
            String code = jsonObject.getString("code");

            if ("A00000".equals(code)) {
                reply.setId(jsonObject.getJSONObject("data").getString("id"));
                LOGGER.info("回复成功[id={}]", reply.getId());
            } else {
                LOGGER.info("回复失败:{}", result);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return reply;
    }

    @Override public void praise(Comment comment) throws RequestException, URISyntaxException {

        try {
            this.init();
            String requestUrl =
                    "http://api.t.iqiyi.com/qx_api/comment/like?$albumid=503325200&$antiCsrf=0a6c572e0b6d101791a4ddd549857d3c&cb=&contentid="
                            + comment.getId()
                            + "&is_video_page=true&qitancallback=&$qitanid=11075642&qypid=01010011010000000000&t=0.8853676982141423&$tvid=503325200&$uid=85840559";

            requestUrl = initUrl(requestUrl);
            LOGGER.info("点赞url[{}]", requestUrl);

            HttpGet httpGet = new HttpGet(requestUrl);
            //设置cookies
            String praiseCookie = JSON.parseObject(attackParam.getCookies()).getString("praise");
            List<HttpCookieEx> cookieList = new ArrayList<>();
            cookieList.addAll(FilterCookies.filter(praiseCookie));
            CookieHelper.setCookies2(requestUrl, httpGet, cookieList);
            //攻击
            String result = XHttpClient.doRequest(httpGet, attackParam.getCharset());
            JSONObject jsonObject = JSON.parseObject(result);
            String code = jsonObject.getString("code");
            if (!"A00000".equals(code)) {
                LOGGER.error("点赞失败，返回码：{}", code);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override public Comment echo(Comment comment) {
        return null;
    }

    @Override public List<Comment> listHotComment(int maxComment, int maxReply) throws RequestException {

        List<Comment> list = null;

        try {
            //初始化
            this.init();
            list = new ArrayList<>();

            String requestUrl = "http://api.t.iqiyi.com/qx_api/comment/get_video_comments?$aid=11741438&$albumid=281718700&categoryid=1&escape=true&is_video_page=true&need_reply=true&need_subject=true&need_total=1&page=1&page_size=10&page_size_reply=3&qitan_comment_type=1&$qitanid=11741438&qypid=01010011010000000000&reply_sort=hot&sort=hot&t=0.22156078186678108&$tvid=281718700";
            requestUrl = initUrl(requestUrl);
            LOGGER.info("获取热评的链接[{}]", requestUrl);

            HttpGet httpGet = new HttpGet(requestUrl);
            String response = XHttpClient.doRequest(httpGet, attackParam.getCharset());

            AqyComment aqyComment = JSONObject.parseObject(response, AqyComment.class);

            for (int i = 0; i < maxComment && i < aqyComment.getData().getComments().size(); i++) {
                AqyComment.Data.Comment obj = aqyComment.getData().getComments().get(i);
                Comment comment = new Comment();
                comment.setId(obj.getContentId());
                for (int j = 0; j < maxReply && j < obj.getReplyList().size(); j++) {
                    AqyComment.Data.Comment.Reply obj1 = obj.getReplyList().get(j);
                    Comment reply = new Comment();
                    reply.setId(obj1.getId());
                    comment.getReplys().add(reply);
                }
                list.add(comment);
            }
        } catch (Exception e) {

        }
        return list;
    }
}

package com.vip.integral.attack.aqy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.vip.integral.attack.aqy.bean.AqyComment;
import com.vip.integral.bean.Comment;
import com.vip.integral.component.Commenter;
import com.vip.integral.exception.RequestException;
import com.vip.integral.util.XHttpClient;
import com.vip.integral.util.cookie.CookieHelper;
import com.vip.integral.util.cookie.FilterCookies;
import com.vip.integral.util.cookie.HttpCookieEx;
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

    @Override public Comment comment() throws URISyntaxException, UnsupportedEncodingException, RequestException {

        //初始化
        String requestUrl = "http://api.t.iqiyi.com/qx_api/comment/publish";
        HttpPost httpPost = new HttpPost(requestUrl);

        //设置header
        httpPost.setHeader("Accept", accept);
        httpPost.setHeader("Origin", origin);
        httpPost.setHeader("User-Agent", userAgent);
        httpPost.setHeader("Referer", attackPage.getLink());

        //设置表单参数
        /**
         * 相同key：is_video_page,play_order,qypid,qitan_comment_type,appid,nosync,categoryid,picid,antiCsrf
         不同key：,,,,,,,tv_year,text,
         */
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
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            LOGGER.error("", e);
        }
        String result = XHttpClient.doRequest(httpPost, attackParam.getCharset());
        JSONObject jsonObject = JSON.parseObject(result);
        String code = jsonObject.getString("code");
        Comment comment = null;
        if ("A00000".equals(code)) {
            LOGGER.info("评论成功[{}]", attackPage.getLink());
            comment = new Comment();
            String getCommentsUrl =
                    "http://api.t.iqiyi.com/qx_api/comment/get_video_comments?albumid=" + pubParams.get("albumid")
                            + "&categoryid=" + pubParams.get("categoryid")
                            + "&cb=&escape=true&is_video_page=true&need_reply=true&need_subject=true&need_total=1&page=1&page_size=10&page_size_reply=3&qitan_comment_type=1&qitancallback=&qitanid="
                            + pubParams.get("qitanid")
                            + "&qypid=01010011010000000000&reply_sort=hot&sort=add_time&tvid=" + pubParams.get("tvid");
            LOGGER.info("获取评论数据[{}]", getCommentsUrl);
            String commentsResult = XHttpClient.doRequest(new HttpGet(getCommentsUrl), attackParam.getCharset());
            AqyComment aqyComment = JSONObject.parseObject(commentsResult, AqyComment.class);
            for (AqyComment.Data.Comment o : aqyComment.getData().getComments()) {
                if (o.getUserInfo().getUid().equals(uid)) {
                    comment.setId(o.getContentId());
                    break;
                }
            }
            if (comment.getId() == null) {
                LOGGER.info("没找到评论");
            }
        } else {
            LOGGER.error("由于{},评论失败[{}]", result, attackPage.getLink());
            //// TODO: 16-7-23 记录日志到DB
        }
        return comment;
    }

    @Override public Comment reply(Comment comment) {
        return null;
    }

    @Override public void praise(Comment comment) throws RequestException, URISyntaxException {

        String requestUrl =
                "http://api.t.iqiyi.com/qx_api/comment/like?$albumid=503325200&$antiCsrf=0a6c572e0b6d101791a4ddd549857d3c&cb=fnsucc&contentid="
                        + comment.getId()
                        + "&is_video_page=true&qitancallback=fnsucc&$qitanid=11075642&qypid=01010011010000000000&t=0.8853676982141423&$tvid=503325200&$uid=85840559";

        requestUrl = initUrl(requestUrl);

        HttpGet httpGet = new HttpGet(requestUrl);
        //设置cookies
        String praiseCookie = JSON.parseObject(attackParam.getCookies()).getString("praise");
        List<HttpCookieEx> cookieList = new ArrayList<>();
        cookieList.addAll(FilterCookies.filter(praiseCookie));
        CookieHelper.setCookies2(requestUrl, httpGet, cookieList);
        //攻击
        String result = XHttpClient.doRequest(httpGet, attackParam.getCharset());
        result = result.replace("var fnsucc=", "");
        JSONObject jsonObject = JSON.parseObject(result);
        String code = jsonObject.getString("code");
        if (!"A00000".equals(code)) {
            LOGGER.error("点赞失败，返回码：{}", code);
        }
    }

    @Override public void echo() {

    }

    @Override public List<Comment> listHotComment(int maxComment, int maxReply) throws RequestException {

        List<Comment> list = new ArrayList<>();

        String requestUrl = "http://api.t.iqiyi.com/qx_api/comment/get_video_comments?$aid=11741438&$albumid=281718700&categoryid=1&cb=fnsucc&escape=true&is_video_page=true&need_reply=true&need_subject=true&need_total=1&page=1&page_size=10&page_size_reply=3&qitan_comment_type=1&qitancallback=fnsucc&$qitanid=11741438&qypid=01010011010000000000&reply_sort=hot&sort=hot&t=0.22156078186678108&$tvid=281718700";
        requestUrl = initUrl(requestUrl);

        HttpGet httpGet = new HttpGet(requestUrl);
        String response = XHttpClient.doRequest(httpGet, attackParam.getCharset());

        response = response.replace("var fnsucc=", "");

        JSONObject jsonObject = JSON.parseObject(response);
        JSONArray comments = jsonObject.getJSONObject("data").getJSONArray("comments");

        for (int i = 0; i < maxComment && i < comments.size(); i++) {
            JSONObject obj = comments.getJSONObject(i);
            Comment comment = new Comment();
            comment.setId(obj.getString("contentId"));
            JSONArray replys = obj.getJSONArray("replyList");
            for (int j = 0; j < maxReply && j < replys.size(); j++) {
                JSONObject obj1 = replys.getJSONObject(j);
                Comment reply = new Comment();
                reply.setId(obj1.getString("id"));
                comment.getReplys().add(reply);
            }
            list.add(comment);
        }
        return list;
    }
}

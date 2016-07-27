package com.vip.integral.spider.aqy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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

    @Override public void init() throws RequestException {
        super.init();
        //收集公共参数
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
    }

    @Override public Comment comment() throws URISyntaxException, UnsupportedEncodingException, RequestException {

        //初始化
        String requestUrl = "http://api.t.iqiyi.com/qx_api/comment/publish";
        HttpPost httpPost = new HttpPost(requestUrl);

        //设置header
        httpPost.setHeader("Accept", accept);
        httpPost.setHeader("Origin", origin);
        httpPost.setHeader("User-Agent", userAgent);
        httpPost.setHeader("Referer", attackPage.getAttackPage().getLink());

        //设置表单参数
        String commentData = "";
        List<NameValuePair> params = initForm(commentData);
        //设置title
        String title = element.attr("data-qitancomment-title");
        params.add(new BasicNameValuePair("title", title));
        //设置sync_src
        params.add(new BasicNameValuePair("sync_src", title));
        //设置playurl
        params.add(new BasicNameValuePair("playurl", attackPage.getAttackPage().getLink()));
        //设置current_url
        params.add(new BasicNameValuePair("current_url", attackPage.getAttackPage().getLink()));

        //设置text
        params.add(new BasicNameValuePair("text", JSON.parseObject(action).getString("comment")));
        //设置tv_year
        String tv_year = element.attr("data-qitancomment-tvyear");
        params.add(new BasicNameValuePair("tv_year", tv_year));
        httpPost.setEntity(new UrlEncodedFormEntity(params, attackParam.getCharset()));
        //设置cookie
        String commentCookie = "";
        List<HttpCookieEx> cookieList = new ArrayList<>();
        cookieList.addAll(FilterCookies.filter(commentCookie));
        CookieHelper.setCookies2(requestUrl, httpPost, cookieList);

        //攻击
        String result = XHttpClient.doRequest(httpPost, attackParam.getCharset());
        JSONObject jsonObject = JSON.parseObject(result);
        String code = jsonObject.getString("code");
        Comment comment = null;
        if ("A00000".equals(code)) {
            comment = new Comment();
            comment.setId(jsonObject.getJSONObject("data").getString("contentId"));
        } else {
            LOGGER.error("评论失败[" + attackPage.getAttackPage().getLink() + "]");
            //// TODO: 16-7-23 记录日志到DB
        }
        return comment;
    }

    @Override public Comment reply(Comment comment) {
        return null;
    }

    @Override public void praise(Comment comment) throws RequestException {

        String requestUrl =
                "http://api.t.iqiyi.com/qx_api/comment/like?$albumid=503325200&$antiCsrf=0a6c572e0b6d101791a4ddd549857d3c&cb=fnsucc&contentid="
                        + comment.getId()
                        + "&is_video_page=true&qitancallback=fnsucc&$qitanid=11075642&qypid=01010011010000000000&t=0.8853676982141423&$tvid=503325200&$uid=85840559";

        HttpGet httpGet = new HttpGet(requestUrl);
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

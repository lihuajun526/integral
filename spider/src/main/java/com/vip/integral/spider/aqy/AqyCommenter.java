package com.vip.integral.spider.aqy;

import com.alibaba.fastjson.JSON;
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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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

    @Override public Comment comment() throws URISyntaxException, UnsupportedEncodingException, RequestException {

        //初始化
        String requestUrl = "http://api.t.iqiyi.com/qx_api/comment/publish";
        HttpPost httpPost = new HttpPost(requestUrl);

        //设置header
        httpPost.setHeader("Accept", accept);
        httpPost.setHeader("Origin", origin);
        httpPost.setHeader("User-Agent", userAgent);
        httpPost.setHeader("Referer", commentAttackPage.getPageLink().getLink());

        //设置表单参数
        List<NameValuePair> params = initForm(commentAttackParam.getData());
        String response = XHttpClient
                .doRequest(new HttpGet(commentAttackPage.getPageLink().getLink()), commentAttackParam.getCharset());
        Document doc = Jsoup.parse(response);
        Element element = null;
        //设置albumid
        element = doc.getElementById("videoShopGuideWrap");
        String albumid = element.attr("data-shop-albumid");
        params.add(new BasicNameValuePair("albumid", albumid));
        //设置tvid
        element = doc.getElementById("qitancommonarea");
        String tvid = element.attr("data-qitancomment-tvid");
        params.add(new BasicNameValuePair("tvid", tvid));
        //设置title
        String title = element.attr("data-qitancomment-title");
        params.add(new BasicNameValuePair("title", title));
        //设置sync_src
        params.add(new BasicNameValuePair("sync_src", title));
        //设置playurl
        params.add(new BasicNameValuePair("playurl", commentAttackPage.getPageLink().getLink()));
        //设置current_url
        params.add(new BasicNameValuePair("current_url", commentAttackPage.getPageLink().getLink()));
        //设置qitanid
        String qitanid = element.attr("data-qitancomment-qitanid");
        params.add(new BasicNameValuePair("qitanid", qitanid));
        //设置text
        params.add(new BasicNameValuePair("text", JSON.parseObject(action).getString("comment")));
        //设置tv_year
        String tv_year = element.attr("data-qitancomment-tvyear");
        params.add(new BasicNameValuePair("tv_year", tv_year));
        httpPost.setEntity(new UrlEncodedFormEntity(params, commentAttackParam.getCharset()));
        //设置cookie
        List<HttpCookieEx> cookieList = new ArrayList<>();
        cookieList.addAll(FilterCookies.filter(commentAttackParam.getCookie()));
        CookieHelper.setCookies2(requestUrl, httpPost, cookieList);

        //攻击
        String result = XHttpClient.doRequest(httpPost, commentAttackParam.getCharset());
        JSONObject jsonObject = JSON.parseObject(result);
        String code = jsonObject.getString("code");
        Comment comment = null;
        if ("A00000".equals(code)) {
            comment = new Comment();
            comment.setId(jsonObject.getJSONObject("data").getString("contentId"));
        } else {
            LOGGER.error("评论失败[" + commentAttackPage.getPageLink().getLink() + "]");
            //// TODO: 16-7-23 记录日志到DB
        }
        return comment;
    }

    @Override public void reply() {

    }

    @Override public void praise() {

    }

    @Override public void echo() {

    }
}

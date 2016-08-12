package com.vip.integral.attack.tengxun;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.vip.integral.attack.qzone.bean.AttackAttr;
import com.vip.integral.attack.qzone.bean.QZoneComment;
import com.vip.integral.bean.Comment;
import com.vip.integral.component.Commenter;
import com.vip.integral.exception.CommentException;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static com.vip.integral.constant.ExceptionTypeEnum.COMMENT_ERROR;

/**
 * QQ空间评论者
 * Created by lihuajun on 16-7-16.
 */
public class TxxwCommenter extends Commenter {

    private static final Logger LOGGER = LoggerFactory.getLogger(TxxwCommenter.class);

    private String userAgent = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.86 Safari/537.36";
    private AttackAttr attackAttr;
    private Integer sex;

    public void setAttackAttr(AttackAttr attackAttr) {
        this.attackAttr = attackAttr;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getSex() {
        return sex;
    }

    @Override
    public void init() throws RequestException, UnsupportedEncodingException {

        if (pubParams.size() > 0)
            return;

        super.init();
        //收集公共参数

    }

    /**
     * 获得最新的说说
     *
     * @return
     */
    public QZoneComment getLatest() {

        QZoneComment qZoneComment = null;

        try {
            //初始化
            this.init();
            String underdog = attackAttr.getUserInfo().getUin().toString();//被攻击者
            String requestUrl = "https://h5.qzone.qq.com/proxy/domain/taotao.qq.com/cgi-bin/emotion_cgi_msglist_v6?uin=" + underdog + "&ftype=0&sort=0&pos=0&num=20&replynum=100&g_tk=" + pubParams.get("g_tk") + "&callback=&code_version=1&format=jsonp&need_private_comment=1";
            requestUrl = initUrl(requestUrl);
            LOGGER.info("获取说说的链接[{}]", requestUrl);

            HttpGet httpGet = new HttpGet(requestUrl);
            //设置header
            httpGet.setHeader("Accept", "*/*");
            httpGet.setHeader("User-Agent", userAgent);
            httpGet.setHeader("Referer", "http://user.qzone.qq.com/" + underdog + "/311");
            httpGet.setHeader("Host", "h5.qzone.qq.com");
            //设置cookie
            String getLatestCookie = JSON.parseObject(attackParam.getCookies()).getString("getLatest");
            List<HttpCookieEx> cookieList = new ArrayList<>();
            cookieList.addAll(FilterCookies.filter(getLatestCookie));
            CookieHelper.setCookies2(requestUrl, httpGet, cookieList);
            //攻击
            String response = XHttpClient.doRequest(httpGet, attackParam.getCharset());
            List<QZoneComment> qZoneComments = JSONArray.parseArray(JSONObject.parseObject(response.replace("_Callback(", "").replace(");", "")).getString("msglist"), QZoneComment.class);
            if (qZoneComments != null && qZoneComments.size() > 0)
                qZoneComment = qZoneComments.get(0);
        } catch (Exception e) {
            LOGGER.error("获取最新说说错误:", e);
        }
        return qZoneComment;
    }

    @Override
    public Comment comment() throws CommentException {

        Comment comment = null;
        //获得最新说说
        QZoneComment qZoneComment = this.getLatest();
        if (qZoneComment == null) {
            LOGGER.warn("无法获取{}的最新说说", attackAttr.getUserInfo().getUin());
            return comment;
        }
        try {
            //初始化
            this.init();
            String requestUrl = "http://h5.qzone.qq.com/proxy/domain/taotao.qzone.qq.com/cgi-bin/emotion_cgi_addcomment_ugc?g_tk=" + pubParams.get("g_tk");
            HttpPost httpPost = new HttpPost(requestUrl);
            //设置header
            httpPost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            httpPost.setHeader("Origin", "http://ctc.qzs.qq.com");
            httpPost.setHeader("User-Agent", userAgent);
            httpPost.setHeader("Referer", "http://ctc.qzs.qq.com/qzone/app/mood_v6/html/index.html");

            String underdog = attackAttr.getUserInfo().getUin().toString();//被攻击者
            String attacker = attackParam.getAccount();//攻击者
            //设置表单参数
            List<NameValuePair> params = initForm();
            params.add(new BasicNameValuePair("qzreferrer", "http://ctc.qzs.qq.com/qzone/app/mood_v6/html/index.html#mood&uin=" + underdog + "&pfid=2&qz_ver=8&appcanvas=0&qz_style=2&params=&entertime=" + System.currentTimeMillis() + "&canvastype="));
            params.add(new BasicNameValuePair("uin", attacker));//攻击者
            params.add(new BasicNameValuePair("hostUin", underdog));//被攻击者
            params.add(new BasicNameValuePair("topicId", attackAttr.getUserInfo().getUin() + "_" + qZoneComment.getTid()));
            params.add(new BasicNameValuePair("commentUin", attacker));//攻击者
            params.add(new BasicNameValuePair("content", JSONObject.parseObject(attackParam.getAction()).getString("comment")));
            params.add(new BasicNameValuePair("richval", ""));
            params.add(new BasicNameValuePair("richtype", ""));
            params.add(new BasicNameValuePair("inCharset", ""));
            params.add(new BasicNameValuePair("outCharset", ""));
            params.add(new BasicNameValuePair("ref", ""));
            params.add(new BasicNameValuePair("private", "0"));
            params.add(new BasicNameValuePair("with_fwd", "0"));
            params.add(new BasicNameValuePair("to_tweet", "0"));
            params.add(new BasicNameValuePair("hostuin", attacker));//攻击者
            params.add(new BasicNameValuePair("code_version", "1"));
            params.add(new BasicNameValuePair("format", "fs"));
            httpPost.setEntity(new UrlEncodedFormEntity(params, attackParam.getCharset()));
            //设置cookie
            String commentCookie = JSON.parseObject(attackParam.getCookies()).getString("comment");
            List<HttpCookieEx> cookieList = new ArrayList<>();
            cookieList.addAll(FilterCookies.filter(commentCookie));
            CookieHelper.setCookies2(requestUrl, httpPost, cookieList);
            //攻击
            Thread.sleep(6000);
            String result = XHttpClient.doRequest(httpPost, attackParam.getCharset());
            boolean isSuccess = false;
            try {
                Document doc = Jsoup.parse(result);
                String str = doc.select("script").html();
                str = str.substring(str.indexOf("frameElement.callback("));
                str = str.replace("frameElement.callback(", "").replace(");", "");
                LOGGER.debug("返回值：{}", str);
                JSONObject commentR = JSONObject.parseObject(str);
                comment = new Comment();
                comment.setId(commentR.getJSONObject("data").getString("id"));
                isSuccess = true;
            } catch (Exception e) {
                LOGGER.error("error:", e);
            }

            if (isSuccess) {
                LOGGER.info("评论成功[{}]", attackPage.getLink());

            } else {
                LOGGER.info("由于{},评论失败[{}]", result, attackPage.getLink());
                throw new CommentException(COMMENT_ERROR.code, result);
            }
        } catch (Exception e) {
            if (e instanceof CommentException)
                throw (CommentException) e;
            else {
                LOGGER.error("评论失败:", e);
                throw new CommentException(COMMENT_ERROR.code, e.getMessage());
            }
        }
        return comment;
    }


}

package com.vip.integral.attack.tengxun;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vip.integral.attack.tengxun.bean.TxxwCommentResult;
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
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.vip.integral.constant.ExceptionTypeEnum.COMMENT_ERROR;

/**
 * 腾讯新闻评论者
 * Created by lihuajun on 16-7-16.
 */
public class TxxwCommenter extends Commenter {

    private static final Logger LOGGER = LoggerFactory.getLogger(TxxwCommenter.class);

    private String userAgent = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.86 Safari/537.36";

    @Override
    public void init() throws RequestException, UnsupportedEncodingException {

        if (pubParams.size() > 0)
            return;

        super.init();
        //收集公共参数

    }


    /**
     * 评论
     *
     * @return
     * @throws CommentException
     */
    @Override
    public Comment comment() throws CommentException {

        Comment comment = null;

        try {
            //初始化
            this.init();
            String requestUrl = "http://w.coral.qq.com/article/comment/";
            HttpPost httpPost = new HttpPost(requestUrl);
            //设置header
            httpPost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            httpPost.setHeader("Origin", "http://www.qq.com");
            httpPost.setHeader("User-Agent", userAgent);
            httpPost.setHeader("Referer", "http://www.qq.com/coral/coralBeta3/coralMainDom3.0.htm");

            //设置表单参数
            String commentid = JSONObject.parseObject(attackPage.getAttr()).getString("commentid");
            List<NameValuePair> params = initForm();
            params.add(new BasicNameValuePair("targetid", commentid));
            params.add(new BasicNameValuePair("type", "1"));
            params.add(new BasicNameValuePair("format", "SCRIPT"));
            params.add(new BasicNameValuePair("callback", ""));
            String content = JSONObject.parseObject(attackParam.getAction()).getString("comment") + "-" + crRandom();
            params.add(new BasicNameValuePair("content", content));
            params.add(new BasicNameValuePair("_method", "put"));
            params.add(new BasicNameValuePair("g_tk", pubParams.get("g_tk")));
            params.add(new BasicNameValuePair("code", "1"));
            params.add(new BasicNameValuePair("source", "1"));
            params.add(new BasicNameValuePair("subsource", "0"));
            params.add(new BasicNameValuePair("picture", ""));
            httpPost.setEntity(new UrlEncodedFormEntity(params, attackParam.getCharset()));
            //设置cookie
            String commentCookie = JSON.parseObject(attackParam.getCookies()).getString("comment");
            List<HttpCookieEx> cookieList = new ArrayList<>();
            cookieList.addAll(FilterCookies.filter(commentCookie));
            CookieHelper.setCookies2(requestUrl, httpPost, cookieList);
            //攻击
            Thread.sleep(3000);
            String result = XHttpClient.doRequest(httpPost, attackParam.getCharset());
            boolean isSuccess = false;
            try {
                Document doc = Jsoup.parse(result);
                String str = doc.select("script").html();
                str = str.substring(str.indexOf("var data=")).replace("var data=", "");
                str = str.substring(0, str.indexOf("}catch(e)"));
                LOGGER.debug("评论后的返回结果为:{}", str);
                TxxwCommentResult txxwCommentResult = JSONObject.parseObject(str, TxxwCommentResult.class);
                comment = new Comment();
                comment.setId(txxwCommentResult.getData().getCommentid());
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

    /**
     * 回复
     */
    @Override
    public Comment reply(Comment comment) throws CommentException {

        String replyUrlTpl = "http://w.coral.qq.com/article/comment/to/%s";
        Comment reply = null;
        try {
            //初始化
            this.init();
            String replyUrl = String.format(replyUrlTpl, comment.getId());
            HttpPost httpPost = new HttpPost(replyUrl);
            //设置header
            httpPost.setHeader("Host", "w.coral.qq.com");
            httpPost.setHeader("Origin", "http://www.qq.com");
            httpPost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            httpPost.setHeader("User-Agent", userAgent);
            httpPost.setHeader("Referer", "http://www.qq.com/coral/coralBeta3/coralMainDom3.2.htm");
            //设置表单参数
            String commentid = JSONObject.parseObject(attackPage.getAttr()).getString("commentid");
            List<NameValuePair> params = initForm();
            params.add(new BasicNameValuePair("targetid", commentid));
            params.add(new BasicNameValuePair("type", "1"));
            params.add(new BasicNameValuePair("format", "SCRIPT"));
            params.add(new BasicNameValuePair("callback", "parent.popCallback"));
            String content = JSONObject.parseObject(attackParam.getAction()).getString("reply") + "-" + crRandom();
            params.add(new BasicNameValuePair("content", content));
            params.add(new BasicNameValuePair("_method", "put"));
            params.add(new BasicNameValuePair("g_tk", pubParams.get("g_tk")));
            params.add(new BasicNameValuePair("code", "1"));
            params.add(new BasicNameValuePair("source", "1"));
            params.add(new BasicNameValuePair("subsource", "0"));
            params.add(new BasicNameValuePair("picture", ""));
            httpPost.setEntity(new UrlEncodedFormEntity(params, attackParam.getCharset()));
            //设置cookie
            String commentCookie = JSON.parseObject(attackParam.getCookies()).getString("reply");
            List<HttpCookieEx> cookieList = new ArrayList<>();
            cookieList.addAll(FilterCookies.filter(commentCookie));
            CookieHelper.setCookies2(replyUrl, httpPost, cookieList);
            //攻击
            Thread.sleep(5000);
            String result = XHttpClient.doRequest(httpPost, attackParam.getCharset());
            boolean isSuccess = false;
            try {
                String str = result.substring(result.indexOf("parent.popCallback(")).replace("parent.popCallback(", "");
                str = str.substring(0, str.indexOf(")}catch(e"));
                LOGGER.debug("回复后的返回结果为:{}", str);
                TxxwCommentResult txxwCommentResult = JSONObject.parseObject(str, TxxwCommentResult.class);
                reply = new Comment();
                reply.setId(txxwCommentResult.getData().getCommentid());
                isSuccess = true;
            } catch (Exception e) {
                LOGGER.error("error:", e);
            }

            if (isSuccess) {
                LOGGER.info("回复成功[{}]", attackPage.getLink());

            } else {
                LOGGER.info("由于{},回复失败[{}]", result, attackPage.getLink());
                throw new CommentException(COMMENT_ERROR.code, result);
            }
        } catch (Exception e) {
            if (e instanceof CommentException)
                throw (CommentException) e;
            else {
                LOGGER.error("回复失败:", e);
                throw new CommentException(COMMENT_ERROR.code, e.getMessage());
            }
        }
        return reply;
    }

    /**
     * 附和
     *
     * @param comment
     */
    @Override
    public Comment echo(Comment comment) throws CommentException {
        return reply(comment);
    }

    @Override
    public void praise(Comment comment) {
        String praiseUrlTpl = "http://w.coral.qq.com/article/comment/up/to/%s?targetid=%s";
        try {
            String targetid = JSONObject.parseObject(attackPage.getAttr()).getString("commentid");
            String praiseUrl = String.format(praiseUrlTpl, comment.getId(), targetid);
            //设置header
            HttpGet httpGet = new HttpGet(praiseUrl);
            httpGet.setHeader("Host", "w.coral.qq.com");
            httpGet.setHeader("Accept", "*/*");
            httpGet.setHeader("User-Agent", userAgent);
            httpGet.setHeader("Referer", "http://www.qq.com/coral/coralBeta3/coralMainDom3.2.htm");
            //设置cookie
            String commentCookie = JSON.parseObject(attackParam.getCookies()).getString("reply");
            List<HttpCookieEx> cookieList = new ArrayList<>();
            cookieList.addAll(FilterCookies.filter(commentCookie));
            CookieHelper.setCookies2(praiseUrl, httpGet, cookieList);
            //攻击
            Thread.sleep(2000);
            String result = XHttpClient.doRequest(httpGet, attackParam.getCharset());
            boolean isSuccess = false;
            try {
                JSONObject jsonObject = JSONObject.parseObject(result);
                Integer errCode = jsonObject.getInteger("errCode");
                if (errCode == 0)
                    isSuccess = true;
                else
                    isSuccess = false;
            } catch (Exception e) {
                LOGGER.error("error:", e);
            }
            if (isSuccess) {
                LOGGER.info("点赞成功[{}]", attackPage.getLink());
            } else {
                LOGGER.info("由于{},点赞失败[{}]", result, attackPage.getLink());
                throw new CommentException(COMMENT_ERROR.code, result);
            }
        } catch (Exception e) {
            LOGGER.error("error:", e);
        }
    }

    /**
     * 产生一个随机字符串
     *
     * @return
     */
    private String crRandom() {
        StringBuffer random = new StringBuffer();
        for (int i = 0; i < 5; i++) {
            char c = (char) (65 + ((int) (Math.random() * 26)));
            random.append(c);
        }
        return random.append(System.currentTimeMillis()).toString();
    }

}

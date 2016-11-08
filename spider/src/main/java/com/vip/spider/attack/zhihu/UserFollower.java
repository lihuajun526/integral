package com.vip.spider.attack.zhihu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vip.dbservice.model.AttackPage;
import com.vip.dbservice.service.AttackPageService;
import com.vip.spider.component.Attacker;
import com.vip.spider.constant.ExceptionTypeEnum;
import com.vip.spider.exception.CommentException;
import com.vip.spider.exception.UserFollowException;
import com.vip.spider.util.XHttpClient;
import com.vip.spider.util.cookie.CookieHelper;
import com.vip.spider.util.cookie.FilterCookies;
import com.vip.spider.util.cookie.HttpCookieEx;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihuajun on 2016/10/30.
 */
public class UserFollower extends Attacker {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserFollower.class);

    private String accept = "*/*";
    private String origin = "https://www.zhihu.com";
    private String userAgent = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.86 Safari/537.36";

    private AttackPageService attackPageService;


    public UserFollower(AttackPageService attackPageService) {
        this.attackPageService = attackPageService;
    }

    public void follow() throws UserFollowException {
        try {
            action = attackParam.getAction();
            String requestUrl = "https://www.zhihu.com/node/MemberFollowBaseV2";
            HttpPost httpPost = new HttpPost(requestUrl);
            //设置header
            httpPost.setHeader("Accept", accept);
            httpPost.setHeader("Origin", origin);
            httpPost.setHeader("User-Agent", userAgent);
            httpPost.setHeader("Referer", attackPage.getLink());

            //设置表单参数
            List<NameValuePair> params = new ArrayList<>();

            JSONObject attr = JSON.parseObject(attackPage.getAttr());

            params.add(new BasicNameValuePair("method", "follow_member"));
            params.add(new BasicNameValuePair("params", "{\"hash_id\":\"" + attr.getString("id") + "\"}"));
            httpPost.setEntity(new UrlEncodedFormEntity(params, attackParam.getCharset()));
            //设置头
            if (!StringUtils.isEmpty(attackParam.getHeader())) {
                String[] kvs = attackParam.getHeader().split(";");
                for (String kv : kvs) {
                    String[] strs = kv.split(":");
                    httpPost.setHeader(strs[0].trim(), strs[1].trim());
                }
            }
            //设置cookie
            String sendCookie = JSON.parseObject(attackParam.getCookies()).getString("send");
            List<HttpCookieEx> cookieList = new ArrayList<>();
            cookieList.addAll(FilterCookies.filter(sendCookie));
            CookieHelper.setCookies2(requestUrl, httpPost, cookieList);
            //攻击
            Thread.sleep(7000);
            String result = XHttpClient.doRequest(httpPost, attackParam.getCharset());
            JSONObject jsonObject = JSON.parseObject(result);
            String code = jsonObject.getString("r");

            if ("0".equals(code)) {
                LOGGER.info("关注成功[{}]", attackPage.getLink());
                AttackPage upAttackPage = new AttackPage();
                upAttackPage.setId(attackPage.getId());
                upAttackPage.setCount(attackPage.getCount() + 1);
                attackPageService.addCount(upAttackPage);
            } else {
                LOGGER.info("由于{},攻击者{}关注[{}]失败", jsonObject.getString("msg"), attackParam.getAccount(), attackPage.getLink());
                throw new UserFollowException(ExceptionTypeEnum.USER_FOLLOW_ERROR.code, result);
            }
        } catch (Exception e) {
            if (e instanceof CommentException)
                throw (UserFollowException) e;
            else {
                LOGGER.error("信息发送失败:", e);
                throw new UserFollowException(ExceptionTypeEnum.USER_FOLLOW_ERROR.code, e.getMessage());
            }
        }
    }

}

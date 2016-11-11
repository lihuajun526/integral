package com.operational.platform.spider.attack.zhihu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.operational.platform.spider.exception.MessageSendException;
import com.operational.platform.spider.util.XHttpClient;
import com.operational.platform.spider.util.cookie.CookieHelper;
import com.operational.platform.dbservice.model.AttackPage;
import com.operational.platform.dbservice.service.AttackPageService;
import com.operational.platform.spider.constant.ExceptionTypeEnum;
import com.operational.platform.spider.exception.CommentException;
import com.operational.platform.spider.util.cookie.FilterCookies;
import com.operational.platform.spider.util.cookie.HttpCookieEx;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by lihuajun on 2016/10/30.
 */
public class MessageSender extends com.operational.platform.spider.component.MessageSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageSender.class);

    private String accept = "*/*";
    private String origin = "https://www.zhihu.com";
    private String userAgent = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.86 Safari/537.36";

    private AttackPageService attackPageService;


    public MessageSender(AttackPageService attackPageService) {
        this.attackPageService = attackPageService;
    }

    @Override
    public void send() throws MessageSendException {
        try {
            action = attackParam.getAction();
            String requestUrl = "https://www.zhihu.com/inbox/post";
            HttpPost httpPost = new HttpPost(requestUrl);
            //设置header
            httpPost.setHeader("Accept", accept);
            httpPost.setHeader("Origin", origin);
            httpPost.setHeader("User-Agent", userAgent);
            httpPost.setHeader("Referer", attackPage.getLink());

            //设置表单参数
            List<NameValuePair> params = new ArrayList<>();

            JSONObject attr = JSON.parseObject(attackPage.getAttr());

            params.add(new BasicNameValuePair("member_id", attr.getString("id")));
            //设置text
            Random random = new Random();
            params.add(new BasicNameValuePair("content", JSON.parseObject(action).getString("send") + random.nextFloat()));
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
            Thread.sleep(5000);
            String result = XHttpClient.doRequest(httpPost, attackParam.getCharset());
            JSONObject jsonObject = JSON.parseObject(result);
            String code = jsonObject.getString("r");

            if ("0".equals(code)) {
                LOGGER.info("信息发送成功[{}]", attackPage.getLink());
                AttackPage upAttackPage = new AttackPage();
                upAttackPage.setId(attackPage.getId());
                upAttackPage.setCount(attackPage.getCount() + 1);
                attackPageService.addCount(upAttackPage);
            } else {
                LOGGER.info("由于{},攻击者{}信息发送失败[{}]", jsonObject.getString("msg"), attackParam.getAccount(), attackPage.getLink());
                throw new MessageSendException(ExceptionTypeEnum.MESSAGE_SEND_ERROR.code, result);
            }
        } catch (Exception e) {
            if (e instanceof CommentException)
                throw (MessageSendException) e;
            else {
                LOGGER.error("信息发送失败:", e);
                throw new MessageSendException(ExceptionTypeEnum.MESSAGE_SEND_ERROR.code, e.getMessage());
            }
        }
    }

}

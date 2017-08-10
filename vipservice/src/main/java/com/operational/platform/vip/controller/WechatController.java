package com.operational.platform.vip.controller;

import com.alibaba.fastjson.JSONObject;
import com.operational.platform.dbservice.model.User;
import com.operational.platform.dbservice.model.WechatMsg;
import com.operational.platform.dbservice.service.*;
import com.operational.platform.vip.constant.Constant;
import com.operational.platform.vip.exception.OpenidNotExistException;
import com.operational.platform.vip.service.AppConfig;
import com.operational.platform.vip.util.XHttpClient;
import com.operational.platform.vip.util.wechat.WechatProcess;
import org.apache.http.client.methods.HttpGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

/**
 * Created by lihuajun on 16-7-6.
 */
@Controller
@RequestMapping("/wechat")
public class WechatController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WechatController.class);

    @Autowired
    private WechatService wechatService;
    @Autowired
    private UserService userService;
    @Autowired
    private ConfigService configService;
    @Autowired
    private VipAccountService vipAccountService;
    @Autowired
    private AppConfig appConfig;
    @Autowired
    private IntegralService integralService;

    @RequestMapping("/center")
    public void center(HttpServletRequest request, HttpServletResponse response) throws IOException, OpenidNotExistException {

        /** 读取接收到的xml消息 */
        StringBuffer sb = new StringBuffer();
        InputStream is = request.getInputStream();
        InputStreamReader isr = new InputStreamReader(is, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String s = null;
        while ((s = br.readLine()) != null) {
            sb.append(s);
        }
        //接收到微信端发送过来的xml数据
        String xml = sb.toString();
        String result = "";
        /** 判断是否是微信接入激活验证，只有首次接入验证时才会收到echostr参数，此时需要把它直接返回 */
        String echostr = request.getParameter("echostr");
        if (!StringUtils.isEmpty(echostr)) {
            LOGGER.info("接入成功");
            LOGGER.info(request.getParameter("nonce"));
            LOGGER.info(request.getParameter("signature"));
            result = echostr;
        } else {
            //正常的微信处理流程
            WechatMsg wechatMsg = new WechatProcess().processWechatMag(xml);
            String openid = wechatMsg.getFromUserName();
            WechatMsg reply = new WechatMsg();
            if ("subscribe".equalsIgnoreCase(wechatMsg.getEvent())) {//关注
                //获取用户基本信息
                HttpGet httpGet = new HttpGet("https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + Constant.ACCESS_TOKEN + "&openid=" + openid + "&lang=zh_CN");
                JSONObject jsonObject = null;
                try {
                    jsonObject = XHttpClient.doRequest(httpGet);
                } catch (Exception e) {
                    LOGGER.error("获取用户基本信息错误：", e);
                }
                String unionid = jsonObject.getString("unionid");
                User user = userService.getByUnionid(unionid);
                if (user == null) {//第一次关注公众号，且没在app中登录过
                    int integral = configService.getInt("integral.subscribe.encourage");
                    user = new User();
                    user.setStatus(1);
                    user.setIntegral(integral);
                    user.setOpenid(openid);
                    user.setNickname(jsonObject.getString("nickname"));
                    user.setSex(jsonObject.getInteger("sex"));
                    user.setLanguage(jsonObject.getString("language"));
                    user.setCity(jsonObject.getString("city"));
                    user.setProvince(jsonObject.getString("province"));
                    user.setCountry(jsonObject.getString("country"));
                    user.setHeadimgurl(jsonObject.getString("headimgurl"));
                    user.setSubscribeTime(new Date());
                    user.setUnionid(unionid);
                    user.setVipExpires(new Date());
                    userService.save(user);
                    reply.setContent("欢迎关注黑眼圈365，已赠送给您" + integral + "积分，下载APP后您可在爱奇艺、乐视、芒果TV中享受7天vip会员权益");
                } else {
                    if (StringUtils.isEmpty(user.getOpenid())) {//在app中登录过
                        int integral = configService.getInt("integral.subscribe.encourage");
                        reply.setContent("欢迎关注黑眼圈365，已赠送给您" + integral + "积分，下载APP后您可在爱奇艺、乐视、芒果TV中享受7天vip会员权益");
                        user.setIntegral(user.getIntegral() + integral);
                    } else {//取消关注后再次关注
                        reply.setContent("欢迎回来");
                    }
                    user.setOpenid(openid);
                    user.setStatus(1);
                    userService.update(user);
                }
                reply.setToUserName(openid);
                reply.setFromUserName(appConfig.wechatAccount);
                reply.setCreateTime(new Date());
                reply.setMsgType("text");

                result = reply.toXml();
                LOGGER.info("关注后的返回数据：" + result);
            } else if ("unsubscribe".equalsIgnoreCase(wechatMsg.getEvent())) {//取消关注
                User updateUser = new User();
                User dbUser = userService.getByOpenid(openid);
                if (dbUser != null) {
                    updateUser.setId(dbUser.getId());
                    updateUser.setStatus(0);
                    userService.update(updateUser);
                    LOGGER.info("用户[{}]取消关注成功", dbUser.getNickname());
                }
            } else if ("scan".equalsIgnoreCase(wechatMsg.getEvent())) {
                LOGGER.info("已关注的用户[{}]扫描二维码不做任何处理");
            } else if ("click".equalsIgnoreCase(wechatMsg.getEvent())) {//点击菜单

            }
        }
        PrintWriter writer = response.getWriter();
        writer.print(result);
        writer.flush();
        writer.close();
    }

    /**
     * 异常处理
     *
     * @param e
     * @param response
     */
    @ExceptionHandler(Exception.class)
    private void exception(Exception e, HttpServletResponse response) {

        LOGGER.error("have exception", e);
        try {
            PrintWriter writer = response.getWriter();
            writer.print("success");
            writer.flush();
            writer.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}

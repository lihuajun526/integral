package com.operational.platform.vip.controller;

import com.alibaba.fastjson.JSONObject;
import com.operational.platform.common.util.Config;
import com.operational.platform.dbservice.model.IntegralRecord;
import com.operational.platform.dbservice.model.Log;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by lihuajun on 16-7-6.
 */
@Controller
@RequestMapping("/wechat")
public class WechatController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WechatController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private LogService logService;
    @Autowired
    private AppConfig appConfig;

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
        try {
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
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    Calendar c = Calendar.getInstance();
                    boolean isNew = false;
                    User user = userService.getByOpenid(openid);
                    if (user != null) {//曾经关注过
                        //修改关注状态
                        user.setStatus(1);
                        userService.update(user);
                        reply.setContent("感谢您再次关注影咖...");
                    } else {
                        //获取用户基本信息
                        HttpGet httpGet = new HttpGet("https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + Constant.ACCESS_TOKEN + "&openid=" + openid + "&lang=zh_CN");
                        JSONObject jsonObject = XHttpClient.doRequest(httpGet);
                        String unionid = jsonObject.getString("unionid");
                        user = userService.getByUnionid(unionid);
                        if (user != null) {//有下载app，补存信息，赠送会员天数
                            user.setOpenid(openid);
                            user.setSubscribeTime(new Date());
                            user.setStatus(1);
                            if (user.getVipExpires().getTime() > System.currentTimeMillis())
                                c.setTime(user.getVipExpires());
                            else
                                c.setTime(new Date());
                            c.add(Calendar.DAY_OF_YEAR, Config.getInt("user.share.days.encourage"));
                            user.setVipExpires(c.getTime());
                            userService.update(user);
                            reply.setContent("欢迎关注影咖，已赠送您" + Config.get("user.subscribe.days.encourage") + "天会员权益，您的会员权益延迟至" + sdf.format(c.getTime()));

                            isNew = true;
                        } else {//创建新用户，赠送会员天数+积分
                            user = new User();
                            user.setStatus(1);
                            user.setIntegral(Config.getInt("user.regist.integral.encourage"));
                            user.setOpenid(openid);
                            user.setNickname(jsonObject.getString("nickname"));
                            user.setSex(jsonObject.getInteger("sex"));
                            user.setLanguage(jsonObject.getString("language"));
                            user.setCity(jsonObject.getString("city"));
                            user.setProvince(jsonObject.getString("province"));
                            user.setCountry(jsonObject.getString("country"));
                            user.setHeadimgurl(jsonObject.getString("headimgurl"));
                            user.setPrivilege(jsonObject.getString("privilege"));
                            user.setSubscribeTime(new Date());
                            user.setUnionid(unionid);
                            c.setTime(new Date());
                            c.add(Calendar.DAY_OF_YEAR, Config.getInt("user.regist.days.encourage"));
                            user.setVipExpires(c.getTime());

                            IntegralRecord integralRecord = new IntegralRecord();
                            integralRecord.setUserid(user.getId());
                            integralRecord.setDescription("新用户注册");
                            integralRecord.setGoodsid(0);
                            integralRecord.setType(14);
                            integralRecord.setIntegral(Config.getInt("user.regist.integral.encourage"));
                            userService.saveUserAndRecord(user, integralRecord);
                            reply.setContent("欢迎关注影咖，已赠送您30天黄金会员，下载APP即可在爱奇艺、乐视、芒果TV中免费使用");

                            isNew = true;
                        }
                    }

                    if (isNew && !StringUtils.isEmpty(wechatMsg.getEventKey())) {
                        if (wechatMsg.getEventKey().startsWith("share_")) {
                            String[] strs = wechatMsg.getEventKey().split("_");
                            if (strs.length == 2) {//给推广用户加会员天数
                                Integer userid = Integer.parseInt(strs[1]);
                                User friend = userService.get(userid);
                                if (friend != null) {
                                    if (friend.getVipExpires().getTime() > System.currentTimeMillis()) {
                                        c.setTime(friend.getVipExpires());
                                    } else {
                                        c.setTime(new Date());
                                    }
                                    c.add(Calendar.DAY_OF_YEAR, Config.getInt("user.populate.days.encourage"));
                                    friend.setVipExpires(c.getTime());
                                    userService.update(friend);

                                    reply.setContent(reply.getContent() + "\n\n" + "同时您的好友" + friend.getNickname() + "也将获得" + Config.getInt("user.populate.days.encourage") + "天会员权益的奖励");

                                    Log log = new Log();
                                    log.setType(2);
                                    log.setDescription("用户[" + friend.getId() + "]将用户[" + user.getId() + "]拉进公众号获得[" + Config.getInt("user.populate.days.encourage") + "天]奖励");
                                    logService.save(log);
                                }
                            } else {
                                LOGGER.error("获取推广用户id异常[{}]", wechatMsg.getEventKey());
                            }
                        }

                    }
                    reply.setToUserName(openid);
                    reply.setFromUserName(appConfig.wechatAccount);
                    reply.setCreateTime(new Date());
                    reply.setMsgType("text");

                    result = reply.toXml();
                    LOGGER.debug("关注后的返回数据：" + result);
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
                    WechatMsg r = new WechatMsg();
                    reply.setToUserName(openid);
                    reply.setFromUserName(appConfig.wechatAccount);
                    reply.setCreateTime(new Date());
                    reply.setMsgType("text");
                    if ("NEW_ONLINE".equalsIgnoreCase(wechatMsg.getEventKey())) {
                        reply.setContent("努力开发中，敬请期待");
                    } else if ("GOOD_VIDEO".equalsIgnoreCase(wechatMsg.getEventKey())) {
                        reply.setContent("努力开发中，敬请期待");
                    }
                    result = reply.toXml();
                }
            }
        } catch (Exception e) {
            LOGGER.error("error:", e);
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

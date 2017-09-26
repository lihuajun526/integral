package com.operational.platform.vip.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.operational.platform.common.constant.ExceptionCode;
import com.operational.platform.common.constant.ExceptionTypeEnum;
import com.operational.platform.common.exception.CommonException;
import com.operational.platform.common.exception.CryptoException;
import com.operational.platform.common.exception.RequestException;
import com.operational.platform.common.util.AESCryptoUtil;
import com.operational.platform.common.util.Config;
import com.operational.platform.common.util.StrUtil;
import com.operational.platform.common.util.XHttpClient;
import com.operational.platform.dbservice.model.IntegralRecord;
import com.operational.platform.dbservice.model.User;
import com.operational.platform.dbservice.service.UserService;
import com.operational.platform.vip.base.BaseController;
import com.operational.platform.vip.base.Result;
import com.operational.platform.vip.constant.Constant;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lihuajun on 16-7-6.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/get")
    public String getUser(User user) {
        Result<User> result = new Result<>();

        result.setCode(0);
        result.setData(userService.getByOpenid(user.getOpenid()));

        return result.toString();
    }

    /**
     * 获得用户的推广记录
     *
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping("/spreads")
    public String listSpreads(User user) {
        Result<User> result = new Result<>();

        result.setCode(0);
        result.setData(userService.getByOpenid(user.getOpenid()));

        return result.toString();
    }

    @RequestMapping("/member")
    public ModelAndView member(HttpServletRequest request) {

        String openid = (String) request.getSession().getAttribute("openid");

        ModelAndView modelAndView = new ModelAndView("member");
        modelAndView.addObject("user", userService.getByOpenid(openid));
        return modelAndView;
    }

    @RequestMapping(value = "/login/wechat")
    @ResponseBody
    public String loginFromWechat(String accessToken, String openid, String unionid) throws CommonException {

        Result<User> result = new Result<>();
        if (StringUtils.isEmpty(accessToken)) {
            result.set(ExceptionCode.PARAM_IS_NULL_ERROR.code, "access_token参数为空");
            return result.toString();
        }
        if (StringUtils.isEmpty(openid)) {
            result.set(ExceptionCode.PARAM_IS_NULL_ERROR.code, "openid参数为空");
            return result.toString();
        }
        if (StringUtils.isEmpty(unionid)) {
            result.set(ExceptionCode.PARAM_IS_NULL_ERROR.code, "unionid参数为空");
            return result.toString();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, 28);//让日期加28
        User user = userService.getByUnionid(unionid);
        if (user == null) {
            //获取用户基本信息
            String url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken + "&openid=" + openid;
            HttpGet httpGet = new HttpGet(url);
            String response = null;
            try {
                response = XHttpClient.doRequest(httpGet);
            } catch (RequestException e) {
                logger.error("请求获取微信用户基本信息失败");
                throw new CommonException(ExceptionTypeEnum.Get_User_Info_ERROR);
            }
            JSONObject jsonObject = JSONObject.parseObject(response);
            if (!StringUtils.isEmpty(jsonObject.getString("errcode"))) {
                logger.error("errcode={}", jsonObject.getString("errcode"));
                throw new CommonException(ExceptionTypeEnum.Get_User_Info_ERROR);
            }
            //保存用户到DB
            user = new User();
            user.setAppOpenid(jsonObject.getString("openid"));
            user.setNickname(jsonObject.getString("nickname"));
            user.setSex(jsonObject.getInteger("sex"));
            user.setLanguage(jsonObject.getString("language"));
            user.setProvince(jsonObject.getString("province"));
            user.setCity(jsonObject.getString("city"));
            user.setCountry(jsonObject.getString("country"));
            user.setHeadimgurl(jsonObject.getString("headimgurl"));
            user.setPrivilege(jsonObject.getString("privilege"));
            user.setUnionid(jsonObject.getString("unionid"));
            user.setVipAccessTokenExpires(calendar.getTime());
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_MONTH, Config.getInt("user.regist.days.encourage"));
            user.setVipExpires(calendar.getTime());
            user.setIntegral(Config.getInt("user.regist.integral.encourage"));

            String vipAccessToken = StrUtil.getRandomString(32) + System.currentTimeMillis();
            try {
                user.setVipAccessToken(AESCryptoUtil.encrypt(vipAccessToken));
            } catch (CryptoException e) {
                logger.error("加密[{}]失败", vipAccessToken);
                user.setVipAccessToken(accessToken);
            }

            IntegralRecord integralRecord = new IntegralRecord();
            integralRecord.setUserid(user.getId());
            integralRecord.setDescription("新用户注册");
            integralRecord.setGoodsid(0);
            integralRecord.setType(14);
            integralRecord.setIntegral(Config.getInt("user.regist.integral.encourage"));
            userService.saveUserAndRecord(user, integralRecord);
            //保存用户到sessionMap
            Constant.SessionMap.put(user.getVipAccessToken(), user);
        } else {
            String oldVipAccessToken = user.getVipAccessToken();
            //更新DB中用户vipAccessToken及过期时间
            user.setVipAccessTokenExpires(calendar.getTime());
            user.setAppOpenid("openid");
            try {
                user.setVipAccessToken(AESCryptoUtil.encrypt(StrUtil.getRandomString(32) + System.currentTimeMillis()));
            } catch (CryptoException e) {
                logger.error("加密[{}]失败", accessToken);
                user.setVipAccessToken(accessToken);
            }
            userService.update(user);
            //更新sessionMap中vipAccessToken及过期时间
            if (!StringUtils.isEmpty(oldVipAccessToken))
                Constant.SessionMap.remove(oldVipAccessToken);
            Constant.SessionMap.put(user.getVipAccessToken(), user);
        }
        result.setData(user);
        return result.toString();
    }

    @ResponseBody
    @RequestMapping("/is/subscribe/v_login")
    public String isSubscribe(String vipAccessToken) {

        /*Result<Map<String, Object>> result = new Result<>();
        Map<String, Object> data = new HashMap<>();

        AppVersion appVersion = baseInfoService.getLatestVersion(appType);
        List<Regular> regulars = baseInfoService.listAll();

        data.put("version", appVersion);
        data.put("regulars", regulars);
        if (!StringUtils.isEmpty(vipAccessToken)) {
            User loginUser = Constant.SessionMap.get(vipAccessToken);
            if (loginUser != null) {
                data.put("vipExpires", loginUser.getVipExpires());
                data.put("integral", loginUser.getIntegral());
            }
        }
        List<String> descList = new ArrayList<>();
        String buyDesc = configService.getString("buy.desc");
        for (String desc : buyDesc.split("#")) {
            descList.add(desc);
        }
        data.put("buyDesc", descList);

        String linkWay = configService.getString("link.way");
        data.put("linkWay", JSONObject.parseObject(linkWay));

        result.setData(data);
        return result.toString();*/
        return null;
    }

    @RequestMapping(value = "/login/wechat/temp")
    @ResponseBody
    public String loginTemp(String username, String password) {
        Result<User> result = new Result<>();

        if (StringUtils.isEmpty(username)) {
            result.set(ExceptionCode.PARAM_IS_NULL_ERROR.code, "用户名不能为空");
            return result.toString();
        }

        if (StringUtils.isEmpty(password)) {
            result.set(ExceptionCode.PARAM_IS_NULL_ERROR.code, "密码不能为空");
            return result.toString();
        }

        if (!username.equals("super") || !password.equals("Password")) {
            result.set(ExceptionCode.USER_OR_PASSWORD_ERROR);
            return result.toString();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 28);//让日期加28

        User loginUser = userService.getByUnionid("ios");
        loginUser.setVipAccessTokenExpires(calendar.getTime());
        Constant.SessionMap.put(loginUser.getVipAccessToken(), loginUser);
        result.setData(loginUser);
        return result.toString();
    }

    @RequestMapping(value = "/nologin")
    @ResponseBody
    public String nologin() {

        Result result = new Result();
        result.set(ExceptionCode.NOT_LOGIN_ERROR.code, "未登录");
        return result.toString();

    }

    @RequestMapping(value = "/notoken")
    @ResponseBody
    public String notoken() {

        Result result = new Result();
        result.set(ExceptionCode.PARAM_IS_NULL_ERROR.code, "vipAccessToken为必传参数");
        return result.toString();

    }

    /**
     * 分享到朋友圈/分享给朋友获得奖励天数
     *
     * @param vipAccessToken
     * @return
     */
    @RequestMapping("/share/encourage/v_login")
    @ResponseBody
    public String encourageFromShare(String vipAccessToken) {

        Result<Long> result = new Result();

        User loginUser = Constant.SessionMap.get(vipAccessToken);
        Date date = userService.encourageFromShare(loginUser, Config.getInt("user.share.days.encourage"));

        if (date == null) {
            result.setCode(1);
            return result.toString();
        }

        loginUser = userService.getByAccessToken(vipAccessToken);
        Constant.SessionMap.put(vipAccessToken, loginUser);
        result.setCode(2);
        result.setData(date.getTime());
        return result.toString();

    }

    @RequestMapping("/page/share/{token}")
    public ModelAndView sharePage(@PathVariable String token) {

        User user = userService.getByAccessToken(token);
        Map<String, String> map = buildQRCode(user.getId());
        ModelAndView modelAndView = new ModelAndView("share");
        modelAndView.addObject("ticket", map.get("ticket"));
        modelAndView.addObject("url", map.get("url"));
        return modelAndView;
    }

    /**
     * 生成带参数二维码
     *
     * @param userid
     * @return
     */
    private Map<String, String> buildQRCode(Integer userid) {

        Map<String, String> map = new HashMap<>();
        HttpPost httpPost = new HttpPost("https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + Constant.ACCESS_TOKEN);
        try {
            StringEntity stringEntity = new StringEntity("{\"expire_seconds\": 2592000, \"action_name\": \"QR_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \"share_" + userid + "\"}}}");
            httpPost.setEntity(stringEntity);
            String response = XHttpClient.doRequest(httpPost);
            JSONObject jsonObject = JSON.parseObject(response);
            map.put("ticket", URLEncoder.encode(jsonObject.getString("ticket"), "utf-8"));
            return map;
        } catch (Exception e) {
            logger.error("error:", e);
        }
        //todo 返回不带参数的公众号二维码
        map.put("url", "");
        return map;
    }

}

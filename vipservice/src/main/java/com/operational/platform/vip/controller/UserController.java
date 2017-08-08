package com.operational.platform.vip.controller;

import com.alibaba.fastjson.JSONObject;
import com.operational.platform.common.constant.ExceptionCode;
import com.operational.platform.common.constant.ExceptionTypeEnum;
import com.operational.platform.common.exception.CommonException;
import com.operational.platform.common.exception.CryptoException;
import com.operational.platform.common.exception.RequestException;
import com.operational.platform.common.util.AESCryptoUtil;
import com.operational.platform.common.util.StrUtil;
import com.operational.platform.common.util.XHttpClient;
import com.operational.platform.dbservice.model.User;
import com.operational.platform.dbservice.service.UserService;
import com.operational.platform.vip.base.BaseController;
import com.operational.platform.vip.base.Result;
import com.operational.platform.vip.constant.Constant;
import org.apache.http.client.methods.HttpGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;

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
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 28);//让日期加28
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
            user.setProvince(jsonObject.getString("province"));
            user.setCity(jsonObject.getString("city"));
            user.setCountry(jsonObject.getString("country"));
            user.setHeadimgurl(jsonObject.getString("headimgurl"));
            user.setPrivilege(jsonObject.getString("privilege"));
            user.setUnionid(jsonObject.getString("unionid"));
            user.setVipAccessTokenExpires(calendar.getTime());
            user.setVipExpires(new Date());
            user.setIntegral(0);

            String vipAccessToken = StrUtil.getRandomString(32) + System.currentTimeMillis();
            try {
                user.setVipAccessToken(AESCryptoUtil.encrypt(vipAccessToken));
            } catch (CryptoException e) {
                logger.error("加密[{}]失败", vipAccessToken);
                user.setVipAccessToken(accessToken);
            }
            userService.save(user);
            //保存用户到sessionMap
            Constant.SessionMap.put(user.getVipAccessToken(), user);
        } else {
            String oldVipAccessToken = user.getVipAccessToken();
            //更新DB中用户vipAccessToken及过期时间
            user.setVipAccessTokenExpires(calendar.getTime());
            try {
                user.setVipAccessToken(AESCryptoUtil.encrypt(StrUtil.getRandomString(32) + System.currentTimeMillis()));
            } catch (CryptoException e) {
                logger.error("加密[{}]失败", accessToken);
                user.setVipAccessToken(accessToken);
            }
            userService.update(user);
            //更新sessionMap中vipAccessToken及过期时间
            Constant.SessionMap.remove(oldVipAccessToken);
            Constant.SessionMap.put(user.getVipAccessToken(), user);
        }
        result.setData(user);
        return result.toString();
    }

    @RequestMapping(value = "/login/wechat/temp")
    @ResponseBody
    public String loginTemp() {
        Result<User> result = new Result<>();

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

}

package com.vip.integral.constant;

import com.vip.integral.util.Config;

/**
 * Created by lihuajun on 2016/9/11.
 */
public class Constant {

    //微信access_token
    public static String ACCESS_TOKEN;

    public static String JSAPI_TICKET;

    public static String WECHAT_APPID = Config.get("wechat.appid");

    public static String WECHAT_SECRET = Config.get("wechat.secret");

    public static String APP_DOMAIN = Config.get("app.domain");

}

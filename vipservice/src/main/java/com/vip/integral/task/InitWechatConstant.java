package com.vip.integral.task;

import com.alibaba.fastjson.JSONObject;
import com.vip.integral.exception.RequestException;
import com.vip.integral.util.Config;
import com.vip.integral.util.XHttpClient;
import com.vip.integral.util.wechat.Constant;
import org.apache.http.client.methods.HttpGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by lihuajun on 2016/9/12.
 */
public class InitWechatConstant {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitWechatConstant.class);

    public static void run() {

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                String appid = Config.get("appid");
                String secret = Config.get("secret");
                //更新ACCESS_TOKEN
                HttpGet httpGet = new HttpGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + secret);
                try {
                    String response = XHttpClient.doRequest(httpGet);
                    JSONObject jsonObject = JSONObject.parseObject(response);
                    Constant.ACCESS_TOKEN = jsonObject.getString("ACCESS_TOKEN");
                    LOGGER.info("最新的ACCESS_TOKEN=" + Constant.ACCESS_TOKEN);
                } catch (RequestException e) {
                    LOGGER.error("更新ACCESS_TOKEN失败:", e);
                }

                //更新JSAPI_TICKET
                httpGet = new HttpGet("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + Constant.ACCESS_TOKEN + "&type=jsapi");
                try {
                    String response = XHttpClient.doRequest(httpGet);
                    JSONObject jsonObject = JSONObject.parseObject(response);
                    Constant.JSAPI_TICKET = jsonObject.getString("ticket");
                    LOGGER.info("最新的JSAPI_TICKET=" + Constant.JSAPI_TICKET);
                } catch (RequestException e) {
                    LOGGER.error("更新JSAPI_TICKET失败:", e);
                }
            }
        };
        Timer timer = new Timer();
        long delay = 0;
        long intevalPeriod = 7000 * 1000;
        timer.scheduleAtFixedRate(task, delay, intevalPeriod);
    }
}

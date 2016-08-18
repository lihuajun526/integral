package com.vip.integral.controller;

import com.vip.integral.service.WechatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by lihuajun on 16-7-6.
 */
@RequestMapping("/wechat")
public class WechatController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WechatController.class);

    @Autowired
    private WechatService wechatService;

    @RequestMapping("/get")
    public void getUser(HttpServletRequest request, HttpServletResponse response) throws IOException {

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
            result = echostr;
        } else {
            //正常的微信处理流程
            //result = new WechatProcess().processWechatMag(xml);
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

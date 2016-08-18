package com.vip.integral.util.wechat;


import com.vip.integral.model.WechatMsg;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

public class WechatProcess {

    private static final Logger LOGGER = LoggerFactory.getLogger(WechatProcess.class);

    public WechatMsg processWechatMag(String xml) {

        /** ����xml���� */
        Document doc = Jsoup.parse(xml);
        WechatMsg wechatMsg = create(doc);
        return wechatMsg;
    }

    private WechatMsg create(Document doc) {
        WechatMsg wechatMsg = new WechatMsg();
        try {
            Field[] fields = WechatMsg.class.getDeclaredFields();
            for (Field field : fields) {
                String key = field.getName();
                if (key.equalsIgnoreCase("LOGGER"))
                    continue;
                String typeName = field.getType().getSimpleName();
                byte[] b = {(byte) (key.charAt(0) - 32)};
                key = new String(b) + key.substring(1);
                Elements elements = doc.select(key.toLowerCase());
                if (elements == null || elements.size() == 0)
                    continue;
                String value = elements.get(0).text();
                if (StringUtils.isEmpty(value))
                    continue;
                if (typeName.equalsIgnoreCase("String")) {
                    Method method = WechatMsg.class.getMethod("set" + key, String.class);
                    method.invoke(wechatMsg, value);
                } else if (typeName.equalsIgnoreCase("Date")) {
                    Method method = WechatMsg.class.getMethod("set" + key, Date.class);
                    method.invoke(wechatMsg, new Date(Long.valueOf(value)));
                } else {
                    LOGGER.warn("��Ȼ��������{}", typeName);
                }
            }
        } catch (Exception e) {
            LOGGER.error("��xmlתΪWechatMsg����ʱ����:", e);
        }
        return wechatMsg;
    }
}

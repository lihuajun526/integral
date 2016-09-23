package com.vip.spider.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by lihuajun on 16-7-18.
 */
public class Config {

    private static Properties prop;

    static {
        prop = new Properties();
        try {
            InputStream is = Config.class.getClass().getResourceAsStream("/properties/config.properties");
            prop.load(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return prop.getProperty(key);
    }
}

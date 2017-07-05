package com.operational.platform.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by lihuajun on 16-7-18.
 */
public class Config {

    private static Properties prop;

    static {
        prop = new Properties();
        try {
            InputStream is = Config.class.getResourceAsStream("/properties/config.properties");
            prop.load(new InputStreamReader(is, "utf-8"));
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return prop.getProperty(key);
    }

    public static Integer getInt(String key) {
        return Integer.parseInt(prop.getProperty(key));
    }

    public static Long getLong(String key) {
        return Long.parseLong(prop.getProperty(key));
    }
}

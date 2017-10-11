package com.operational.platform.common.util;


import com.operational.platform.common.exception.String2TxtException;
import com.operational.platform.common.exception.Txt2StringException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Created by lihuajun on 2017/9/14.
 */
public class TxtUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(TxtUtil.class);

    public static String txt2String(File file) throws Txt2StringException {

        if (file == null || !file.exists())
            return "";

        StringBuilder result = new StringBuilder();
        String str = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                result.append(System.lineSeparator() + s);
            }
            br.close();
            str = result.toString().replaceAll("\\n", "").replaceAll("\\r", "");
        } catch (Exception e) {
            LOGGER.error("error:", e);
            throw new Txt2StringException();
        }
        return str;
    }

    public static void string2Txt(String str, File txtFile) throws String2TxtException {

        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(txtFile), "utf-8"));
            out.write(str);
            out.flush();
            out.close();
        } catch (Exception e) {
            throw new String2TxtException();
        }
    }

}

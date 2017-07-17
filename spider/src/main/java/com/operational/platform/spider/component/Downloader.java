package com.operational.platform.spider.component;

import com.operational.platform.common.util.Config;
import com.operational.platform.spider.util.StrUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * 附件下载器
 *
 * @author: Zhou Xuanang
 * @Date: 17:49 16/7/1.
 */
@Service
public class Downloader {

    private static final Logger LOGGER = LoggerFactory.getLogger(Downloader.class);


    /**
     * 图片下载
     *
     * @param picurl
     * @return
     */
    public static String downloadPic(String picurl) {
        String suffix = ".jpg";
        long fileName = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer(Config.get("filter.pictures.storage.path"));
        sb.append(File.separator).append(fileName).append(suffix);
        try {
            InputStream in;
            picurl = StrUtil.encodeUrl(picurl);
            URLConnection conn = new URL(picurl).openConnection();
            conn.setConnectTimeout(Config.getInt("filter.url.connection.timeout"));
            conn.setReadTimeout(Config.getInt("filter.url.connection.timeout"));
            in = conn.getInputStream();
            byte[] file = IOUtils.toByteArray(in);
            FileUtils.writeByteArrayToFile(new File(sb.toString()), file);
            IOUtils.closeQuietly(in);
        } catch (Exception e) {
            LOGGER.error("download picture[{}] timeout:", picurl, e);
            return picurl;
        }
        return "http://www.qheeshow.com/upload/images/20170716/" + fileName + suffix;
    }
}

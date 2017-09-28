package com.operational.platform.common.util;

import com.operational.platform.common.constant.ExceptionTypeEnum;
import com.operational.platform.common.exception.CommonException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;


public class Downloader {

    private static final Logger LOGGER = LoggerFactory.getLogger(Downloader.class);

    /**
     * 文件下载
     *
     * @param url
     * @param filePath
     * @return
     */
    public static String file(String url, String filePath) throws CommonException {

        CloseableHttpClient httpClient = HttpConnectionManager.getHttpClient();
        try {
            HttpGet httpGet = new HttpGet(url);
            HttpResponse resp = httpClient.execute(httpGet);
            if (HttpStatus.SC_OK == resp.getStatusLine().getStatusCode()) {
                HttpEntity entity = resp.getEntity();
                InputStream in = entity.getContent();
                byte[] file = IOUtils.toByteArray(in);
                FileUtils.writeByteArrayToFile(new File(filePath), file);
                IOUtils.closeQuietly(in);
            }
        } catch (Exception e) {
            LOGGER.error("下载失败[url={}]", url, e);
            throw new CommonException(ExceptionTypeEnum.File_Download_ERROR);
        }
        return filePath;
    }
}

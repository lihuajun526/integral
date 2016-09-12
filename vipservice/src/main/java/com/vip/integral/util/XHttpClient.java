package com.vip.integral.util;

import com.vip.integral.constant.ExceptionTypeEnum;
import com.vip.integral.exception.RequestException;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by lihuajun on 16-6-21.
 */
public class XHttpClient {

    private final static Logger LOGGER = LoggerFactory.getLogger(XHttpClient.class);

    private static RequestConfig requestConfig = null;

    static {

        requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(Config.getInt("httpclient.request.timeout"))
                .setConnectTimeout(Config.getInt("httpclient.connect.timeout"))
                .setSocketTimeout(Config.getInt("httpclient.socket.timeout"))
                .setExpectContinueEnabled(false)
                .build();
    }

    public static String doRequest(HttpUriRequest httpUriRequest)
            throws RequestException {

        String response = null;
        CloseableHttpResponse httpResponse = null;
        HttpRequestBase httpRequestBase = null;
        try {
            httpRequestBase = (HttpRequestBase) httpUriRequest;
            //设置请求参数
            httpRequestBase.setConfig(requestConfig);
            //获得httpclient
            CloseableHttpClient httpClient = HttpConnectionManager.getHttpClient();

            //请求
            httpResponse = httpClient.execute(httpRequestBase);
            if (httpResponse.getStatusLine().getStatusCode() >= 400) {
                LOGGER.warn("http请求状态码为{}，请求地址{}", httpResponse.getStatusLine().getStatusCode(), httpRequestBase.getURI().toString());
                throw new RequestException(ExceptionTypeEnum.HTTP_REQUEST_ERROR);
            }
            HttpEntity httpEntity = httpResponse.getEntity();
            InputStream in = httpEntity.getContent();

            String charset = null;
            Header header = httpResponse.getFirstHeader("Content-Type");
            if (header != null && header.getValue().indexOf("charset=") != -1) {
                charset = header.getValue().split("charset=")[1];
            }

            response = IOUtils.toString(in, charset);
            in.close();
        } catch (Exception e) {
            LOGGER.error("request {} error:", httpUriRequest.getURI(), e);
            throw new RequestException(ExceptionTypeEnum.HTTP_REQUEST_ERROR);
        } finally {
            try {
                if (httpResponse != null)
                    httpResponse.close();
            } catch (IOException e) {
                LOGGER.error("error:", e);
            }
        }
        return response;
    }
}

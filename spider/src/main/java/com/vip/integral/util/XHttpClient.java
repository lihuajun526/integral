package com.vip.integral.util;

import com.vip.integral.constant.ExceptionTypeEnum;
import com.vip.integral.exception.RequestException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by lihuajun on 16-6-21.
 */
public class XHttpClient {

    private final static Logger LOGGER = LoggerFactory.getLogger(XHttpClient.class);

    private static HttpClient httpClient;

    static {
        ThreadSafeClientConnManager threadSafeClientConnManager = new ThreadSafeClientConnManager();
        threadSafeClientConnManager.setMaxTotal(100);
        httpClient = new DefaultHttpClient(threadSafeClientConnManager);
        //设置超时
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 20000);
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 30000);

        // TODO: 16-7-13 重试机制 
    }

    public static String doRequest(HttpUriRequest httpUriRequest) throws RequestException {

        return doRequest(httpUriRequest, "utf-8");
    }

    public static String doRequest(HttpUriRequest httpUriRequest, String charset) throws RequestException {

        String response = null;

        try {
            HttpResponse httpResponse = httpClient.execute(httpUriRequest);
            HttpEntity httpEntity = httpResponse.getEntity();
            response = EntityUtils.toString(httpEntity, charset);
        } catch (IOException e) {
            httpUriRequest.abort();
            LOGGER.error("request {} error:{}", httpUriRequest.getURI(), e);
            throw new RequestException(ExceptionTypeEnum.HTTP_REQUEST_ERROR);
        }

        return response;
    }

    public static void setProxy(String host, int port) {
        HttpHost proxy = new HttpHost(host, port);
        httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
    }

    public static void main(String[] args) {
        HttpGet httpGet = new HttpGet("http://cc.cmbchina.com/");
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            String str = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

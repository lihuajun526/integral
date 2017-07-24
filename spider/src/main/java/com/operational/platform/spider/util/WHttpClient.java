package com.operational.platform.spider.util;

import com.operational.platform.common.bean.ReqSettings;
import com.operational.platform.common.exception.RequestException;
import com.operational.platform.common.util.Config;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Created by lihuajun on 2016/8/26.
 */
public class WHttpClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(WHttpClient.class);

    private static PoolingHttpClientConnectionManager cm = null;
    private static HttpRequestRetryHandler httpRequestRetryHandler = null;
    private static CredentialsProvider credsProvider = null;
    private static RequestConfig requestConfig = null;

    static {
        ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
        LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory.getSocketFactory();
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", plainsf)
                .register("https", sslsf)
                .build();
        cm = new PoolingHttpClientConnectionManager(registry);
        // 最大连接数
        cm.setMaxTotal(Config.getInt("httpclient.pool.max.connection"));
        // 每个路由基础的连接数
        cm.setDefaultMaxPerRoute(Config.getInt("httpclient.pool.max.route"));
    }


    public static String doRequest(HttpRequestBase httpRequestBase) throws RequestException {

        String response = null;
        int statusCode;
        CloseableHttpResponse httpResponse = null;
        try {
            // 设置请求参数
            httpRequestBase.setConfig(requestConfig);
            // 获得httpclient
            CloseableHttpClient httpClient = HttpClients.custom()
                    .setConnectionManager(cm)
                            // .setRetryHandler(httpRequestRetryHandler)
                            // .setRetryHandler(new DefaultHttpRequestRetryHandler())
                    .setDefaultCredentialsProvider(credsProvider)
                    .build();
            httpResponse = httpClient.execute(httpRequestBase);
            statusCode = httpResponse.getStatusLine().getStatusCode();
            LOGGER.debug("返回状态：{}", statusCode);
            if (statusCode != 200) {
                LOGGER.warn("请求{}返回{}", httpRequestBase.getURI().toString(), statusCode);
            } else {
                handleResponse(httpResponse);
                HttpEntity httpEntity = httpResponse.getEntity();
                InputStream in = httpEntity.getContent();
                response = IOUtils.toString(in);
                in.close();
            }
        } catch (Exception e) {
            LOGGER.error("error:", e);
            throw new RequestException();
        } finally {
            try {
                if (httpResponse != null)
                    httpResponse.close();
            } catch (IOException e) {
                LOGGER.error("httpResponse close exception:", e);
            }
        }
        return response;
    }

    /**
     * 处理response
     *
     * @param httpResponse
     * @return
     */
    private static CloseableHttpResponse handleResponse(CloseableHttpResponse httpResponse) {

        Header ceheader = httpResponse.getEntity().getContentEncoding();
        if (ceheader != null) {
            HeaderElement[] codecs = ceheader.getElements();
            for (int i = 0; i < codecs.length; i++) {
                if (codecs[i].getName().equalsIgnoreCase("gzip")) {
                    httpResponse.setEntity(
                            new GzipDecompressingEntity(httpResponse.getEntity()));
                }
            }
        }
        return httpResponse;
    }
}

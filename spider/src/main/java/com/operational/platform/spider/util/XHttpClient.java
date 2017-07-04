package com.operational.platform.spider.util;

import com.operational.platform.spider.exception.RequestException;
import com.operational.platform.spider.constant.ExceptionTypeEnum;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.*;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihuajun on 16-6-21.
 */
public class XHttpClient {

    private final static Logger LOGGER = LoggerFactory.getLogger(XHttpClient.class);

    private static CloseableHttpClient httpClient;

    private static void init(String protocol) throws Exception {
        ThreadSafeClientConnManager threadSafeClientConnManager = new ThreadSafeClientConnManager();
        threadSafeClientConnManager.setMaxTotal(100);
        if ("http".equalsIgnoreCase(protocol)) {
            httpClient = new DefaultHttpClient(threadSafeClientConnManager);
        } else if ("https".equalsIgnoreCase(protocol)) {
            httpClient = new SSLClient(threadSafeClientConnManager);
        }
        //设置超时
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 20000);
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 30000);
    }

    public static String doRequest(HttpUriRequest httpUriRequest) throws RequestException {

        return doRequest(httpUriRequest, "utf-8");
    }

    public static String doRequest(HttpUriRequest httpUriRequest, String charset) throws RequestException {

        String result = null;
        CloseableHttpResponse response = null;
        try {
            //HttpHost proxy = new HttpHost("127.0.0.1", 8888, "http");
            //RequestConfig backstage = RequestConfig.custom().setProxy(proxy).build();
            RequestConfig config = RequestConfig.custom().build();

            if (httpUriRequest instanceof HttpPost) {
                ((HttpPost) httpUriRequest).setConfig(config);
            } else if (httpUriRequest instanceof HttpGet) {
                ((HttpGet) httpUriRequest).setConfig(config);
            }
            //采用绕过验证的方式处理https请求
            SSLContext sslcontext = createIgnoreVerifySSL();
            // 设置协议http和https对应的处理socket链接工厂的对象
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", new SSLConnectionSocketFactory(sslcontext))
                    .build();
            PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            HttpClients.custom().setConnectionManager(connManager);

            ////////////////////////////////
            HttpHost target = new HttpHost("proxy.abuyun.com", 9020, "http");
            CredentialsProvider credsProvider = new BasicCredentialsProvider();
            credsProvider.setCredentials(
                    new AuthScope("proxy.abuyun.com", 9020),
                    new UsernamePasswordCredentials("H448WW3T6G0ODEQD", "C9D3ACC71EC5C04D"));

            //创建自定义的httpclient对象
            httpClient = HttpClients.custom().setConnectionManager(connManager)
                    .setDefaultCredentialsProvider(credsProvider)
                    .build();


            AuthCache authCache = new BasicAuthCache();
            BasicScheme basicAuth = new BasicScheme();
            authCache.put(target, basicAuth);
            HttpClientContext localContext = HttpClientContext.create();
            localContext.setAuthCache(authCache);
            response = httpClient.execute(httpUriRequest, localContext);
            ///////////////////////////////


            //response = httpClient.execute(httpUriRequest);
            //获取结果实体
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                //按指定编码转换结果实体为String类型
                result = EntityUtils.toString(entity, charset);
            }
            //todo 这个有什么用?
            EntityUtils.consume(entity);
        } catch (Exception e) {
            httpUriRequest.abort();
            LOGGER.error("request {} error:{}", httpUriRequest.getURI(), e);
            throw new RequestException(ExceptionTypeEnum.HTTP_REQUEST_ERROR);
        } finally {
            //释放链接
            try {
                if (response != null)
                    response.close();
            } catch (IOException e) {
                LOGGER.error("error:", e);
            }
        }
        return result;
    }

    /**
     * 绕过验证
     *
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    public static SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException {

        SSLContext sc = SSLContext.getInstance("SSLv3");
        // 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };

        sc.init(null, new TrustManager[]{trustManager}, null);
        return sc;
    }

    public static void main(String[] args) {
        try {
            HttpPost httpPost = new HttpPost("https://www.zhihu.com/node/TopicsPlazzaListV2");
            //设置表单参数
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("method", "next"));
            params.add(new BasicNameValuePair("params", "{\"topic_id\":285,\"offset\":20,\"hash_id\":\"f5cb8680c6458f09fc21f3e1e716aa94\"}"));
            httpPost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));

            String response = XHttpClient.doRequest(httpPost);
            //String response = IHttpClient.doRequest(httpPost);
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}

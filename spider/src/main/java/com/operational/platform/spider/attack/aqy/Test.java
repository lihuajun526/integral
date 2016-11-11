package com.operational.platform.spider.attack.aqy;

import com.operational.platform.spider.attack.HttpAsyncClient;
import com.operational.platform.spider.attack.QueryEvent;
import com.operational.platform.spider.util.cookie.HttpCookieEx;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihuajun on 16-7-12.
 */
public class Test {

    public void query() {

        HttpPost httpPost = new HttpPost(
                "http://passport.iqiyi.com/apis/user/info.action?fields=prus%2Cuserinfo%2Cqiyi_vip&authcookie=637f04sFr4A87wtt2YMa4cONnqREruRKxPP7E6PzAbu7ifVtojTVCLEXlTp3Qw2wkE64&callback=window.Q.__callbacks__.cb8dqs73");
        httpPost.setHeader("Accept", "*/*");
        httpPost.setHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.86 Safari/537.36");
        httpPost.setHeader("Referer", "http://www.iqiyi.com/");

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("fields", "prus,userinfo,qiyi_vip"));
        params.add(new BasicNameValuePair("authcookie", "637f04sFr4A87wtt2YMa4cONnqREruRKxPP7E6PzAbu7ifVtojTVCLEXlTp3Qw2wkE64"));
        params.add(new BasicNameValuePair("callback", "window.Q.__callbacks__.cb8dqs73"));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        QueryEvent ccbiShopEvent = new QueryEvent();
        String sCookie1 = "Set-Cookie: Hm_lpvt_53b7374a63c37483e5dd97d78d9bb36e=1468300919 ; path=/";
        String sCookie2 = "Set-Cookie: Hm_lvt_53b7374a63c37483e5dd97d78d9bb36e=1468300919 ; path=/";
        String sCookie3 = "Set-Cookie: P00001=637f04sFr4A87wtt2YMa4cONnqREruRKxPP7E6PzAbu7ifVtojTVCLEXlTp3Qw2wkE64 ; path=/";
        String sCookie4 = "Set-Cookie: P00002=%7B%22uid%22%3A%221266687801%22%2C%22user_name%22%3A%2213738047929%22%2C%22email%22%3A%22%22%2C%22nickname%22%3A%22LeXQ1%22%2C%22pru%22%3A1266687801%2C%22type%22%3A11%2C%22pnickname%22%3A%22LeXQ1%22%7D ; path=/";
        String sCookie5 = "Set-Cookie: P00003=1266687801 ; path=/";
        String sCookie6 = "Set-Cookie: P00004=-632241962.1468300962.4c8213d733 ; path=/";
        String sCookie7 = "Set-Cookie: P00007=637f04sFr4A87wtt2YMa4cONnqREruRKxPP7E6PzAbu7ifVtojTVCLEXlTp3Qw2wkE64 ; path=/";
        String sCookie8 = "Set-Cookie: P00010=1266687801 ; path=/";
        String sCookie9 = "Set-Cookie: P000email=13738047929 ; path=/";
        String sCookie10 = "Set-Cookie: P00PRU=1266687801 ; path=/";
        String sCookie11 = "Set-Cookie: P01010=1468339200 ; path=/";
        String sCookie12 = "Set-Cookie: QC005=2245f1efa49c3b9dcd2e5c02f7a7953d ; path=/";
        String sCookie13 = "Set-Cookie: QC006=106r2w1243mm9gmu9rjozos2 ; path=/";
        String sCookie14 = "Set-Cookie: QC007=https%253A%252F%252Fwww.baidu.com%252Flink%253Furl%253DJ9c2i6gx-L9YzxtcyBj84Ca-omMMpMAVY2X9X51S_ym%2526wd%253D%2526eqid%253D9a9e2c930002caf80000000457847e16 ; path=/";
        String sCookie15 = "Set-Cookie: QC008=1468300918.1468300918.1468300918.1 ; path=/";
        String sCookie16 = "Set-Cookie: QC010=71779920 ; path=/";
        String sCookie17 = "Set-Cookie: QC017=1 ; path=/";
        String sCookie18 = "Set-Cookie: QC116= ; path=/";
        String sCookie19 = "Set-Cookie: T00404=abe59412607d548369b6e056d5db2252 ; path=/";
        ccbiShopEvent.getCookieList().addAll(HttpCookieEx.parse(sCookie1));
        ccbiShopEvent.getCookieList().addAll(HttpCookieEx.parse(sCookie2));
        ccbiShopEvent.getCookieList().addAll(HttpCookieEx.parse(sCookie3));
        ccbiShopEvent.getCookieList().addAll(HttpCookieEx.parse(sCookie4));
        ccbiShopEvent.getCookieList().addAll(HttpCookieEx.parse(sCookie5));
        ccbiShopEvent.getCookieList().addAll(HttpCookieEx.parse(sCookie6));
        ccbiShopEvent.getCookieList().addAll(HttpCookieEx.parse(sCookie7));
        ccbiShopEvent.getCookieList().addAll(HttpCookieEx.parse(sCookie8));
        ccbiShopEvent.getCookieList().addAll(HttpCookieEx.parse(sCookie9));
        ccbiShopEvent.getCookieList().addAll(HttpCookieEx.parse(sCookie10));
        ccbiShopEvent.getCookieList().addAll(HttpCookieEx.parse(sCookie11));
        ccbiShopEvent.getCookieList().addAll(HttpCookieEx.parse(sCookie12));
        ccbiShopEvent.getCookieList().addAll(HttpCookieEx.parse(sCookie13));
        ccbiShopEvent.getCookieList().addAll(HttpCookieEx.parse(sCookie14));
        ccbiShopEvent.getCookieList().addAll(HttpCookieEx.parse(sCookie15));
        ccbiShopEvent.getCookieList().addAll(HttpCookieEx.parse(sCookie16));
        ccbiShopEvent.getCookieList().addAll(HttpCookieEx.parse(sCookie17));
        ccbiShopEvent.getCookieList().addAll(HttpCookieEx.parse(sCookie18));
        ccbiShopEvent.getCookieList().addAll(HttpCookieEx.parse(sCookie19));

        CloseableHttpAsyncClient httpAsyncClient = HttpAsyncClient.getInstance();
        httpAsyncClient.execute(httpPost, new HttpAsyncCallback(ccbiShopEvent));

    }

    public void publish() {

        //String requestUrl = "https://h5.qzone.qq.com/proxy/domain/base.qzone.qq.com/cgi-bin/user/cgi_userinfo_get_all?uin=515182557&vuin=3198493143&fupdate=1&rd=0.4394825559326032&g_tk=294917838";
        String requestUrl = "https://www.baidu.com";

        HttpGet httpGet = new HttpGet(requestUrl);
        httpGet.setHeader("Accept", "*/*");
        httpGet.setHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.86 Safari/537.36");
        //httpGet.setHeader("Referer", "http://ctc.qzs.qq.com/qzone/profile/index.html");

        QueryEvent ccbiShopEvent = new QueryEvent();
        String sCookie1 = "Set-Cookie: pgv_pvi=4122858496; path=/";
        String sCookie2 = "Set-Cookie: RK=2eHvHml2Ty; path=/";
        String sCookie3 = "Set-Cookie: pgv_pvid=5220037454; path=/";
        String sCookie4 = "Set-Cookie: cpu_performance_v8=5; path=/";
        String sCookie5 = "Set-Cookie: __Q_w_s__QZN_TodoMsgCnt=1; path=/";
        String sCookie6 = "Set-Cookie: pt2gguin=o3198493143; path=/";
        String sCookie7 = "Set-Cookie: uin=o3198493143; path=/";
        String sCookie8 = "Set-Cookie: skey=@pYKFBlZYy; path=/";
        String sCookie9 = "Set-Cookie: ptisp=ctc; path=/";
        String sCookie10 = "Set-Cookie: qzone_check=3198493143_1470541464; path=/";
        String sCookie11 = "Set-Cookie: ptcz=74cb5e87e8eca56c823b22c6128198d16ecc659e3bdde0be04a79e72c2e75b23; path=/";
        String sCookie12 = "Set-Cookie: Loading=Yes; path=/";
        String sCookie13 = "Set-Cookie: p_skey=b6szCcOUxGJuqvEvYIa2YNfufPmo4e3AaiwA3D0VAbc_; path=/";
        String sCookie14 = "Set-Cookie: p_uin=o3198493143; path=/";
        String sCookie15 = "Set-Cookie: pt4_token=Nzmrfbg-1p6oh9MAGlPNigZKW19C-dlbbKd9NKMfyZA_; path=/";
        String sCookie16 = "Set-Cookie: pgv_info=ssid=s8440759310; path=/";
        String sCookie17 = "Set-Cookie: QZ_FE_WEBP_SUPPORT=1; path=/";
        String sCookie18 = "Set-Cookie: qzspeedup=sdch path=/";


        ccbiShopEvent.getCookieList().addAll(HttpCookieEx.parse(sCookie1));
        ccbiShopEvent.getCookieList().addAll(HttpCookieEx.parse(sCookie2));
        ccbiShopEvent.getCookieList().addAll(HttpCookieEx.parse(sCookie3));
        ccbiShopEvent.getCookieList().addAll(HttpCookieEx.parse(sCookie4));
        ccbiShopEvent.getCookieList().addAll(HttpCookieEx.parse(sCookie5));
        ccbiShopEvent.getCookieList().addAll(HttpCookieEx.parse(sCookie6));
        ccbiShopEvent.getCookieList().addAll(HttpCookieEx.parse(sCookie7));
        ccbiShopEvent.getCookieList().addAll(HttpCookieEx.parse(sCookie8));
        ccbiShopEvent.getCookieList().addAll(HttpCookieEx.parse(sCookie9));
        ccbiShopEvent.getCookieList().addAll(HttpCookieEx.parse(sCookie10));
        ccbiShopEvent.getCookieList().addAll(HttpCookieEx.parse(sCookie11));
        ccbiShopEvent.getCookieList().addAll(HttpCookieEx.parse(sCookie12));
        ccbiShopEvent.getCookieList().addAll(HttpCookieEx.parse(sCookie13));
        ccbiShopEvent.getCookieList().addAll(HttpCookieEx.parse(sCookie14));
        ccbiShopEvent.getCookieList().addAll(HttpCookieEx.parse(sCookie15));
        ccbiShopEvent.getCookieList().addAll(HttpCookieEx.parse(sCookie16));
        ccbiShopEvent.getCookieList().addAll(HttpCookieEx.parse(sCookie17));
        ccbiShopEvent.getCookieList().addAll(HttpCookieEx.parse(sCookie18));


//        StringEntity entity = new StringEntity("","utf-8");//解决中文乱码问题
//        entity.setContentEncoding("UTF-8");
//        entity.setContentType("application/json");
//        method.setEntity(entity);
//        try {
//            CookieHelper.setCookies2(requestUrl, httpGet, ccbiShopEvent.getCookieList());
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
        CloseableHttpAsyncClient httpAsyncClient = HttpAsyncClient.getInstance();
        httpAsyncClient.execute(httpGet, new HttpAsyncCallback(ccbiShopEvent));

    }

    private class HttpAsyncCallback implements FutureCallback<HttpResponse> {
        private QueryEvent event;
        private boolean skipNextStep = false;

        public HttpAsyncCallback(QueryEvent event) {
            this.event = event;
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }

        @Override
        public void completed(HttpResponse result) {
            try {
                String resultStr = EntityUtils.toString(result.getEntity());
                System.out.println(resultStr);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void failed(Exception ex) {

        }

        @Override
        public void cancelled() {

        }

    }

    public static void main(String[]args){
        Test test = new Test();
        test.publish();
    }


}

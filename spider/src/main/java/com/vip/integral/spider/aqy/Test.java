package com.vip.integral.spider.aqy;

import com.vip.integral.spider.HttpAsyncClient;
import com.vip.integral.spider.QueryEvent;
import com.vip.integral.util.cookie.HttpCookieEx;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
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

        HttpPost httpPost = new HttpPost(
                "http://api.t.iqiyi.com/qx_api/comment/publish");
        httpPost.setHeader("Accept", "*/*");
        httpPost.setHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.86 Safari/537.36");
        httpPost.setHeader("Referer", "http://www.iqiyi.com/dianying/20121213/3225b6b8f5e8df25.html");

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("qitanid", "1145901"));
        params.add(new BasicNameValuePair("tvid", "329552"));
        params.add(new BasicNameValuePair("categoryid", "1"));
        params.add(new BasicNameValuePair("qitan_comment_type", "1"));
        params.add(new BasicNameValuePair("title", "寒战"));
        params.add(new BasicNameValuePair("playurl", "http://www.iqiyi.com/dianying/20121213/3225b6b8f5e8df25.html#vfrm=19-9-0-1"));
        params.add(new BasicNameValuePair("play_order", "1"));
        params.add(new BasicNameValuePair("tv_year", "20121108"));
        params.add(new BasicNameValuePair("sync_src", "寒战"));
        params.add(new BasicNameValuePair("current_url", "http://www.iqiyi.com/dianying/20121213/3225b6b8f5e8df25.html#vfrm=19-9-0-1"));
        params.add(new BasicNameValuePair("text", "ok啦，不错了。。。"));
        params.add(new BasicNameValuePair("nosync", "qzone,renren"));
        params.add(new BasicNameValuePair("picid", ""));
        params.add(new BasicNameValuePair("is_video_page", "true"));
        params.add(new BasicNameValuePair("qypid", "01010011010000000000"));
        params.add(new BasicNameValuePair("albumid", "276601"));
        params.add(new BasicNameValuePair("appid", "21"));
        params.add(new BasicNameValuePair("antiCsrf", "aad1a1e03219134d6626ca3131771470"));


        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        QueryEvent ccbiShopEvent = new QueryEvent();
        String sCookie1 = "Set-Cookie: c241266687801=1468372463819 ; path=/";
        String sCookie2 = "Set-Cookie: Hm_lpvt_53b7374a63c37483e5dd97d78d9bb36e=1468383898 ; path=/";
        String sCookie3 = "Set-Cookie: Hm_lvt_53b7374a63c37483e5dd97d78d9bb36e=1468372386,1468383880 ; path=/";
        String sCookie4 = "Set-Cookie: P00001=63xnTm30CCewVdm19e9CzSDl0MKZ3zflHLHs2PFm10jk6BW6u6E6k6cvk4EQMjGm3XGyA564 ; path=/";
        String sCookie5 = "Set-Cookie: P00002=%7B%22uid%22%3A%221266687801%22%2C%22user_name%22%3A%2213738047929%22%2C%22email%22%3A%22%22%2C%22nickname%22%3A%22LeXQ1%22%2C%22pru%22%3A1266687801%2C%22type%22%3A11%2C%22pnickname%22%3A%22LeXQ1%22%7D ; path=/";
        String sCookie6 = "Set-Cookie: P00003=1266687801 ; path=/";
        String sCookie7 = "Set-Cookie: P00004=-632241962.1468321680.654ff977ef ; path=/";
        String sCookie8 = "Set-Cookie: P00007=63xnTm30CCewVdm19e9CzSDl0MKZ3zflHLHs2PFm10jk6BW6u6E6k6cvk4EQMjGm3XGyA564 ; path=/";
        String sCookie9 = "Set-Cookie: P00010=1266687801 ; path=/";
        String sCookie10 = "Set-Cookie: P000email=13738047929 ; path=/";
        String sCookie11 = "Set-Cookie: P00PRU=1266687801 ; path=/";
        String sCookie12 = "Set-Cookie: P01010=1468425600 ; path=/";
        String sCookie13 = "Set-Cookie: QC001=1 ; path=/";
        String sCookie14 = "Set-Cookie: QC005=9ef7afa12ae99ed778c46bc52abcc278 ; path=/";
        String sCookie15 = "Set-Cookie: QC006=htl2py5qc5k1jk7ftrqdj6il ; path=/";
        String sCookie16 = "Set-Cookie: QC007=DIRECT ; path=/";
        String sCookie17 = "Set-Cookie: QC008=1468321688.1468372385.1468383880.3 ; path=/";
        String sCookie18 = "Set-Cookie: QC010=225354329 ; path=/";
        String sCookie19 = "Set-Cookie: QC025=1266687801-20160713 ; path=/";

        String sCookie20 = "Set-Cookie: QC116= ; path=/";
        String sCookie21 = "Set-Cookie: QC118=%7B%22isFilterImage%22%3A0%2C%22hadTip%22%3A1%2C%22isOpen%22%3A0%7D ; path=/";
        String sCookie22 = "Set-Cookie: QILINPUSH=1 ; path=/";
        String sCookie23 = "Set-Cookie: QIYUECK=qy_pc_7bdd1f0dd8f2458d9b8012d0582c74e5 ; path=/";
        String sCookie24 = "Set-Cookie: QY00001=1266687801 ; path=/";
        String sCookie25 = "Set-Cookie: T00404=d23a6a63905024576bb5afbea9253e3a ; path=/";
        String sCookie26 = "Set-Cookie: T00700=EgcI0b-tIRAD ; path=/";

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
        ccbiShopEvent.getCookieList().addAll(HttpCookieEx.parse(sCookie20));
        ccbiShopEvent.getCookieList().addAll(HttpCookieEx.parse(sCookie21));
        ccbiShopEvent.getCookieList().addAll(HttpCookieEx.parse(sCookie22));
        ccbiShopEvent.getCookieList().addAll(HttpCookieEx.parse(sCookie23));
        ccbiShopEvent.getCookieList().addAll(HttpCookieEx.parse(sCookie24));
        ccbiShopEvent.getCookieList().addAll(HttpCookieEx.parse(sCookie25));
        ccbiShopEvent.getCookieList().addAll(HttpCookieEx.parse(sCookie26));


//        StringEntity entity = new StringEntity("","utf-8");//解决中文乱码问题
//        entity.setContentEncoding("UTF-8");
//        entity.setContentType("application/json");
//        method.setEntity(entity);

        CloseableHttpAsyncClient httpAsyncClient = HttpAsyncClient.getInstance();
        httpAsyncClient.execute(httpPost, new HttpAsyncCallback(ccbiShopEvent));

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

package com.vip.integral.util;

import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihuajun on 16-6-28.
 */
public class StrUtil {

    public static String cleanUrl(String url) {
        if (StringUtils.isEmpty(url))
            return null;
        return url.replaceAll("\r|\n|\t| ", "");
    }

    public static String encode(String str) {

        return str.replaceAll("<", "%3C").replaceAll(">", "%3E")
                .replaceAll("/", "%2F").replaceAll(";", "%3B")
                .replaceAll("\\(", "%28").replaceAll("\\)", "%29")
                .replaceAll(",", "%2C").replaceAll("\"", "%22")
                .replaceAll(" ", "%20")
                ;
    }

    /**
     * 将页面上不完整的链接拼接成完整的链接
     *
     * @param pLink
     * @param linkList
     * @return
     */
    public static List<String> handleLink(String pLink, List<String> linkList) {
        if (linkList == null)
            return null;

        List<String> list = new ArrayList<>();
        for (String link : linkList) {
            list.add(handleLink(pLink, link));
        }
        return list;
    }

    /**
     * 将页面上不完整的链接拼接成完整的链接
     *
     * @param pLink
     * @param link
     * @return
     */
    public static String handleLink(String pLink, String link) {
        String childLink = null;
        String protocol = pLink.indexOf("http://") == 0 ? "http" : pLink.indexOf("https://") == 0 ? "https" : null;
        if (link.contains("http://") || link.contains("https://"))
            childLink = link;
        else if (link.contains("../")) {
            String[] strs = link.replaceFirst(protocol + "://", "").split("\\.\\.\\/");
            String[] pStrs = pLink.replaceFirst(protocol + "://", "").split("\\/");
            StringBuffer sb = new StringBuffer(protocol);
            sb.append("://");
            for (int i = 0; i < pStrs.length - strs.length + 1; i++) {
                sb.append(pStrs[i]).append("/");
            }
            sb.append(strs[strs.length - 1]);
            childLink = sb.toString();
        } else if (link.indexOf("/") == 0) {
            String str = pLink.replaceFirst(protocol + "://", "");
            if ("http".equals(protocol)) {
                childLink = protocol + "://" + str.substring(0, str.indexOf("/")) + link;
            } else if ("https".equals(protocol)) {
                childLink = protocol + "://" + str.substring(0, str.indexOf("/")) + link;
            }
        } else {
            String str = pLink.replace(protocol + "://", "");
            int pos = str.lastIndexOf("/");
            if (pos != -1) {
                childLink = protocol + "://" + str.substring(0, pos + 1) + link;
            } else {
                childLink = protocol + "://" + str + "/" + link;
            }
        }
        return childLink;
    }

    public static void showDif(String str1, String str2, String split) throws UnsupportedEncodingException {

        String[] strsSrc1 = str1.split(split);
        String[] strsSrc2 = str2.split(split);
        String[] strs1 = null;
        String[] strs2 = null;
        Map<String, String> map1 = new HashMap<>();
        Map<String, String> map2 = new HashMap<>();

        if (strsSrc1.length != strsSrc2.length) {
            System.out.println("参数个数不同");
        }

        if (strsSrc1.length >= strsSrc2.length) {
            strs1 = strsSrc1;
            strs2 = strsSrc2;
        } else {
            strs1 = strsSrc2;
            strs2 = strsSrc1;
        }

        for (int i = 0; i < strs1.length; i++) {
            String[] temp = strs1[i].split("=");
            if (temp.length == 1) {
                map1.put(temp[0].trim(), "");
            } else
                map1.put(temp[0].trim(), temp[1].trim());
        }

        for (int i = 0; i < strs2.length; i++) {
            String[] temp = strs2[i].split("=");
            if (temp.length == 1) {
                map2.put(temp[0].trim(), "");
            } else
                map2.put(temp[0].trim(), temp[1].trim());
        }

        for (String key : map1.keySet()) {
            if (map2.get(key) == null) {
                System.out.println("其中一个集合没有[" + key + "]属性");
            }
            if (!map1.get(key).equals(map2.get(key))) {
                System.out.println("map1中[" + key + "]值为：" + URLDecoder.decode(map1.get(key), "utf-8"));
                System.out.println("map2中[" + key + "]值为：" + URLDecoder.decode(map2.get(key), "utf-8"));
                System.out.println("------------------------------------");
            }
        }
    }

    public static void main(String[] args) {

        //评论
        String str1 = "qitanid=13619889&tvid=496696400&categoryid=1&qitan_comment_type=1&title=丧尸屠城2&playurl=http:%2F%2Fwww.iqiyi.com%2Fv_19rrlm5g70.html?fc%3D8b62d5327a54411b%23vfrm%3D19-9-0-1&play_order=1&tv_year=20160721&sync_src=丧尸屠城2&current_url=http:%2F%2Fwww.iqiyi.com%2Fv_19rrlm5g70.html?fc%3D8b62d5327a54411b%23vfrm%3D19-9-0-1&text=搞笑&nosync=weibo,qzone,renren&picid=&is_video_page=true&qypid=01010011010000000000&albumid=496696400&appid=21&antiCsrf=aad1a1e03219134d6626ca3131771470";
        String str2 = "qitanid=1155150&tvid=204213700&categoryid=1&qitan_comment_type=1&title=我的尴尬性之旅&playurl=http:%2F%2Fwww.iqiyi.com%2Fv_19rrhart7s.html?fc%3D8b62d5327a54411b%23vfrm%3D19-9-0-1&play_order=1&tv_year=20130419&sync_src=我的尴尬性之旅&current_url=http:%2F%2Fwww.iqiyi.com%2Fv_19rrhart7s.html?fc%3D8b62d5327a54411b%23vfrm%3D19-9-0-1&text=有所感悟&nosync=weibo,qzone,renren&picid=&is_video_page=true&qypid=01010011010000000000&albumid=204213700&appid=21&antiCsrf=aad1a1e03219134d6626ca3131771470";
        String str3 = "P00004=-632241962.1468321680.654ff977ef; QC005=9ef7afa12ae99ed778c46bc52abcc278; P00001=63xnTm30CCewVdm19e9CzSDl0MKZ3zflHLHs2PFm10jk6BW6u6E6k6cvk4EQMjGm3XGyA564; P00003=1266687801; P00010=1266687801; P01010=1468425600; P00007=63xnTm30CCewVdm19e9CzSDl0MKZ3zflHLHs2PFm10jk6BW6u6E6k6cvk4EQMjGm3XGyA564; P00PRU=1266687801; P00002=%7B%22uid%22%3A%221266687801%22%2C%22user_name%22%3A%2213738047929%22%2C%22email%22%3A%22%22%2C%22nickname%22%3A%22LeXQ1%22%2C%22pru%22%3A1266687801%2C%22type%22%3A11%2C%22pnickname%22%3A%22LeXQ1%22%7D; P000email=13738047929; QC116=; QIYUECK=qy_pc_7bdd1f0dd8f2458d9b8012d0582c74e5; uaid=e4ed34332f5a75ff782477871f4d4399; QC008=1468321688.1468636254.1469236762.7; QC001=1; QC025=1266687801-20160723; T00404=d23a6a63905024576bb5afbea9253e3a; QILINPUSH=1; Hm_lvt_53b7374a63c37483e5dd97d78d9bb36e=1468383880,1468499092,1468559279,1469236762; Hm_lpvt_53b7374a63c37483e5dd97d78d9bb36e=1469236928; QC007=https%253A%252F%252Fwww.baidu.com%252Flink%253Furl%253DNy6kmMRd0qtjWHi4nsF_qnLohZm8yx5ZheuEIOp-hcy%2526wd%253D%2526eqid%253Db8575d1e0011055d000000045792c61e; QC009=8b62d5327a54411b; T00700=EgcI0b-tIRAVEgcIjMDtIRABEgcI9r-tIRAHEgcI1L-tIRABEgcI67-tIRACEgcI6b-tIRABEgcI77-tIRABEgcI87-tIRABEgcI9L-tIRABEgcI4b-tIRABEgcIz7-tIRAC; QC006=htl2py5qc5k1jk7ftrqdj6il; QC010=7538994; QY00001=1266687801; QC118=%7B%22isFilterImage%22%3A0%2C%22hadTip%22%3A1%2C%22isOpen%22%3A0%7D\n";
        String str4 = "P00004=-632241962.1468321680.654ff977ef; QC005=9ef7afa12ae99ed778c46bc52abcc278; P00001=63xnTm30CCewVdm19e9CzSDl0MKZ3zflHLHs2PFm10jk6BW6u6E6k6cvk4EQMjGm3XGyA564; P00003=1266687801; P00010=1266687801; P01010=1468425600; P00007=63xnTm30CCewVdm19e9CzSDl0MKZ3zflHLHs2PFm10jk6BW6u6E6k6cvk4EQMjGm3XGyA564; P00PRU=1266687801; P00002=%7B%22uid%22%3A%221266687801%22%2C%22user_name%22%3A%2213738047929%22%2C%22email%22%3A%22%22%2C%22nickname%22%3A%22LeXQ1%22%2C%22pru%22%3A1266687801%2C%22type%22%3A11%2C%22pnickname%22%3A%22LeXQ1%22%7D; P000email=13738047929; QC116=; QIYUECK=qy_pc_7bdd1f0dd8f2458d9b8012d0582c74e5; uaid=e4ed34332f5a75ff782477871f4d4399; QC008=1468321688.1468636254.1469236762.7; QC001=1; QC025=1266687801-20160723; T00404=d23a6a63905024576bb5afbea9253e3a; Hm_lvt_53b7374a63c37483e5dd97d78d9bb36e=1468383880,1468499092,1468559279,1469236762; Hm_lpvt_53b7374a63c37483e5dd97d78d9bb36e=1469236792; T00700=EgcI0b-tIRAUEgcIjMDtIRABEgcI9r-tIRAHEgcI1L-tIRABEgcI67-tIRACEgcI6b-tIRABEgcI77-tIRABEgcI87-tIRABEgcI9L-tIRABEgcI4b-tIRABEgcIz7-tIRAC; QC007=https%253A%252F%252Fwww.baidu.com%252Flink%253Furl%253DNy6kmMRd0qtjWHi4nsF_qnLohZm8yx5ZheuEIOp-hcy%2526wd%253D%2526eqid%253Db8575d1e0011055d000000045792c61e; QC009=8b62d5327a54411b; QY00001=1266687801; QC006=htl2py5qc5k1jk7ftrqdj6il; QC010=203032721; QILINPUSH=1; QC118=%7B%22isFilterImage%22%3A0%2C%22hadTip%22%3A1%2C%22isOpen%22%3A0%7D\n";
        //点赞
        String str5 = "http://api.t.iqiyi.com/qx_api/comment/like?albumid=327188600&antiCsrf=aad1a1e03219134d6626ca3131771470&cb=fnsucc&contentid=6608403648&is_video_page=true&qitancallback=fnsucc&qitanid=10918839&qypid=01010011010000000000&t=0.3413835194937269&tvid=327188600&uid=1266687801";
        String str6 = "http://api.t.iqiyi.com/qx_api/comment/like?albumid=327188600&antiCsrf=d0c99269427248afa8fed975e92e37b4&cb=fnsucc&contentid=6608403648&is_video_page=true&qitancallback=fnsucc&qitanid=10918839&qypid=01010011010000000000&t=0.6085927824507835&tvid=327188600&uid=85840559";

        String cookie = "P00004=-632241962.1468321680.654ff977ef; QC005=9ef7afa12ae99ed778c46bc52abcc278; P00001=63xnTm30CCewVdm19e9CzSDl0MKZ3zflHLHs2PFm10jk6BW6u6E6k6cvk4EQMjGm3XGyA564; P00003=1266687801; P00010=1266687801; P01010=1468425600; P00007=63xnTm30CCewVdm19e9CzSDl0MKZ3zflHLHs2PFm10jk6BW6u6E6k6cvk4EQMjGm3XGyA564; P00PRU=1266687801; P00002=%7B%22uid%22%3A%221266687801%22%2C%22user_name%22%3A%2213738047929%22%2C%22email%22%3A%22%22%2C%22nickname%22%3A%22LeXQ1%22%2C%22pru%22%3A1266687801%2C%22type%22%3A11%2C%22pnickname%22%3A%22LeXQ1%22%7D; P000email=13738047929; QC116=; QIYUECK=qy_pc_7bdd1f0dd8f2458d9b8012d0582c74e5; uaid=e4ed34332f5a75ff782477871f4d4399; QC008=1468321688.1468636254.1469236762.7; QC001=1; QC025=1266687801-20160723; T00404=d23a6a63905024576bb5afbea9253e3a; Hm_lvt_53b7374a63c37483e5dd97d78d9bb36e=1468383880,1468499092,1468559279,1469236762; QC007=https%253A%252F%252Fwww.baidu.com%252Flink%253Furl%253DNy6kmMRd0qtjWHi4nsF_qnLohZm8yx5ZheuEIOp-hcy%2526wd%253D%2526eqid%253Db8575d1e0011055d000000045792c61e; QC009=8b62d5327a54411b; QY00001=1266687801; QC006=htl2py5qc5k1jk7ftrqdj6il; QILINPUSH=1; QC118=%7B%22isFilterImage%22%3A0%2C%22hadTip%22%3A1%2C%22isOpen%22%3A0%7D\n";

        try {
            showDif(str5, str6, "&");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

}

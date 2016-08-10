package com.vip.integral.util;

import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URL;
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
                System.out.println("集合2没有[" + key + "]属性");
            }
        }

        for (String key : map2.keySet()) {
            if (map1.get(key) == null) {
                System.out.println("集合1没有[" + key + "]属性");
            }
        }

        List<String> keys = new ArrayList<>();
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer diff = new StringBuffer();

        for (String key : map1.keySet()) {
            stringBuffer.append(key).append(",");
            if (map2.get(key) == null) {
                continue;
            }
            if (!map1.get(key).equals(map2.get(key))) {
                System.out.println("map1中[" + key + "]值为：" + URLDecoder.decode(map1.get(key), "utf-8"));
                System.out.println("map2中[" + key + "]值为：" + URLDecoder.decode(map2.get(key), "utf-8"));
                System.out.println("------------------------------------");
                diff.append(key).append(",");
            } else {
                keys.add(key);
            }
        }

        StringBuffer sb = new StringBuffer();
        StringBuffer sb1 = new StringBuffer();
        Boolean isFirst = true;
        for (String key : keys) {
            if (isFirst) {
                sb.append(key).append("=").append(map1.get(key));
                sb1.append(key);
                isFirst = false;
                continue;
            }
            sb.append(split).append(key).append("=").append(map1.get(key));
            sb1.append(",").append(key);
        }
        System.out.println(sb.toString());
        System.out.println("相同key：" + sb1.toString());
        System.out.println("不同key：" + diff.toString());
        System.out.println("所有key：" + stringBuffer.toString());
    }

    //设置表单
    private static void setForm(String str, String decode) throws UnsupportedEncodingException {
        String[] params = str.split("&");
        for (int i = 0; i < params.length; i++) {
            String[] kv = params[i].split("=");
            if (kv.length == 1) {
                System.out.println("params.add(new BasicNameValuePair(\"" + kv[0] + "\", \"\"));");
            } else {
                System.out.println("params.add(new BasicNameValuePair(\"" + kv[0] + "\", \"" + URLDecoder.decode(kv[1], decode) + "\"));");
            }
        }
    }

    public static void main(String[] args) {

        //评论
        /*String str1 = "aid=10630589&albumid=344891800&categoryid=1&cb=fnsucc&escape=true&is_video_page=true&need_reply=true&need_subject=true&need_total=1&page=1&page_size=10&page_size_reply=3&qitan_comment_type=1&qitancallback=fnsucc&qitanid=10630589&qypid=01010011010000000000&reply_sort=hot&sort=hot&t=0.373654650586821&tvid=344891800";
        String str2 = "aid=10630589&albumid=344891800&categoryid=1&cb=fnsucc&escape=true&is_video_page=true&need_reply=true&need_subject=true&need_total=1&page=1&page_size=10&page_size_reply=3&qitan_comment_type=1&qitancallback=fnsucc&qitanid=10630589&qypid=01010011010000000000&reply_sort=hot&sort=add_time&t=0.3868190594192775&tvid=344891800";

        try {
            showDif(str1, str2, "&");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/

        //设置表单
        String str = "qzreferrer=http%3A%2F%2Fctc.qzs.qq.com%2Fqzone%2Fapp%2Fmood_v6%2Fhtml%2Findex.html%23mood%26uin%3D516809046%26pfid%3D2%26qz_ver%3D8%26appcanvas%3D0%26qz_style%3D2%26params%3D%26entertime%3D1470584105720%26canvastype%3D&uin=515182557&hostUin=516809046&topicId=516809046_56e1cd1e3b5730566bd70e00&commentUin=515182557&content=%3F&richval=&richtype=&inCharset=&outCharset=&ref=&private=0&with_fwd=0&to_tweet=0&hostuin=515182557&code_version=1&format=fs";
        try {
            setForm(str,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}

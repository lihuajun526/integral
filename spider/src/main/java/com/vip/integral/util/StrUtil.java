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
        String str1 = "albumid=496447100&antiCsrf=805092735ddfe431fba7700cbe938a51&cb=fnsucc&contentid=6369697948&is_video_page=true&qitancallback=fnsucc&qitanid=12488029&qypid=01010011010000000000&t=0.7257644792499902&tvid=496447100&uid=1266687801";
        String str2 = "albumid=503325200&antiCsrf=0a6c572e0b6d101791a4ddd549857d3c&cb=fnsucc&contentid=6285529048&is_video_page=true&qitancallback=fnsucc&qitanid=11075642&qypid=01010011010000000000&t=0.8853676982141423&tvid=503325200&uid=85840559";

        try {
            showDif(str1, str2, "&");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

}

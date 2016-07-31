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

    public static void main(String[] args) {

        //评论
        String str1 = "qitanid=1153643&tvid=475451100&categoryid=1&qitan_comment_type=1&title=那年·同学之不作不死不青春&playurl=http:%2F%2Fwww.iqiyi.com%2Fv_19rrloht4g.html%23vfrm%3D2-4-0-1&play_order=1&tv_year=20160520&sync_src=那年·同学之不作不死不青春&current_url=http:%2F%2Fwww.iqiyi.com%2Fv_19rrloht4g.html%23vfrm%3D2-4-0-1&text=青春啊青春&nosync=&picid=&is_video_page=true&qypid=01010011010000000000&albumid=475451100&appid=21&antiCsrf=e9d30acbe98b349b35cb4ca8b3911162";
        String str2 = "qitanid=13622088&tvid=501250500&categoryid=1&qitan_comment_type=1&title=再见那年&playurl=http:%2F%2Fwww.iqiyi.com%2Fv_19rrlimch4.html%23vfrm%3D3-2-zebra-1&play_order=1&tv_year=20160713&sync_src=再见那年&current_url=http:%2F%2Fwww.iqiyi.com%2Fv_19rrlimch4.html%23vfrm%3D3-2-zebra-1&text=青春啊青春&nosync=weibo,qzone,renren&picid=&is_video_page=true&qypid=01010011010000000000&albumid=501250500&appid=21&antiCsrf=e9d30acbe98b349b35cb4ca8b3911162";

        try {
            showDif(str1, str2, "&");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

}

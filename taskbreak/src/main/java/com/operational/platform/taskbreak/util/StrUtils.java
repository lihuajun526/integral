package com.operational.platform.taskbreak.util;

/**
 * @author: Zhou Xuanang
 * @Date: 11:05 2016/11/3.
 */
public class StrUtils {
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
}

package com.vip.integral.util.cookie;

import java.util.ArrayList;
import java.util.List;

public class FilterCookies {

    public static List<HttpCookieEx> filter(String str) {

        String[] strs = str.split(";");
        List<HttpCookieEx> cookieList = new ArrayList<>();
        for (String cookie : strs) {
            cookie = "Set-Cookie: " + cookie + " ; path=/";
            cookieList.addAll(HttpCookieEx.parse(cookie));
        }
        return cookieList;
    }

}

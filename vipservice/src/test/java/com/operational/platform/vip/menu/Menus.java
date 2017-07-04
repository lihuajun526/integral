package com.operational.platform.vip.menu;

import com.alibaba.fastjson.JSON;
import com.operational.platform.vip.util.Config;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihuajun on 2016/9/14.
 */
public class Menus {

    private List<Menu> button;


    public List<Menu> getButton() {
        return button;
    }

    public void setButton(List<Menu> button) {
        this.button = button;
    }

    public class Menu {
        private String type;
        private String name;
        private String url;
        private String key;
        private List<Menu> sub_button;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public List<Menu> getSub_button() {
            return sub_button;
        }

        public void setSub_button(List<Menu> sub_button) {
            this.sub_button = sub_button;
        }
    }

    public String create() throws UnsupportedEncodingException {

        Menus menus = new Menus();

        Menu menu1 = new Menus.Menu();
        menu1.setType("view");
        menu1.setName("黑眼圈365");
        String redirectUri = Config.get("app.domain") + "/goods/list";
        menu1.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + Config.get("wechat.appid") + "&redirect_uri=" + URLEncoder.encode(redirectUri, "utf-8") + "&response_type=code&scope=snsapi_base&state=#wechat_redirect");

        Menu menu2 = new Menus.Menu();
        menu2.setType("click");
        menu2.setName("会员账号");
        menu2.setKey("getVip");

        Menu menu3 = new Menus.Menu();
        menu3.setName("会员");
        Menu menu3_1 = new Menus.Menu();
        menu3_1.setType("view");
        menu3_1.setName("投诉");
        redirectUri = Config.get("app.domain") + "/index/suggest?type=2";
        menu3_1.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + Config.get("wechat.appid") + "&redirect_uri=" + URLEncoder.encode(redirectUri, "utf-8") + "&response_type=code&scope=snsapi_base&state=#wechat_redirect");
        Menu menu3_2 = new Menus.Menu();
        menu3_2.setType("view");
        menu3_2.setName("建议");
        redirectUri = Config.get("app.domain") + "/index/suggest?type=1";
        menu3_2.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + Config.get("wechat.appid") + "&redirect_uri=" + URLEncoder.encode(redirectUri, "utf-8") + "&response_type=code&scope=snsapi_base&state=#wechat_redirect");
        menu3.setSub_button(new ArrayList<>());
        menu3.getSub_button().add(menu3_1);
        menu3.getSub_button().add(menu3_2);

        menus.setButton(new ArrayList<>());
        menus.getButton().add(menu1);
        menus.getButton().add(menu2);
        menus.getButton().add(menu3);

        return JSON.toJSONString(menus);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {

        Menus menus = new Menus();
        System.out.println(menus.create());

    }

}

package com.operational.platform.spider.attack.qzone.task;

import com.alibaba.fastjson.JSON;
import com.operational.platform.spider.exception.NotLoginException;
import com.operational.platform.spider.util.cookie.CookieHelper;
import com.operational.platform.spider.constant.Belong;
import com.operational.platform.spider.attack.qzone.bean.AttackAttr;
import com.operational.platform.spider.attack.qzone.bean.Attr;
import com.operational.platform.spider.attack.qzone.bean.Result;
import com.operational.platform.spider.bean.CrawlPointAttr;
import com.operational.platform.spider.bean.SpringContext;
import com.operational.platform.spider.exception.UnknowException;
import com.operational.platform.dbservice.model.AttackPage;
import com.operational.platform.dbservice.model.CrawlPoint;
import com.operational.platform.dbservice.service.AttackPageService;
import com.operational.platform.dbservice.service.CrawlPointService;
import com.operational.platform.spider.util.XHttpClient;
import com.operational.platform.spider.util.cookie.FilterCookies;
import com.operational.platform.spider.util.cookie.HttpCookieEx;
import org.apache.http.client.methods.HttpGet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class CrawlTask implements Runnable {

    private final static Logger LOGGER = LoggerFactory.getLogger(CrawlTask.class);
    private CrawlPointAttr crawlPointAttr;

    public CrawlTask(CrawlPointAttr crawlPointAttr) {
        this.crawlPointAttr = crawlPointAttr;
    }

    @Override
    public void run() {
        //初始化
        String requestUrlTpl = "https://h5.qzone.qq.com/proxy/domain/base.qzone.qq.com/cgi-bin/user/cgi_userinfo_get_all?uin=%s&vuin=%s&fupdate=1&rd=0.4394825559326032&g_tk=%s";
        Attr attr = JSON.parseObject(crawlPointAttr.getAttr(), Attr.class);
        Long boundary = attr.getBoundary();//边界值
        String account = attr.getAccount();//攻击者账号
        List<HttpCookieEx> cookieList = new ArrayList<>();
        cookieList.addAll(FilterCookies.filter(crawlPointAttr.getCookies()));
        Document document = null;
        HttpGet httpGet = new HttpGet();
        httpGet.setHeader("Accept", "*/*");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36");
        httpGet.setHeader("Referer", "http://ctc.qzs.qq.com/qzone/profile/index.html");
        for (int i = 0; i < crawlPointAttr.getMaxPage() && attr.getCurrent() < boundary; i++) {
            try {
                //用户状态：1正常,2空间设置了权限,3空间未开通,-1未知
                int status = -1;
                String requestUrl = String.format(requestUrlTpl, String.valueOf(attr.getCurrent()), account, attr.getGtk());
                httpGet.setURI(new URI(requestUrl));
                httpGet.setHeader("Host", "h5.qzone.qq.com");
                CookieHelper.setCookies2(requestUrl, httpGet, cookieList);
                //攻击
                Thread.sleep(2500);
                String response = XHttpClient.doRequest(httpGet);
                response = response.replace("_Callback(", "").replace(");", "");
                Result result = JSON.parseObject(response, Result.class);
                //获取用户基本信息
                if ("获取成功".equals(result.getMessage())) {//空间可以访问
                    status = 1;
                    LOGGER.info("获取用户信息成功[{}]", result.getData().getUin());
                } else if ("您无权访问".equals(result.getMessage())) {//空间不可以访问
                    LOGGER.info("空间不可以访问[{}]", result.getMessage());
                    httpGet.setURI(new URI("http://user.qzone.qq.com/" + attr.getCurrent()));
                    httpGet.setHeader("Host", "user.qzone.qq.com");
                    response = XHttpClient.doRequest(httpGet);
                    document = Jsoup.parse(response);
                    Elements elements = document.select("div.page_main>div.main_content>p.tips");
                    if (elements == null || elements.size() == 0) {
                        elements = document.select("div.page_main>div.error_content>p:eq(1)");
                        if (elements == null || elements.size() == 0) {
                            LOGGER.warn("未知情况的用户[{}]", attr.getCurrent());
                        } else if (elements.get(0).text().contains("未开通空间")) {
                            status = 3;
                            LOGGER.info("未开通空间的用户[{}]", attr.getCurrent());
                        } else {
                            LOGGER.warn("未知情况的用户[{},{}]", attr.getCurrent(), elements.get(0).text());
                        }
                    } else if (elements.get(0).text().contains("主人设置了权限")) {
                        status = 2;
                        LOGGER.info("设置了权限的用户[{}]", attr.getCurrent());
                    } else {
                        LOGGER.warn("未知情况的用户[{},{}]", attr.getCurrent(), elements.get(0).text());
                    }
                } else if ("请先登录".equals(result.getMessage())) {
                    LOGGER.error("未登录");
                    throw new NotLoginException();
                } else {
                    LOGGER.error("未知错误[{}]", result.getMessage());
                    throw new UnknowException();
                }
                //保存到DB
                AttackAttr attackAttr = new AttackAttr();
                attackAttr.setStatus(status);
                attackAttr.setUserInfo(result.getData());
                AttackPage attackPage = new AttackPage();
                attackPage.setAttr(JSON.toJSONString(attackAttr));
                attackPage.setTitle(String.valueOf(attr.getCurrent()));
                attackPage.setBelong(Belong.QZONE.value());
                attackPage.setCount(0);
                attackPage.setLink("http://user.qzone.qq.com/" + attr.getCurrent());
                this.saveAttackPage(attackPage);

            } catch (Exception e) {
                LOGGER.error("获取用户信息失败:", e);
                /*if (e instanceof UnknowException || e instanceof NotLoginException || e instanceof RequestException) {
                    break;
                }*/
                break;
            }
            attr.setCurrent(attr.getCurrent() + 1);
        }
        //更新下次爬取时的开始点
        LOGGER.info("更新下次爬取时的开始点为:{}", attr.getCurrent());
        crawlPointAttr.setAttr(JSON.toJSONString(attr));
        this.updateNextStart(crawlPointAttr);
    }

    /**
     * 更新下次爬取的起始点
     *
     * @param crawlPointAttr
     */
    private void updateNextStart(CrawlPointAttr crawlPointAttr) {
        CrawlPointService crawlPointService = (CrawlPointService) SpringContext.getContext().getBean("crawlPointService");
        CrawlPoint crawlPoint = new CrawlPoint();
        crawlPoint.setAttr(crawlPointAttr.getAttr());
        crawlPoint.setId(crawlPointAttr.getId());
        crawlPointService.update(crawlPoint);
    }

    private void saveAttackPage(AttackPage attackPage) {
        AttackPageService attackPageService = (AttackPageService) SpringContext.getContext().getBean("attackPageService");
        attackPageService.save(attackPage);
    }

    public static void main(String[] args) {
        String str = "3198491070";
        Long i = Long.valueOf(str);
        System.out.println(i);
    }

}

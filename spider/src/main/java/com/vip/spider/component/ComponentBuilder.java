package com.vip.spider.component;

import com.vip.spider.bean.CrawlPointAttr;
import com.vip.spider.bean.SpringContext;
import com.vip.spider.component.loader.PageLoader;
import com.vip.spider.component.loader.PageIndexLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * 组件生成器
 *
 * @author lihuajun
 */
public class ComponentBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComponentBuilder.class);

    private CrawlPointAttr crawlPointAttr;

    public ComponentBuilder(CrawlPointAttr crawlPointAttr) {
        this.crawlPointAttr = crawlPointAttr;
    }

    /**
     * 创建列表解析器
     *
     * @return
     */
    public ListParser buildListParser() {

        ListParser listParser = (ListParser) SpringContext.getContext().getBean("listParser");
        listParser.init(crawlPointAttr);
        return listParser;
    }

    /**
     * 创建分页加载器
     *
     * @return
     */
    public PageIndexLoader buildPageIndexLoader() throws ClassNotFoundException, IllegalAccessException,
            InstantiationException, UnsupportedEncodingException {
        PageIndexLoader pageIndexLoader = (PageIndexLoader) Class.forName(crawlPointAttr.getPageIndexClassPath()).newInstance();
        pageIndexLoader.init(crawlPointAttr);
        return pageIndexLoader;
    }

    /**
     * 创建页面加载器
     *
     * @return
     */
    public PageLoader buildPageLoader() {
        PageLoader pageLoader = new PageLoader();
        pageLoader.init(crawlPointAttr);
        return pageLoader;
    }
}

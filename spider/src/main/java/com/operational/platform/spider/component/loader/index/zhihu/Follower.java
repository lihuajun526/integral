package com.operational.platform.spider.component.loader.index.zhihu;

import com.operational.platform.spider.bean.SpringContext;
import com.operational.platform.spider.component.loader.PageIndexLoader;
import com.operational.platform.spider.constant.ExceptionTypeEnum;
import com.operational.platform.spider.exception.ElementNotExistException;
import com.operational.platform.dbservice.model.CrawlPoint;
import com.operational.platform.dbservice.service.CrawlPointService;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihuajun on 2016/10/28.
 * 知乎话题关注者
 */
public class Follower extends PageIndexLoader {

    private CrawlPointService crawlPointService;

    public Follower() {
        ClassPathXmlApplicationContext context = SpringContext.getContext();
        crawlPointService = (CrawlPointService) context.getBean("crawlPointService");
    }

    @Override
    public void updatePageCount(String response) throws ElementNotExistException {
        try {
            Document doc = Jsoup.parse(response);

            Elements elements = doc.select("div.zm-person-item");

            if (elements != null && elements.size() > 0) {
                Element element = elements.get(elements.size() - 1);
                String id = element.attr("id");
                id = id.replace("mi-", "");

                pageCount++;

                HttpPost httpPost = (HttpPost) httpRequestBase;
                List<NameValuePair> params = new ArrayList<>();

                CrawlPoint crawlPoint = crawlPointService.selectByPrimaryKey(crawlPointAttr.getId());
                String postParam = crawlPoint.getPostParam();
                String[] postParams = postParam.split("&");
                Integer offset = Integer.parseInt(postParams[0].split("=")[1]) + 20;

                params.add(new BasicNameValuePair("offset", String.valueOf(offset)));
                params.add(new BasicNameValuePair("start", id));
                httpPost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));

                //更新采集点的postParam属性，方便任务下次继续执行
                crawlPoint.setPostParam("offset=" + offset + "&start=" + id);
                crawlPointService.update(crawlPoint);

                httpRequestBase = httpPost;
            }
        } catch (Exception e) {
            LOGGER.error("更新页数错误[采集点id={},category={}]:", crawlPointAttr.getId(), crawlPointAttr.getCategory(), e);
            throw new ElementNotExistException(ExceptionTypeEnum.ELEMENT_NOT_EXIST_ERROR.code,
                    "无法获取[知乎->" + crawlPointAttr.getCategory() + "]分页导航");
        }
    }
}

package com.vip.spider.component.loader.index.zhihu;

import com.vip.spider.component.loader.PageIndexLoader;
import com.vip.spider.constant.ExceptionTypeEnum;
import com.vip.spider.exception.ElementNotExistException;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihuajun on 2016/10/28.
 * 知乎话题关注者
 */
public class Follower extends PageIndexLoader {
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

                HttpPost httpPost = (HttpPost) httpUriRequest;
                List<NameValuePair> params = new ArrayList<>();
                params.add(new BasicNameValuePair("offset", String.valueOf(20 * pageCount)));
                params.add(new BasicNameValuePair("start", id));
                httpPost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));

                httpUriRequest = httpPost;
            }
        } catch (Exception e) {
            LOGGER.error("更新页数错误[采集点id={},category={}]:", crawlPointAttr.getId(), crawlPointAttr.getCategory(), e);
            throw new ElementNotExistException(ExceptionTypeEnum.ELEMENT_NOT_EXIST_ERROR.code,
                    "无法获取[知乎->" + crawlPointAttr.getCategory() + "]分页导航");
        }
    }
}

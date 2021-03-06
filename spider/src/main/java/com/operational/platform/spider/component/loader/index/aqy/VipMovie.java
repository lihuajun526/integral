package com.operational.platform.spider.component.loader.index.aqy;

import com.alibaba.fastjson.JSONObject;
import com.operational.platform.spider.component.loader.PageIndexLoader;
import com.operational.platform.spider.constant.ExceptionTypeEnum;
import com.operational.platform.spider.exception.ElementNotExistException;

/**
 * VIP电影
 * Created by lihuajun on 16-7-29.
 */
public class VipMovie extends PageIndexLoader {

    @Override
    public void updatePageCount(String response) throws ElementNotExistException {
        JSONObject result = null;
        Integer code = 0;
        try {
            result = JSONObject.parseObject(response);

            if(result.getString("data").contains("search result is empty"))
                return;

            code = result.getJSONObject("data").getInteger("code");
            if (code == 0) {
                pageCount++;
            }
        } catch (Exception e) {
            LOGGER.error("更新页数错误[采集点id={},category={}]:", crawlPointAttr.getId(), crawlPointAttr.getCategory(), e);
            throw new ElementNotExistException(ExceptionTypeEnum.ELEMENT_NOT_EXIST_ERROR.code,
                    "无法获取[爱奇艺]分页导航");
        }
    }
}

package com.operational.platform.spider.component.post.builder;

import org.apache.http.NameValuePair;

import java.util.List;

/**
 * Created by lihuajun on 2017/9/8.
 */
public interface PostBuilder {

    void setParams(List<NameValuePair> params);

}

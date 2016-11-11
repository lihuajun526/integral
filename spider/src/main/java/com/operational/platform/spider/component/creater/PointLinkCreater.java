package com.operational.platform.spider.component.creater;

import java.util.List;
import java.util.Map;

/**
 * Created by lihuajun on 16-7-19.
 */
public interface PointLinkCreater {

    List<Map<String,String>> get(String channel);

}

package com.operational.platform.taskbreak.rule;

import java.util.ArrayList;
import java.util.List;

import com.operational.platform.taskbreak.entity.DataSource;
import com.operational.platform.taskbreak.entity.SpiderAction;

/**
 * @author: Zhou Xuanang
 * @Date: 10:53 2016/11/1.
 */
public class Rules {

    public static List<DataSource> dataSourceList;

    public static void init(List<DataSource> dataSourceList) {
        Rules.dataSourceList = dataSourceList;
    }

    public static List<DataSource> get(SpiderAction action) {

        List<DataSource> list = new ArrayList<>();
        if (action.getId() > 0) {// 跟据id爬取某个采集点的数据
            for (DataSource dataSource : dataSourceList) {
                if (dataSource.getParentId() == action.getId()) {
                    list.add(dataSource);
                    break;
                }
            }
            return list;
            // } else if (!StringUtils.isEmpty(action.getBankCode())) {// 跟据bankcode爬取某个银行下的所有采集点
            // for (SourceSpider sourceSpider : sourceSpiderList) {
            // if (sourceSpider.getBankCode().equalsIgnoreCase(action.getBankCode())) {
            // list.add(sourceSpider);
            // }
            // }
            // return list;
        } else
            // 爬取所有采集点
            return dataSourceList;
    }

}

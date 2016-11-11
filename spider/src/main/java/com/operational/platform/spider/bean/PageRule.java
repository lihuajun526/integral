package com.operational.platform.spider.bean;

import java.util.List;

/**
 * Created by lihuajun on 16-7-4.
 */
/*
*
{
    "attrs":[
        {
          "key":"content",
          "jsoup":"xxx",
          "attr" :"xxx",
          "regex":"xxx",
          "script":""
        },
        {...}
    ],
    "nexts":[
        {
          "jsoup":"xxx",
          "attr" :"xxx",
          "regex":"xxx",
          "script":"",
          "child":{...}
        },
        {...}
    ]
}
*
* */
public class PageRule {

    //当前页的属性规则
    private List<PageRuleKV> attrs;
    //下一层的规则
    private List<PageRuleKV> nexts;

    public List<PageRuleKV> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<PageRuleKV> attrs) {
        this.attrs = attrs;
    }

    public List<PageRuleKV> getNexts() {
        return nexts;
    }

    public void setNexts(List<PageRuleKV> nexts) {
        this.nexts = nexts;
    }

}


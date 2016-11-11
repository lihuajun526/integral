package com.vip.taskbreak.entity;

import java.util.List;

/**
 * @author: Zhou Xuanang
 * @Date: 10:59 2016/11/1.
 */
public class DataSource {
    private String taskId;// 任务id
    private Integer parentId;
    private SourceRoot sourceRoot;
    private List<BreakRule> breakRuleList;
    private String pageIndexRule;
    private String countRuleJson;

    public DataSource(Integer parentId, SourceRoot sourceRoot, List<BreakRule> breakRuleList, String countRuleJson,
            String pageIndexRule) {
        this.parentId = parentId;
        this.sourceRoot = sourceRoot;
        this.breakRuleList = breakRuleList;
        this.pageIndexRule = pageIndexRule;
        this.countRuleJson = countRuleJson;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {

        this.taskId = taskId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {

        this.parentId = parentId;
    }

    public SourceRoot getSourceRoot() {

        return sourceRoot;
    }

    public void setSourceRoot(SourceRoot sourceRoot) {

        this.sourceRoot = sourceRoot;
    }

    public List<BreakRule> getBreakRuleList() {

        return breakRuleList;
    }

    public void setBreakRuleList(List<BreakRule> breakRuleList) {

        this.breakRuleList = breakRuleList;
    }

    public String getPageIndexRule() {
        return pageIndexRule;
    }

    public void setPageIndexRule(String pageIndexRule) {

        this.pageIndexRule = pageIndexRule;
    }

    public String getCountRuleJson() {
        return countRuleJson;
    }

    public void setCountRuleJson(String countRuleJson) {

        this.countRuleJson = countRuleJson;
    }
}

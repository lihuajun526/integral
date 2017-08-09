package com.operational.platform.dbservice.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CrawlPointExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CrawlPointExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNodeidIsNull() {
            addCriterion("nodeid is null");
            return (Criteria) this;
        }

        public Criteria andNodeidIsNotNull() {
            addCriterion("nodeid is not null");
            return (Criteria) this;
        }

        public Criteria andNodeidEqualTo(Integer value) {
            addCriterion("nodeid =", value, "nodeid");
            return (Criteria) this;
        }

        public Criteria andNodeidNotEqualTo(Integer value) {
            addCriterion("nodeid <>", value, "nodeid");
            return (Criteria) this;
        }

        public Criteria andNodeidGreaterThan(Integer value) {
            addCriterion("nodeid >", value, "nodeid");
            return (Criteria) this;
        }

        public Criteria andNodeidGreaterThanOrEqualTo(Integer value) {
            addCriterion("nodeid >=", value, "nodeid");
            return (Criteria) this;
        }

        public Criteria andNodeidLessThan(Integer value) {
            addCriterion("nodeid <", value, "nodeid");
            return (Criteria) this;
        }

        public Criteria andNodeidLessThanOrEqualTo(Integer value) {
            addCriterion("nodeid <=", value, "nodeid");
            return (Criteria) this;
        }

        public Criteria andNodeidIn(List<Integer> values) {
            addCriterion("nodeid in", values, "nodeid");
            return (Criteria) this;
        }

        public Criteria andNodeidNotIn(List<Integer> values) {
            addCriterion("nodeid not in", values, "nodeid");
            return (Criteria) this;
        }

        public Criteria andNodeidBetween(Integer value1, Integer value2) {
            addCriterion("nodeid between", value1, value2, "nodeid");
            return (Criteria) this;
        }

        public Criteria andNodeidNotBetween(Integer value1, Integer value2) {
            addCriterion("nodeid not between", value1, value2, "nodeid");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNull() {
            addCriterion("category is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNotNull() {
            addCriterion("category is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryEqualTo(String value) {
            addCriterion("category =", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotEqualTo(String value) {
            addCriterion("category <>", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThan(String value) {
            addCriterion("category >", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("category >=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThan(String value) {
            addCriterion("category <", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThanOrEqualTo(String value) {
            addCriterion("category <=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLike(String value) {
            addCriterion("category like", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotLike(String value) {
            addCriterion("category not like", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryIn(List<String> values) {
            addCriterion("category in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotIn(List<String> values) {
            addCriterion("category not in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryBetween(String value1, String value2) {
            addCriterion("category between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotBetween(String value1, String value2) {
            addCriterion("category not between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andUrlIsNull() {
            addCriterion("url is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("url is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("url =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("url <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("url >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("url >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("url <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("url <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("url like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("url not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("url in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("url not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("url between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("url not between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlCrClasspathIsNull() {
            addCriterion("url_cr_classpath is null");
            return (Criteria) this;
        }

        public Criteria andUrlCrClasspathIsNotNull() {
            addCriterion("url_cr_classpath is not null");
            return (Criteria) this;
        }

        public Criteria andUrlCrClasspathEqualTo(String value) {
            addCriterion("url_cr_classpath =", value, "urlCrClasspath");
            return (Criteria) this;
        }

        public Criteria andUrlCrClasspathNotEqualTo(String value) {
            addCriterion("url_cr_classpath <>", value, "urlCrClasspath");
            return (Criteria) this;
        }

        public Criteria andUrlCrClasspathGreaterThan(String value) {
            addCriterion("url_cr_classpath >", value, "urlCrClasspath");
            return (Criteria) this;
        }

        public Criteria andUrlCrClasspathGreaterThanOrEqualTo(String value) {
            addCriterion("url_cr_classpath >=", value, "urlCrClasspath");
            return (Criteria) this;
        }

        public Criteria andUrlCrClasspathLessThan(String value) {
            addCriterion("url_cr_classpath <", value, "urlCrClasspath");
            return (Criteria) this;
        }

        public Criteria andUrlCrClasspathLessThanOrEqualTo(String value) {
            addCriterion("url_cr_classpath <=", value, "urlCrClasspath");
            return (Criteria) this;
        }

        public Criteria andUrlCrClasspathLike(String value) {
            addCriterion("url_cr_classpath like", value, "urlCrClasspath");
            return (Criteria) this;
        }

        public Criteria andUrlCrClasspathNotLike(String value) {
            addCriterion("url_cr_classpath not like", value, "urlCrClasspath");
            return (Criteria) this;
        }

        public Criteria andUrlCrClasspathIn(List<String> values) {
            addCriterion("url_cr_classpath in", values, "urlCrClasspath");
            return (Criteria) this;
        }

        public Criteria andUrlCrClasspathNotIn(List<String> values) {
            addCriterion("url_cr_classpath not in", values, "urlCrClasspath");
            return (Criteria) this;
        }

        public Criteria andUrlCrClasspathBetween(String value1, String value2) {
            addCriterion("url_cr_classpath between", value1, value2, "urlCrClasspath");
            return (Criteria) this;
        }

        public Criteria andUrlCrClasspathNotBetween(String value1, String value2) {
            addCriterion("url_cr_classpath not between", value1, value2, "urlCrClasspath");
            return (Criteria) this;
        }

        public Criteria andIsCrawlDetailIsNull() {
            addCriterion("is_crawl_detail is null");
            return (Criteria) this;
        }

        public Criteria andIsCrawlDetailIsNotNull() {
            addCriterion("is_crawl_detail is not null");
            return (Criteria) this;
        }

        public Criteria andIsCrawlDetailEqualTo(Integer value) {
            addCriterion("is_crawl_detail =", value, "isCrawlDetail");
            return (Criteria) this;
        }

        public Criteria andIsCrawlDetailNotEqualTo(Integer value) {
            addCriterion("is_crawl_detail <>", value, "isCrawlDetail");
            return (Criteria) this;
        }

        public Criteria andIsCrawlDetailGreaterThan(Integer value) {
            addCriterion("is_crawl_detail >", value, "isCrawlDetail");
            return (Criteria) this;
        }

        public Criteria andIsCrawlDetailGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_crawl_detail >=", value, "isCrawlDetail");
            return (Criteria) this;
        }

        public Criteria andIsCrawlDetailLessThan(Integer value) {
            addCriterion("is_crawl_detail <", value, "isCrawlDetail");
            return (Criteria) this;
        }

        public Criteria andIsCrawlDetailLessThanOrEqualTo(Integer value) {
            addCriterion("is_crawl_detail <=", value, "isCrawlDetail");
            return (Criteria) this;
        }

        public Criteria andIsCrawlDetailIn(List<Integer> values) {
            addCriterion("is_crawl_detail in", values, "isCrawlDetail");
            return (Criteria) this;
        }

        public Criteria andIsCrawlDetailNotIn(List<Integer> values) {
            addCriterion("is_crawl_detail not in", values, "isCrawlDetail");
            return (Criteria) this;
        }

        public Criteria andIsCrawlDetailBetween(Integer value1, Integer value2) {
            addCriterion("is_crawl_detail between", value1, value2, "isCrawlDetail");
            return (Criteria) this;
        }

        public Criteria andIsCrawlDetailNotBetween(Integer value1, Integer value2) {
            addCriterion("is_crawl_detail not between", value1, value2, "isCrawlDetail");
            return (Criteria) this;
        }

        public Criteria andJsonAnalyzePathIsNull() {
            addCriterion("json_analyze_path is null");
            return (Criteria) this;
        }

        public Criteria andJsonAnalyzePathIsNotNull() {
            addCriterion("json_analyze_path is not null");
            return (Criteria) this;
        }

        public Criteria andJsonAnalyzePathEqualTo(String value) {
            addCriterion("json_analyze_path =", value, "jsonAnalyzePath");
            return (Criteria) this;
        }

        public Criteria andJsonAnalyzePathNotEqualTo(String value) {
            addCriterion("json_analyze_path <>", value, "jsonAnalyzePath");
            return (Criteria) this;
        }

        public Criteria andJsonAnalyzePathGreaterThan(String value) {
            addCriterion("json_analyze_path >", value, "jsonAnalyzePath");
            return (Criteria) this;
        }

        public Criteria andJsonAnalyzePathGreaterThanOrEqualTo(String value) {
            addCriterion("json_analyze_path >=", value, "jsonAnalyzePath");
            return (Criteria) this;
        }

        public Criteria andJsonAnalyzePathLessThan(String value) {
            addCriterion("json_analyze_path <", value, "jsonAnalyzePath");
            return (Criteria) this;
        }

        public Criteria andJsonAnalyzePathLessThanOrEqualTo(String value) {
            addCriterion("json_analyze_path <=", value, "jsonAnalyzePath");
            return (Criteria) this;
        }

        public Criteria andJsonAnalyzePathLike(String value) {
            addCriterion("json_analyze_path like", value, "jsonAnalyzePath");
            return (Criteria) this;
        }

        public Criteria andJsonAnalyzePathNotLike(String value) {
            addCriterion("json_analyze_path not like", value, "jsonAnalyzePath");
            return (Criteria) this;
        }

        public Criteria andJsonAnalyzePathIn(List<String> values) {
            addCriterion("json_analyze_path in", values, "jsonAnalyzePath");
            return (Criteria) this;
        }

        public Criteria andJsonAnalyzePathNotIn(List<String> values) {
            addCriterion("json_analyze_path not in", values, "jsonAnalyzePath");
            return (Criteria) this;
        }

        public Criteria andJsonAnalyzePathBetween(String value1, String value2) {
            addCriterion("json_analyze_path between", value1, value2, "jsonAnalyzePath");
            return (Criteria) this;
        }

        public Criteria andJsonAnalyzePathNotBetween(String value1, String value2) {
            addCriterion("json_analyze_path not between", value1, value2, "jsonAnalyzePath");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andBelongIsNull() {
            addCriterion("belong is null");
            return (Criteria) this;
        }

        public Criteria andBelongIsNotNull() {
            addCriterion("belong is not null");
            return (Criteria) this;
        }

        public Criteria andBelongEqualTo(String value) {
            addCriterion("belong =", value, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongNotEqualTo(String value) {
            addCriterion("belong <>", value, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongGreaterThan(String value) {
            addCriterion("belong >", value, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongGreaterThanOrEqualTo(String value) {
            addCriterion("belong >=", value, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongLessThan(String value) {
            addCriterion("belong <", value, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongLessThanOrEqualTo(String value) {
            addCriterion("belong <=", value, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongLike(String value) {
            addCriterion("belong like", value, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongNotLike(String value) {
            addCriterion("belong not like", value, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongIn(List<String> values) {
            addCriterion("belong in", values, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongNotIn(List<String> values) {
            addCriterion("belong not in", values, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongBetween(String value1, String value2) {
            addCriterion("belong between", value1, value2, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongNotBetween(String value1, String value2) {
            addCriterion("belong not between", value1, value2, "belong");
            return (Criteria) this;
        }

        public Criteria andMaxPageIsNull() {
            addCriterion("max_page is null");
            return (Criteria) this;
        }

        public Criteria andMaxPageIsNotNull() {
            addCriterion("max_page is not null");
            return (Criteria) this;
        }

        public Criteria andMaxPageEqualTo(Integer value) {
            addCriterion("max_page =", value, "maxPage");
            return (Criteria) this;
        }

        public Criteria andMaxPageNotEqualTo(Integer value) {
            addCriterion("max_page <>", value, "maxPage");
            return (Criteria) this;
        }

        public Criteria andMaxPageGreaterThan(Integer value) {
            addCriterion("max_page >", value, "maxPage");
            return (Criteria) this;
        }

        public Criteria andMaxPageGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_page >=", value, "maxPage");
            return (Criteria) this;
        }

        public Criteria andMaxPageLessThan(Integer value) {
            addCriterion("max_page <", value, "maxPage");
            return (Criteria) this;
        }

        public Criteria andMaxPageLessThanOrEqualTo(Integer value) {
            addCriterion("max_page <=", value, "maxPage");
            return (Criteria) this;
        }

        public Criteria andMaxPageIn(List<Integer> values) {
            addCriterion("max_page in", values, "maxPage");
            return (Criteria) this;
        }

        public Criteria andMaxPageNotIn(List<Integer> values) {
            addCriterion("max_page not in", values, "maxPage");
            return (Criteria) this;
        }

        public Criteria andMaxPageBetween(Integer value1, Integer value2) {
            addCriterion("max_page between", value1, value2, "maxPage");
            return (Criteria) this;
        }

        public Criteria andMaxPageNotBetween(Integer value1, Integer value2) {
            addCriterion("max_page not between", value1, value2, "maxPage");
            return (Criteria) this;
        }

        public Criteria andSleepTimeIsNull() {
            addCriterion("sleep_time is null");
            return (Criteria) this;
        }

        public Criteria andSleepTimeIsNotNull() {
            addCriterion("sleep_time is not null");
            return (Criteria) this;
        }

        public Criteria andSleepTimeEqualTo(Long value) {
            addCriterion("sleep_time =", value, "sleepTime");
            return (Criteria) this;
        }

        public Criteria andSleepTimeNotEqualTo(Long value) {
            addCriterion("sleep_time <>", value, "sleepTime");
            return (Criteria) this;
        }

        public Criteria andSleepTimeGreaterThan(Long value) {
            addCriterion("sleep_time >", value, "sleepTime");
            return (Criteria) this;
        }

        public Criteria andSleepTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("sleep_time >=", value, "sleepTime");
            return (Criteria) this;
        }

        public Criteria andSleepTimeLessThan(Long value) {
            addCriterion("sleep_time <", value, "sleepTime");
            return (Criteria) this;
        }

        public Criteria andSleepTimeLessThanOrEqualTo(Long value) {
            addCriterion("sleep_time <=", value, "sleepTime");
            return (Criteria) this;
        }

        public Criteria andSleepTimeIn(List<Long> values) {
            addCriterion("sleep_time in", values, "sleepTime");
            return (Criteria) this;
        }

        public Criteria andSleepTimeNotIn(List<Long> values) {
            addCriterion("sleep_time not in", values, "sleepTime");
            return (Criteria) this;
        }

        public Criteria andSleepTimeBetween(Long value1, Long value2) {
            addCriterion("sleep_time between", value1, value2, "sleepTime");
            return (Criteria) this;
        }

        public Criteria andSleepTimeNotBetween(Long value1, Long value2) {
            addCriterion("sleep_time not between", value1, value2, "sleepTime");
            return (Criteria) this;
        }

        public Criteria andMethodIsNull() {
            addCriterion("method is null");
            return (Criteria) this;
        }

        public Criteria andMethodIsNotNull() {
            addCriterion("method is not null");
            return (Criteria) this;
        }

        public Criteria andMethodEqualTo(String value) {
            addCriterion("method =", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodNotEqualTo(String value) {
            addCriterion("method <>", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodGreaterThan(String value) {
            addCriterion("method >", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodGreaterThanOrEqualTo(String value) {
            addCriterion("method >=", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodLessThan(String value) {
            addCriterion("method <", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodLessThanOrEqualTo(String value) {
            addCriterion("method <=", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodLike(String value) {
            addCriterion("method like", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodNotLike(String value) {
            addCriterion("method not like", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodIn(List<String> values) {
            addCriterion("method in", values, "method");
            return (Criteria) this;
        }

        public Criteria andMethodNotIn(List<String> values) {
            addCriterion("method not in", values, "method");
            return (Criteria) this;
        }

        public Criteria andMethodBetween(String value1, String value2) {
            addCriterion("method between", value1, value2, "method");
            return (Criteria) this;
        }

        public Criteria andMethodNotBetween(String value1, String value2) {
            addCriterion("method not between", value1, value2, "method");
            return (Criteria) this;
        }

        public Criteria andPostParamIsNull() {
            addCriterion("post_param is null");
            return (Criteria) this;
        }

        public Criteria andPostParamIsNotNull() {
            addCriterion("post_param is not null");
            return (Criteria) this;
        }

        public Criteria andPostParamEqualTo(String value) {
            addCriterion("post_param =", value, "postParam");
            return (Criteria) this;
        }

        public Criteria andPostParamNotEqualTo(String value) {
            addCriterion("post_param <>", value, "postParam");
            return (Criteria) this;
        }

        public Criteria andPostParamGreaterThan(String value) {
            addCriterion("post_param >", value, "postParam");
            return (Criteria) this;
        }

        public Criteria andPostParamGreaterThanOrEqualTo(String value) {
            addCriterion("post_param >=", value, "postParam");
            return (Criteria) this;
        }

        public Criteria andPostParamLessThan(String value) {
            addCriterion("post_param <", value, "postParam");
            return (Criteria) this;
        }

        public Criteria andPostParamLessThanOrEqualTo(String value) {
            addCriterion("post_param <=", value, "postParam");
            return (Criteria) this;
        }

        public Criteria andPostParamLike(String value) {
            addCriterion("post_param like", value, "postParam");
            return (Criteria) this;
        }

        public Criteria andPostParamNotLike(String value) {
            addCriterion("post_param not like", value, "postParam");
            return (Criteria) this;
        }

        public Criteria andPostParamIn(List<String> values) {
            addCriterion("post_param in", values, "postParam");
            return (Criteria) this;
        }

        public Criteria andPostParamNotIn(List<String> values) {
            addCriterion("post_param not in", values, "postParam");
            return (Criteria) this;
        }

        public Criteria andPostParamBetween(String value1, String value2) {
            addCriterion("post_param between", value1, value2, "postParam");
            return (Criteria) this;
        }

        public Criteria andPostParamNotBetween(String value1, String value2) {
            addCriterion("post_param not between", value1, value2, "postParam");
            return (Criteria) this;
        }

        public Criteria andHeaderIsNull() {
            addCriterion("header is null");
            return (Criteria) this;
        }

        public Criteria andHeaderIsNotNull() {
            addCriterion("header is not null");
            return (Criteria) this;
        }

        public Criteria andHeaderEqualTo(String value) {
            addCriterion("header =", value, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderNotEqualTo(String value) {
            addCriterion("header <>", value, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderGreaterThan(String value) {
            addCriterion("header >", value, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderGreaterThanOrEqualTo(String value) {
            addCriterion("header >=", value, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderLessThan(String value) {
            addCriterion("header <", value, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderLessThanOrEqualTo(String value) {
            addCriterion("header <=", value, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderLike(String value) {
            addCriterion("header like", value, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderNotLike(String value) {
            addCriterion("header not like", value, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderIn(List<String> values) {
            addCriterion("header in", values, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderNotIn(List<String> values) {
            addCriterion("header not in", values, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderBetween(String value1, String value2) {
            addCriterion("header between", value1, value2, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderNotBetween(String value1, String value2) {
            addCriterion("header not between", value1, value2, "header");
            return (Criteria) this;
        }

        public Criteria andCookiesIsNull() {
            addCriterion("cookies is null");
            return (Criteria) this;
        }

        public Criteria andCookiesIsNotNull() {
            addCriterion("cookies is not null");
            return (Criteria) this;
        }

        public Criteria andCookiesEqualTo(String value) {
            addCriterion("cookies =", value, "cookies");
            return (Criteria) this;
        }

        public Criteria andCookiesNotEqualTo(String value) {
            addCriterion("cookies <>", value, "cookies");
            return (Criteria) this;
        }

        public Criteria andCookiesGreaterThan(String value) {
            addCriterion("cookies >", value, "cookies");
            return (Criteria) this;
        }

        public Criteria andCookiesGreaterThanOrEqualTo(String value) {
            addCriterion("cookies >=", value, "cookies");
            return (Criteria) this;
        }

        public Criteria andCookiesLessThan(String value) {
            addCriterion("cookies <", value, "cookies");
            return (Criteria) this;
        }

        public Criteria andCookiesLessThanOrEqualTo(String value) {
            addCriterion("cookies <=", value, "cookies");
            return (Criteria) this;
        }

        public Criteria andCookiesLike(String value) {
            addCriterion("cookies like", value, "cookies");
            return (Criteria) this;
        }

        public Criteria andCookiesNotLike(String value) {
            addCriterion("cookies not like", value, "cookies");
            return (Criteria) this;
        }

        public Criteria andCookiesIn(List<String> values) {
            addCriterion("cookies in", values, "cookies");
            return (Criteria) this;
        }

        public Criteria andCookiesNotIn(List<String> values) {
            addCriterion("cookies not in", values, "cookies");
            return (Criteria) this;
        }

        public Criteria andCookiesBetween(String value1, String value2) {
            addCriterion("cookies between", value1, value2, "cookies");
            return (Criteria) this;
        }

        public Criteria andCookiesNotBetween(String value1, String value2) {
            addCriterion("cookies not between", value1, value2, "cookies");
            return (Criteria) this;
        }

        public Criteria andRefererIsNull() {
            addCriterion("referer is null");
            return (Criteria) this;
        }

        public Criteria andRefererIsNotNull() {
            addCriterion("referer is not null");
            return (Criteria) this;
        }

        public Criteria andRefererEqualTo(String value) {
            addCriterion("referer =", value, "referer");
            return (Criteria) this;
        }

        public Criteria andRefererNotEqualTo(String value) {
            addCriterion("referer <>", value, "referer");
            return (Criteria) this;
        }

        public Criteria andRefererGreaterThan(String value) {
            addCriterion("referer >", value, "referer");
            return (Criteria) this;
        }

        public Criteria andRefererGreaterThanOrEqualTo(String value) {
            addCriterion("referer >=", value, "referer");
            return (Criteria) this;
        }

        public Criteria andRefererLessThan(String value) {
            addCriterion("referer <", value, "referer");
            return (Criteria) this;
        }

        public Criteria andRefererLessThanOrEqualTo(String value) {
            addCriterion("referer <=", value, "referer");
            return (Criteria) this;
        }

        public Criteria andRefererLike(String value) {
            addCriterion("referer like", value, "referer");
            return (Criteria) this;
        }

        public Criteria andRefererNotLike(String value) {
            addCriterion("referer not like", value, "referer");
            return (Criteria) this;
        }

        public Criteria andRefererIn(List<String> values) {
            addCriterion("referer in", values, "referer");
            return (Criteria) this;
        }

        public Criteria andRefererNotIn(List<String> values) {
            addCriterion("referer not in", values, "referer");
            return (Criteria) this;
        }

        public Criteria andRefererBetween(String value1, String value2) {
            addCriterion("referer between", value1, value2, "referer");
            return (Criteria) this;
        }

        public Criteria andRefererNotBetween(String value1, String value2) {
            addCriterion("referer not between", value1, value2, "referer");
            return (Criteria) this;
        }

        public Criteria andAcceptIsNull() {
            addCriterion("accept is null");
            return (Criteria) this;
        }

        public Criteria andAcceptIsNotNull() {
            addCriterion("accept is not null");
            return (Criteria) this;
        }

        public Criteria andAcceptEqualTo(String value) {
            addCriterion("accept =", value, "accept");
            return (Criteria) this;
        }

        public Criteria andAcceptNotEqualTo(String value) {
            addCriterion("accept <>", value, "accept");
            return (Criteria) this;
        }

        public Criteria andAcceptGreaterThan(String value) {
            addCriterion("accept >", value, "accept");
            return (Criteria) this;
        }

        public Criteria andAcceptGreaterThanOrEqualTo(String value) {
            addCriterion("accept >=", value, "accept");
            return (Criteria) this;
        }

        public Criteria andAcceptLessThan(String value) {
            addCriterion("accept <", value, "accept");
            return (Criteria) this;
        }

        public Criteria andAcceptLessThanOrEqualTo(String value) {
            addCriterion("accept <=", value, "accept");
            return (Criteria) this;
        }

        public Criteria andAcceptLike(String value) {
            addCriterion("accept like", value, "accept");
            return (Criteria) this;
        }

        public Criteria andAcceptNotLike(String value) {
            addCriterion("accept not like", value, "accept");
            return (Criteria) this;
        }

        public Criteria andAcceptIn(List<String> values) {
            addCriterion("accept in", values, "accept");
            return (Criteria) this;
        }

        public Criteria andAcceptNotIn(List<String> values) {
            addCriterion("accept not in", values, "accept");
            return (Criteria) this;
        }

        public Criteria andAcceptBetween(String value1, String value2) {
            addCriterion("accept between", value1, value2, "accept");
            return (Criteria) this;
        }

        public Criteria andAcceptNotBetween(String value1, String value2) {
            addCriterion("accept not between", value1, value2, "accept");
            return (Criteria) this;
        }

        public Criteria andResponseEncodeIsNull() {
            addCriterion("response_encode is null");
            return (Criteria) this;
        }

        public Criteria andResponseEncodeIsNotNull() {
            addCriterion("response_encode is not null");
            return (Criteria) this;
        }

        public Criteria andResponseEncodeEqualTo(String value) {
            addCriterion("response_encode =", value, "responseEncode");
            return (Criteria) this;
        }

        public Criteria andResponseEncodeNotEqualTo(String value) {
            addCriterion("response_encode <>", value, "responseEncode");
            return (Criteria) this;
        }

        public Criteria andResponseEncodeGreaterThan(String value) {
            addCriterion("response_encode >", value, "responseEncode");
            return (Criteria) this;
        }

        public Criteria andResponseEncodeGreaterThanOrEqualTo(String value) {
            addCriterion("response_encode >=", value, "responseEncode");
            return (Criteria) this;
        }

        public Criteria andResponseEncodeLessThan(String value) {
            addCriterion("response_encode <", value, "responseEncode");
            return (Criteria) this;
        }

        public Criteria andResponseEncodeLessThanOrEqualTo(String value) {
            addCriterion("response_encode <=", value, "responseEncode");
            return (Criteria) this;
        }

        public Criteria andResponseEncodeLike(String value) {
            addCriterion("response_encode like", value, "responseEncode");
            return (Criteria) this;
        }

        public Criteria andResponseEncodeNotLike(String value) {
            addCriterion("response_encode not like", value, "responseEncode");
            return (Criteria) this;
        }

        public Criteria andResponseEncodeIn(List<String> values) {
            addCriterion("response_encode in", values, "responseEncode");
            return (Criteria) this;
        }

        public Criteria andResponseEncodeNotIn(List<String> values) {
            addCriterion("response_encode not in", values, "responseEncode");
            return (Criteria) this;
        }

        public Criteria andResponseEncodeBetween(String value1, String value2) {
            addCriterion("response_encode between", value1, value2, "responseEncode");
            return (Criteria) this;
        }

        public Criteria andResponseEncodeNotBetween(String value1, String value2) {
            addCriterion("response_encode not between", value1, value2, "responseEncode");
            return (Criteria) this;
        }

        public Criteria andResponseTypeIsNull() {
            addCriterion("response_type is null");
            return (Criteria) this;
        }

        public Criteria andResponseTypeIsNotNull() {
            addCriterion("response_type is not null");
            return (Criteria) this;
        }

        public Criteria andResponseTypeEqualTo(String value) {
            addCriterion("response_type =", value, "responseType");
            return (Criteria) this;
        }

        public Criteria andResponseTypeNotEqualTo(String value) {
            addCriterion("response_type <>", value, "responseType");
            return (Criteria) this;
        }

        public Criteria andResponseTypeGreaterThan(String value) {
            addCriterion("response_type >", value, "responseType");
            return (Criteria) this;
        }

        public Criteria andResponseTypeGreaterThanOrEqualTo(String value) {
            addCriterion("response_type >=", value, "responseType");
            return (Criteria) this;
        }

        public Criteria andResponseTypeLessThan(String value) {
            addCriterion("response_type <", value, "responseType");
            return (Criteria) this;
        }

        public Criteria andResponseTypeLessThanOrEqualTo(String value) {
            addCriterion("response_type <=", value, "responseType");
            return (Criteria) this;
        }

        public Criteria andResponseTypeLike(String value) {
            addCriterion("response_type like", value, "responseType");
            return (Criteria) this;
        }

        public Criteria andResponseTypeNotLike(String value) {
            addCriterion("response_type not like", value, "responseType");
            return (Criteria) this;
        }

        public Criteria andResponseTypeIn(List<String> values) {
            addCriterion("response_type in", values, "responseType");
            return (Criteria) this;
        }

        public Criteria andResponseTypeNotIn(List<String> values) {
            addCriterion("response_type not in", values, "responseType");
            return (Criteria) this;
        }

        public Criteria andResponseTypeBetween(String value1, String value2) {
            addCriterion("response_type between", value1, value2, "responseType");
            return (Criteria) this;
        }

        public Criteria andResponseTypeNotBetween(String value1, String value2) {
            addCriterion("response_type not between", value1, value2, "responseType");
            return (Criteria) this;
        }

        public Criteria andResponseHandlerIsNull() {
            addCriterion("response_handler is null");
            return (Criteria) this;
        }

        public Criteria andResponseHandlerIsNotNull() {
            addCriterion("response_handler is not null");
            return (Criteria) this;
        }

        public Criteria andResponseHandlerEqualTo(String value) {
            addCriterion("response_handler =", value, "responseHandler");
            return (Criteria) this;
        }

        public Criteria andResponseHandlerNotEqualTo(String value) {
            addCriterion("response_handler <>", value, "responseHandler");
            return (Criteria) this;
        }

        public Criteria andResponseHandlerGreaterThan(String value) {
            addCriterion("response_handler >", value, "responseHandler");
            return (Criteria) this;
        }

        public Criteria andResponseHandlerGreaterThanOrEqualTo(String value) {
            addCriterion("response_handler >=", value, "responseHandler");
            return (Criteria) this;
        }

        public Criteria andResponseHandlerLessThan(String value) {
            addCriterion("response_handler <", value, "responseHandler");
            return (Criteria) this;
        }

        public Criteria andResponseHandlerLessThanOrEqualTo(String value) {
            addCriterion("response_handler <=", value, "responseHandler");
            return (Criteria) this;
        }

        public Criteria andResponseHandlerLike(String value) {
            addCriterion("response_handler like", value, "responseHandler");
            return (Criteria) this;
        }

        public Criteria andResponseHandlerNotLike(String value) {
            addCriterion("response_handler not like", value, "responseHandler");
            return (Criteria) this;
        }

        public Criteria andResponseHandlerIn(List<String> values) {
            addCriterion("response_handler in", values, "responseHandler");
            return (Criteria) this;
        }

        public Criteria andResponseHandlerNotIn(List<String> values) {
            addCriterion("response_handler not in", values, "responseHandler");
            return (Criteria) this;
        }

        public Criteria andResponseHandlerBetween(String value1, String value2) {
            addCriterion("response_handler between", value1, value2, "responseHandler");
            return (Criteria) this;
        }

        public Criteria andResponseHandlerNotBetween(String value1, String value2) {
            addCriterion("response_handler not between", value1, value2, "responseHandler");
            return (Criteria) this;
        }

        public Criteria andListRecordRuleIsNull() {
            addCriterion("list_record_rule is null");
            return (Criteria) this;
        }

        public Criteria andListRecordRuleIsNotNull() {
            addCriterion("list_record_rule is not null");
            return (Criteria) this;
        }

        public Criteria andListRecordRuleEqualTo(String value) {
            addCriterion("list_record_rule =", value, "listRecordRule");
            return (Criteria) this;
        }

        public Criteria andListRecordRuleNotEqualTo(String value) {
            addCriterion("list_record_rule <>", value, "listRecordRule");
            return (Criteria) this;
        }

        public Criteria andListRecordRuleGreaterThan(String value) {
            addCriterion("list_record_rule >", value, "listRecordRule");
            return (Criteria) this;
        }

        public Criteria andListRecordRuleGreaterThanOrEqualTo(String value) {
            addCriterion("list_record_rule >=", value, "listRecordRule");
            return (Criteria) this;
        }

        public Criteria andListRecordRuleLessThan(String value) {
            addCriterion("list_record_rule <", value, "listRecordRule");
            return (Criteria) this;
        }

        public Criteria andListRecordRuleLessThanOrEqualTo(String value) {
            addCriterion("list_record_rule <=", value, "listRecordRule");
            return (Criteria) this;
        }

        public Criteria andListRecordRuleLike(String value) {
            addCriterion("list_record_rule like", value, "listRecordRule");
            return (Criteria) this;
        }

        public Criteria andListRecordRuleNotLike(String value) {
            addCriterion("list_record_rule not like", value, "listRecordRule");
            return (Criteria) this;
        }

        public Criteria andListRecordRuleIn(List<String> values) {
            addCriterion("list_record_rule in", values, "listRecordRule");
            return (Criteria) this;
        }

        public Criteria andListRecordRuleNotIn(List<String> values) {
            addCriterion("list_record_rule not in", values, "listRecordRule");
            return (Criteria) this;
        }

        public Criteria andListRecordRuleBetween(String value1, String value2) {
            addCriterion("list_record_rule between", value1, value2, "listRecordRule");
            return (Criteria) this;
        }

        public Criteria andListRecordRuleNotBetween(String value1, String value2) {
            addCriterion("list_record_rule not between", value1, value2, "listRecordRule");
            return (Criteria) this;
        }

        public Criteria andListAttrRuleIsNull() {
            addCriterion("list_attr_rule is null");
            return (Criteria) this;
        }

        public Criteria andListAttrRuleIsNotNull() {
            addCriterion("list_attr_rule is not null");
            return (Criteria) this;
        }

        public Criteria andListAttrRuleEqualTo(String value) {
            addCriterion("list_attr_rule =", value, "listAttrRule");
            return (Criteria) this;
        }

        public Criteria andListAttrRuleNotEqualTo(String value) {
            addCriterion("list_attr_rule <>", value, "listAttrRule");
            return (Criteria) this;
        }

        public Criteria andListAttrRuleGreaterThan(String value) {
            addCriterion("list_attr_rule >", value, "listAttrRule");
            return (Criteria) this;
        }

        public Criteria andListAttrRuleGreaterThanOrEqualTo(String value) {
            addCriterion("list_attr_rule >=", value, "listAttrRule");
            return (Criteria) this;
        }

        public Criteria andListAttrRuleLessThan(String value) {
            addCriterion("list_attr_rule <", value, "listAttrRule");
            return (Criteria) this;
        }

        public Criteria andListAttrRuleLessThanOrEqualTo(String value) {
            addCriterion("list_attr_rule <=", value, "listAttrRule");
            return (Criteria) this;
        }

        public Criteria andListAttrRuleLike(String value) {
            addCriterion("list_attr_rule like", value, "listAttrRule");
            return (Criteria) this;
        }

        public Criteria andListAttrRuleNotLike(String value) {
            addCriterion("list_attr_rule not like", value, "listAttrRule");
            return (Criteria) this;
        }

        public Criteria andListAttrRuleIn(List<String> values) {
            addCriterion("list_attr_rule in", values, "listAttrRule");
            return (Criteria) this;
        }

        public Criteria andListAttrRuleNotIn(List<String> values) {
            addCriterion("list_attr_rule not in", values, "listAttrRule");
            return (Criteria) this;
        }

        public Criteria andListAttrRuleBetween(String value1, String value2) {
            addCriterion("list_attr_rule between", value1, value2, "listAttrRule");
            return (Criteria) this;
        }

        public Criteria andListAttrRuleNotBetween(String value1, String value2) {
            addCriterion("list_attr_rule not between", value1, value2, "listAttrRule");
            return (Criteria) this;
        }

        public Criteria andLinkRuleIsNull() {
            addCriterion("link_rule is null");
            return (Criteria) this;
        }

        public Criteria andLinkRuleIsNotNull() {
            addCriterion("link_rule is not null");
            return (Criteria) this;
        }

        public Criteria andLinkRuleEqualTo(String value) {
            addCriterion("link_rule =", value, "linkRule");
            return (Criteria) this;
        }

        public Criteria andLinkRuleNotEqualTo(String value) {
            addCriterion("link_rule <>", value, "linkRule");
            return (Criteria) this;
        }

        public Criteria andLinkRuleGreaterThan(String value) {
            addCriterion("link_rule >", value, "linkRule");
            return (Criteria) this;
        }

        public Criteria andLinkRuleGreaterThanOrEqualTo(String value) {
            addCriterion("link_rule >=", value, "linkRule");
            return (Criteria) this;
        }

        public Criteria andLinkRuleLessThan(String value) {
            addCriterion("link_rule <", value, "linkRule");
            return (Criteria) this;
        }

        public Criteria andLinkRuleLessThanOrEqualTo(String value) {
            addCriterion("link_rule <=", value, "linkRule");
            return (Criteria) this;
        }

        public Criteria andLinkRuleLike(String value) {
            addCriterion("link_rule like", value, "linkRule");
            return (Criteria) this;
        }

        public Criteria andLinkRuleNotLike(String value) {
            addCriterion("link_rule not like", value, "linkRule");
            return (Criteria) this;
        }

        public Criteria andLinkRuleIn(List<String> values) {
            addCriterion("link_rule in", values, "linkRule");
            return (Criteria) this;
        }

        public Criteria andLinkRuleNotIn(List<String> values) {
            addCriterion("link_rule not in", values, "linkRule");
            return (Criteria) this;
        }

        public Criteria andLinkRuleBetween(String value1, String value2) {
            addCriterion("link_rule between", value1, value2, "linkRule");
            return (Criteria) this;
        }

        public Criteria andLinkRuleNotBetween(String value1, String value2) {
            addCriterion("link_rule not between", value1, value2, "linkRule");
            return (Criteria) this;
        }

        public Criteria andLinkSelfRuleIsNull() {
            addCriterion("link_self_rule is null");
            return (Criteria) this;
        }

        public Criteria andLinkSelfRuleIsNotNull() {
            addCriterion("link_self_rule is not null");
            return (Criteria) this;
        }

        public Criteria andLinkSelfRuleEqualTo(String value) {
            addCriterion("link_self_rule =", value, "linkSelfRule");
            return (Criteria) this;
        }

        public Criteria andLinkSelfRuleNotEqualTo(String value) {
            addCriterion("link_self_rule <>", value, "linkSelfRule");
            return (Criteria) this;
        }

        public Criteria andLinkSelfRuleGreaterThan(String value) {
            addCriterion("link_self_rule >", value, "linkSelfRule");
            return (Criteria) this;
        }

        public Criteria andLinkSelfRuleGreaterThanOrEqualTo(String value) {
            addCriterion("link_self_rule >=", value, "linkSelfRule");
            return (Criteria) this;
        }

        public Criteria andLinkSelfRuleLessThan(String value) {
            addCriterion("link_self_rule <", value, "linkSelfRule");
            return (Criteria) this;
        }

        public Criteria andLinkSelfRuleLessThanOrEqualTo(String value) {
            addCriterion("link_self_rule <=", value, "linkSelfRule");
            return (Criteria) this;
        }

        public Criteria andLinkSelfRuleLike(String value) {
            addCriterion("link_self_rule like", value, "linkSelfRule");
            return (Criteria) this;
        }

        public Criteria andLinkSelfRuleNotLike(String value) {
            addCriterion("link_self_rule not like", value, "linkSelfRule");
            return (Criteria) this;
        }

        public Criteria andLinkSelfRuleIn(List<String> values) {
            addCriterion("link_self_rule in", values, "linkSelfRule");
            return (Criteria) this;
        }

        public Criteria andLinkSelfRuleNotIn(List<String> values) {
            addCriterion("link_self_rule not in", values, "linkSelfRule");
            return (Criteria) this;
        }

        public Criteria andLinkSelfRuleBetween(String value1, String value2) {
            addCriterion("link_self_rule between", value1, value2, "linkSelfRule");
            return (Criteria) this;
        }

        public Criteria andLinkSelfRuleNotBetween(String value1, String value2) {
            addCriterion("link_self_rule not between", value1, value2, "linkSelfRule");
            return (Criteria) this;
        }

        public Criteria andPageIndexRuleIsNull() {
            addCriterion("page_index_rule is null");
            return (Criteria) this;
        }

        public Criteria andPageIndexRuleIsNotNull() {
            addCriterion("page_index_rule is not null");
            return (Criteria) this;
        }

        public Criteria andPageIndexRuleEqualTo(String value) {
            addCriterion("page_index_rule =", value, "pageIndexRule");
            return (Criteria) this;
        }

        public Criteria andPageIndexRuleNotEqualTo(String value) {
            addCriterion("page_index_rule <>", value, "pageIndexRule");
            return (Criteria) this;
        }

        public Criteria andPageIndexRuleGreaterThan(String value) {
            addCriterion("page_index_rule >", value, "pageIndexRule");
            return (Criteria) this;
        }

        public Criteria andPageIndexRuleGreaterThanOrEqualTo(String value) {
            addCriterion("page_index_rule >=", value, "pageIndexRule");
            return (Criteria) this;
        }

        public Criteria andPageIndexRuleLessThan(String value) {
            addCriterion("page_index_rule <", value, "pageIndexRule");
            return (Criteria) this;
        }

        public Criteria andPageIndexRuleLessThanOrEqualTo(String value) {
            addCriterion("page_index_rule <=", value, "pageIndexRule");
            return (Criteria) this;
        }

        public Criteria andPageIndexRuleLike(String value) {
            addCriterion("page_index_rule like", value, "pageIndexRule");
            return (Criteria) this;
        }

        public Criteria andPageIndexRuleNotLike(String value) {
            addCriterion("page_index_rule not like", value, "pageIndexRule");
            return (Criteria) this;
        }

        public Criteria andPageIndexRuleIn(List<String> values) {
            addCriterion("page_index_rule in", values, "pageIndexRule");
            return (Criteria) this;
        }

        public Criteria andPageIndexRuleNotIn(List<String> values) {
            addCriterion("page_index_rule not in", values, "pageIndexRule");
            return (Criteria) this;
        }

        public Criteria andPageIndexRuleBetween(String value1, String value2) {
            addCriterion("page_index_rule between", value1, value2, "pageIndexRule");
            return (Criteria) this;
        }

        public Criteria andPageIndexRuleNotBetween(String value1, String value2) {
            addCriterion("page_index_rule not between", value1, value2, "pageIndexRule");
            return (Criteria) this;
        }

        public Criteria andPageIndexClasspathIsNull() {
            addCriterion("page_index_classpath is null");
            return (Criteria) this;
        }

        public Criteria andPageIndexClasspathIsNotNull() {
            addCriterion("page_index_classpath is not null");
            return (Criteria) this;
        }

        public Criteria andPageIndexClasspathEqualTo(String value) {
            addCriterion("page_index_classpath =", value, "pageIndexClasspath");
            return (Criteria) this;
        }

        public Criteria andPageIndexClasspathNotEqualTo(String value) {
            addCriterion("page_index_classpath <>", value, "pageIndexClasspath");
            return (Criteria) this;
        }

        public Criteria andPageIndexClasspathGreaterThan(String value) {
            addCriterion("page_index_classpath >", value, "pageIndexClasspath");
            return (Criteria) this;
        }

        public Criteria andPageIndexClasspathGreaterThanOrEqualTo(String value) {
            addCriterion("page_index_classpath >=", value, "pageIndexClasspath");
            return (Criteria) this;
        }

        public Criteria andPageIndexClasspathLessThan(String value) {
            addCriterion("page_index_classpath <", value, "pageIndexClasspath");
            return (Criteria) this;
        }

        public Criteria andPageIndexClasspathLessThanOrEqualTo(String value) {
            addCriterion("page_index_classpath <=", value, "pageIndexClasspath");
            return (Criteria) this;
        }

        public Criteria andPageIndexClasspathLike(String value) {
            addCriterion("page_index_classpath like", value, "pageIndexClasspath");
            return (Criteria) this;
        }

        public Criteria andPageIndexClasspathNotLike(String value) {
            addCriterion("page_index_classpath not like", value, "pageIndexClasspath");
            return (Criteria) this;
        }

        public Criteria andPageIndexClasspathIn(List<String> values) {
            addCriterion("page_index_classpath in", values, "pageIndexClasspath");
            return (Criteria) this;
        }

        public Criteria andPageIndexClasspathNotIn(List<String> values) {
            addCriterion("page_index_classpath not in", values, "pageIndexClasspath");
            return (Criteria) this;
        }

        public Criteria andPageIndexClasspathBetween(String value1, String value2) {
            addCriterion("page_index_classpath between", value1, value2, "pageIndexClasspath");
            return (Criteria) this;
        }

        public Criteria andPageIndexClasspathNotBetween(String value1, String value2) {
            addCriterion("page_index_classpath not between", value1, value2, "pageIndexClasspath");
            return (Criteria) this;
        }

        public Criteria andTaskClasspathIsNull() {
            addCriterion("task_classpath is null");
            return (Criteria) this;
        }

        public Criteria andTaskClasspathIsNotNull() {
            addCriterion("task_classpath is not null");
            return (Criteria) this;
        }

        public Criteria andTaskClasspathEqualTo(String value) {
            addCriterion("task_classpath =", value, "taskClasspath");
            return (Criteria) this;
        }

        public Criteria andTaskClasspathNotEqualTo(String value) {
            addCriterion("task_classpath <>", value, "taskClasspath");
            return (Criteria) this;
        }

        public Criteria andTaskClasspathGreaterThan(String value) {
            addCriterion("task_classpath >", value, "taskClasspath");
            return (Criteria) this;
        }

        public Criteria andTaskClasspathGreaterThanOrEqualTo(String value) {
            addCriterion("task_classpath >=", value, "taskClasspath");
            return (Criteria) this;
        }

        public Criteria andTaskClasspathLessThan(String value) {
            addCriterion("task_classpath <", value, "taskClasspath");
            return (Criteria) this;
        }

        public Criteria andTaskClasspathLessThanOrEqualTo(String value) {
            addCriterion("task_classpath <=", value, "taskClasspath");
            return (Criteria) this;
        }

        public Criteria andTaskClasspathLike(String value) {
            addCriterion("task_classpath like", value, "taskClasspath");
            return (Criteria) this;
        }

        public Criteria andTaskClasspathNotLike(String value) {
            addCriterion("task_classpath not like", value, "taskClasspath");
            return (Criteria) this;
        }

        public Criteria andTaskClasspathIn(List<String> values) {
            addCriterion("task_classpath in", values, "taskClasspath");
            return (Criteria) this;
        }

        public Criteria andTaskClasspathNotIn(List<String> values) {
            addCriterion("task_classpath not in", values, "taskClasspath");
            return (Criteria) this;
        }

        public Criteria andTaskClasspathBetween(String value1, String value2) {
            addCriterion("task_classpath between", value1, value2, "taskClasspath");
            return (Criteria) this;
        }

        public Criteria andTaskClasspathNotBetween(String value1, String value2) {
            addCriterion("task_classpath not between", value1, value2, "taskClasspath");
            return (Criteria) this;
        }

        public Criteria andAttrIsNull() {
            addCriterion("attr is null");
            return (Criteria) this;
        }

        public Criteria andAttrIsNotNull() {
            addCriterion("attr is not null");
            return (Criteria) this;
        }

        public Criteria andAttrEqualTo(String value) {
            addCriterion("attr =", value, "attr");
            return (Criteria) this;
        }

        public Criteria andAttrNotEqualTo(String value) {
            addCriterion("attr <>", value, "attr");
            return (Criteria) this;
        }

        public Criteria andAttrGreaterThan(String value) {
            addCriterion("attr >", value, "attr");
            return (Criteria) this;
        }

        public Criteria andAttrGreaterThanOrEqualTo(String value) {
            addCriterion("attr >=", value, "attr");
            return (Criteria) this;
        }

        public Criteria andAttrLessThan(String value) {
            addCriterion("attr <", value, "attr");
            return (Criteria) this;
        }

        public Criteria andAttrLessThanOrEqualTo(String value) {
            addCriterion("attr <=", value, "attr");
            return (Criteria) this;
        }

        public Criteria andAttrLike(String value) {
            addCriterion("attr like", value, "attr");
            return (Criteria) this;
        }

        public Criteria andAttrNotLike(String value) {
            addCriterion("attr not like", value, "attr");
            return (Criteria) this;
        }

        public Criteria andAttrIn(List<String> values) {
            addCriterion("attr in", values, "attr");
            return (Criteria) this;
        }

        public Criteria andAttrNotIn(List<String> values) {
            addCriterion("attr not in", values, "attr");
            return (Criteria) this;
        }

        public Criteria andAttrBetween(String value1, String value2) {
            addCriterion("attr between", value1, value2, "attr");
            return (Criteria) this;
        }

        public Criteria andAttrNotBetween(String value1, String value2) {
            addCriterion("attr not between", value1, value2, "attr");
            return (Criteria) this;
        }

        public Criteria andPageRuleIsNull() {
            addCriterion("page_rule is null");
            return (Criteria) this;
        }

        public Criteria andPageRuleIsNotNull() {
            addCriterion("page_rule is not null");
            return (Criteria) this;
        }

        public Criteria andPageRuleEqualTo(String value) {
            addCriterion("page_rule =", value, "pageRule");
            return (Criteria) this;
        }

        public Criteria andPageRuleNotEqualTo(String value) {
            addCriterion("page_rule <>", value, "pageRule");
            return (Criteria) this;
        }

        public Criteria andPageRuleGreaterThan(String value) {
            addCriterion("page_rule >", value, "pageRule");
            return (Criteria) this;
        }

        public Criteria andPageRuleGreaterThanOrEqualTo(String value) {
            addCriterion("page_rule >=", value, "pageRule");
            return (Criteria) this;
        }

        public Criteria andPageRuleLessThan(String value) {
            addCriterion("page_rule <", value, "pageRule");
            return (Criteria) this;
        }

        public Criteria andPageRuleLessThanOrEqualTo(String value) {
            addCriterion("page_rule <=", value, "pageRule");
            return (Criteria) this;
        }

        public Criteria andPageRuleLike(String value) {
            addCriterion("page_rule like", value, "pageRule");
            return (Criteria) this;
        }

        public Criteria andPageRuleNotLike(String value) {
            addCriterion("page_rule not like", value, "pageRule");
            return (Criteria) this;
        }

        public Criteria andPageRuleIn(List<String> values) {
            addCriterion("page_rule in", values, "pageRule");
            return (Criteria) this;
        }

        public Criteria andPageRuleNotIn(List<String> values) {
            addCriterion("page_rule not in", values, "pageRule");
            return (Criteria) this;
        }

        public Criteria andPageRuleBetween(String value1, String value2) {
            addCriterion("page_rule between", value1, value2, "pageRule");
            return (Criteria) this;
        }

        public Criteria andPageRuleNotBetween(String value1, String value2) {
            addCriterion("page_rule not between", value1, value2, "pageRule");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
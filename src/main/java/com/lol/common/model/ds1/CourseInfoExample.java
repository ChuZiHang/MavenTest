package com.lol.common.model.ds1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.lol.common.plugin.Page;

public class CourseInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Page page;

    private String dialect = "mysql";

    public CourseInfoExample() {
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

    public void setPage(Page page) {
        this.page=page;
    }

    public Page getPage() {
        return page;
    }

    public void setDialect(String dialect) {
        this.dialect=dialect;
    }

    public String getDialect() {
        return dialect;
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andClassNameIsNull() {
            addCriterion("class_name is null");
            return (Criteria) this;
        }

        public Criteria andClassNameIsNotNull() {
            addCriterion("class_name is not null");
            return (Criteria) this;
        }

        public Criteria andClassNameEqualTo(String value) {
            addCriterion("class_name =", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotEqualTo(String value) {
            addCriterion("class_name <>", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameGreaterThan(String value) {
            addCriterion("class_name >", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameGreaterThanOrEqualTo(String value) {
            addCriterion("class_name >=", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameLessThan(String value) {
            addCriterion("class_name <", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameLessThanOrEqualTo(String value) {
            addCriterion("class_name <=", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameLike(String value) {
            addCriterion("class_name like", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotLike(String value) {
            addCriterion("class_name not like", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameIn(List<String> values) {
            addCriterion("class_name in", values, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotIn(List<String> values) {
            addCriterion("class_name not in", values, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameBetween(String value1, String value2) {
            addCriterion("class_name between", value1, value2, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotBetween(String value1, String value2) {
            addCriterion("class_name not between", value1, value2, "className");
            return (Criteria) this;
        }

        public Criteria andClassImgIsNull() {
            addCriterion("class_img is null");
            return (Criteria) this;
        }

        public Criteria andClassImgIsNotNull() {
            addCriterion("class_img is not null");
            return (Criteria) this;
        }

        public Criteria andClassImgEqualTo(String value) {
            addCriterion("class_img =", value, "classImg");
            return (Criteria) this;
        }

        public Criteria andClassImgNotEqualTo(String value) {
            addCriterion("class_img <>", value, "classImg");
            return (Criteria) this;
        }

        public Criteria andClassImgGreaterThan(String value) {
            addCriterion("class_img >", value, "classImg");
            return (Criteria) this;
        }

        public Criteria andClassImgGreaterThanOrEqualTo(String value) {
            addCriterion("class_img >=", value, "classImg");
            return (Criteria) this;
        }

        public Criteria andClassImgLessThan(String value) {
            addCriterion("class_img <", value, "classImg");
            return (Criteria) this;
        }

        public Criteria andClassImgLessThanOrEqualTo(String value) {
            addCriterion("class_img <=", value, "classImg");
            return (Criteria) this;
        }

        public Criteria andClassImgLike(String value) {
            addCriterion("class_img like", value, "classImg");
            return (Criteria) this;
        }

        public Criteria andClassImgNotLike(String value) {
            addCriterion("class_img not like", value, "classImg");
            return (Criteria) this;
        }

        public Criteria andClassImgIn(List<String> values) {
            addCriterion("class_img in", values, "classImg");
            return (Criteria) this;
        }

        public Criteria andClassImgNotIn(List<String> values) {
            addCriterion("class_img not in", values, "classImg");
            return (Criteria) this;
        }

        public Criteria andClassImgBetween(String value1, String value2) {
            addCriterion("class_img between", value1, value2, "classImg");
            return (Criteria) this;
        }

        public Criteria andClassImgNotBetween(String value1, String value2) {
            addCriterion("class_img not between", value1, value2, "classImg");
            return (Criteria) this;
        }

        public Criteria andCourseRateIsNull() {
            addCriterion("course_rate is null");
            return (Criteria) this;
        }

        public Criteria andCourseRateIsNotNull() {
            addCriterion("course_rate is not null");
            return (Criteria) this;
        }

        public Criteria andCourseRateEqualTo(Integer value) {
            addCriterion("course_rate =", value, "courseRate");
            return (Criteria) this;
        }

        public Criteria andCourseRateNotEqualTo(Integer value) {
            addCriterion("course_rate <>", value, "courseRate");
            return (Criteria) this;
        }

        public Criteria andCourseRateGreaterThan(Integer value) {
            addCriterion("course_rate >", value, "courseRate");
            return (Criteria) this;
        }

        public Criteria andCourseRateGreaterThanOrEqualTo(Integer value) {
            addCriterion("course_rate >=", value, "courseRate");
            return (Criteria) this;
        }

        public Criteria andCourseRateLessThan(Integer value) {
            addCriterion("course_rate <", value, "courseRate");
            return (Criteria) this;
        }

        public Criteria andCourseRateLessThanOrEqualTo(Integer value) {
            addCriterion("course_rate <=", value, "courseRate");
            return (Criteria) this;
        }

        public Criteria andCourseRateIn(List<Integer> values) {
            addCriterion("course_rate in", values, "courseRate");
            return (Criteria) this;
        }

        public Criteria andCourseRateNotIn(List<Integer> values) {
            addCriterion("course_rate not in", values, "courseRate");
            return (Criteria) this;
        }

        public Criteria andCourseRateBetween(Integer value1, Integer value2) {
            addCriterion("course_rate between", value1, value2, "courseRate");
            return (Criteria) this;
        }

        public Criteria andCourseRateNotBetween(Integer value1, Integer value2) {
            addCriterion("course_rate not between", value1, value2, "courseRate");
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

        public Criteria andSysCustIdIsNull() {
            addCriterion("sys_cust_id is null");
            return (Criteria) this;
        }

        public Criteria andSysCustIdIsNotNull() {
            addCriterion("sys_cust_id is not null");
            return (Criteria) this;
        }

        public Criteria andSysCustIdEqualTo(Integer value) {
            addCriterion("sys_cust_id =", value, "sysCustId");
            return (Criteria) this;
        }

        public Criteria andSysCustIdNotEqualTo(Integer value) {
            addCriterion("sys_cust_id <>", value, "sysCustId");
            return (Criteria) this;
        }

        public Criteria andSysCustIdGreaterThan(Integer value) {
            addCriterion("sys_cust_id >", value, "sysCustId");
            return (Criteria) this;
        }

        public Criteria andSysCustIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("sys_cust_id >=", value, "sysCustId");
            return (Criteria) this;
        }

        public Criteria andSysCustIdLessThan(Integer value) {
            addCriterion("sys_cust_id <", value, "sysCustId");
            return (Criteria) this;
        }

        public Criteria andSysCustIdLessThanOrEqualTo(Integer value) {
            addCriterion("sys_cust_id <=", value, "sysCustId");
            return (Criteria) this;
        }

        public Criteria andSysCustIdIn(List<Integer> values) {
            addCriterion("sys_cust_id in", values, "sysCustId");
            return (Criteria) this;
        }

        public Criteria andSysCustIdNotIn(List<Integer> values) {
            addCriterion("sys_cust_id not in", values, "sysCustId");
            return (Criteria) this;
        }

        public Criteria andSysCustIdBetween(Integer value1, Integer value2) {
            addCriterion("sys_cust_id between", value1, value2, "sysCustId");
            return (Criteria) this;
        }

        public Criteria andSysCustIdNotBetween(Integer value1, Integer value2) {
            addCriterion("sys_cust_id not between", value1, value2, "sysCustId");
            return (Criteria) this;
        }

        public Criteria andParentCustIdIsNull() {
            addCriterion("parent_cust_id is null");
            return (Criteria) this;
        }

        public Criteria andParentCustIdIsNotNull() {
            addCriterion("parent_cust_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentCustIdEqualTo(Integer value) {
            addCriterion("parent_cust_id =", value, "parentCustId");
            return (Criteria) this;
        }

        public Criteria andParentCustIdNotEqualTo(Integer value) {
            addCriterion("parent_cust_id <>", value, "parentCustId");
            return (Criteria) this;
        }

        public Criteria andParentCustIdGreaterThan(Integer value) {
            addCriterion("parent_cust_id >", value, "parentCustId");
            return (Criteria) this;
        }

        public Criteria andParentCustIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("parent_cust_id >=", value, "parentCustId");
            return (Criteria) this;
        }

        public Criteria andParentCustIdLessThan(Integer value) {
            addCriterion("parent_cust_id <", value, "parentCustId");
            return (Criteria) this;
        }

        public Criteria andParentCustIdLessThanOrEqualTo(Integer value) {
            addCriterion("parent_cust_id <=", value, "parentCustId");
            return (Criteria) this;
        }

        public Criteria andParentCustIdIn(List<Integer> values) {
            addCriterion("parent_cust_id in", values, "parentCustId");
            return (Criteria) this;
        }

        public Criteria andParentCustIdNotIn(List<Integer> values) {
            addCriterion("parent_cust_id not in", values, "parentCustId");
            return (Criteria) this;
        }

        public Criteria andParentCustIdBetween(Integer value1, Integer value2) {
            addCriterion("parent_cust_id between", value1, value2, "parentCustId");
            return (Criteria) this;
        }

        public Criteria andParentCustIdNotBetween(Integer value1, Integer value2) {
            addCriterion("parent_cust_id not between", value1, value2, "parentCustId");
            return (Criteria) this;
        }

        public Criteria andScoreIsNull() {
            addCriterion("score is null");
            return (Criteria) this;
        }

        public Criteria andScoreIsNotNull() {
            addCriterion("score is not null");
            return (Criteria) this;
        }

        public Criteria andScoreEqualTo(Float value) {
            addCriterion("score =", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotEqualTo(Float value) {
            addCriterion("score <>", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThan(Float value) {
            addCriterion("score >", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(Float value) {
            addCriterion("score >=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThan(Float value) {
            addCriterion("score <", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThanOrEqualTo(Float value) {
            addCriterion("score <=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreIn(List<Float> values) {
            addCriterion("score in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotIn(List<Float> values) {
            addCriterion("score not in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreBetween(Float value1, Float value2) {
            addCriterion("score between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotBetween(Float value1, Float value2) {
            addCriterion("score not between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andClassNameLikeInsensitive(String value) {
            addCriterion("upper(class_name) like", value.toUpperCase(), "className");
            return (Criteria) this;
        }

        public Criteria andClassImgLikeInsensitive(String value) {
            addCriterion("upper(class_img) like", value.toUpperCase(), "classImg");
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
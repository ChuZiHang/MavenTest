package com.lol.common.model.ds1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lol.common.plugin.Page;

public class SmsAuthCodeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Page page;

    private String dialect = "mysql";

    public SmsAuthCodeExample() {
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

        public Criteria andMobileIsNull() {
            addCriterion("mobile is null");
            return (Criteria) this;
        }

        public Criteria andMobileIsNotNull() {
            addCriterion("mobile is not null");
            return (Criteria) this;
        }

        public Criteria andMobileEqualTo(String value) {
            addCriterion("mobile =", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotEqualTo(String value) {
            addCriterion("mobile <>", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThan(String value) {
            addCriterion("mobile >", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThanOrEqualTo(String value) {
            addCriterion("mobile >=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThan(String value) {
            addCriterion("mobile <", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThanOrEqualTo(String value) {
            addCriterion("mobile <=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLike(String value) {
            addCriterion("mobile like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotLike(String value) {
            addCriterion("mobile not like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileIn(List<String> values) {
            addCriterion("mobile in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotIn(List<String> values) {
            addCriterion("mobile not in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileBetween(String value1, String value2) {
            addCriterion("mobile between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotBetween(String value1, String value2) {
            addCriterion("mobile not between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andAuthCodeIsNull() {
            addCriterion("auth_code is null");
            return (Criteria) this;
        }

        public Criteria andAuthCodeIsNotNull() {
            addCriterion("auth_code is not null");
            return (Criteria) this;
        }

        public Criteria andAuthCodeEqualTo(String value) {
            addCriterion("auth_code =", value, "authCode");
            return (Criteria) this;
        }

        public Criteria andAuthCodeNotEqualTo(String value) {
            addCriterion("auth_code <>", value, "authCode");
            return (Criteria) this;
        }

        public Criteria andAuthCodeGreaterThan(String value) {
            addCriterion("auth_code >", value, "authCode");
            return (Criteria) this;
        }

        public Criteria andAuthCodeGreaterThanOrEqualTo(String value) {
            addCriterion("auth_code >=", value, "authCode");
            return (Criteria) this;
        }

        public Criteria andAuthCodeLessThan(String value) {
            addCriterion("auth_code <", value, "authCode");
            return (Criteria) this;
        }

        public Criteria andAuthCodeLessThanOrEqualTo(String value) {
            addCriterion("auth_code <=", value, "authCode");
            return (Criteria) this;
        }

        public Criteria andAuthCodeLike(String value) {
            addCriterion("auth_code like", value, "authCode");
            return (Criteria) this;
        }

        public Criteria andAuthCodeNotLike(String value) {
            addCriterion("auth_code not like", value, "authCode");
            return (Criteria) this;
        }

        public Criteria andAuthCodeIn(List<String> values) {
            addCriterion("auth_code in", values, "authCode");
            return (Criteria) this;
        }

        public Criteria andAuthCodeNotIn(List<String> values) {
            addCriterion("auth_code not in", values, "authCode");
            return (Criteria) this;
        }

        public Criteria andAuthCodeBetween(String value1, String value2) {
            addCriterion("auth_code between", value1, value2, "authCode");
            return (Criteria) this;
        }

        public Criteria andAuthCodeNotBetween(String value1, String value2) {
            addCriterion("auth_code not between", value1, value2, "authCode");
            return (Criteria) this;
        }

        public Criteria andMsgIsNull() {
            addCriterion("msg is null");
            return (Criteria) this;
        }

        public Criteria andMsgIsNotNull() {
            addCriterion("msg is not null");
            return (Criteria) this;
        }

        public Criteria andMsgEqualTo(String value) {
            addCriterion("msg =", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgNotEqualTo(String value) {
            addCriterion("msg <>", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgGreaterThan(String value) {
            addCriterion("msg >", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgGreaterThanOrEqualTo(String value) {
            addCriterion("msg >=", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgLessThan(String value) {
            addCriterion("msg <", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgLessThanOrEqualTo(String value) {
            addCriterion("msg <=", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgLike(String value) {
            addCriterion("msg like", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgNotLike(String value) {
            addCriterion("msg not like", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgIn(List<String> values) {
            addCriterion("msg in", values, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgNotIn(List<String> values) {
            addCriterion("msg not in", values, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgBetween(String value1, String value2) {
            addCriterion("msg between", value1, value2, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgNotBetween(String value1, String value2) {
            addCriterion("msg not between", value1, value2, "msg");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNull() {
            addCriterion("send_time is null");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNotNull() {
            addCriterion("send_time is not null");
            return (Criteria) this;
        }

        public Criteria andSendTimeEqualTo(Date value) {
            addCriterion("send_time =", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotEqualTo(Date value) {
            addCriterion("send_time <>", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThan(Date value) {
            addCriterion("send_time >", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("send_time >=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThan(Date value) {
            addCriterion("send_time <", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThanOrEqualTo(Date value) {
            addCriterion("send_time <=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeIn(List<Date> values) {
            addCriterion("send_time in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotIn(List<Date> values) {
            addCriterion("send_time not in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeBetween(Date value1, Date value2) {
            addCriterion("send_time between", value1, value2, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotBetween(Date value1, Date value2) {
            addCriterion("send_time not between", value1, value2, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendStatusIsNull() {
            addCriterion("send_status is null");
            return (Criteria) this;
        }

        public Criteria andSendStatusIsNotNull() {
            addCriterion("send_status is not null");
            return (Criteria) this;
        }

        public Criteria andSendStatusEqualTo(String value) {
            addCriterion("send_status =", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusNotEqualTo(String value) {
            addCriterion("send_status <>", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusGreaterThan(String value) {
            addCriterion("send_status >", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusGreaterThanOrEqualTo(String value) {
            addCriterion("send_status >=", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusLessThan(String value) {
            addCriterion("send_status <", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusLessThanOrEqualTo(String value) {
            addCriterion("send_status <=", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusLike(String value) {
            addCriterion("send_status like", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusNotLike(String value) {
            addCriterion("send_status not like", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusIn(List<String> values) {
            addCriterion("send_status in", values, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusNotIn(List<String> values) {
            addCriterion("send_status not in", values, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusBetween(String value1, String value2) {
            addCriterion("send_status between", value1, value2, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusNotBetween(String value1, String value2) {
            addCriterion("send_status not between", value1, value2, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andArriveStatusIsNull() {
            addCriterion("arrive_status is null");
            return (Criteria) this;
        }

        public Criteria andArriveStatusIsNotNull() {
            addCriterion("arrive_status is not null");
            return (Criteria) this;
        }

        public Criteria andArriveStatusEqualTo(String value) {
            addCriterion("arrive_status =", value, "arriveStatus");
            return (Criteria) this;
        }

        public Criteria andArriveStatusNotEqualTo(String value) {
            addCriterion("arrive_status <>", value, "arriveStatus");
            return (Criteria) this;
        }

        public Criteria andArriveStatusGreaterThan(String value) {
            addCriterion("arrive_status >", value, "arriveStatus");
            return (Criteria) this;
        }

        public Criteria andArriveStatusGreaterThanOrEqualTo(String value) {
            addCriterion("arrive_status >=", value, "arriveStatus");
            return (Criteria) this;
        }

        public Criteria andArriveStatusLessThan(String value) {
            addCriterion("arrive_status <", value, "arriveStatus");
            return (Criteria) this;
        }

        public Criteria andArriveStatusLessThanOrEqualTo(String value) {
            addCriterion("arrive_status <=", value, "arriveStatus");
            return (Criteria) this;
        }

        public Criteria andArriveStatusLike(String value) {
            addCriterion("arrive_status like", value, "arriveStatus");
            return (Criteria) this;
        }

        public Criteria andArriveStatusNotLike(String value) {
            addCriterion("arrive_status not like", value, "arriveStatus");
            return (Criteria) this;
        }

        public Criteria andArriveStatusIn(List<String> values) {
            addCriterion("arrive_status in", values, "arriveStatus");
            return (Criteria) this;
        }

        public Criteria andArriveStatusNotIn(List<String> values) {
            addCriterion("arrive_status not in", values, "arriveStatus");
            return (Criteria) this;
        }

        public Criteria andArriveStatusBetween(String value1, String value2) {
            addCriterion("arrive_status between", value1, value2, "arriveStatus");
            return (Criteria) this;
        }

        public Criteria andArriveStatusNotBetween(String value1, String value2) {
            addCriterion("arrive_status not between", value1, value2, "arriveStatus");
            return (Criteria) this;
        }

        public Criteria andProviderIdIsNull() {
            addCriterion("provider_id is null");
            return (Criteria) this;
        }

        public Criteria andProviderIdIsNotNull() {
            addCriterion("provider_id is not null");
            return (Criteria) this;
        }

        public Criteria andProviderIdEqualTo(Integer value) {
            addCriterion("provider_id =", value, "providerId");
            return (Criteria) this;
        }

        public Criteria andProviderIdNotEqualTo(Integer value) {
            addCriterion("provider_id <>", value, "providerId");
            return (Criteria) this;
        }

        public Criteria andProviderIdGreaterThan(Integer value) {
            addCriterion("provider_id >", value, "providerId");
            return (Criteria) this;
        }

        public Criteria andProviderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("provider_id >=", value, "providerId");
            return (Criteria) this;
        }

        public Criteria andProviderIdLessThan(Integer value) {
            addCriterion("provider_id <", value, "providerId");
            return (Criteria) this;
        }

        public Criteria andProviderIdLessThanOrEqualTo(Integer value) {
            addCriterion("provider_id <=", value, "providerId");
            return (Criteria) this;
        }

        public Criteria andProviderIdIn(List<Integer> values) {
            addCriterion("provider_id in", values, "providerId");
            return (Criteria) this;
        }

        public Criteria andProviderIdNotIn(List<Integer> values) {
            addCriterion("provider_id not in", values, "providerId");
            return (Criteria) this;
        }

        public Criteria andProviderIdBetween(Integer value1, Integer value2) {
            addCriterion("provider_id between", value1, value2, "providerId");
            return (Criteria) this;
        }

        public Criteria andProviderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("provider_id not between", value1, value2, "providerId");
            return (Criteria) this;
        }

        public Criteria andProviderTypeIsNull() {
            addCriterion("provider_type is null");
            return (Criteria) this;
        }

        public Criteria andProviderTypeIsNotNull() {
            addCriterion("provider_type is not null");
            return (Criteria) this;
        }

        public Criteria andProviderTypeEqualTo(Integer value) {
            addCriterion("provider_type =", value, "providerType");
            return (Criteria) this;
        }

        public Criteria andProviderTypeNotEqualTo(Integer value) {
            addCriterion("provider_type <>", value, "providerType");
            return (Criteria) this;
        }

        public Criteria andProviderTypeGreaterThan(Integer value) {
            addCriterion("provider_type >", value, "providerType");
            return (Criteria) this;
        }

        public Criteria andProviderTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("provider_type >=", value, "providerType");
            return (Criteria) this;
        }

        public Criteria andProviderTypeLessThan(Integer value) {
            addCriterion("provider_type <", value, "providerType");
            return (Criteria) this;
        }

        public Criteria andProviderTypeLessThanOrEqualTo(Integer value) {
            addCriterion("provider_type <=", value, "providerType");
            return (Criteria) this;
        }

        public Criteria andProviderTypeIn(List<Integer> values) {
            addCriterion("provider_type in", values, "providerType");
            return (Criteria) this;
        }

        public Criteria andProviderTypeNotIn(List<Integer> values) {
            addCriterion("provider_type not in", values, "providerType");
            return (Criteria) this;
        }

        public Criteria andProviderTypeBetween(Integer value1, Integer value2) {
            addCriterion("provider_type between", value1, value2, "providerType");
            return (Criteria) this;
        }

        public Criteria andProviderTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("provider_type not between", value1, value2, "providerType");
            return (Criteria) this;
        }

        public Criteria andSendResultIsNull() {
            addCriterion("send_result is null");
            return (Criteria) this;
        }

        public Criteria andSendResultIsNotNull() {
            addCriterion("send_result is not null");
            return (Criteria) this;
        }

        public Criteria andSendResultEqualTo(Integer value) {
            addCriterion("send_result =", value, "sendResult");
            return (Criteria) this;
        }

        public Criteria andSendResultNotEqualTo(Integer value) {
            addCriterion("send_result <>", value, "sendResult");
            return (Criteria) this;
        }

        public Criteria andSendResultGreaterThan(Integer value) {
            addCriterion("send_result >", value, "sendResult");
            return (Criteria) this;
        }

        public Criteria andSendResultGreaterThanOrEqualTo(Integer value) {
            addCriterion("send_result >=", value, "sendResult");
            return (Criteria) this;
        }

        public Criteria andSendResultLessThan(Integer value) {
            addCriterion("send_result <", value, "sendResult");
            return (Criteria) this;
        }

        public Criteria andSendResultLessThanOrEqualTo(Integer value) {
            addCriterion("send_result <=", value, "sendResult");
            return (Criteria) this;
        }

        public Criteria andSendResultIn(List<Integer> values) {
            addCriterion("send_result in", values, "sendResult");
            return (Criteria) this;
        }

        public Criteria andSendResultNotIn(List<Integer> values) {
            addCriterion("send_result not in", values, "sendResult");
            return (Criteria) this;
        }

        public Criteria andSendResultBetween(Integer value1, Integer value2) {
            addCriterion("send_result between", value1, value2, "sendResult");
            return (Criteria) this;
        }

        public Criteria andSendResultNotBetween(Integer value1, Integer value2) {
            addCriterion("send_result not between", value1, value2, "sendResult");
            return (Criteria) this;
        }

        public Criteria andArriveResultIsNull() {
            addCriterion("arrive_result is null");
            return (Criteria) this;
        }

        public Criteria andArriveResultIsNotNull() {
            addCriterion("arrive_result is not null");
            return (Criteria) this;
        }

        public Criteria andArriveResultEqualTo(Integer value) {
            addCriterion("arrive_result =", value, "arriveResult");
            return (Criteria) this;
        }

        public Criteria andArriveResultNotEqualTo(Integer value) {
            addCriterion("arrive_result <>", value, "arriveResult");
            return (Criteria) this;
        }

        public Criteria andArriveResultGreaterThan(Integer value) {
            addCriterion("arrive_result >", value, "arriveResult");
            return (Criteria) this;
        }

        public Criteria andArriveResultGreaterThanOrEqualTo(Integer value) {
            addCriterion("arrive_result >=", value, "arriveResult");
            return (Criteria) this;
        }

        public Criteria andArriveResultLessThan(Integer value) {
            addCriterion("arrive_result <", value, "arriveResult");
            return (Criteria) this;
        }

        public Criteria andArriveResultLessThanOrEqualTo(Integer value) {
            addCriterion("arrive_result <=", value, "arriveResult");
            return (Criteria) this;
        }

        public Criteria andArriveResultIn(List<Integer> values) {
            addCriterion("arrive_result in", values, "arriveResult");
            return (Criteria) this;
        }

        public Criteria andArriveResultNotIn(List<Integer> values) {
            addCriterion("arrive_result not in", values, "arriveResult");
            return (Criteria) this;
        }

        public Criteria andArriveResultBetween(Integer value1, Integer value2) {
            addCriterion("arrive_result between", value1, value2, "arriveResult");
            return (Criteria) this;
        }

        public Criteria andArriveResultNotBetween(Integer value1, Integer value2) {
            addCriterion("arrive_result not between", value1, value2, "arriveResult");
            return (Criteria) this;
        }

        public Criteria andCustIdIsNull() {
            addCriterion("cust_id is null");
            return (Criteria) this;
        }

        public Criteria andCustIdIsNotNull() {
            addCriterion("cust_id is not null");
            return (Criteria) this;
        }

        public Criteria andCustIdEqualTo(Long value) {
            addCriterion("cust_id =", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdNotEqualTo(Long value) {
            addCriterion("cust_id <>", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdGreaterThan(Long value) {
            addCriterion("cust_id >", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdGreaterThanOrEqualTo(Long value) {
            addCriterion("cust_id >=", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdLessThan(Long value) {
            addCriterion("cust_id <", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdLessThanOrEqualTo(Long value) {
            addCriterion("cust_id <=", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdIn(List<Long> values) {
            addCriterion("cust_id in", values, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdNotIn(List<Long> values) {
            addCriterion("cust_id not in", values, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdBetween(Long value1, Long value2) {
            addCriterion("cust_id between", value1, value2, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdNotBetween(Long value1, Long value2) {
            addCriterion("cust_id not between", value1, value2, "custId");
            return (Criteria) this;
        }

        public Criteria andMobileLikeInsensitive(String value) {
            addCriterion("upper(mobile) like", value.toUpperCase(), "mobile");
            return (Criteria) this;
        }

        public Criteria andAuthCodeLikeInsensitive(String value) {
            addCriterion("upper(auth_code) like", value.toUpperCase(), "authCode");
            return (Criteria) this;
        }

        public Criteria andMsgLikeInsensitive(String value) {
            addCriterion("upper(msg) like", value.toUpperCase(), "msg");
            return (Criteria) this;
        }

        public Criteria andSendStatusLikeInsensitive(String value) {
            addCriterion("upper(send_status) like", value.toUpperCase(), "sendStatus");
            return (Criteria) this;
        }

        public Criteria andArriveStatusLikeInsensitive(String value) {
            addCriterion("upper(arrive_status) like", value.toUpperCase(), "arriveStatus");
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
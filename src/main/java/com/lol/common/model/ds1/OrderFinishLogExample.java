package com.lol.common.model.ds1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.lol.common.plugin.Page;

public class OrderFinishLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Page page;

    private String dialect = "mysql";

    public OrderFinishLogExample() {
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

        public Criteria andOrderIdIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(Long value) {
            addCriterion("order_id =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(Long value) {
            addCriterion("order_id <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(Long value) {
            addCriterion("order_id >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(Long value) {
            addCriterion("order_id >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(Long value) {
            addCriterion("order_id <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(Long value) {
            addCriterion("order_id <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<Long> values) {
            addCriterion("order_id in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<Long> values) {
            addCriterion("order_id not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(Long value1, Long value2) {
            addCriterion("order_id between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(Long value1, Long value2) {
            addCriterion("order_id not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderCodeIsNull() {
            addCriterion("order_code is null");
            return (Criteria) this;
        }

        public Criteria andOrderCodeIsNotNull() {
            addCriterion("order_code is not null");
            return (Criteria) this;
        }

        public Criteria andOrderCodeEqualTo(String value) {
            addCriterion("order_code =", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotEqualTo(String value) {
            addCriterion("order_code <>", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeGreaterThan(String value) {
            addCriterion("order_code >", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeGreaterThanOrEqualTo(String value) {
            addCriterion("order_code >=", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeLessThan(String value) {
            addCriterion("order_code <", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeLessThanOrEqualTo(String value) {
            addCriterion("order_code <=", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeLike(String value) {
            addCriterion("order_code like", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotLike(String value) {
            addCriterion("order_code not like", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeIn(List<String> values) {
            addCriterion("order_code in", values, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotIn(List<String> values) {
            addCriterion("order_code not in", values, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeBetween(String value1, String value2) {
            addCriterion("order_code between", value1, value2, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotBetween(String value1, String value2) {
            addCriterion("order_code not between", value1, value2, "orderCode");
            return (Criteria) this;
        }

        public Criteria andFinishTimeIsNull() {
            addCriterion("finish_time is null");
            return (Criteria) this;
        }

        public Criteria andFinishTimeIsNotNull() {
            addCriterion("finish_time is not null");
            return (Criteria) this;
        }

        public Criteria andFinishTimeEqualTo(Date value) {
            addCriterion("finish_time =", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeNotEqualTo(Date value) {
            addCriterion("finish_time <>", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeGreaterThan(Date value) {
            addCriterion("finish_time >", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("finish_time >=", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeLessThan(Date value) {
            addCriterion("finish_time <", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeLessThanOrEqualTo(Date value) {
            addCriterion("finish_time <=", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeIn(List<Date> values) {
            addCriterion("finish_time in", values, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeNotIn(List<Date> values) {
            addCriterion("finish_time not in", values, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeBetween(Date value1, Date value2) {
            addCriterion("finish_time between", value1, value2, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeNotBetween(Date value1, Date value2) {
            addCriterion("finish_time not between", value1, value2, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishUserIsNull() {
            addCriterion("finish_user is null");
            return (Criteria) this;
        }

        public Criteria andFinishUserIsNotNull() {
            addCriterion("finish_user is not null");
            return (Criteria) this;
        }

        public Criteria andFinishUserEqualTo(String value) {
            addCriterion("finish_user =", value, "finishUser");
            return (Criteria) this;
        }

        public Criteria andFinishUserNotEqualTo(String value) {
            addCriterion("finish_user <>", value, "finishUser");
            return (Criteria) this;
        }

        public Criteria andFinishUserGreaterThan(String value) {
            addCriterion("finish_user >", value, "finishUser");
            return (Criteria) this;
        }

        public Criteria andFinishUserGreaterThanOrEqualTo(String value) {
            addCriterion("finish_user >=", value, "finishUser");
            return (Criteria) this;
        }

        public Criteria andFinishUserLessThan(String value) {
            addCriterion("finish_user <", value, "finishUser");
            return (Criteria) this;
        }

        public Criteria andFinishUserLessThanOrEqualTo(String value) {
            addCriterion("finish_user <=", value, "finishUser");
            return (Criteria) this;
        }

        public Criteria andFinishUserLike(String value) {
            addCriterion("finish_user like", value, "finishUser");
            return (Criteria) this;
        }

        public Criteria andFinishUserNotLike(String value) {
            addCriterion("finish_user not like", value, "finishUser");
            return (Criteria) this;
        }

        public Criteria andFinishUserIn(List<String> values) {
            addCriterion("finish_user in", values, "finishUser");
            return (Criteria) this;
        }

        public Criteria andFinishUserNotIn(List<String> values) {
            addCriterion("finish_user not in", values, "finishUser");
            return (Criteria) this;
        }

        public Criteria andFinishUserBetween(String value1, String value2) {
            addCriterion("finish_user between", value1, value2, "finishUser");
            return (Criteria) this;
        }

        public Criteria andFinishUserNotBetween(String value1, String value2) {
            addCriterion("finish_user not between", value1, value2, "finishUser");
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

        public Criteria andFinishMemoIsNull() {
            addCriterion("finish_memo is null");
            return (Criteria) this;
        }

        public Criteria andFinishMemoIsNotNull() {
            addCriterion("finish_memo is not null");
            return (Criteria) this;
        }

        public Criteria andFinishMemoEqualTo(String value) {
            addCriterion("finish_memo =", value, "finishMemo");
            return (Criteria) this;
        }

        public Criteria andFinishMemoNotEqualTo(String value) {
            addCriterion("finish_memo <>", value, "finishMemo");
            return (Criteria) this;
        }

        public Criteria andFinishMemoGreaterThan(String value) {
            addCriterion("finish_memo >", value, "finishMemo");
            return (Criteria) this;
        }

        public Criteria andFinishMemoGreaterThanOrEqualTo(String value) {
            addCriterion("finish_memo >=", value, "finishMemo");
            return (Criteria) this;
        }

        public Criteria andFinishMemoLessThan(String value) {
            addCriterion("finish_memo <", value, "finishMemo");
            return (Criteria) this;
        }

        public Criteria andFinishMemoLessThanOrEqualTo(String value) {
            addCriterion("finish_memo <=", value, "finishMemo");
            return (Criteria) this;
        }

        public Criteria andFinishMemoLike(String value) {
            addCriterion("finish_memo like", value, "finishMemo");
            return (Criteria) this;
        }

        public Criteria andFinishMemoNotLike(String value) {
            addCriterion("finish_memo not like", value, "finishMemo");
            return (Criteria) this;
        }

        public Criteria andFinishMemoIn(List<String> values) {
            addCriterion("finish_memo in", values, "finishMemo");
            return (Criteria) this;
        }

        public Criteria andFinishMemoNotIn(List<String> values) {
            addCriterion("finish_memo not in", values, "finishMemo");
            return (Criteria) this;
        }

        public Criteria andFinishMemoBetween(String value1, String value2) {
            addCriterion("finish_memo between", value1, value2, "finishMemo");
            return (Criteria) this;
        }

        public Criteria andFinishMemoNotBetween(String value1, String value2) {
            addCriterion("finish_memo not between", value1, value2, "finishMemo");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNull() {
            addCriterion("member_id is null");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNotNull() {
            addCriterion("member_id is not null");
            return (Criteria) this;
        }

        public Criteria andMemberIdEqualTo(Long value) {
            addCriterion("member_id =", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotEqualTo(Long value) {
            addCriterion("member_id <>", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThan(Long value) {
            addCriterion("member_id >", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThanOrEqualTo(Long value) {
            addCriterion("member_id >=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThan(Long value) {
            addCriterion("member_id <", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThanOrEqualTo(Long value) {
            addCriterion("member_id <=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdIn(List<Long> values) {
            addCriterion("member_id in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotIn(List<Long> values) {
            addCriterion("member_id not in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdBetween(Long value1, Long value2) {
            addCriterion("member_id between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotBetween(Long value1, Long value2) {
            addCriterion("member_id not between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andInfoIdIsNull() {
            addCriterion("info_id is null");
            return (Criteria) this;
        }

        public Criteria andInfoIdIsNotNull() {
            addCriterion("info_id is not null");
            return (Criteria) this;
        }

        public Criteria andInfoIdEqualTo(Long value) {
            addCriterion("info_id =", value, "infoId");
            return (Criteria) this;
        }

        public Criteria andInfoIdNotEqualTo(Long value) {
            addCriterion("info_id <>", value, "infoId");
            return (Criteria) this;
        }

        public Criteria andInfoIdGreaterThan(Long value) {
            addCriterion("info_id >", value, "infoId");
            return (Criteria) this;
        }

        public Criteria andInfoIdGreaterThanOrEqualTo(Long value) {
            addCriterion("info_id >=", value, "infoId");
            return (Criteria) this;
        }

        public Criteria andInfoIdLessThan(Long value) {
            addCriterion("info_id <", value, "infoId");
            return (Criteria) this;
        }

        public Criteria andInfoIdLessThanOrEqualTo(Long value) {
            addCriterion("info_id <=", value, "infoId");
            return (Criteria) this;
        }

        public Criteria andInfoIdIn(List<Long> values) {
            addCriterion("info_id in", values, "infoId");
            return (Criteria) this;
        }

        public Criteria andInfoIdNotIn(List<Long> values) {
            addCriterion("info_id not in", values, "infoId");
            return (Criteria) this;
        }

        public Criteria andInfoIdBetween(Long value1, Long value2) {
            addCriterion("info_id between", value1, value2, "infoId");
            return (Criteria) this;
        }

        public Criteria andInfoIdNotBetween(Long value1, Long value2) {
            addCriterion("info_id not between", value1, value2, "infoId");
            return (Criteria) this;
        }

        public Criteria andOrderCodeLikeInsensitive(String value) {
            addCriterion("upper(order_code) like", value.toUpperCase(), "orderCode");
            return (Criteria) this;
        }

        public Criteria andFinishUserLikeInsensitive(String value) {
            addCriterion("upper(finish_user) like", value.toUpperCase(), "finishUser");
            return (Criteria) this;
        }

        public Criteria andFinishMemoLikeInsensitive(String value) {
            addCriterion("upper(finish_memo) like", value.toUpperCase(), "finishMemo");
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
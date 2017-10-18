package com.lol.common.model.ds1;

import com.lol.common.plugin.Page;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PayBalanceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Page page;

    private String dialect = "mysql";

    public PayBalanceExample() {
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

        public Criteria andParentIdIsNull() {
            addCriterion("parent_id is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(Long value) {
            addCriterion("parent_id =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(Long value) {
            addCriterion("parent_id <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(Long value) {
            addCriterion("parent_id >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(Long value) {
            addCriterion("parent_id >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(Long value) {
            addCriterion("parent_id <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(Long value) {
            addCriterion("parent_id <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<Long> values) {
            addCriterion("parent_id in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<Long> values) {
            addCriterion("parent_id not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(Long value1, Long value2) {
            addCriterion("parent_id between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(Long value1, Long value2) {
            addCriterion("parent_id not between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andPayServiceIdIsNull() {
            addCriterion("pay_service_id is null");
            return (Criteria) this;
        }

        public Criteria andPayServiceIdIsNotNull() {
            addCriterion("pay_service_id is not null");
            return (Criteria) this;
        }

        public Criteria andPayServiceIdEqualTo(Long value) {
            addCriterion("pay_service_id =", value, "payServiceId");
            return (Criteria) this;
        }

        public Criteria andPayServiceIdNotEqualTo(Long value) {
            addCriterion("pay_service_id <>", value, "payServiceId");
            return (Criteria) this;
        }

        public Criteria andPayServiceIdGreaterThan(Long value) {
            addCriterion("pay_service_id >", value, "payServiceId");
            return (Criteria) this;
        }

        public Criteria andPayServiceIdGreaterThanOrEqualTo(Long value) {
            addCriterion("pay_service_id >=", value, "payServiceId");
            return (Criteria) this;
        }

        public Criteria andPayServiceIdLessThan(Long value) {
            addCriterion("pay_service_id <", value, "payServiceId");
            return (Criteria) this;
        }

        public Criteria andPayServiceIdLessThanOrEqualTo(Long value) {
            addCriterion("pay_service_id <=", value, "payServiceId");
            return (Criteria) this;
        }

        public Criteria andPayServiceIdIn(List<Long> values) {
            addCriterion("pay_service_id in", values, "payServiceId");
            return (Criteria) this;
        }

        public Criteria andPayServiceIdNotIn(List<Long> values) {
            addCriterion("pay_service_id not in", values, "payServiceId");
            return (Criteria) this;
        }

        public Criteria andPayServiceIdBetween(Long value1, Long value2) {
            addCriterion("pay_service_id between", value1, value2, "payServiceId");
            return (Criteria) this;
        }

        public Criteria andPayServiceIdNotBetween(Long value1, Long value2) {
            addCriterion("pay_service_id not between", value1, value2, "payServiceId");
            return (Criteria) this;
        }

        public Criteria andPayServiceAccountIsNull() {
            addCriterion("pay_service_account is null");
            return (Criteria) this;
        }

        public Criteria andPayServiceAccountIsNotNull() {
            addCriterion("pay_service_account is not null");
            return (Criteria) this;
        }

        public Criteria andPayServiceAccountEqualTo(String value) {
            addCriterion("pay_service_account =", value, "payServiceAccount");
            return (Criteria) this;
        }

        public Criteria andPayServiceAccountNotEqualTo(String value) {
            addCriterion("pay_service_account <>", value, "payServiceAccount");
            return (Criteria) this;
        }

        public Criteria andPayServiceAccountGreaterThan(String value) {
            addCriterion("pay_service_account >", value, "payServiceAccount");
            return (Criteria) this;
        }

        public Criteria andPayServiceAccountGreaterThanOrEqualTo(String value) {
            addCriterion("pay_service_account >=", value, "payServiceAccount");
            return (Criteria) this;
        }

        public Criteria andPayServiceAccountLessThan(String value) {
            addCriterion("pay_service_account <", value, "payServiceAccount");
            return (Criteria) this;
        }

        public Criteria andPayServiceAccountLessThanOrEqualTo(String value) {
            addCriterion("pay_service_account <=", value, "payServiceAccount");
            return (Criteria) this;
        }

        public Criteria andPayServiceAccountLike(String value) {
            addCriterion("pay_service_account like", value, "payServiceAccount");
            return (Criteria) this;
        }

        public Criteria andPayServiceAccountNotLike(String value) {
            addCriterion("pay_service_account not like", value, "payServiceAccount");
            return (Criteria) this;
        }

        public Criteria andPayServiceAccountIn(List<String> values) {
            addCriterion("pay_service_account in", values, "payServiceAccount");
            return (Criteria) this;
        }

        public Criteria andPayServiceAccountNotIn(List<String> values) {
            addCriterion("pay_service_account not in", values, "payServiceAccount");
            return (Criteria) this;
        }

        public Criteria andPayServiceAccountBetween(String value1, String value2) {
            addCriterion("pay_service_account between", value1, value2, "payServiceAccount");
            return (Criteria) this;
        }

        public Criteria andPayServiceAccountNotBetween(String value1, String value2) {
            addCriterion("pay_service_account not between", value1, value2, "payServiceAccount");
            return (Criteria) this;
        }

        public Criteria andPayServiceOrderIdIsNull() {
            addCriterion("pay_service_order_id is null");
            return (Criteria) this;
        }

        public Criteria andPayServiceOrderIdIsNotNull() {
            addCriterion("pay_service_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andPayServiceOrderIdEqualTo(String value) {
            addCriterion("pay_service_order_id =", value, "payServiceOrderId");
            return (Criteria) this;
        }

        public Criteria andPayServiceOrderIdNotEqualTo(String value) {
            addCriterion("pay_service_order_id <>", value, "payServiceOrderId");
            return (Criteria) this;
        }

        public Criteria andPayServiceOrderIdGreaterThan(String value) {
            addCriterion("pay_service_order_id >", value, "payServiceOrderId");
            return (Criteria) this;
        }

        public Criteria andPayServiceOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("pay_service_order_id >=", value, "payServiceOrderId");
            return (Criteria) this;
        }

        public Criteria andPayServiceOrderIdLessThan(String value) {
            addCriterion("pay_service_order_id <", value, "payServiceOrderId");
            return (Criteria) this;
        }

        public Criteria andPayServiceOrderIdLessThanOrEqualTo(String value) {
            addCriterion("pay_service_order_id <=", value, "payServiceOrderId");
            return (Criteria) this;
        }

        public Criteria andPayServiceOrderIdLike(String value) {
            addCriterion("pay_service_order_id like", value, "payServiceOrderId");
            return (Criteria) this;
        }

        public Criteria andPayServiceOrderIdNotLike(String value) {
            addCriterion("pay_service_order_id not like", value, "payServiceOrderId");
            return (Criteria) this;
        }

        public Criteria andPayServiceOrderIdIn(List<String> values) {
            addCriterion("pay_service_order_id in", values, "payServiceOrderId");
            return (Criteria) this;
        }

        public Criteria andPayServiceOrderIdNotIn(List<String> values) {
            addCriterion("pay_service_order_id not in", values, "payServiceOrderId");
            return (Criteria) this;
        }

        public Criteria andPayServiceOrderIdBetween(String value1, String value2) {
            addCriterion("pay_service_order_id between", value1, value2, "payServiceOrderId");
            return (Criteria) this;
        }

        public Criteria andPayServiceOrderIdNotBetween(String value1, String value2) {
            addCriterion("pay_service_order_id not between", value1, value2, "payServiceOrderId");
            return (Criteria) this;
        }

        public Criteria andProductOrderIdIsNull() {
            addCriterion("product_order_id is null");
            return (Criteria) this;
        }

        public Criteria andProductOrderIdIsNotNull() {
            addCriterion("product_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductOrderIdEqualTo(String value) {
            addCriterion("product_order_id =", value, "productOrderId");
            return (Criteria) this;
        }

        public Criteria andProductOrderIdNotEqualTo(String value) {
            addCriterion("product_order_id <>", value, "productOrderId");
            return (Criteria) this;
        }

        public Criteria andProductOrderIdGreaterThan(String value) {
            addCriterion("product_order_id >", value, "productOrderId");
            return (Criteria) this;
        }

        public Criteria andProductOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("product_order_id >=", value, "productOrderId");
            return (Criteria) this;
        }

        public Criteria andProductOrderIdLessThan(String value) {
            addCriterion("product_order_id <", value, "productOrderId");
            return (Criteria) this;
        }

        public Criteria andProductOrderIdLessThanOrEqualTo(String value) {
            addCriterion("product_order_id <=", value, "productOrderId");
            return (Criteria) this;
        }

        public Criteria andProductOrderIdLike(String value) {
            addCriterion("product_order_id like", value, "productOrderId");
            return (Criteria) this;
        }

        public Criteria andProductOrderIdNotLike(String value) {
            addCriterion("product_order_id not like", value, "productOrderId");
            return (Criteria) this;
        }

        public Criteria andProductOrderIdIn(List<String> values) {
            addCriterion("product_order_id in", values, "productOrderId");
            return (Criteria) this;
        }

        public Criteria andProductOrderIdNotIn(List<String> values) {
            addCriterion("product_order_id not in", values, "productOrderId");
            return (Criteria) this;
        }

        public Criteria andProductOrderIdBetween(String value1, String value2) {
            addCriterion("product_order_id between", value1, value2, "productOrderId");
            return (Criteria) this;
        }

        public Criteria andProductOrderIdNotBetween(String value1, String value2) {
            addCriterion("product_order_id not between", value1, value2, "productOrderId");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNull() {
            addCriterion("product_name is null");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNotNull() {
            addCriterion("product_name is not null");
            return (Criteria) this;
        }

        public Criteria andProductNameEqualTo(String value) {
            addCriterion("product_name =", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotEqualTo(String value) {
            addCriterion("product_name <>", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThan(String value) {
            addCriterion("product_name >", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThanOrEqualTo(String value) {
            addCriterion("product_name >=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThan(String value) {
            addCriterion("product_name <", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThanOrEqualTo(String value) {
            addCriterion("product_name <=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLike(String value) {
            addCriterion("product_name like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotLike(String value) {
            addCriterion("product_name not like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameIn(List<String> values) {
            addCriterion("product_name in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotIn(List<String> values) {
            addCriterion("product_name not in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameBetween(String value1, String value2) {
            addCriterion("product_name between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotBetween(String value1, String value2) {
            addCriterion("product_name not between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNumIsNull() {
            addCriterion("product_num is null");
            return (Criteria) this;
        }

        public Criteria andProductNumIsNotNull() {
            addCriterion("product_num is not null");
            return (Criteria) this;
        }

        public Criteria andProductNumEqualTo(Integer value) {
            addCriterion("product_num =", value, "productNum");
            return (Criteria) this;
        }

        public Criteria andProductNumNotEqualTo(Integer value) {
            addCriterion("product_num <>", value, "productNum");
            return (Criteria) this;
        }

        public Criteria andProductNumGreaterThan(Integer value) {
            addCriterion("product_num >", value, "productNum");
            return (Criteria) this;
        }

        public Criteria andProductNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_num >=", value, "productNum");
            return (Criteria) this;
        }

        public Criteria andProductNumLessThan(Integer value) {
            addCriterion("product_num <", value, "productNum");
            return (Criteria) this;
        }

        public Criteria andProductNumLessThanOrEqualTo(Integer value) {
            addCriterion("product_num <=", value, "productNum");
            return (Criteria) this;
        }

        public Criteria andProductNumIn(List<Integer> values) {
            addCriterion("product_num in", values, "productNum");
            return (Criteria) this;
        }

        public Criteria andProductNumNotIn(List<Integer> values) {
            addCriterion("product_num not in", values, "productNum");
            return (Criteria) this;
        }

        public Criteria andProductNumBetween(Integer value1, Integer value2) {
            addCriterion("product_num between", value1, value2, "productNum");
            return (Criteria) this;
        }

        public Criteria andProductNumNotBetween(Integer value1, Integer value2) {
            addCriterion("product_num not between", value1, value2, "productNum");
            return (Criteria) this;
        }

        public Criteria andProductTypeIsNull() {
            addCriterion("product_type is null");
            return (Criteria) this;
        }

        public Criteria andProductTypeIsNotNull() {
            addCriterion("product_type is not null");
            return (Criteria) this;
        }

        public Criteria andProductTypeEqualTo(Long value) {
            addCriterion("product_type =", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeNotEqualTo(Long value) {
            addCriterion("product_type <>", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeGreaterThan(Long value) {
            addCriterion("product_type >", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeGreaterThanOrEqualTo(Long value) {
            addCriterion("product_type >=", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeLessThan(Long value) {
            addCriterion("product_type <", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeLessThanOrEqualTo(Long value) {
            addCriterion("product_type <=", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeIn(List<Long> values) {
            addCriterion("product_type in", values, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeNotIn(List<Long> values) {
            addCriterion("product_type not in", values, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeBetween(Long value1, Long value2) {
            addCriterion("product_type between", value1, value2, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeNotBetween(Long value1, Long value2) {
            addCriterion("product_type not between", value1, value2, "productType");
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

        public Criteria andMemberIdIsNull() {
            addCriterion("member_id is null");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNotNull() {
            addCriterion("member_id is not null");
            return (Criteria) this;
        }

        public Criteria andMemberIdEqualTo(String value) {
            addCriterion("member_id =", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotEqualTo(String value) {
            addCriterion("member_id <>", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThan(String value) {
            addCriterion("member_id >", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThanOrEqualTo(String value) {
            addCriterion("member_id >=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThan(String value) {
            addCriterion("member_id <", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThanOrEqualTo(String value) {
            addCriterion("member_id <=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLike(String value) {
            addCriterion("member_id like", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotLike(String value) {
            addCriterion("member_id not like", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdIn(List<String> values) {
            addCriterion("member_id in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotIn(List<String> values) {
            addCriterion("member_id not in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdBetween(String value1, String value2) {
            addCriterion("member_id between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotBetween(String value1, String value2) {
            addCriterion("member_id not between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberNameIsNull() {
            addCriterion("member_name is null");
            return (Criteria) this;
        }

        public Criteria andMemberNameIsNotNull() {
            addCriterion("member_name is not null");
            return (Criteria) this;
        }

        public Criteria andMemberNameEqualTo(String value) {
            addCriterion("member_name =", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameNotEqualTo(String value) {
            addCriterion("member_name <>", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameGreaterThan(String value) {
            addCriterion("member_name >", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameGreaterThanOrEqualTo(String value) {
            addCriterion("member_name >=", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameLessThan(String value) {
            addCriterion("member_name <", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameLessThanOrEqualTo(String value) {
            addCriterion("member_name <=", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameLike(String value) {
            addCriterion("member_name like", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameNotLike(String value) {
            addCriterion("member_name not like", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameIn(List<String> values) {
            addCriterion("member_name in", values, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameNotIn(List<String> values) {
            addCriterion("member_name not in", values, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameBetween(String value1, String value2) {
            addCriterion("member_name between", value1, value2, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameNotBetween(String value1, String value2) {
            addCriterion("member_name not between", value1, value2, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberServiceAccountIsNull() {
            addCriterion("member_service_account is null");
            return (Criteria) this;
        }

        public Criteria andMemberServiceAccountIsNotNull() {
            addCriterion("member_service_account is not null");
            return (Criteria) this;
        }

        public Criteria andMemberServiceAccountEqualTo(String value) {
            addCriterion("member_service_account =", value, "memberServiceAccount");
            return (Criteria) this;
        }

        public Criteria andMemberServiceAccountNotEqualTo(String value) {
            addCriterion("member_service_account <>", value, "memberServiceAccount");
            return (Criteria) this;
        }

        public Criteria andMemberServiceAccountGreaterThan(String value) {
            addCriterion("member_service_account >", value, "memberServiceAccount");
            return (Criteria) this;
        }

        public Criteria andMemberServiceAccountGreaterThanOrEqualTo(String value) {
            addCriterion("member_service_account >=", value, "memberServiceAccount");
            return (Criteria) this;
        }

        public Criteria andMemberServiceAccountLessThan(String value) {
            addCriterion("member_service_account <", value, "memberServiceAccount");
            return (Criteria) this;
        }

        public Criteria andMemberServiceAccountLessThanOrEqualTo(String value) {
            addCriterion("member_service_account <=", value, "memberServiceAccount");
            return (Criteria) this;
        }

        public Criteria andMemberServiceAccountLike(String value) {
            addCriterion("member_service_account like", value, "memberServiceAccount");
            return (Criteria) this;
        }

        public Criteria andMemberServiceAccountNotLike(String value) {
            addCriterion("member_service_account not like", value, "memberServiceAccount");
            return (Criteria) this;
        }

        public Criteria andMemberServiceAccountIn(List<String> values) {
            addCriterion("member_service_account in", values, "memberServiceAccount");
            return (Criteria) this;
        }

        public Criteria andMemberServiceAccountNotIn(List<String> values) {
            addCriterion("member_service_account not in", values, "memberServiceAccount");
            return (Criteria) this;
        }

        public Criteria andMemberServiceAccountBetween(String value1, String value2) {
            addCriterion("member_service_account between", value1, value2, "memberServiceAccount");
            return (Criteria) this;
        }

        public Criteria andMemberServiceAccountNotBetween(String value1, String value2) {
            addCriterion("member_service_account not between", value1, value2, "memberServiceAccount");
            return (Criteria) this;
        }

        public Criteria andSubmitDateIsNull() {
            addCriterion("submit_date is null");
            return (Criteria) this;
        }

        public Criteria andSubmitDateIsNotNull() {
            addCriterion("submit_date is not null");
            return (Criteria) this;
        }

        public Criteria andSubmitDateEqualTo(Date value) {
            addCriterion("submit_date =", value, "submitDate");
            return (Criteria) this;
        }

        public Criteria andSubmitDateNotEqualTo(Date value) {
            addCriterion("submit_date <>", value, "submitDate");
            return (Criteria) this;
        }

        public Criteria andSubmitDateGreaterThan(Date value) {
            addCriterion("submit_date >", value, "submitDate");
            return (Criteria) this;
        }

        public Criteria andSubmitDateGreaterThanOrEqualTo(Date value) {
            addCriterion("submit_date >=", value, "submitDate");
            return (Criteria) this;
        }

        public Criteria andSubmitDateLessThan(Date value) {
            addCriterion("submit_date <", value, "submitDate");
            return (Criteria) this;
        }

        public Criteria andSubmitDateLessThanOrEqualTo(Date value) {
            addCriterion("submit_date <=", value, "submitDate");
            return (Criteria) this;
        }

        public Criteria andSubmitDateIn(List<Date> values) {
            addCriterion("submit_date in", values, "submitDate");
            return (Criteria) this;
        }

        public Criteria andSubmitDateNotIn(List<Date> values) {
            addCriterion("submit_date not in", values, "submitDate");
            return (Criteria) this;
        }

        public Criteria andSubmitDateBetween(Date value1, Date value2) {
            addCriterion("submit_date between", value1, value2, "submitDate");
            return (Criteria) this;
        }

        public Criteria andSubmitDateNotBetween(Date value1, Date value2) {
            addCriterion("submit_date not between", value1, value2, "submitDate");
            return (Criteria) this;
        }

        public Criteria andServiceCalledDateIsNull() {
            addCriterion("service_called_date is null");
            return (Criteria) this;
        }

        public Criteria andServiceCalledDateIsNotNull() {
            addCriterion("service_called_date is not null");
            return (Criteria) this;
        }

        public Criteria andServiceCalledDateEqualTo(Date value) {
            addCriterion("service_called_date =", value, "serviceCalledDate");
            return (Criteria) this;
        }

        public Criteria andServiceCalledDateNotEqualTo(Date value) {
            addCriterion("service_called_date <>", value, "serviceCalledDate");
            return (Criteria) this;
        }

        public Criteria andServiceCalledDateGreaterThan(Date value) {
            addCriterion("service_called_date >", value, "serviceCalledDate");
            return (Criteria) this;
        }

        public Criteria andServiceCalledDateGreaterThanOrEqualTo(Date value) {
            addCriterion("service_called_date >=", value, "serviceCalledDate");
            return (Criteria) this;
        }

        public Criteria andServiceCalledDateLessThan(Date value) {
            addCriterion("service_called_date <", value, "serviceCalledDate");
            return (Criteria) this;
        }

        public Criteria andServiceCalledDateLessThanOrEqualTo(Date value) {
            addCriterion("service_called_date <=", value, "serviceCalledDate");
            return (Criteria) this;
        }

        public Criteria andServiceCalledDateIn(List<Date> values) {
            addCriterion("service_called_date in", values, "serviceCalledDate");
            return (Criteria) this;
        }

        public Criteria andServiceCalledDateNotIn(List<Date> values) {
            addCriterion("service_called_date not in", values, "serviceCalledDate");
            return (Criteria) this;
        }

        public Criteria andServiceCalledDateBetween(Date value1, Date value2) {
            addCriterion("service_called_date between", value1, value2, "serviceCalledDate");
            return (Criteria) this;
        }

        public Criteria andServiceCalledDateNotBetween(Date value1, Date value2) {
            addCriterion("service_called_date not between", value1, value2, "serviceCalledDate");
            return (Criteria) this;
        }

        public Criteria andStartCallbackDateIsNull() {
            addCriterion("start_callback_date is null");
            return (Criteria) this;
        }

        public Criteria andStartCallbackDateIsNotNull() {
            addCriterion("start_callback_date is not null");
            return (Criteria) this;
        }

        public Criteria andStartCallbackDateEqualTo(Date value) {
            addCriterion("start_callback_date =", value, "startCallbackDate");
            return (Criteria) this;
        }

        public Criteria andStartCallbackDateNotEqualTo(Date value) {
            addCriterion("start_callback_date <>", value, "startCallbackDate");
            return (Criteria) this;
        }

        public Criteria andStartCallbackDateGreaterThan(Date value) {
            addCriterion("start_callback_date >", value, "startCallbackDate");
            return (Criteria) this;
        }

        public Criteria andStartCallbackDateGreaterThanOrEqualTo(Date value) {
            addCriterion("start_callback_date >=", value, "startCallbackDate");
            return (Criteria) this;
        }

        public Criteria andStartCallbackDateLessThan(Date value) {
            addCriterion("start_callback_date <", value, "startCallbackDate");
            return (Criteria) this;
        }

        public Criteria andStartCallbackDateLessThanOrEqualTo(Date value) {
            addCriterion("start_callback_date <=", value, "startCallbackDate");
            return (Criteria) this;
        }

        public Criteria andStartCallbackDateIn(List<Date> values) {
            addCriterion("start_callback_date in", values, "startCallbackDate");
            return (Criteria) this;
        }

        public Criteria andStartCallbackDateNotIn(List<Date> values) {
            addCriterion("start_callback_date not in", values, "startCallbackDate");
            return (Criteria) this;
        }

        public Criteria andStartCallbackDateBetween(Date value1, Date value2) {
            addCriterion("start_callback_date between", value1, value2, "startCallbackDate");
            return (Criteria) this;
        }

        public Criteria andStartCallbackDateNotBetween(Date value1, Date value2) {
            addCriterion("start_callback_date not between", value1, value2, "startCallbackDate");
            return (Criteria) this;
        }

        public Criteria andLastCallbackDateIsNull() {
            addCriterion("last_callback_date is null");
            return (Criteria) this;
        }

        public Criteria andLastCallbackDateIsNotNull() {
            addCriterion("last_callback_date is not null");
            return (Criteria) this;
        }

        public Criteria andLastCallbackDateEqualTo(Date value) {
            addCriterion("last_callback_date =", value, "lastCallbackDate");
            return (Criteria) this;
        }

        public Criteria andLastCallbackDateNotEqualTo(Date value) {
            addCriterion("last_callback_date <>", value, "lastCallbackDate");
            return (Criteria) this;
        }

        public Criteria andLastCallbackDateGreaterThan(Date value) {
            addCriterion("last_callback_date >", value, "lastCallbackDate");
            return (Criteria) this;
        }

        public Criteria andLastCallbackDateGreaterThanOrEqualTo(Date value) {
            addCriterion("last_callback_date >=", value, "lastCallbackDate");
            return (Criteria) this;
        }

        public Criteria andLastCallbackDateLessThan(Date value) {
            addCriterion("last_callback_date <", value, "lastCallbackDate");
            return (Criteria) this;
        }

        public Criteria andLastCallbackDateLessThanOrEqualTo(Date value) {
            addCriterion("last_callback_date <=", value, "lastCallbackDate");
            return (Criteria) this;
        }

        public Criteria andLastCallbackDateIn(List<Date> values) {
            addCriterion("last_callback_date in", values, "lastCallbackDate");
            return (Criteria) this;
        }

        public Criteria andLastCallbackDateNotIn(List<Date> values) {
            addCriterion("last_callback_date not in", values, "lastCallbackDate");
            return (Criteria) this;
        }

        public Criteria andLastCallbackDateBetween(Date value1, Date value2) {
            addCriterion("last_callback_date between", value1, value2, "lastCallbackDate");
            return (Criteria) this;
        }

        public Criteria andLastCallbackDateNotBetween(Date value1, Date value2) {
            addCriterion("last_callback_date not between", value1, value2, "lastCallbackDate");
            return (Criteria) this;
        }

        public Criteria andPayTaxIsNull() {
            addCriterion("pay_tax is null");
            return (Criteria) this;
        }

        public Criteria andPayTaxIsNotNull() {
            addCriterion("pay_tax is not null");
            return (Criteria) this;
        }

        public Criteria andPayTaxEqualTo(Double value) {
            addCriterion("pay_tax =", value, "payTax");
            return (Criteria) this;
        }

        public Criteria andPayTaxNotEqualTo(Double value) {
            addCriterion("pay_tax <>", value, "payTax");
            return (Criteria) this;
        }

        public Criteria andPayTaxGreaterThan(Double value) {
            addCriterion("pay_tax >", value, "payTax");
            return (Criteria) this;
        }

        public Criteria andPayTaxGreaterThanOrEqualTo(Double value) {
            addCriterion("pay_tax >=", value, "payTax");
            return (Criteria) this;
        }

        public Criteria andPayTaxLessThan(Double value) {
            addCriterion("pay_tax <", value, "payTax");
            return (Criteria) this;
        }

        public Criteria andPayTaxLessThanOrEqualTo(Double value) {
            addCriterion("pay_tax <=", value, "payTax");
            return (Criteria) this;
        }

        public Criteria andPayTaxIn(List<Double> values) {
            addCriterion("pay_tax in", values, "payTax");
            return (Criteria) this;
        }

        public Criteria andPayTaxNotIn(List<Double> values) {
            addCriterion("pay_tax not in", values, "payTax");
            return (Criteria) this;
        }

        public Criteria andPayTaxBetween(Double value1, Double value2) {
            addCriterion("pay_tax between", value1, value2, "payTax");
            return (Criteria) this;
        }

        public Criteria andPayTaxNotBetween(Double value1, Double value2) {
            addCriterion("pay_tax not between", value1, value2, "payTax");
            return (Criteria) this;
        }

        public Criteria andPayAmountIsNull() {
            addCriterion("pay_amount is null");
            return (Criteria) this;
        }

        public Criteria andPayAmountIsNotNull() {
            addCriterion("pay_amount is not null");
            return (Criteria) this;
        }

        public Criteria andPayAmountEqualTo(Long value) {
            addCriterion("pay_amount =", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountNotEqualTo(Long value) {
            addCriterion("pay_amount <>", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountGreaterThan(Long value) {
            addCriterion("pay_amount >", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("pay_amount >=", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountLessThan(Long value) {
            addCriterion("pay_amount <", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountLessThanOrEqualTo(Long value) {
            addCriterion("pay_amount <=", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountIn(List<Long> values) {
            addCriterion("pay_amount in", values, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountNotIn(List<Long> values) {
            addCriterion("pay_amount not in", values, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountBetween(Long value1, Long value2) {
            addCriterion("pay_amount between", value1, value2, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountNotBetween(Long value1, Long value2) {
            addCriterion("pay_amount not between", value1, value2, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayedAmountIsNull() {
            addCriterion("payed_amount is null");
            return (Criteria) this;
        }

        public Criteria andPayedAmountIsNotNull() {
            addCriterion("payed_amount is not null");
            return (Criteria) this;
        }

        public Criteria andPayedAmountEqualTo(Long value) {
            addCriterion("payed_amount =", value, "payedAmount");
            return (Criteria) this;
        }

        public Criteria andPayedAmountNotEqualTo(Long value) {
            addCriterion("payed_amount <>", value, "payedAmount");
            return (Criteria) this;
        }

        public Criteria andPayedAmountGreaterThan(Long value) {
            addCriterion("payed_amount >", value, "payedAmount");
            return (Criteria) this;
        }

        public Criteria andPayedAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("payed_amount >=", value, "payedAmount");
            return (Criteria) this;
        }

        public Criteria andPayedAmountLessThan(Long value) {
            addCriterion("payed_amount <", value, "payedAmount");
            return (Criteria) this;
        }

        public Criteria andPayedAmountLessThanOrEqualTo(Long value) {
            addCriterion("payed_amount <=", value, "payedAmount");
            return (Criteria) this;
        }

        public Criteria andPayedAmountIn(List<Long> values) {
            addCriterion("payed_amount in", values, "payedAmount");
            return (Criteria) this;
        }

        public Criteria andPayedAmountNotIn(List<Long> values) {
            addCriterion("payed_amount not in", values, "payedAmount");
            return (Criteria) this;
        }

        public Criteria andPayedAmountBetween(Long value1, Long value2) {
            addCriterion("payed_amount between", value1, value2, "payedAmount");
            return (Criteria) this;
        }

        public Criteria andPayedAmountNotBetween(Long value1, Long value2) {
            addCriterion("payed_amount not between", value1, value2, "payedAmount");
            return (Criteria) this;
        }

        public Criteria andInvoicedAmountIsNull() {
            addCriterion("invoiced_amount is null");
            return (Criteria) this;
        }

        public Criteria andInvoicedAmountIsNotNull() {
            addCriterion("invoiced_amount is not null");
            return (Criteria) this;
        }

        public Criteria andInvoicedAmountEqualTo(Long value) {
            addCriterion("invoiced_amount =", value, "invoicedAmount");
            return (Criteria) this;
        }

        public Criteria andInvoicedAmountNotEqualTo(Long value) {
            addCriterion("invoiced_amount <>", value, "invoicedAmount");
            return (Criteria) this;
        }

        public Criteria andInvoicedAmountGreaterThan(Long value) {
            addCriterion("invoiced_amount >", value, "invoicedAmount");
            return (Criteria) this;
        }

        public Criteria andInvoicedAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("invoiced_amount >=", value, "invoicedAmount");
            return (Criteria) this;
        }

        public Criteria andInvoicedAmountLessThan(Long value) {
            addCriterion("invoiced_amount <", value, "invoicedAmount");
            return (Criteria) this;
        }

        public Criteria andInvoicedAmountLessThanOrEqualTo(Long value) {
            addCriterion("invoiced_amount <=", value, "invoicedAmount");
            return (Criteria) this;
        }

        public Criteria andInvoicedAmountIn(List<Long> values) {
            addCriterion("invoiced_amount in", values, "invoicedAmount");
            return (Criteria) this;
        }

        public Criteria andInvoicedAmountNotIn(List<Long> values) {
            addCriterion("invoiced_amount not in", values, "invoicedAmount");
            return (Criteria) this;
        }

        public Criteria andInvoicedAmountBetween(Long value1, Long value2) {
            addCriterion("invoiced_amount between", value1, value2, "invoicedAmount");
            return (Criteria) this;
        }

        public Criteria andInvoicedAmountNotBetween(Long value1, Long value2) {
            addCriterion("invoiced_amount not between", value1, value2, "invoicedAmount");
            return (Criteria) this;
        }

        public Criteria andPayStatusIsNull() {
            addCriterion("pay_status is null");
            return (Criteria) this;
        }

        public Criteria andPayStatusIsNotNull() {
            addCriterion("pay_status is not null");
            return (Criteria) this;
        }

        public Criteria andPayStatusEqualTo(Integer value) {
            addCriterion("pay_status =", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusNotEqualTo(Integer value) {
            addCriterion("pay_status <>", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusGreaterThan(Integer value) {
            addCriterion("pay_status >", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("pay_status >=", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusLessThan(Integer value) {
            addCriterion("pay_status <", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusLessThanOrEqualTo(Integer value) {
            addCriterion("pay_status <=", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusIn(List<Integer> values) {
            addCriterion("pay_status in", values, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusNotIn(List<Integer> values) {
            addCriterion("pay_status not in", values, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusBetween(Integer value1, Integer value2) {
            addCriterion("pay_status between", value1, value2, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("pay_status not between", value1, value2, "payStatus");
            return (Criteria) this;
        }

        public Criteria andCallbackStatusIsNull() {
            addCriterion("callback_status is null");
            return (Criteria) this;
        }

        public Criteria andCallbackStatusIsNotNull() {
            addCriterion("callback_status is not null");
            return (Criteria) this;
        }

        public Criteria andCallbackStatusEqualTo(Integer value) {
            addCriterion("callback_status =", value, "callbackStatus");
            return (Criteria) this;
        }

        public Criteria andCallbackStatusNotEqualTo(Integer value) {
            addCriterion("callback_status <>", value, "callbackStatus");
            return (Criteria) this;
        }

        public Criteria andCallbackStatusGreaterThan(Integer value) {
            addCriterion("callback_status >", value, "callbackStatus");
            return (Criteria) this;
        }

        public Criteria andCallbackStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("callback_status >=", value, "callbackStatus");
            return (Criteria) this;
        }

        public Criteria andCallbackStatusLessThan(Integer value) {
            addCriterion("callback_status <", value, "callbackStatus");
            return (Criteria) this;
        }

        public Criteria andCallbackStatusLessThanOrEqualTo(Integer value) {
            addCriterion("callback_status <=", value, "callbackStatus");
            return (Criteria) this;
        }

        public Criteria andCallbackStatusIn(List<Integer> values) {
            addCriterion("callback_status in", values, "callbackStatus");
            return (Criteria) this;
        }

        public Criteria andCallbackStatusNotIn(List<Integer> values) {
            addCriterion("callback_status not in", values, "callbackStatus");
            return (Criteria) this;
        }

        public Criteria andCallbackStatusBetween(Integer value1, Integer value2) {
            addCriterion("callback_status between", value1, value2, "callbackStatus");
            return (Criteria) this;
        }

        public Criteria andCallbackStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("callback_status not between", value1, value2, "callbackStatus");
            return (Criteria) this;
        }

        public Criteria andExtendParamIsNull() {
            addCriterion("extend_param is null");
            return (Criteria) this;
        }

        public Criteria andExtendParamIsNotNull() {
            addCriterion("extend_param is not null");
            return (Criteria) this;
        }

        public Criteria andExtendParamEqualTo(String value) {
            addCriterion("extend_param =", value, "extendParam");
            return (Criteria) this;
        }

        public Criteria andExtendParamNotEqualTo(String value) {
            addCriterion("extend_param <>", value, "extendParam");
            return (Criteria) this;
        }

        public Criteria andExtendParamGreaterThan(String value) {
            addCriterion("extend_param >", value, "extendParam");
            return (Criteria) this;
        }

        public Criteria andExtendParamGreaterThanOrEqualTo(String value) {
            addCriterion("extend_param >=", value, "extendParam");
            return (Criteria) this;
        }

        public Criteria andExtendParamLessThan(String value) {
            addCriterion("extend_param <", value, "extendParam");
            return (Criteria) this;
        }

        public Criteria andExtendParamLessThanOrEqualTo(String value) {
            addCriterion("extend_param <=", value, "extendParam");
            return (Criteria) this;
        }

        public Criteria andExtendParamLike(String value) {
            addCriterion("extend_param like", value, "extendParam");
            return (Criteria) this;
        }

        public Criteria andExtendParamNotLike(String value) {
            addCriterion("extend_param not like", value, "extendParam");
            return (Criteria) this;
        }

        public Criteria andExtendParamIn(List<String> values) {
            addCriterion("extend_param in", values, "extendParam");
            return (Criteria) this;
        }

        public Criteria andExtendParamNotIn(List<String> values) {
            addCriterion("extend_param not in", values, "extendParam");
            return (Criteria) this;
        }

        public Criteria andExtendParamBetween(String value1, String value2) {
            addCriterion("extend_param between", value1, value2, "extendParam");
            return (Criteria) this;
        }

        public Criteria andExtendParamNotBetween(String value1, String value2) {
            addCriterion("extend_param not between", value1, value2, "extendParam");
            return (Criteria) this;
        }

        public Criteria andServiceInvoicedAmountIsNull() {
            addCriterion("service_invoiced_amount is null");
            return (Criteria) this;
        }

        public Criteria andServiceInvoicedAmountIsNotNull() {
            addCriterion("service_invoiced_amount is not null");
            return (Criteria) this;
        }

        public Criteria andServiceInvoicedAmountEqualTo(Long value) {
            addCriterion("service_invoiced_amount =", value, "serviceInvoicedAmount");
            return (Criteria) this;
        }

        public Criteria andServiceInvoicedAmountNotEqualTo(Long value) {
            addCriterion("service_invoiced_amount <>", value, "serviceInvoicedAmount");
            return (Criteria) this;
        }

        public Criteria andServiceInvoicedAmountGreaterThan(Long value) {
            addCriterion("service_invoiced_amount >", value, "serviceInvoicedAmount");
            return (Criteria) this;
        }

        public Criteria andServiceInvoicedAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("service_invoiced_amount >=", value, "serviceInvoicedAmount");
            return (Criteria) this;
        }

        public Criteria andServiceInvoicedAmountLessThan(Long value) {
            addCriterion("service_invoiced_amount <", value, "serviceInvoicedAmount");
            return (Criteria) this;
        }

        public Criteria andServiceInvoicedAmountLessThanOrEqualTo(Long value) {
            addCriterion("service_invoiced_amount <=", value, "serviceInvoicedAmount");
            return (Criteria) this;
        }

        public Criteria andServiceInvoicedAmountIn(List<Long> values) {
            addCriterion("service_invoiced_amount in", values, "serviceInvoicedAmount");
            return (Criteria) this;
        }

        public Criteria andServiceInvoicedAmountNotIn(List<Long> values) {
            addCriterion("service_invoiced_amount not in", values, "serviceInvoicedAmount");
            return (Criteria) this;
        }

        public Criteria andServiceInvoicedAmountBetween(Long value1, Long value2) {
            addCriterion("service_invoiced_amount between", value1, value2, "serviceInvoicedAmount");
            return (Criteria) this;
        }

        public Criteria andServiceInvoicedAmountNotBetween(Long value1, Long value2) {
            addCriterion("service_invoiced_amount not between", value1, value2, "serviceInvoicedAmount");
            return (Criteria) this;
        }

        public Criteria andCallbackNumIsNull() {
            addCriterion("callback_num is null");
            return (Criteria) this;
        }

        public Criteria andCallbackNumIsNotNull() {
            addCriterion("callback_num is not null");
            return (Criteria) this;
        }

        public Criteria andCallbackNumEqualTo(Integer value) {
            addCriterion("callback_num =", value, "callbackNum");
            return (Criteria) this;
        }

        public Criteria andCallbackNumNotEqualTo(Integer value) {
            addCriterion("callback_num <>", value, "callbackNum");
            return (Criteria) this;
        }

        public Criteria andCallbackNumGreaterThan(Integer value) {
            addCriterion("callback_num >", value, "callbackNum");
            return (Criteria) this;
        }

        public Criteria andCallbackNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("callback_num >=", value, "callbackNum");
            return (Criteria) this;
        }

        public Criteria andCallbackNumLessThan(Integer value) {
            addCriterion("callback_num <", value, "callbackNum");
            return (Criteria) this;
        }

        public Criteria andCallbackNumLessThanOrEqualTo(Integer value) {
            addCriterion("callback_num <=", value, "callbackNum");
            return (Criteria) this;
        }

        public Criteria andCallbackNumIn(List<Integer> values) {
            addCriterion("callback_num in", values, "callbackNum");
            return (Criteria) this;
        }

        public Criteria andCallbackNumNotIn(List<Integer> values) {
            addCriterion("callback_num not in", values, "callbackNum");
            return (Criteria) this;
        }

        public Criteria andCallbackNumBetween(Integer value1, Integer value2) {
            addCriterion("callback_num between", value1, value2, "callbackNum");
            return (Criteria) this;
        }

        public Criteria andCallbackNumNotBetween(Integer value1, Integer value2) {
            addCriterion("callback_num not between", value1, value2, "callbackNum");
            return (Criteria) this;
        }

        public Criteria andPayServiceAccountLikeInsensitive(String value) {
            addCriterion("upper(pay_service_account) like", value.toUpperCase(), "payServiceAccount");
            return (Criteria) this;
        }

        public Criteria andPayServiceOrderIdLikeInsensitive(String value) {
            addCriterion("upper(pay_service_order_id) like", value.toUpperCase(), "payServiceOrderId");
            return (Criteria) this;
        }

        public Criteria andProductOrderIdLikeInsensitive(String value) {
            addCriterion("upper(product_order_id) like", value.toUpperCase(), "productOrderId");
            return (Criteria) this;
        }

        public Criteria andProductNameLikeInsensitive(String value) {
            addCriterion("upper(product_name) like", value.toUpperCase(), "productName");
            return (Criteria) this;
        }

        public Criteria andMemberIdLikeInsensitive(String value) {
            addCriterion("upper(member_id) like", value.toUpperCase(), "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberNameLikeInsensitive(String value) {
            addCriterion("upper(member_name) like", value.toUpperCase(), "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberServiceAccountLikeInsensitive(String value) {
            addCriterion("upper(member_service_account) like", value.toUpperCase(), "memberServiceAccount");
            return (Criteria) this;
        }

        public Criteria andExtendParamLikeInsensitive(String value) {
            addCriterion("upper(extend_param) like", value.toUpperCase(), "extendParam");
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
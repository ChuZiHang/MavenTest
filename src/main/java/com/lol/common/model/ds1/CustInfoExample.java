package com.lol.common.model.ds1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lol.common.plugin.Page;

public class CustInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Page page;

    private String dialect = "mysql";

    public CustInfoExample() {
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

        public Criteria andCustNameIsNull() {
            addCriterion("cust_name is null");
            return (Criteria) this;
        }

        public Criteria andCustNameIsNotNull() {
            addCriterion("cust_name is not null");
            return (Criteria) this;
        }

        public Criteria andCustNameEqualTo(String value) {
            addCriterion("cust_name =", value, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameNotEqualTo(String value) {
            addCriterion("cust_name <>", value, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameGreaterThan(String value) {
            addCriterion("cust_name >", value, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameGreaterThanOrEqualTo(String value) {
            addCriterion("cust_name >=", value, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameLessThan(String value) {
            addCriterion("cust_name <", value, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameLessThanOrEqualTo(String value) {
            addCriterion("cust_name <=", value, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameLike(String value) {
            addCriterion("cust_name like", value, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameNotLike(String value) {
            addCriterion("cust_name not like", value, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameIn(List<String> values) {
            addCriterion("cust_name in", values, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameNotIn(List<String> values) {
            addCriterion("cust_name not in", values, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameBetween(String value1, String value2) {
            addCriterion("cust_name between", value1, value2, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameNotBetween(String value1, String value2) {
            addCriterion("cust_name not between", value1, value2, "custName");
            return (Criteria) this;
        }

        public Criteria andSignimgIsNull() {
            addCriterion("signimg is null");
            return (Criteria) this;
        }

        public Criteria andSignimgIsNotNull() {
            addCriterion("signimg is not null");
            return (Criteria) this;
        }

        public Criteria andSignimgEqualTo(String value) {
            addCriterion("signimg =", value, "signimg");
            return (Criteria) this;
        }

        public Criteria andSignimgNotEqualTo(String value) {
            addCriterion("signimg <>", value, "signimg");
            return (Criteria) this;
        }

        public Criteria andSignimgGreaterThan(String value) {
            addCriterion("signimg >", value, "signimg");
            return (Criteria) this;
        }

        public Criteria andSignimgGreaterThanOrEqualTo(String value) {
            addCriterion("signimg >=", value, "signimg");
            return (Criteria) this;
        }

        public Criteria andSignimgLessThan(String value) {
            addCriterion("signimg <", value, "signimg");
            return (Criteria) this;
        }

        public Criteria andSignimgLessThanOrEqualTo(String value) {
            addCriterion("signimg <=", value, "signimg");
            return (Criteria) this;
        }

        public Criteria andSignimgLike(String value) {
            addCriterion("signimg like", value, "signimg");
            return (Criteria) this;
        }

        public Criteria andSignimgNotLike(String value) {
            addCriterion("signimg not like", value, "signimg");
            return (Criteria) this;
        }

        public Criteria andSignimgIn(List<String> values) {
            addCriterion("signimg in", values, "signimg");
            return (Criteria) this;
        }

        public Criteria andSignimgNotIn(List<String> values) {
            addCriterion("signimg not in", values, "signimg");
            return (Criteria) this;
        }

        public Criteria andSignimgBetween(String value1, String value2) {
            addCriterion("signimg between", value1, value2, "signimg");
            return (Criteria) this;
        }

        public Criteria andSignimgNotBetween(String value1, String value2) {
            addCriterion("signimg not between", value1, value2, "signimg");
            return (Criteria) this;
        }

        public Criteria andCustTypeIsNull() {
            addCriterion("cust_type is null");
            return (Criteria) this;
        }

        public Criteria andCustTypeIsNotNull() {
            addCriterion("cust_type is not null");
            return (Criteria) this;
        }

        public Criteria andCustTypeEqualTo(Integer value) {
            addCriterion("cust_type =", value, "custType");
            return (Criteria) this;
        }

        public Criteria andCustTypeNotEqualTo(Integer value) {
            addCriterion("cust_type <>", value, "custType");
            return (Criteria) this;
        }

        public Criteria andCustTypeGreaterThan(Integer value) {
            addCriterion("cust_type >", value, "custType");
            return (Criteria) this;
        }

        public Criteria andCustTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("cust_type >=", value, "custType");
            return (Criteria) this;
        }

        public Criteria andCustTypeLessThan(Integer value) {
            addCriterion("cust_type <", value, "custType");
            return (Criteria) this;
        }

        public Criteria andCustTypeLessThanOrEqualTo(Integer value) {
            addCriterion("cust_type <=", value, "custType");
            return (Criteria) this;
        }

        public Criteria andCustTypeIn(List<Integer> values) {
            addCriterion("cust_type in", values, "custType");
            return (Criteria) this;
        }

        public Criteria andCustTypeNotIn(List<Integer> values) {
            addCriterion("cust_type not in", values, "custType");
            return (Criteria) this;
        }

        public Criteria andCustTypeBetween(Integer value1, Integer value2) {
            addCriterion("cust_type between", value1, value2, "custType");
            return (Criteria) this;
        }

        public Criteria andCustTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("cust_type not between", value1, value2, "custType");
            return (Criteria) this;
        }

        public Criteria andAreaIdIsNull() {
            addCriterion("area_id is null");
            return (Criteria) this;
        }

        public Criteria andAreaIdIsNotNull() {
            addCriterion("area_id is not null");
            return (Criteria) this;
        }

        public Criteria andAreaIdEqualTo(Integer value) {
            addCriterion("area_id =", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotEqualTo(Integer value) {
            addCriterion("area_id <>", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdGreaterThan(Integer value) {
            addCriterion("area_id >", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("area_id >=", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdLessThan(Integer value) {
            addCriterion("area_id <", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdLessThanOrEqualTo(Integer value) {
            addCriterion("area_id <=", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdIn(List<Integer> values) {
            addCriterion("area_id in", values, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotIn(List<Integer> values) {
            addCriterion("area_id not in", values, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdBetween(Integer value1, Integer value2) {
            addCriterion("area_id between", value1, value2, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotBetween(Integer value1, Integer value2) {
            addCriterion("area_id not between", value1, value2, "areaId");
            return (Criteria) this;
        }

        public Criteria andLinkManIsNull() {
            addCriterion("link_man is null");
            return (Criteria) this;
        }

        public Criteria andLinkManIsNotNull() {
            addCriterion("link_man is not null");
            return (Criteria) this;
        }

        public Criteria andLinkManEqualTo(String value) {
            addCriterion("link_man =", value, "linkMan");
            return (Criteria) this;
        }

        public Criteria andLinkManNotEqualTo(String value) {
            addCriterion("link_man <>", value, "linkMan");
            return (Criteria) this;
        }

        public Criteria andLinkManGreaterThan(String value) {
            addCriterion("link_man >", value, "linkMan");
            return (Criteria) this;
        }

        public Criteria andLinkManGreaterThanOrEqualTo(String value) {
            addCriterion("link_man >=", value, "linkMan");
            return (Criteria) this;
        }

        public Criteria andLinkManLessThan(String value) {
            addCriterion("link_man <", value, "linkMan");
            return (Criteria) this;
        }

        public Criteria andLinkManLessThanOrEqualTo(String value) {
            addCriterion("link_man <=", value, "linkMan");
            return (Criteria) this;
        }

        public Criteria andLinkManLike(String value) {
            addCriterion("link_man like", value, "linkMan");
            return (Criteria) this;
        }

        public Criteria andLinkManNotLike(String value) {
            addCriterion("link_man not like", value, "linkMan");
            return (Criteria) this;
        }

        public Criteria andLinkManIn(List<String> values) {
            addCriterion("link_man in", values, "linkMan");
            return (Criteria) this;
        }

        public Criteria andLinkManNotIn(List<String> values) {
            addCriterion("link_man not in", values, "linkMan");
            return (Criteria) this;
        }

        public Criteria andLinkManBetween(String value1, String value2) {
            addCriterion("link_man between", value1, value2, "linkMan");
            return (Criteria) this;
        }

        public Criteria andLinkManNotBetween(String value1, String value2) {
            addCriterion("link_man not between", value1, value2, "linkMan");
            return (Criteria) this;
        }

        public Criteria andCustDescIsNull() {
            addCriterion("cust_desc is null");
            return (Criteria) this;
        }

        public Criteria andCustDescIsNotNull() {
            addCriterion("cust_desc is not null");
            return (Criteria) this;
        }

        public Criteria andCustDescEqualTo(String value) {
            addCriterion("cust_desc =", value, "custDesc");
            return (Criteria) this;
        }

        public Criteria andCustDescNotEqualTo(String value) {
            addCriterion("cust_desc <>", value, "custDesc");
            return (Criteria) this;
        }

        public Criteria andCustDescGreaterThan(String value) {
            addCriterion("cust_desc >", value, "custDesc");
            return (Criteria) this;
        }

        public Criteria andCustDescGreaterThanOrEqualTo(String value) {
            addCriterion("cust_desc >=", value, "custDesc");
            return (Criteria) this;
        }

        public Criteria andCustDescLessThan(String value) {
            addCriterion("cust_desc <", value, "custDesc");
            return (Criteria) this;
        }

        public Criteria andCustDescLessThanOrEqualTo(String value) {
            addCriterion("cust_desc <=", value, "custDesc");
            return (Criteria) this;
        }

        public Criteria andCustDescLike(String value) {
            addCriterion("cust_desc like", value, "custDesc");
            return (Criteria) this;
        }

        public Criteria andCustDescNotLike(String value) {
            addCriterion("cust_desc not like", value, "custDesc");
            return (Criteria) this;
        }

        public Criteria andCustDescIn(List<String> values) {
            addCriterion("cust_desc in", values, "custDesc");
            return (Criteria) this;
        }

        public Criteria andCustDescNotIn(List<String> values) {
            addCriterion("cust_desc not in", values, "custDesc");
            return (Criteria) this;
        }

        public Criteria andCustDescBetween(String value1, String value2) {
            addCriterion("cust_desc between", value1, value2, "custDesc");
            return (Criteria) this;
        }

        public Criteria andCustDescNotBetween(String value1, String value2) {
            addCriterion("cust_desc not between", value1, value2, "custDesc");
            return (Criteria) this;
        }

        public Criteria andLinkMobileIsNull() {
            addCriterion("link_mobile is null");
            return (Criteria) this;
        }

        public Criteria andLinkMobileIsNotNull() {
            addCriterion("link_mobile is not null");
            return (Criteria) this;
        }

        public Criteria andLinkMobileEqualTo(String value) {
            addCriterion("link_mobile =", value, "linkMobile");
            return (Criteria) this;
        }

        public Criteria andLinkMobileNotEqualTo(String value) {
            addCriterion("link_mobile <>", value, "linkMobile");
            return (Criteria) this;
        }

        public Criteria andLinkMobileGreaterThan(String value) {
            addCriterion("link_mobile >", value, "linkMobile");
            return (Criteria) this;
        }

        public Criteria andLinkMobileGreaterThanOrEqualTo(String value) {
            addCriterion("link_mobile >=", value, "linkMobile");
            return (Criteria) this;
        }

        public Criteria andLinkMobileLessThan(String value) {
            addCriterion("link_mobile <", value, "linkMobile");
            return (Criteria) this;
        }

        public Criteria andLinkMobileLessThanOrEqualTo(String value) {
            addCriterion("link_mobile <=", value, "linkMobile");
            return (Criteria) this;
        }

        public Criteria andLinkMobileLike(String value) {
            addCriterion("link_mobile like", value, "linkMobile");
            return (Criteria) this;
        }

        public Criteria andLinkMobileNotLike(String value) {
            addCriterion("link_mobile not like", value, "linkMobile");
            return (Criteria) this;
        }

        public Criteria andLinkMobileIn(List<String> values) {
            addCriterion("link_mobile in", values, "linkMobile");
            return (Criteria) this;
        }

        public Criteria andLinkMobileNotIn(List<String> values) {
            addCriterion("link_mobile not in", values, "linkMobile");
            return (Criteria) this;
        }

        public Criteria andLinkMobileBetween(String value1, String value2) {
            addCriterion("link_mobile between", value1, value2, "linkMobile");
            return (Criteria) this;
        }

        public Criteria andLinkMobileNotBetween(String value1, String value2) {
            addCriterion("link_mobile not between", value1, value2, "linkMobile");
            return (Criteria) this;
        }

        public Criteria andLinkQqIsNull() {
            addCriterion("link_qq is null");
            return (Criteria) this;
        }

        public Criteria andLinkQqIsNotNull() {
            addCriterion("link_qq is not null");
            return (Criteria) this;
        }

        public Criteria andLinkQqEqualTo(String value) {
            addCriterion("link_qq =", value, "linkQq");
            return (Criteria) this;
        }

        public Criteria andLinkQqNotEqualTo(String value) {
            addCriterion("link_qq <>", value, "linkQq");
            return (Criteria) this;
        }

        public Criteria andLinkQqGreaterThan(String value) {
            addCriterion("link_qq >", value, "linkQq");
            return (Criteria) this;
        }

        public Criteria andLinkQqGreaterThanOrEqualTo(String value) {
            addCriterion("link_qq >=", value, "linkQq");
            return (Criteria) this;
        }

        public Criteria andLinkQqLessThan(String value) {
            addCriterion("link_qq <", value, "linkQq");
            return (Criteria) this;
        }

        public Criteria andLinkQqLessThanOrEqualTo(String value) {
            addCriterion("link_qq <=", value, "linkQq");
            return (Criteria) this;
        }

        public Criteria andLinkQqLike(String value) {
            addCriterion("link_qq like", value, "linkQq");
            return (Criteria) this;
        }

        public Criteria andLinkQqNotLike(String value) {
            addCriterion("link_qq not like", value, "linkQq");
            return (Criteria) this;
        }

        public Criteria andLinkQqIn(List<String> values) {
            addCriterion("link_qq in", values, "linkQq");
            return (Criteria) this;
        }

        public Criteria andLinkQqNotIn(List<String> values) {
            addCriterion("link_qq not in", values, "linkQq");
            return (Criteria) this;
        }

        public Criteria andLinkQqBetween(String value1, String value2) {
            addCriterion("link_qq between", value1, value2, "linkQq");
            return (Criteria) this;
        }

        public Criteria andLinkQqNotBetween(String value1, String value2) {
            addCriterion("link_qq not between", value1, value2, "linkQq");
            return (Criteria) this;
        }

        public Criteria andLinkEmailIsNull() {
            addCriterion("link_email is null");
            return (Criteria) this;
        }

        public Criteria andLinkEmailIsNotNull() {
            addCriterion("link_email is not null");
            return (Criteria) this;
        }

        public Criteria andLinkEmailEqualTo(String value) {
            addCriterion("link_email =", value, "linkEmail");
            return (Criteria) this;
        }

        public Criteria andLinkEmailNotEqualTo(String value) {
            addCriterion("link_email <>", value, "linkEmail");
            return (Criteria) this;
        }

        public Criteria andLinkEmailGreaterThan(String value) {
            addCriterion("link_email >", value, "linkEmail");
            return (Criteria) this;
        }

        public Criteria andLinkEmailGreaterThanOrEqualTo(String value) {
            addCriterion("link_email >=", value, "linkEmail");
            return (Criteria) this;
        }

        public Criteria andLinkEmailLessThan(String value) {
            addCriterion("link_email <", value, "linkEmail");
            return (Criteria) this;
        }

        public Criteria andLinkEmailLessThanOrEqualTo(String value) {
            addCriterion("link_email <=", value, "linkEmail");
            return (Criteria) this;
        }

        public Criteria andLinkEmailLike(String value) {
            addCriterion("link_email like", value, "linkEmail");
            return (Criteria) this;
        }

        public Criteria andLinkEmailNotLike(String value) {
            addCriterion("link_email not like", value, "linkEmail");
            return (Criteria) this;
        }

        public Criteria andLinkEmailIn(List<String> values) {
            addCriterion("link_email in", values, "linkEmail");
            return (Criteria) this;
        }

        public Criteria andLinkEmailNotIn(List<String> values) {
            addCriterion("link_email not in", values, "linkEmail");
            return (Criteria) this;
        }

        public Criteria andLinkEmailBetween(String value1, String value2) {
            addCriterion("link_email between", value1, value2, "linkEmail");
            return (Criteria) this;
        }

        public Criteria andLinkEmailNotBetween(String value1, String value2) {
            addCriterion("link_email not between", value1, value2, "linkEmail");
            return (Criteria) this;
        }

        public Criteria andLinkFaxIsNull() {
            addCriterion("link_fax is null");
            return (Criteria) this;
        }

        public Criteria andLinkFaxIsNotNull() {
            addCriterion("link_fax is not null");
            return (Criteria) this;
        }

        public Criteria andLinkFaxEqualTo(String value) {
            addCriterion("link_fax =", value, "linkFax");
            return (Criteria) this;
        }

        public Criteria andLinkFaxNotEqualTo(String value) {
            addCriterion("link_fax <>", value, "linkFax");
            return (Criteria) this;
        }

        public Criteria andLinkFaxGreaterThan(String value) {
            addCriterion("link_fax >", value, "linkFax");
            return (Criteria) this;
        }

        public Criteria andLinkFaxGreaterThanOrEqualTo(String value) {
            addCriterion("link_fax >=", value, "linkFax");
            return (Criteria) this;
        }

        public Criteria andLinkFaxLessThan(String value) {
            addCriterion("link_fax <", value, "linkFax");
            return (Criteria) this;
        }

        public Criteria andLinkFaxLessThanOrEqualTo(String value) {
            addCriterion("link_fax <=", value, "linkFax");
            return (Criteria) this;
        }

        public Criteria andLinkFaxLike(String value) {
            addCriterion("link_fax like", value, "linkFax");
            return (Criteria) this;
        }

        public Criteria andLinkFaxNotLike(String value) {
            addCriterion("link_fax not like", value, "linkFax");
            return (Criteria) this;
        }

        public Criteria andLinkFaxIn(List<String> values) {
            addCriterion("link_fax in", values, "linkFax");
            return (Criteria) this;
        }

        public Criteria andLinkFaxNotIn(List<String> values) {
            addCriterion("link_fax not in", values, "linkFax");
            return (Criteria) this;
        }

        public Criteria andLinkFaxBetween(String value1, String value2) {
            addCriterion("link_fax between", value1, value2, "linkFax");
            return (Criteria) this;
        }

        public Criteria andLinkFaxNotBetween(String value1, String value2) {
            addCriterion("link_fax not between", value1, value2, "linkFax");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
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

        public Criteria andOrderCountIsNull() {
            addCriterion("order_count is null");
            return (Criteria) this;
        }

        public Criteria andOrderCountIsNotNull() {
            addCriterion("order_count is not null");
            return (Criteria) this;
        }

        public Criteria andOrderCountEqualTo(Integer value) {
            addCriterion("order_count =", value, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountNotEqualTo(Integer value) {
            addCriterion("order_count <>", value, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountGreaterThan(Integer value) {
            addCriterion("order_count >", value, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_count >=", value, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountLessThan(Integer value) {
            addCriterion("order_count <", value, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountLessThanOrEqualTo(Integer value) {
            addCriterion("order_count <=", value, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountIn(List<Integer> values) {
            addCriterion("order_count in", values, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountNotIn(List<Integer> values) {
            addCriterion("order_count not in", values, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountBetween(Integer value1, Integer value2) {
            addCriterion("order_count between", value1, value2, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountNotBetween(Integer value1, Integer value2) {
            addCriterion("order_count not between", value1, value2, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderMoneyIsNull() {
            addCriterion("order_money is null");
            return (Criteria) this;
        }

        public Criteria andOrderMoneyIsNotNull() {
            addCriterion("order_money is not null");
            return (Criteria) this;
        }

        public Criteria andOrderMoneyEqualTo(Long value) {
            addCriterion("order_money =", value, "orderMoney");
            return (Criteria) this;
        }

        public Criteria andOrderMoneyNotEqualTo(Long value) {
            addCriterion("order_money <>", value, "orderMoney");
            return (Criteria) this;
        }

        public Criteria andOrderMoneyGreaterThan(Long value) {
            addCriterion("order_money >", value, "orderMoney");
            return (Criteria) this;
        }

        public Criteria andOrderMoneyGreaterThanOrEqualTo(Long value) {
            addCriterion("order_money >=", value, "orderMoney");
            return (Criteria) this;
        }

        public Criteria andOrderMoneyLessThan(Long value) {
            addCriterion("order_money <", value, "orderMoney");
            return (Criteria) this;
        }

        public Criteria andOrderMoneyLessThanOrEqualTo(Long value) {
            addCriterion("order_money <=", value, "orderMoney");
            return (Criteria) this;
        }

        public Criteria andOrderMoneyIn(List<Long> values) {
            addCriterion("order_money in", values, "orderMoney");
            return (Criteria) this;
        }

        public Criteria andOrderMoneyNotIn(List<Long> values) {
            addCriterion("order_money not in", values, "orderMoney");
            return (Criteria) this;
        }

        public Criteria andOrderMoneyBetween(Long value1, Long value2) {
            addCriterion("order_money between", value1, value2, "orderMoney");
            return (Criteria) this;
        }

        public Criteria andOrderMoneyNotBetween(Long value1, Long value2) {
            addCriterion("order_money not between", value1, value2, "orderMoney");
            return (Criteria) this;
        }

        public Criteria andSaleCountIsNull() {
            addCriterion("sale_count is null");
            return (Criteria) this;
        }

        public Criteria andSaleCountIsNotNull() {
            addCriterion("sale_count is not null");
            return (Criteria) this;
        }

        public Criteria andSaleCountEqualTo(Integer value) {
            addCriterion("sale_count =", value, "saleCount");
            return (Criteria) this;
        }

        public Criteria andSaleCountNotEqualTo(Integer value) {
            addCriterion("sale_count <>", value, "saleCount");
            return (Criteria) this;
        }

        public Criteria andSaleCountGreaterThan(Integer value) {
            addCriterion("sale_count >", value, "saleCount");
            return (Criteria) this;
        }

        public Criteria andSaleCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("sale_count >=", value, "saleCount");
            return (Criteria) this;
        }

        public Criteria andSaleCountLessThan(Integer value) {
            addCriterion("sale_count <", value, "saleCount");
            return (Criteria) this;
        }

        public Criteria andSaleCountLessThanOrEqualTo(Integer value) {
            addCriterion("sale_count <=", value, "saleCount");
            return (Criteria) this;
        }

        public Criteria andSaleCountIn(List<Integer> values) {
            addCriterion("sale_count in", values, "saleCount");
            return (Criteria) this;
        }

        public Criteria andSaleCountNotIn(List<Integer> values) {
            addCriterion("sale_count not in", values, "saleCount");
            return (Criteria) this;
        }

        public Criteria andSaleCountBetween(Integer value1, Integer value2) {
            addCriterion("sale_count between", value1, value2, "saleCount");
            return (Criteria) this;
        }

        public Criteria andSaleCountNotBetween(Integer value1, Integer value2) {
            addCriterion("sale_count not between", value1, value2, "saleCount");
            return (Criteria) this;
        }

        public Criteria andSaleMoneyIsNull() {
            addCriterion("sale_money is null");
            return (Criteria) this;
        }

        public Criteria andSaleMoneyIsNotNull() {
            addCriterion("sale_money is not null");
            return (Criteria) this;
        }

        public Criteria andSaleMoneyEqualTo(Long value) {
            addCriterion("sale_money =", value, "saleMoney");
            return (Criteria) this;
        }

        public Criteria andSaleMoneyNotEqualTo(Long value) {
            addCriterion("sale_money <>", value, "saleMoney");
            return (Criteria) this;
        }

        public Criteria andSaleMoneyGreaterThan(Long value) {
            addCriterion("sale_money >", value, "saleMoney");
            return (Criteria) this;
        }

        public Criteria andSaleMoneyGreaterThanOrEqualTo(Long value) {
            addCriterion("sale_money >=", value, "saleMoney");
            return (Criteria) this;
        }

        public Criteria andSaleMoneyLessThan(Long value) {
            addCriterion("sale_money <", value, "saleMoney");
            return (Criteria) this;
        }

        public Criteria andSaleMoneyLessThanOrEqualTo(Long value) {
            addCriterion("sale_money <=", value, "saleMoney");
            return (Criteria) this;
        }

        public Criteria andSaleMoneyIn(List<Long> values) {
            addCriterion("sale_money in", values, "saleMoney");
            return (Criteria) this;
        }

        public Criteria andSaleMoneyNotIn(List<Long> values) {
            addCriterion("sale_money not in", values, "saleMoney");
            return (Criteria) this;
        }

        public Criteria andSaleMoneyBetween(Long value1, Long value2) {
            addCriterion("sale_money between", value1, value2, "saleMoney");
            return (Criteria) this;
        }

        public Criteria andSaleMoneyNotBetween(Long value1, Long value2) {
            addCriterion("sale_money not between", value1, value2, "saleMoney");
            return (Criteria) this;
        }

        public Criteria andDepositIsNull() {
            addCriterion("deposit is null");
            return (Criteria) this;
        }

        public Criteria andDepositIsNotNull() {
            addCriterion("deposit is not null");
            return (Criteria) this;
        }

        public Criteria andDepositEqualTo(Integer value) {
            addCriterion("deposit =", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotEqualTo(Integer value) {
            addCriterion("deposit <>", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositGreaterThan(Integer value) {
            addCriterion("deposit >", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositGreaterThanOrEqualTo(Integer value) {
            addCriterion("deposit >=", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositLessThan(Integer value) {
            addCriterion("deposit <", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositLessThanOrEqualTo(Integer value) {
            addCriterion("deposit <=", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositIn(List<Integer> values) {
            addCriterion("deposit in", values, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotIn(List<Integer> values) {
            addCriterion("deposit not in", values, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositBetween(Integer value1, Integer value2) {
            addCriterion("deposit between", value1, value2, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotBetween(Integer value1, Integer value2) {
            addCriterion("deposit not between", value1, value2, "deposit");
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

        public Criteria andParentIdIsNull() {
            addCriterion("parent_id is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(Integer value) {
            addCriterion("parent_id =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(Integer value) {
            addCriterion("parent_id <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(Integer value) {
            addCriterion("parent_id >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("parent_id >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(Integer value) {
            addCriterion("parent_id <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(Integer value) {
            addCriterion("parent_id <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<Integer> values) {
            addCriterion("parent_id in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<Integer> values) {
            addCriterion("parent_id not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(Integer value1, Integer value2) {
            addCriterion("parent_id between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("parent_id not between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andSystemIdIsNull() {
            addCriterion("system_id is null");
            return (Criteria) this;
        }

        public Criteria andSystemIdIsNotNull() {
            addCriterion("system_id is not null");
            return (Criteria) this;
        }

        public Criteria andSystemIdEqualTo(Integer value) {
            addCriterion("system_id =", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdNotEqualTo(Integer value) {
            addCriterion("system_id <>", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdGreaterThan(Integer value) {
            addCriterion("system_id >", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("system_id >=", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdLessThan(Integer value) {
            addCriterion("system_id <", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdLessThanOrEqualTo(Integer value) {
            addCriterion("system_id <=", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdIn(List<Integer> values) {
            addCriterion("system_id in", values, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdNotIn(List<Integer> values) {
            addCriterion("system_id not in", values, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdBetween(Integer value1, Integer value2) {
            addCriterion("system_id between", value1, value2, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdNotBetween(Integer value1, Integer value2) {
            addCriterion("system_id not between", value1, value2, "systemId");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountIsNull() {
            addCriterion("alipay_account is null");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountIsNotNull() {
            addCriterion("alipay_account is not null");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountEqualTo(String value) {
            addCriterion("alipay_account =", value, "alipayAccount");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountNotEqualTo(String value) {
            addCriterion("alipay_account <>", value, "alipayAccount");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountGreaterThan(String value) {
            addCriterion("alipay_account >", value, "alipayAccount");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountGreaterThanOrEqualTo(String value) {
            addCriterion("alipay_account >=", value, "alipayAccount");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountLessThan(String value) {
            addCriterion("alipay_account <", value, "alipayAccount");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountLessThanOrEqualTo(String value) {
            addCriterion("alipay_account <=", value, "alipayAccount");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountLike(String value) {
            addCriterion("alipay_account like", value, "alipayAccount");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountNotLike(String value) {
            addCriterion("alipay_account not like", value, "alipayAccount");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountIn(List<String> values) {
            addCriterion("alipay_account in", values, "alipayAccount");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountNotIn(List<String> values) {
            addCriterion("alipay_account not in", values, "alipayAccount");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountBetween(String value1, String value2) {
            addCriterion("alipay_account between", value1, value2, "alipayAccount");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountNotBetween(String value1, String value2) {
            addCriterion("alipay_account not between", value1, value2, "alipayAccount");
            return (Criteria) this;
        }

        public Criteria andAlipayNameIsNull() {
            addCriterion("alipay_name is null");
            return (Criteria) this;
        }

        public Criteria andAlipayNameIsNotNull() {
            addCriterion("alipay_name is not null");
            return (Criteria) this;
        }

        public Criteria andAlipayNameEqualTo(String value) {
            addCriterion("alipay_name =", value, "alipayName");
            return (Criteria) this;
        }

        public Criteria andAlipayNameNotEqualTo(String value) {
            addCriterion("alipay_name <>", value, "alipayName");
            return (Criteria) this;
        }

        public Criteria andAlipayNameGreaterThan(String value) {
            addCriterion("alipay_name >", value, "alipayName");
            return (Criteria) this;
        }

        public Criteria andAlipayNameGreaterThanOrEqualTo(String value) {
            addCriterion("alipay_name >=", value, "alipayName");
            return (Criteria) this;
        }

        public Criteria andAlipayNameLessThan(String value) {
            addCriterion("alipay_name <", value, "alipayName");
            return (Criteria) this;
        }

        public Criteria andAlipayNameLessThanOrEqualTo(String value) {
            addCriterion("alipay_name <=", value, "alipayName");
            return (Criteria) this;
        }

        public Criteria andAlipayNameLike(String value) {
            addCriterion("alipay_name like", value, "alipayName");
            return (Criteria) this;
        }

        public Criteria andAlipayNameNotLike(String value) {
            addCriterion("alipay_name not like", value, "alipayName");
            return (Criteria) this;
        }

        public Criteria andAlipayNameIn(List<String> values) {
            addCriterion("alipay_name in", values, "alipayName");
            return (Criteria) this;
        }

        public Criteria andAlipayNameNotIn(List<String> values) {
            addCriterion("alipay_name not in", values, "alipayName");
            return (Criteria) this;
        }

        public Criteria andAlipayNameBetween(String value1, String value2) {
            addCriterion("alipay_name between", value1, value2, "alipayName");
            return (Criteria) this;
        }

        public Criteria andAlipayNameNotBetween(String value1, String value2) {
            addCriterion("alipay_name not between", value1, value2, "alipayName");
            return (Criteria) this;
        }

        public Criteria andBankProvinceIsNull() {
            addCriterion("bank_province is null");
            return (Criteria) this;
        }

        public Criteria andBankProvinceIsNotNull() {
            addCriterion("bank_province is not null");
            return (Criteria) this;
        }

        public Criteria andBankProvinceEqualTo(String value) {
            addCriterion("bank_province =", value, "bankProvince");
            return (Criteria) this;
        }

        public Criteria andBankProvinceNotEqualTo(String value) {
            addCriterion("bank_province <>", value, "bankProvince");
            return (Criteria) this;
        }

        public Criteria andBankProvinceGreaterThan(String value) {
            addCriterion("bank_province >", value, "bankProvince");
            return (Criteria) this;
        }

        public Criteria andBankProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("bank_province >=", value, "bankProvince");
            return (Criteria) this;
        }

        public Criteria andBankProvinceLessThan(String value) {
            addCriterion("bank_province <", value, "bankProvince");
            return (Criteria) this;
        }

        public Criteria andBankProvinceLessThanOrEqualTo(String value) {
            addCriterion("bank_province <=", value, "bankProvince");
            return (Criteria) this;
        }

        public Criteria andBankProvinceLike(String value) {
            addCriterion("bank_province like", value, "bankProvince");
            return (Criteria) this;
        }

        public Criteria andBankProvinceNotLike(String value) {
            addCriterion("bank_province not like", value, "bankProvince");
            return (Criteria) this;
        }

        public Criteria andBankProvinceIn(List<String> values) {
            addCriterion("bank_province in", values, "bankProvince");
            return (Criteria) this;
        }

        public Criteria andBankProvinceNotIn(List<String> values) {
            addCriterion("bank_province not in", values, "bankProvince");
            return (Criteria) this;
        }

        public Criteria andBankProvinceBetween(String value1, String value2) {
            addCriterion("bank_province between", value1, value2, "bankProvince");
            return (Criteria) this;
        }

        public Criteria andBankProvinceNotBetween(String value1, String value2) {
            addCriterion("bank_province not between", value1, value2, "bankProvince");
            return (Criteria) this;
        }

        public Criteria andBankCityIsNull() {
            addCriterion("bank_city is null");
            return (Criteria) this;
        }

        public Criteria andBankCityIsNotNull() {
            addCriterion("bank_city is not null");
            return (Criteria) this;
        }

        public Criteria andBankCityEqualTo(String value) {
            addCriterion("bank_city =", value, "bankCity");
            return (Criteria) this;
        }

        public Criteria andBankCityNotEqualTo(String value) {
            addCriterion("bank_city <>", value, "bankCity");
            return (Criteria) this;
        }

        public Criteria andBankCityGreaterThan(String value) {
            addCriterion("bank_city >", value, "bankCity");
            return (Criteria) this;
        }

        public Criteria andBankCityGreaterThanOrEqualTo(String value) {
            addCriterion("bank_city >=", value, "bankCity");
            return (Criteria) this;
        }

        public Criteria andBankCityLessThan(String value) {
            addCriterion("bank_city <", value, "bankCity");
            return (Criteria) this;
        }

        public Criteria andBankCityLessThanOrEqualTo(String value) {
            addCriterion("bank_city <=", value, "bankCity");
            return (Criteria) this;
        }

        public Criteria andBankCityLike(String value) {
            addCriterion("bank_city like", value, "bankCity");
            return (Criteria) this;
        }

        public Criteria andBankCityNotLike(String value) {
            addCriterion("bank_city not like", value, "bankCity");
            return (Criteria) this;
        }

        public Criteria andBankCityIn(List<String> values) {
            addCriterion("bank_city in", values, "bankCity");
            return (Criteria) this;
        }

        public Criteria andBankCityNotIn(List<String> values) {
            addCriterion("bank_city not in", values, "bankCity");
            return (Criteria) this;
        }

        public Criteria andBankCityBetween(String value1, String value2) {
            addCriterion("bank_city between", value1, value2, "bankCity");
            return (Criteria) this;
        }

        public Criteria andBankCityNotBetween(String value1, String value2) {
            addCriterion("bank_city not between", value1, value2, "bankCity");
            return (Criteria) this;
        }

        public Criteria andBankDistrictIsNull() {
            addCriterion("bank_district is null");
            return (Criteria) this;
        }

        public Criteria andBankDistrictIsNotNull() {
            addCriterion("bank_district is not null");
            return (Criteria) this;
        }

        public Criteria andBankDistrictEqualTo(String value) {
            addCriterion("bank_district =", value, "bankDistrict");
            return (Criteria) this;
        }

        public Criteria andBankDistrictNotEqualTo(String value) {
            addCriterion("bank_district <>", value, "bankDistrict");
            return (Criteria) this;
        }

        public Criteria andBankDistrictGreaterThan(String value) {
            addCriterion("bank_district >", value, "bankDistrict");
            return (Criteria) this;
        }

        public Criteria andBankDistrictGreaterThanOrEqualTo(String value) {
            addCriterion("bank_district >=", value, "bankDistrict");
            return (Criteria) this;
        }

        public Criteria andBankDistrictLessThan(String value) {
            addCriterion("bank_district <", value, "bankDistrict");
            return (Criteria) this;
        }

        public Criteria andBankDistrictLessThanOrEqualTo(String value) {
            addCriterion("bank_district <=", value, "bankDistrict");
            return (Criteria) this;
        }

        public Criteria andBankDistrictLike(String value) {
            addCriterion("bank_district like", value, "bankDistrict");
            return (Criteria) this;
        }

        public Criteria andBankDistrictNotLike(String value) {
            addCriterion("bank_district not like", value, "bankDistrict");
            return (Criteria) this;
        }

        public Criteria andBankDistrictIn(List<String> values) {
            addCriterion("bank_district in", values, "bankDistrict");
            return (Criteria) this;
        }

        public Criteria andBankDistrictNotIn(List<String> values) {
            addCriterion("bank_district not in", values, "bankDistrict");
            return (Criteria) this;
        }

        public Criteria andBankDistrictBetween(String value1, String value2) {
            addCriterion("bank_district between", value1, value2, "bankDistrict");
            return (Criteria) this;
        }

        public Criteria andBankDistrictNotBetween(String value1, String value2) {
            addCriterion("bank_district not between", value1, value2, "bankDistrict");
            return (Criteria) this;
        }

        public Criteria andBankAccountIsNull() {
            addCriterion("bank_account is null");
            return (Criteria) this;
        }

        public Criteria andBankAccountIsNotNull() {
            addCriterion("bank_account is not null");
            return (Criteria) this;
        }

        public Criteria andBankAccountEqualTo(String value) {
            addCriterion("bank_account =", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountNotEqualTo(String value) {
            addCriterion("bank_account <>", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountGreaterThan(String value) {
            addCriterion("bank_account >", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountGreaterThanOrEqualTo(String value) {
            addCriterion("bank_account >=", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountLessThan(String value) {
            addCriterion("bank_account <", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountLessThanOrEqualTo(String value) {
            addCriterion("bank_account <=", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountLike(String value) {
            addCriterion("bank_account like", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountNotLike(String value) {
            addCriterion("bank_account not like", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountIn(List<String> values) {
            addCriterion("bank_account in", values, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountNotIn(List<String> values) {
            addCriterion("bank_account not in", values, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountBetween(String value1, String value2) {
            addCriterion("bank_account between", value1, value2, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountNotBetween(String value1, String value2) {
            addCriterion("bank_account not between", value1, value2, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNull() {
            addCriterion("bank_name is null");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNotNull() {
            addCriterion("bank_name is not null");
            return (Criteria) this;
        }

        public Criteria andBankNameEqualTo(String value) {
            addCriterion("bank_name =", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotEqualTo(String value) {
            addCriterion("bank_name <>", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThan(String value) {
            addCriterion("bank_name >", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThanOrEqualTo(String value) {
            addCriterion("bank_name >=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThan(String value) {
            addCriterion("bank_name <", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThanOrEqualTo(String value) {
            addCriterion("bank_name <=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLike(String value) {
            addCriterion("bank_name like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotLike(String value) {
            addCriterion("bank_name not like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameIn(List<String> values) {
            addCriterion("bank_name in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotIn(List<String> values) {
            addCriterion("bank_name not in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameBetween(String value1, String value2) {
            addCriterion("bank_name between", value1, value2, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotBetween(String value1, String value2) {
            addCriterion("bank_name not between", value1, value2, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankAcnameIsNull() {
            addCriterion("bank_acname is null");
            return (Criteria) this;
        }

        public Criteria andBankAcnameIsNotNull() {
            addCriterion("bank_acname is not null");
            return (Criteria) this;
        }

        public Criteria andBankAcnameEqualTo(String value) {
            addCriterion("bank_acname =", value, "bankAcname");
            return (Criteria) this;
        }

        public Criteria andBankAcnameNotEqualTo(String value) {
            addCriterion("bank_acname <>", value, "bankAcname");
            return (Criteria) this;
        }

        public Criteria andBankAcnameGreaterThan(String value) {
            addCriterion("bank_acname >", value, "bankAcname");
            return (Criteria) this;
        }

        public Criteria andBankAcnameGreaterThanOrEqualTo(String value) {
            addCriterion("bank_acname >=", value, "bankAcname");
            return (Criteria) this;
        }

        public Criteria andBankAcnameLessThan(String value) {
            addCriterion("bank_acname <", value, "bankAcname");
            return (Criteria) this;
        }

        public Criteria andBankAcnameLessThanOrEqualTo(String value) {
            addCriterion("bank_acname <=", value, "bankAcname");
            return (Criteria) this;
        }

        public Criteria andBankAcnameLike(String value) {
            addCriterion("bank_acname like", value, "bankAcname");
            return (Criteria) this;
        }

        public Criteria andBankAcnameNotLike(String value) {
            addCriterion("bank_acname not like", value, "bankAcname");
            return (Criteria) this;
        }

        public Criteria andBankAcnameIn(List<String> values) {
            addCriterion("bank_acname in", values, "bankAcname");
            return (Criteria) this;
        }

        public Criteria andBankAcnameNotIn(List<String> values) {
            addCriterion("bank_acname not in", values, "bankAcname");
            return (Criteria) this;
        }

        public Criteria andBankAcnameBetween(String value1, String value2) {
            addCriterion("bank_acname between", value1, value2, "bankAcname");
            return (Criteria) this;
        }

        public Criteria andBankAcnameNotBetween(String value1, String value2) {
            addCriterion("bank_acname not between", value1, value2, "bankAcname");
            return (Criteria) this;
        }

        public Criteria andProdNumIsNull() {
            addCriterion("prod_num is null");
            return (Criteria) this;
        }

        public Criteria andProdNumIsNotNull() {
            addCriterion("prod_num is not null");
            return (Criteria) this;
        }

        public Criteria andProdNumEqualTo(Integer value) {
            addCriterion("prod_num =", value, "prodNum");
            return (Criteria) this;
        }

        public Criteria andProdNumNotEqualTo(Integer value) {
            addCriterion("prod_num <>", value, "prodNum");
            return (Criteria) this;
        }

        public Criteria andProdNumGreaterThan(Integer value) {
            addCriterion("prod_num >", value, "prodNum");
            return (Criteria) this;
        }

        public Criteria andProdNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("prod_num >=", value, "prodNum");
            return (Criteria) this;
        }

        public Criteria andProdNumLessThan(Integer value) {
            addCriterion("prod_num <", value, "prodNum");
            return (Criteria) this;
        }

        public Criteria andProdNumLessThanOrEqualTo(Integer value) {
            addCriterion("prod_num <=", value, "prodNum");
            return (Criteria) this;
        }

        public Criteria andProdNumIn(List<Integer> values) {
            addCriterion("prod_num in", values, "prodNum");
            return (Criteria) this;
        }

        public Criteria andProdNumNotIn(List<Integer> values) {
            addCriterion("prod_num not in", values, "prodNum");
            return (Criteria) this;
        }

        public Criteria andProdNumBetween(Integer value1, Integer value2) {
            addCriterion("prod_num between", value1, value2, "prodNum");
            return (Criteria) this;
        }

        public Criteria andProdNumNotBetween(Integer value1, Integer value2) {
            addCriterion("prod_num not between", value1, value2, "prodNum");
            return (Criteria) this;
        }

        public Criteria andInterfaceModeIsNull() {
            addCriterion("interface_mode is null");
            return (Criteria) this;
        }

        public Criteria andInterfaceModeIsNotNull() {
            addCriterion("interface_mode is not null");
            return (Criteria) this;
        }

        public Criteria andInterfaceModeEqualTo(Integer value) {
            addCriterion("interface_mode =", value, "interfaceMode");
            return (Criteria) this;
        }

        public Criteria andInterfaceModeNotEqualTo(Integer value) {
            addCriterion("interface_mode <>", value, "interfaceMode");
            return (Criteria) this;
        }

        public Criteria andInterfaceModeGreaterThan(Integer value) {
            addCriterion("interface_mode >", value, "interfaceMode");
            return (Criteria) this;
        }

        public Criteria andInterfaceModeGreaterThanOrEqualTo(Integer value) {
            addCriterion("interface_mode >=", value, "interfaceMode");
            return (Criteria) this;
        }

        public Criteria andInterfaceModeLessThan(Integer value) {
            addCriterion("interface_mode <", value, "interfaceMode");
            return (Criteria) this;
        }

        public Criteria andInterfaceModeLessThanOrEqualTo(Integer value) {
            addCriterion("interface_mode <=", value, "interfaceMode");
            return (Criteria) this;
        }

        public Criteria andInterfaceModeIn(List<Integer> values) {
            addCriterion("interface_mode in", values, "interfaceMode");
            return (Criteria) this;
        }

        public Criteria andInterfaceModeNotIn(List<Integer> values) {
            addCriterion("interface_mode not in", values, "interfaceMode");
            return (Criteria) this;
        }

        public Criteria andInterfaceModeBetween(Integer value1, Integer value2) {
            addCriterion("interface_mode between", value1, value2, "interfaceMode");
            return (Criteria) this;
        }

        public Criteria andInterfaceModeNotBetween(Integer value1, Integer value2) {
            addCriterion("interface_mode not between", value1, value2, "interfaceMode");
            return (Criteria) this;
        }

        public Criteria andLogoIsNull() {
            addCriterion("logo is null");
            return (Criteria) this;
        }

        public Criteria andLogoIsNotNull() {
            addCriterion("logo is not null");
            return (Criteria) this;
        }

        public Criteria andLogoEqualTo(String value) {
            addCriterion("logo =", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotEqualTo(String value) {
            addCriterion("logo <>", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoGreaterThan(String value) {
            addCriterion("logo >", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoGreaterThanOrEqualTo(String value) {
            addCriterion("logo >=", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoLessThan(String value) {
            addCriterion("logo <", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoLessThanOrEqualTo(String value) {
            addCriterion("logo <=", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoLike(String value) {
            addCriterion("logo like", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotLike(String value) {
            addCriterion("logo not like", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoIn(List<String> values) {
            addCriterion("logo in", values, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotIn(List<String> values) {
            addCriterion("logo not in", values, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoBetween(String value1, String value2) {
            addCriterion("logo between", value1, value2, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotBetween(String value1, String value2) {
            addCriterion("logo not between", value1, value2, "logo");
            return (Criteria) this;
        }

        public Criteria andIsFinishIsNull() {
            addCriterion("is_finish is null");
            return (Criteria) this;
        }

        public Criteria andIsFinishIsNotNull() {
            addCriterion("is_finish is not null");
            return (Criteria) this;
        }

        public Criteria andIsFinishEqualTo(Integer value) {
            addCriterion("is_finish =", value, "isFinish");
            return (Criteria) this;
        }

        public Criteria andIsFinishNotEqualTo(Integer value) {
            addCriterion("is_finish <>", value, "isFinish");
            return (Criteria) this;
        }

        public Criteria andIsFinishGreaterThan(Integer value) {
            addCriterion("is_finish >", value, "isFinish");
            return (Criteria) this;
        }

        public Criteria andIsFinishGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_finish >=", value, "isFinish");
            return (Criteria) this;
        }

        public Criteria andIsFinishLessThan(Integer value) {
            addCriterion("is_finish <", value, "isFinish");
            return (Criteria) this;
        }

        public Criteria andIsFinishLessThanOrEqualTo(Integer value) {
            addCriterion("is_finish <=", value, "isFinish");
            return (Criteria) this;
        }

        public Criteria andIsFinishIn(List<Integer> values) {
            addCriterion("is_finish in", values, "isFinish");
            return (Criteria) this;
        }

        public Criteria andIsFinishNotIn(List<Integer> values) {
            addCriterion("is_finish not in", values, "isFinish");
            return (Criteria) this;
        }

        public Criteria andIsFinishBetween(Integer value1, Integer value2) {
            addCriterion("is_finish between", value1, value2, "isFinish");
            return (Criteria) this;
        }

        public Criteria andIsFinishNotBetween(Integer value1, Integer value2) {
            addCriterion("is_finish not between", value1, value2, "isFinish");
            return (Criteria) this;
        }

        public Criteria andMoneyModeIsNull() {
            addCriterion("money_mode is null");
            return (Criteria) this;
        }

        public Criteria andMoneyModeIsNotNull() {
            addCriterion("money_mode is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyModeEqualTo(Integer value) {
            addCriterion("money_mode =", value, "moneyMode");
            return (Criteria) this;
        }

        public Criteria andMoneyModeNotEqualTo(Integer value) {
            addCriterion("money_mode <>", value, "moneyMode");
            return (Criteria) this;
        }

        public Criteria andMoneyModeGreaterThan(Integer value) {
            addCriterion("money_mode >", value, "moneyMode");
            return (Criteria) this;
        }

        public Criteria andMoneyModeGreaterThanOrEqualTo(Integer value) {
            addCriterion("money_mode >=", value, "moneyMode");
            return (Criteria) this;
        }

        public Criteria andMoneyModeLessThan(Integer value) {
            addCriterion("money_mode <", value, "moneyMode");
            return (Criteria) this;
        }

        public Criteria andMoneyModeLessThanOrEqualTo(Integer value) {
            addCriterion("money_mode <=", value, "moneyMode");
            return (Criteria) this;
        }

        public Criteria andMoneyModeIn(List<Integer> values) {
            addCriterion("money_mode in", values, "moneyMode");
            return (Criteria) this;
        }

        public Criteria andMoneyModeNotIn(List<Integer> values) {
            addCriterion("money_mode not in", values, "moneyMode");
            return (Criteria) this;
        }

        public Criteria andMoneyModeBetween(Integer value1, Integer value2) {
            addCriterion("money_mode between", value1, value2, "moneyMode");
            return (Criteria) this;
        }

        public Criteria andMoneyModeNotBetween(Integer value1, Integer value2) {
            addCriterion("money_mode not between", value1, value2, "moneyMode");
            return (Criteria) this;
        }

        public Criteria andIsPushIsNull() {
            addCriterion("is_push is null");
            return (Criteria) this;
        }

        public Criteria andIsPushIsNotNull() {
            addCriterion("is_push is not null");
            return (Criteria) this;
        }

        public Criteria andIsPushEqualTo(Integer value) {
            addCriterion("is_push =", value, "isPush");
            return (Criteria) this;
        }

        public Criteria andIsPushNotEqualTo(Integer value) {
            addCriterion("is_push <>", value, "isPush");
            return (Criteria) this;
        }

        public Criteria andIsPushGreaterThan(Integer value) {
            addCriterion("is_push >", value, "isPush");
            return (Criteria) this;
        }

        public Criteria andIsPushGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_push >=", value, "isPush");
            return (Criteria) this;
        }

        public Criteria andIsPushLessThan(Integer value) {
            addCriterion("is_push <", value, "isPush");
            return (Criteria) this;
        }

        public Criteria andIsPushLessThanOrEqualTo(Integer value) {
            addCriterion("is_push <=", value, "isPush");
            return (Criteria) this;
        }

        public Criteria andIsPushIn(List<Integer> values) {
            addCriterion("is_push in", values, "isPush");
            return (Criteria) this;
        }

        public Criteria andIsPushNotIn(List<Integer> values) {
            addCriterion("is_push not in", values, "isPush");
            return (Criteria) this;
        }

        public Criteria andIsPushBetween(Integer value1, Integer value2) {
            addCriterion("is_push between", value1, value2, "isPush");
            return (Criteria) this;
        }

        public Criteria andIsPushNotBetween(Integer value1, Integer value2) {
            addCriterion("is_push not between", value1, value2, "isPush");
            return (Criteria) this;
        }

        public Criteria andIsB2bIsNull() {
            addCriterion("is_b2b is null");
            return (Criteria) this;
        }

        public Criteria andIsB2bIsNotNull() {
            addCriterion("is_b2b is not null");
            return (Criteria) this;
        }

        public Criteria andIsB2bEqualTo(Integer value) {
            addCriterion("is_b2b =", value, "isB2b");
            return (Criteria) this;
        }

        public Criteria andIsB2bNotEqualTo(Integer value) {
            addCriterion("is_b2b <>", value, "isB2b");
            return (Criteria) this;
        }

        public Criteria andIsB2bGreaterThan(Integer value) {
            addCriterion("is_b2b >", value, "isB2b");
            return (Criteria) this;
        }

        public Criteria andIsB2bGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_b2b >=", value, "isB2b");
            return (Criteria) this;
        }

        public Criteria andIsB2bLessThan(Integer value) {
            addCriterion("is_b2b <", value, "isB2b");
            return (Criteria) this;
        }

        public Criteria andIsB2bLessThanOrEqualTo(Integer value) {
            addCriterion("is_b2b <=", value, "isB2b");
            return (Criteria) this;
        }

        public Criteria andIsB2bIn(List<Integer> values) {
            addCriterion("is_b2b in", values, "isB2b");
            return (Criteria) this;
        }

        public Criteria andIsB2bNotIn(List<Integer> values) {
            addCriterion("is_b2b not in", values, "isB2b");
            return (Criteria) this;
        }

        public Criteria andIsB2bBetween(Integer value1, Integer value2) {
            addCriterion("is_b2b between", value1, value2, "isB2b");
            return (Criteria) this;
        }

        public Criteria andIsB2bNotBetween(Integer value1, Integer value2) {
            addCriterion("is_b2b not between", value1, value2, "isB2b");
            return (Criteria) this;
        }

        public Criteria andIsGmoneyIsNull() {
            addCriterion("is_gmoney is null");
            return (Criteria) this;
        }

        public Criteria andIsGmoneyIsNotNull() {
            addCriterion("is_gmoney is not null");
            return (Criteria) this;
        }

        public Criteria andIsGmoneyEqualTo(Integer value) {
            addCriterion("is_gmoney =", value, "isGmoney");
            return (Criteria) this;
        }

        public Criteria andIsGmoneyNotEqualTo(Integer value) {
            addCriterion("is_gmoney <>", value, "isGmoney");
            return (Criteria) this;
        }

        public Criteria andIsGmoneyGreaterThan(Integer value) {
            addCriterion("is_gmoney >", value, "isGmoney");
            return (Criteria) this;
        }

        public Criteria andIsGmoneyGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_gmoney >=", value, "isGmoney");
            return (Criteria) this;
        }

        public Criteria andIsGmoneyLessThan(Integer value) {
            addCriterion("is_gmoney <", value, "isGmoney");
            return (Criteria) this;
        }

        public Criteria andIsGmoneyLessThanOrEqualTo(Integer value) {
            addCriterion("is_gmoney <=", value, "isGmoney");
            return (Criteria) this;
        }

        public Criteria andIsGmoneyIn(List<Integer> values) {
            addCriterion("is_gmoney in", values, "isGmoney");
            return (Criteria) this;
        }

        public Criteria andIsGmoneyNotIn(List<Integer> values) {
            addCriterion("is_gmoney not in", values, "isGmoney");
            return (Criteria) this;
        }

        public Criteria andIsGmoneyBetween(Integer value1, Integer value2) {
            addCriterion("is_gmoney between", value1, value2, "isGmoney");
            return (Criteria) this;
        }

        public Criteria andIsGmoneyNotBetween(Integer value1, Integer value2) {
            addCriterion("is_gmoney not between", value1, value2, "isGmoney");
            return (Criteria) this;
        }

        public Criteria andPayFeeIsNull() {
            addCriterion("pay_fee is null");
            return (Criteria) this;
        }

        public Criteria andPayFeeIsNotNull() {
            addCriterion("pay_fee is not null");
            return (Criteria) this;
        }

        public Criteria andPayFeeEqualTo(Integer value) {
            addCriterion("pay_fee =", value, "payFee");
            return (Criteria) this;
        }

        public Criteria andPayFeeNotEqualTo(Integer value) {
            addCriterion("pay_fee <>", value, "payFee");
            return (Criteria) this;
        }

        public Criteria andPayFeeGreaterThan(Integer value) {
            addCriterion("pay_fee >", value, "payFee");
            return (Criteria) this;
        }

        public Criteria andPayFeeGreaterThanOrEqualTo(Integer value) {
            addCriterion("pay_fee >=", value, "payFee");
            return (Criteria) this;
        }

        public Criteria andPayFeeLessThan(Integer value) {
            addCriterion("pay_fee <", value, "payFee");
            return (Criteria) this;
        }

        public Criteria andPayFeeLessThanOrEqualTo(Integer value) {
            addCriterion("pay_fee <=", value, "payFee");
            return (Criteria) this;
        }

        public Criteria andPayFeeIn(List<Integer> values) {
            addCriterion("pay_fee in", values, "payFee");
            return (Criteria) this;
        }

        public Criteria andPayFeeNotIn(List<Integer> values) {
            addCriterion("pay_fee not in", values, "payFee");
            return (Criteria) this;
        }

        public Criteria andPayFeeBetween(Integer value1, Integer value2) {
            addCriterion("pay_fee between", value1, value2, "payFee");
            return (Criteria) this;
        }

        public Criteria andPayFeeNotBetween(Integer value1, Integer value2) {
            addCriterion("pay_fee not between", value1, value2, "payFee");
            return (Criteria) this;
        }

        public Criteria andGatFeeIsNull() {
            addCriterion("gat_fee is null");
            return (Criteria) this;
        }

        public Criteria andGatFeeIsNotNull() {
            addCriterion("gat_fee is not null");
            return (Criteria) this;
        }

        public Criteria andGatFeeEqualTo(Integer value) {
            addCriterion("gat_fee =", value, "gatFee");
            return (Criteria) this;
        }

        public Criteria andGatFeeNotEqualTo(Integer value) {
            addCriterion("gat_fee <>", value, "gatFee");
            return (Criteria) this;
        }

        public Criteria andGatFeeGreaterThan(Integer value) {
            addCriterion("gat_fee >", value, "gatFee");
            return (Criteria) this;
        }

        public Criteria andGatFeeGreaterThanOrEqualTo(Integer value) {
            addCriterion("gat_fee >=", value, "gatFee");
            return (Criteria) this;
        }

        public Criteria andGatFeeLessThan(Integer value) {
            addCriterion("gat_fee <", value, "gatFee");
            return (Criteria) this;
        }

        public Criteria andGatFeeLessThanOrEqualTo(Integer value) {
            addCriterion("gat_fee <=", value, "gatFee");
            return (Criteria) this;
        }

        public Criteria andGatFeeIn(List<Integer> values) {
            addCriterion("gat_fee in", values, "gatFee");
            return (Criteria) this;
        }

        public Criteria andGatFeeNotIn(List<Integer> values) {
            addCriterion("gat_fee not in", values, "gatFee");
            return (Criteria) this;
        }

        public Criteria andGatFeeBetween(Integer value1, Integer value2) {
            addCriterion("gat_fee between", value1, value2, "gatFee");
            return (Criteria) this;
        }

        public Criteria andGatFeeNotBetween(Integer value1, Integer value2) {
            addCriterion("gat_fee not between", value1, value2, "gatFee");
            return (Criteria) this;
        }

        public Criteria andPushMobileIsNull() {
            addCriterion("push_mobile is null");
            return (Criteria) this;
        }

        public Criteria andPushMobileIsNotNull() {
            addCriterion("push_mobile is not null");
            return (Criteria) this;
        }

        public Criteria andPushMobileEqualTo(String value) {
            addCriterion("push_mobile =", value, "pushMobile");
            return (Criteria) this;
        }

        public Criteria andPushMobileNotEqualTo(String value) {
            addCriterion("push_mobile <>", value, "pushMobile");
            return (Criteria) this;
        }

        public Criteria andPushMobileGreaterThan(String value) {
            addCriterion("push_mobile >", value, "pushMobile");
            return (Criteria) this;
        }

        public Criteria andPushMobileGreaterThanOrEqualTo(String value) {
            addCriterion("push_mobile >=", value, "pushMobile");
            return (Criteria) this;
        }

        public Criteria andPushMobileLessThan(String value) {
            addCriterion("push_mobile <", value, "pushMobile");
            return (Criteria) this;
        }

        public Criteria andPushMobileLessThanOrEqualTo(String value) {
            addCriterion("push_mobile <=", value, "pushMobile");
            return (Criteria) this;
        }

        public Criteria andPushMobileLike(String value) {
            addCriterion("push_mobile like", value, "pushMobile");
            return (Criteria) this;
        }

        public Criteria andPushMobileNotLike(String value) {
            addCriterion("push_mobile not like", value, "pushMobile");
            return (Criteria) this;
        }

        public Criteria andPushMobileIn(List<String> values) {
            addCriterion("push_mobile in", values, "pushMobile");
            return (Criteria) this;
        }

        public Criteria andPushMobileNotIn(List<String> values) {
            addCriterion("push_mobile not in", values, "pushMobile");
            return (Criteria) this;
        }

        public Criteria andPushMobileBetween(String value1, String value2) {
            addCriterion("push_mobile between", value1, value2, "pushMobile");
            return (Criteria) this;
        }

        public Criteria andPushMobileNotBetween(String value1, String value2) {
            addCriterion("push_mobile not between", value1, value2, "pushMobile");
            return (Criteria) this;
        }

        public Criteria andPushEmailIsNull() {
            addCriterion("push_email is null");
            return (Criteria) this;
        }

        public Criteria andPushEmailIsNotNull() {
            addCriterion("push_email is not null");
            return (Criteria) this;
        }

        public Criteria andPushEmailEqualTo(String value) {
            addCriterion("push_email =", value, "pushEmail");
            return (Criteria) this;
        }

        public Criteria andPushEmailNotEqualTo(String value) {
            addCriterion("push_email <>", value, "pushEmail");
            return (Criteria) this;
        }

        public Criteria andPushEmailGreaterThan(String value) {
            addCriterion("push_email >", value, "pushEmail");
            return (Criteria) this;
        }

        public Criteria andPushEmailGreaterThanOrEqualTo(String value) {
            addCriterion("push_email >=", value, "pushEmail");
            return (Criteria) this;
        }

        public Criteria andPushEmailLessThan(String value) {
            addCriterion("push_email <", value, "pushEmail");
            return (Criteria) this;
        }

        public Criteria andPushEmailLessThanOrEqualTo(String value) {
            addCriterion("push_email <=", value, "pushEmail");
            return (Criteria) this;
        }

        public Criteria andPushEmailLike(String value) {
            addCriterion("push_email like", value, "pushEmail");
            return (Criteria) this;
        }

        public Criteria andPushEmailNotLike(String value) {
            addCriterion("push_email not like", value, "pushEmail");
            return (Criteria) this;
        }

        public Criteria andPushEmailIn(List<String> values) {
            addCriterion("push_email in", values, "pushEmail");
            return (Criteria) this;
        }

        public Criteria andPushEmailNotIn(List<String> values) {
            addCriterion("push_email not in", values, "pushEmail");
            return (Criteria) this;
        }

        public Criteria andPushEmailBetween(String value1, String value2) {
            addCriterion("push_email between", value1, value2, "pushEmail");
            return (Criteria) this;
        }

        public Criteria andPushEmailNotBetween(String value1, String value2) {
            addCriterion("push_email not between", value1, value2, "pushEmail");
            return (Criteria) this;
        }

        public Criteria andCurrencyTypeIsNull() {
            addCriterion("currency_type is null");
            return (Criteria) this;
        }

        public Criteria andCurrencyTypeIsNotNull() {
            addCriterion("currency_type is not null");
            return (Criteria) this;
        }

        public Criteria andCurrencyTypeEqualTo(Integer value) {
            addCriterion("currency_type =", value, "currencyType");
            return (Criteria) this;
        }

        public Criteria andCurrencyTypeNotEqualTo(Integer value) {
            addCriterion("currency_type <>", value, "currencyType");
            return (Criteria) this;
        }

        public Criteria andCurrencyTypeGreaterThan(Integer value) {
            addCriterion("currency_type >", value, "currencyType");
            return (Criteria) this;
        }

        public Criteria andCurrencyTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("currency_type >=", value, "currencyType");
            return (Criteria) this;
        }

        public Criteria andCurrencyTypeLessThan(Integer value) {
            addCriterion("currency_type <", value, "currencyType");
            return (Criteria) this;
        }

        public Criteria andCurrencyTypeLessThanOrEqualTo(Integer value) {
            addCriterion("currency_type <=", value, "currencyType");
            return (Criteria) this;
        }

        public Criteria andCurrencyTypeIn(List<Integer> values) {
            addCriterion("currency_type in", values, "currencyType");
            return (Criteria) this;
        }

        public Criteria andCurrencyTypeNotIn(List<Integer> values) {
            addCriterion("currency_type not in", values, "currencyType");
            return (Criteria) this;
        }

        public Criteria andCurrencyTypeBetween(Integer value1, Integer value2) {
            addCriterion("currency_type between", value1, value2, "currencyType");
            return (Criteria) this;
        }

        public Criteria andCurrencyTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("currency_type not between", value1, value2, "currencyType");
            return (Criteria) this;
        }

        public Criteria andBusinessInfoIsNull() {
            addCriterion("business_info is null");
            return (Criteria) this;
        }

        public Criteria andBusinessInfoIsNotNull() {
            addCriterion("business_info is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessInfoEqualTo(String value) {
            addCriterion("business_info =", value, "businessInfo");
            return (Criteria) this;
        }

        public Criteria andBusinessInfoNotEqualTo(String value) {
            addCriterion("business_info <>", value, "businessInfo");
            return (Criteria) this;
        }

        public Criteria andBusinessInfoGreaterThan(String value) {
            addCriterion("business_info >", value, "businessInfo");
            return (Criteria) this;
        }

        public Criteria andBusinessInfoGreaterThanOrEqualTo(String value) {
            addCriterion("business_info >=", value, "businessInfo");
            return (Criteria) this;
        }

        public Criteria andBusinessInfoLessThan(String value) {
            addCriterion("business_info <", value, "businessInfo");
            return (Criteria) this;
        }

        public Criteria andBusinessInfoLessThanOrEqualTo(String value) {
            addCriterion("business_info <=", value, "businessInfo");
            return (Criteria) this;
        }

        public Criteria andBusinessInfoLike(String value) {
            addCriterion("business_info like", value, "businessInfo");
            return (Criteria) this;
        }

        public Criteria andBusinessInfoNotLike(String value) {
            addCriterion("business_info not like", value, "businessInfo");
            return (Criteria) this;
        }

        public Criteria andBusinessInfoIn(List<String> values) {
            addCriterion("business_info in", values, "businessInfo");
            return (Criteria) this;
        }

        public Criteria andBusinessInfoNotIn(List<String> values) {
            addCriterion("business_info not in", values, "businessInfo");
            return (Criteria) this;
        }

        public Criteria andBusinessInfoBetween(String value1, String value2) {
            addCriterion("business_info between", value1, value2, "businessInfo");
            return (Criteria) this;
        }

        public Criteria andBusinessInfoNotBetween(String value1, String value2) {
            addCriterion("business_info not between", value1, value2, "businessInfo");
            return (Criteria) this;
        }

        public Criteria andSportTypeIsNull() {
            addCriterion("sport_type is null");
            return (Criteria) this;
        }

        public Criteria andSportTypeIsNotNull() {
            addCriterion("sport_type is not null");
            return (Criteria) this;
        }

        public Criteria andSportTypeEqualTo(Integer value) {
            addCriterion("sport_type =", value, "sportType");
            return (Criteria) this;
        }

        public Criteria andSportTypeNotEqualTo(Integer value) {
            addCriterion("sport_type <>", value, "sportType");
            return (Criteria) this;
        }

        public Criteria andSportTypeGreaterThan(Integer value) {
            addCriterion("sport_type >", value, "sportType");
            return (Criteria) this;
        }

        public Criteria andSportTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("sport_type >=", value, "sportType");
            return (Criteria) this;
        }

        public Criteria andSportTypeLessThan(Integer value) {
            addCriterion("sport_type <", value, "sportType");
            return (Criteria) this;
        }

        public Criteria andSportTypeLessThanOrEqualTo(Integer value) {
            addCriterion("sport_type <=", value, "sportType");
            return (Criteria) this;
        }

        public Criteria andSportTypeIn(List<Integer> values) {
            addCriterion("sport_type in", values, "sportType");
            return (Criteria) this;
        }

        public Criteria andSportTypeNotIn(List<Integer> values) {
            addCriterion("sport_type not in", values, "sportType");
            return (Criteria) this;
        }

        public Criteria andSportTypeBetween(Integer value1, Integer value2) {
            addCriterion("sport_type between", value1, value2, "sportType");
            return (Criteria) this;
        }

        public Criteria andSportTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("sport_type not between", value1, value2, "sportType");
            return (Criteria) this;
        }

        public Criteria andParentTypeIsNull() {
            addCriterion("parent_type is null");
            return (Criteria) this;
        }

        public Criteria andParentTypeIsNotNull() {
            addCriterion("parent_type is not null");
            return (Criteria) this;
        }

        public Criteria andParentTypeEqualTo(Integer value) {
            addCriterion("parent_type =", value, "parentType");
            return (Criteria) this;
        }

        public Criteria andParentTypeNotEqualTo(Integer value) {
            addCriterion("parent_type <>", value, "parentType");
            return (Criteria) this;
        }

        public Criteria andParentTypeGreaterThan(Integer value) {
            addCriterion("parent_type >", value, "parentType");
            return (Criteria) this;
        }

        public Criteria andParentTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("parent_type >=", value, "parentType");
            return (Criteria) this;
        }

        public Criteria andParentTypeLessThan(Integer value) {
            addCriterion("parent_type <", value, "parentType");
            return (Criteria) this;
        }

        public Criteria andParentTypeLessThanOrEqualTo(Integer value) {
            addCriterion("parent_type <=", value, "parentType");
            return (Criteria) this;
        }

        public Criteria andParentTypeIn(List<Integer> values) {
            addCriterion("parent_type in", values, "parentType");
            return (Criteria) this;
        }

        public Criteria andParentTypeNotIn(List<Integer> values) {
            addCriterion("parent_type not in", values, "parentType");
            return (Criteria) this;
        }

        public Criteria andParentTypeBetween(Integer value1, Integer value2) {
            addCriterion("parent_type between", value1, value2, "parentType");
            return (Criteria) this;
        }

        public Criteria andParentTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("parent_type not between", value1, value2, "parentType");
            return (Criteria) this;
        }

        public Criteria andBankTypeIsNull() {
            addCriterion("bank_type is null");
            return (Criteria) this;
        }

        public Criteria andBankTypeIsNotNull() {
            addCriterion("bank_type is not null");
            return (Criteria) this;
        }

        public Criteria andBankTypeEqualTo(String value) {
            addCriterion("bank_type =", value, "bankType");
            return (Criteria) this;
        }

        public Criteria andBankTypeNotEqualTo(String value) {
            addCriterion("bank_type <>", value, "bankType");
            return (Criteria) this;
        }

        public Criteria andBankTypeGreaterThan(String value) {
            addCriterion("bank_type >", value, "bankType");
            return (Criteria) this;
        }

        public Criteria andBankTypeGreaterThanOrEqualTo(String value) {
            addCriterion("bank_type >=", value, "bankType");
            return (Criteria) this;
        }

        public Criteria andBankTypeLessThan(String value) {
            addCriterion("bank_type <", value, "bankType");
            return (Criteria) this;
        }

        public Criteria andBankTypeLessThanOrEqualTo(String value) {
            addCriterion("bank_type <=", value, "bankType");
            return (Criteria) this;
        }

        public Criteria andBankTypeLike(String value) {
            addCriterion("bank_type like", value, "bankType");
            return (Criteria) this;
        }

        public Criteria andBankTypeNotLike(String value) {
            addCriterion("bank_type not like", value, "bankType");
            return (Criteria) this;
        }

        public Criteria andBankTypeIn(List<String> values) {
            addCriterion("bank_type in", values, "bankType");
            return (Criteria) this;
        }

        public Criteria andBankTypeNotIn(List<String> values) {
            addCriterion("bank_type not in", values, "bankType");
            return (Criteria) this;
        }

        public Criteria andBankTypeBetween(String value1, String value2) {
            addCriterion("bank_type between", value1, value2, "bankType");
            return (Criteria) this;
        }

        public Criteria andBankTypeNotBetween(String value1, String value2) {
            addCriterion("bank_type not between", value1, value2, "bankType");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNull() {
            addCriterion("longitude is null");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNotNull() {
            addCriterion("longitude is not null");
            return (Criteria) this;
        }

        public Criteria andLongitudeEqualTo(Double value) {
            addCriterion("longitude =", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotEqualTo(Double value) {
            addCriterion("longitude <>", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThan(Double value) {
            addCriterion("longitude >", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThanOrEqualTo(Double value) {
            addCriterion("longitude >=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThan(Double value) {
            addCriterion("longitude <", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThanOrEqualTo(Double value) {
            addCriterion("longitude <=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeIn(List<Double> values) {
            addCriterion("longitude in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotIn(List<Double> values) {
            addCriterion("longitude not in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeBetween(Double value1, Double value2) {
            addCriterion("longitude between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotBetween(Double value1, Double value2) {
            addCriterion("longitude not between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNull() {
            addCriterion("latitude is null");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNotNull() {
            addCriterion("latitude is not null");
            return (Criteria) this;
        }

        public Criteria andLatitudeEqualTo(Double value) {
            addCriterion("latitude =", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotEqualTo(Double value) {
            addCriterion("latitude <>", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThan(Double value) {
            addCriterion("latitude >", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThanOrEqualTo(Double value) {
            addCriterion("latitude >=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThan(Double value) {
            addCriterion("latitude <", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThanOrEqualTo(Double value) {
            addCriterion("latitude <=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIn(List<Double> values) {
            addCriterion("latitude in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotIn(List<Double> values) {
            addCriterion("latitude not in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeBetween(Double value1, Double value2) {
            addCriterion("latitude between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotBetween(Double value1, Double value2) {
            addCriterion("latitude not between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andCustNameLikeInsensitive(String value) {
            addCriterion("upper(cust_name) like", value.toUpperCase(), "custName");
            return (Criteria) this;
        }

        public Criteria andSignimgLikeInsensitive(String value) {
            addCriterion("upper(signimg) like", value.toUpperCase(), "signimg");
            return (Criteria) this;
        }

        public Criteria andLinkManLikeInsensitive(String value) {
            addCriterion("upper(link_man) like", value.toUpperCase(), "linkMan");
            return (Criteria) this;
        }

        public Criteria andCustDescLikeInsensitive(String value) {
            addCriterion("upper(cust_desc) like", value.toUpperCase(), "custDesc");
            return (Criteria) this;
        }

        public Criteria andLinkMobileLikeInsensitive(String value) {
            addCriterion("upper(link_mobile) like", value.toUpperCase(), "linkMobile");
            return (Criteria) this;
        }

        public Criteria andLinkQqLikeInsensitive(String value) {
            addCriterion("upper(link_qq) like", value.toUpperCase(), "linkQq");
            return (Criteria) this;
        }

        public Criteria andLinkEmailLikeInsensitive(String value) {
            addCriterion("upper(link_email) like", value.toUpperCase(), "linkEmail");
            return (Criteria) this;
        }

        public Criteria andLinkFaxLikeInsensitive(String value) {
            addCriterion("upper(link_fax) like", value.toUpperCase(), "linkFax");
            return (Criteria) this;
        }

        public Criteria andAddressLikeInsensitive(String value) {
            addCriterion("upper(address) like", value.toUpperCase(), "address");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountLikeInsensitive(String value) {
            addCriterion("upper(alipay_account) like", value.toUpperCase(), "alipayAccount");
            return (Criteria) this;
        }

        public Criteria andAlipayNameLikeInsensitive(String value) {
            addCriterion("upper(alipay_name) like", value.toUpperCase(), "alipayName");
            return (Criteria) this;
        }

        public Criteria andBankProvinceLikeInsensitive(String value) {
            addCriterion("upper(bank_province) like", value.toUpperCase(), "bankProvince");
            return (Criteria) this;
        }

        public Criteria andBankCityLikeInsensitive(String value) {
            addCriterion("upper(bank_city) like", value.toUpperCase(), "bankCity");
            return (Criteria) this;
        }

        public Criteria andBankDistrictLikeInsensitive(String value) {
            addCriterion("upper(bank_district) like", value.toUpperCase(), "bankDistrict");
            return (Criteria) this;
        }

        public Criteria andBankAccountLikeInsensitive(String value) {
            addCriterion("upper(bank_account) like", value.toUpperCase(), "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankNameLikeInsensitive(String value) {
            addCriterion("upper(bank_name) like", value.toUpperCase(), "bankName");
            return (Criteria) this;
        }

        public Criteria andBankAcnameLikeInsensitive(String value) {
            addCriterion("upper(bank_acname) like", value.toUpperCase(), "bankAcname");
            return (Criteria) this;
        }

        public Criteria andLogoLikeInsensitive(String value) {
            addCriterion("upper(logo) like", value.toUpperCase(), "logo");
            return (Criteria) this;
        }

        public Criteria andPushMobileLikeInsensitive(String value) {
            addCriterion("upper(push_mobile) like", value.toUpperCase(), "pushMobile");
            return (Criteria) this;
        }

        public Criteria andPushEmailLikeInsensitive(String value) {
            addCriterion("upper(push_email) like", value.toUpperCase(), "pushEmail");
            return (Criteria) this;
        }

        public Criteria andBusinessInfoLikeInsensitive(String value) {
            addCriterion("upper(business_info) like", value.toUpperCase(), "businessInfo");
            return (Criteria) this;
        }

        public Criteria andBankTypeLikeInsensitive(String value) {
            addCriterion("upper(bank_type) like", value.toUpperCase(), "bankType");
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
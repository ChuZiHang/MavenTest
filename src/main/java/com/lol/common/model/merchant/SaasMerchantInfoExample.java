package com.lol.common.model.merchant;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lol.common.plugin.Page;

public class SaasMerchantInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Page page;

    private String dialect = "mysql";

    public SaasMerchantInfoExample() {
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

        public Criteria andMcIdIsNull() {
            addCriterion("mc_id is null");
            return (Criteria) this;
        }

        public Criteria andMcIdIsNotNull() {
            addCriterion("mc_id is not null");
            return (Criteria) this;
        }

        public Criteria andMcIdEqualTo(Integer value) {
            addCriterion("mc_id =", value, "mcId");
            return (Criteria) this;
        }

        public Criteria andMcIdNotEqualTo(Integer value) {
            addCriterion("mc_id <>", value, "mcId");
            return (Criteria) this;
        }

        public Criteria andMcIdGreaterThan(Integer value) {
            addCriterion("mc_id >", value, "mcId");
            return (Criteria) this;
        }

        public Criteria andMcIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("mc_id >=", value, "mcId");
            return (Criteria) this;
        }

        public Criteria andMcIdLessThan(Integer value) {
            addCriterion("mc_id <", value, "mcId");
            return (Criteria) this;
        }

        public Criteria andMcIdLessThanOrEqualTo(Integer value) {
            addCriterion("mc_id <=", value, "mcId");
            return (Criteria) this;
        }

        public Criteria andMcIdIn(List<Integer> values) {
            addCriterion("mc_id in", values, "mcId");
            return (Criteria) this;
        }

        public Criteria andMcIdNotIn(List<Integer> values) {
            addCriterion("mc_id not in", values, "mcId");
            return (Criteria) this;
        }

        public Criteria andMcIdBetween(Integer value1, Integer value2) {
            addCriterion("mc_id between", value1, value2, "mcId");
            return (Criteria) this;
        }

        public Criteria andMcIdNotBetween(Integer value1, Integer value2) {
            addCriterion("mc_id not between", value1, value2, "mcId");
            return (Criteria) this;
        }

        public Criteria andMcNameIsNull() {
            addCriterion("mc_name is null");
            return (Criteria) this;
        }

        public Criteria andMcNameIsNotNull() {
            addCriterion("mc_name is not null");
            return (Criteria) this;
        }

        public Criteria andMcNameEqualTo(String value) {
            addCriterion("mc_name =", value, "mcName");
            return (Criteria) this;
        }

        public Criteria andMcNameNotEqualTo(String value) {
            addCriterion("mc_name <>", value, "mcName");
            return (Criteria) this;
        }

        public Criteria andMcNameGreaterThan(String value) {
            addCriterion("mc_name >", value, "mcName");
            return (Criteria) this;
        }

        public Criteria andMcNameGreaterThanOrEqualTo(String value) {
            addCriterion("mc_name >=", value, "mcName");
            return (Criteria) this;
        }

        public Criteria andMcNameLessThan(String value) {
            addCriterion("mc_name <", value, "mcName");
            return (Criteria) this;
        }

        public Criteria andMcNameLessThanOrEqualTo(String value) {
            addCriterion("mc_name <=", value, "mcName");
            return (Criteria) this;
        }

        public Criteria andMcNameLike(String value) {
            addCriterion("mc_name like", value, "mcName");
            return (Criteria) this;
        }

        public Criteria andMcNameNotLike(String value) {
            addCriterion("mc_name not like", value, "mcName");
            return (Criteria) this;
        }

        public Criteria andMcNameIn(List<String> values) {
            addCriterion("mc_name in", values, "mcName");
            return (Criteria) this;
        }

        public Criteria andMcNameNotIn(List<String> values) {
            addCriterion("mc_name not in", values, "mcName");
            return (Criteria) this;
        }

        public Criteria andMcNameBetween(String value1, String value2) {
            addCriterion("mc_name between", value1, value2, "mcName");
            return (Criteria) this;
        }

        public Criteria andMcNameNotBetween(String value1, String value2) {
            addCriterion("mc_name not between", value1, value2, "mcName");
            return (Criteria) this;
        }

        public Criteria andMcDomainIsNull() {
            addCriterion("mc_domain is null");
            return (Criteria) this;
        }

        public Criteria andMcDomainIsNotNull() {
            addCriterion("mc_domain is not null");
            return (Criteria) this;
        }

        public Criteria andMcDomainEqualTo(String value) {
            addCriterion("mc_domain =", value, "mcDomain");
            return (Criteria) this;
        }

        public Criteria andMcDomainNotEqualTo(String value) {
            addCriterion("mc_domain <>", value, "mcDomain");
            return (Criteria) this;
        }

        public Criteria andMcDomainGreaterThan(String value) {
            addCriterion("mc_domain >", value, "mcDomain");
            return (Criteria) this;
        }

        public Criteria andMcDomainGreaterThanOrEqualTo(String value) {
            addCriterion("mc_domain >=", value, "mcDomain");
            return (Criteria) this;
        }

        public Criteria andMcDomainLessThan(String value) {
            addCriterion("mc_domain <", value, "mcDomain");
            return (Criteria) this;
        }

        public Criteria andMcDomainLessThanOrEqualTo(String value) {
            addCriterion("mc_domain <=", value, "mcDomain");
            return (Criteria) this;
        }

        public Criteria andMcDomainLike(String value) {
            addCriterion("mc_domain like", value, "mcDomain");
            return (Criteria) this;
        }

        public Criteria andMcDomainNotLike(String value) {
            addCriterion("mc_domain not like", value, "mcDomain");
            return (Criteria) this;
        }

        public Criteria andMcDomainIn(List<String> values) {
            addCriterion("mc_domain in", values, "mcDomain");
            return (Criteria) this;
        }

        public Criteria andMcDomainNotIn(List<String> values) {
            addCriterion("mc_domain not in", values, "mcDomain");
            return (Criteria) this;
        }

        public Criteria andMcDomainBetween(String value1, String value2) {
            addCriterion("mc_domain between", value1, value2, "mcDomain");
            return (Criteria) this;
        }

        public Criteria andMcDomainNotBetween(String value1, String value2) {
            addCriterion("mc_domain not between", value1, value2, "mcDomain");
            return (Criteria) this;
        }

        public Criteria andMcLogoIsNull() {
            addCriterion("mc_logo is null");
            return (Criteria) this;
        }

        public Criteria andMcLogoIsNotNull() {
            addCriterion("mc_logo is not null");
            return (Criteria) this;
        }

        public Criteria andMcLogoEqualTo(String value) {
            addCriterion("mc_logo =", value, "mcLogo");
            return (Criteria) this;
        }

        public Criteria andMcLogoNotEqualTo(String value) {
            addCriterion("mc_logo <>", value, "mcLogo");
            return (Criteria) this;
        }

        public Criteria andMcLogoGreaterThan(String value) {
            addCriterion("mc_logo >", value, "mcLogo");
            return (Criteria) this;
        }

        public Criteria andMcLogoGreaterThanOrEqualTo(String value) {
            addCriterion("mc_logo >=", value, "mcLogo");
            return (Criteria) this;
        }

        public Criteria andMcLogoLessThan(String value) {
            addCriterion("mc_logo <", value, "mcLogo");
            return (Criteria) this;
        }

        public Criteria andMcLogoLessThanOrEqualTo(String value) {
            addCriterion("mc_logo <=", value, "mcLogo");
            return (Criteria) this;
        }

        public Criteria andMcLogoLike(String value) {
            addCriterion("mc_logo like", value, "mcLogo");
            return (Criteria) this;
        }

        public Criteria andMcLogoNotLike(String value) {
            addCriterion("mc_logo not like", value, "mcLogo");
            return (Criteria) this;
        }

        public Criteria andMcLogoIn(List<String> values) {
            addCriterion("mc_logo in", values, "mcLogo");
            return (Criteria) this;
        }

        public Criteria andMcLogoNotIn(List<String> values) {
            addCriterion("mc_logo not in", values, "mcLogo");
            return (Criteria) this;
        }

        public Criteria andMcLogoBetween(String value1, String value2) {
            addCriterion("mc_logo between", value1, value2, "mcLogo");
            return (Criteria) this;
        }

        public Criteria andMcLogoNotBetween(String value1, String value2) {
            addCriterion("mc_logo not between", value1, value2, "mcLogo");
            return (Criteria) this;
        }

        public Criteria andMcSTimeIsNull() {
            addCriterion("mc_s_time is null");
            return (Criteria) this;
        }

        public Criteria andMcSTimeIsNotNull() {
            addCriterion("mc_s_time is not null");
            return (Criteria) this;
        }

        public Criteria andMcSTimeEqualTo(Date value) {
            addCriterion("mc_s_time =", value, "mcSTime");
            return (Criteria) this;
        }

        public Criteria andMcSTimeNotEqualTo(Date value) {
            addCriterion("mc_s_time <>", value, "mcSTime");
            return (Criteria) this;
        }

        public Criteria andMcSTimeGreaterThan(Date value) {
            addCriterion("mc_s_time >", value, "mcSTime");
            return (Criteria) this;
        }

        public Criteria andMcSTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("mc_s_time >=", value, "mcSTime");
            return (Criteria) this;
        }

        public Criteria andMcSTimeLessThan(Date value) {
            addCriterion("mc_s_time <", value, "mcSTime");
            return (Criteria) this;
        }

        public Criteria andMcSTimeLessThanOrEqualTo(Date value) {
            addCriterion("mc_s_time <=", value, "mcSTime");
            return (Criteria) this;
        }

        public Criteria andMcSTimeIn(List<Date> values) {
            addCriterion("mc_s_time in", values, "mcSTime");
            return (Criteria) this;
        }

        public Criteria andMcSTimeNotIn(List<Date> values) {
            addCriterion("mc_s_time not in", values, "mcSTime");
            return (Criteria) this;
        }

        public Criteria andMcSTimeBetween(Date value1, Date value2) {
            addCriterion("mc_s_time between", value1, value2, "mcSTime");
            return (Criteria) this;
        }

        public Criteria andMcSTimeNotBetween(Date value1, Date value2) {
            addCriterion("mc_s_time not between", value1, value2, "mcSTime");
            return (Criteria) this;
        }

        public Criteria andMcETimeIsNull() {
            addCriterion("mc_e_time is null");
            return (Criteria) this;
        }

        public Criteria andMcETimeIsNotNull() {
            addCriterion("mc_e_time is not null");
            return (Criteria) this;
        }

        public Criteria andMcETimeEqualTo(Date value) {
            addCriterion("mc_e_time =", value, "mcETime");
            return (Criteria) this;
        }

        public Criteria andMcETimeNotEqualTo(Date value) {
            addCriterion("mc_e_time <>", value, "mcETime");
            return (Criteria) this;
        }

        public Criteria andMcETimeGreaterThan(Date value) {
            addCriterion("mc_e_time >", value, "mcETime");
            return (Criteria) this;
        }

        public Criteria andMcETimeGreaterThanOrEqualTo(Date value) {
            addCriterion("mc_e_time >=", value, "mcETime");
            return (Criteria) this;
        }

        public Criteria andMcETimeLessThan(Date value) {
            addCriterion("mc_e_time <", value, "mcETime");
            return (Criteria) this;
        }

        public Criteria andMcETimeLessThanOrEqualTo(Date value) {
            addCriterion("mc_e_time <=", value, "mcETime");
            return (Criteria) this;
        }

        public Criteria andMcETimeIn(List<Date> values) {
            addCriterion("mc_e_time in", values, "mcETime");
            return (Criteria) this;
        }

        public Criteria andMcETimeNotIn(List<Date> values) {
            addCriterion("mc_e_time not in", values, "mcETime");
            return (Criteria) this;
        }

        public Criteria andMcETimeBetween(Date value1, Date value2) {
            addCriterion("mc_e_time between", value1, value2, "mcETime");
            return (Criteria) this;
        }

        public Criteria andMcETimeNotBetween(Date value1, Date value2) {
            addCriterion("mc_e_time not between", value1, value2, "mcETime");
            return (Criteria) this;
        }

        public Criteria andMcPayFeeIsNull() {
            addCriterion("mc_pay_fee is null");
            return (Criteria) this;
        }

        public Criteria andMcPayFeeIsNotNull() {
            addCriterion("mc_pay_fee is not null");
            return (Criteria) this;
        }

        public Criteria andMcPayFeeEqualTo(Integer value) {
            addCriterion("mc_pay_fee =", value, "mcPayFee");
            return (Criteria) this;
        }

        public Criteria andMcPayFeeNotEqualTo(Integer value) {
            addCriterion("mc_pay_fee <>", value, "mcPayFee");
            return (Criteria) this;
        }

        public Criteria andMcPayFeeGreaterThan(Integer value) {
            addCriterion("mc_pay_fee >", value, "mcPayFee");
            return (Criteria) this;
        }

        public Criteria andMcPayFeeGreaterThanOrEqualTo(Integer value) {
            addCriterion("mc_pay_fee >=", value, "mcPayFee");
            return (Criteria) this;
        }

        public Criteria andMcPayFeeLessThan(Integer value) {
            addCriterion("mc_pay_fee <", value, "mcPayFee");
            return (Criteria) this;
        }

        public Criteria andMcPayFeeLessThanOrEqualTo(Integer value) {
            addCriterion("mc_pay_fee <=", value, "mcPayFee");
            return (Criteria) this;
        }

        public Criteria andMcPayFeeIn(List<Integer> values) {
            addCriterion("mc_pay_fee in", values, "mcPayFee");
            return (Criteria) this;
        }

        public Criteria andMcPayFeeNotIn(List<Integer> values) {
            addCriterion("mc_pay_fee not in", values, "mcPayFee");
            return (Criteria) this;
        }

        public Criteria andMcPayFeeBetween(Integer value1, Integer value2) {
            addCriterion("mc_pay_fee between", value1, value2, "mcPayFee");
            return (Criteria) this;
        }

        public Criteria andMcPayFeeNotBetween(Integer value1, Integer value2) {
            addCriterion("mc_pay_fee not between", value1, value2, "mcPayFee");
            return (Criteria) this;
        }

        public Criteria andMcSmsCountIsNull() {
            addCriterion("mc_sms_count is null");
            return (Criteria) this;
        }

        public Criteria andMcSmsCountIsNotNull() {
            addCriterion("mc_sms_count is not null");
            return (Criteria) this;
        }

        public Criteria andMcSmsCountEqualTo(Integer value) {
            addCriterion("mc_sms_count =", value, "mcSmsCount");
            return (Criteria) this;
        }

        public Criteria andMcSmsCountNotEqualTo(Integer value) {
            addCriterion("mc_sms_count <>", value, "mcSmsCount");
            return (Criteria) this;
        }

        public Criteria andMcSmsCountGreaterThan(Integer value) {
            addCriterion("mc_sms_count >", value, "mcSmsCount");
            return (Criteria) this;
        }

        public Criteria andMcSmsCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("mc_sms_count >=", value, "mcSmsCount");
            return (Criteria) this;
        }

        public Criteria andMcSmsCountLessThan(Integer value) {
            addCriterion("mc_sms_count <", value, "mcSmsCount");
            return (Criteria) this;
        }

        public Criteria andMcSmsCountLessThanOrEqualTo(Integer value) {
            addCriterion("mc_sms_count <=", value, "mcSmsCount");
            return (Criteria) this;
        }

        public Criteria andMcSmsCountIn(List<Integer> values) {
            addCriterion("mc_sms_count in", values, "mcSmsCount");
            return (Criteria) this;
        }

        public Criteria andMcSmsCountNotIn(List<Integer> values) {
            addCriterion("mc_sms_count not in", values, "mcSmsCount");
            return (Criteria) this;
        }

        public Criteria andMcSmsCountBetween(Integer value1, Integer value2) {
            addCriterion("mc_sms_count between", value1, value2, "mcSmsCount");
            return (Criteria) this;
        }

        public Criteria andMcSmsCountNotBetween(Integer value1, Integer value2) {
            addCriterion("mc_sms_count not between", value1, value2, "mcSmsCount");
            return (Criteria) this;
        }

        public Criteria andMcCustNameIsNull() {
            addCriterion("mc_cust_name is null");
            return (Criteria) this;
        }

        public Criteria andMcCustNameIsNotNull() {
            addCriterion("mc_cust_name is not null");
            return (Criteria) this;
        }

        public Criteria andMcCustNameEqualTo(String value) {
            addCriterion("mc_cust_name =", value, "mcCustName");
            return (Criteria) this;
        }

        public Criteria andMcCustNameNotEqualTo(String value) {
            addCriterion("mc_cust_name <>", value, "mcCustName");
            return (Criteria) this;
        }

        public Criteria andMcCustNameGreaterThan(String value) {
            addCriterion("mc_cust_name >", value, "mcCustName");
            return (Criteria) this;
        }

        public Criteria andMcCustNameGreaterThanOrEqualTo(String value) {
            addCriterion("mc_cust_name >=", value, "mcCustName");
            return (Criteria) this;
        }

        public Criteria andMcCustNameLessThan(String value) {
            addCriterion("mc_cust_name <", value, "mcCustName");
            return (Criteria) this;
        }

        public Criteria andMcCustNameLessThanOrEqualTo(String value) {
            addCriterion("mc_cust_name <=", value, "mcCustName");
            return (Criteria) this;
        }

        public Criteria andMcCustNameLike(String value) {
            addCriterion("mc_cust_name like", value, "mcCustName");
            return (Criteria) this;
        }

        public Criteria andMcCustNameNotLike(String value) {
            addCriterion("mc_cust_name not like", value, "mcCustName");
            return (Criteria) this;
        }

        public Criteria andMcCustNameIn(List<String> values) {
            addCriterion("mc_cust_name in", values, "mcCustName");
            return (Criteria) this;
        }

        public Criteria andMcCustNameNotIn(List<String> values) {
            addCriterion("mc_cust_name not in", values, "mcCustName");
            return (Criteria) this;
        }

        public Criteria andMcCustNameBetween(String value1, String value2) {
            addCriterion("mc_cust_name between", value1, value2, "mcCustName");
            return (Criteria) this;
        }

        public Criteria andMcCustNameNotBetween(String value1, String value2) {
            addCriterion("mc_cust_name not between", value1, value2, "mcCustName");
            return (Criteria) this;
        }

        public Criteria andMcLinkManIsNull() {
            addCriterion("mc_link_man is null");
            return (Criteria) this;
        }

        public Criteria andMcLinkManIsNotNull() {
            addCriterion("mc_link_man is not null");
            return (Criteria) this;
        }

        public Criteria andMcLinkManEqualTo(String value) {
            addCriterion("mc_link_man =", value, "mcLinkMan");
            return (Criteria) this;
        }

        public Criteria andMcLinkManNotEqualTo(String value) {
            addCriterion("mc_link_man <>", value, "mcLinkMan");
            return (Criteria) this;
        }

        public Criteria andMcLinkManGreaterThan(String value) {
            addCriterion("mc_link_man >", value, "mcLinkMan");
            return (Criteria) this;
        }

        public Criteria andMcLinkManGreaterThanOrEqualTo(String value) {
            addCriterion("mc_link_man >=", value, "mcLinkMan");
            return (Criteria) this;
        }

        public Criteria andMcLinkManLessThan(String value) {
            addCriterion("mc_link_man <", value, "mcLinkMan");
            return (Criteria) this;
        }

        public Criteria andMcLinkManLessThanOrEqualTo(String value) {
            addCriterion("mc_link_man <=", value, "mcLinkMan");
            return (Criteria) this;
        }

        public Criteria andMcLinkManLike(String value) {
            addCriterion("mc_link_man like", value, "mcLinkMan");
            return (Criteria) this;
        }

        public Criteria andMcLinkManNotLike(String value) {
            addCriterion("mc_link_man not like", value, "mcLinkMan");
            return (Criteria) this;
        }

        public Criteria andMcLinkManIn(List<String> values) {
            addCriterion("mc_link_man in", values, "mcLinkMan");
            return (Criteria) this;
        }

        public Criteria andMcLinkManNotIn(List<String> values) {
            addCriterion("mc_link_man not in", values, "mcLinkMan");
            return (Criteria) this;
        }

        public Criteria andMcLinkManBetween(String value1, String value2) {
            addCriterion("mc_link_man between", value1, value2, "mcLinkMan");
            return (Criteria) this;
        }

        public Criteria andMcLinkManNotBetween(String value1, String value2) {
            addCriterion("mc_link_man not between", value1, value2, "mcLinkMan");
            return (Criteria) this;
        }

        public Criteria andMcCustDescIsNull() {
            addCriterion("mc_cust_desc is null");
            return (Criteria) this;
        }

        public Criteria andMcCustDescIsNotNull() {
            addCriterion("mc_cust_desc is not null");
            return (Criteria) this;
        }

        public Criteria andMcCustDescEqualTo(String value) {
            addCriterion("mc_cust_desc =", value, "mcCustDesc");
            return (Criteria) this;
        }

        public Criteria andMcCustDescNotEqualTo(String value) {
            addCriterion("mc_cust_desc <>", value, "mcCustDesc");
            return (Criteria) this;
        }

        public Criteria andMcCustDescGreaterThan(String value) {
            addCriterion("mc_cust_desc >", value, "mcCustDesc");
            return (Criteria) this;
        }

        public Criteria andMcCustDescGreaterThanOrEqualTo(String value) {
            addCriterion("mc_cust_desc >=", value, "mcCustDesc");
            return (Criteria) this;
        }

        public Criteria andMcCustDescLessThan(String value) {
            addCriterion("mc_cust_desc <", value, "mcCustDesc");
            return (Criteria) this;
        }

        public Criteria andMcCustDescLessThanOrEqualTo(String value) {
            addCriterion("mc_cust_desc <=", value, "mcCustDesc");
            return (Criteria) this;
        }

        public Criteria andMcCustDescLike(String value) {
            addCriterion("mc_cust_desc like", value, "mcCustDesc");
            return (Criteria) this;
        }

        public Criteria andMcCustDescNotLike(String value) {
            addCriterion("mc_cust_desc not like", value, "mcCustDesc");
            return (Criteria) this;
        }

        public Criteria andMcCustDescIn(List<String> values) {
            addCriterion("mc_cust_desc in", values, "mcCustDesc");
            return (Criteria) this;
        }

        public Criteria andMcCustDescNotIn(List<String> values) {
            addCriterion("mc_cust_desc not in", values, "mcCustDesc");
            return (Criteria) this;
        }

        public Criteria andMcCustDescBetween(String value1, String value2) {
            addCriterion("mc_cust_desc between", value1, value2, "mcCustDesc");
            return (Criteria) this;
        }

        public Criteria andMcCustDescNotBetween(String value1, String value2) {
            addCriterion("mc_cust_desc not between", value1, value2, "mcCustDesc");
            return (Criteria) this;
        }

        public Criteria andMcLinkMobileIsNull() {
            addCriterion("mc_link_mobile is null");
            return (Criteria) this;
        }

        public Criteria andMcLinkMobileIsNotNull() {
            addCriterion("mc_link_mobile is not null");
            return (Criteria) this;
        }

        public Criteria andMcLinkMobileEqualTo(String value) {
            addCriterion("mc_link_mobile =", value, "mcLinkMobile");
            return (Criteria) this;
        }

        public Criteria andMcLinkMobileNotEqualTo(String value) {
            addCriterion("mc_link_mobile <>", value, "mcLinkMobile");
            return (Criteria) this;
        }

        public Criteria andMcLinkMobileGreaterThan(String value) {
            addCriterion("mc_link_mobile >", value, "mcLinkMobile");
            return (Criteria) this;
        }

        public Criteria andMcLinkMobileGreaterThanOrEqualTo(String value) {
            addCriterion("mc_link_mobile >=", value, "mcLinkMobile");
            return (Criteria) this;
        }

        public Criteria andMcLinkMobileLessThan(String value) {
            addCriterion("mc_link_mobile <", value, "mcLinkMobile");
            return (Criteria) this;
        }

        public Criteria andMcLinkMobileLessThanOrEqualTo(String value) {
            addCriterion("mc_link_mobile <=", value, "mcLinkMobile");
            return (Criteria) this;
        }

        public Criteria andMcLinkMobileLike(String value) {
            addCriterion("mc_link_mobile like", value, "mcLinkMobile");
            return (Criteria) this;
        }

        public Criteria andMcLinkMobileNotLike(String value) {
            addCriterion("mc_link_mobile not like", value, "mcLinkMobile");
            return (Criteria) this;
        }

        public Criteria andMcLinkMobileIn(List<String> values) {
            addCriterion("mc_link_mobile in", values, "mcLinkMobile");
            return (Criteria) this;
        }

        public Criteria andMcLinkMobileNotIn(List<String> values) {
            addCriterion("mc_link_mobile not in", values, "mcLinkMobile");
            return (Criteria) this;
        }

        public Criteria andMcLinkMobileBetween(String value1, String value2) {
            addCriterion("mc_link_mobile between", value1, value2, "mcLinkMobile");
            return (Criteria) this;
        }

        public Criteria andMcLinkMobileNotBetween(String value1, String value2) {
            addCriterion("mc_link_mobile not between", value1, value2, "mcLinkMobile");
            return (Criteria) this;
        }

        public Criteria andMcLinkQqIsNull() {
            addCriterion("mc_link_qq is null");
            return (Criteria) this;
        }

        public Criteria andMcLinkQqIsNotNull() {
            addCriterion("mc_link_qq is not null");
            return (Criteria) this;
        }

        public Criteria andMcLinkQqEqualTo(String value) {
            addCriterion("mc_link_qq =", value, "mcLinkQq");
            return (Criteria) this;
        }

        public Criteria andMcLinkQqNotEqualTo(String value) {
            addCriterion("mc_link_qq <>", value, "mcLinkQq");
            return (Criteria) this;
        }

        public Criteria andMcLinkQqGreaterThan(String value) {
            addCriterion("mc_link_qq >", value, "mcLinkQq");
            return (Criteria) this;
        }

        public Criteria andMcLinkQqGreaterThanOrEqualTo(String value) {
            addCriterion("mc_link_qq >=", value, "mcLinkQq");
            return (Criteria) this;
        }

        public Criteria andMcLinkQqLessThan(String value) {
            addCriterion("mc_link_qq <", value, "mcLinkQq");
            return (Criteria) this;
        }

        public Criteria andMcLinkQqLessThanOrEqualTo(String value) {
            addCriterion("mc_link_qq <=", value, "mcLinkQq");
            return (Criteria) this;
        }

        public Criteria andMcLinkQqLike(String value) {
            addCriterion("mc_link_qq like", value, "mcLinkQq");
            return (Criteria) this;
        }

        public Criteria andMcLinkQqNotLike(String value) {
            addCriterion("mc_link_qq not like", value, "mcLinkQq");
            return (Criteria) this;
        }

        public Criteria andMcLinkQqIn(List<String> values) {
            addCriterion("mc_link_qq in", values, "mcLinkQq");
            return (Criteria) this;
        }

        public Criteria andMcLinkQqNotIn(List<String> values) {
            addCriterion("mc_link_qq not in", values, "mcLinkQq");
            return (Criteria) this;
        }

        public Criteria andMcLinkQqBetween(String value1, String value2) {
            addCriterion("mc_link_qq between", value1, value2, "mcLinkQq");
            return (Criteria) this;
        }

        public Criteria andMcLinkQqNotBetween(String value1, String value2) {
            addCriterion("mc_link_qq not between", value1, value2, "mcLinkQq");
            return (Criteria) this;
        }

        public Criteria andMcLinkEmailIsNull() {
            addCriterion("mc_link_email is null");
            return (Criteria) this;
        }

        public Criteria andMcLinkEmailIsNotNull() {
            addCriterion("mc_link_email is not null");
            return (Criteria) this;
        }

        public Criteria andMcLinkEmailEqualTo(String value) {
            addCriterion("mc_link_email =", value, "mcLinkEmail");
            return (Criteria) this;
        }

        public Criteria andMcLinkEmailNotEqualTo(String value) {
            addCriterion("mc_link_email <>", value, "mcLinkEmail");
            return (Criteria) this;
        }

        public Criteria andMcLinkEmailGreaterThan(String value) {
            addCriterion("mc_link_email >", value, "mcLinkEmail");
            return (Criteria) this;
        }

        public Criteria andMcLinkEmailGreaterThanOrEqualTo(String value) {
            addCriterion("mc_link_email >=", value, "mcLinkEmail");
            return (Criteria) this;
        }

        public Criteria andMcLinkEmailLessThan(String value) {
            addCriterion("mc_link_email <", value, "mcLinkEmail");
            return (Criteria) this;
        }

        public Criteria andMcLinkEmailLessThanOrEqualTo(String value) {
            addCriterion("mc_link_email <=", value, "mcLinkEmail");
            return (Criteria) this;
        }

        public Criteria andMcLinkEmailLike(String value) {
            addCriterion("mc_link_email like", value, "mcLinkEmail");
            return (Criteria) this;
        }

        public Criteria andMcLinkEmailNotLike(String value) {
            addCriterion("mc_link_email not like", value, "mcLinkEmail");
            return (Criteria) this;
        }

        public Criteria andMcLinkEmailIn(List<String> values) {
            addCriterion("mc_link_email in", values, "mcLinkEmail");
            return (Criteria) this;
        }

        public Criteria andMcLinkEmailNotIn(List<String> values) {
            addCriterion("mc_link_email not in", values, "mcLinkEmail");
            return (Criteria) this;
        }

        public Criteria andMcLinkEmailBetween(String value1, String value2) {
            addCriterion("mc_link_email between", value1, value2, "mcLinkEmail");
            return (Criteria) this;
        }

        public Criteria andMcLinkEmailNotBetween(String value1, String value2) {
            addCriterion("mc_link_email not between", value1, value2, "mcLinkEmail");
            return (Criteria) this;
        }

        public Criteria andMcLinkFaxIsNull() {
            addCriterion("mc_link_fax is null");
            return (Criteria) this;
        }

        public Criteria andMcLinkFaxIsNotNull() {
            addCriterion("mc_link_fax is not null");
            return (Criteria) this;
        }

        public Criteria andMcLinkFaxEqualTo(String value) {
            addCriterion("mc_link_fax =", value, "mcLinkFax");
            return (Criteria) this;
        }

        public Criteria andMcLinkFaxNotEqualTo(String value) {
            addCriterion("mc_link_fax <>", value, "mcLinkFax");
            return (Criteria) this;
        }

        public Criteria andMcLinkFaxGreaterThan(String value) {
            addCriterion("mc_link_fax >", value, "mcLinkFax");
            return (Criteria) this;
        }

        public Criteria andMcLinkFaxGreaterThanOrEqualTo(String value) {
            addCriterion("mc_link_fax >=", value, "mcLinkFax");
            return (Criteria) this;
        }

        public Criteria andMcLinkFaxLessThan(String value) {
            addCriterion("mc_link_fax <", value, "mcLinkFax");
            return (Criteria) this;
        }

        public Criteria andMcLinkFaxLessThanOrEqualTo(String value) {
            addCriterion("mc_link_fax <=", value, "mcLinkFax");
            return (Criteria) this;
        }

        public Criteria andMcLinkFaxLike(String value) {
            addCriterion("mc_link_fax like", value, "mcLinkFax");
            return (Criteria) this;
        }

        public Criteria andMcLinkFaxNotLike(String value) {
            addCriterion("mc_link_fax not like", value, "mcLinkFax");
            return (Criteria) this;
        }

        public Criteria andMcLinkFaxIn(List<String> values) {
            addCriterion("mc_link_fax in", values, "mcLinkFax");
            return (Criteria) this;
        }

        public Criteria andMcLinkFaxNotIn(List<String> values) {
            addCriterion("mc_link_fax not in", values, "mcLinkFax");
            return (Criteria) this;
        }

        public Criteria andMcLinkFaxBetween(String value1, String value2) {
            addCriterion("mc_link_fax between", value1, value2, "mcLinkFax");
            return (Criteria) this;
        }

        public Criteria andMcLinkFaxNotBetween(String value1, String value2) {
            addCriterion("mc_link_fax not between", value1, value2, "mcLinkFax");
            return (Criteria) this;
        }

        public Criteria andMcAddressIsNull() {
            addCriterion("mc_address is null");
            return (Criteria) this;
        }

        public Criteria andMcAddressIsNotNull() {
            addCriterion("mc_address is not null");
            return (Criteria) this;
        }

        public Criteria andMcAddressEqualTo(String value) {
            addCriterion("mc_address =", value, "mcAddress");
            return (Criteria) this;
        }

        public Criteria andMcAddressNotEqualTo(String value) {
            addCriterion("mc_address <>", value, "mcAddress");
            return (Criteria) this;
        }

        public Criteria andMcAddressGreaterThan(String value) {
            addCriterion("mc_address >", value, "mcAddress");
            return (Criteria) this;
        }

        public Criteria andMcAddressGreaterThanOrEqualTo(String value) {
            addCriterion("mc_address >=", value, "mcAddress");
            return (Criteria) this;
        }

        public Criteria andMcAddressLessThan(String value) {
            addCriterion("mc_address <", value, "mcAddress");
            return (Criteria) this;
        }

        public Criteria andMcAddressLessThanOrEqualTo(String value) {
            addCriterion("mc_address <=", value, "mcAddress");
            return (Criteria) this;
        }

        public Criteria andMcAddressLike(String value) {
            addCriterion("mc_address like", value, "mcAddress");
            return (Criteria) this;
        }

        public Criteria andMcAddressNotLike(String value) {
            addCriterion("mc_address not like", value, "mcAddress");
            return (Criteria) this;
        }

        public Criteria andMcAddressIn(List<String> values) {
            addCriterion("mc_address in", values, "mcAddress");
            return (Criteria) this;
        }

        public Criteria andMcAddressNotIn(List<String> values) {
            addCriterion("mc_address not in", values, "mcAddress");
            return (Criteria) this;
        }

        public Criteria andMcAddressBetween(String value1, String value2) {
            addCriterion("mc_address between", value1, value2, "mcAddress");
            return (Criteria) this;
        }

        public Criteria andMcAddressNotBetween(String value1, String value2) {
            addCriterion("mc_address not between", value1, value2, "mcAddress");
            return (Criteria) this;
        }

        public Criteria andMcAlipayAccountIsNull() {
            addCriterion("mc_alipay_account is null");
            return (Criteria) this;
        }

        public Criteria andMcAlipayAccountIsNotNull() {
            addCriterion("mc_alipay_account is not null");
            return (Criteria) this;
        }

        public Criteria andMcAlipayAccountEqualTo(String value) {
            addCriterion("mc_alipay_account =", value, "mcAlipayAccount");
            return (Criteria) this;
        }

        public Criteria andMcAlipayAccountNotEqualTo(String value) {
            addCriterion("mc_alipay_account <>", value, "mcAlipayAccount");
            return (Criteria) this;
        }

        public Criteria andMcAlipayAccountGreaterThan(String value) {
            addCriterion("mc_alipay_account >", value, "mcAlipayAccount");
            return (Criteria) this;
        }

        public Criteria andMcAlipayAccountGreaterThanOrEqualTo(String value) {
            addCriterion("mc_alipay_account >=", value, "mcAlipayAccount");
            return (Criteria) this;
        }

        public Criteria andMcAlipayAccountLessThan(String value) {
            addCriterion("mc_alipay_account <", value, "mcAlipayAccount");
            return (Criteria) this;
        }

        public Criteria andMcAlipayAccountLessThanOrEqualTo(String value) {
            addCriterion("mc_alipay_account <=", value, "mcAlipayAccount");
            return (Criteria) this;
        }

        public Criteria andMcAlipayAccountLike(String value) {
            addCriterion("mc_alipay_account like", value, "mcAlipayAccount");
            return (Criteria) this;
        }

        public Criteria andMcAlipayAccountNotLike(String value) {
            addCriterion("mc_alipay_account not like", value, "mcAlipayAccount");
            return (Criteria) this;
        }

        public Criteria andMcAlipayAccountIn(List<String> values) {
            addCriterion("mc_alipay_account in", values, "mcAlipayAccount");
            return (Criteria) this;
        }

        public Criteria andMcAlipayAccountNotIn(List<String> values) {
            addCriterion("mc_alipay_account not in", values, "mcAlipayAccount");
            return (Criteria) this;
        }

        public Criteria andMcAlipayAccountBetween(String value1, String value2) {
            addCriterion("mc_alipay_account between", value1, value2, "mcAlipayAccount");
            return (Criteria) this;
        }

        public Criteria andMcAlipayAccountNotBetween(String value1, String value2) {
            addCriterion("mc_alipay_account not between", value1, value2, "mcAlipayAccount");
            return (Criteria) this;
        }

        public Criteria andMcAlipayNameIsNull() {
            addCriterion("mc_alipay_name is null");
            return (Criteria) this;
        }

        public Criteria andMcAlipayNameIsNotNull() {
            addCriterion("mc_alipay_name is not null");
            return (Criteria) this;
        }

        public Criteria andMcAlipayNameEqualTo(String value) {
            addCriterion("mc_alipay_name =", value, "mcAlipayName");
            return (Criteria) this;
        }

        public Criteria andMcAlipayNameNotEqualTo(String value) {
            addCriterion("mc_alipay_name <>", value, "mcAlipayName");
            return (Criteria) this;
        }

        public Criteria andMcAlipayNameGreaterThan(String value) {
            addCriterion("mc_alipay_name >", value, "mcAlipayName");
            return (Criteria) this;
        }

        public Criteria andMcAlipayNameGreaterThanOrEqualTo(String value) {
            addCriterion("mc_alipay_name >=", value, "mcAlipayName");
            return (Criteria) this;
        }

        public Criteria andMcAlipayNameLessThan(String value) {
            addCriterion("mc_alipay_name <", value, "mcAlipayName");
            return (Criteria) this;
        }

        public Criteria andMcAlipayNameLessThanOrEqualTo(String value) {
            addCriterion("mc_alipay_name <=", value, "mcAlipayName");
            return (Criteria) this;
        }

        public Criteria andMcAlipayNameLike(String value) {
            addCriterion("mc_alipay_name like", value, "mcAlipayName");
            return (Criteria) this;
        }

        public Criteria andMcAlipayNameNotLike(String value) {
            addCriterion("mc_alipay_name not like", value, "mcAlipayName");
            return (Criteria) this;
        }

        public Criteria andMcAlipayNameIn(List<String> values) {
            addCriterion("mc_alipay_name in", values, "mcAlipayName");
            return (Criteria) this;
        }

        public Criteria andMcAlipayNameNotIn(List<String> values) {
            addCriterion("mc_alipay_name not in", values, "mcAlipayName");
            return (Criteria) this;
        }

        public Criteria andMcAlipayNameBetween(String value1, String value2) {
            addCriterion("mc_alipay_name between", value1, value2, "mcAlipayName");
            return (Criteria) this;
        }

        public Criteria andMcAlipayNameNotBetween(String value1, String value2) {
            addCriterion("mc_alipay_name not between", value1, value2, "mcAlipayName");
            return (Criteria) this;
        }

        public Criteria andMcBankProvinceIsNull() {
            addCriterion("mc_bank_province is null");
            return (Criteria) this;
        }

        public Criteria andMcBankProvinceIsNotNull() {
            addCriterion("mc_bank_province is not null");
            return (Criteria) this;
        }

        public Criteria andMcBankProvinceEqualTo(String value) {
            addCriterion("mc_bank_province =", value, "mcBankProvince");
            return (Criteria) this;
        }

        public Criteria andMcBankProvinceNotEqualTo(String value) {
            addCriterion("mc_bank_province <>", value, "mcBankProvince");
            return (Criteria) this;
        }

        public Criteria andMcBankProvinceGreaterThan(String value) {
            addCriterion("mc_bank_province >", value, "mcBankProvince");
            return (Criteria) this;
        }

        public Criteria andMcBankProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("mc_bank_province >=", value, "mcBankProvince");
            return (Criteria) this;
        }

        public Criteria andMcBankProvinceLessThan(String value) {
            addCriterion("mc_bank_province <", value, "mcBankProvince");
            return (Criteria) this;
        }

        public Criteria andMcBankProvinceLessThanOrEqualTo(String value) {
            addCriterion("mc_bank_province <=", value, "mcBankProvince");
            return (Criteria) this;
        }

        public Criteria andMcBankProvinceLike(String value) {
            addCriterion("mc_bank_province like", value, "mcBankProvince");
            return (Criteria) this;
        }

        public Criteria andMcBankProvinceNotLike(String value) {
            addCriterion("mc_bank_province not like", value, "mcBankProvince");
            return (Criteria) this;
        }

        public Criteria andMcBankProvinceIn(List<String> values) {
            addCriterion("mc_bank_province in", values, "mcBankProvince");
            return (Criteria) this;
        }

        public Criteria andMcBankProvinceNotIn(List<String> values) {
            addCriterion("mc_bank_province not in", values, "mcBankProvince");
            return (Criteria) this;
        }

        public Criteria andMcBankProvinceBetween(String value1, String value2) {
            addCriterion("mc_bank_province between", value1, value2, "mcBankProvince");
            return (Criteria) this;
        }

        public Criteria andMcBankProvinceNotBetween(String value1, String value2) {
            addCriterion("mc_bank_province not between", value1, value2, "mcBankProvince");
            return (Criteria) this;
        }

        public Criteria andMcBankCityIsNull() {
            addCriterion("mc_bank_city is null");
            return (Criteria) this;
        }

        public Criteria andMcBankCityIsNotNull() {
            addCriterion("mc_bank_city is not null");
            return (Criteria) this;
        }

        public Criteria andMcBankCityEqualTo(String value) {
            addCriterion("mc_bank_city =", value, "mcBankCity");
            return (Criteria) this;
        }

        public Criteria andMcBankCityNotEqualTo(String value) {
            addCriterion("mc_bank_city <>", value, "mcBankCity");
            return (Criteria) this;
        }

        public Criteria andMcBankCityGreaterThan(String value) {
            addCriterion("mc_bank_city >", value, "mcBankCity");
            return (Criteria) this;
        }

        public Criteria andMcBankCityGreaterThanOrEqualTo(String value) {
            addCriterion("mc_bank_city >=", value, "mcBankCity");
            return (Criteria) this;
        }

        public Criteria andMcBankCityLessThan(String value) {
            addCriterion("mc_bank_city <", value, "mcBankCity");
            return (Criteria) this;
        }

        public Criteria andMcBankCityLessThanOrEqualTo(String value) {
            addCriterion("mc_bank_city <=", value, "mcBankCity");
            return (Criteria) this;
        }

        public Criteria andMcBankCityLike(String value) {
            addCriterion("mc_bank_city like", value, "mcBankCity");
            return (Criteria) this;
        }

        public Criteria andMcBankCityNotLike(String value) {
            addCriterion("mc_bank_city not like", value, "mcBankCity");
            return (Criteria) this;
        }

        public Criteria andMcBankCityIn(List<String> values) {
            addCriterion("mc_bank_city in", values, "mcBankCity");
            return (Criteria) this;
        }

        public Criteria andMcBankCityNotIn(List<String> values) {
            addCriterion("mc_bank_city not in", values, "mcBankCity");
            return (Criteria) this;
        }

        public Criteria andMcBankCityBetween(String value1, String value2) {
            addCriterion("mc_bank_city between", value1, value2, "mcBankCity");
            return (Criteria) this;
        }

        public Criteria andMcBankCityNotBetween(String value1, String value2) {
            addCriterion("mc_bank_city not between", value1, value2, "mcBankCity");
            return (Criteria) this;
        }

        public Criteria andMcBankDistrictIsNull() {
            addCriterion("mc_bank_district is null");
            return (Criteria) this;
        }

        public Criteria andMcBankDistrictIsNotNull() {
            addCriterion("mc_bank_district is not null");
            return (Criteria) this;
        }

        public Criteria andMcBankDistrictEqualTo(String value) {
            addCriterion("mc_bank_district =", value, "mcBankDistrict");
            return (Criteria) this;
        }

        public Criteria andMcBankDistrictNotEqualTo(String value) {
            addCriterion("mc_bank_district <>", value, "mcBankDistrict");
            return (Criteria) this;
        }

        public Criteria andMcBankDistrictGreaterThan(String value) {
            addCriterion("mc_bank_district >", value, "mcBankDistrict");
            return (Criteria) this;
        }

        public Criteria andMcBankDistrictGreaterThanOrEqualTo(String value) {
            addCriterion("mc_bank_district >=", value, "mcBankDistrict");
            return (Criteria) this;
        }

        public Criteria andMcBankDistrictLessThan(String value) {
            addCriterion("mc_bank_district <", value, "mcBankDistrict");
            return (Criteria) this;
        }

        public Criteria andMcBankDistrictLessThanOrEqualTo(String value) {
            addCriterion("mc_bank_district <=", value, "mcBankDistrict");
            return (Criteria) this;
        }

        public Criteria andMcBankDistrictLike(String value) {
            addCriterion("mc_bank_district like", value, "mcBankDistrict");
            return (Criteria) this;
        }

        public Criteria andMcBankDistrictNotLike(String value) {
            addCriterion("mc_bank_district not like", value, "mcBankDistrict");
            return (Criteria) this;
        }

        public Criteria andMcBankDistrictIn(List<String> values) {
            addCriterion("mc_bank_district in", values, "mcBankDistrict");
            return (Criteria) this;
        }

        public Criteria andMcBankDistrictNotIn(List<String> values) {
            addCriterion("mc_bank_district not in", values, "mcBankDistrict");
            return (Criteria) this;
        }

        public Criteria andMcBankDistrictBetween(String value1, String value2) {
            addCriterion("mc_bank_district between", value1, value2, "mcBankDistrict");
            return (Criteria) this;
        }

        public Criteria andMcBankDistrictNotBetween(String value1, String value2) {
            addCriterion("mc_bank_district not between", value1, value2, "mcBankDistrict");
            return (Criteria) this;
        }

        public Criteria andMcBankAccountIsNull() {
            addCriterion("mc_bank_account is null");
            return (Criteria) this;
        }

        public Criteria andMcBankAccountIsNotNull() {
            addCriterion("mc_bank_account is not null");
            return (Criteria) this;
        }

        public Criteria andMcBankAccountEqualTo(String value) {
            addCriterion("mc_bank_account =", value, "mcBankAccount");
            return (Criteria) this;
        }

        public Criteria andMcBankAccountNotEqualTo(String value) {
            addCriterion("mc_bank_account <>", value, "mcBankAccount");
            return (Criteria) this;
        }

        public Criteria andMcBankAccountGreaterThan(String value) {
            addCriterion("mc_bank_account >", value, "mcBankAccount");
            return (Criteria) this;
        }

        public Criteria andMcBankAccountGreaterThanOrEqualTo(String value) {
            addCriterion("mc_bank_account >=", value, "mcBankAccount");
            return (Criteria) this;
        }

        public Criteria andMcBankAccountLessThan(String value) {
            addCriterion("mc_bank_account <", value, "mcBankAccount");
            return (Criteria) this;
        }

        public Criteria andMcBankAccountLessThanOrEqualTo(String value) {
            addCriterion("mc_bank_account <=", value, "mcBankAccount");
            return (Criteria) this;
        }

        public Criteria andMcBankAccountLike(String value) {
            addCriterion("mc_bank_account like", value, "mcBankAccount");
            return (Criteria) this;
        }

        public Criteria andMcBankAccountNotLike(String value) {
            addCriterion("mc_bank_account not like", value, "mcBankAccount");
            return (Criteria) this;
        }

        public Criteria andMcBankAccountIn(List<String> values) {
            addCriterion("mc_bank_account in", values, "mcBankAccount");
            return (Criteria) this;
        }

        public Criteria andMcBankAccountNotIn(List<String> values) {
            addCriterion("mc_bank_account not in", values, "mcBankAccount");
            return (Criteria) this;
        }

        public Criteria andMcBankAccountBetween(String value1, String value2) {
            addCriterion("mc_bank_account between", value1, value2, "mcBankAccount");
            return (Criteria) this;
        }

        public Criteria andMcBankAccountNotBetween(String value1, String value2) {
            addCriterion("mc_bank_account not between", value1, value2, "mcBankAccount");
            return (Criteria) this;
        }

        public Criteria andMcBankNameIsNull() {
            addCriterion("mc_bank_name is null");
            return (Criteria) this;
        }

        public Criteria andMcBankNameIsNotNull() {
            addCriterion("mc_bank_name is not null");
            return (Criteria) this;
        }

        public Criteria andMcBankNameEqualTo(String value) {
            addCriterion("mc_bank_name =", value, "mcBankName");
            return (Criteria) this;
        }

        public Criteria andMcBankNameNotEqualTo(String value) {
            addCriterion("mc_bank_name <>", value, "mcBankName");
            return (Criteria) this;
        }

        public Criteria andMcBankNameGreaterThan(String value) {
            addCriterion("mc_bank_name >", value, "mcBankName");
            return (Criteria) this;
        }

        public Criteria andMcBankNameGreaterThanOrEqualTo(String value) {
            addCriterion("mc_bank_name >=", value, "mcBankName");
            return (Criteria) this;
        }

        public Criteria andMcBankNameLessThan(String value) {
            addCriterion("mc_bank_name <", value, "mcBankName");
            return (Criteria) this;
        }

        public Criteria andMcBankNameLessThanOrEqualTo(String value) {
            addCriterion("mc_bank_name <=", value, "mcBankName");
            return (Criteria) this;
        }

        public Criteria andMcBankNameLike(String value) {
            addCriterion("mc_bank_name like", value, "mcBankName");
            return (Criteria) this;
        }

        public Criteria andMcBankNameNotLike(String value) {
            addCriterion("mc_bank_name not like", value, "mcBankName");
            return (Criteria) this;
        }

        public Criteria andMcBankNameIn(List<String> values) {
            addCriterion("mc_bank_name in", values, "mcBankName");
            return (Criteria) this;
        }

        public Criteria andMcBankNameNotIn(List<String> values) {
            addCriterion("mc_bank_name not in", values, "mcBankName");
            return (Criteria) this;
        }

        public Criteria andMcBankNameBetween(String value1, String value2) {
            addCriterion("mc_bank_name between", value1, value2, "mcBankName");
            return (Criteria) this;
        }

        public Criteria andMcBankNameNotBetween(String value1, String value2) {
            addCriterion("mc_bank_name not between", value1, value2, "mcBankName");
            return (Criteria) this;
        }

        public Criteria andMcBankAcnameIsNull() {
            addCriterion("mc_bank_acname is null");
            return (Criteria) this;
        }

        public Criteria andMcBankAcnameIsNotNull() {
            addCriterion("mc_bank_acname is not null");
            return (Criteria) this;
        }

        public Criteria andMcBankAcnameEqualTo(String value) {
            addCriterion("mc_bank_acname =", value, "mcBankAcname");
            return (Criteria) this;
        }

        public Criteria andMcBankAcnameNotEqualTo(String value) {
            addCriterion("mc_bank_acname <>", value, "mcBankAcname");
            return (Criteria) this;
        }

        public Criteria andMcBankAcnameGreaterThan(String value) {
            addCriterion("mc_bank_acname >", value, "mcBankAcname");
            return (Criteria) this;
        }

        public Criteria andMcBankAcnameGreaterThanOrEqualTo(String value) {
            addCriterion("mc_bank_acname >=", value, "mcBankAcname");
            return (Criteria) this;
        }

        public Criteria andMcBankAcnameLessThan(String value) {
            addCriterion("mc_bank_acname <", value, "mcBankAcname");
            return (Criteria) this;
        }

        public Criteria andMcBankAcnameLessThanOrEqualTo(String value) {
            addCriterion("mc_bank_acname <=", value, "mcBankAcname");
            return (Criteria) this;
        }

        public Criteria andMcBankAcnameLike(String value) {
            addCriterion("mc_bank_acname like", value, "mcBankAcname");
            return (Criteria) this;
        }

        public Criteria andMcBankAcnameNotLike(String value) {
            addCriterion("mc_bank_acname not like", value, "mcBankAcname");
            return (Criteria) this;
        }

        public Criteria andMcBankAcnameIn(List<String> values) {
            addCriterion("mc_bank_acname in", values, "mcBankAcname");
            return (Criteria) this;
        }

        public Criteria andMcBankAcnameNotIn(List<String> values) {
            addCriterion("mc_bank_acname not in", values, "mcBankAcname");
            return (Criteria) this;
        }

        public Criteria andMcBankAcnameBetween(String value1, String value2) {
            addCriterion("mc_bank_acname between", value1, value2, "mcBankAcname");
            return (Criteria) this;
        }

        public Criteria andMcBankAcnameNotBetween(String value1, String value2) {
            addCriterion("mc_bank_acname not between", value1, value2, "mcBankAcname");
            return (Criteria) this;
        }

        public Criteria andMcStypeIsNull() {
            addCriterion("mc_stype is null");
            return (Criteria) this;
        }

        public Criteria andMcStypeIsNotNull() {
            addCriterion("mc_stype is not null");
            return (Criteria) this;
        }

        public Criteria andMcStypeEqualTo(Integer value) {
            addCriterion("mc_stype =", value, "mcStype");
            return (Criteria) this;
        }

        public Criteria andMcStypeNotEqualTo(Integer value) {
            addCriterion("mc_stype <>", value, "mcStype");
            return (Criteria) this;
        }

        public Criteria andMcStypeGreaterThan(Integer value) {
            addCriterion("mc_stype >", value, "mcStype");
            return (Criteria) this;
        }

        public Criteria andMcStypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("mc_stype >=", value, "mcStype");
            return (Criteria) this;
        }

        public Criteria andMcStypeLessThan(Integer value) {
            addCriterion("mc_stype <", value, "mcStype");
            return (Criteria) this;
        }

        public Criteria andMcStypeLessThanOrEqualTo(Integer value) {
            addCriterion("mc_stype <=", value, "mcStype");
            return (Criteria) this;
        }

        public Criteria andMcStypeIn(List<Integer> values) {
            addCriterion("mc_stype in", values, "mcStype");
            return (Criteria) this;
        }

        public Criteria andMcStypeNotIn(List<Integer> values) {
            addCriterion("mc_stype not in", values, "mcStype");
            return (Criteria) this;
        }

        public Criteria andMcStypeBetween(Integer value1, Integer value2) {
            addCriterion("mc_stype between", value1, value2, "mcStype");
            return (Criteria) this;
        }

        public Criteria andMcStypeNotBetween(Integer value1, Integer value2) {
            addCriterion("mc_stype not between", value1, value2, "mcStype");
            return (Criteria) this;
        }

        public Criteria andMcTypeIsNull() {
            addCriterion("mc_type is null");
            return (Criteria) this;
        }

        public Criteria andMcTypeIsNotNull() {
            addCriterion("mc_type is not null");
            return (Criteria) this;
        }

        public Criteria andMcTypeEqualTo(Integer value) {
            addCriterion("mc_type =", value, "mcType");
            return (Criteria) this;
        }

        public Criteria andMcTypeNotEqualTo(Integer value) {
            addCriterion("mc_type <>", value, "mcType");
            return (Criteria) this;
        }

        public Criteria andMcTypeGreaterThan(Integer value) {
            addCriterion("mc_type >", value, "mcType");
            return (Criteria) this;
        }

        public Criteria andMcTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("mc_type >=", value, "mcType");
            return (Criteria) this;
        }

        public Criteria andMcTypeLessThan(Integer value) {
            addCriterion("mc_type <", value, "mcType");
            return (Criteria) this;
        }

        public Criteria andMcTypeLessThanOrEqualTo(Integer value) {
            addCriterion("mc_type <=", value, "mcType");
            return (Criteria) this;
        }

        public Criteria andMcTypeIn(List<Integer> values) {
            addCriterion("mc_type in", values, "mcType");
            return (Criteria) this;
        }

        public Criteria andMcTypeNotIn(List<Integer> values) {
            addCriterion("mc_type not in", values, "mcType");
            return (Criteria) this;
        }

        public Criteria andMcTypeBetween(Integer value1, Integer value2) {
            addCriterion("mc_type between", value1, value2, "mcType");
            return (Criteria) this;
        }

        public Criteria andMcTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("mc_type not between", value1, value2, "mcType");
            return (Criteria) this;
        }

        public Criteria andMcPIdIsNull() {
            addCriterion("mc_p_id is null");
            return (Criteria) this;
        }

        public Criteria andMcPIdIsNotNull() {
            addCriterion("mc_p_id is not null");
            return (Criteria) this;
        }

        public Criteria andMcPIdEqualTo(Integer value) {
            addCriterion("mc_p_id =", value, "mcPId");
            return (Criteria) this;
        }

        public Criteria andMcPIdNotEqualTo(Integer value) {
            addCriterion("mc_p_id <>", value, "mcPId");
            return (Criteria) this;
        }

        public Criteria andMcPIdGreaterThan(Integer value) {
            addCriterion("mc_p_id >", value, "mcPId");
            return (Criteria) this;
        }

        public Criteria andMcPIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("mc_p_id >=", value, "mcPId");
            return (Criteria) this;
        }

        public Criteria andMcPIdLessThan(Integer value) {
            addCriterion("mc_p_id <", value, "mcPId");
            return (Criteria) this;
        }

        public Criteria andMcPIdLessThanOrEqualTo(Integer value) {
            addCriterion("mc_p_id <=", value, "mcPId");
            return (Criteria) this;
        }

        public Criteria andMcPIdIn(List<Integer> values) {
            addCriterion("mc_p_id in", values, "mcPId");
            return (Criteria) this;
        }

        public Criteria andMcPIdNotIn(List<Integer> values) {
            addCriterion("mc_p_id not in", values, "mcPId");
            return (Criteria) this;
        }

        public Criteria andMcPIdBetween(Integer value1, Integer value2) {
            addCriterion("mc_p_id between", value1, value2, "mcPId");
            return (Criteria) this;
        }

        public Criteria andMcPIdNotBetween(Integer value1, Integer value2) {
            addCriterion("mc_p_id not between", value1, value2, "mcPId");
            return (Criteria) this;
        }

        public Criteria andMcSignimgIsNull() {
            addCriterion("mc_signimg is null");
            return (Criteria) this;
        }

        public Criteria andMcSignimgIsNotNull() {
            addCriterion("mc_signimg is not null");
            return (Criteria) this;
        }

        public Criteria andMcSignimgEqualTo(String value) {
            addCriterion("mc_signimg =", value, "mcSignimg");
            return (Criteria) this;
        }

        public Criteria andMcSignimgNotEqualTo(String value) {
            addCriterion("mc_signimg <>", value, "mcSignimg");
            return (Criteria) this;
        }

        public Criteria andMcSignimgGreaterThan(String value) {
            addCriterion("mc_signimg >", value, "mcSignimg");
            return (Criteria) this;
        }

        public Criteria andMcSignimgGreaterThanOrEqualTo(String value) {
            addCriterion("mc_signimg >=", value, "mcSignimg");
            return (Criteria) this;
        }

        public Criteria andMcSignimgLessThan(String value) {
            addCriterion("mc_signimg <", value, "mcSignimg");
            return (Criteria) this;
        }

        public Criteria andMcSignimgLessThanOrEqualTo(String value) {
            addCriterion("mc_signimg <=", value, "mcSignimg");
            return (Criteria) this;
        }

        public Criteria andMcSignimgLike(String value) {
            addCriterion("mc_signimg like", value, "mcSignimg");
            return (Criteria) this;
        }

        public Criteria andMcSignimgNotLike(String value) {
            addCriterion("mc_signimg not like", value, "mcSignimg");
            return (Criteria) this;
        }

        public Criteria andMcSignimgIn(List<String> values) {
            addCriterion("mc_signimg in", values, "mcSignimg");
            return (Criteria) this;
        }

        public Criteria andMcSignimgNotIn(List<String> values) {
            addCriterion("mc_signimg not in", values, "mcSignimg");
            return (Criteria) this;
        }

        public Criteria andMcSignimgBetween(String value1, String value2) {
            addCriterion("mc_signimg between", value1, value2, "mcSignimg");
            return (Criteria) this;
        }

        public Criteria andMcSignimgNotBetween(String value1, String value2) {
            addCriterion("mc_signimg not between", value1, value2, "mcSignimg");
            return (Criteria) this;
        }

        public Criteria andMcDbLinkIsNull() {
            addCriterion("mc_db_link is null");
            return (Criteria) this;
        }

        public Criteria andMcDbLinkIsNotNull() {
            addCriterion("mc_db_link is not null");
            return (Criteria) this;
        }

        public Criteria andMcDbLinkEqualTo(String value) {
            addCriterion("mc_db_link =", value, "mcDbLink");
            return (Criteria) this;
        }

        public Criteria andMcDbLinkNotEqualTo(String value) {
            addCriterion("mc_db_link <>", value, "mcDbLink");
            return (Criteria) this;
        }

        public Criteria andMcDbLinkGreaterThan(String value) {
            addCriterion("mc_db_link >", value, "mcDbLink");
            return (Criteria) this;
        }

        public Criteria andMcDbLinkGreaterThanOrEqualTo(String value) {
            addCriterion("mc_db_link >=", value, "mcDbLink");
            return (Criteria) this;
        }

        public Criteria andMcDbLinkLessThan(String value) {
            addCriterion("mc_db_link <", value, "mcDbLink");
            return (Criteria) this;
        }

        public Criteria andMcDbLinkLessThanOrEqualTo(String value) {
            addCriterion("mc_db_link <=", value, "mcDbLink");
            return (Criteria) this;
        }

        public Criteria andMcDbLinkLike(String value) {
            addCriterion("mc_db_link like", value, "mcDbLink");
            return (Criteria) this;
        }

        public Criteria andMcDbLinkNotLike(String value) {
            addCriterion("mc_db_link not like", value, "mcDbLink");
            return (Criteria) this;
        }

        public Criteria andMcDbLinkIn(List<String> values) {
            addCriterion("mc_db_link in", values, "mcDbLink");
            return (Criteria) this;
        }

        public Criteria andMcDbLinkNotIn(List<String> values) {
            addCriterion("mc_db_link not in", values, "mcDbLink");
            return (Criteria) this;
        }

        public Criteria andMcDbLinkBetween(String value1, String value2) {
            addCriterion("mc_db_link between", value1, value2, "mcDbLink");
            return (Criteria) this;
        }

        public Criteria andMcDbLinkNotBetween(String value1, String value2) {
            addCriterion("mc_db_link not between", value1, value2, "mcDbLink");
            return (Criteria) this;
        }

        public Criteria andMcLoginAccountIsNull() {
            addCriterion("mc_login_account is null");
            return (Criteria) this;
        }

        public Criteria andMcLoginAccountIsNotNull() {
            addCriterion("mc_login_account is not null");
            return (Criteria) this;
        }

        public Criteria andMcLoginAccountEqualTo(String value) {
            addCriterion("mc_login_account =", value, "mcLoginAccount");
            return (Criteria) this;
        }

        public Criteria andMcLoginAccountNotEqualTo(String value) {
            addCriterion("mc_login_account <>", value, "mcLoginAccount");
            return (Criteria) this;
        }

        public Criteria andMcLoginAccountGreaterThan(String value) {
            addCriterion("mc_login_account >", value, "mcLoginAccount");
            return (Criteria) this;
        }

        public Criteria andMcLoginAccountGreaterThanOrEqualTo(String value) {
            addCriterion("mc_login_account >=", value, "mcLoginAccount");
            return (Criteria) this;
        }

        public Criteria andMcLoginAccountLessThan(String value) {
            addCriterion("mc_login_account <", value, "mcLoginAccount");
            return (Criteria) this;
        }

        public Criteria andMcLoginAccountLessThanOrEqualTo(String value) {
            addCriterion("mc_login_account <=", value, "mcLoginAccount");
            return (Criteria) this;
        }

        public Criteria andMcLoginAccountLike(String value) {
            addCriterion("mc_login_account like", value, "mcLoginAccount");
            return (Criteria) this;
        }

        public Criteria andMcLoginAccountNotLike(String value) {
            addCriterion("mc_login_account not like", value, "mcLoginAccount");
            return (Criteria) this;
        }

        public Criteria andMcLoginAccountIn(List<String> values) {
            addCriterion("mc_login_account in", values, "mcLoginAccount");
            return (Criteria) this;
        }

        public Criteria andMcLoginAccountNotIn(List<String> values) {
            addCriterion("mc_login_account not in", values, "mcLoginAccount");
            return (Criteria) this;
        }

        public Criteria andMcLoginAccountBetween(String value1, String value2) {
            addCriterion("mc_login_account between", value1, value2, "mcLoginAccount");
            return (Criteria) this;
        }

        public Criteria andMcLoginAccountNotBetween(String value1, String value2) {
            addCriterion("mc_login_account not between", value1, value2, "mcLoginAccount");
            return (Criteria) this;
        }

        public Criteria andMcLoginPwdIsNull() {
            addCriterion("mc_login_pwd is null");
            return (Criteria) this;
        }

        public Criteria andMcLoginPwdIsNotNull() {
            addCriterion("mc_login_pwd is not null");
            return (Criteria) this;
        }

        public Criteria andMcLoginPwdEqualTo(String value) {
            addCriterion("mc_login_pwd =", value, "mcLoginPwd");
            return (Criteria) this;
        }

        public Criteria andMcLoginPwdNotEqualTo(String value) {
            addCriterion("mc_login_pwd <>", value, "mcLoginPwd");
            return (Criteria) this;
        }

        public Criteria andMcLoginPwdGreaterThan(String value) {
            addCriterion("mc_login_pwd >", value, "mcLoginPwd");
            return (Criteria) this;
        }

        public Criteria andMcLoginPwdGreaterThanOrEqualTo(String value) {
            addCriterion("mc_login_pwd >=", value, "mcLoginPwd");
            return (Criteria) this;
        }

        public Criteria andMcLoginPwdLessThan(String value) {
            addCriterion("mc_login_pwd <", value, "mcLoginPwd");
            return (Criteria) this;
        }

        public Criteria andMcLoginPwdLessThanOrEqualTo(String value) {
            addCriterion("mc_login_pwd <=", value, "mcLoginPwd");
            return (Criteria) this;
        }

        public Criteria andMcLoginPwdLike(String value) {
            addCriterion("mc_login_pwd like", value, "mcLoginPwd");
            return (Criteria) this;
        }

        public Criteria andMcLoginPwdNotLike(String value) {
            addCriterion("mc_login_pwd not like", value, "mcLoginPwd");
            return (Criteria) this;
        }

        public Criteria andMcLoginPwdIn(List<String> values) {
            addCriterion("mc_login_pwd in", values, "mcLoginPwd");
            return (Criteria) this;
        }

        public Criteria andMcLoginPwdNotIn(List<String> values) {
            addCriterion("mc_login_pwd not in", values, "mcLoginPwd");
            return (Criteria) this;
        }

        public Criteria andMcLoginPwdBetween(String value1, String value2) {
            addCriterion("mc_login_pwd between", value1, value2, "mcLoginPwd");
            return (Criteria) this;
        }

        public Criteria andMcLoginPwdNotBetween(String value1, String value2) {
            addCriterion("mc_login_pwd not between", value1, value2, "mcLoginPwd");
            return (Criteria) this;
        }

        public Criteria andMcCustTypeIsNull() {
            addCriterion("mc_cust_type is null");
            return (Criteria) this;
        }

        public Criteria andMcCustTypeIsNotNull() {
            addCriterion("mc_cust_type is not null");
            return (Criteria) this;
        }

        public Criteria andMcCustTypeEqualTo(Integer value) {
            addCriterion("mc_cust_type =", value, "mcCustType");
            return (Criteria) this;
        }

        public Criteria andMcCustTypeNotEqualTo(Integer value) {
            addCriterion("mc_cust_type <>", value, "mcCustType");
            return (Criteria) this;
        }

        public Criteria andMcCustTypeGreaterThan(Integer value) {
            addCriterion("mc_cust_type >", value, "mcCustType");
            return (Criteria) this;
        }

        public Criteria andMcCustTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("mc_cust_type >=", value, "mcCustType");
            return (Criteria) this;
        }

        public Criteria andMcCustTypeLessThan(Integer value) {
            addCriterion("mc_cust_type <", value, "mcCustType");
            return (Criteria) this;
        }

        public Criteria andMcCustTypeLessThanOrEqualTo(Integer value) {
            addCriterion("mc_cust_type <=", value, "mcCustType");
            return (Criteria) this;
        }

        public Criteria andMcCustTypeIn(List<Integer> values) {
            addCriterion("mc_cust_type in", values, "mcCustType");
            return (Criteria) this;
        }

        public Criteria andMcCustTypeNotIn(List<Integer> values) {
            addCriterion("mc_cust_type not in", values, "mcCustType");
            return (Criteria) this;
        }

        public Criteria andMcCustTypeBetween(Integer value1, Integer value2) {
            addCriterion("mc_cust_type between", value1, value2, "mcCustType");
            return (Criteria) this;
        }

        public Criteria andMcCustTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("mc_cust_type not between", value1, value2, "mcCustType");
            return (Criteria) this;
        }

        public Criteria andMcAreaIdIsNull() {
            addCriterion("mc_area_id is null");
            return (Criteria) this;
        }

        public Criteria andMcAreaIdIsNotNull() {
            addCriterion("mc_area_id is not null");
            return (Criteria) this;
        }

        public Criteria andMcAreaIdEqualTo(Integer value) {
            addCriterion("mc_area_id =", value, "mcAreaId");
            return (Criteria) this;
        }

        public Criteria andMcAreaIdNotEqualTo(Integer value) {
            addCriterion("mc_area_id <>", value, "mcAreaId");
            return (Criteria) this;
        }

        public Criteria andMcAreaIdGreaterThan(Integer value) {
            addCriterion("mc_area_id >", value, "mcAreaId");
            return (Criteria) this;
        }

        public Criteria andMcAreaIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("mc_area_id >=", value, "mcAreaId");
            return (Criteria) this;
        }

        public Criteria andMcAreaIdLessThan(Integer value) {
            addCriterion("mc_area_id <", value, "mcAreaId");
            return (Criteria) this;
        }

        public Criteria andMcAreaIdLessThanOrEqualTo(Integer value) {
            addCriterion("mc_area_id <=", value, "mcAreaId");
            return (Criteria) this;
        }

        public Criteria andMcAreaIdIn(List<Integer> values) {
            addCriterion("mc_area_id in", values, "mcAreaId");
            return (Criteria) this;
        }

        public Criteria andMcAreaIdNotIn(List<Integer> values) {
            addCriterion("mc_area_id not in", values, "mcAreaId");
            return (Criteria) this;
        }

        public Criteria andMcAreaIdBetween(Integer value1, Integer value2) {
            addCriterion("mc_area_id between", value1, value2, "mcAreaId");
            return (Criteria) this;
        }

        public Criteria andMcAreaIdNotBetween(Integer value1, Integer value2) {
            addCriterion("mc_area_id not between", value1, value2, "mcAreaId");
            return (Criteria) this;
        }

        public Criteria andMcCTimeIsNull() {
            addCriterion("mc_c_time is null");
            return (Criteria) this;
        }

        public Criteria andMcCTimeIsNotNull() {
            addCriterion("mc_c_time is not null");
            return (Criteria) this;
        }

        public Criteria andMcCTimeEqualTo(Date value) {
            addCriterion("mc_c_time =", value, "mcCTime");
            return (Criteria) this;
        }

        public Criteria andMcCTimeNotEqualTo(Date value) {
            addCriterion("mc_c_time <>", value, "mcCTime");
            return (Criteria) this;
        }

        public Criteria andMcCTimeGreaterThan(Date value) {
            addCriterion("mc_c_time >", value, "mcCTime");
            return (Criteria) this;
        }

        public Criteria andMcCTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("mc_c_time >=", value, "mcCTime");
            return (Criteria) this;
        }

        public Criteria andMcCTimeLessThan(Date value) {
            addCriterion("mc_c_time <", value, "mcCTime");
            return (Criteria) this;
        }

        public Criteria andMcCTimeLessThanOrEqualTo(Date value) {
            addCriterion("mc_c_time <=", value, "mcCTime");
            return (Criteria) this;
        }

        public Criteria andMcCTimeIn(List<Date> values) {
            addCriterion("mc_c_time in", values, "mcCTime");
            return (Criteria) this;
        }

        public Criteria andMcCTimeNotIn(List<Date> values) {
            addCriterion("mc_c_time not in", values, "mcCTime");
            return (Criteria) this;
        }

        public Criteria andMcCTimeBetween(Date value1, Date value2) {
            addCriterion("mc_c_time between", value1, value2, "mcCTime");
            return (Criteria) this;
        }

        public Criteria andMcCTimeNotBetween(Date value1, Date value2) {
            addCriterion("mc_c_time not between", value1, value2, "mcCTime");
            return (Criteria) this;
        }

        public Criteria andMcStatusIsNull() {
            addCriterion("mc_status is null");
            return (Criteria) this;
        }

        public Criteria andMcStatusIsNotNull() {
            addCriterion("mc_status is not null");
            return (Criteria) this;
        }

        public Criteria andMcStatusEqualTo(Integer value) {
            addCriterion("mc_status =", value, "mcStatus");
            return (Criteria) this;
        }

        public Criteria andMcStatusNotEqualTo(Integer value) {
            addCriterion("mc_status <>", value, "mcStatus");
            return (Criteria) this;
        }

        public Criteria andMcStatusGreaterThan(Integer value) {
            addCriterion("mc_status >", value, "mcStatus");
            return (Criteria) this;
        }

        public Criteria andMcStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("mc_status >=", value, "mcStatus");
            return (Criteria) this;
        }

        public Criteria andMcStatusLessThan(Integer value) {
            addCriterion("mc_status <", value, "mcStatus");
            return (Criteria) this;
        }

        public Criteria andMcStatusLessThanOrEqualTo(Integer value) {
            addCriterion("mc_status <=", value, "mcStatus");
            return (Criteria) this;
        }

        public Criteria andMcStatusIn(List<Integer> values) {
            addCriterion("mc_status in", values, "mcStatus");
            return (Criteria) this;
        }

        public Criteria andMcStatusNotIn(List<Integer> values) {
            addCriterion("mc_status not in", values, "mcStatus");
            return (Criteria) this;
        }

        public Criteria andMcStatusBetween(Integer value1, Integer value2) {
            addCriterion("mc_status between", value1, value2, "mcStatus");
            return (Criteria) this;
        }

        public Criteria andMcStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("mc_status not between", value1, value2, "mcStatus");
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

        public Criteria andMcSmsStatusIsNull() {
            addCriterion("mc_sms_status is null");
            return (Criteria) this;
        }

        public Criteria andMcSmsStatusIsNotNull() {
            addCriterion("mc_sms_status is not null");
            return (Criteria) this;
        }

        public Criteria andMcSmsStatusEqualTo(Integer value) {
            addCriterion("mc_sms_status =", value, "mcSmsStatus");
            return (Criteria) this;
        }

        public Criteria andMcSmsStatusNotEqualTo(Integer value) {
            addCriterion("mc_sms_status <>", value, "mcSmsStatus");
            return (Criteria) this;
        }

        public Criteria andMcSmsStatusGreaterThan(Integer value) {
            addCriterion("mc_sms_status >", value, "mcSmsStatus");
            return (Criteria) this;
        }

        public Criteria andMcSmsStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("mc_sms_status >=", value, "mcSmsStatus");
            return (Criteria) this;
        }

        public Criteria andMcSmsStatusLessThan(Integer value) {
            addCriterion("mc_sms_status <", value, "mcSmsStatus");
            return (Criteria) this;
        }

        public Criteria andMcSmsStatusLessThanOrEqualTo(Integer value) {
            addCriterion("mc_sms_status <=", value, "mcSmsStatus");
            return (Criteria) this;
        }

        public Criteria andMcSmsStatusIn(List<Integer> values) {
            addCriterion("mc_sms_status in", values, "mcSmsStatus");
            return (Criteria) this;
        }

        public Criteria andMcSmsStatusNotIn(List<Integer> values) {
            addCriterion("mc_sms_status not in", values, "mcSmsStatus");
            return (Criteria) this;
        }

        public Criteria andMcSmsStatusBetween(Integer value1, Integer value2) {
            addCriterion("mc_sms_status between", value1, value2, "mcSmsStatus");
            return (Criteria) this;
        }

        public Criteria andMcSmsStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("mc_sms_status not between", value1, value2, "mcSmsStatus");
            return (Criteria) this;
        }

        public Criteria andMcNameLikeInsensitive(String value) {
            addCriterion("upper(mc_name) like", value.toUpperCase(), "mcName");
            return (Criteria) this;
        }

        public Criteria andMcDomainLikeInsensitive(String value) {
            addCriterion("upper(mc_domain) like", value.toUpperCase(), "mcDomain");
            return (Criteria) this;
        }

        public Criteria andMcLogoLikeInsensitive(String value) {
            addCriterion("upper(mc_logo) like", value.toUpperCase(), "mcLogo");
            return (Criteria) this;
        }

        public Criteria andMcCustNameLikeInsensitive(String value) {
            addCriterion("upper(mc_cust_name) like", value.toUpperCase(), "mcCustName");
            return (Criteria) this;
        }

        public Criteria andMcLinkManLikeInsensitive(String value) {
            addCriterion("upper(mc_link_man) like", value.toUpperCase(), "mcLinkMan");
            return (Criteria) this;
        }

        public Criteria andMcCustDescLikeInsensitive(String value) {
            addCriterion("upper(mc_cust_desc) like", value.toUpperCase(), "mcCustDesc");
            return (Criteria) this;
        }

        public Criteria andMcLinkMobileLikeInsensitive(String value) {
            addCriterion("upper(mc_link_mobile) like", value.toUpperCase(), "mcLinkMobile");
            return (Criteria) this;
        }

        public Criteria andMcLinkQqLikeInsensitive(String value) {
            addCriterion("upper(mc_link_qq) like", value.toUpperCase(), "mcLinkQq");
            return (Criteria) this;
        }

        public Criteria andMcLinkEmailLikeInsensitive(String value) {
            addCriterion("upper(mc_link_email) like", value.toUpperCase(), "mcLinkEmail");
            return (Criteria) this;
        }

        public Criteria andMcLinkFaxLikeInsensitive(String value) {
            addCriterion("upper(mc_link_fax) like", value.toUpperCase(), "mcLinkFax");
            return (Criteria) this;
        }

        public Criteria andMcAddressLikeInsensitive(String value) {
            addCriterion("upper(mc_address) like", value.toUpperCase(), "mcAddress");
            return (Criteria) this;
        }

        public Criteria andMcAlipayAccountLikeInsensitive(String value) {
            addCriterion("upper(mc_alipay_account) like", value.toUpperCase(), "mcAlipayAccount");
            return (Criteria) this;
        }

        public Criteria andMcAlipayNameLikeInsensitive(String value) {
            addCriterion("upper(mc_alipay_name) like", value.toUpperCase(), "mcAlipayName");
            return (Criteria) this;
        }

        public Criteria andMcBankProvinceLikeInsensitive(String value) {
            addCriterion("upper(mc_bank_province) like", value.toUpperCase(), "mcBankProvince");
            return (Criteria) this;
        }

        public Criteria andMcBankCityLikeInsensitive(String value) {
            addCriterion("upper(mc_bank_city) like", value.toUpperCase(), "mcBankCity");
            return (Criteria) this;
        }

        public Criteria andMcBankDistrictLikeInsensitive(String value) {
            addCriterion("upper(mc_bank_district) like", value.toUpperCase(), "mcBankDistrict");
            return (Criteria) this;
        }

        public Criteria andMcBankAccountLikeInsensitive(String value) {
            addCriterion("upper(mc_bank_account) like", value.toUpperCase(), "mcBankAccount");
            return (Criteria) this;
        }

        public Criteria andMcBankNameLikeInsensitive(String value) {
            addCriterion("upper(mc_bank_name) like", value.toUpperCase(), "mcBankName");
            return (Criteria) this;
        }

        public Criteria andMcBankAcnameLikeInsensitive(String value) {
            addCriterion("upper(mc_bank_acname) like", value.toUpperCase(), "mcBankAcname");
            return (Criteria) this;
        }

        public Criteria andMcSignimgLikeInsensitive(String value) {
            addCriterion("upper(mc_signimg) like", value.toUpperCase(), "mcSignimg");
            return (Criteria) this;
        }

        public Criteria andMcDbLinkLikeInsensitive(String value) {
            addCriterion("upper(mc_db_link) like", value.toUpperCase(), "mcDbLink");
            return (Criteria) this;
        }

        public Criteria andMcLoginAccountLikeInsensitive(String value) {
            addCriterion("upper(mc_login_account) like", value.toUpperCase(), "mcLoginAccount");
            return (Criteria) this;
        }

        public Criteria andMcLoginPwdLikeInsensitive(String value) {
            addCriterion("upper(mc_login_pwd) like", value.toUpperCase(), "mcLoginPwd");
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
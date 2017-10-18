package com.lol.common.model.ds0;

import com.lol.common.plugin.Page;
import java.util.ArrayList;
import java.util.List;

public class InfoAreaExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Page page;

    private String dialect = "mysql";

    public InfoAreaExample() {
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

        public Criteria andTreeIdIsNull() {
            addCriterion("TREE_ID is null");
            return (Criteria) this;
        }

        public Criteria andTreeIdIsNotNull() {
            addCriterion("TREE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTreeIdEqualTo(Integer value) {
            addCriterion("TREE_ID =", value, "treeId");
            return (Criteria) this;
        }

        public Criteria andTreeIdNotEqualTo(Integer value) {
            addCriterion("TREE_ID <>", value, "treeId");
            return (Criteria) this;
        }

        public Criteria andTreeIdGreaterThan(Integer value) {
            addCriterion("TREE_ID >", value, "treeId");
            return (Criteria) this;
        }

        public Criteria andTreeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("TREE_ID >=", value, "treeId");
            return (Criteria) this;
        }

        public Criteria andTreeIdLessThan(Integer value) {
            addCriterion("TREE_ID <", value, "treeId");
            return (Criteria) this;
        }

        public Criteria andTreeIdLessThanOrEqualTo(Integer value) {
            addCriterion("TREE_ID <=", value, "treeId");
            return (Criteria) this;
        }

        public Criteria andTreeIdIn(List<Integer> values) {
            addCriterion("TREE_ID in", values, "treeId");
            return (Criteria) this;
        }

        public Criteria andTreeIdNotIn(List<Integer> values) {
            addCriterion("TREE_ID not in", values, "treeId");
            return (Criteria) this;
        }

        public Criteria andTreeIdBetween(Integer value1, Integer value2) {
            addCriterion("TREE_ID between", value1, value2, "treeId");
            return (Criteria) this;
        }

        public Criteria andTreeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("TREE_ID not between", value1, value2, "treeId");
            return (Criteria) this;
        }

        public Criteria andTreeNameIsNull() {
            addCriterion("TREE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTreeNameIsNotNull() {
            addCriterion("TREE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTreeNameEqualTo(String value) {
            addCriterion("TREE_NAME =", value, "treeName");
            return (Criteria) this;
        }

        public Criteria andTreeNameNotEqualTo(String value) {
            addCriterion("TREE_NAME <>", value, "treeName");
            return (Criteria) this;
        }

        public Criteria andTreeNameGreaterThan(String value) {
            addCriterion("TREE_NAME >", value, "treeName");
            return (Criteria) this;
        }

        public Criteria andTreeNameGreaterThanOrEqualTo(String value) {
            addCriterion("TREE_NAME >=", value, "treeName");
            return (Criteria) this;
        }

        public Criteria andTreeNameLessThan(String value) {
            addCriterion("TREE_NAME <", value, "treeName");
            return (Criteria) this;
        }

        public Criteria andTreeNameLessThanOrEqualTo(String value) {
            addCriterion("TREE_NAME <=", value, "treeName");
            return (Criteria) this;
        }

        public Criteria andTreeNameLike(String value) {
            addCriterion("TREE_NAME like", value, "treeName");
            return (Criteria) this;
        }

        public Criteria andTreeNameNotLike(String value) {
            addCriterion("TREE_NAME not like", value, "treeName");
            return (Criteria) this;
        }

        public Criteria andTreeNameIn(List<String> values) {
            addCriterion("TREE_NAME in", values, "treeName");
            return (Criteria) this;
        }

        public Criteria andTreeNameNotIn(List<String> values) {
            addCriterion("TREE_NAME not in", values, "treeName");
            return (Criteria) this;
        }

        public Criteria andTreeNameBetween(String value1, String value2) {
            addCriterion("TREE_NAME between", value1, value2, "treeName");
            return (Criteria) this;
        }

        public Criteria andTreeNameNotBetween(String value1, String value2) {
            addCriterion("TREE_NAME not between", value1, value2, "treeName");
            return (Criteria) this;
        }

        public Criteria andParentidIsNull() {
            addCriterion("PARENTID is null");
            return (Criteria) this;
        }

        public Criteria andParentidIsNotNull() {
            addCriterion("PARENTID is not null");
            return (Criteria) this;
        }

        public Criteria andParentidEqualTo(Integer value) {
            addCriterion("PARENTID =", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotEqualTo(Integer value) {
            addCriterion("PARENTID <>", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidGreaterThan(Integer value) {
            addCriterion("PARENTID >", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidGreaterThanOrEqualTo(Integer value) {
            addCriterion("PARENTID >=", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidLessThan(Integer value) {
            addCriterion("PARENTID <", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidLessThanOrEqualTo(Integer value) {
            addCriterion("PARENTID <=", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidIn(List<Integer> values) {
            addCriterion("PARENTID in", values, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotIn(List<Integer> values) {
            addCriterion("PARENTID not in", values, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidBetween(Integer value1, Integer value2) {
            addCriterion("PARENTID between", value1, value2, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotBetween(Integer value1, Integer value2) {
            addCriterion("PARENTID not between", value1, value2, "parentid");
            return (Criteria) this;
        }

        public Criteria andIsleafIsNull() {
            addCriterion("ISLEAF is null");
            return (Criteria) this;
        }

        public Criteria andIsleafIsNotNull() {
            addCriterion("ISLEAF is not null");
            return (Criteria) this;
        }

        public Criteria andIsleafEqualTo(Integer value) {
            addCriterion("ISLEAF =", value, "isleaf");
            return (Criteria) this;
        }

        public Criteria andIsleafNotEqualTo(Integer value) {
            addCriterion("ISLEAF <>", value, "isleaf");
            return (Criteria) this;
        }

        public Criteria andIsleafGreaterThan(Integer value) {
            addCriterion("ISLEAF >", value, "isleaf");
            return (Criteria) this;
        }

        public Criteria andIsleafGreaterThanOrEqualTo(Integer value) {
            addCriterion("ISLEAF >=", value, "isleaf");
            return (Criteria) this;
        }

        public Criteria andIsleafLessThan(Integer value) {
            addCriterion("ISLEAF <", value, "isleaf");
            return (Criteria) this;
        }

        public Criteria andIsleafLessThanOrEqualTo(Integer value) {
            addCriterion("ISLEAF <=", value, "isleaf");
            return (Criteria) this;
        }

        public Criteria andIsleafIn(List<Integer> values) {
            addCriterion("ISLEAF in", values, "isleaf");
            return (Criteria) this;
        }

        public Criteria andIsleafNotIn(List<Integer> values) {
            addCriterion("ISLEAF not in", values, "isleaf");
            return (Criteria) this;
        }

        public Criteria andIsleafBetween(Integer value1, Integer value2) {
            addCriterion("ISLEAF between", value1, value2, "isleaf");
            return (Criteria) this;
        }

        public Criteria andIsleafNotBetween(Integer value1, Integer value2) {
            addCriterion("ISLEAF not between", value1, value2, "isleaf");
            return (Criteria) this;
        }

        public Criteria andEntIdIsNull() {
            addCriterion("ENT_ID is null");
            return (Criteria) this;
        }

        public Criteria andEntIdIsNotNull() {
            addCriterion("ENT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andEntIdEqualTo(Integer value) {
            addCriterion("ENT_ID =", value, "entId");
            return (Criteria) this;
        }

        public Criteria andEntIdNotEqualTo(Integer value) {
            addCriterion("ENT_ID <>", value, "entId");
            return (Criteria) this;
        }

        public Criteria andEntIdGreaterThan(Integer value) {
            addCriterion("ENT_ID >", value, "entId");
            return (Criteria) this;
        }

        public Criteria andEntIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ENT_ID >=", value, "entId");
            return (Criteria) this;
        }

        public Criteria andEntIdLessThan(Integer value) {
            addCriterion("ENT_ID <", value, "entId");
            return (Criteria) this;
        }

        public Criteria andEntIdLessThanOrEqualTo(Integer value) {
            addCriterion("ENT_ID <=", value, "entId");
            return (Criteria) this;
        }

        public Criteria andEntIdIn(List<Integer> values) {
            addCriterion("ENT_ID in", values, "entId");
            return (Criteria) this;
        }

        public Criteria andEntIdNotIn(List<Integer> values) {
            addCriterion("ENT_ID not in", values, "entId");
            return (Criteria) this;
        }

        public Criteria andEntIdBetween(Integer value1, Integer value2) {
            addCriterion("ENT_ID between", value1, value2, "entId");
            return (Criteria) this;
        }

        public Criteria andEntIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ENT_ID not between", value1, value2, "entId");
            return (Criteria) this;
        }

        public Criteria andTreeTypeIsNull() {
            addCriterion("TREE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTreeTypeIsNotNull() {
            addCriterion("TREE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTreeTypeEqualTo(Integer value) {
            addCriterion("TREE_TYPE =", value, "treeType");
            return (Criteria) this;
        }

        public Criteria andTreeTypeNotEqualTo(Integer value) {
            addCriterion("TREE_TYPE <>", value, "treeType");
            return (Criteria) this;
        }

        public Criteria andTreeTypeGreaterThan(Integer value) {
            addCriterion("TREE_TYPE >", value, "treeType");
            return (Criteria) this;
        }

        public Criteria andTreeTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("TREE_TYPE >=", value, "treeType");
            return (Criteria) this;
        }

        public Criteria andTreeTypeLessThan(Integer value) {
            addCriterion("TREE_TYPE <", value, "treeType");
            return (Criteria) this;
        }

        public Criteria andTreeTypeLessThanOrEqualTo(Integer value) {
            addCriterion("TREE_TYPE <=", value, "treeType");
            return (Criteria) this;
        }

        public Criteria andTreeTypeIn(List<Integer> values) {
            addCriterion("TREE_TYPE in", values, "treeType");
            return (Criteria) this;
        }

        public Criteria andTreeTypeNotIn(List<Integer> values) {
            addCriterion("TREE_TYPE not in", values, "treeType");
            return (Criteria) this;
        }

        public Criteria andTreeTypeBetween(Integer value1, Integer value2) {
            addCriterion("TREE_TYPE between", value1, value2, "treeType");
            return (Criteria) this;
        }

        public Criteria andTreeTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("TREE_TYPE not between", value1, value2, "treeType");
            return (Criteria) this;
        }

        public Criteria andSeqidIsNull() {
            addCriterion("SEQID is null");
            return (Criteria) this;
        }

        public Criteria andSeqidIsNotNull() {
            addCriterion("SEQID is not null");
            return (Criteria) this;
        }

        public Criteria andSeqidEqualTo(Integer value) {
            addCriterion("SEQID =", value, "seqid");
            return (Criteria) this;
        }

        public Criteria andSeqidNotEqualTo(Integer value) {
            addCriterion("SEQID <>", value, "seqid");
            return (Criteria) this;
        }

        public Criteria andSeqidGreaterThan(Integer value) {
            addCriterion("SEQID >", value, "seqid");
            return (Criteria) this;
        }

        public Criteria andSeqidGreaterThanOrEqualTo(Integer value) {
            addCriterion("SEQID >=", value, "seqid");
            return (Criteria) this;
        }

        public Criteria andSeqidLessThan(Integer value) {
            addCriterion("SEQID <", value, "seqid");
            return (Criteria) this;
        }

        public Criteria andSeqidLessThanOrEqualTo(Integer value) {
            addCriterion("SEQID <=", value, "seqid");
            return (Criteria) this;
        }

        public Criteria andSeqidIn(List<Integer> values) {
            addCriterion("SEQID in", values, "seqid");
            return (Criteria) this;
        }

        public Criteria andSeqidNotIn(List<Integer> values) {
            addCriterion("SEQID not in", values, "seqid");
            return (Criteria) this;
        }

        public Criteria andSeqidBetween(Integer value1, Integer value2) {
            addCriterion("SEQID between", value1, value2, "seqid");
            return (Criteria) this;
        }

        public Criteria andSeqidNotBetween(Integer value1, Integer value2) {
            addCriterion("SEQID not between", value1, value2, "seqid");
            return (Criteria) this;
        }

        public Criteria andMapimgIsNull() {
            addCriterion("MAPIMG is null");
            return (Criteria) this;
        }

        public Criteria andMapimgIsNotNull() {
            addCriterion("MAPIMG is not null");
            return (Criteria) this;
        }

        public Criteria andMapimgEqualTo(String value) {
            addCriterion("MAPIMG =", value, "mapimg");
            return (Criteria) this;
        }

        public Criteria andMapimgNotEqualTo(String value) {
            addCriterion("MAPIMG <>", value, "mapimg");
            return (Criteria) this;
        }

        public Criteria andMapimgGreaterThan(String value) {
            addCriterion("MAPIMG >", value, "mapimg");
            return (Criteria) this;
        }

        public Criteria andMapimgGreaterThanOrEqualTo(String value) {
            addCriterion("MAPIMG >=", value, "mapimg");
            return (Criteria) this;
        }

        public Criteria andMapimgLessThan(String value) {
            addCriterion("MAPIMG <", value, "mapimg");
            return (Criteria) this;
        }

        public Criteria andMapimgLessThanOrEqualTo(String value) {
            addCriterion("MAPIMG <=", value, "mapimg");
            return (Criteria) this;
        }

        public Criteria andMapimgLike(String value) {
            addCriterion("MAPIMG like", value, "mapimg");
            return (Criteria) this;
        }

        public Criteria andMapimgNotLike(String value) {
            addCriterion("MAPIMG not like", value, "mapimg");
            return (Criteria) this;
        }

        public Criteria andMapimgIn(List<String> values) {
            addCriterion("MAPIMG in", values, "mapimg");
            return (Criteria) this;
        }

        public Criteria andMapimgNotIn(List<String> values) {
            addCriterion("MAPIMG not in", values, "mapimg");
            return (Criteria) this;
        }

        public Criteria andMapimgBetween(String value1, String value2) {
            addCriterion("MAPIMG between", value1, value2, "mapimg");
            return (Criteria) this;
        }

        public Criteria andMapimgNotBetween(String value1, String value2) {
            addCriterion("MAPIMG not between", value1, value2, "mapimg");
            return (Criteria) this;
        }

        public Criteria andGuideIsNull() {
            addCriterion("GUIDE is null");
            return (Criteria) this;
        }

        public Criteria andGuideIsNotNull() {
            addCriterion("GUIDE is not null");
            return (Criteria) this;
        }

        public Criteria andGuideEqualTo(String value) {
            addCriterion("GUIDE =", value, "guide");
            return (Criteria) this;
        }

        public Criteria andGuideNotEqualTo(String value) {
            addCriterion("GUIDE <>", value, "guide");
            return (Criteria) this;
        }

        public Criteria andGuideGreaterThan(String value) {
            addCriterion("GUIDE >", value, "guide");
            return (Criteria) this;
        }

        public Criteria andGuideGreaterThanOrEqualTo(String value) {
            addCriterion("GUIDE >=", value, "guide");
            return (Criteria) this;
        }

        public Criteria andGuideLessThan(String value) {
            addCriterion("GUIDE <", value, "guide");
            return (Criteria) this;
        }

        public Criteria andGuideLessThanOrEqualTo(String value) {
            addCriterion("GUIDE <=", value, "guide");
            return (Criteria) this;
        }

        public Criteria andGuideLike(String value) {
            addCriterion("GUIDE like", value, "guide");
            return (Criteria) this;
        }

        public Criteria andGuideNotLike(String value) {
            addCriterion("GUIDE not like", value, "guide");
            return (Criteria) this;
        }

        public Criteria andGuideIn(List<String> values) {
            addCriterion("GUIDE in", values, "guide");
            return (Criteria) this;
        }

        public Criteria andGuideNotIn(List<String> values) {
            addCriterion("GUIDE not in", values, "guide");
            return (Criteria) this;
        }

        public Criteria andGuideBetween(String value1, String value2) {
            addCriterion("GUIDE between", value1, value2, "guide");
            return (Criteria) this;
        }

        public Criteria andGuideNotBetween(String value1, String value2) {
            addCriterion("GUIDE not between", value1, value2, "guide");
            return (Criteria) this;
        }

        public Criteria andEngnameIsNull() {
            addCriterion("ENGNAME is null");
            return (Criteria) this;
        }

        public Criteria andEngnameIsNotNull() {
            addCriterion("ENGNAME is not null");
            return (Criteria) this;
        }

        public Criteria andEngnameEqualTo(String value) {
            addCriterion("ENGNAME =", value, "engname");
            return (Criteria) this;
        }

        public Criteria andEngnameNotEqualTo(String value) {
            addCriterion("ENGNAME <>", value, "engname");
            return (Criteria) this;
        }

        public Criteria andEngnameGreaterThan(String value) {
            addCriterion("ENGNAME >", value, "engname");
            return (Criteria) this;
        }

        public Criteria andEngnameGreaterThanOrEqualTo(String value) {
            addCriterion("ENGNAME >=", value, "engname");
            return (Criteria) this;
        }

        public Criteria andEngnameLessThan(String value) {
            addCriterion("ENGNAME <", value, "engname");
            return (Criteria) this;
        }

        public Criteria andEngnameLessThanOrEqualTo(String value) {
            addCriterion("ENGNAME <=", value, "engname");
            return (Criteria) this;
        }

        public Criteria andEngnameLike(String value) {
            addCriterion("ENGNAME like", value, "engname");
            return (Criteria) this;
        }

        public Criteria andEngnameNotLike(String value) {
            addCriterion("ENGNAME not like", value, "engname");
            return (Criteria) this;
        }

        public Criteria andEngnameIn(List<String> values) {
            addCriterion("ENGNAME in", values, "engname");
            return (Criteria) this;
        }

        public Criteria andEngnameNotIn(List<String> values) {
            addCriterion("ENGNAME not in", values, "engname");
            return (Criteria) this;
        }

        public Criteria andEngnameBetween(String value1, String value2) {
            addCriterion("ENGNAME between", value1, value2, "engname");
            return (Criteria) this;
        }

        public Criteria andEngnameNotBetween(String value1, String value2) {
            addCriterion("ENGNAME not between", value1, value2, "engname");
            return (Criteria) this;
        }

        public Criteria andHrefIsNull() {
            addCriterion("HREF is null");
            return (Criteria) this;
        }

        public Criteria andHrefIsNotNull() {
            addCriterion("HREF is not null");
            return (Criteria) this;
        }

        public Criteria andHrefEqualTo(String value) {
            addCriterion("HREF =", value, "href");
            return (Criteria) this;
        }

        public Criteria andHrefNotEqualTo(String value) {
            addCriterion("HREF <>", value, "href");
            return (Criteria) this;
        }

        public Criteria andHrefGreaterThan(String value) {
            addCriterion("HREF >", value, "href");
            return (Criteria) this;
        }

        public Criteria andHrefGreaterThanOrEqualTo(String value) {
            addCriterion("HREF >=", value, "href");
            return (Criteria) this;
        }

        public Criteria andHrefLessThan(String value) {
            addCriterion("HREF <", value, "href");
            return (Criteria) this;
        }

        public Criteria andHrefLessThanOrEqualTo(String value) {
            addCriterion("HREF <=", value, "href");
            return (Criteria) this;
        }

        public Criteria andHrefLike(String value) {
            addCriterion("HREF like", value, "href");
            return (Criteria) this;
        }

        public Criteria andHrefNotLike(String value) {
            addCriterion("HREF not like", value, "href");
            return (Criteria) this;
        }

        public Criteria andHrefIn(List<String> values) {
            addCriterion("HREF in", values, "href");
            return (Criteria) this;
        }

        public Criteria andHrefNotIn(List<String> values) {
            addCriterion("HREF not in", values, "href");
            return (Criteria) this;
        }

        public Criteria andHrefBetween(String value1, String value2) {
            addCriterion("HREF between", value1, value2, "href");
            return (Criteria) this;
        }

        public Criteria andHrefNotBetween(String value1, String value2) {
            addCriterion("HREF not between", value1, value2, "href");
            return (Criteria) this;
        }

        public Criteria andSpaceIsNull() {
            addCriterion("SPACE is null");
            return (Criteria) this;
        }

        public Criteria andSpaceIsNotNull() {
            addCriterion("SPACE is not null");
            return (Criteria) this;
        }

        public Criteria andSpaceEqualTo(Integer value) {
            addCriterion("SPACE =", value, "space");
            return (Criteria) this;
        }

        public Criteria andSpaceNotEqualTo(Integer value) {
            addCriterion("SPACE <>", value, "space");
            return (Criteria) this;
        }

        public Criteria andSpaceGreaterThan(Integer value) {
            addCriterion("SPACE >", value, "space");
            return (Criteria) this;
        }

        public Criteria andSpaceGreaterThanOrEqualTo(Integer value) {
            addCriterion("SPACE >=", value, "space");
            return (Criteria) this;
        }

        public Criteria andSpaceLessThan(Integer value) {
            addCriterion("SPACE <", value, "space");
            return (Criteria) this;
        }

        public Criteria andSpaceLessThanOrEqualTo(Integer value) {
            addCriterion("SPACE <=", value, "space");
            return (Criteria) this;
        }

        public Criteria andSpaceIn(List<Integer> values) {
            addCriterion("SPACE in", values, "space");
            return (Criteria) this;
        }

        public Criteria andSpaceNotIn(List<Integer> values) {
            addCriterion("SPACE not in", values, "space");
            return (Criteria) this;
        }

        public Criteria andSpaceBetween(Integer value1, Integer value2) {
            addCriterion("SPACE between", value1, value2, "space");
            return (Criteria) this;
        }

        public Criteria andSpaceNotBetween(Integer value1, Integer value2) {
            addCriterion("SPACE not between", value1, value2, "space");
            return (Criteria) this;
        }

        public Criteria andPopulationIsNull() {
            addCriterion("POPULATION is null");
            return (Criteria) this;
        }

        public Criteria andPopulationIsNotNull() {
            addCriterion("POPULATION is not null");
            return (Criteria) this;
        }

        public Criteria andPopulationEqualTo(Integer value) {
            addCriterion("POPULATION =", value, "population");
            return (Criteria) this;
        }

        public Criteria andPopulationNotEqualTo(Integer value) {
            addCriterion("POPULATION <>", value, "population");
            return (Criteria) this;
        }

        public Criteria andPopulationGreaterThan(Integer value) {
            addCriterion("POPULATION >", value, "population");
            return (Criteria) this;
        }

        public Criteria andPopulationGreaterThanOrEqualTo(Integer value) {
            addCriterion("POPULATION >=", value, "population");
            return (Criteria) this;
        }

        public Criteria andPopulationLessThan(Integer value) {
            addCriterion("POPULATION <", value, "population");
            return (Criteria) this;
        }

        public Criteria andPopulationLessThanOrEqualTo(Integer value) {
            addCriterion("POPULATION <=", value, "population");
            return (Criteria) this;
        }

        public Criteria andPopulationIn(List<Integer> values) {
            addCriterion("POPULATION in", values, "population");
            return (Criteria) this;
        }

        public Criteria andPopulationNotIn(List<Integer> values) {
            addCriterion("POPULATION not in", values, "population");
            return (Criteria) this;
        }

        public Criteria andPopulationBetween(Integer value1, Integer value2) {
            addCriterion("POPULATION between", value1, value2, "population");
            return (Criteria) this;
        }

        public Criteria andPopulationNotBetween(Integer value1, Integer value2) {
            addCriterion("POPULATION not between", value1, value2, "population");
            return (Criteria) this;
        }

        public Criteria andPostcodeIsNull() {
            addCriterion("POSTCODE is null");
            return (Criteria) this;
        }

        public Criteria andPostcodeIsNotNull() {
            addCriterion("POSTCODE is not null");
            return (Criteria) this;
        }

        public Criteria andPostcodeEqualTo(String value) {
            addCriterion("POSTCODE =", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeNotEqualTo(String value) {
            addCriterion("POSTCODE <>", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeGreaterThan(String value) {
            addCriterion("POSTCODE >", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeGreaterThanOrEqualTo(String value) {
            addCriterion("POSTCODE >=", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeLessThan(String value) {
            addCriterion("POSTCODE <", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeLessThanOrEqualTo(String value) {
            addCriterion("POSTCODE <=", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeLike(String value) {
            addCriterion("POSTCODE like", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeNotLike(String value) {
            addCriterion("POSTCODE not like", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeIn(List<String> values) {
            addCriterion("POSTCODE in", values, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeNotIn(List<String> values) {
            addCriterion("POSTCODE not in", values, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeBetween(String value1, String value2) {
            addCriterion("POSTCODE between", value1, value2, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeNotBetween(String value1, String value2) {
            addCriterion("POSTCODE not between", value1, value2, "postcode");
            return (Criteria) this;
        }

        public Criteria andAreacodeIsNull() {
            addCriterion("AREACODE is null");
            return (Criteria) this;
        }

        public Criteria andAreacodeIsNotNull() {
            addCriterion("AREACODE is not null");
            return (Criteria) this;
        }

        public Criteria andAreacodeEqualTo(String value) {
            addCriterion("AREACODE =", value, "areacode");
            return (Criteria) this;
        }

        public Criteria andAreacodeNotEqualTo(String value) {
            addCriterion("AREACODE <>", value, "areacode");
            return (Criteria) this;
        }

        public Criteria andAreacodeGreaterThan(String value) {
            addCriterion("AREACODE >", value, "areacode");
            return (Criteria) this;
        }

        public Criteria andAreacodeGreaterThanOrEqualTo(String value) {
            addCriterion("AREACODE >=", value, "areacode");
            return (Criteria) this;
        }

        public Criteria andAreacodeLessThan(String value) {
            addCriterion("AREACODE <", value, "areacode");
            return (Criteria) this;
        }

        public Criteria andAreacodeLessThanOrEqualTo(String value) {
            addCriterion("AREACODE <=", value, "areacode");
            return (Criteria) this;
        }

        public Criteria andAreacodeLike(String value) {
            addCriterion("AREACODE like", value, "areacode");
            return (Criteria) this;
        }

        public Criteria andAreacodeNotLike(String value) {
            addCriterion("AREACODE not like", value, "areacode");
            return (Criteria) this;
        }

        public Criteria andAreacodeIn(List<String> values) {
            addCriterion("AREACODE in", values, "areacode");
            return (Criteria) this;
        }

        public Criteria andAreacodeNotIn(List<String> values) {
            addCriterion("AREACODE not in", values, "areacode");
            return (Criteria) this;
        }

        public Criteria andAreacodeBetween(String value1, String value2) {
            addCriterion("AREACODE between", value1, value2, "areacode");
            return (Criteria) this;
        }

        public Criteria andAreacodeNotBetween(String value1, String value2) {
            addCriterion("AREACODE not between", value1, value2, "areacode");
            return (Criteria) this;
        }

        public Criteria andIsopenIsNull() {
            addCriterion("ISOPEN is null");
            return (Criteria) this;
        }

        public Criteria andIsopenIsNotNull() {
            addCriterion("ISOPEN is not null");
            return (Criteria) this;
        }

        public Criteria andIsopenEqualTo(Integer value) {
            addCriterion("ISOPEN =", value, "isopen");
            return (Criteria) this;
        }

        public Criteria andIsopenNotEqualTo(Integer value) {
            addCriterion("ISOPEN <>", value, "isopen");
            return (Criteria) this;
        }

        public Criteria andIsopenGreaterThan(Integer value) {
            addCriterion("ISOPEN >", value, "isopen");
            return (Criteria) this;
        }

        public Criteria andIsopenGreaterThanOrEqualTo(Integer value) {
            addCriterion("ISOPEN >=", value, "isopen");
            return (Criteria) this;
        }

        public Criteria andIsopenLessThan(Integer value) {
            addCriterion("ISOPEN <", value, "isopen");
            return (Criteria) this;
        }

        public Criteria andIsopenLessThanOrEqualTo(Integer value) {
            addCriterion("ISOPEN <=", value, "isopen");
            return (Criteria) this;
        }

        public Criteria andIsopenIn(List<Integer> values) {
            addCriterion("ISOPEN in", values, "isopen");
            return (Criteria) this;
        }

        public Criteria andIsopenNotIn(List<Integer> values) {
            addCriterion("ISOPEN not in", values, "isopen");
            return (Criteria) this;
        }

        public Criteria andIsopenBetween(Integer value1, Integer value2) {
            addCriterion("ISOPEN between", value1, value2, "isopen");
            return (Criteria) this;
        }

        public Criteria andIsopenNotBetween(Integer value1, Integer value2) {
            addCriterion("ISOPEN not between", value1, value2, "isopen");
            return (Criteria) this;
        }

        public Criteria andAreaNameIsNull() {
            addCriterion("AREA_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAreaNameIsNotNull() {
            addCriterion("AREA_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAreaNameEqualTo(String value) {
            addCriterion("AREA_NAME =", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameNotEqualTo(String value) {
            addCriterion("AREA_NAME <>", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameGreaterThan(String value) {
            addCriterion("AREA_NAME >", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameGreaterThanOrEqualTo(String value) {
            addCriterion("AREA_NAME >=", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameLessThan(String value) {
            addCriterion("AREA_NAME <", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameLessThanOrEqualTo(String value) {
            addCriterion("AREA_NAME <=", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameLike(String value) {
            addCriterion("AREA_NAME like", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameNotLike(String value) {
            addCriterion("AREA_NAME not like", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameIn(List<String> values) {
            addCriterion("AREA_NAME in", values, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameNotIn(List<String> values) {
            addCriterion("AREA_NAME not in", values, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameBetween(String value1, String value2) {
            addCriterion("AREA_NAME between", value1, value2, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameNotBetween(String value1, String value2) {
            addCriterion("AREA_NAME not between", value1, value2, "areaName");
            return (Criteria) this;
        }

        public Criteria andMapPointIsNull() {
            addCriterion("MAP_POINT is null");
            return (Criteria) this;
        }

        public Criteria andMapPointIsNotNull() {
            addCriterion("MAP_POINT is not null");
            return (Criteria) this;
        }

        public Criteria andMapPointEqualTo(String value) {
            addCriterion("MAP_POINT =", value, "mapPoint");
            return (Criteria) this;
        }

        public Criteria andMapPointNotEqualTo(String value) {
            addCriterion("MAP_POINT <>", value, "mapPoint");
            return (Criteria) this;
        }

        public Criteria andMapPointGreaterThan(String value) {
            addCriterion("MAP_POINT >", value, "mapPoint");
            return (Criteria) this;
        }

        public Criteria andMapPointGreaterThanOrEqualTo(String value) {
            addCriterion("MAP_POINT >=", value, "mapPoint");
            return (Criteria) this;
        }

        public Criteria andMapPointLessThan(String value) {
            addCriterion("MAP_POINT <", value, "mapPoint");
            return (Criteria) this;
        }

        public Criteria andMapPointLessThanOrEqualTo(String value) {
            addCriterion("MAP_POINT <=", value, "mapPoint");
            return (Criteria) this;
        }

        public Criteria andMapPointLike(String value) {
            addCriterion("MAP_POINT like", value, "mapPoint");
            return (Criteria) this;
        }

        public Criteria andMapPointNotLike(String value) {
            addCriterion("MAP_POINT not like", value, "mapPoint");
            return (Criteria) this;
        }

        public Criteria andMapPointIn(List<String> values) {
            addCriterion("MAP_POINT in", values, "mapPoint");
            return (Criteria) this;
        }

        public Criteria andMapPointNotIn(List<String> values) {
            addCriterion("MAP_POINT not in", values, "mapPoint");
            return (Criteria) this;
        }

        public Criteria andMapPointBetween(String value1, String value2) {
            addCriterion("MAP_POINT between", value1, value2, "mapPoint");
            return (Criteria) this;
        }

        public Criteria andMapPointNotBetween(String value1, String value2) {
            addCriterion("MAP_POINT not between", value1, value2, "mapPoint");
            return (Criteria) this;
        }

        public Criteria andAirportIsNull() {
            addCriterion("AIRPORT is null");
            return (Criteria) this;
        }

        public Criteria andAirportIsNotNull() {
            addCriterion("AIRPORT is not null");
            return (Criteria) this;
        }

        public Criteria andAirportEqualTo(String value) {
            addCriterion("AIRPORT =", value, "airport");
            return (Criteria) this;
        }

        public Criteria andAirportNotEqualTo(String value) {
            addCriterion("AIRPORT <>", value, "airport");
            return (Criteria) this;
        }

        public Criteria andAirportGreaterThan(String value) {
            addCriterion("AIRPORT >", value, "airport");
            return (Criteria) this;
        }

        public Criteria andAirportGreaterThanOrEqualTo(String value) {
            addCriterion("AIRPORT >=", value, "airport");
            return (Criteria) this;
        }

        public Criteria andAirportLessThan(String value) {
            addCriterion("AIRPORT <", value, "airport");
            return (Criteria) this;
        }

        public Criteria andAirportLessThanOrEqualTo(String value) {
            addCriterion("AIRPORT <=", value, "airport");
            return (Criteria) this;
        }

        public Criteria andAirportLike(String value) {
            addCriterion("AIRPORT like", value, "airport");
            return (Criteria) this;
        }

        public Criteria andAirportNotLike(String value) {
            addCriterion("AIRPORT not like", value, "airport");
            return (Criteria) this;
        }

        public Criteria andAirportIn(List<String> values) {
            addCriterion("AIRPORT in", values, "airport");
            return (Criteria) this;
        }

        public Criteria andAirportNotIn(List<String> values) {
            addCriterion("AIRPORT not in", values, "airport");
            return (Criteria) this;
        }

        public Criteria andAirportBetween(String value1, String value2) {
            addCriterion("AIRPORT between", value1, value2, "airport");
            return (Criteria) this;
        }

        public Criteria andAirportNotBetween(String value1, String value2) {
            addCriterion("AIRPORT not between", value1, value2, "airport");
            return (Criteria) this;
        }

        public Criteria andPIdIsNull() {
            addCriterion("P_ID is null");
            return (Criteria) this;
        }

        public Criteria andPIdIsNotNull() {
            addCriterion("P_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPIdEqualTo(Integer value) {
            addCriterion("P_ID =", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdNotEqualTo(Integer value) {
            addCriterion("P_ID <>", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdGreaterThan(Integer value) {
            addCriterion("P_ID >", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("P_ID >=", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdLessThan(Integer value) {
            addCriterion("P_ID <", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdLessThanOrEqualTo(Integer value) {
            addCriterion("P_ID <=", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdIn(List<Integer> values) {
            addCriterion("P_ID in", values, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdNotIn(List<Integer> values) {
            addCriterion("P_ID not in", values, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdBetween(Integer value1, Integer value2) {
            addCriterion("P_ID between", value1, value2, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdNotBetween(Integer value1, Integer value2) {
            addCriterion("P_ID not between", value1, value2, "pId");
            return (Criteria) this;
        }

        public Criteria andSpellIsNull() {
            addCriterion("SPELL is null");
            return (Criteria) this;
        }

        public Criteria andSpellIsNotNull() {
            addCriterion("SPELL is not null");
            return (Criteria) this;
        }

        public Criteria andSpellEqualTo(String value) {
            addCriterion("SPELL =", value, "spell");
            return (Criteria) this;
        }

        public Criteria andSpellNotEqualTo(String value) {
            addCriterion("SPELL <>", value, "spell");
            return (Criteria) this;
        }

        public Criteria andSpellGreaterThan(String value) {
            addCriterion("SPELL >", value, "spell");
            return (Criteria) this;
        }

        public Criteria andSpellGreaterThanOrEqualTo(String value) {
            addCriterion("SPELL >=", value, "spell");
            return (Criteria) this;
        }

        public Criteria andSpellLessThan(String value) {
            addCriterion("SPELL <", value, "spell");
            return (Criteria) this;
        }

        public Criteria andSpellLessThanOrEqualTo(String value) {
            addCriterion("SPELL <=", value, "spell");
            return (Criteria) this;
        }

        public Criteria andSpellLike(String value) {
            addCriterion("SPELL like", value, "spell");
            return (Criteria) this;
        }

        public Criteria andSpellNotLike(String value) {
            addCriterion("SPELL not like", value, "spell");
            return (Criteria) this;
        }

        public Criteria andSpellIn(List<String> values) {
            addCriterion("SPELL in", values, "spell");
            return (Criteria) this;
        }

        public Criteria andSpellNotIn(List<String> values) {
            addCriterion("SPELL not in", values, "spell");
            return (Criteria) this;
        }

        public Criteria andSpellBetween(String value1, String value2) {
            addCriterion("SPELL between", value1, value2, "spell");
            return (Criteria) this;
        }

        public Criteria andSpellNotBetween(String value1, String value2) {
            addCriterion("SPELL not between", value1, value2, "spell");
            return (Criteria) this;
        }

        public Criteria andShortSpellIsNull() {
            addCriterion("SHORT_SPELL is null");
            return (Criteria) this;
        }

        public Criteria andShortSpellIsNotNull() {
            addCriterion("SHORT_SPELL is not null");
            return (Criteria) this;
        }

        public Criteria andShortSpellEqualTo(String value) {
            addCriterion("SHORT_SPELL =", value, "shortSpell");
            return (Criteria) this;
        }

        public Criteria andShortSpellNotEqualTo(String value) {
            addCriterion("SHORT_SPELL <>", value, "shortSpell");
            return (Criteria) this;
        }

        public Criteria andShortSpellGreaterThan(String value) {
            addCriterion("SHORT_SPELL >", value, "shortSpell");
            return (Criteria) this;
        }

        public Criteria andShortSpellGreaterThanOrEqualTo(String value) {
            addCriterion("SHORT_SPELL >=", value, "shortSpell");
            return (Criteria) this;
        }

        public Criteria andShortSpellLessThan(String value) {
            addCriterion("SHORT_SPELL <", value, "shortSpell");
            return (Criteria) this;
        }

        public Criteria andShortSpellLessThanOrEqualTo(String value) {
            addCriterion("SHORT_SPELL <=", value, "shortSpell");
            return (Criteria) this;
        }

        public Criteria andShortSpellLike(String value) {
            addCriterion("SHORT_SPELL like", value, "shortSpell");
            return (Criteria) this;
        }

        public Criteria andShortSpellNotLike(String value) {
            addCriterion("SHORT_SPELL not like", value, "shortSpell");
            return (Criteria) this;
        }

        public Criteria andShortSpellIn(List<String> values) {
            addCriterion("SHORT_SPELL in", values, "shortSpell");
            return (Criteria) this;
        }

        public Criteria andShortSpellNotIn(List<String> values) {
            addCriterion("SHORT_SPELL not in", values, "shortSpell");
            return (Criteria) this;
        }

        public Criteria andShortSpellBetween(String value1, String value2) {
            addCriterion("SHORT_SPELL between", value1, value2, "shortSpell");
            return (Criteria) this;
        }

        public Criteria andShortSpellNotBetween(String value1, String value2) {
            addCriterion("SHORT_SPELL not between", value1, value2, "shortSpell");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNull() {
            addCriterion("LONGITUDE is null");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNotNull() {
            addCriterion("LONGITUDE is not null");
            return (Criteria) this;
        }

        public Criteria andLongitudeEqualTo(Double value) {
            addCriterion("LONGITUDE =", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotEqualTo(Double value) {
            addCriterion("LONGITUDE <>", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThan(Double value) {
            addCriterion("LONGITUDE >", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThanOrEqualTo(Double value) {
            addCriterion("LONGITUDE >=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThan(Double value) {
            addCriterion("LONGITUDE <", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThanOrEqualTo(Double value) {
            addCriterion("LONGITUDE <=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeIn(List<Double> values) {
            addCriterion("LONGITUDE in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotIn(List<Double> values) {
            addCriterion("LONGITUDE not in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeBetween(Double value1, Double value2) {
            addCriterion("LONGITUDE between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotBetween(Double value1, Double value2) {
            addCriterion("LONGITUDE not between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNull() {
            addCriterion("LATITUDE is null");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNotNull() {
            addCriterion("LATITUDE is not null");
            return (Criteria) this;
        }

        public Criteria andLatitudeEqualTo(Double value) {
            addCriterion("LATITUDE =", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotEqualTo(Double value) {
            addCriterion("LATITUDE <>", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThan(Double value) {
            addCriterion("LATITUDE >", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThanOrEqualTo(Double value) {
            addCriterion("LATITUDE >=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThan(Double value) {
            addCriterion("LATITUDE <", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThanOrEqualTo(Double value) {
            addCriterion("LATITUDE <=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIn(List<Double> values) {
            addCriterion("LATITUDE in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotIn(List<Double> values) {
            addCriterion("LATITUDE not in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeBetween(Double value1, Double value2) {
            addCriterion("LATITUDE between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotBetween(Double value1, Double value2) {
            addCriterion("LATITUDE not between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andViewCountIsNull() {
            addCriterion("VIEW_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andViewCountIsNotNull() {
            addCriterion("VIEW_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andViewCountEqualTo(Integer value) {
            addCriterion("VIEW_COUNT =", value, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountNotEqualTo(Integer value) {
            addCriterion("VIEW_COUNT <>", value, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountGreaterThan(Integer value) {
            addCriterion("VIEW_COUNT >", value, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("VIEW_COUNT >=", value, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountLessThan(Integer value) {
            addCriterion("VIEW_COUNT <", value, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountLessThanOrEqualTo(Integer value) {
            addCriterion("VIEW_COUNT <=", value, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountIn(List<Integer> values) {
            addCriterion("VIEW_COUNT in", values, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountNotIn(List<Integer> values) {
            addCriterion("VIEW_COUNT not in", values, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountBetween(Integer value1, Integer value2) {
            addCriterion("VIEW_COUNT between", value1, value2, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountNotBetween(Integer value1, Integer value2) {
            addCriterion("VIEW_COUNT not between", value1, value2, "viewCount");
            return (Criteria) this;
        }

        public Criteria andTicketSaleNumIsNull() {
            addCriterion("TICKET_SALE_NUM is null");
            return (Criteria) this;
        }

        public Criteria andTicketSaleNumIsNotNull() {
            addCriterion("TICKET_SALE_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andTicketSaleNumEqualTo(String value) {
            addCriterion("TICKET_SALE_NUM =", value, "ticketSaleNum");
            return (Criteria) this;
        }

        public Criteria andTicketSaleNumNotEqualTo(String value) {
            addCriterion("TICKET_SALE_NUM <>", value, "ticketSaleNum");
            return (Criteria) this;
        }

        public Criteria andTicketSaleNumGreaterThan(String value) {
            addCriterion("TICKET_SALE_NUM >", value, "ticketSaleNum");
            return (Criteria) this;
        }

        public Criteria andTicketSaleNumGreaterThanOrEqualTo(String value) {
            addCriterion("TICKET_SALE_NUM >=", value, "ticketSaleNum");
            return (Criteria) this;
        }

        public Criteria andTicketSaleNumLessThan(String value) {
            addCriterion("TICKET_SALE_NUM <", value, "ticketSaleNum");
            return (Criteria) this;
        }

        public Criteria andTicketSaleNumLessThanOrEqualTo(String value) {
            addCriterion("TICKET_SALE_NUM <=", value, "ticketSaleNum");
            return (Criteria) this;
        }

        public Criteria andTicketSaleNumLike(String value) {
            addCriterion("TICKET_SALE_NUM like", value, "ticketSaleNum");
            return (Criteria) this;
        }

        public Criteria andTicketSaleNumNotLike(String value) {
            addCriterion("TICKET_SALE_NUM not like", value, "ticketSaleNum");
            return (Criteria) this;
        }

        public Criteria andTicketSaleNumIn(List<String> values) {
            addCriterion("TICKET_SALE_NUM in", values, "ticketSaleNum");
            return (Criteria) this;
        }

        public Criteria andTicketSaleNumNotIn(List<String> values) {
            addCriterion("TICKET_SALE_NUM not in", values, "ticketSaleNum");
            return (Criteria) this;
        }

        public Criteria andTicketSaleNumBetween(String value1, String value2) {
            addCriterion("TICKET_SALE_NUM between", value1, value2, "ticketSaleNum");
            return (Criteria) this;
        }

        public Criteria andTicketSaleNumNotBetween(String value1, String value2) {
            addCriterion("TICKET_SALE_NUM not between", value1, value2, "ticketSaleNum");
            return (Criteria) this;
        }

        public Criteria andTicketViewNumIsNull() {
            addCriterion("TICKET_VIEW_NUM is null");
            return (Criteria) this;
        }

        public Criteria andTicketViewNumIsNotNull() {
            addCriterion("TICKET_VIEW_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andTicketViewNumEqualTo(Integer value) {
            addCriterion("TICKET_VIEW_NUM =", value, "ticketViewNum");
            return (Criteria) this;
        }

        public Criteria andTicketViewNumNotEqualTo(Integer value) {
            addCriterion("TICKET_VIEW_NUM <>", value, "ticketViewNum");
            return (Criteria) this;
        }

        public Criteria andTicketViewNumGreaterThan(Integer value) {
            addCriterion("TICKET_VIEW_NUM >", value, "ticketViewNum");
            return (Criteria) this;
        }

        public Criteria andTicketViewNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("TICKET_VIEW_NUM >=", value, "ticketViewNum");
            return (Criteria) this;
        }

        public Criteria andTicketViewNumLessThan(Integer value) {
            addCriterion("TICKET_VIEW_NUM <", value, "ticketViewNum");
            return (Criteria) this;
        }

        public Criteria andTicketViewNumLessThanOrEqualTo(Integer value) {
            addCriterion("TICKET_VIEW_NUM <=", value, "ticketViewNum");
            return (Criteria) this;
        }

        public Criteria andTicketViewNumIn(List<Integer> values) {
            addCriterion("TICKET_VIEW_NUM in", values, "ticketViewNum");
            return (Criteria) this;
        }

        public Criteria andTicketViewNumNotIn(List<Integer> values) {
            addCriterion("TICKET_VIEW_NUM not in", values, "ticketViewNum");
            return (Criteria) this;
        }

        public Criteria andTicketViewNumBetween(Integer value1, Integer value2) {
            addCriterion("TICKET_VIEW_NUM between", value1, value2, "ticketViewNum");
            return (Criteria) this;
        }

        public Criteria andTicketViewNumNotBetween(Integer value1, Integer value2) {
            addCriterion("TICKET_VIEW_NUM not between", value1, value2, "ticketViewNum");
            return (Criteria) this;
        }

        public Criteria andAbbEngIsNull() {
            addCriterion("ABB_ENG is null");
            return (Criteria) this;
        }

        public Criteria andAbbEngIsNotNull() {
            addCriterion("ABB_ENG is not null");
            return (Criteria) this;
        }

        public Criteria andAbbEngEqualTo(String value) {
            addCriterion("ABB_ENG =", value, "abbEng");
            return (Criteria) this;
        }

        public Criteria andAbbEngNotEqualTo(String value) {
            addCriterion("ABB_ENG <>", value, "abbEng");
            return (Criteria) this;
        }

        public Criteria andAbbEngGreaterThan(String value) {
            addCriterion("ABB_ENG >", value, "abbEng");
            return (Criteria) this;
        }

        public Criteria andAbbEngGreaterThanOrEqualTo(String value) {
            addCriterion("ABB_ENG >=", value, "abbEng");
            return (Criteria) this;
        }

        public Criteria andAbbEngLessThan(String value) {
            addCriterion("ABB_ENG <", value, "abbEng");
            return (Criteria) this;
        }

        public Criteria andAbbEngLessThanOrEqualTo(String value) {
            addCriterion("ABB_ENG <=", value, "abbEng");
            return (Criteria) this;
        }

        public Criteria andAbbEngLike(String value) {
            addCriterion("ABB_ENG like", value, "abbEng");
            return (Criteria) this;
        }

        public Criteria andAbbEngNotLike(String value) {
            addCriterion("ABB_ENG not like", value, "abbEng");
            return (Criteria) this;
        }

        public Criteria andAbbEngIn(List<String> values) {
            addCriterion("ABB_ENG in", values, "abbEng");
            return (Criteria) this;
        }

        public Criteria andAbbEngNotIn(List<String> values) {
            addCriterion("ABB_ENG not in", values, "abbEng");
            return (Criteria) this;
        }

        public Criteria andAbbEngBetween(String value1, String value2) {
            addCriterion("ABB_ENG between", value1, value2, "abbEng");
            return (Criteria) this;
        }

        public Criteria andAbbEngNotBetween(String value1, String value2) {
            addCriterion("ABB_ENG not between", value1, value2, "abbEng");
            return (Criteria) this;
        }

        public Criteria andSignImgIsNull() {
            addCriterion("SIGN_IMG is null");
            return (Criteria) this;
        }

        public Criteria andSignImgIsNotNull() {
            addCriterion("SIGN_IMG is not null");
            return (Criteria) this;
        }

        public Criteria andSignImgEqualTo(String value) {
            addCriterion("SIGN_IMG =", value, "signImg");
            return (Criteria) this;
        }

        public Criteria andSignImgNotEqualTo(String value) {
            addCriterion("SIGN_IMG <>", value, "signImg");
            return (Criteria) this;
        }

        public Criteria andSignImgGreaterThan(String value) {
            addCriterion("SIGN_IMG >", value, "signImg");
            return (Criteria) this;
        }

        public Criteria andSignImgGreaterThanOrEqualTo(String value) {
            addCriterion("SIGN_IMG >=", value, "signImg");
            return (Criteria) this;
        }

        public Criteria andSignImgLessThan(String value) {
            addCriterion("SIGN_IMG <", value, "signImg");
            return (Criteria) this;
        }

        public Criteria andSignImgLessThanOrEqualTo(String value) {
            addCriterion("SIGN_IMG <=", value, "signImg");
            return (Criteria) this;
        }

        public Criteria andSignImgLike(String value) {
            addCriterion("SIGN_IMG like", value, "signImg");
            return (Criteria) this;
        }

        public Criteria andSignImgNotLike(String value) {
            addCriterion("SIGN_IMG not like", value, "signImg");
            return (Criteria) this;
        }

        public Criteria andSignImgIn(List<String> values) {
            addCriterion("SIGN_IMG in", values, "signImg");
            return (Criteria) this;
        }

        public Criteria andSignImgNotIn(List<String> values) {
            addCriterion("SIGN_IMG not in", values, "signImg");
            return (Criteria) this;
        }

        public Criteria andSignImgBetween(String value1, String value2) {
            addCriterion("SIGN_IMG between", value1, value2, "signImg");
            return (Criteria) this;
        }

        public Criteria andSignImgNotBetween(String value1, String value2) {
            addCriterion("SIGN_IMG not between", value1, value2, "signImg");
            return (Criteria) this;
        }

        public Criteria andImgIsNull() {
            addCriterion("IMG is null");
            return (Criteria) this;
        }

        public Criteria andImgIsNotNull() {
            addCriterion("IMG is not null");
            return (Criteria) this;
        }

        public Criteria andImgEqualTo(String value) {
            addCriterion("IMG =", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotEqualTo(String value) {
            addCriterion("IMG <>", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgGreaterThan(String value) {
            addCriterion("IMG >", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgGreaterThanOrEqualTo(String value) {
            addCriterion("IMG >=", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLessThan(String value) {
            addCriterion("IMG <", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLessThanOrEqualTo(String value) {
            addCriterion("IMG <=", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLike(String value) {
            addCriterion("IMG like", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotLike(String value) {
            addCriterion("IMG not like", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgIn(List<String> values) {
            addCriterion("IMG in", values, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotIn(List<String> values) {
            addCriterion("IMG not in", values, "img");
            return (Criteria) this;
        }

        public Criteria andImgBetween(String value1, String value2) {
            addCriterion("IMG between", value1, value2, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotBetween(String value1, String value2) {
            addCriterion("IMG not between", value1, value2, "img");
            return (Criteria) this;
        }

        public Criteria andAreapathIsNull() {
            addCriterion("AREAPATH is null");
            return (Criteria) this;
        }

        public Criteria andAreapathIsNotNull() {
            addCriterion("AREAPATH is not null");
            return (Criteria) this;
        }

        public Criteria andAreapathEqualTo(String value) {
            addCriterion("AREAPATH =", value, "areapath");
            return (Criteria) this;
        }

        public Criteria andAreapathNotEqualTo(String value) {
            addCriterion("AREAPATH <>", value, "areapath");
            return (Criteria) this;
        }

        public Criteria andAreapathGreaterThan(String value) {
            addCriterion("AREAPATH >", value, "areapath");
            return (Criteria) this;
        }

        public Criteria andAreapathGreaterThanOrEqualTo(String value) {
            addCriterion("AREAPATH >=", value, "areapath");
            return (Criteria) this;
        }

        public Criteria andAreapathLessThan(String value) {
            addCriterion("AREAPATH <", value, "areapath");
            return (Criteria) this;
        }

        public Criteria andAreapathLessThanOrEqualTo(String value) {
            addCriterion("AREAPATH <=", value, "areapath");
            return (Criteria) this;
        }

        public Criteria andAreapathLike(String value) {
            addCriterion("AREAPATH like", value, "areapath");
            return (Criteria) this;
        }

        public Criteria andAreapathNotLike(String value) {
            addCriterion("AREAPATH not like", value, "areapath");
            return (Criteria) this;
        }

        public Criteria andAreapathIn(List<String> values) {
            addCriterion("AREAPATH in", values, "areapath");
            return (Criteria) this;
        }

        public Criteria andAreapathNotIn(List<String> values) {
            addCriterion("AREAPATH not in", values, "areapath");
            return (Criteria) this;
        }

        public Criteria andAreapathBetween(String value1, String value2) {
            addCriterion("AREAPATH between", value1, value2, "areapath");
            return (Criteria) this;
        }

        public Criteria andAreapathNotBetween(String value1, String value2) {
            addCriterion("AREAPATH not between", value1, value2, "areapath");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }

        public Criteria andTreeNameLikeInsensitive(String value) {
            addCriterion("upper(TREE_NAME) like", value.toUpperCase(), "treeName");
            return this;
        }

        public Criteria andMapimgLikeInsensitive(String value) {
            addCriterion("upper(MAPIMG) like", value.toUpperCase(), "mapimg");
            return this;
        }

        public Criteria andGuideLikeInsensitive(String value) {
            addCriterion("upper(GUIDE) like", value.toUpperCase(), "guide");
            return this;
        }

        public Criteria andEngnameLikeInsensitive(String value) {
            addCriterion("upper(ENGNAME) like", value.toUpperCase(), "engname");
            return this;
        }

        public Criteria andHrefLikeInsensitive(String value) {
            addCriterion("upper(HREF) like", value.toUpperCase(), "href");
            return this;
        }

        public Criteria andPostcodeLikeInsensitive(String value) {
            addCriterion("upper(POSTCODE) like", value.toUpperCase(), "postcode");
            return this;
        }

        public Criteria andAreacodeLikeInsensitive(String value) {
            addCriterion("upper(AREACODE) like", value.toUpperCase(), "areacode");
            return this;
        }

        public Criteria andAreaNameLikeInsensitive(String value) {
            addCriterion("upper(AREA_NAME) like", value.toUpperCase(), "areaName");
            return this;
        }

        public Criteria andMapPointLikeInsensitive(String value) {
            addCriterion("upper(MAP_POINT) like", value.toUpperCase(), "mapPoint");
            return this;
        }

        public Criteria andAirportLikeInsensitive(String value) {
            addCriterion("upper(AIRPORT) like", value.toUpperCase(), "airport");
            return this;
        }

        public Criteria andSpellLikeInsensitive(String value) {
            addCriterion("upper(SPELL) like", value.toUpperCase(), "spell");
            return this;
        }

        public Criteria andShortSpellLikeInsensitive(String value) {
            addCriterion("upper(SHORT_SPELL) like", value.toUpperCase(), "shortSpell");
            return this;
        }

        public Criteria andTicketSaleNumLikeInsensitive(String value) {
            addCriterion("upper(TICKET_SALE_NUM) like", value.toUpperCase(), "ticketSaleNum");
            return this;
        }

        public Criteria andAbbEngLikeInsensitive(String value) {
            addCriterion("upper(ABB_ENG) like", value.toUpperCase(), "abbEng");
            return this;
        }

        public Criteria andSignImgLikeInsensitive(String value) {
            addCriterion("upper(SIGN_IMG) like", value.toUpperCase(), "signImg");
            return this;
        }

        public Criteria andImgLikeInsensitive(String value) {
            addCriterion("upper(IMG) like", value.toUpperCase(), "img");
            return this;
        }

        public Criteria andAreapathLikeInsensitive(String value) {
            addCriterion("upper(AREAPATH) like", value.toUpperCase(), "areapath");
            return this;
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
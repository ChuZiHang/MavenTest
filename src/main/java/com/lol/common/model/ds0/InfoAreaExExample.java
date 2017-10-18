package com.lol.common.model.ds0;

import com.lol.common.plugin.Page;
import java.util.ArrayList;
import java.util.List;

public class InfoAreaExExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Page page;

    private String dialect = "mysql";

    public InfoAreaExExample() {
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

        public Criteria andAreaIdIsNull() {
            addCriterion("AREA_ID is null");
            return (Criteria) this;
        }

        public Criteria andAreaIdIsNotNull() {
            addCriterion("AREA_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAreaIdEqualTo(Double value) {
            addCriterion("AREA_ID =", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotEqualTo(Double value) {
            addCriterion("AREA_ID <>", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdGreaterThan(Double value) {
            addCriterion("AREA_ID >", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdGreaterThanOrEqualTo(Double value) {
            addCriterion("AREA_ID >=", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdLessThan(Double value) {
            addCriterion("AREA_ID <", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdLessThanOrEqualTo(Double value) {
            addCriterion("AREA_ID <=", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdIn(List<Double> values) {
            addCriterion("AREA_ID in", values, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotIn(List<Double> values) {
            addCriterion("AREA_ID not in", values, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdBetween(Double value1, Double value2) {
            addCriterion("AREA_ID between", value1, value2, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotBetween(Double value1, Double value2) {
            addCriterion("AREA_ID not between", value1, value2, "areaId");
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

        public Criteria andPyIsNull() {
            addCriterion("PY is null");
            return (Criteria) this;
        }

        public Criteria andPyIsNotNull() {
            addCriterion("PY is not null");
            return (Criteria) this;
        }

        public Criteria andPyEqualTo(String value) {
            addCriterion("PY =", value, "py");
            return (Criteria) this;
        }

        public Criteria andPyNotEqualTo(String value) {
            addCriterion("PY <>", value, "py");
            return (Criteria) this;
        }

        public Criteria andPyGreaterThan(String value) {
            addCriterion("PY >", value, "py");
            return (Criteria) this;
        }

        public Criteria andPyGreaterThanOrEqualTo(String value) {
            addCriterion("PY >=", value, "py");
            return (Criteria) this;
        }

        public Criteria andPyLessThan(String value) {
            addCriterion("PY <", value, "py");
            return (Criteria) this;
        }

        public Criteria andPyLessThanOrEqualTo(String value) {
            addCriterion("PY <=", value, "py");
            return (Criteria) this;
        }

        public Criteria andPyLike(String value) {
            addCriterion("PY like", value, "py");
            return (Criteria) this;
        }

        public Criteria andPyNotLike(String value) {
            addCriterion("PY not like", value, "py");
            return (Criteria) this;
        }

        public Criteria andPyIn(List<String> values) {
            addCriterion("PY in", values, "py");
            return (Criteria) this;
        }

        public Criteria andPyNotIn(List<String> values) {
            addCriterion("PY not in", values, "py");
            return (Criteria) this;
        }

        public Criteria andPyBetween(String value1, String value2) {
            addCriterion("PY between", value1, value2, "py");
            return (Criteria) this;
        }

        public Criteria andPyNotBetween(String value1, String value2) {
            addCriterion("PY not between", value1, value2, "py");
            return (Criteria) this;
        }

        public Criteria andPysxIsNull() {
            addCriterion("PYSX is null");
            return (Criteria) this;
        }

        public Criteria andPysxIsNotNull() {
            addCriterion("PYSX is not null");
            return (Criteria) this;
        }

        public Criteria andPysxEqualTo(String value) {
            addCriterion("PYSX =", value, "pysx");
            return (Criteria) this;
        }

        public Criteria andPysxNotEqualTo(String value) {
            addCriterion("PYSX <>", value, "pysx");
            return (Criteria) this;
        }

        public Criteria andPysxGreaterThan(String value) {
            addCriterion("PYSX >", value, "pysx");
            return (Criteria) this;
        }

        public Criteria andPysxGreaterThanOrEqualTo(String value) {
            addCriterion("PYSX >=", value, "pysx");
            return (Criteria) this;
        }

        public Criteria andPysxLessThan(String value) {
            addCriterion("PYSX <", value, "pysx");
            return (Criteria) this;
        }

        public Criteria andPysxLessThanOrEqualTo(String value) {
            addCriterion("PYSX <=", value, "pysx");
            return (Criteria) this;
        }

        public Criteria andPysxLike(String value) {
            addCriterion("PYSX like", value, "pysx");
            return (Criteria) this;
        }

        public Criteria andPysxNotLike(String value) {
            addCriterion("PYSX not like", value, "pysx");
            return (Criteria) this;
        }

        public Criteria andPysxIn(List<String> values) {
            addCriterion("PYSX in", values, "pysx");
            return (Criteria) this;
        }

        public Criteria andPysxNotIn(List<String> values) {
            addCriterion("PYSX not in", values, "pysx");
            return (Criteria) this;
        }

        public Criteria andPysxBetween(String value1, String value2) {
            addCriterion("PYSX between", value1, value2, "pysx");
            return (Criteria) this;
        }

        public Criteria andPysxNotBetween(String value1, String value2) {
            addCriterion("PYSX not between", value1, value2, "pysx");
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

        public Criteria andAirportMIsNull() {
            addCriterion("AIRPORT_M is null");
            return (Criteria) this;
        }

        public Criteria andAirportMIsNotNull() {
            addCriterion("AIRPORT_M is not null");
            return (Criteria) this;
        }

        public Criteria andAirportMEqualTo(String value) {
            addCriterion("AIRPORT_M =", value, "airportM");
            return (Criteria) this;
        }

        public Criteria andAirportMNotEqualTo(String value) {
            addCriterion("AIRPORT_M <>", value, "airportM");
            return (Criteria) this;
        }

        public Criteria andAirportMGreaterThan(String value) {
            addCriterion("AIRPORT_M >", value, "airportM");
            return (Criteria) this;
        }

        public Criteria andAirportMGreaterThanOrEqualTo(String value) {
            addCriterion("AIRPORT_M >=", value, "airportM");
            return (Criteria) this;
        }

        public Criteria andAirportMLessThan(String value) {
            addCriterion("AIRPORT_M <", value, "airportM");
            return (Criteria) this;
        }

        public Criteria andAirportMLessThanOrEqualTo(String value) {
            addCriterion("AIRPORT_M <=", value, "airportM");
            return (Criteria) this;
        }

        public Criteria andAirportMLike(String value) {
            addCriterion("AIRPORT_M like", value, "airportM");
            return (Criteria) this;
        }

        public Criteria andAirportMNotLike(String value) {
            addCriterion("AIRPORT_M not like", value, "airportM");
            return (Criteria) this;
        }

        public Criteria andAirportMIn(List<String> values) {
            addCriterion("AIRPORT_M in", values, "airportM");
            return (Criteria) this;
        }

        public Criteria andAirportMNotIn(List<String> values) {
            addCriterion("AIRPORT_M not in", values, "airportM");
            return (Criteria) this;
        }

        public Criteria andAirportMBetween(String value1, String value2) {
            addCriterion("AIRPORT_M between", value1, value2, "airportM");
            return (Criteria) this;
        }

        public Criteria andAirportMNotBetween(String value1, String value2) {
            addCriterion("AIRPORT_M not between", value1, value2, "airportM");
            return (Criteria) this;
        }

        public Criteria andStationIsNull() {
            addCriterion("STATION is null");
            return (Criteria) this;
        }

        public Criteria andStationIsNotNull() {
            addCriterion("STATION is not null");
            return (Criteria) this;
        }

        public Criteria andStationEqualTo(String value) {
            addCriterion("STATION =", value, "station");
            return (Criteria) this;
        }

        public Criteria andStationNotEqualTo(String value) {
            addCriterion("STATION <>", value, "station");
            return (Criteria) this;
        }

        public Criteria andStationGreaterThan(String value) {
            addCriterion("STATION >", value, "station");
            return (Criteria) this;
        }

        public Criteria andStationGreaterThanOrEqualTo(String value) {
            addCriterion("STATION >=", value, "station");
            return (Criteria) this;
        }

        public Criteria andStationLessThan(String value) {
            addCriterion("STATION <", value, "station");
            return (Criteria) this;
        }

        public Criteria andStationLessThanOrEqualTo(String value) {
            addCriterion("STATION <=", value, "station");
            return (Criteria) this;
        }

        public Criteria andStationLike(String value) {
            addCriterion("STATION like", value, "station");
            return (Criteria) this;
        }

        public Criteria andStationNotLike(String value) {
            addCriterion("STATION not like", value, "station");
            return (Criteria) this;
        }

        public Criteria andStationIn(List<String> values) {
            addCriterion("STATION in", values, "station");
            return (Criteria) this;
        }

        public Criteria andStationNotIn(List<String> values) {
            addCriterion("STATION not in", values, "station");
            return (Criteria) this;
        }

        public Criteria andStationBetween(String value1, String value2) {
            addCriterion("STATION between", value1, value2, "station");
            return (Criteria) this;
        }

        public Criteria andStationNotBetween(String value1, String value2) {
            addCriterion("STATION not between", value1, value2, "station");
            return (Criteria) this;
        }

        public Criteria andStationMIsNull() {
            addCriterion("STATION_M is null");
            return (Criteria) this;
        }

        public Criteria andStationMIsNotNull() {
            addCriterion("STATION_M is not null");
            return (Criteria) this;
        }

        public Criteria andStationMEqualTo(String value) {
            addCriterion("STATION_M =", value, "stationM");
            return (Criteria) this;
        }

        public Criteria andStationMNotEqualTo(String value) {
            addCriterion("STATION_M <>", value, "stationM");
            return (Criteria) this;
        }

        public Criteria andStationMGreaterThan(String value) {
            addCriterion("STATION_M >", value, "stationM");
            return (Criteria) this;
        }

        public Criteria andStationMGreaterThanOrEqualTo(String value) {
            addCriterion("STATION_M >=", value, "stationM");
            return (Criteria) this;
        }

        public Criteria andStationMLessThan(String value) {
            addCriterion("STATION_M <", value, "stationM");
            return (Criteria) this;
        }

        public Criteria andStationMLessThanOrEqualTo(String value) {
            addCriterion("STATION_M <=", value, "stationM");
            return (Criteria) this;
        }

        public Criteria andStationMLike(String value) {
            addCriterion("STATION_M like", value, "stationM");
            return (Criteria) this;
        }

        public Criteria andStationMNotLike(String value) {
            addCriterion("STATION_M not like", value, "stationM");
            return (Criteria) this;
        }

        public Criteria andStationMIn(List<String> values) {
            addCriterion("STATION_M in", values, "stationM");
            return (Criteria) this;
        }

        public Criteria andStationMNotIn(List<String> values) {
            addCriterion("STATION_M not in", values, "stationM");
            return (Criteria) this;
        }

        public Criteria andStationMBetween(String value1, String value2) {
            addCriterion("STATION_M between", value1, value2, "stationM");
            return (Criteria) this;
        }

        public Criteria andStationMNotBetween(String value1, String value2) {
            addCriterion("STATION_M not between", value1, value2, "stationM");
            return (Criteria) this;
        }

        public Criteria andMapCodeIsNull() {
            addCriterion("MAP_CODE is null");
            return (Criteria) this;
        }

        public Criteria andMapCodeIsNotNull() {
            addCriterion("MAP_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andMapCodeEqualTo(String value) {
            addCriterion("MAP_CODE =", value, "mapCode");
            return (Criteria) this;
        }

        public Criteria andMapCodeNotEqualTo(String value) {
            addCriterion("MAP_CODE <>", value, "mapCode");
            return (Criteria) this;
        }

        public Criteria andMapCodeGreaterThan(String value) {
            addCriterion("MAP_CODE >", value, "mapCode");
            return (Criteria) this;
        }

        public Criteria andMapCodeGreaterThanOrEqualTo(String value) {
            addCriterion("MAP_CODE >=", value, "mapCode");
            return (Criteria) this;
        }

        public Criteria andMapCodeLessThan(String value) {
            addCriterion("MAP_CODE <", value, "mapCode");
            return (Criteria) this;
        }

        public Criteria andMapCodeLessThanOrEqualTo(String value) {
            addCriterion("MAP_CODE <=", value, "mapCode");
            return (Criteria) this;
        }

        public Criteria andMapCodeLike(String value) {
            addCriterion("MAP_CODE like", value, "mapCode");
            return (Criteria) this;
        }

        public Criteria andMapCodeNotLike(String value) {
            addCriterion("MAP_CODE not like", value, "mapCode");
            return (Criteria) this;
        }

        public Criteria andMapCodeIn(List<String> values) {
            addCriterion("MAP_CODE in", values, "mapCode");
            return (Criteria) this;
        }

        public Criteria andMapCodeNotIn(List<String> values) {
            addCriterion("MAP_CODE not in", values, "mapCode");
            return (Criteria) this;
        }

        public Criteria andMapCodeBetween(String value1, String value2) {
            addCriterion("MAP_CODE between", value1, value2, "mapCode");
            return (Criteria) this;
        }

        public Criteria andMapCodeNotBetween(String value1, String value2) {
            addCriterion("MAP_CODE not between", value1, value2, "mapCode");
            return (Criteria) this;
        }

        public Criteria andWatherCodeIsNull() {
            addCriterion("WATHER_CODE is null");
            return (Criteria) this;
        }

        public Criteria andWatherCodeIsNotNull() {
            addCriterion("WATHER_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andWatherCodeEqualTo(String value) {
            addCriterion("WATHER_CODE =", value, "watherCode");
            return (Criteria) this;
        }

        public Criteria andWatherCodeNotEqualTo(String value) {
            addCriterion("WATHER_CODE <>", value, "watherCode");
            return (Criteria) this;
        }

        public Criteria andWatherCodeGreaterThan(String value) {
            addCriterion("WATHER_CODE >", value, "watherCode");
            return (Criteria) this;
        }

        public Criteria andWatherCodeGreaterThanOrEqualTo(String value) {
            addCriterion("WATHER_CODE >=", value, "watherCode");
            return (Criteria) this;
        }

        public Criteria andWatherCodeLessThan(String value) {
            addCriterion("WATHER_CODE <", value, "watherCode");
            return (Criteria) this;
        }

        public Criteria andWatherCodeLessThanOrEqualTo(String value) {
            addCriterion("WATHER_CODE <=", value, "watherCode");
            return (Criteria) this;
        }

        public Criteria andWatherCodeLike(String value) {
            addCriterion("WATHER_CODE like", value, "watherCode");
            return (Criteria) this;
        }

        public Criteria andWatherCodeNotLike(String value) {
            addCriterion("WATHER_CODE not like", value, "watherCode");
            return (Criteria) this;
        }

        public Criteria andWatherCodeIn(List<String> values) {
            addCriterion("WATHER_CODE in", values, "watherCode");
            return (Criteria) this;
        }

        public Criteria andWatherCodeNotIn(List<String> values) {
            addCriterion("WATHER_CODE not in", values, "watherCode");
            return (Criteria) this;
        }

        public Criteria andWatherCodeBetween(String value1, String value2) {
            addCriterion("WATHER_CODE between", value1, value2, "watherCode");
            return (Criteria) this;
        }

        public Criteria andWatherCodeNotBetween(String value1, String value2) {
            addCriterion("WATHER_CODE not between", value1, value2, "watherCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeIsNull() {
            addCriterion("POST_CODE is null");
            return (Criteria) this;
        }

        public Criteria andPostCodeIsNotNull() {
            addCriterion("POST_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andPostCodeEqualTo(String value) {
            addCriterion("POST_CODE =", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeNotEqualTo(String value) {
            addCriterion("POST_CODE <>", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeGreaterThan(String value) {
            addCriterion("POST_CODE >", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeGreaterThanOrEqualTo(String value) {
            addCriterion("POST_CODE >=", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeLessThan(String value) {
            addCriterion("POST_CODE <", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeLessThanOrEqualTo(String value) {
            addCriterion("POST_CODE <=", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeLike(String value) {
            addCriterion("POST_CODE like", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeNotLike(String value) {
            addCriterion("POST_CODE not like", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeIn(List<String> values) {
            addCriterion("POST_CODE in", values, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeNotIn(List<String> values) {
            addCriterion("POST_CODE not in", values, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeBetween(String value1, String value2) {
            addCriterion("POST_CODE between", value1, value2, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeNotBetween(String value1, String value2) {
            addCriterion("POST_CODE not between", value1, value2, "postCode");
            return (Criteria) this;
        }

        public Criteria andPhoneCodeIsNull() {
            addCriterion("PHONE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andPhoneCodeIsNotNull() {
            addCriterion("PHONE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneCodeEqualTo(String value) {
            addCriterion("PHONE_CODE =", value, "phoneCode");
            return (Criteria) this;
        }

        public Criteria andPhoneCodeNotEqualTo(String value) {
            addCriterion("PHONE_CODE <>", value, "phoneCode");
            return (Criteria) this;
        }

        public Criteria andPhoneCodeGreaterThan(String value) {
            addCriterion("PHONE_CODE >", value, "phoneCode");
            return (Criteria) this;
        }

        public Criteria andPhoneCodeGreaterThanOrEqualTo(String value) {
            addCriterion("PHONE_CODE >=", value, "phoneCode");
            return (Criteria) this;
        }

        public Criteria andPhoneCodeLessThan(String value) {
            addCriterion("PHONE_CODE <", value, "phoneCode");
            return (Criteria) this;
        }

        public Criteria andPhoneCodeLessThanOrEqualTo(String value) {
            addCriterion("PHONE_CODE <=", value, "phoneCode");
            return (Criteria) this;
        }

        public Criteria andPhoneCodeLike(String value) {
            addCriterion("PHONE_CODE like", value, "phoneCode");
            return (Criteria) this;
        }

        public Criteria andPhoneCodeNotLike(String value) {
            addCriterion("PHONE_CODE not like", value, "phoneCode");
            return (Criteria) this;
        }

        public Criteria andPhoneCodeIn(List<String> values) {
            addCriterion("PHONE_CODE in", values, "phoneCode");
            return (Criteria) this;
        }

        public Criteria andPhoneCodeNotIn(List<String> values) {
            addCriterion("PHONE_CODE not in", values, "phoneCode");
            return (Criteria) this;
        }

        public Criteria andPhoneCodeBetween(String value1, String value2) {
            addCriterion("PHONE_CODE between", value1, value2, "phoneCode");
            return (Criteria) this;
        }

        public Criteria andPhoneCodeNotBetween(String value1, String value2) {
            addCriterion("PHONE_CODE not between", value1, value2, "phoneCode");
            return (Criteria) this;
        }

        public Criteria andPathIsNull() {
            addCriterion("PATH is null");
            return (Criteria) this;
        }

        public Criteria andPathIsNotNull() {
            addCriterion("PATH is not null");
            return (Criteria) this;
        }

        public Criteria andPathEqualTo(String value) {
            addCriterion("PATH =", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotEqualTo(String value) {
            addCriterion("PATH <>", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathGreaterThan(String value) {
            addCriterion("PATH >", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathGreaterThanOrEqualTo(String value) {
            addCriterion("PATH >=", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathLessThan(String value) {
            addCriterion("PATH <", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathLessThanOrEqualTo(String value) {
            addCriterion("PATH <=", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathLike(String value) {
            addCriterion("PATH like", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotLike(String value) {
            addCriterion("PATH not like", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathIn(List<String> values) {
            addCriterion("PATH in", values, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotIn(List<String> values) {
            addCriterion("PATH not in", values, "path");
            return (Criteria) this;
        }

        public Criteria andPathBetween(String value1, String value2) {
            addCriterion("PATH between", value1, value2, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotBetween(String value1, String value2) {
            addCriterion("PATH not between", value1, value2, "path");
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
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }

        public Criteria andAreaNameLikeInsensitive(String value) {
            addCriterion("upper(AREA_NAME) like", value.toUpperCase(), "areaName");
            return this;
        }

        public Criteria andPyLikeInsensitive(String value) {
            addCriterion("upper(PY) like", value.toUpperCase(), "py");
            return this;
        }

        public Criteria andPysxLikeInsensitive(String value) {
            addCriterion("upper(PYSX) like", value.toUpperCase(), "pysx");
            return this;
        }

        public Criteria andAirportLikeInsensitive(String value) {
            addCriterion("upper(AIRPORT) like", value.toUpperCase(), "airport");
            return this;
        }

        public Criteria andAirportMLikeInsensitive(String value) {
            addCriterion("upper(AIRPORT_M) like", value.toUpperCase(), "airportM");
            return this;
        }

        public Criteria andStationLikeInsensitive(String value) {
            addCriterion("upper(STATION) like", value.toUpperCase(), "station");
            return this;
        }

        public Criteria andStationMLikeInsensitive(String value) {
            addCriterion("upper(STATION_M) like", value.toUpperCase(), "stationM");
            return this;
        }

        public Criteria andMapCodeLikeInsensitive(String value) {
            addCriterion("upper(MAP_CODE) like", value.toUpperCase(), "mapCode");
            return this;
        }

        public Criteria andWatherCodeLikeInsensitive(String value) {
            addCriterion("upper(WATHER_CODE) like", value.toUpperCase(), "watherCode");
            return this;
        }

        public Criteria andPostCodeLikeInsensitive(String value) {
            addCriterion("upper(POST_CODE) like", value.toUpperCase(), "postCode");
            return this;
        }

        public Criteria andPhoneCodeLikeInsensitive(String value) {
            addCriterion("upper(PHONE_CODE) like", value.toUpperCase(), "phoneCode");
            return this;
        }

        public Criteria andPathLikeInsensitive(String value) {
            addCriterion("upper(PATH) like", value.toUpperCase(), "path");
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
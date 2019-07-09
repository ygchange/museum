package com.museum.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SelectLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SelectLogExample() {
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

        public Criteria andExhibitsInfoIdIsNull() {
            addCriterion("exhibits_info_id is null");
            return (Criteria) this;
        }

        public Criteria andExhibitsInfoIdIsNotNull() {
            addCriterion("exhibits_info_id is not null");
            return (Criteria) this;
        }

        public Criteria andExhibitsInfoIdEqualTo(Integer value) {
            addCriterion("exhibits_info_id =", value, "exhibitsInfoId");
            return (Criteria) this;
        }

        public Criteria andExhibitsInfoIdNotEqualTo(Integer value) {
            addCriterion("exhibits_info_id <>", value, "exhibitsInfoId");
            return (Criteria) this;
        }

        public Criteria andExhibitsInfoIdGreaterThan(Integer value) {
            addCriterion("exhibits_info_id >", value, "exhibitsInfoId");
            return (Criteria) this;
        }

        public Criteria andExhibitsInfoIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("exhibits_info_id >=", value, "exhibitsInfoId");
            return (Criteria) this;
        }

        public Criteria andExhibitsInfoIdLessThan(Integer value) {
            addCriterion("exhibits_info_id <", value, "exhibitsInfoId");
            return (Criteria) this;
        }

        public Criteria andExhibitsInfoIdLessThanOrEqualTo(Integer value) {
            addCriterion("exhibits_info_id <=", value, "exhibitsInfoId");
            return (Criteria) this;
        }

        public Criteria andExhibitsInfoIdIn(List<Integer> values) {
            addCriterion("exhibits_info_id in", values, "exhibitsInfoId");
            return (Criteria) this;
        }

        public Criteria andExhibitsInfoIdNotIn(List<Integer> values) {
            addCriterion("exhibits_info_id not in", values, "exhibitsInfoId");
            return (Criteria) this;
        }

        public Criteria andExhibitsInfoIdBetween(Integer value1, Integer value2) {
            addCriterion("exhibits_info_id between", value1, value2, "exhibitsInfoId");
            return (Criteria) this;
        }

        public Criteria andExhibitsInfoIdNotBetween(Integer value1, Integer value2) {
            addCriterion("exhibits_info_id not between", value1, value2, "exhibitsInfoId");
            return (Criteria) this;
        }

        public Criteria andWechatUserIdIsNull() {
            addCriterion("wechat_user_id is null");
            return (Criteria) this;
        }

        public Criteria andWechatUserIdIsNotNull() {
            addCriterion("wechat_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andWechatUserIdEqualTo(Integer value) {
            addCriterion("wechat_user_id =", value, "wechatUserId");
            return (Criteria) this;
        }

        public Criteria andWechatUserIdNotEqualTo(Integer value) {
            addCriterion("wechat_user_id <>", value, "wechatUserId");
            return (Criteria) this;
        }

        public Criteria andWechatUserIdGreaterThan(Integer value) {
            addCriterion("wechat_user_id >", value, "wechatUserId");
            return (Criteria) this;
        }

        public Criteria andWechatUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("wechat_user_id >=", value, "wechatUserId");
            return (Criteria) this;
        }

        public Criteria andWechatUserIdLessThan(Integer value) {
            addCriterion("wechat_user_id <", value, "wechatUserId");
            return (Criteria) this;
        }

        public Criteria andWechatUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("wechat_user_id <=", value, "wechatUserId");
            return (Criteria) this;
        }

        public Criteria andWechatUserIdIn(List<Integer> values) {
            addCriterion("wechat_user_id in", values, "wechatUserId");
            return (Criteria) this;
        }

        public Criteria andWechatUserIdNotIn(List<Integer> values) {
            addCriterion("wechat_user_id not in", values, "wechatUserId");
            return (Criteria) this;
        }

        public Criteria andWechatUserIdBetween(Integer value1, Integer value2) {
            addCriterion("wechat_user_id between", value1, value2, "wechatUserId");
            return (Criteria) this;
        }

        public Criteria andWechatUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("wechat_user_id not between", value1, value2, "wechatUserId");
            return (Criteria) this;
        }

        public Criteria andSelectTimeIsNull() {
            addCriterion("select_time is null");
            return (Criteria) this;
        }

        public Criteria andSelectTimeIsNotNull() {
            addCriterion("select_time is not null");
            return (Criteria) this;
        }

        public Criteria andSelectTimeEqualTo(Date value) {
            addCriterion("select_time =", value, "selectTime");
            return (Criteria) this;
        }

        public Criteria andSelectTimeNotEqualTo(Date value) {
            addCriterion("select_time <>", value, "selectTime");
            return (Criteria) this;
        }

        public Criteria andSelectTimeGreaterThan(Date value) {
            addCriterion("select_time >", value, "selectTime");
            return (Criteria) this;
        }

        public Criteria andSelectTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("select_time >=", value, "selectTime");
            return (Criteria) this;
        }

        public Criteria andSelectTimeLessThan(Date value) {
            addCriterion("select_time <", value, "selectTime");
            return (Criteria) this;
        }

        public Criteria andSelectTimeLessThanOrEqualTo(Date value) {
            addCriterion("select_time <=", value, "selectTime");
            return (Criteria) this;
        }

        public Criteria andSelectTimeIn(List<Date> values) {
            addCriterion("select_time in", values, "selectTime");
            return (Criteria) this;
        }

        public Criteria andSelectTimeNotIn(List<Date> values) {
            addCriterion("select_time not in", values, "selectTime");
            return (Criteria) this;
        }

        public Criteria andSelectTimeBetween(Date value1, Date value2) {
            addCriterion("select_time between", value1, value2, "selectTime");
            return (Criteria) this;
        }

        public Criteria andSelectTimeNotBetween(Date value1, Date value2) {
            addCriterion("select_time not between", value1, value2, "selectTime");
            return (Criteria) this;
        }

        public Criteria andSelectUserIpIsNull() {
            addCriterion("select_user_ip is null");
            return (Criteria) this;
        }

        public Criteria andSelectUserIpIsNotNull() {
            addCriterion("select_user_ip is not null");
            return (Criteria) this;
        }

        public Criteria andSelectUserIpEqualTo(String value) {
            addCriterion("select_user_ip =", value, "selectUserIp");
            return (Criteria) this;
        }

        public Criteria andSelectUserIpNotEqualTo(String value) {
            addCriterion("select_user_ip <>", value, "selectUserIp");
            return (Criteria) this;
        }

        public Criteria andSelectUserIpGreaterThan(String value) {
            addCriterion("select_user_ip >", value, "selectUserIp");
            return (Criteria) this;
        }

        public Criteria andSelectUserIpGreaterThanOrEqualTo(String value) {
            addCriterion("select_user_ip >=", value, "selectUserIp");
            return (Criteria) this;
        }

        public Criteria andSelectUserIpLessThan(String value) {
            addCriterion("select_user_ip <", value, "selectUserIp");
            return (Criteria) this;
        }

        public Criteria andSelectUserIpLessThanOrEqualTo(String value) {
            addCriterion("select_user_ip <=", value, "selectUserIp");
            return (Criteria) this;
        }

        public Criteria andSelectUserIpLike(String value) {
            addCriterion("select_user_ip like", value, "selectUserIp");
            return (Criteria) this;
        }

        public Criteria andSelectUserIpNotLike(String value) {
            addCriterion("select_user_ip not like", value, "selectUserIp");
            return (Criteria) this;
        }

        public Criteria andSelectUserIpIn(List<String> values) {
            addCriterion("select_user_ip in", values, "selectUserIp");
            return (Criteria) this;
        }

        public Criteria andSelectUserIpNotIn(List<String> values) {
            addCriterion("select_user_ip not in", values, "selectUserIp");
            return (Criteria) this;
        }

        public Criteria andSelectUserIpBetween(String value1, String value2) {
            addCriterion("select_user_ip between", value1, value2, "selectUserIp");
            return (Criteria) this;
        }

        public Criteria andSelectUserIpNotBetween(String value1, String value2) {
            addCriterion("select_user_ip not between", value1, value2, "selectUserIp");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
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
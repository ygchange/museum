package com.museum.pojo;

import java.util.Date;

public class SelectLog {
    private Integer id;

    private Integer exhibitsInfoId;

    private Integer wechatUserId;

    private Date selectTime;

    private String selectUserIp;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExhibitsInfoId() {
        return exhibitsInfoId;
    }

    public void setExhibitsInfoId(Integer exhibitsInfoId) {
        this.exhibitsInfoId = exhibitsInfoId;
    }

    public Integer getWechatUserId() {
        return wechatUserId;
    }

    public void setWechatUserId(Integer wechatUserId) {
        this.wechatUserId = wechatUserId;
    }

    public Date getSelectTime() {
        return selectTime;
    }

    public void setSelectTime(Date selectTime) {
        this.selectTime = selectTime;
    }

    public String getSelectUserIp() {
        return selectUserIp;
    }

    public void setSelectUserIp(String selectUserIp) {
        this.selectUserIp = selectUserIp == null ? null : selectUserIp.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}
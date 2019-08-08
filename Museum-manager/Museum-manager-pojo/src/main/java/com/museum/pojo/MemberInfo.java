package com.museum.pojo;

import java.util.Date;

public class MemberInfo {
    private Integer id;

    private String userName;

    private String password;

    private String nickname;

    private String status;

    private Integer memberLevelTypeId;

    private Integer memberAccountTypeId;

    private Date lastLoginDate;

    private String lastIp;

    private Date openDate;

    private Integer landingStatus;

    private String allowIp;

    private String remark;

    private String telephone;

    private String token;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getMemberLevelTypeId() {
        return memberLevelTypeId;
    }

    public void setMemberLevelTypeId(Integer memberLevelTypeId) {
        this.memberLevelTypeId = memberLevelTypeId;
    }

    public Integer getMemberAccountTypeId() {
        return memberAccountTypeId;
    }

    public void setMemberAccountTypeId(Integer memberAccountTypeId) {
        this.memberAccountTypeId = memberAccountTypeId;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp == null ? null : lastIp.trim();
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public Integer getLandingStatus() {
        return landingStatus;
    }

    public void setLandingStatus(Integer landingStatus) {
        this.landingStatus = landingStatus;
    }

    public String getAllowIp() {
        return allowIp;
    }

    public void setAllowIp(String allowIp) {
        this.allowIp = allowIp == null ? null : allowIp.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }
}
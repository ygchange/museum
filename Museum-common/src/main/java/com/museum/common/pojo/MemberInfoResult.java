package com.museum.common.pojo;

import java.io.Serializable;

public class MemberInfoResult implements Serializable {
    private Integer id;
    private String nickname;
    private String remark;
    private Integer memberAccountTypeId;
    private String Token;

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getMemberAccountTypeId() {
        return memberAccountTypeId;
    }

    public void setMemberAccountTypeId(Integer memberAccountTypeId) {
        this.memberAccountTypeId = memberAccountTypeId;
    }
}

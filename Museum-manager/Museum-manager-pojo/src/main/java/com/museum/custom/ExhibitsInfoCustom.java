package com.museum.custom;

import com.museum.pojo.ExhibitsInfo;
import com.museum.pojo.MemberInfo;

public class ExhibitsInfoCustom extends ExhibitsInfo {
    private String typeName;
    private MemberInfo memberInfo;
    private  Integer number;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public MemberInfo getMemberInfo() {
        return memberInfo;
    }

    public void setMemberInfo(MemberInfo memberInfo) {
        this.memberInfo = memberInfo;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}

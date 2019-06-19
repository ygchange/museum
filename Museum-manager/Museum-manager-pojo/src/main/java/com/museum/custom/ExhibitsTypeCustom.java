package com.museum.custom;

import com.museum.pojo.ExhibitsType;
import com.museum.pojo.MemberInfo;

public class ExhibitsTypeCustom  extends ExhibitsType {
     private MemberInfo memberInfo;

    public MemberInfo getMemberInfo() {
        return memberInfo;
    }

    public void setMemberInfo(MemberInfo memberInfo) {
        this.memberInfo = memberInfo;
    }
}

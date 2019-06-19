package com.museum.custom;

import com.museum.pojo.LostInfo;
import com.museum.pojo.MemberInfo;

public class LostInfoCustom  extends LostInfo {
    private MemberInfo memberInfo;

    public MemberInfo getMemberInfo() {
        return memberInfo;
    }

    public void setMemberInfo(MemberInfo memberInfo) {
        this.memberInfo = memberInfo;
    }
}

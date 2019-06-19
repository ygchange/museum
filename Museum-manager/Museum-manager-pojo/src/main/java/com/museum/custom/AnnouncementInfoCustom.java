package com.museum.custom;

import com.museum.pojo.AnnouncementInfo;
import com.museum.pojo.MemberInfo;

public class AnnouncementInfoCustom extends AnnouncementInfo {
    private MemberInfo memberInfo;

    public MemberInfo getMemberInfo() {
        return memberInfo;
    }

    public void setMemberInfo(MemberInfo memberInfo) {
        this.memberInfo = memberInfo;
    }
}

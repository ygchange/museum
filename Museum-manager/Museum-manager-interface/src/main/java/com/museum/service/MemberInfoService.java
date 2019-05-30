package com.museum.service;

import com.museum.common.pojo.MemberInfoResult;
import com.museum.common.pojo.PageHelperResult;
import com.museum.pojo.MemberInfo;
import com.museum.pojo.MemberInfoCustom;

public interface MemberInfoService {
    //查询管理员信息
    PageHelperResult getMemberInfoList(Integer page, Integer rows);
    MemberInfoResult resultCustomUser(MemberInfoCustom custom);
    //根据username查找用户信息
    MemberInfo selectMemberInfoByUserName(String userName);
    //修改用户信息
    Integer updateMemberInfoById(MemberInfo memberInfo);
    Integer deleteMemberInfoById(Integer id);
}

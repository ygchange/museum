package com.museum.service;

import com.museum.common.pojo.MemberInfoResult;
import com.museum.common.pojo.PageHelperResult;
import com.museum.pojo.MemberInfo;
import com.museum.custom.MemberInfoCustom;

public interface MemberInfoService {
    //查询管理员信息
    PageHelperResult getMemberInfoList(Integer page, Integer rows);
    MemberInfoResult resultCustomUser(MemberInfoCustom custom);
    //根据username查找用户信息
    MemberInfo selectMemberInfoByUserName(String userName);
    //修改用户信息
    Integer updateMemberInfoById(MemberInfo memberInfo);
    //删除管理员用户
    Integer deleteMemberInfoById(Integer id);
    //添加管理员
    void insertMember(MemberInfo memberInfo) throws Exception;
    MemberInfo getPasswordById(MemberInfo memberInfo);

    Integer updateStatusById(MemberInfo memberInfo);
}

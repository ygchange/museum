package com.museum.controller;


import com.museum.common.pojo.AjaxResponseBody;
import com.museum.common.pojo.PageHelperResult;
import com.museum.pojo.MemberInfo;
import com.museum.service.MemberInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/memberInfo")
public class MemberInfoController {
    @Autowired
    private MemberInfoService memberInfoService;
    //管理员信息查询
    @RequestMapping(value = "/list",method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResponseBody getMemberInfoList(@RequestBody HashMap<String,String> map){
        Integer page=Integer.valueOf(map.get("page"));
        Integer rows=Integer.valueOf(map.get("rows"));
        PageHelperResult result = memberInfoService.getMemberInfoList(page, rows);
        return AjaxResponseBody.ok(result);
    }
    //修改信息
    @RequestMapping(value = "/update",method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResponseBody updateMemberInfoById(@RequestBody MemberInfo memberInfo){
        Integer integer = memberInfoService.updateMemberInfoById(memberInfo);
        if(integer>=1){
            return AjaxResponseBody.build(200,"修改成功");
        }

        return AjaxResponseBody.build(400,"修改失败,用户已被删除");
    }
    //删除信息
    @RequestMapping(value = "/delete",method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResponseBody deleteMemberInfoById(@RequestBody Map<String,String> map){
        Integer id=Integer.valueOf(map.get("id"));
        Integer integer = memberInfoService.deleteMemberInfoById(id);
        if(integer>=1){
            return AjaxResponseBody.ok();
        }
        return AjaxResponseBody.build(400,"删除失败,用户已被删除");
    }
    //添加信息
    @RequestMapping(value = "/insert",method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResponseBody insertMember(@RequestBody MemberInfo memberInfo){

        try {
             memberInfoService.insertMember(memberInfo);
            return  AjaxResponseBody.ok();
        } catch (Exception e) {
            return AjaxResponseBody.build(400,"用户名已存在");
        }

    }
    //验证用户名是否存在
    @RequestMapping(value = "/verification",method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResponseBody verificationMemberInfo(@RequestBody Map<String,String> map){
        String username=map.get("userName");
        MemberInfo memberInfo = memberInfoService.selectMemberInfoByUserName(username);
        if(memberInfo==null){
            return AjaxResponseBody.ok();
        }else{
            return AjaxResponseBody.build(412,"用户名已存在");
        }
    }

}

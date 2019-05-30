package com.museum.controller;


import com.museum.common.pojo.AjaxResponseBody;
import com.museum.common.pojo.MemberInfoResult;
import com.museum.common.pojo.PageHelperResult;
import com.museum.pojo.MemberInfo;
import com.museum.service.MemberInfoService;
import org.omg.CORBA.PUBLIC_MEMBER;
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
    public AjaxResponseBody getMemberInfoList(@RequestBody HashMap<String,Integer> hashMap){
         Integer page=hashMap.get("page");
         Integer rows=hashMap.get("rows");
        PageHelperResult result = memberInfoService.getMemberInfoList(page, rows);
        return AjaxResponseBody.ok(result);
    }
    //修改信息
    @RequestMapping(value = "/update",method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResponseBody updateMemberInfoById(@RequestBody MemberInfo memberInfo){
        Integer integer = memberInfoService.updateMemberInfoById(memberInfo);
        if(integer>=1){
            return AjaxResponseBody.ok();
        }
        return AjaxResponseBody.build(400,"修改失败");
    }
    //删除信息
    @RequestMapping(value = "/delete",method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResponseBody deleteMemberInfoById(@RequestBody Map<String,Integer> map){
            Integer id= map.get("id");
        Integer integer = memberInfoService.deleteMemberInfoById(id);
        if(integer>=1){
            return AjaxResponseBody.ok();
        }
        return AjaxResponseBody.build(400,"删除失败");
    }

}

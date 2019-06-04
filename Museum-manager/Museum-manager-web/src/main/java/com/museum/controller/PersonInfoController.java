package com.museum.controller;

import com.museum.common.pojo.AjaxResponseBody;
import com.museum.pojo.MemberInfo;
import com.museum.service.MemberInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/personInfo")
public class PersonInfoController {
    @Autowired
    private MemberInfoService memberInfoService;
    //修改密码
    @RequestMapping("/updatePassword")
    @ResponseBody
    public AjaxResponseBody updatePassword(@RequestBody Map<String,String> map){
        MemberInfo memberInfo=new MemberInfo();
        memberInfo.setId(Integer.valueOf(map.get("id")));
        memberInfo.setPassword(map.get("password"));
        MemberInfo password = memberInfoService.getPasswordById(memberInfo);
        if(new BCryptPasswordEncoder().matches(map.get("oldPass"),password.getPassword())){
            memberInfoService.updateMemberInfoById(memberInfo);
            return AjaxResponseBody.ok();
        }else {
           return AjaxResponseBody.build(400,"旧密码不正确");
        }
    }

    @RequestMapping("/updatePersonInfo")
    @ResponseBody
    public  AjaxResponseBody updatePersonInfo(@RequestBody MemberInfo memberInfo){
        Integer integer = memberInfoService.updateMemberInfoById(memberInfo);
        return AjaxResponseBody.ok();
    }
}

package com.museum.controller;

import com.museum.common.pojo.AjaxResponseBody;
import com.museum.common.pojo.PageHelperResult;
import com.museum.service.WeChatUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 操作微信用户
 */
@Controller
@RequestMapping("/weChat")
public class WeChatUserController {
    @Autowired
    private WeChatUserService weChatUserService;
    //分页查新查询微信用户
    @RequestMapping("/list")
    @ResponseBody
    public AjaxResponseBody getWeChatList(@RequestBody Map<String,String> map){
        Integer page=Integer.valueOf(map.get("page"));
        Integer rows=Integer.valueOf(map.get("rows"));
        PageHelperResult result = weChatUserService.getWeChatList(page, rows);
        return AjaxResponseBody.ok(result);
    }

}

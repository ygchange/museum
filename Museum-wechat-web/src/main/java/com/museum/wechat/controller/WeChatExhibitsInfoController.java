package com.museum.wechat.controller;

import com.museum.common.pojo.AjaxResponseBody;
import com.museum.pojo.ExhibitsInfo;
import com.museum.service.ItemInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/weChatExhibits")
public class WeChatExhibitsInfoController {
    @Autowired
    private ItemInfoService itemInfoService;
    @RequestMapping("/select")
    @ResponseBody
    public AjaxResponseBody selectExhibitsInfoById(@RequestBody Map<String,String> map){
        Integer id=Integer.valueOf(map.get("id"));
        ExhibitsInfo exhibitsInfo = itemInfoService.selectExhibitsInfoById(id);
        if(exhibitsInfo!=null) {
            return AjaxResponseBody.ok(exhibitsInfo);
        }else {
            return AjaxResponseBody.build(400,"该展品已经被删除,请查看其他展品");
        }
    }
}

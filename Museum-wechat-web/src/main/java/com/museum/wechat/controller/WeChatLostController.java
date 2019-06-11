package com.museum.wechat.controller;

import com.museum.common.pojo.AjaxResponseBody;
import com.museum.pojo.LostInfo;
import com.museum.service.LostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/weChatLost")
public class WeChatLostController {
    @Autowired
    private LostService lostService;
    @RequestMapping("/list")
    @ResponseBody
    private AjaxResponseBody getweChatLostList(){
        List<LostInfo> lostInfos= lostService.getWeChatLostList();
        if(lostInfos.size()==0){
            return AjaxResponseBody.build(0,"无信息");
        }else {
            return AjaxResponseBody.ok(lostInfos);
        }
    }
}

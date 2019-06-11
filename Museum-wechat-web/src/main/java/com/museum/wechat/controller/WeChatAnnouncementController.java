package com.museum.wechat.controller;

import com.museum.common.pojo.AjaxResponseBody;
import com.museum.pojo.AnnouncementInfo;
import com.museum.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/weChatAnnouncement")
public class WeChatAnnouncementController {
    @Autowired
    private AnnouncementService announcementService;
    @RequestMapping("/list")
    @ResponseBody
    public AjaxResponseBody getWeChatAnnouncementList(){
        List<AnnouncementInfo> list = announcementService.getWeChatAnnouncementList();
        if(list.size()==0){
           return AjaxResponseBody.build(0,"暂无公告");
        }else {
            return AjaxResponseBody.ok(list);
        }
    }
}

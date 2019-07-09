package com.museum.wechat.controller;

import com.museum.common.pojo.AjaxResponseBody;
import com.museum.pojo.AnnouncementInfo;
import com.museum.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/weChatAnnouncement")
public class WeChatAnnouncementController {
    @Autowired
    private AnnouncementService announcementService;
    @RequestMapping("/list")
    @ResponseBody
    //查询所有公告
    public AjaxResponseBody getWeChatAnnouncementList(){
        List<AnnouncementInfo> list = announcementService.getWeChatAnnouncementList();
        if(list.size()==0){
           return AjaxResponseBody.build(0,"暂无公告");
        }else {
            return AjaxResponseBody.ok(list);
        }
    }
    //根据id查询公告
    @RequestMapping("/select")
    @ResponseBody
    public AjaxResponseBody getWeChatAnnouncementById(@RequestBody Map<String,String> map){
        Integer id=Integer.valueOf(map.get("id"));
        AnnouncementInfo announcementInfo=announcementService.getWeChatAnnouncementById(id);
        if(announcementInfo==null){
            return AjaxResponseBody.build(400,"该公告已被删除");
        }else{
            return AjaxResponseBody.ok(announcementInfo);
        }
    }
}

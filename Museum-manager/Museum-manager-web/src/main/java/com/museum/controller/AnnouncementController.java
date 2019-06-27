package com.museum.controller;

import com.museum.common.pojo.AjaxResponseBody;
import com.museum.common.pojo.PageHelperResult;
import com.museum.pojo.AnnouncementInfo;
import com.museum.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/Announcement")
public class AnnouncementController {
    @Autowired
    private AnnouncementService announcementService;
    @RequestMapping("/list")
    @ResponseBody
    //分页查询公告信息
    public AjaxResponseBody getAnnouncementList(@RequestBody Map<String,String> map){
        Integer page = Integer.valueOf(map.get("page"));
        Integer rows = Integer.valueOf(map.get("rows"));
        PageHelperResult result =announcementService.getAnnouncementList(page,rows);
        return AjaxResponseBody.ok(result);
    }
    @RequestMapping("/insert")
    @ResponseBody
    //添加公告信息
    public  AjaxResponseBody insertAnnouncement(@RequestBody AnnouncementInfo announcementInfo){
        announcementService.insertAnnouncement(announcementInfo);
        return AjaxResponseBody.ok();
    }
    @RequestMapping("/delete")
    @ResponseBody
    //删除公告信息
    public  AjaxResponseBody deleteAnnouncementById(@RequestBody Map<String,String> map){
        Integer id=Integer.valueOf(map.get("id"));
       Integer i =announcementService.deleteAnnouncementById(id);
        if(i>=1){
            return AjaxResponseBody.ok();
        }
        return AjaxResponseBody.build(400,"该通告已被删除");
    }
    @RequestMapping("/update")
    @ResponseBody
    //修改公告信息
    public  AjaxResponseBody updateAnnouncementById(@RequestBody AnnouncementInfo announcementInfo){
        Integer i=announcementService.updateAnnouncementById(announcementInfo);
        if(i>=1){
            return AjaxResponseBody.build(200,"修改成功");
        }
        return AjaxResponseBody.build(400,"该通告已被删除");
    }

}

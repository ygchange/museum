package com.museum.controller;

import com.museum.common.pojo.AjaxResponseBody;
import com.museum.common.pojo.PageHelperResult;
import com.museum.pojo.LostInfo;
import com.museum.service.LostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/lost")
public class LostController {
    @Autowired
    private LostService lostService;
    //查询失物招领
    @RequestMapping("/list")
    @ResponseBody
    public AjaxResponseBody getLostList(@RequestBody Map<String,String> map){
        Integer page=Integer.valueOf(map.get("page"));
        Integer rows=Integer.valueOf(map.get("rows"));
        PageHelperResult lostList = lostService.getLostList(page, rows);
        return AjaxResponseBody.ok(lostList);
    }
    //删除失物招领
    @RequestMapping("/delete")
    @ResponseBody
    public AjaxResponseBody  deleteLostById(@RequestBody Map<String,String> map){
        Integer id=Integer.valueOf(map.get("id"));
        Integer integer =lostService.deleteLostById(id);
        if(integer>=1){
            return AjaxResponseBody.ok();
        }
        /*
         */
        return AjaxResponseBody.build(400,"该信息已被删除");
    }
    //添加失物招领
    @RequestMapping("/insert")
    @ResponseBody
    public  AjaxResponseBody insertLost(@RequestBody LostInfo lostInfo){
            lostService.insertLost(lostInfo);
            return AjaxResponseBody.ok();
    }
    //更新状态
    @RequestMapping("/updateStatus")
    @ResponseBody
    public  AjaxResponseBody updateLostStatus(@RequestBody LostInfo lostInfo){
        Integer i=lostService.updateLostStatus(lostInfo);
        if(i>=1){
            return AjaxResponseBody.build(200,"修改成功");
        }
        return AjaxResponseBody.build(400,"该信息已被删除");
    }
}

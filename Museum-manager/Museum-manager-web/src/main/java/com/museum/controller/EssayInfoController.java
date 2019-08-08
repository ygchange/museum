package com.museum.controller;

import com.museum.common.pojo.AjaxResponseBody;
import com.museum.common.pojo.PageHelperResult;
import com.museum.pojo.EssayInfo;
import com.museum.service.EssayInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/essay")
public class EssayInfoController {
    @Value("${qiniu.bucket.host.name}")
    private String bucketHostName;
    @Autowired
    private EssayInfoService essayInfoService;

    //插入新的文章
    @RequestMapping("/insert")
    @ResponseBody
    public AjaxResponseBody insertEssayInfo(@RequestBody EssayInfo essayInfo){
        essayInfo.setAddTime(new Date());
        String a=essayInfo.getContent();
        essayInfo.setContent(a.replaceAll(bucketHostName,""));
        if(a.indexOf(bucketHostName)!=-1) {
            essayInfo.setImg(a.substring(a.indexOf(bucketHostName) + bucketHostName.length(), a.indexOf(".jpg") + 4));
        }
        essayInfoService.insertEssayInfo(essayInfo);
        return AjaxResponseBody.ok();
    }

    //分页查询文章列表
    @RequestMapping("/list")
    @ResponseBody
    public AjaxResponseBody getEssayInfo(@RequestBody Map<String,Object> map){
        Integer page = (Integer) map.get("page");
        Integer rows = (Integer) map.get("rows");
        PageHelperResult essayInfo = essayInfoService.getEssayInfo(page, rows,bucketHostName);

        return AjaxResponseBody.ok(essayInfo);
    }

    //修改文章
    @RequestMapping("/update")
    @ResponseBody
    public AjaxResponseBody updateEssayInfoById(@RequestBody EssayInfo essayInfo){
        String a=essayInfo.getContent();
        essayInfo.setContent(a.replaceAll(bucketHostName,""));
        if(a.indexOf(bucketHostName)!=-1) {
            essayInfo.setImg(a.substring(a.indexOf(bucketHostName) + bucketHostName.length(), a.indexOf(".jpg") + 4));
        }
        Integer i=essayInfoService.updateEssayInfoById(essayInfo);
        if(i>=1){
            return AjaxResponseBody.build(200,"修改成功");
        }
        return AjaxResponseBody.build(400,"该文章已被删除");
    }
    //删除文章
    @RequestMapping("/delete")
    @ResponseBody
    public AjaxResponseBody deleteEssayInfoById(@RequestBody Map<String,String> map){
        Integer id=Integer.valueOf(map.get("id"));
        Integer i=essayInfoService.deleteEssayInfoById(id);
        if(i>=1){
            return AjaxResponseBody.ok();
        }
        return AjaxResponseBody.build(400,"该文章已被删除");
    }

}

package com.museum.official.controller;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/essay")
public class EssayInfoController {
    @Autowired
    private EssayInfoService essayInfoService;
    @Value("${qiniu.bucket.host.name}")
    private String bucketHostName;
    //获取最新文章
    @RequestMapping("/latest")
    @ResponseBody
    public AjaxResponseBody getLatestEssayInfo(@RequestBody Map<String,Object> map){
        Integer essayType = (Integer) map.get("essayType");
        List<EssayInfo> official = essayInfoService.getLatestEssayInfo(essayType);
        List<Map<String,Object>> newList=null;

        if(official.size()!=0){
            newList=new ArrayList<>();

        }
        for (int i = 0; i <official.size() ; i++) {
            Map<String,Object> resultMap=new HashMap<>();
            if(i>=5){
                break;
            }
            resultMap.put("title",official.get(i).getTitle());
            resultMap.put("addTime",official.get(i).getAddTime());
            resultMap.put("id",official.get(i).getId());
            newList.add(resultMap);
        }
        return AjaxResponseBody.ok(newList);
    }
    //获取热门文章
    @RequestMapping("/hot")
    @ResponseBody
    public  AjaxResponseBody getHotEssayInfo(@RequestBody Map<String,Object> map){
        Integer essayType = (Integer) map.get("essayType");
        List<EssayInfo> official= essayInfoService.getHotEssayInfo(essayType);
        List<Map<String,Object>> newList=null;

        if(official.size()!=0){
            newList=new ArrayList<>();

        }
        for (int i = 0; i <official.size() ; i++) {
            Map<String,Object> resultMap=new HashMap<>();
            if(i>=5){
                break;
            }
            resultMap.put("title",official.get(i).getTitle());
            resultMap.put("addTime",official.get(i).getAddTime());
            resultMap.put("id",official.get(i).getId());
            newList.add(resultMap);
        }
        return AjaxResponseBody.ok(newList);
    }
    //获取动态或者文化
    @RequestMapping("/getEssay")
    @ResponseBody
    public AjaxResponseBody getDynamicOrCulture(@RequestBody Map<String,Object> map){
        Integer essayType = (Integer) map.get("essayType");
        List<EssayInfo> list=essayInfoService.getDynamicOrCulture(essayType);
        List<Map<String,Object>> newList=null;

        if(list.size()!=0){
            newList=new ArrayList<>();

        }
        for (int i = 0; i <list.size() ; i++) {
            Map<String,Object> resultMap=new HashMap<>();
            if(i>=10){
                break;
            }
            resultMap.put("title",list.get(i).getTitle());
            resultMap.put("addTime",list.get(i).getAddTime());
            resultMap.put("id",list.get(i).getId());
            newList.add(resultMap);

        }
        return AjaxResponseBody.ok(newList);
    }
    //分页获取动态或者文化
    @RequestMapping("/getEssayByPage")
    @ResponseBody
    public AjaxResponseBody  getDynamicOrCultureByPage(@RequestBody Map<String,Object> map ) {
        Integer essayType = (Integer) map.get("essayType");
        Integer page = (Integer) map.get("page");
        Integer rows = (Integer) map.get("rows");
        PageHelperResult result = essayInfoService.getDynamicOrCultureByPage(essayType, page, rows);
        return AjaxResponseBody.ok(result);
    }
    //根据id查询文章
    @RequestMapping("/getEssayById")
    @ResponseBody
    public AjaxResponseBody getDynamicOrCultureById(@RequestBody Map<String,Object> map){
        Integer id = (Integer) map.get("id");
        EssayInfo essayInfo=essayInfoService.getDynamicOrCultureById(id);
        essayInfo.setContent(essayInfo.getContent().replaceAll("src=\"","src=\""+bucketHostName));
        if(essayInfo==null){
            return AjaxResponseBody.build(400,"抱歉,该文章已经被删除");
        }
        List<EssayInfo> dynamicOrCulture = essayInfoService.getDynamicOrCulture(essayInfo.getEssayType());

        int count = -1;
        for (int i = 0; i <dynamicOrCulture.size() ; i++) {
            if(dynamicOrCulture.get(i).getId()==id){
                count=i;
                break;
            }
        }
        if(count==-1){
            return AjaxResponseBody.build(400,"抱歉,该文章已经被删除");
        }
        Map<String,Object> resultMap=new HashMap<>();
        EssayInfo essayInfoUpper= null;
        EssayInfo essayInfoLower= null;
        resultMap.put("essayInfo",essayInfo);
        try {
            essayInfoLower = dynamicOrCulture.get(count+1);
            resultMap.put("LowerTitle",essayInfoLower.getTitle());
            resultMap.put("LowerId",essayInfoLower.getId());
        } catch (IndexOutOfBoundsException e1){
            resultMap.put("LowerTitle",null);
            resultMap.put("LowerId",null);
        }
        try {
            essayInfoUpper = dynamicOrCulture.get(count-1);
            resultMap.put("upperTitle",essayInfoUpper.getTitle());
            resultMap.put("upperId",essayInfoUpper.getId());
        } catch (ArrayIndexOutOfBoundsException e) {
            resultMap.put("upperTitle",null);
            resultMap.put("upperId",null);
        }
        EssayInfo update=new EssayInfo();
        update.setId(id);
        update.setViews(essayInfo.getViews()+1);
        essayInfoService.updateEssayInfoById(update);

        return AjaxResponseBody.ok(resultMap);
    }


}

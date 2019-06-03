package com.museum.controller;

import com.museum.common.pojo.AjaxResponseBody;
import com.museum.common.pojo.PageHelperResult;
import com.museum.pojo.ExhibitsInfo;
import com.museum.pojo.ExhibitsType;
import com.museum.service.ItemInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/itemInfo")
public class ExhibitsInfoController {
    @Autowired
    private ItemInfoService itemInfoService;
    //展品查询
    @RequestMapping("/list")
    @ResponseBody
    public AjaxResponseBody getItemInfoList(@RequestBody Map<String,String> map){
        Integer page = Integer.valueOf(map.get("page"));
        Integer rows = Integer.valueOf(map.get("rows"));
        PageHelperResult result = itemInfoService.getItemInfoList(page, rows);
        return AjaxResponseBody.ok(result);
    }
    //添加展品
    @RequestMapping("/insert")
    @ResponseBody
    public AjaxResponseBody insertItemInfo(@RequestBody ExhibitsInfo exhibitsInfo){
        itemInfoService.insertItemInfo(exhibitsInfo);
        return AjaxResponseBody.ok();
    }
    //查询展品类型
    @RequestMapping("/listOnItemType/verification")
    @ResponseBody
    public AjaxResponseBody getItemInfoList(){
        List<ExhibitsType> exhibitsTypes = itemInfoService.selectItemType();
        return AjaxResponseBody.ok(exhibitsTypes);
    }
    //删除展品
    @RequestMapping("/delete")
    @ResponseBody
    public  AjaxResponseBody deleteItemInfo(@RequestBody Map<String,String> map){
        Integer id=Integer.valueOf(map.get("id"));
       Integer i= itemInfoService.deleteItemInfo(id);
       if(i>=1){
           return AjaxResponseBody.ok();
       }
        return AjaxResponseBody.build(400,"删除失败,该展品已被删除");
    }
    //更新商品
    @RequestMapping("/update")
    @ResponseBody
    public AjaxResponseBody updateItemInfo(@RequestBody ExhibitsInfo exhibitsInfo ){
        Integer i=itemInfoService.updateItemInfo(exhibitsInfo);
        if(i>=1){
            return AjaxResponseBody.build(200,"修改成功");
        }
        return AjaxResponseBody.build(400,"修改失败,用户已被删除");
    }
}

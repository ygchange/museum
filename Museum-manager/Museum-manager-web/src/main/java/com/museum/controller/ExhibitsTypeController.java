package com.museum.controller;

import com.museum.common.pojo.AjaxResponseBody;
import com.museum.common.pojo.PageHelperResult;
import com.museum.pojo.ExhibitsType;
import com.museum.service.ItemTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 商品类型
 *
 */
@Controller
@RequestMapping("/itemType")
public class ExhibitsTypeController {
    @Autowired
    private ItemTypeService itemTypeService;
    //添加商品类型
    @RequestMapping
    @ResponseBody
    public AjaxResponseBody insertItemType(@RequestBody ExhibitsType exhibitsType){
        try {
            itemTypeService.insertItemType(exhibitsType);
            System.out.println("123");
            return AjaxResponseBody.ok();
        } catch (Exception e) {
            return AjaxResponseBody.build(400,"该商品类型已存在");
        }
    }
    //商品类型查询
    @RequestMapping(value = "/list",method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResponseBody getItemTypeList(@RequestBody Map<String,String> map){
        Integer page=Integer.valueOf(map.get("page"));
        Integer rows=Integer.valueOf(map.get("rows"));
        PageHelperResult result = itemTypeService.getItemTypeList(page, rows);
        return AjaxResponseBody.ok(result);
    }
    //判断商品类型是否存在
    @RequestMapping(value = "/verification",method = {RequestMethod.POST})
    @ResponseBody
    public  AjaxResponseBody verificationItemType(@RequestBody Map<String,String> map){
        String typeName = map.get("typeName");
        ExhibitsType exhibitsType = itemTypeService.selectItemTypeByUserName(typeName);
        if(exhibitsType==null){
            return AjaxResponseBody.ok();
        }else{
            return AjaxResponseBody.build(400,"该类型已存在");
        }

    }

}

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
 */
@Controller
@RequestMapping("/itemType")
public class ExhibitsTypeController {
    @Autowired
    private ItemTypeService itemTypeService;

    //添加商品类型
    @RequestMapping(value = "/insert", method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResponseBody insertItemType(@RequestBody ExhibitsType exhibitsType) {
        try {
            itemTypeService.insertItemType(exhibitsType);
            return AjaxResponseBody.ok();
        } catch (Exception e) {
            return AjaxResponseBody.build(400, "该商品类型已存在");
        }
    }

    //商品类型查询
    @RequestMapping(value = "/list", method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResponseBody getItemTypeList(@RequestBody Map<String, String> map) {
        Integer page = Integer.valueOf(map.get("page"));
        Integer rows = Integer.valueOf(map.get("rows"));
        PageHelperResult result = itemTypeService.getItemTypeList(page, rows);
        return AjaxResponseBody.ok(result);
    }

    //判断商品类型是否存在
    @RequestMapping(value = "/verification", method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResponseBody verificationItemType(@RequestBody Map<String, String> map) {
        String typeName = map.get("typeName");
        ExhibitsType exhibitsType = itemTypeService.selectItemTypeByUserName(typeName);
        if (exhibitsType == null) {
            return AjaxResponseBody.ok();
        } else {
            return AjaxResponseBody.build(412, "该类型已存在");
        }

    }
    //修改商品类型
    @RequestMapping("/update")
    @ResponseBody
    public AjaxResponseBody updateItemTypeById(@RequestBody ExhibitsType exhibitsType){
        Integer integer = itemTypeService.updateItemTypeById(exhibitsType);
        if(integer>=1){
            return AjaxResponseBody.build(200,"修改成功");
        }

        return AjaxResponseBody.build(400,"改展区已经被删除");
    }
    //删除展品类型
    @RequestMapping("/delete")
    @ResponseBody
    public  AjaxResponseBody deleteItemTypeById(@RequestBody Map<String,Object> map) {
        Integer id = (Integer) map.get("id");
        Integer integer = itemTypeService.deleteItemTypeById(id);
        if(integer==-1){
            return AjaxResponseBody.build(400,"该展区还有展品,暂时无法删除");
        }else if (integer>=1){
            return AjaxResponseBody.ok();
        }
        return AjaxResponseBody.build(400,"该展区已被删除");
    }

}

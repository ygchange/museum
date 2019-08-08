package com.museum.controller;

import com.museum.common.pojo.AjaxResponseBody;
import com.museum.pojo.ExhibitsInfo;
import com.museum.pojo.ExhibitsType;
import com.museum.service.ItemInfoService;
import com.museum.service.ItemTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class EcxcelController {
    @Value("${realm.name}")
    private String realm;
    @Autowired
    private ItemInfoService itemInfoService;
    @Autowired
    private ItemTypeService itemTypeService;

    @RequestMapping("/exportExhibitsInfo")
    @ResponseBody
    public AjaxResponseBody exportExhibitsInfo(@RequestBody Map<String, Object> map) {
        Integer itemType = (Integer) map.get("itemType");
        String itemName = (String) map.get("itemName");
        List<ExhibitsInfo> list = itemInfoService.getExhibitsInfo(itemType, itemName);
        if(list.size()==0){
            return AjaxResponseBody.build(400,"暂无数据,无法导出");
        }
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (ExhibitsInfo exhibitsInfo :
                list) {
            ExhibitsType exhibitsType = itemTypeService.selectItemTypeById(exhibitsInfo.getTypeId());
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("id", exhibitsInfo.getId());
            resultMap.put("name", exhibitsInfo.getName());
            resultMap.put("url", realm+"museumwx/detail-" + exhibitsInfo.getId());
            resultMap.put("type", exhibitsType.getTypeName());
            resultList.add(resultMap);
        }
        return AjaxResponseBody.ok(resultList);

    }
}

package com.museum.controller;

import com.museum.common.pojo.AjaxResponseBody;
import com.museum.pojo.ExhibitsInfo;
import com.museum.service.ItemInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EcxcelController {
    @Value("${code.url}")
    private String url;
    @Autowired
    private ItemInfoService itemInfoService;
    @RequestMapping("/exportExhibitsInfo")
    @ResponseBody
    public AjaxResponseBody exportExhibitsInfo( ) {
       List<ExhibitsInfo> list= itemInfoService.getExhibitsInfo();
       List<Map<String,Object>> resultList =null;
       if(list.size()!=0){
           resultList=new ArrayList<>();
       }
        for (ExhibitsInfo exhibitsInfo:
                list) {
            Map<String,Object> map=new HashMap<>();
            map.put("id",exhibitsInfo.getId());
            map.put("name",exhibitsInfo.getName());
            map.put("url",url+exhibitsInfo.getId());
            resultList.add(map);
        }
        return AjaxResponseBody.ok(resultList);

    }
}

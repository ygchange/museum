package com.museum.official.controller;

import com.museum.common.pojo.AjaxResponseBody;
import com.museum.pojo.EssayInfo;
import com.museum.service.EssayInfoService;
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
@RequestMapping("/map")
public class RotaryMapController {
    @Autowired
    private EssayInfoService essayInfoService;
    @Value("${qiniu.bucket.host.name}")
    private String bucketHostName;
    @Value("${realm.name}")
    private String realm;
    @RequestMapping("/get")
    @ResponseBody
    public AjaxResponseBody getRotaryMap(){
        List<EssayInfo> hotEssayInfo = essayInfoService.getHotEssayInfo();
        int count=0;
        List<Map<String,String>> newList=new ArrayList<>();
        for (int i = 0; i <hotEssayInfo.size() ; i++) {
            if(hotEssayInfo.get(i).getImg()==null){
                continue;
            }
            if (count==5){
                break;
            }
            Map<String,String> map=new HashMap<>();
            map.put("img",bucketHostName+hotEssayInfo.get(i).getImg());
            map.put("url",realm+"/official/Item.html?type="+hotEssayInfo.get(i).getEssayType()+"&id="+hotEssayInfo.get(i).getId());
            map.put("title",hotEssayInfo.get(i).getTitle());
            newList.add(map);

            count+=1;
        }
        return AjaxResponseBody.ok(newList);

    }
}

package com.museum.controller;

import com.museum.common.pojo.AjaxResponseBody;
import com.museum.common.pojo.PageHelperResult;
import com.museum.common.utils.CodeUploadUtil;
import com.museum.pojo.ExhibitsInfo;
import com.museum.pojo.ExhibitsType;
import com.museum.service.ItemInfoService;
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
@RequestMapping("/itemInfo")
public class ExhibitsInfoController {
    @Value("${qiniu.access.key}")
    private String accesskey;

    @Value("${qiniu.secret.key}")
    private String secretKey;

    @Value("${qiniu.bucket.name}")
    private String bucketName;

    @Value("${qiniu.bucket.host.name}")
    private String bucketHostName;
    @Value("${realm.name}")
    private String realm;
    @Autowired
    private ItemInfoService itemInfoService;
    //分页查询展品信息
    @RequestMapping("/list")
    @ResponseBody
    public AjaxResponseBody getItemInfoList(@RequestBody Map<String,Object> map){
        Integer page = (Integer) map.get("page");
        Integer rows = (Integer) map.get("rows");
        Integer itemType = (Integer) map.get("itemType");
        String itemName= (String) map.get("itemName");
        PageHelperResult result = itemInfoService.getItemInfoList(page, rows,itemType,itemName,bucketHostName);
        return AjaxResponseBody.ok(result);
    }
    //查询展品名字
    @RequestMapping("/list/name")
    @ResponseBody
    public AjaxResponseBody getItemInfoName(){
        List<ExhibitsInfo> exhibitsInfos=itemInfoService.getExhibitsInfo();
        List<Map<String, String>> list=new ArrayList<>();
        for (ExhibitsInfo exhibitsInfo:exhibitsInfos
        ) {
            Map<String,String> hashMap=new HashMap<>();
            hashMap.put("value",exhibitsInfo.getName());
            list.add(hashMap);
        }
        return AjaxResponseBody.ok(list);
    }
    //添加展品
    @RequestMapping("/insert")
    @ResponseBody
    public AjaxResponseBody insertItemInfo(@RequestBody ExhibitsInfo exhibitsInfo){
        ExhibitsInfo exhibitsInfoResult = itemInfoService.insertItemInfo(exhibitsInfo);
        realm=realm+"museumwx/detail-"+exhibitsInfoResult.getId();
        try {
            String path = CodeUploadUtil.generateCode(accesskey, secretKey, exhibitsInfoResult.getName(), bucketName, realm);
            exhibitsInfoResult.setQrCode(path);
            itemInfoService.updateItemInfoById(exhibitsInfoResult);
        } catch (Exception e) {
            itemInfoService.deleteItemInfoById(exhibitsInfoResult.getId());
            return AjaxResponseBody.build(400,"添加失败");
        }
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
    public  AjaxResponseBody deleteItemInfoById(@RequestBody Map<String,String> map){
        Integer id=Integer.valueOf(map.get("id"));
            Integer i= itemInfoService.deleteItemInfoById(id);
        if(i>=1){
           return AjaxResponseBody.ok();
       }
        return AjaxResponseBody.build(400,"该展品已被删除");
    }
    //更新展品
    @RequestMapping("/update")
    @ResponseBody
    public AjaxResponseBody updateItemInfoById(@RequestBody ExhibitsInfo exhibitsInfo ){
        if(exhibitsInfo.getAudioName().indexOf(bucketHostName)!=-1){
            exhibitsInfo.setAudioName(exhibitsInfo.getAudioName().substring(bucketHostName.length()));
        }
        if(exhibitsInfo.getImgName().indexOf(bucketHostName)!=-1){
            exhibitsInfo.setImgName(exhibitsInfo.getImgName().substring(bucketHostName.length()));
        }
        Integer i=itemInfoService.updateItemInfoById(exhibitsInfo);
        if(i>=1){
            return AjaxResponseBody.build(200,"修改成功");
        }
        return AjaxResponseBody.build(400,"用户已被删除");
    }
}

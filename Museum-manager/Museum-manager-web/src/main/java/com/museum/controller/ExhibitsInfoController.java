package com.museum.controller;

import com.google.zxing.WriterException;
import com.museum.common.pojo.AjaxResponseBody;
import com.museum.common.pojo.PageHelperResult;
import com.museum.common.utils.CodeUploadUtil;
import com.museum.common.utils.QiniuUtil;
import com.museum.pojo.ExhibitsInfo;
import com.museum.pojo.ExhibitsType;
import com.museum.service.ItemInfoService;
import com.qiniu.common.QiniuException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
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
    @Value("${code.url}")
    private String url;
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
        exhibitsInfo.setImgName(bucketHostName+exhibitsInfo.getImgName());
        exhibitsInfo.setAudioName(bucketHostName+exhibitsInfo.getAudioName());
        ExhibitsInfo exhibitsInfoResult = itemInfoService.insertItemInfo(exhibitsInfo);
        url=url+exhibitsInfoResult.getId();
        try {
            String path = CodeUploadUtil.generateCode(accesskey, secretKey, exhibitsInfoResult.getName(), bucketName, url);
            String qiniu =bucketHostName+path;
            exhibitsInfoResult.setQrCode(qiniu);
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
    //更新商品
    @RequestMapping("/update")
    @ResponseBody
    public AjaxResponseBody updateItemInfoById(@RequestBody ExhibitsInfo exhibitsInfo ){
        if(exhibitsInfo.getAudioName().indexOf(bucketHostName)==-1){
            exhibitsInfo.setAudioName(bucketHostName+exhibitsInfo.getAudioName());
        }
        if(exhibitsInfo.getImgName().indexOf(bucketHostName)==-1){
            exhibitsInfo.setImgName(bucketHostName+exhibitsInfo.getImgName());
        }
        Integer i=itemInfoService.updateItemInfoById(exhibitsInfo);
        if(i>=1){
            return AjaxResponseBody.build(200,"修改成功");
        }
        return AjaxResponseBody.build(400,"用户已被删除");
    }
}

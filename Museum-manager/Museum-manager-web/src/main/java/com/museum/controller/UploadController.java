package com.museum.controller;

import com.museum.common.pojo.AjaxResponseBody;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UploadController {
    //引入第一步的七牛配置
    @Value("${qiniu.access.key}")
    private String accesskey;

    @Value("${qiniu.secret.key}")
    private String secretKey;

    @Value("${qiniu.bucket.name}")
    private String bucketName;

    @Value("${qiniu.bucket.host.name}")
    private String bucketHostName;
    //获取七牛云token
    @RequestMapping("/file/upload")
    @ResponseBody
    public AjaxResponseBody uploadByImgAndMp3() {
        Auth auth = Auth.create(this.accesskey, this.secretKey);
        String token = auth.uploadToken(this.bucketName);
        Map<String,String> map=new HashMap<>();
        map.put("token",token);
        map.put("bucketHostName",bucketHostName);
        return AjaxResponseBody.ok(map);
    }

    @RequestMapping("/file/bucketHostName")
    @ResponseBody
    public AjaxResponseBody getBucketHostName(){
        return  AjaxResponseBody.ok(bucketHostName);
    }
    //删除七牛云图片
    @RequestMapping("/file/delete")
    @ResponseBody
    public  AjaxResponseBody deleteByName(@RequestBody Map<String,String> map){
        String imgName = map.get("imgName");
        Auth auth = Auth.create(accesskey, secretKey);
        Configuration config = new Configuration(Zone.autoZone());
        BucketManager bucketMgr = new BucketManager(auth, config);
        //指定需要删除的文件，和文件所在的存储空间
        String  key = imgName;
        try {
            Response delete = bucketMgr.delete(bucketName, key);
            delete.close();
        }catch (Exception e){
           return  AjaxResponseBody.build(412,"删除失败");
        }
        return AjaxResponseBody.ok();
    }
}

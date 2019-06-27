package com.museum.common.utils;


import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

public class test {
    public static void main(String[] args) throws IOException {
        Auth auth = Auth.create("CnR-Fl77zEBcEbBOUoex7A72eeLoYwsAZywmr4ji", "fX-ZdBaErQvS-HTauYajABxBBlDdhGVz7DWtELf5");
        Configuration config = new Configuration(Zone.autoZone());
        BucketManager bucketMgr = new BucketManager(auth, config);
//        //指定需要删除的文件，和文件所在的存储空间
//        String bucketName = "museum";
//        String  key = "museum/fd34b3a9947d21569.mp3";
//        try {
//            Response delete = bucketMgr.delete(bucketName, key);
//            delete.close();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        System.out.println("结束了");

//        byte[] bytes = Files.readAllBytes(file.toPath());
//        String token = auth.uploadToken("museum");
//        Response put = new UploadManager(config).put(bytes, "11111", token);

    }
}

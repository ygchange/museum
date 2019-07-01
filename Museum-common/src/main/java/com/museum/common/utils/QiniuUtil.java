package com.museum.common.utils;

import com.museum.common.pojo.AjaxResponseBody;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.util.Auth;

public class QiniuUtil {
    public static void deleteImg(String imgName,String accesskey,String secretKey,String bucketName) throws QiniuException {
        Auth auth = Auth.create(accesskey, secretKey);
        Configuration config = new Configuration(Zone.autoZone());
        BucketManager bucketMgr = new BucketManager(auth, config);
        //指定需要删除的文件，和文件所在的存储空间
        String  key = imgName;

            Response delete = bucketMgr.delete(bucketName, key);
            delete.close();



    }
}

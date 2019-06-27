//package com.museum.common.utils;
//
//import com.qiniu.util.Auth;
//
//public class UploadFactory {
//    public static UploadUtil createUpload(String accessKey, String secretKeySpec, String bucketHostName, String bucketName) {
//        Auth auth = Auth.create(accessKey, secretKeySpec);
//        return new QiniuUtil(bucketHostName, bucketName, auth);
//    }
//
//    public static void main(String[] args) {
//        Auth auth = Auth.create("CnR-Fl77zEBcEbBOUoex7A72eeLoYwsAZywmr4ji", "fX-ZdBaErQvS-HTauYajABxBBlDdhGVz7DWtELf5");
//        System.out.println(auth.uploadToken("museum"));
//    }
//}

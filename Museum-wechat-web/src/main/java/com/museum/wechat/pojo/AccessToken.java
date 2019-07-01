package com.museum.wechat.pojo;

public class AccessToken {
   public static  String ACCESSTOKEN;
    public static Long Timestamp;


    public static Long getTimestamp() {
        return Timestamp;
    }

    public static void setTimestamp(Long timestamp) {
        Timestamp = timestamp;
    }

    public static String getACCESSTOKEN() {
        return ACCESSTOKEN;
    }

    public static void setACCESSTOKEN(String ACCESSTOKEN) {
        AccessToken.ACCESSTOKEN = ACCESSTOKEN;
    }


}

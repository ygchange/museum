package com.museum.wechat.pojo;

public class JsapiTicket {
    public static String ticket;
    public   static String expiresIn;
    public static Long Timestamp;

    public static String getTicket() {
        return ticket;
    }

    public static void setTicket(String ticket) {
        JsapiTicket.ticket = ticket;
    }

    public static String getExpiresIn() {
        return expiresIn;
    }

    public static void setExpiresIn(String expiresIn) {
        JsapiTicket.expiresIn = expiresIn;
    }

    public static Long getTimestamp() {
        return Timestamp;
    }

    public static void setTimestamp(Long timestamp) {
        Timestamp = timestamp;
    }
}

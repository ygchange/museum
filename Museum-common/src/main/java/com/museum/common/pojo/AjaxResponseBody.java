package com.museum.common.pojo;



import java.io.Serializable;


public class AjaxResponseBody implements Serializable {



    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object info;
   

    public static AjaxResponseBody build(Integer status, String msg, Object info) {
        return new AjaxResponseBody(status, msg, info);
    }

    public static AjaxResponseBody ok(Object info) {
        return new AjaxResponseBody(info);
    }

    public static AjaxResponseBody ok() {
        return new AjaxResponseBody(null);
    }

    public AjaxResponseBody() {

    }

    public static AjaxResponseBody build(Integer status, String msg) {
        return new AjaxResponseBody(status, msg, null);
    }

    public AjaxResponseBody(Integer status, String msg, Object info) {
        this.status = status;
        this.msg = msg;
        this.info = info;

    }

    public AjaxResponseBody(Object info) {
        this.status = 200;
        this.msg = "OK";
        this.info = info;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getinfo() {
        return info;
    }

    public void setinfo(Object info) {
        this.info = info;
    }

    
}
package com.museum.wechat.utils;

import com.museum.common.utils.UnicodeUtil;
import com.museum.pojo.WechatUser;
import com.museum.service.WeChatUserService;
import com.museum.wechat.pojo.JsapiTicket;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class WeixinUtil {
    public final static String js_api_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
    @Autowired
   private WeChatUserService weChatUserService;
    @Autowired
    private TokenUtil tokenUtil;
    private Logger logger=Logger.getLogger(WeixinUtil.class);

    public  String getJsapiTicket() {
        String token = tokenUtil.getToken();
        String requestUrl = js_api_ticket_url.replace("ACCESS_TOKEN",token);
        if(JsapiTicket.ticket==null){
            JSONObject jsonObject =CommonUtil.httpsRequest(requestUrl, "GET", null);
            if(jsonObject!=null){
                JsapiTicket.setTicket(jsonObject.getString("ticket"));
                JsapiTicket.setTimestamp(System.currentTimeMillis());
            }else{
                getJsapiTicket();
            }
        }else {
            if(System.currentTimeMillis()- JsapiTicket.Timestamp>=3600*1000){
                JSONObject jsonObject =CommonUtil.httpsRequest(requestUrl, "GET", null);
                if(jsonObject!=null){
                    JsapiTicket.setTicket(jsonObject.getString("ticket"));
                    JsapiTicket.setTimestamp(System.currentTimeMillis());
                }else{
                    getJsapiTicket();
                }
            }
        }
        return JsapiTicket.ticket;

    }


    public  String processWechatMag(String openid,String token){
        JSONObject jsonObject = CommonUtil.httpsRequest("https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + token + "&openid=" + openid + "&lang=zh_CN", "GET", null);
        WechatUser wechatUser =new WechatUser();
        wechatUser.setNickName(UnicodeUtil.unicodeEncode(jsonObject.getString("nickname")));
        wechatUser.setCity(jsonObject.getString("country")+" "+jsonObject.getString("province")+" "+jsonObject.getString("city"));
        wechatUser.setHeadImg(jsonObject.getString("headimgurl"));
        wechatUser.setUserId(jsonObject.getString("openid"));
        wechatUser.setOpenTime(new Date(jsonObject.getLong("subscribe_time")*1000));
        wechatUser.setStatus(jsonObject.getInt("subscribe"));
        WechatUser wechatUserResult = weChatUserService.selectWeChatInfoByUserId(wechatUser);
        if(wechatUserResult==null){
            weChatUserService.insertWeChatInfo(wechatUser);
        }else {
            weChatUserService.updateStatusByUserId(wechatUser);
        }

        return jsonObject.getString("nickname");
    }
    public void noProcessWechatMag(String openid){
        WechatUser wechatUser=new WechatUser();
        wechatUser.setStatus(0);
        wechatUser.setUserId(openid);
        weChatUserService.updateStatusByUserId(wechatUser);
    }
    public Map<String, String> getWeChatUserInfo(String openid ){
        Map<String,String> map =null;
        JSONObject jsonObject = CommonUtil.httpsRequest("https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + tokenUtil.getToken() + "&openid=" + openid + "&lang=zh_CN", "GET", null);
        if(jsonObject!=null) {
                map = new HashMap<>();
                map.put("headImgUrl", jsonObject.getString("headimgurl"));
                map.put("nickname", jsonObject.getString("nickname"));
                map.put("city", jsonObject.getString("country") + " " + jsonObject.getString("province") + " " + jsonObject.getString("city"));
            }
        return map;
    }

    public  WechatUser updateWeChatInfo(String openid) throws Exception{
        WechatUser wechatUser=null;
        JSONObject jsonObject = CommonUtil.httpsRequest("https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + tokenUtil.getToken() + "&openid=" + openid + "&lang=zh_CN", "GET", null);
        if(jsonObject!=null) {
            wechatUser = new WechatUser();
            if(jsonObject.getInt("subscribe")!=0){
                wechatUser.setNickName(UnicodeUtil.unicodeEncode(jsonObject.getString("nickname")));
                wechatUser.setCity(jsonObject.getString("country") + " " + jsonObject.getString("province") + " " + jsonObject.getString("city"));
                wechatUser.setHeadImg(jsonObject.getString("headimgurl"));

                wechatUser.setOpenTime(new Date(jsonObject.getLong("subscribe_time")*1000));
            }
            wechatUser.setUserId(jsonObject.getString("openid"));
            wechatUser.setStatus(jsonObject.getInt("subscribe"));
        }else{
            throw new Exception();
        }
        return wechatUser;

    }
}

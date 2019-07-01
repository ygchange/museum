package com.museum.wechat.utils;

import com.museum.common.utils.UnicodeUtil;
import com.museum.pojo.WechatUser;
import com.museum.service.WeChatUserService;
import com.museum.wechat.pojo.JsapiTicket;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.util.Date;

@Component
public class WeixinUtil {
    public final static String js_api_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
    @Autowired
   private WeChatUserService weChatUserService;
    @Autowired
    private TokenUtil tokenUtil;
    private Logger logger=Logger.getLogger(WeixinUtil.class);

    public  JsapiTicket getJsapiTicket(String wxAppId, String appSecret) {

        String token = tokenUtil.getToken();
        JsapiTicket ticket=new JsapiTicket();
        String requestUrl = js_api_ticket_url.replace("ACCESS_TOKEN",token);
        JSONObject jsonObject =CommonUtil.httpsRequest(requestUrl, "GET", null);
        if(jsonObject.getString("errcode").equals("0")){
            ticket.setTicket(jsonObject.getString("ticket"));
            ticket.setExpiresIn(jsonObject.getString("expires_in"));
        }else{
            System.out.println("error");
        }
        return ticket;

    }


    public  String processWechatMag(String openid,String token){
        JSONObject jsonObject = CommonUtil.httpsRequest("https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + token + "&openid=" + openid + "&lang=zh_CN", "GET", null);
        WechatUser wechatUser =new WechatUser();
        wechatUser.setNickName(UnicodeUtil.unicodeEncode(jsonObject.getString("nickname")));
        wechatUser.setCity(jsonObject.getString("country")+" "+jsonObject.getString("province")+" "+jsonObject.getString("city"));
        wechatUser.setHeadImg(jsonObject.getString("headimgurl"));
        wechatUser.setUserId(jsonObject.getString("openid"));
        wechatUser.setOpenTime(new Date());
        wechatUser.setStatus(1);
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
}

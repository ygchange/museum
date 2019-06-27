package com.museum.wechat.utils;

import com.museum.common.utils.UnicodeUtil;
import com.museum.pojo.WechatUser;
import com.museum.service.WeChatUserService;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.util.Date;

@Component
public class WeixinUtil {
    @Value("${oauth.wxAppId}")
    private String wxAppId;
    @Value("${oauth.appSecret}")
    private String appSecret;
    @Autowired
   private WeChatUserService weChatUserService;

    private Logger logger=Logger.getLogger(WeixinUtil.class);
    public  String getToken() {
        String grant_type = "client_credential";
        JSONObject getToken = CommonUtil.httpsRequest("https://api.weixin.qq.com/cgi-bin/token?grant_type=" + grant_type + "&appid=" + wxAppId+ "&secret=" + appSecret,"GET",null);
        JSONObject jsonObject = JSONObject.fromObject(getToken);
        String token = (String)jsonObject.get("access_token");
        return token;
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

package com.museum.wechat.utils;

import com.museum.wechat.pojo.AccessToken;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TokenUtil {
    @Value("${oauth.wxAppId}")
    private  String wxAppId;
    @Value("${oauth.appSecret}")
    private  String appSecret;

    public  String getToken() {

        if(AccessToken.ACCESSTOKEN==null){
            String grant_type = "client_credential";
            JSONObject getToken = CommonUtil.httpsRequest("https://api.weixin.qq.com/cgi-bin/token?grant_type=" + grant_type + "&appid=" + wxAppId+ "&secret=" + appSecret,"GET",null);
            JSONObject jsonObject = JSONObject.fromObject(getToken);
            String token = (String)jsonObject.get("access_token");
            AccessToken.setACCESSTOKEN(token);
            AccessToken.setTimestamp(System.currentTimeMillis());
        }else {
            if(System.currentTimeMillis()-AccessToken.Timestamp>=3600*1000){
                String grant_type = "client_credential";
                JSONObject getToken = CommonUtil.httpsRequest("https://api.weixin.qq.com/cgi-bin/token?grant_type=" + grant_type + "&appid=" + wxAppId+ "&secret=" + appSecret,"GET",null);
                JSONObject jsonObject = JSONObject.fromObject(getToken);
                String token = (String)jsonObject.get("access_token");
                AccessToken.setACCESSTOKEN(token);
                AccessToken.setTimestamp(System.currentTimeMillis());
            }
        }
        return AccessToken.ACCESSTOKEN;
    }
}

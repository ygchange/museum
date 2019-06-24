package com.museum.wechat.utils;

import com.museum.wechat.pojo.SNSUserInfo;
import com.museum.wechat.pojo.WeixinOauth2Token;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;


public class AdvancedUtil {
    private static Logger logger = Logger.getLogger(AdvancedUtil.class);

    public AdvancedUtil() {
    }

    public static WeixinOauth2Token getOauth2AccessToken(String appId, String appSecret, String code) {
        WeixinOauth2Token wat = null;
        String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        requestUrl = requestUrl.replace("APPID", appId);
        requestUrl = requestUrl.replace("SECRET", appSecret);
        requestUrl = requestUrl.replace("CODE", code);
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", (String)null);
        if (jsonObject != null) {
            try {
                wat = new WeixinOauth2Token();
                wat.setAccessToken(jsonObject.getString("access_token"));
                wat.setExpiresIn(jsonObject.getInt("expires_in"));
                wat.setRefreshToken(jsonObject.getString("refresh_token"));
                wat.setOpenId(jsonObject.getString("openid"));
                wat.setScope(jsonObject.getString("scope"));
            } catch (Exception var9) {
                wat = null;
                int errorCode = jsonObject.getInt("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                logger.error("获取网页授权凭证失败 errcode:{" + errorCode + "} errmsg:{" + errorMsg.toString() + "}");
            }
        }

        return wat;
    }

    public static SNSUserInfo getSNSUserInfo(String accessToken, String openId) {
        SNSUserInfo snsUserInfo = null;
        String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", (String)null);
        if (jsonObject != null) {
            try {
                snsUserInfo = new SNSUserInfo();
                snsUserInfo.setOpenId(jsonObject.getString("openid"));
                snsUserInfo.setNickname(jsonObject.getString("nickname"));
                snsUserInfo.setSex(jsonObject.getInt("sex"));
                snsUserInfo.setCountry(jsonObject.getString("country"));
                snsUserInfo.setProvince(jsonObject.getString("province"));
                snsUserInfo.setCity(jsonObject.getString("city"));
                snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
                snsUserInfo.setPrivilegeList("");
            } catch (Exception var8) {
                snsUserInfo = null;
                int errorCode = jsonObject.getInt("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                logger.error("获取用户信息失败 errcode:{" + errorCode + "} errmsg:{" + errorMsg + "}");
            }
        }

        return snsUserInfo;
    }
}

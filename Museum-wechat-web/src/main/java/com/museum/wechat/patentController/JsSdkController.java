package com.museum.wechat.patentController;

import com.museum.wechat.pojo.JsapiTicket;
import com.museum.wechat.utils.Sign;
import com.museum.wechat.utils.WeixinUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class JsSdkController {
    @Value("${oauth.wxAppId}")
    private String wxAppId;
    @Value("${oauth.appSecret}")
    private String appSecret;
    @Value("${oauth.returnUrl}")
    private String returnUrl;
    @Autowired
    private WeixinUtil weixinUtil;
    @RequestMapping("/wechat/jsSdk")
    @ResponseBody
    public Map<String,String> getWechatJsSdk(@RequestBody Map<String,String> map){
        String url = map.get("url");
        url=returnUrl+"/"+url;
        String ticket= weixinUtil.getJsapiTicket();
        Map<String, String> sign = Sign.sign(ticket, url, wxAppId);
        return  sign;

    }
}

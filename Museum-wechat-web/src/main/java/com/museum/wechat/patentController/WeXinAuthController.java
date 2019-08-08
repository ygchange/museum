package com.museum.wechat.patentController;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@Controller
@RequestMapping("/weChat")
public class WeXinAuthController {
    @Value("${oauth.wxAppId}")
    private String wxAppId;
    @Value("${realm.name}")
    private String realm;
    @RequestMapping("/auth")
    public String auth(String returnUrl, HttpServletResponse response) throws IOException {
        return "redirect:" +
                String.format("https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect",
                        this.wxAppId, realm+"museumwx/wechat/wechatOauth", "snsapi_userinfo", returnUrl);
    }
}

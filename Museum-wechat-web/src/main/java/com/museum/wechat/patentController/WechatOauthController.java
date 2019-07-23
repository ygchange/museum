package com.museum.wechat.patentController;

import com.museum.pojo.WechatUser;
import com.museum.service.ItemInfoService;
import com.museum.service.WeChatUserService;
import com.museum.wechat.pojo.WeixinOauth2Token;
import com.museum.wechat.utils.AdvancedUtil;
import com.museum.wechat.utils.WeixinUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class WechatOauthController {
    @Autowired
    private ItemInfoService itemInfoService;
    @Autowired
    private WeixinUtil weixinUtil;
    @Autowired
    private WeChatUserService weChatUserService;
    @Value("${oauth.wxAppId}")
    private String wxAppId;
    @Value("${oauth.appSecret}")
    private String appSecret;
    @Value("${oauth.returnUrl}")
    private String returnUrl;
    private Logger logger = Logger.getLogger(WechatOauthController.class);
    private  WeixinOauth2Token weixinOauth2Token;

    @RequestMapping("/wechat/wechatOauth")
    public void WechatOauth(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        try {
            weixinOauth2Token = AdvancedUtil.getOauth2AccessToken(wxAppId, appSecret, code);
            String accessToken = weixinOauth2Token.getAccessToken();
            String openId = weixinOauth2Token.getOpenId();
            WechatUser wechatUserResult = weixinUtil.updateWeChatInfo(openId);
            if(wechatUserResult.getStatus()==0){
                response.sendRedirect(returnUrl+"/museumwx/author?url="+state+"&status=0");
            }else {
                weChatUserService.updateStatusByUserId(wechatUserResult);
                response.sendRedirect(returnUrl+"/museumwx/author?url="+state+"&token="+accessToken+"&openid="+openId+"&status=1");

            }
        } catch (Exception e) {
            response.sendRedirect(returnUrl+"/museumwx/"+state);
        }
    }
}

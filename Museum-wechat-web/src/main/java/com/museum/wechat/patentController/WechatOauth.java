package com.museum.wechat.patentController;

import com.museum.common.utils.UnicodeUtil;
import com.museum.wechat.pojo.SNSUserInfo;
import com.museum.wechat.pojo.WeixinOauth2Token;
import com.museum.wechat.utils.AdvancedUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class WechatOauth {
    private static final long serialVersionUID = 1L;
    private Logger logger = Logger.getLogger(WechatOauth.class);

    @RequestMapping("/wechat/wechatOauth")
    public void WechatOauth(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String code = request.getParameter("code");
        String state = request.getParameter("state");

        SNSUserInfo snsUserInfo = null;
        String openId = null;
        if (!"authdeny".equals(code)) {
            try {
                WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken("wxa2c23b7573ed8ed9", "9c2d9582477d81a144a37de0f10b97b3", code);
                String accessToken = weixinOauth2Token.getAccessToken();
                openId = weixinOauth2Token.getOpenId();
                snsUserInfo = AdvancedUtil.getSNSUserInfo(accessToken, openId);
                String s = UnicodeUtil.unicodeEncode(snsUserInfo.getNickname());
                snsUserInfo.setNickname(s);
                logger.info(snsUserInfo.toString());
                if (state.equals("announcement")) {
                  response.sendRedirect("http://supnft.natappfree.cc/dist/#/notice/img="+snsUserInfo.getHeadImgUrl()+"&nickName="+snsUserInfo.getNickname());
                } else if (state.equals("lost")){
                    response.sendRedirect("http://supnft.natappfree.cc/dist/#/lostandfound/img="+snsUserInfo.getHeadImgUrl()+"&nickName="+snsUserInfo.getNickname());
                }else {

                }
            } catch (Exception var9) {
                this.logger.info("oauth_get is error:", var9);
                response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxa2c23b7573ed8ed9&redirect_uri=http://www.server.trueonly.cc/patent/wechat/wechatOauth&response_type=code&scope=snsapi_userinfo&state=phone#wechat_redirect");
            }
        }
    }
}

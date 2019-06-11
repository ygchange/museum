package com.museum.wechat.patentController;

import com.museum.common.pojo.SNSUserInfo;
import com.museum.common.pojo.WeixinOauth2Token;
import com.museum.common.utils.AdvancedUtil;
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
                WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken("wx27a5c8bcf72f488f", "f6cb242b68d77e4dfc9d117086734b39", code);
                String accessToken = weixinOauth2Token.getAccessToken();
                openId = weixinOauth2Token.getOpenId();
                snsUserInfo = AdvancedUtil.getSNSUserInfo(accessToken, openId);
                if (state.equals("phone")) {
                    request.getRequestDispatcher("/aaa/aaa.html?state=" + state + "&nickName=" + snsUserInfo.getNickname() + "&img=" + snsUserInfo.getHeadImgUrl() + "&openId=" + openId).forward(request, response);
                } else {
                    request.getRequestDispatcher("/aaa/bbb.html?state=" + state + "&nickName=" + snsUserInfo.getNickname() + "&img=" + snsUserInfo.getHeadImgUrl() + "&openId=" + openId).forward(request, response);
                }
            } catch (Exception var9) {
                this.logger.info("oauth_get is error:", var9);
                request.getRequestDispatcher("/return.jsp").forward(request, response);
            }
        }
    }
}

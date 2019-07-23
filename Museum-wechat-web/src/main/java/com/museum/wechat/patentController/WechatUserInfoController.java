package com.museum.wechat.patentController;

import com.museum.common.pojo.AjaxResponseBody;
import com.museum.pojo.WechatUser;
import com.museum.wechat.pojo.SNSUserInfo;
import com.museum.wechat.utils.AdvancedUtil;
import com.museum.wechat.utils.WeixinUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


@Controller
@RequestMapping("/wechat")
public class WechatUserInfoController {
    @Autowired
    private WeixinUtil weixinUtil;
    @RequestMapping("/userInfo")
    @ResponseBody
    public AjaxResponseBody getWechatUserInfo(@RequestBody Map<String,String> map) {
        String access_token = map.get("ACCESS_TOKEN");
        String openid = map.get("OPENID");
        Map<String, String> weChatUserInfo = weixinUtil.getWeChatUserInfo(openid);

        if (weChatUserInfo != null) {
            return AjaxResponseBody.ok(weChatUserInfo);
        } else {

            return AjaxResponseBody.build(400,"获取用户信息失败");
        }
    }
}


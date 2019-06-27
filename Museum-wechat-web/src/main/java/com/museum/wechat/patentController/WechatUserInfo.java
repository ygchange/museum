package com.museum.wechat.patentController;

import com.museum.common.pojo.AjaxResponseBody;
import com.museum.wechat.pojo.SNSUserInfo;
import com.museum.wechat.utils.AdvancedUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


@Controller
@RequestMapping("/wechat")
public class WechatUserInfo {
    @RequestMapping("/userInfo")
    @ResponseBody
    public AjaxResponseBody getWechatUserInfo(@RequestBody Map<String,String> map) {
        String access_token = map.get("ACCESS_TOKEN");
        String openid = map.get("OPENID");
        SNSUserInfo snsUserInfo = AdvancedUtil.getSNSUserInfo(access_token, openid);

        if (snsUserInfo != null) {
            return AjaxResponseBody.ok(snsUserInfo);
        } else {

            return AjaxResponseBody.build(400,"   ");
        }
    }
}


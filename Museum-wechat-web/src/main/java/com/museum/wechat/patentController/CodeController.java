package com.museum.wechat.patentController;

import com.museum.common.pojo.AjaxResponseBody;
import com.museum.wechat.utils.CommonUtil;
import com.museum.wechat.utils.TokenUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class CodeController {
    @Value("${oauth.wxAppId}")
    private String wxAppId;
    @Value("${oauth.appSecret}")
    private String appSecret;
    @Autowired
    private TokenUtil tokenUtil;
    @RequestMapping("weChat/get/code")
    @ResponseBody
    public AjaxResponseBody getWechatCode(@RequestBody Map<String,String> map){
        String id=map.get("id");
//        JSONObject tokenResult = CommonUtil.httpsRequest("https://api.weixin.qq.com/cgi-bin/token?grant_type=" +
//                "client_credential&appid="+wxAppId+"&secret="+appSecret, "GET", null);
        String token = tokenUtil.getToken();
        JSONObject jsonObject = CommonUtil.httpsRequest("https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+token, "POST", "{\"expire_seconds\": 604800, \"action_name\": \"QR_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \""+id+"\"}}}");
        String ticket = jsonObject.getString("ticket");
        String code="https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+ticket;
        return AjaxResponseBody.ok(code);
    }
}

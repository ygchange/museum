package com.museum.wechat.utils;
import net.sf.json.JSONObject;
public class TEST {
    public static void main(String[] args) {
        JSONObject jsonObject = CommonUtil.httpsRequest("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxa2c23b7573ed8ed9&secret=9c2d9582477d81a144a37de0f10b97b3", "GET", null);
        String s = (String) jsonObject.get("access_toke");
        System.out.println(jsonObject);
        JSONObject jsonObject1 = CommonUtil.httpsRequest("https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+s, "POST", "{\"expire_seconds\": 604800, \"action_name\": \"QR_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \"5\"}}}");
        System.out.println(jsonObject1);
        String s1 = (String) jsonObject1.get("ticket");
        System.out.println(s1);
//        JSONObject jsonObject2 = CommonUtil.httpsRequest("https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+s1, "GET", null);
//        System.out.println(jsonObject2);
    }

}

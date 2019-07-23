package com.museum.wechat.controller;

import com.museum.common.pojo.AjaxResponseBody;
import com.museum.common.utils.GetIpUtil;
import com.museum.pojo.ExhibitsInfo;
import com.museum.pojo.SelectLog;
import com.museum.pojo.WechatUser;
import com.museum.service.ItemInfoService;
import com.museum.service.WeChatUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/weChatExhibits")
public class WeChatExhibitsInfoController {
    @Value("${qiniu.bucket.host.name}")
    private String bucketHostName;
    @Autowired
    private ItemInfoService itemInfoService;
    @Autowired
    private WeChatUserService weChatUserService;
    @RequestMapping("/select")
    @ResponseBody
    public AjaxResponseBody selectExhibitsInfoById(HttpServletRequest httpServletRequest, @RequestBody Map<String, String> map){
        String openid = map.get("openid");
        Integer id=Integer.valueOf(map.get("id"));
        WechatUser wechatUser=new WechatUser();
        wechatUser.setUserId(openid);
        ExhibitsInfo exhibitsInfo = itemInfoService.getExhibitsInfoById(id);
        if(exhibitsInfo!=null) {
            WechatUser wechatUserResult = weChatUserService.selectWeChatInfoByUserId(wechatUser);
            String ip2 = GetIpUtil.getIp2(httpServletRequest);
            SelectLog selectLog=new SelectLog();
            selectLog.setSelectTime(new Date());
            selectLog.setWechatUserId(wechatUserResult.getId());
            selectLog.setExhibitsInfoId(id);
            selectLog.setSelectUserIp(ip2);
            exhibitsInfo.setQueryTimes(exhibitsInfo.getQueryTimes()+1);
            itemInfoService.updateItemInfoAndInsertSelectLog(exhibitsInfo,selectLog);
            exhibitsInfo.setQrCode(bucketHostName+exhibitsInfo.getQrCode());
            exhibitsInfo.setAudioName(bucketHostName+exhibitsInfo.getAudioName());
            exhibitsInfo.setImgName(bucketHostName+exhibitsInfo.getImgName());
            return AjaxResponseBody.ok(exhibitsInfo);
        }else {
            return AjaxResponseBody.build(400,"该展品已经被删除,请查看其他展品");
        }
    }
}

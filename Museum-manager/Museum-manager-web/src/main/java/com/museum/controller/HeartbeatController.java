package com.museum.controller;

import com.museum.common.pojo.AjaxResponseBody;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/heartbeat")
public class HeartbeatController {
    @RequestMapping("/decide")
    @ResponseBody
    public AjaxResponseBody monitoringUserInfo(){
        return  AjaxResponseBody.ok();
    }
}

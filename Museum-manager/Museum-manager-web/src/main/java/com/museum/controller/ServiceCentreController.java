package com.museum.controller;

import com.museum.common.pojo.AjaxResponseBody;
import com.museum.pojo.ServiceCentre;
import com.museum.service.ServiceCentreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ServiceCentre")
public class ServiceCentreController {
   @Autowired
   private ServiceCentreService serviceCentreService;
    //查询服务中心信息
    @RequestMapping("/get")
    @ResponseBody
    public AjaxResponseBody getServiceCentreInfoById(){
        ServiceCentre serviceCentre=serviceCentreService.getServiceCentreInfoById();
        return AjaxResponseBody.ok(serviceCentre);
    }

    @RequestMapping("/update")
    @ResponseBody
    public AjaxResponseBody updateServiceCentreInfoById(@RequestBody ServiceCentre serviceCentre){
        serviceCentreService.updateServiceCentreInfoById(serviceCentre);
        return AjaxResponseBody.ok();
    }
}

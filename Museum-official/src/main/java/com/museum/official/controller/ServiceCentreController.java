package com.museum.official.controller;

import com.museum.common.pojo.AjaxResponseBody;
import com.museum.pojo.ServiceCentre;
import com.museum.service.ServiceCentreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/official")
public class ServiceCentreController {
    @Autowired
    private ServiceCentreService serviceCentreService;
    @RequestMapping("/centreList")
    @ResponseBody
    public AjaxResponseBody getCentreListById(){
        ServiceCentre result = serviceCentreService.getServiceCentreInfoById();
        return AjaxResponseBody.ok(result);
    }
}

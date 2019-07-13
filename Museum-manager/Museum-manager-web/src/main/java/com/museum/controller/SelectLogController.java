package com.museum.controller;

import com.museum.common.pojo.AjaxResponseBody;
import com.museum.common.pojo.PageHelperResult;
import com.museum.service.SelectLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Map;

@Controller
@RequestMapping("/selectLog")
public class SelectLogController {
    @Autowired
    private SelectLogService selectLogService;
    @RequestMapping("/list")
    @ResponseBody
    public AjaxResponseBody getSelectLogList(@RequestBody Map<String,Object> map){
        Integer page= (Integer) map.get("page");
        Integer rows= (Integer) map.get("rows");
//         Long before=null;
//         Long after=null;
//         if(map.get("before")!=null&&map.get("after")!=null){
           Long before = (Long) map.get("before");
           Long after= (Long) map.get("after");
//         }
        PageHelperResult selectLogList = selectLogService.getSelectLogList(page, rows,before,after);
        return AjaxResponseBody.ok(selectLogList);
    }
}

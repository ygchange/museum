package com.museum.security;

import com.museum.common.pojo.AjaxResponseBody;
import com.museum.common.pojo.MemberInfoResult;
import com.museum.common.utils.GetIpUtil;
import com.museum.common.utils.JsonUtils;
import com.museum.common.utils.JwtUtil;
import com.museum.custom.MemberInfoCustom;
import com.museum.service.MemberInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登陆成功返回的信息
 */
@Component
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private MemberInfoService memberInfoService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //认证成功后，获取用户的信息
        httpServletResponse.setContentType("text/json;charset=UTF-8");
        MemberInfoCustom custom = (MemberInfoCustom) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        if(custom.getStatus().equals("off")){
            AjaxResponseBody msg = AjaxResponseBody.build(400,"该账户已被注销,请联系管理员");
            String result = JsonUtils.objectToJson(msg);
            httpServletResponse.getWriter().write(result);
        }else {
            String ip = GetIpUtil.getIp2(httpServletRequest);
            custom.setLastIp(ip);
            String jwtToken = "Bearer " + JwtUtil.setClaim(custom.getUsername() + "," + custom.getPassword(), true, 3 * 24 * 60 * 60 * 1000);
            custom.setToken(jwtToken);
            MemberInfoResult memberInfoResult = memberInfoService.resultCustomUser(custom);
            memberInfoResult.setToken(jwtToken);
            AjaxResponseBody ok = AjaxResponseBody.ok(memberInfoResult);
            String result = JsonUtils
                    .objectToJson(ok);
            httpServletResponse.getWriter().write(result);

        }
    }


}

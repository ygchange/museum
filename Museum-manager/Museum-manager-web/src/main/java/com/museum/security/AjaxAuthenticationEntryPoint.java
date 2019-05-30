package com.museum.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.museum.common.pojo.AjaxResponseBody;
import com.museum.common.utils.JsonUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class AjaxAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        AjaxResponseBody build = AjaxResponseBody.build(000, "权限不足,请重新登陆");
        String result = JsonUtils.objectToJson(build);
        httpServletResponse.setContentType("text/json;charset=UTF-8");
        httpServletResponse.getWriter().write(result);
    }
}

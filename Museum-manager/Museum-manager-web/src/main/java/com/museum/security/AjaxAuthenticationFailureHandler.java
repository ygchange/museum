package com.museum.security;
import com.museum.common.pojo.AjaxResponseBody;
import com.museum.common.utils.JsonUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * 登陆失败返回的信息
 *
 *
 */
@Component
public class AjaxAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        AjaxResponseBody build = AjaxResponseBody.build(400, "账号或者密码错误,请重新登陆");
        String result = JsonUtils.objectToJson(build);
        httpServletResponse.setContentType("text/json;charset=UTF-8");
        httpServletResponse.getWriter().write(result);
    }
}

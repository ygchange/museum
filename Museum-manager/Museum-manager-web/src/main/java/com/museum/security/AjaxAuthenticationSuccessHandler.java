package com.museum.security;
import com.museum.common.pojo.AjaxResponseBody;
import com.museum.common.pojo.MemberInfoResult;
import com.museum.common.utils.JsonUtils;
import com.museum.common.utils.JwtUtil;
import com.museum.pojo.MemberInfoCustom;
import com.museum.service.MemberInfoService;
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
 *
 *
 *
 */
@Component
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private MemberInfoService memberInfoService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //认证成功后，获取用户的信息
        MemberInfoCustom custom = (MemberInfoCustom) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String jwtToken ="Bearer "+JwtUtil.setClaim(custom.getUsername(),true,60*60*1000);
        MemberInfoResult memberInfoResult = memberInfoService.resultCustomUser(custom);
        memberInfoResult.setToken(jwtToken);
        AjaxResponseBody ok = AjaxResponseBody.ok(memberInfoResult);
        String result = JsonUtils.objectToJson(ok);
        httpServletResponse.setContentType("text/json;charset=UTF-8");
        httpServletResponse.getWriter().write(result);


    }
}

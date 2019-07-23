package com.museum.security;

import com.museum.common.pojo.AjaxResponseBody;
import com.museum.common.utils.JsonUtils;
import com.museum.common.utils.JwtUtil;
import com.museum.custom.MemberInfoCustom;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * 自定义过滤器
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {


    @Autowired
    private AjaxUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        response.setContentType("text/json;charset=UTF-8");
        String token = request.getHeader("Authorization");
        // 可能是登录或者注册的请求，不带token信息，又或者是不需要登录，不需要token即可访问的资源。
        //静态资源和login不过滤
        String uri = request.getRequestURI();
        if (uri.substring(uri.lastIndexOf("/")).equals("/login")
                || uri.startsWith("/museum/dist")
                || uri.substring(uri.lastIndexOf("/")).equals("/")) {
            chain.doFilter(request, response);
            return;
        }
        //没有token，提示重新登陆
        if (token == null) {
            getResponse("无Token,请重新登陆", response);
            return;
        }
        //token不合法
        if (!token.startsWith("Bearer ")) {
            getResponse("Token不合法", response);
            return;

        }
        Claims claims = JwtUtil.getClaim(token.substring("Bearer ".length()));
        if (claims == null) {
            getResponse("当前用户已过期,请重新登录", response);
            return;
        }
        String string = claims.getSubject();
        if (string == null) {
            getResponse("当前用户已过期,请重新登录", response);
            return;
        }
        //token过期重新登陆
        Date expiredTime = claims.getExpiration();
        if ((new Date().getTime() > expiredTime.getTime())) {
            getResponse("当前用户已过期,请重新登录", response);
            return;
        }
        String userName = string.split(",")[0];
        String password = string.split(",")[1];
        MemberInfoCustom userDetails = (MemberInfoCustom) userDetailsService.loadUserByUsername(userName);
        if (userDetails == null) {
            getResponse("当前用户被删除,请联系管理员", response);
            return;
        }
        if(userDetails.getStatus().equals("off")){
            getResponse("该账户已被注销,请联系管理员",response);
            return;
        }
        if (!password.equals(userDetails.getPassword())) {
            getResponse("密码被修改,请联系管理员", response);
            return;
        }
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            //登陆认证通过
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);
            return;

        }
        chain.doFilter(request, response);
    }

    public void getResponse(String message, HttpServletResponse response) throws IOException, ServletException {
        AjaxResponseBody build = AjaxResponseBody.build(401, message);
        String s = JsonUtils.objectToJson(build);
        response.getWriter().write(s);
    }
}


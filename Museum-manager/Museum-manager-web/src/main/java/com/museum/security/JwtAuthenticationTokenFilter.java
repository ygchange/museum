package com.museum.security;

import com.museum.common.pojo.AjaxResponseBody;
import com.museum.common.pojo.PageHelperResult;
import com.museum.common.utils.JsonUtils;
import com.museum.common.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
                || uri.startsWith("/dist")
                || uri.substring(uri.lastIndexOf("/")).equals("/")) {
            chain.doFilter(request, response);
            return;
        }
        //没有token，提示重新登陆
        if (token == null) {
            AjaxResponseBody build = AjaxResponseBody.build(000, "当前用户已过期,请重新登录");
            String s = JsonUtils.objectToJson(build);
            response.getWriter().write(s);
            return;
        }
        //token不合法
        if (!token.startsWith("Bearer ")) {
            AjaxResponseBody build = AjaxResponseBody.build(000, "Token不合法");
            String s = JsonUtils.objectToJson(build);
            response.getWriter().write(s);
            return;

        }
        Claims claims = JwtUtil.getClaim(token.substring("Bearer ".length()));
        if (claims == null) {
            AjaxResponseBody build = AjaxResponseBody.build(000, "当前用户已过期,请重新登录");
            String s = JsonUtils.objectToJson(build);
            response.getWriter().write(s);
            return;
        }
        String userName = claims.getSubject();
        if (userName == null) {
            AjaxResponseBody build = AjaxResponseBody.build(000, "当前用户已过期,请重新登录");
            String s = JsonUtils.objectToJson(build);
            response.getWriter().write(s);
            return;
        }
        //token过期重新登陆
        Date expiredTime = claims.getExpiration();
        if ((new Date().getTime() > expiredTime.getTime())) {
            AjaxResponseBody build = AjaxResponseBody.build(000, "当前用户已过期,请重新登录");
            String s = JsonUtils.objectToJson(build);
            response.getWriter().write(s);
            return;

        }
        if (userName != null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

                if(userDetails ==null){
                    List list=new ArrayList();
                    PageHelperResult result=new PageHelperResult();
                    result.setRows(list);
                    result.setTotal(0);
                    AjaxResponseBody build = AjaxResponseBody.build(000, "用户被删除,请联系管理员",result);
                    String s = JsonUtils.objectToJson(build);
                    response.getWriter().write(s);
                    return;
                }

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                //登陆认证通过
                SecurityContextHolder.getContext().setAuthentication(authentication);
                chain.doFilter(request, response);
                return;
            }




    }
}


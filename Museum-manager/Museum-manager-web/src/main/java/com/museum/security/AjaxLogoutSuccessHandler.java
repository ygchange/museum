package com.museum.security;
import com.museum.common.pojo.AjaxResponseBody;
import com.museum.common.utils.JsonUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class AjaxLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("text/json;charset=UTF-8");
        AjaxResponseBody build = AjaxResponseBody.build(200, "退出成功");
        String result = JsonUtils.objectToJson(build);
        response.getWriter().write(result);

    }
}

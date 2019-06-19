package com.museum.wechat.patentController;

import com.museum.wechat.service.CoreService;
import com.museum.wechat.utils.CheckoutUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Controller
@RequestMapping("/wechat")
public class CoreServiceController {
    private Logger log = Logger.getLogger(CoreServiceController.class);

    /**
     * 校验信息是否是从微信服务器发过来的。
     *
     *
     * @param out
     */
    @RequestMapping(method = { RequestMethod.GET })
    public void doGet(HttpServletRequest request, HttpServletResponse response, PrintWriter out) {
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (signature != null && CheckoutUtil.checkSignature(signature, timestamp, nonce)) {
            out.print(echostr);
        } else {
            System.out.println("不是微信服务器发来的请求!");
        }
        out.flush();
        out.close();
    }
    /**
     * 微信消息的处理
     * @param request
     */
    @RequestMapping(method = { RequestMethod.POST })
    public void doPose(HttpServletRequest request, HttpServletResponse response)throws Exception {
        /* 消息的接收、处理、响应 */
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        // 调用核心业务类接收消息、处理消息
        String respMessage = CoreService.processRequest(request,response);
        log.info(respMessage);
        // 响应消息
        PrintWriter out = response.getWriter();
        out.print(respMessage);
        out.close();
    }

}

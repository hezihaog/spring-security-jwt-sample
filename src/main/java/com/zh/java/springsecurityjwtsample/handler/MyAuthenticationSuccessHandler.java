package com.zh.java.springsecurityjwtsample.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author hezihao
 * @version 1.0
 * <p>
 * @date 2020/9/4 8:54 下午
 * <p>
 * 自定义成功的Handler
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //设置返回数据的内容格式
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        //输出json
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write("{\"code\":20001,\"msg\":\"登录成功\"}");
        writer.flush();
    }
}
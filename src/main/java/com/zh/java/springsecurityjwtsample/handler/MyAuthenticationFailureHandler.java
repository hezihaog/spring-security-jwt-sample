package com.zh.java.springsecurityjwtsample.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
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
 * @date 2020/9/4 9:00 下午
 * <p>
 * 自定义失败处理器
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        //设置返回数据的内容格式
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        //输出json
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write("{\"code\":40001,\"msg\":\"登录失败，请确认用户名和密码是否正确\"}");
        writer.flush();
    }
}
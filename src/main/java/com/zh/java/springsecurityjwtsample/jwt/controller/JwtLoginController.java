package com.zh.java.springsecurityjwtsample.jwt.controller;

import com.zh.java.springsecurityjwtsample.jwt.model.RestResult;
import com.zh.java.springsecurityjwtsample.jwt.service.JwtAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author hezihao
 * @version 1.0
 * <p>
 * @date 2020/9/5 11:39 上午
 * <p>
 * 基于Jwt实现的登录控制器，如果使用JWT，使用这个控制器，测试时记得请求路径是jwtLogin，而不是login！
 */
@RestController()
public class JwtLoginController {
    @Autowired
    private JwtAuthService jwtAuthService;

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     */
    @PostMapping("/jwtLogin")
    public RestResult login(String username, String password) {
        RestResult result = new RestResult();
        String token = jwtAuthService.login(username, password);
        result.put("token", token);
        return result;
    }
}
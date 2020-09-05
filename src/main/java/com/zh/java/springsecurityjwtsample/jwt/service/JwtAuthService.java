package com.zh.java.springsecurityjwtsample.jwt.service;


/**
 * @author hezihao
 * @version 1.0
 * <p>
 * @date 2020/9/5 11:41 上午
 * <p>
 */
public interface JwtAuthService {
    /**
     * 登录，返回Token字符串
     */
    String login(String username, String password);
}
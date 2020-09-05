package com.zh.java.springsecurityjwtsample.jwt.service.impl;

import com.zh.java.springsecurityjwtsample.entity.Users;
import com.zh.java.springsecurityjwtsample.jwt.service.JwtAuthService;
import com.zh.java.springsecurityjwtsample.jwt.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * @author hezihao
 * @version 1.0
 * <p>
 * @date 2020/9/5 11:42 上午
 * <p>
 */
@Service
public class JwtAuthServiceImpl implements JwtAuthService {
    /**
     * 从SecurityConfig类中获取AuthenticationManager实例
     */
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public String login(String username, String password) {
        Authentication authenticate;
        try {
            //验证，会调用我们的UserDetailsService实现类的loadUserByUsername
            authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("用户名或密码错误");
        }
        //验证成功，过去User对象
        Users loginUser = (Users) authenticate.getPrincipal();
        //生成Token
        return jwtTokenUtil.generateToken(loginUser);
    }
}
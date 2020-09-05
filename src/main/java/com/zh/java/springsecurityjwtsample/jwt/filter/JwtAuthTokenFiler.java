package com.zh.java.springsecurityjwtsample.jwt.filter;

import com.zh.java.springsecurityjwtsample.jwt.util.JwtTokenUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author hezihao
 * @version 1.0
 * <p>
 * @date 2020/9/5 12:56 下午
 * <p>
 */
@Component
public class JwtAuthTokenFiler extends OncePerRequestFilter {
    @Resource
    private JwtTokenUtil jwtTokenUtil;
    @Resource
    UserDetailsService myUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        //获取传过来的Token，传了，则验证Token是否正确和过期
        String jwtToken = request.getHeader(jwtTokenUtil.getHeader());
        if (!StringUtils.isEmpty(jwtToken)) {
            //获取Token中的用户信息
            String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            //如果可以正确的从JWT中提取用户信息，并且该用户未被授权
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
                //验证Token
                if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                    //验证通过，给使用该JWT令牌的用户进行授权
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    //交给SpringSecurity管理，在之后的过滤器中不会再被拦截进行二次授权了
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
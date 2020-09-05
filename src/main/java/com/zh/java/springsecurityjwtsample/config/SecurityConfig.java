package com.zh.java.springsecurityjwtsample.config;

import com.zh.java.springsecurityjwtsample.handler.MyAuthenticationFailureHandler;
import com.zh.java.springsecurityjwtsample.handler.MyAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

/**
 * @author hezihao
 * @version 1.0
 * <p>
 * @date 2020/9/4 8:08 下午
 * <p>
 * Spring Security 配置类
 */
//开启Spring Security
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private MyAuthenticationSuccessHandler successHandler;
    @Resource
    private MyAuthenticationFailureHandler failureHandler;

    @Resource
    private UserDetailsService myUserDetailsService;

    /**
     * 配置授权页面和请求，以及哪些页面需要登录才可以进入
     */
    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security
                //开启FormLogin模式
                .formLogin()
                //1）配置授权，用户未登录时，访问任何资源都跳转到该路径，即登录页面
                .loginPage("/login")
                //登录表单from中action的地址，也就是处理认证请求的路径
                .loginProcessingUrl("/login")
                //指定账号的字段，默认是username，你可以改
                .usernameParameter("username")
                //指定密码的字段，默认是password，你可以改
                .passwordParameter("password")

                //前后端不分离可用，登录成功后跳转的接口
                //.defaultSuccessUrl("/index")
                //前后端不分离可用，登录失败时，跳转的也页面
                //.failureUrl("/login.html")

                //前后端分离可用，成功
                .successHandler(successHandler)
                //前后端分离可用，失败
                .failureHandler(failureHandler)

                //使用and()连接
                .and()
                //2）配置权限
                .authorizeRequests()
                //登录页面配置，所有用户都可以访问
                .antMatchers("/login").permitAll()
                //订单页面配置，user角色和admin角色都可以访问
                .antMatchers("/order").hasAnyAuthority("ROLE_user", "ROLE_admin")
                //用户管理、角色管理、菜单管理，只有admin角色可以访问
                .antMatchers("/system/user", "/system/role", "/system/menu").hasAnyRole("admin")
                //authenticated()，上面的所有请求，必须已经登录才可以访问
                .anyRequest().authenticated()
                .and()
                //禁用跨站csrf攻击防御，否则无法登录成功
                .csrf().disable();
        //指定登出操作的Url
        security.logout().logoutUrl("/logout");
    }

    /**
     * 从数据库中进行存储数据
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                //传入自己自定义的UserDetailsService
                .userDetailsService(myUserDetailsService)
                //指定加密方式
                .passwordEncoder(bCryptPasswordEncoder());
    }

//    /**
//     * 配置内存进行存储数据
//     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                //配置一个叫user名称的用户
//                .withUser("user")
//                //配置user用户的密码
//                .password(bCryptPasswordEncoder().encode("123"))
//                //配置user用户的角色
//                .roles("user")
//                .and()
//                //配置一个叫admin名称的用户
//                .withUser("admin")
//                //配置admin用户的密码
//                .password(bCryptPasswordEncoder().encode("123"))
//                //配置admin用户的角色
//                .roles("admin")
//                .and()
//                //配置BCrypt加密
//                .passwordEncoder(bCryptPasswordEncoder());
//    }

    /**
     * 强散列哈希加密实现
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
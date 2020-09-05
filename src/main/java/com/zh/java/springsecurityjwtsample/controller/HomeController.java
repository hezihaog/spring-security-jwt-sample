package com.zh.java.springsecurityjwtsample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author hezihao
 * @version 1.0
 * <p>
 * @date 2020/9/4 6:06 下午
 * <p>
 */
@Controller("/")
public class HomeController {
    /**
     * 登录
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 登录成功之后的首页
     */
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    /**
     * 用户管理
     */
    @GetMapping("/system/user")
    public String userList() {
        return "user";
    }

    /**
     * 角色管理
     */
    @GetMapping("/system/role")
    public String roleList() {
        return "role";
    }

    /**
     * 菜单管理
     */
    @GetMapping("/system/menu")
    public String menuList() {
        return "menu";
    }

    /**
     * 订单管理
     */
    @GetMapping("/order")
    public String orderList() {
        return "order";
    }
}
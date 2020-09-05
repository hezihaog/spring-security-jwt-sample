package com.zh.java.springsecurityjwtsample.service;

import com.zh.java.springsecurityjwtsample.entity.Users;

/**
 * @author hezihao
 * @version 1.0
 * <p>
 * @date 2020/9/4 10:06 下午
 * <p>
 */
public interface UserService {
    /**
     * 根据用户名查找用户信息
     *
     * @param username 用户名
     */
    Users selectUserByUserName(String username);
}
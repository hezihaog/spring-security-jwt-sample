package com.zh.java.springsecurityjwtsample.service.impl;

import com.zh.java.springsecurityjwtsample.dao.UserDao;
import com.zh.java.springsecurityjwtsample.entity.Users;
import com.zh.java.springsecurityjwtsample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hezihao
 * @version 1.0
 * <p>
 * @date 2020/9/4 10:07 下午
 * <p>
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public Users selectUserByUserName(String username) {
        Users user = new Users();
        user.setUserName(username);
        List<Users> list = userDao.findAll(Example.of(user));
        return list.isEmpty() ? null : list.get(0);
    }
}
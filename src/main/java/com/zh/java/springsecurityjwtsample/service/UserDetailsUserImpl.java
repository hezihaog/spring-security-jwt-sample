package com.zh.java.springsecurityjwtsample.service;

import com.zh.java.springsecurityjwtsample.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hezihao
 * @version 1.0
 * <p>
 * @date 2020/9/4 10:19 下午
 * <p>
 * 使用Spring Security 要实现UserDetailsService接口
 */
@Service
public class UserDetailsUserImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userService.selectUserByUserName(username);
        if (users == null) {
            throw new UsernameNotFoundException("登录用户：" + username + "不存在");
        }
        //将roles信息按逗号分隔，生成权限对象列表
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(users.getRoles());
        //保存到实体类中
        users.setAuthorities(grantedAuthorities);
        return users;
    }
}
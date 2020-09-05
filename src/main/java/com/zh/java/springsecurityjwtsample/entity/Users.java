package com.zh.java.springsecurityjwtsample.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * @author hezihao
 * @version 1.0
 * <p>
 * @date 2020/9/4 9:25 下午
 * <p>
 * 用户表实体
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
//实现UserDetails接口
public class Users implements UserDetails {
    /**
     * 主键，用户Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long userId;

    /**
     * 用户账号
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 账号状态（0正常，1停用）
     */
    @Column(name = "status")
    private String status;

    /**
     * 用户角色（多个角色用逗号间隔）
     */
    @Column(name = "roles")
    private String roles;

    /**
     * 授权列表，它不是数据库字段，要使用@Transient注解
     */
    @Transient
    private List<GrantedAuthority> authorities;

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    /**
     * 账号是否未过期，过期无法验证
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 指定用户是否解锁，锁定的用户无法进行身份验证
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 指示是否已过期的用户的凭证（密码），过期的凭证防止认证
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否可用，禁用的用户不能身份验证
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
package com.zh.java.springsecurityjwtsample.dao;

import com.zh.java.springsecurityjwtsample.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author hezihao
 * @version 1.0
 * <p>
 * @date 2020/9/4 10:05 下午
 * <p>
 * 用户表Dao
 */
@Repository
public interface UserDao extends JpaRepository<Users, Long> {
}
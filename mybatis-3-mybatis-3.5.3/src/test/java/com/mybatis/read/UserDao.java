package com.mybatis.read;

/**
 * @Description
 * @Author sunxy
 * @Create 2019-12-25 16:17
 **/
public interface UserDao {
    User selectUser(Long id);
}

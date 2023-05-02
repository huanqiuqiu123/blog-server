package com.huanqiu.blog.service;

import com.huanqiu.blog.domain.pojo.SelectUserByAccountPo;

/**
 * @author huanqiu
 * @version 1.0
 * @description
 * @date 2023/02/20 下午 1:08
 */
public interface UserService {
    Integer selectUserByEmail(String email);

    Integer addUser(Long userId,String password,String email);

    SelectUserByAccountPo selectUserByAccount(String account);
}

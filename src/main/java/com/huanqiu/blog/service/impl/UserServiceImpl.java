package com.huanqiu.blog.service.impl;

import com.huanqiu.blog.domain.pojo.InsertUserPo;
import com.huanqiu.blog.domain.pojo.SelectUserByAccountPo;
import com.huanqiu.blog.mapper.UserMapper;
import com.huanqiu.blog.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author huanqiu
 * @version 1.0
 * @description
 * @date 2023/02/20 下午 1:08
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;


    @Override
    public Integer selectUserByEmail(String email) {
        return userMapper.selectUserByEmail(email);
    }

    @Override
    public Integer addUser(Long   userId, String password, String email) {
        //生成默认用户名和昵称
        String username = "sy_" + String.valueOf(userId).substring(String.valueOf(userId).length() - 8);
        return userMapper.insertUser(new InsertUserPo(userId, username, password, email, username));
    }

    @Override
    public SelectUserByAccountPo selectUserByAccount(String account) {
        return userMapper.selectUserByAccount(account);
    }

}

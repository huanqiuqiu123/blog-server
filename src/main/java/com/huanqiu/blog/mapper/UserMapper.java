package com.huanqiu.blog.mapper;

import com.huanqiu.blog.domain.pojo.InsertUserPo;
import com.huanqiu.blog.domain.pojo.SelectUserByAccountPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author huanqiu
 * @version 1.0
 * @description
 * @date 2023/02/19 下午 4:24
 */
@Mapper
public interface UserMapper {
    Integer selectUserByEmail(String email);

    Integer insertUser(InsertUserPo insertUserPo);

    SelectUserByAccountPo selectUserByAccount(String account);

    String selectUserNameById(String id);
}

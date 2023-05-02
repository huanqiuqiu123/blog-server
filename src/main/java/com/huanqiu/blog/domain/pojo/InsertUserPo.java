package com.huanqiu.blog.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author huanqiu
 * @version 1.0
 * @description
 * @date 2023/04/11 下午 12:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsertUserPo {

    private Long userId;
    private String username;
    private String password;
    private String email;
    private String nickName;
}

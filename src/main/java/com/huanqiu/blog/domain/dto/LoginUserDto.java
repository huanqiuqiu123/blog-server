package com.huanqiu.blog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author huanqiu
 * @version 1.0
 * @description
 * @date 2023/04/11 下午 4:02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserDto {

    private String account;
    private String password;
}

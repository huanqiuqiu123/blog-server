package com.huanqiu.blog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author huanqiu
 * @version 1.0
 * @description
 * @date 2023/04/11 下午 3:55
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDto {

    private String email;

    private String password;

    private String code;
}

package com.huanqiu.blog.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author huanqiu
 * @version 1.0
 * @description
 * @date 2023/04/11 下午 4:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectUserByAccountPo {
    private Long id;

    private String password;

}

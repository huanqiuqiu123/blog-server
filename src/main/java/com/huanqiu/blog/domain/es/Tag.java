package com.huanqiu.blog.domain.es;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author huanqiu
 * @version 1.0
 * @description
 * @date 2023/05/08 下午 7:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag {

    private String id;
    private String name;
}

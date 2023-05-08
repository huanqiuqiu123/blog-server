package com.huanqiu.blog.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author huanqiu
 * @version 1.0
 * @description
 * @date 2023/05/07 下午 3:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsertContentPo {
    private Long id;
    private String content;
}

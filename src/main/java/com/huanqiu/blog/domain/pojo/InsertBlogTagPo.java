package com.huanqiu.blog.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author huanqiu
 * @version 1.0
 * @description
 * @date 2023/05/08 下午 2:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsertBlogTagPo {
    private Long id;
    private Long blogId;
    private Long tagId;
}

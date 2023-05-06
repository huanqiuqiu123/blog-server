package com.huanqiu.blog.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author huanqiu
 * @version 1.0
 * @description
 * @date 2023/05/06 上午 11:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsertAuditArticlePo {
    private Long id;

    private String title;

    private Long userId;

    private String status;

}

package com.huanqiu.blog.recommend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author huanqiu
 * @version 1.0
 * @description
 * @date 2023/04/29 下午 3:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelateEntity {

    private String userId;

    private String itemId;

    private Double score;

}

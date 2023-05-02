package com.huanqiu.blog.recommend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author huanqiu
 * @version 1.0
 * @description
 * @date 2023/04/30 下午 2:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogEntity {

    private String blogId;

    private List<String> tags;

}

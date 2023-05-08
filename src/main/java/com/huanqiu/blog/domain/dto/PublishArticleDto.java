package com.huanqiu.blog.domain.dto;

import com.huanqiu.blog.domain.es.Tag;
import com.huanqiu.blog.domain.pojo.TagPo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author huanqiu
 * @version 1.0
 * @description
 * @date 2023/05/08 下午 2:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublishArticleDto {

    private Long articleId;

    private String title;

    private String content;

    private List<Tag> tags;
}

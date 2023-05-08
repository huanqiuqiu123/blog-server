package com.huanqiu.blog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveArticleDto {

    private String articleId;

    private String title;

    private String content;

}
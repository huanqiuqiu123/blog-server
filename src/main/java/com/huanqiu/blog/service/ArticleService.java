package com.huanqiu.blog.service;

import com.huanqiu.blog.domain.dto.PublishArticleDto;
import com.huanqiu.blog.domain.dto.SaveArticleDto;
import com.huanqiu.blog.domain.pojo.TagPo;

import java.util.List;

/**
 * @author huanqiu
 * @version 1.0
 * @description
 * @date 2023/05/05 下午 5:39
 */
public interface ArticleService {

    List<TagPo> getAllTags();

    void addTag(String name);

    void addTagList(List<String> nameList);

    void saveBlog(Long userId, Long articleId, String title, String content);

    void publishArticle(PublishArticleDto publishArticleDto,Long userId);

    void saveArticle(SaveArticleDto saveArticleDto,String userId);
}

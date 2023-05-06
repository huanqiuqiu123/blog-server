package com.huanqiu.blog.service;

import com.huanqiu.blog.domain.pojo.InsertAuditArticlePo;
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

    void addAuditArticle(InsertAuditArticlePo insertAuditArticlePo);

}

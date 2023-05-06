package com.huanqiu.blog.mapper;

import com.huanqiu.blog.domain.pojo.InsertAuditArticlePo;
import com.huanqiu.blog.domain.pojo.TagPo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author huanqiu
 * @version 1.0
 * @description
 * @date 2023/05/05 下午 5:28
 */
@Mapper
public interface ArticleMapper {

    List<TagPo> selectAllTags();

    void insertTag(TagPo tagPo);

    void insertAuditArticle(InsertAuditArticlePo insertAuditArticlePo);

    List<InsertAuditArticlePo> getAuditArticle();



}

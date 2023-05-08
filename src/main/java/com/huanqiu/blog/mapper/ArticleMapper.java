package com.huanqiu.blog.mapper;

import com.huanqiu.blog.domain.es.Article;
import com.huanqiu.blog.domain.pojo.InsertBlogTagPo;
import com.huanqiu.blog.domain.pojo.InsertContentPo;
import com.huanqiu.blog.domain.pojo.InsertBlogPo;
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

    void insertTemporaryBlog(InsertBlogPo insertBlogPo);

    void insertContent(InsertContentPo insertContentPo);

    void insertBlog(InsertBlogPo insertBlogPo);

    void insertBlogTag(InsertBlogTagPo insertBlogTagPo);

    void deleteTagByBlogId(String blogId);

    Article selectBlogInfoById(String blogId);
}

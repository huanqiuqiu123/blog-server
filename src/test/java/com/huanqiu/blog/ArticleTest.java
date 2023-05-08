package com.huanqiu.blog;

import com.huanqiu.blog.domain.es.Article;
import com.huanqiu.blog.domain.pojo.TagPo;
import com.huanqiu.blog.mapper.ArticleMapper;
import com.huanqiu.blog.service.ArticleService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author huanqiu
 * @version 1.0
 * @description
 * @date 2023/05/05 下午 6:29
 */
@SpringBootTest
public class ArticleTest {
    @Resource
    private ArticleService articleService;

    @Resource
    private ArticleMapper articleMapper;

    @Test
    void testAddTag() {
        articleService.addTag("java");
    }

    @Test
    void testAddTags() {
        articleService.addTagList(List.of("ui","搜索引擎"));
    }

    @Test
    void testGetAllTags() {
        List<TagPo> allTags = articleService.getAllTags();
        allTags.forEach(System.out::println);

    }

    @Test
    void testAddTemporaryBlog() {
        articleService.saveBlog(851154538214719488L,null,"测试","测试内容");
    }

    @Test
    void testSelectBlogInfo(){
        Article article = articleMapper.selectBlogInfoById("852989424080781312");
        System.out.println(article);
    }

}

package com.huanqiu.blog;

import com.huanqiu.blog.domain.pojo.InsertAuditArticlePo;
import com.huanqiu.blog.domain.pojo.TagPo;
import com.huanqiu.blog.mapper.ArticleMapper;
import com.huanqiu.blog.service.ArticleService;
import com.huanqiu.blog.util.SnowFlakeUtil;
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
    void testInsertAuditArticle() {
        InsertAuditArticlePo insertAuditArticlePo=new InsertAuditArticlePo(SnowFlakeUtil.getNextId(),"java开发",851154538214719488L,"审核");
        articleService.addAuditArticle(insertAuditArticlePo);
    }

    @Resource
    private ArticleMapper articleMapper;
    @Test
    void testSelectAuditArticle(){
        articleMapper.getAuditArticle().forEach(System.out::println);
    }

}

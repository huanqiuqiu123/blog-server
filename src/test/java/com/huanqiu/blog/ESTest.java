package com.huanqiu.blog;

import com.huanqiu.blog.domain.es.Article;
import com.huanqiu.blog.domain.es.Tag;
import com.huanqiu.blog.mapper.es.ArticleESMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.SearchHits;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author huanqiu
 * @version 1.0
 * @description
 * @date 2023/05/08 上午 11:23
 */
@SpringBootTest
public class ESTest {

    @Resource
    private ArticleESMapper articleESMapper;


    @Test
    public void testSelectAll(){
        SearchHits<Article> hits = articleESMapper.find("搜索");
        hits.forEach(System.out::println);
    }


}

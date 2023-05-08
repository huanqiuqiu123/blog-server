package com.huanqiu.blog.mapper.es;

import com.huanqiu.blog.domain.es.Article;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author huanqiu
 * @version 1.0
 * @description
 * @date 2023/05/08 上午 10:54
 */
@Mapper
public interface ArticleESMapper extends ElasticsearchRepository<Article, String> {

    @Query("""
            {"bool": {
                        "should": [
                            {
                                "match": {
                                    "title": "?0"
                                }
                            },
                            {
                                "match": {
                                    "content": "?0"
                                }
                            },
                            {
                                "match":{
                                    "tags":"?0"
                                }
                            }
                        ]
                    }
                    }
            """)
    SearchHits<Article> find(String keyword);

}

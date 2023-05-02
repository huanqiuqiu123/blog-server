package com.huanqiu.blog.domain.es;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

/**
 * @author huanqiu
 * @version 1.0
 * @description
 * @date 2023/04/25 下午 6:15
 */
@Data
@Document(indexName = "blog")
public class Blog {
    /**
     * 博客id
     */
    @Id
    @Field(name = "id", type = FieldType.Text)
    private String id;

    /**
     * 博客标题
     */
    @Field(name = "title", type = FieldType.Text)
    private String title;

    /**
     * 博客标签
     */
    @Field(name = "tags", type = FieldType.Nested)
    private List<String> tags;

    /**
     * 博客作者
     */
    @Field(name = "author", type = FieldType.Text)
    private String author;


}

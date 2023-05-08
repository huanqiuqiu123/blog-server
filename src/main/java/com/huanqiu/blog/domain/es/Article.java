package com.huanqiu.blog.domain.es;

import com.huanqiu.blog.domain.pojo.TagPo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author huanqiu
 * @version 1.0
 * @description
 * @date 2023/05/08 上午 10:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "article")
public class Article implements Serializable {

    @Id
    @Field(name = "id", type = FieldType.Long)
    private Long id;

    @Field(name = "title", type = FieldType.Text)
    private String title;

    @Field(name = "content", type = FieldType.Text)
    private String content;

    @Field(name = "tags",type = FieldType.Nested)
    private List<Tag> tags;

    @Field(name = "author", type = FieldType.Text)
    private String author;

    @Field(name = "createTime", type = FieldType.Date)
    private Date createTime;

    @Field(name = "updateTime", type = FieldType.Date)
    private Date updateTime;
}

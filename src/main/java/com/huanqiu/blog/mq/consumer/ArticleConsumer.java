package com.huanqiu.blog.mq.consumer;

import com.huanqiu.blog.common.Constants;
import com.huanqiu.blog.domain.es.Article;
import com.huanqiu.blog.mapper.es.ArticleESMapper;
import jakarta.annotation.Resource;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author huanqiu
 * @version 1.0
 * @description
 * @date 2023/05/07 下午 8:58
 */
@Component
@RocketMQMessageListener(consumerGroup = "ARTICLE_GROUP",topic = Constants.ConsumerTopic.ARTICLE_TOPIC,selectorExpression = "articleSave")
public class ArticleConsumer implements RocketMQListener<Article> {

    @Resource
    private ArticleESMapper articleESMapper;

    @Override
    public void onMessage(Article article) {
        //进行文章存储
        articleESMapper.save(article);
    }
}

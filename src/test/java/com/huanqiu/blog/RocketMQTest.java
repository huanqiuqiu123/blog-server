package com.huanqiu.blog;

import com.huanqiu.blog.common.Constants;
import com.huanqiu.blog.domain.es.Article;
import com.huanqiu.blog.mq.producer.ProducerService;
import jakarta.annotation.Resource;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

/**
 * @author huanqiu
 * @version 1.0
 * @description
 * @date 2023/05/07 下午 6:54
 */
@SpringBootTest
public class RocketMQTest {

    @Resource
    private ProducerService producerService;
}

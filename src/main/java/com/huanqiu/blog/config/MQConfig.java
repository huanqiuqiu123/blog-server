package com.huanqiu.blog.config;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.spring.autoconfigure.RocketMQAutoConfiguration;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author huanqiu
 * @version 1.0
 * @description
 * @date 2023/05/07 下午 10:04
 */
@Configuration
public class MQConfig extends RocketMQAutoConfiguration {
    @Value("${rocketmq.name-server}")
    private String nameServer;

    @Value("${rocketmq.producer.group}")
    private String producerGroup;

    @Bean
    public RocketMQTemplate rocketMQTemplate(){
        DefaultMQProducer defaultMQProducer=new DefaultMQProducer();
        defaultMQProducer.setNamesrvAddr(nameServer);
        defaultMQProducer.setProducerGroup(producerGroup);
        RocketMQTemplate rocketMQTemplate = new RocketMQTemplate();
        rocketMQTemplate.setProducer(defaultMQProducer);
        return rocketMQTemplate;
    }
}

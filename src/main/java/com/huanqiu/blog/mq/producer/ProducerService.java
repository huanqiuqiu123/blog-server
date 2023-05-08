package com.huanqiu.blog.mq.producer;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * @author huanqiu
 * @version 1.0
 * @description
 * @date 2023/05/07 下午 4:51
 */
@Slf4j
@Component
public class ProducerService {
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 发送普通消息
     * @param topic 主题
     * @param tag 标签
     * @param obj 消息体
     * @param <T> 消息体类型
     */
    public <T> void send(String topic, String tag, T obj) {
        rocketMQTemplate.send(topic + ":" + tag, MessageBuilder.withPayload(obj).build());
    }

    public <T> void sendAsync(String topic, String tag, T obj, SendCallback callback){
        rocketMQTemplate.asyncSend(topic+":"+tag,MessageBuilder.withPayload(obj).build(),callback);
    }


}

package com.huanqiu.blog.common;

/**
 * @author huanqiu
 * @version 1.0
 * @description
 * @date 2022/12/31 下午 3:34
 */
public class Constants {
    public static final class StatusCode {
        public static final Integer SUCCESS = 200;
        public static final Integer ERROR_COMMON = 600;
    }

    public static final class Gender {
        public static final String FEMALE = "女";
        public static final String MALE = "男";
        public static final String NONE = "无";
    }

    public static final class BlogStatus {
        public static final String DRAFT = "草稿";
        public static final String PUBLISH = "发布";
        public static final String AUDIT = "审核";
        public static final String RETURN = "退回";
    }

    public static final class ConsumerTopic{
        public static final String ARTICLE_TOPIC = "article_topic";
    }

}
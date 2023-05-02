# 2023/02/15

create database hq_blog;

use hq_blog;

create table `user`
(
    `id`          bigint primary key comment '用户表id',
    `username`    varchar(255) unique   not null comment '用户名',
    `password`    varchar(255)          not null comment '密码',
    `phone`       varchar(255) unique comment '手机号',
    `email`       varchar(255) unique   not null comment '邮箱',
    `nick_name`   varchar(255) unique   not null comment '昵称',
    `avatar`      varchar(255)          not null default '' comment '头像',
    `description` varchar(255) comment '个人介绍',
    `experience` bigint unsigned default 0 not null comment '经验',
    `score`       int unsigned                   default 0 comment '积分',
    `gender`      enum ('男','女','无') not null default '无' comment '性别',
    `role`        int                   not null default 0 comment '角色',
    `create_time` datetime                       default CURRENT_TIMESTAMP comment '创建时间',
    `update_time` datetime                       default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '更新时间'
) comment '用户表';

create table `blog`
(
    `id`          bigint primary key comment '博客id',
    `title`       varchar(255)         not null comment '博客标题',
    `name`        varchar(255) unique  not null comment '博客名',
    `status`      enum ('草稿','发布') not null default '草稿' comment '博客状态',
    `user_id`     bigint               not null comment '用户id',
    `create_time` datetime                      default CURRENT_TIMESTAMP comment '创建时间',
    `update_time` datetime                      default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '更新时间',
    foreign key (user_id) references user (id)
) comment '博客表';

create table `tag`
(
    `id`   bigint primary key comment '标签id',
    `name` varchar(255) not null unique comment '标签名'
) comment '标签表';

create table `blog_tag`
(
    `id`          bigint primary key comment '博客-标签id',
    `blog_id`     bigint not null comment '博客id',
    `tag_id`      bigint not null comment '标签id',
    `create_time` datetime default CURRENT_TIMESTAMP comment '创建时间',
    `update_time` datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '更新时间',
    foreign key (`blog_id`) references blog (`id`),
    foreign key (`tag_id`) references tag (`id`)
) comment '博客-标签表';

create table `follow`
(
    `id`          bigint primary key comment '粉丝表id',
    `user_id`     bigint not null comment '用户id',
    `follow_id`   bigint not null comment '粉丝id',
    `create_time` datetime default CURRENT_TIMESTAMP comment '创建时间',
    `update_time` datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '更新时间',
    foreign key (`user_id`) references user (`id`),
    foreign key (`follow_id`) references user (`id`)
) comment '粉丝表';

create table `black_list`
(
    `id`            bigint primary key comment '黑名单表id',
    `user_id`       bigint not null comment '用户id',
    `black_list_id` bigint not null comment '黑名单id',
    `create_time`   datetime default CURRENT_TIMESTAMP comment '创建时间',
    `update_time`   datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '更新时间',
    foreign key (`user_id`) references user (`id`),
    foreign key (`black_list_id`) references user (`id`)
) comment '黑名单表';

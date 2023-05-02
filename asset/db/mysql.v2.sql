# 2023/03/22

use hq_blog;

### CREATE


### MODIFY
alter table blog
    add column `status` enum ('草稿','发布','审核') not null default '草稿' comment '博客状态';
create table `blog`
(
    `id`          bigint primary key comment '博客id',
    `title`       varchar(255)        not null comment '博客标题',
    `name`        varchar(255) unique not null comment '博客名',
    `user_id`     bigint              not null comment '用户id',
    `create_time` datetime default CURRENT_TIMESTAMP comment '创建时间',
    `update_time` datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '更新时间',
    foreign key (user_id) references user (id)
) comment '已发布博客表';

alter table `blog`
    add column `browse_count` int unsigned default 0 comment '浏览量';

create table `blog_audit`
(
    `id`          bigint primary key comment '博客id',
    `title`       varchar(255)        not null comment '博客标题',
    `name`        varchar(255) unique not null comment '博客名',
    `user_id`     bigint              not null comment '用户id',
    `create_time` datetime default CURRENT_TIMESTAMP comment '创建时间',
    `update_time` datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '更新时间',
    foreign key (user_id) references user (id)
) comment '审核博客表';

create table `blog_return`
(
    `id`            bigint primary key comment '博客id',
    `title`         varchar(255)        not null comment '博客标题',
    `name`          varchar(255) unique not null comment '博客名',
    `reason`        longtext            not null comment '退回理由',
    `audit_user_id` bigint              not null comment '审核人',
    `user_id`       bigint              not null comment '用户id',
    `create_time`   datetime default CURRENT_TIMESTAMP comment '创建时间',
    `update_time`   datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '更新时间',
    foreign key (user_id) references user (id),
    foreign key (audit_user_id) references user (id)
) comment '审核失败博客表';

create table `tag_audit`
(
    `id`          bigint primary key comment '标签id',
    `name`        varchar(255) not null unique comment '标签名',
    `user_id`     bigint       not null comment '用户id',
    `create_time` datetime default CURRENT_TIMESTAMP comment '创建时间',
    `update_time` datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '更新时间',
    foreign key (user_id) references user (id)
) comment '审核博客表';

create table `tag_audit`
(
    `id`          bigint primary key comment '标签id',
    `name`        varchar(255) not null unique comment '标签名',
    `user_id`     bigint       not null comment '用户id',
    `create_time` datetime default CURRENT_TIMESTAMP comment '创建时间',
    `update_time` datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '更新时间',
    foreign key (user_id) references user (id)
) comment '审核标签表';

create table `tag_return`
(
    `id`            bigint primary key comment '标签id',
    `name`          varchar(255) not null unique comment '标签名',
    `reason`        longtext     not null comment '退回理由',
    `audit_user_id` bigint       not null comment '审核人',
    `user_id`       bigint       not null comment '用户id',
    `create_time`   datetime default CURRENT_TIMESTAMP comment '创建时间',
    `update_time`   datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '更新时间',
    foreign key (user_id) references user (id),
    foreign key (audit_user_id) references user (id)
) comment '审核标签表';

# 2023/03/22

use hq_blog;
drop table `blog`;
alter table `blog`
    add column `status` enum ('草稿','审核','退回','发布') not null default '草稿' comment '博客状态';

create table `article` (
    `id` bigint primary key comment '博客id',
    `content` mediumtext not null comment '博客内容',
    foreign key (`id`) references `blog`(`id`)
) comment '博客内容表';

create table `blog`
(
    `id`            bigint primary key comment '博客id',
    `title`         varchar(255)                       not null comment '博客标题',
    `user_id`       bigint                             not null comment '用户id',
    `browse_count`  int unsigned                                default 0 comment '浏览量',
    `collect_count` int unsigned                                default 0 comment '收藏量',
    `type_id`       bigint                             not null comment '博客类型id',
    `status`        enum ('草稿','审核','退回','发布') not null default '草稿' comment '博客状态',
    `create_time`   datetime                                    default CURRENT_TIMESTAMP comment '创建时间',
    `update_time`   datetime                                    default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '更新时间',
    foreign key (user_id) references user (id),
    foreign key (`type_id`) references `blog_type` (`id`)
) comment '已发布博客表';

drop table `blog_audit`;
drop table `blog_return`;

create table `blog_audit`
(
    `id`          bigint primary key comment '博客id',
    `title`       varchar(255) not null comment '博客标题',
    `content`     mediumtext   not null comment '博客内容',
    `user_id`     bigint       not null comment '用户id',
    `create_time` datetime default CURRENT_TIMESTAMP comment '创建时间',
    `update_time` datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '更新时间',
    foreign key (user_id) references user (id)
) comment '审核博客表';

create table `blog_return`
(
    `id`            bigint primary key comment '博客id',
    `title`         varchar(255) not null comment '博客标题',
    `content`       mediumtext   not null comment '博客内容',
    `reason`        longtext     not null comment '退回理由',
    `audit_user_id` bigint       not null comment '审核人',
    `user_id`       bigint       not null comment '用户id',
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

create table `blog_type`
(
    `id`          bigint primary key comment '博客类型id',
    `name`        varchar(255) not null unique comment '博客类型名',
    `create_time` datetime default CURRENT_TIMESTAMP comment '创建时间',
    `update_time` datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '更新时间'
) comment '博客类型表'


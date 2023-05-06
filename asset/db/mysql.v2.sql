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
    `status`        enum ('草稿','审核','退回','发布') not null default '草稿' comment '博客状态',
    `create_time`   datetime                                    default CURRENT_TIMESTAMP comment '创建时间',
    `update_time`   datetime                                    default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '更新时间',
    foreign key (user_id) references user (id)
) comment '已发布博客表';



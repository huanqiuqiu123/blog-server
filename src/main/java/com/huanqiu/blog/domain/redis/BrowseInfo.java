package com.huanqiu.blog.domain.redis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author huanqiu
 * @version 1.0
 * @description 用户浏览数据
 * @date 2023/04/29 下午 1:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrowseInfo {

    /**
     * 博客id
     */
    private String blogleId;

    /**
     * 评分 -2 -1 0 1 2
     */
    private Integer score;

    /**
     * 用户浏览次数
     */
    private Integer viewTime;

    /**
     * 用户最近浏览时间
     */
    private long timestamp;


}

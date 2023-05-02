package com.huanqiu.blog.service.redis;

import com.huanqiu.blog.domain.redis.BrowseInfo;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author huanqiu
 * @version 1.0
 * @description
 * @date 2023/04/29 下午 1:19
 */
@Service
public class RedisService {

    private final String BROWSE_INFO_PREFIX = "user_browse_info_";


    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 增加用户浏览次数
     *
     * @param userId 用户id
     * @param blogId 博客id
     */
    public void addViewTime(String userId, String blogId) {
        long timestamp = new Date().getTime();
        String key = BROWSE_INFO_PREFIX + userId;
        ListOperations<String, BrowseInfo> listOperations = redisTemplate.opsForList();
        List<BrowseInfo> browseInfoList = listOperations.range(key, 0, -1);
        if (browseInfoList == null || browseInfoList.size() == 0) {
            //用户还没有浏览过，将浏览信息存入redis
            BrowseInfo browseInfo = new BrowseInfo(blogId, 0, 1, timestamp);
            listOperations.leftPush(key, browseInfo);
        } else {
            //用户已经浏览过，判断是否浏览过该博客
            browseInfoList.stream().filter(browseInfo -> browseInfo.getBlogleId().equals(blogId)).findFirst().ifPresentOrElse(u -> {
                //将浏览次数以及浏览日期更新
                listOperations.remove(key, 1, u);
                u.setViewTime(u.getViewTime() + 1);
                u.setTimestamp(timestamp);
                listOperations.leftPush(key, u);
            }, () -> {
                //没有浏览过该博客，将浏览信息存入redis
                BrowseInfo browseInfo = new BrowseInfo(blogId, 0, 1, timestamp);
                listOperations.leftPush(key, browseInfo);
            });
        }
    }

    public void updateScore(String userId, String blogId, Integer score) {
        long timestamp = new Date().getTime();
        if (score < -2 || score > 2) {
            throw new RuntimeException("评分范围不正确");
        } else {
            String key = BROWSE_INFO_PREFIX + userId;
            ListOperations<String, BrowseInfo> listOperations = redisTemplate.opsForList();
            List<BrowseInfo> browseInfoList = listOperations.range(key, 0, -1);
            if (browseInfoList == null || browseInfoList.size() == 0) {
                //用户还没有浏览过，将浏览信息存入redis
                BrowseInfo browseInfo = new BrowseInfo(blogId, score, 1, timestamp);
                listOperations.leftPush(key, browseInfo);
            } else {
                //用户已经浏览过，判断是否浏览过该博客
                browseInfoList.stream().filter(browseInfo -> browseInfo.getBlogleId().equals(blogId)).findFirst().ifPresentOrElse(u -> {
                    //将评分更新
                    listOperations.remove(key, 1, u);
                    u.setScore(score);
                    listOperations.leftPush(key, u);
                }, () -> {
                    //没有浏览过该博客，将浏览信息存入redis
                    BrowseInfo browseInfo = new BrowseInfo(blogId, score, 1, timestamp);
                    listOperations.leftPush(key, browseInfo);
                });
            }
        }
    }


}

package com.huanqiu.blog.controller;

import com.huanqiu.blog.common.ResponseResult;
import com.huanqiu.blog.domain.pojo.TagPo;
import com.huanqiu.blog.service.ArticleService;
import com.huanqiu.blog.util.SnowFlakeUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author huanqiu
 * @version 1.0
 * @description
 * @date 2023/05/05 上午 10:37
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Resource
    private ArticleService articleService;

    @GetMapping("/get_article_id")
    public ResponseResult getArticleId() {
        long articleId = SnowFlakeUtil.getNextId();
        return new ResponseResult(200, "id获取成功", Map.of("id", articleId));
    }

    @GetMapping("/get_all_tags")
    public ResponseResult getAllTags() {
        return new ResponseResult(200, "标签获取成功", Map.of("tags", articleService.getAllTags()));
    }


}

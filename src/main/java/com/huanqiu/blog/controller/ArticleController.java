package com.huanqiu.blog.controller;

import com.auth0.jwt.interfaces.Claim;
import com.huanqiu.blog.common.ResponseResult;
import com.huanqiu.blog.domain.dto.PublishArticleDto;
import com.huanqiu.blog.domain.dto.SaveArticleDto;
import com.huanqiu.blog.domain.pojo.TagPo;
import com.huanqiu.blog.service.ArticleService;
import com.huanqiu.blog.util.JwtUtil;
import com.huanqiu.blog.util.SnowFlakeUtil;
import jakarta.annotation.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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
        List<TagPo> allTags = articleService.getAllTags();
        return new ResponseResult(200, "标签获取成功", Map.of("tags", allTags));
    }

    @PostMapping("/publish")
    public ResponseResult publish(@RequestBody PublishArticleDto publishArticleDto, @RequestHeader String token) {
        if (!StringUtils.hasText(token)) {
            return new ResponseResult(600, "请先登陆", null);
        }
        Map<String, Claim> verify = JwtUtil.verify(token);
        String userId = verify.get("userId").asString();
        if (!StringUtils.hasText(userId)) {
            return new ResponseResult(600, "请先登陆", null);
        }
        articleService.publishArticle(publishArticleDto, Long.valueOf(userId));
        return new ResponseResult(200, "发布成功", null);
    }

    @PostMapping("/save")
    public ResponseResult save(@RequestBody SaveArticleDto saveArticleDto, @RequestHeader String token) {
        if (!StringUtils.hasText(token)) {
            return new ResponseResult(600, "请先登陆", null);
        }
        Map<String, Claim> verify = JwtUtil.verify(token);
        String userId = verify.get("userId").asString();
        if (!StringUtils.hasText(userId)){
            return new ResponseResult(600, "请先登陆", null);
        }
        articleService.saveArticle(saveArticleDto, userId);
        return new ResponseResult(200, "保存成功", null);
    }


}

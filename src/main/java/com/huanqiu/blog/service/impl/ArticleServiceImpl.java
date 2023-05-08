package com.huanqiu.blog.service.impl;

import com.huanqiu.blog.common.Constants;
import com.huanqiu.blog.domain.dto.PublishArticleDto;
import com.huanqiu.blog.domain.dto.SaveArticleDto;
import com.huanqiu.blog.domain.es.Article;
import com.huanqiu.blog.domain.pojo.InsertBlogPo;
import com.huanqiu.blog.domain.pojo.InsertBlogTagPo;
import com.huanqiu.blog.domain.pojo.InsertContentPo;
import com.huanqiu.blog.domain.pojo.TagPo;
import com.huanqiu.blog.mapper.ArticleMapper;
import com.huanqiu.blog.mapper.UserMapper;
import com.huanqiu.blog.mq.producer.ProducerService;
import com.huanqiu.blog.service.ArticleService;
import com.huanqiu.blog.util.SnowFlakeUtil;
import jakarta.annotation.Resource;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author huanqiu
 * @version 1.0
 * @description
 * @date 2023/05/05 下午 5:39
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private SqlSessionFactory sqlSessionFactory;

    @Resource
    private ProducerService producerService;

    @Override
    public List<TagPo> getAllTags() {
        return articleMapper.selectAllTags();
    }

    @Override
    public void addTag(String name) {
        long id = SnowFlakeUtil.getNextId();
        articleMapper.insertTag(new TagPo(String.valueOf(id), name));
    }

    @Override
    public void addTagList(List<String> nameList) {
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
        nameList.forEach(name -> mapper.insertTag(new TagPo(String.valueOf(SnowFlakeUtil.getNextId()), name)));
        sqlSession.commit();
        sqlSession.clearCache();
    }

    @Transactional
    @Override
    public void saveBlog(Long userId, Long articleId, String title, String content) {
        if (articleId == null) {
            articleId = SnowFlakeUtil.getNextId();
        }
        articleMapper.insertTemporaryBlog(new InsertBlogPo(articleId, title, userId, Constants.BlogStatus.DRAFT));
        articleMapper.insertContent(new InsertContentPo(articleId, content));
    }

    @Transactional
    @Override
    public void publishArticle(PublishArticleDto publishArticleDto, Long userId) {
        if (publishArticleDto.getArticleId() == null) {
            publishArticleDto.setArticleId(SnowFlakeUtil.getNextId());
        }
        //插入博客
        articleMapper.insertBlog(new InsertBlogPo(publishArticleDto.getArticleId(), publishArticleDto.getTitle(), userId, Constants.BlogStatus.PUBLISH));
        //插入文章
        articleMapper.insertContent(new InsertContentPo(publishArticleDto.getArticleId(), publishArticleDto.getContent()));
        //删除原本标签
        articleMapper.deleteTagByBlogId(String.valueOf(publishArticleDto.getArticleId()));
        //插入标签
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
        System.out.println(publishArticleDto.getTags());
        publishArticleDto.getTags().forEach(tag -> mapper.insertBlogTag(new InsertBlogTagPo(SnowFlakeUtil.getNextId(), publishArticleDto.getArticleId(), Long.valueOf(tag.getId()))));
        sqlSession.commit();
        sqlSession.clearCache();

        //将文章信息放入mq
        producerService.sendAsync(Constants.ConsumerTopic.ARTICLE_TOPIC, "articleSave", articleMapper.selectBlogInfoById(String.valueOf(publishArticleDto.getArticleId())), null);
    }

    @Transactional
    @Override
    public void saveArticle(SaveArticleDto saveArticleDto, String userId) {
        if (saveArticleDto.getArticleId() == null) {
            saveArticleDto.setArticleId(String.valueOf(SnowFlakeUtil.getNextId()));
        }
        //插入博客
        articleMapper.insertTemporaryBlog(new InsertBlogPo(Long.valueOf(saveArticleDto.getArticleId()), saveArticleDto.getTitle(), Long.valueOf(userId), Constants.BlogStatus.DRAFT));
        //插入文章
        articleMapper.insertContent(new InsertContentPo(Long.valueOf(saveArticleDto.getArticleId()), saveArticleDto.getContent()));
    }
}

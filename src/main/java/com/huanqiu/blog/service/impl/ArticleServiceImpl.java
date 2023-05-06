package com.huanqiu.blog.service.impl;

import com.huanqiu.blog.domain.pojo.InsertAuditArticlePo;
import com.huanqiu.blog.domain.pojo.TagPo;
import com.huanqiu.blog.mapper.ArticleMapper;
import com.huanqiu.blog.service.ArticleService;
import com.huanqiu.blog.util.SnowFlakeUtil;
import jakarta.annotation.Resource;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

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
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public List<TagPo> getAllTags() {
        return articleMapper.selectAllTags();
    }

    @Override
    public void addTag(String name) {
        long id = SnowFlakeUtil.getNextId();
        articleMapper.insertTag(new TagPo(id, name));
    }

    @Override
    public void addTagList(List<String> nameList) {
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
        nameList.forEach(name -> mapper.insertTag(new TagPo(SnowFlakeUtil.getNextId(), name)));
        sqlSession.commit();
        sqlSession.clearCache();
    }

    @Override
    public void addAuditArticle(InsertAuditArticlePo insertAuditArticlePo) {
        articleMapper.insertAuditArticle(insertAuditArticlePo);
    }
}

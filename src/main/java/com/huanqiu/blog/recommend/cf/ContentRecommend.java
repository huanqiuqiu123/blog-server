package com.huanqiu.blog.recommend.cf;

import com.huanqiu.blog.recommend.core.MathCore;
import com.huanqiu.blog.recommend.entity.BlogEntity;
import com.huanqiu.blog.recommend.entity.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author huanqiu
 * @version 1.0
 * @description
 * @date 2023/04/30 下午 2:13
 */
public class ContentRecommend {

    public static List<Result> getScore(List<String> tags, List<BlogEntity> blogEntityList) {
        List<Result> resultList = new ArrayList<>();
        blogEntityList.forEach(u -> {
            double v = computeCosine(tags, u.getTags());
            resultList.add(new Result(u.getBlogId(), v));
        });
        return resultList;
    }


    /**
     * 计算余弦相似度
     *
     * @param objs1 特征列表1
     * @param objs2 特征列表2
     * @return 余弦相似度
     */
    private static double computeCosine(List<?> objs1, List<?> objs2) {
        List<Object> combine = combine(objs1, objs2);
        List<Double> vector1 = new ArrayList<>();
        List<Double> vector2 = new ArrayList<>();
        List<Object> objs1Clone = new ArrayList<>(objs1);
        List<Object> objs2Clone = new ArrayList<>(objs2);

        combine.forEach(u -> {
            int i1 = objs1Clone.indexOf(u);
            int i2 = objs2Clone.indexOf(u);
            if (i1 != -1) {
                objs1Clone.remove(i1);
                vector1.add(1.0);
            } else {
                vector1.add(0.0);
            }
            if (i2 != -1) {
                objs2Clone.remove(i2);
                vector2.add(1.0);
            } else {
                vector2.add(0.0);
            }
        });
        return MathCore.cosine(vector1, vector2);
    }


    private static List<Object> combine(List<?> objs1, List<?> objs2) {
        List<Object> result = new ArrayList<>();
        result.addAll(objs1);
        result.addAll(objs2);
        return result.stream().distinct().collect(Collectors.toList());
    }


}

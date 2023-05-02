package com.huanqiu.blog.recommend;

import com.huanqiu.blog.recommend.cf.ContentRecommend;
import com.huanqiu.blog.recommend.cf.ItemCF;
import com.huanqiu.blog.recommend.entity.BlogEntity;
import com.huanqiu.blog.recommend.entity.RelateEntity;
import com.huanqiu.blog.recommend.entity.Result;
import org.springframework.util.Assert;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author huanqiu
 * @version 1.0
 * @description
 * @date 2023/04/29 下午 3:32
 */
public class Recommend {
    private static final Double MIN_SCORE = 0.8;

    public static List<String> recommendItem(String itemId, List<RelateEntity> itemList) {
        //按物品分组
        Map<String, List<RelateEntity>> itemMap = itemList.stream().collect(Collectors.groupingBy(RelateEntity::getItemId));
        //获取其他物品与当前物品的关系值
        List<Result> itemRecommendList = ItemCF.computeNeighbourByItem(itemId, itemMap);
        List<Result> resultList = itemRecommendList.stream().filter(u -> u.getScore() >= MIN_SCORE).sorted(Comparator.comparing(Result::getScore).reversed()).toList();
        return resultList.stream().map(Result::getKey).toList();
    }

    public static List<String> recommendItemByContent(List<BlogEntity> myList, List<BlogEntity> itemList) {
        //移除看过的博客
        itemList.removeAll(myList);
        List<Result> list = new ArrayList<>();
        List<Result> resultList = new ArrayList<>();
        myList.forEach(u -> {
            List<Result> score = ContentRecommend.getScore(u.getTags(), itemList);
            list.addAll(score);
        });
        Map<String, List<Result>> mapCollect = list.stream().collect(Collectors.groupingBy(Result::getKey));
        mapCollect.forEach((k, v) -> {
            List<Double> scoreList = v.stream().map(Result::getScore).toList();
            Double score = scoreList.stream().mapToDouble(Double::doubleValue).average().getAsDouble();
            resultList.add(new Result(k, score));
        });
        System.out.println(resultList);
        List<Result> result = resultList.stream().filter(u -> u.getScore() >= MIN_SCORE).sorted(Comparator.comparing(Result::getScore).reversed()).toList();
        return result.stream().map(Result::getKey).toList();

    }


}

package com.huanqiu.blog.recommend.cf;

import com.huanqiu.blog.recommend.core.MathCore;
import com.huanqiu.blog.recommend.entity.RelateEntity;
import com.huanqiu.blog.recommend.entity.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author huanqiu
 * @version 1.0
 * @description
 * @date 2023/04/30 下午 2:03
 */
public class UserCF {
    /**
     * 计算用户之间的相似度
     *
     * @param userId 用户id
     * @param map    用户物品评分表
     * @return 相似度列表 其中评分都在0-1之间
     */
    public static List<Result> computeNeighbourByUser(String userId, Map<String, List<RelateEntity>> map) {
        List<Result> resultList = new ArrayList<>();
        List<RelateEntity> userItemList = map.get(userId);
        map.forEach((k, v) -> {
            if (!k.equals(userId)) {
                double dist = computeDistByUser(userItemList, v);
                resultList.add(new Result(k, dist));
            }
        });
        return resultList;
    }

    private static double computeDistByUser(List<RelateEntity> xList, List<RelateEntity> yList) {
        List<Double> xs = new ArrayList<>();
        List<Double> ys = new ArrayList<>();
        xList.forEach(x -> {
            yList.forEach(y -> {
                //找出所有相同的物品
                if (x.getItemId().equals(y.getItemId())) {
                    xs.add(x.getScore());
                    ys.add(y.getScore());
                }
            });
        });
        return MathCore.computePearsonCoefficient(xs, ys);
    }
}

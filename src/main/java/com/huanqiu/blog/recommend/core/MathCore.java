package com.huanqiu.blog.recommend.core;

import java.util.List;
import java.util.stream.IntStream;

/**
 * @author huanqiu
 * @version 1.0
 * @description
 * @date 2023/04/29 下午 3:02
 */
public class MathCore {
    /**
     * 方法描述: 皮尔森（pearson）相关系数计算
     *
     * @param xs x集合
     * @param ys y集合
     */
    public static double computePearsonCoefficient(List<Double> xs, List<Double> ys) {
        int n = xs.size();
        //至少有两个元素
        if (n < 2) {
            return 0D;
        }
        double Ex = xs.stream().mapToDouble(x -> x).sum();
        double Ey = ys.stream().mapToDouble(y -> y).sum();
        double Ex2 = xs.stream().mapToDouble(x -> Math.pow(x, 2)).sum();
        double Ey2 = ys.stream().mapToDouble(y -> Math.pow(y, 2)).sum();
        double Exy = IntStream.range(0, n).mapToDouble(i -> xs.get(i) * ys.get(i)).sum();
        double numerator = Exy - Ex * Ey / n;
        double denominator = Math.sqrt((Ex2 - Math.pow(Ex, 2) / n) * (Ey2 - Math.pow(Ey, 2) / n));
        if (denominator == 0) {
            return 0D;
        }
        return numerator / denominator;
    }

    public static double cosine(List<Double> vector1, List<Double> vector2) {
        if (vector1.size() != vector2.size()) {
            throw new IllegalArgumentException("vector1 and vector2 must have the same size");
        }
        double cos = 0;
        double vector1Modulo = 0;
        double vector2Modulo = 0;
        for (int i = 0; i < vector1.size(); i++) {
            cos += vector1.get(i) * vector2.get(i);
            vector1Modulo += Math.pow(vector1.get(i), 2);
            vector2Modulo += Math.pow(vector2.get(i), 2);
        }
        return cos / (Math.sqrt(vector1Modulo) * Math.sqrt(vector2Modulo));
    }


}

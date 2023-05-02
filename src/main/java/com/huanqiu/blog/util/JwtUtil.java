package com.huanqiu.blog.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JwtUtil {
    //签名
    private static final String SIGN = "huanqiu";
    //过期时间 默认7天
    private static final int EXPIRED_TIME = 7 * 24 * 60 * 60;

    /**
     * @param map  token内部存储的字段
     * @param time 过期时间 单位 s
     * @return token
     */
    public static String getToken(Map<String, String> map, int time) {
        Calendar instance = Calendar.getInstance();
        //默认time秒过期
        instance.add(Calendar.SECOND, time);
        JWTCreator.Builder builder = JWT.create();
        map.forEach(builder::withClaim);
        return builder.withExpiresAt(instance.getTime())  //指定令牌过期时间
                .sign(Algorithm.HMAC256(SIGN));
    }

    /**
     * @param map token内部存储的字段
     * @return token
     */
    public static String getToken(Map<String, String> map) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, EXPIRED_TIME);
        JWTCreator.Builder builder = JWT.create();
        map.forEach(builder::withClaim);
        return builder.withExpiresAt(instance.getTime())  //指定令牌过期时间
                .sign(Algorithm.HMAC256(SIGN));
    }

    /**
     * @param token token
     * @return 使用返回值.get(字段名).asXxx()获取内部数据
     * @apiNote SignatureVerificationException 无效签名
     * TokenExpiredException token过期
     * AlgorithmMismatchException 算法不一致
     * Exception token无效
     */
    public static Map<String, Claim> verify(String token) throws TokenExpiredException {
        DecodedJWT jwt = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        return jwt.getClaims();
    }
}
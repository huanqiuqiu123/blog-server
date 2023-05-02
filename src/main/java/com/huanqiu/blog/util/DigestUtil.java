package com.huanqiu.blog.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author huanqiu
 * @version 1.0
 * @description
 * @date 2022/12/31 下午 6:57
 */
public class DigestUtil {

    /**
     * 加密方法 默认使用md5加密
     *
     * @param str 要加密的字符串
     * @return 加密后的字符串
     */
    public static String encrypt(String str) {
        return md5(str);
    }

    public static String md5(String str) {
        return DigestUtils.md5Hex(str);
    }

    public static String sha256(String str) {
        return DigestUtils.sha256Hex(str);
    }

}

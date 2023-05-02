package com.huanqiu.blog.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.huanqiu.blog.common.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author huanqiu
 * @version 1.0
 * @description
 * @date 2023/02/18 下午 5:27
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResult {
    /**
     * 状态码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 返回值
     */
    private Object data;
}

package com.zh.java.springsecurityjwtsample.jwt.model;

import java.util.HashMap;

/**
 * @author hezihao
 * @version 1.0
 * <p>
 * @date 2020/9/5 11:44 上午
 * <p>
 * 公共响应结果
 */
public class RestResult extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    private static final String CODE_TAG = "code";
    /**
     * 响应信息
     */
    private static final String MSG_TAG = "msg";
    /**
     * 数据对象
     */
    private static final String DATA_TAG = "data";

    public RestResult() {
        super();
    }

    /**
     * 放置状态码和响应消息
     */
    public RestResult(int code, String msg) {
        put(CODE_TAG, code);
        put(MSG_TAG, msg);
    }

    /**
     * 放置状态码、响应消息以及数据对象
     */
    public RestResult(int code, String msg, Object data) {
        put(CODE_TAG, code);
        put(MSG_TAG, msg);
        if (data != null) {
            put(DATA_TAG, data);
        }
    }
}
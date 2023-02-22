package com.blog.exception;

import com.blog.enums.HttpResponseCode;

/**
 * 系统异常
 *
 * @author a1387
 * @date 2023/02/21
 */
public class SystemException extends RuntimeException {

    private int code;

    private String msg;

    public SystemException(HttpResponseCode httpResponseCode) {
        super(httpResponseCode.getMsg());
        this.code = httpResponseCode.getCode();
        this.msg = httpResponseCode.getMsg();
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

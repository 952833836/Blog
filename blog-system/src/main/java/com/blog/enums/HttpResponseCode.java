package com.blog.enums;

/**
 * http响应代码
 *
 * @author a1387
 * @date 2023/02/16
 */
public enum HttpResponseCode {
    /**
     * 成功
     */
    SUCCESS(200, "操作成功"),

    /**
     * 需要登录
     */
    NEED_LOGIN(401, "需要登录后操作"),
    /**
     * 没有操作员身份验证
     */
    NO_OPERATOR_AUTH(403, "无权限操作"),
    /**
     * 系统错误
     */
    SYSTEM_ERROR(500, "出现错误"),
    /**
     * 用户名存在
     */
    USERNAME_EXIST(501, "用户名已存在"),
    /**
     * phonenumber存在
     */
    PHONENUMBER_EXIST(502, "手机号已存在"), EMAIL_EXIST(503, "邮箱已存在"),
    /**
     * 需要用户名
     */
    REQUIRE_USERNAME(504, "必需填写用户名"),
    /**
     * 内容不空
     */
    CONTENT_NOT_NULL(506, "评论内容不能为空"),
    /**
     * 文件类型错误
     */
    FILE_TYPE_ERROR(507, "文件类型错误，请上传png文件"),
    /**
     * 用户名不能空
     */
    USERNAME_NOT_NULL(508, "用户名不能为空"),
    /**
     * 昵称不空
     */
    NICKNAME_NOT_NULL(509, "昵称不能为空"),
    /**
     * 密码非空
     */
    PASSWORD_NOT_NULL(510, "密码不能为空"),
    /**
     * 电子邮件不是零
     */
    EMAIL_NOT_NULL(511, "邮箱不能为空"),
    /**
     * 昵称存在
     */
    NICKNAME_EXIST(512, "昵称已存在"),
    /**
     * 登录错误
     */
    LOGIN_ERROR(505, "用户名或密码错误"),
    /**
     * 令牌非法或超时
     */
    TOKEN_ILLEGAL_OR_TIMEOUT(506, "token非法或超时"),
    /**
     * 参数异常
     */
    PARAMETER_EXCEPTION(405, "参数异常");

    private Integer code;
    private String msg;

    HttpResponseCode(int code, String errorMessage) {
        this.code = code;
        this.msg = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
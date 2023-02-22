package com.blog.pojo.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户信息
 *
 * @author a1387
 * @date 2023/02/20
 */
@Data
@Accessors(chain = true)
public class UserInfo {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private String id;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String phoneNumber;
    /**
     * 用户性别（0男，1女，2未知）
     */
    private Integer sex;
    /**
     * 头像
     */
    private String avatar;
}

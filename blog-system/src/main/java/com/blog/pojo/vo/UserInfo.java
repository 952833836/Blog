package com.blog.pojo.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
    @NotNull(message = "昵称不能为空")
    private String nickname;
    /**
     * 邮箱
     */
    @NotNull(message = "邮箱不能为空")
    @Email(message = "邮箱格式错误")
    private String email;
    /**
     * 手机号
     */
    @NotNull(message = "电话号码不能为空")
    @Pattern(regexp = "^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$",message = "错误的电话")
    private String phoneNumber;
    /**
     * 用户性别（0男，1女，2未知）
     */
    private Integer sex;
    /**
     * 头像
     */
    @NotNull(message = "请选择头像")
    private String avatar;
}

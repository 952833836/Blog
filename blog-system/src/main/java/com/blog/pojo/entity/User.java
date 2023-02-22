package com.blog.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 * 用户表
 *
 * @author a1387
 * @TableName user
 * @date 2023/02/22
 */
@TableName(value = "user")
@Data
@Accessors(chain = true)
public class User implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "id")
    private String id;
    /**
     * 用户名
     */
    @TableField(value = "user_name")
    private String username;
    /**
     * 昵称
     */
    @TableField(value = "nick_name")
    private String nickname;
    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;
    /**
     * 用户类型：0代表普通用户，1代表管理员
     */
    @TableField(value = "type")
    private Integer type;
    /**
     * 账号状态（0正常 1停用）
     */
    @TableField(value = "status")
    private Integer status;
    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;
    /**
     * 手机号
     */
    @TableField(value = "phonenumber")
    private String phoneNumber;
    /**
     * 用户性别（0男，1女，2未知）
     */
    @TableField(value = "sex")
    private Integer sex;
    /**
     * 头像
     */
    @TableField(value = "avatar")
    private String avatar;
    /**
     * 创建者id
     */
    @TableField(value = "create_by")
    private String createBy;
    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 更新者id
     */
    @TableField(value = "update_by")
    private String updateBy;
    /**
     * 跟新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    /**
     * 逻辑删除标制
     */
    @TableField(value = "del_flag")
    @TableLogic
    private Integer delFlag;


}
package com.blog.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 类别
 *
 * @author a1387
 * @TableName category
 * @date 2023/02/18
 */
@TableName(value = "category")
@Data
@Accessors(chain = true)
public class Category implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @TableId(value = "id")
    private String id;
    /**
     * 分类名
     */
    @TableField(value = "name")
    private String name;
    /**
     * 父分类id，如果没有父分类为-1
     */
    @TableField(value = "pid")
    private String pid;
    /**
     * 状态0:正常,1禁用
     */
    @TableField(value = "status")
    private Integer status;
    /**
     * 描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 创建者id
     */
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private String createBy;
    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 更新者id
     */
    @TableField(value = "update_by", fill = FieldFill.INSERT)
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
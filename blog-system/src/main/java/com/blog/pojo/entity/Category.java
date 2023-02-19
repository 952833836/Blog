package com.blog.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

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

    @TableField(value = "create_by")
    private String createBy;

    @TableField(value = "create_time")
    private LocalDateTime createTime;

    @TableField(value = "update_by")
    private String updateBy;

    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    @TableLogic
    @TableField(value = "del_flag")
    private Integer delFlag;

}
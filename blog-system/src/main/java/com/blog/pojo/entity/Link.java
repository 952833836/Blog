package com.blog.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 友链表
 *
 * @author a1387
 * @TableName link
 * @date 2023/02/20
 */
@TableName(value ="link")
@Data
@Accessors(chain = true)
public class Link implements Serializable {
    /**
     * 
     */
    @TableId(value = "id")
    private String id;

    /**
     * 友链名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 友链地址
     */
    @TableField(value = "address")
    private String address;

    /**
     * 友链logo
     */
    @TableField(value = "logo")
    private String logo;

    /**
     * 友链详情
     */
    @TableField(value = "description")
    private String description;

    /**
     * 审核状态(0审核通过，1待审核，2审核不通过)
     */
    @TableField(value = "status",fill = FieldFill.INSERT)
    private Integer status;

    /**
     * 创建者id
     */
    @TableField(value = "create_by")
    private String createBy;
    /**
     * 创建时间
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 更新者id
     */
    @TableField(value = "update_by")
    private String updateBy;
    /**
     * 跟新时间
     */
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE )
    private LocalDateTime updateTime;
    /**
     * 逻辑删除标制
     */
    @TableField(value = "del_flag")
    @TableLogic
    private Integer delFlag;

    @TableField(exist = false)
    private static final long rr = 1L;

}
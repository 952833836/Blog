package com.blog.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论表
 *
 * @author a1387
 * @TableName comment
 * @date 2023/02/21
 */
@TableName(value = "comment")
@Data
@Accessors(chain = true)
public class Comment implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @TableId(value = "id")
    private String id;

    /**
     * 评论类型（0代表文章评论，1代表友链评论）
     */
    @TableField(value = "type")
    private Integer type;
    /**
     * 文章id
     */
    @TableField(value = "article_id")
    private String articleId;
    /**
     * 根评论id
     */
    @TableField(value = "root_id")
    private String rootId;
    /**
     * 评论内容
     */
    @TableField(value = "content")
    private String content;
    /**
     * 所回复的目标评论的userid
     */
    @TableField(value = "to_comment_user_id")
    private String toCommentUserId;
    /**
     * 回复目标评论id
     */
    @TableField(value = "to_comment_id")
    private String toCommentId;
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
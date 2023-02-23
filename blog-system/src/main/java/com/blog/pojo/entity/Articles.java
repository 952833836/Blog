package com.blog.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章
 *
 * @author a1387
 * @TableName articles
 * @date 2023/02/16
 */
@TableName(value = "articles")
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Articles implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @TableId(value = "id")
    private String id;
    /**
     * 文章标题
     */
    @TableField(value = "title")
    private String title;
    /**
     * 文章正文
     */
    @TableField(value = "content")
    private String content;
    /**
     * 文章摘要
     */
    @TableField(value = "summary")
    private String summary;
    /**
     * 所属类别id
     */
    @TableField(value = "category_id")
    private String categoryId;

    /**
     * 类别名称
     */
    @TableField(exist = false)
    private String categoryName;
    /**
     * 缩略图地址
     */
    @TableField(value = "thumbnail")
    private String thumbnail;
    /**
     * 是否置顶(0置顶,1不置顶)
     */
    @TableField(value = "is_top")
    private Integer isTop;
    /**
     * 状态(0已发布,1未发布)
     */
    @TableField(value = "status")
    private Integer status;
    /**
     * 访问量
     */
    @TableField(value = "view_count")
    private Long viewCount;
    /**
     * 是否允许评论(0允许,1不允许)
     */
    @TableField(value = "is_comment")
    private Integer isComment;
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
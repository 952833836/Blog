package com.blog.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 添加评论dto
 *
 * @author a1387
 * @date 2023/02/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCommentDto {
    private String id;
    /**
     * 评论类型（0代表文章评论，1代表友链评论）
     */
    private Integer type;

    private String articleId;

    private String rootId;

    private String content;

    private String toCommentUserId;

    private String toCommentId;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private Integer delFlag;
}

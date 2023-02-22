package com.blog.pojo.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * 评论Vo
 *
 * @author a1387
 * @date 2023/02/21
 */
@Data
@Accessors(chain = true)
public class CommentVo {

    private static final long serialVersionUID = 1L;

    private String id;

    private String articleId;

    private String rootId;

    private String nickname;

    private String content;

    private String toCommentUserId;

    private String toCommentUserName;

    private String toCommentId;

    private String createBy;

    private Date createTime;

    private List<CommentVo> children;
}

package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.pojo.ResponseResult;
import com.blog.pojo.entity.Comment;

/**
 * @author a1387
 * @description 针对表【comment(评论表)】的数据库操作Service
 * @createDate 2023-02-21 18:10:14
 */
public interface CommentService extends IService<Comment> {

    /**
     * 添加评论
     *
     * @param comment 评论
     * @return {@link ResponseResult}
     */
    ResponseResult addComment(Comment comment);

    /**
     * 得到评论列表
     *
     * @param articleId 文章id
     * @param pageNum   页码
     * @param pageSize  每页评论数
     * @param type      类型
     * @return {@link ResponseResult}
     */
    ResponseResult getCommentList(String articleId, Integer type, Integer pageNum, Integer pageSize);
}

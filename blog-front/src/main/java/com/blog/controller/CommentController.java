package com.blog.controller;

import com.blog.pojo.ResponseResult;
import com.blog.pojo.dto.AddCommentDto;
import com.blog.pojo.entity.Comment;
import com.blog.service.CommentService;
import com.blog.pojo.util.BeanCopyUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 评论控件
 *
 * @author a1387
 * @date 2023/02/21
 */
@RestController
@RequestMapping("/comments")
@Validated
public class CommentController {
    @Resource
    CommentService commentService;

    @GetMapping("/{articleId}/{pageNum}/{pageSize}")
    public ResponseResult getCommentList(@PathVariable("articleId") String articleId, @Min(1) @PathVariable("pageNum") Integer pageNum, @Min(1) @PathVariable("pageSize") Integer pageSize) {
        return commentService.getCommentList(articleId, 0, pageNum, pageSize);
    }

    @PostMapping
    public ResponseResult addComment(@RequestBody @NotNull AddCommentDto addCommentDto) {
        Comment comment = BeanCopyUtil.copyBean(addCommentDto, Comment.class);
        return commentService.addComment(comment);
    }
}

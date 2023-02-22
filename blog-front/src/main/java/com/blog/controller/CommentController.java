package com.blog.controller;

import com.blog.pojo.ResponseResult;
import com.blog.service.CommentService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.Min;

/**
 * 评论控件
 *
 * @author a1387
 * @date 2023/02/21
 */
@RestController
@RequestMapping("/comments")
public class CommentController {
    @Resource
    CommentService commentService;


    @GetMapping("/{articleId}/{pageNum}/{pageSize}")
    @Validated
    public ResponseResult getCommentList(@PathVariable("articleId") String articleId, @Min(1) @PathVariable("pageNum") Integer pageNum, @Min(1) @PathVariable("pageSize") Integer pageSize) {
        return commentService.getCommentList(articleId, 0, pageNum, pageSize);
    }


}

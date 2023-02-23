package com.blog.controller;

import com.blog.pojo.ResponseResult;
import com.blog.pojo.dto.AddCommentDto;
import com.blog.pojo.entity.Comment;
import com.blog.pojo.vo.LinkVo;
import com.blog.service.CommentService;
import com.blog.service.LinkService;
import com.blog.pojo.util.BeanCopyUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 链接控制器
 *
 * @author a1387
 * @date 2023/02/20
 */
@RestController
@RequestMapping("/links")
@Validated
public class LinkController {

    @Resource
    LinkService linkService;

    @Resource
    CommentService commentService;

    @GetMapping("getAllLink")
    public ResponseResult<List<LinkVo>> getLinkList() {
        return linkService.getLinkList();
    }

    @GetMapping("/{articleId}/{pageNum}/{pageSize}")
    public ResponseResult getCommentList(@PathVariable("articleId") String articleId, @Min(1) @PathVariable("pageNum") Integer pageNum, @Min(1) @PathVariable("pageSize") Integer pageSize) {
        return commentService.getCommentList(articleId, 2, pageNum, pageSize);
    }

    @PostMapping
    public ResponseResult addComment(@NotNull @RequestBody AddCommentDto addCommentDto) {
        Comment comment = BeanCopyUtil.copyBean(addCommentDto, Comment.class);
        return commentService.addComment(comment);
    }
}

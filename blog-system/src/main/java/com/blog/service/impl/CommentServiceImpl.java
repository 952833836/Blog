package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.common.SystemConstants;
import com.blog.mapper.CommentMapper;
import com.blog.pojo.ResponseResult;
import com.blog.pojo.entity.Comment;
import com.blog.pojo.entity.User;
import com.blog.pojo.vo.CommentVo;
import com.blog.pojo.vo.PageVo;
import com.blog.service.CommentService;
import com.blog.service.UserService;
import com.blog.util.BeanCopyUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 评论服务impl
 *
 * @author a1387
 * @description 针对表【comment(评论表)】的数据库操作Service实现
 * @createDate 2023-02-21 18:10:14
 * @date 2023/02/21
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
        implements CommentService {

    @Resource
    CommentMapper commentMapper;

    @Resource
    UserService userService;

    @Override
    public ResponseResult getCommentList(String articleId, Integer type, Integer pageNum, Integer pageSize) {
        //获取所以对该文章的根评论
        LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Comment::getArticleId, articleId)
                .eq(Comment::getType, type)
                .eq(Comment::getRootId, SystemConstants.COMMENT_ROOT);
        Page<Comment> page = new Page<Comment>(pageNum, pageSize);
        page = commentMapper.selectPage(page, wrapper);
        List<Comment> records = page.getRecords();
        if (records.isEmpty()) {
            throw new RuntimeException("暂无评论");
        }
        //将comment封装为commentVo
        List<CommentVo> commentVoList = getCommentVoList(records);
        //获取子评论
        List<CommentVo> collect = commentVoList.stream()
                .map(e -> {
                    List<CommentVo> childComments = getChildComments(e.getId());
                    return e.setChildren(childComments);
                }).collect(Collectors.toList());
        PageVo pageVo = new PageVo(collect, page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    /**
     * 获取子评论评论
     *
     * @return {@link List}<{@link CommentVo}>
     */
    public List<CommentVo> getChildComments(String id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getRootId, id);
        queryWrapper.orderByAsc(Comment::getCreateTime);
        List<Comment> comments = list(queryWrapper);
        return getCommentVoList(comments);
    }

    /**
     * comment封装为commentVo
     *
     * @param commentList 评论列表
     * @return {@link List}<{@link CommentVo}>
     */
    public List<CommentVo> getCommentVoList(List<Comment> commentList) {
        List<CommentVo> commentVos = BeanCopyUtil.copyBeanList(commentList, CommentVo.class);
        return commentVos.stream()
                .map(e -> {
                    User user = userService.getById(e.getCreateBy());
                    e.setNickname(user.getNickname());
                    if (!e.getRootId().equals(SystemConstants.COMMENT_ROOT)) {
                        User byId = userService.getById(e.getToCommentUserId());
                        e.setToCommentUserName(byId.getNickname());
                    }
                    return e;
                }).collect(Collectors.toList());
    }

}





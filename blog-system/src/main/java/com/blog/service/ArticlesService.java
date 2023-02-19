package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.pojo.ResponseResult;
import com.blog.pojo.entity.Articles;
import com.blog.pojo.vo.HotArticlesVo;
import com.blog.pojo.vo.PageVo;

import java.util.List;

/**
 * @author a1387
 * @description 针对表【articles】的数据库操作Service
 * @createDate 2023-02-16 22:40:50
 */
public interface ArticlesService extends IService<Articles> {
    /**
     * 查询受欢迎文章
     * <p>
     * 需要查询浏览量最高的前10篇文章的信息，要求展示文章标题和浏览量。
     * 能让用户自己点击跳转到具体的文章详情进行浏览。
     * 注意:不能展示草稿，以及逻辑删除了的文章 要按照浏览量进行降序排列
     *
     * @return {@link ResponseResult}<{@link List}<{@link Articles}>>
     */
    ResponseResult<List<HotArticlesVo>> queryPopularArticles();

    /**
     * 查询文章列表
     *
     * @param categoryId 类别id(若id为空则查询所以，不为空按类别查询)
     * @param pageNum    页面num
     * @param pageSize   页面大小
     * @return {@link ResponseResult}
     */
    ResponseResult<List<PageVo>> queryListArticle(String categoryId, Integer pageNum, Integer pageSize);
}

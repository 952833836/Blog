package com.blog.controller;

import com.blog.pojo.ResponseResult;
import com.blog.pojo.vo.ArticleDetailVo;
import com.blog.pojo.vo.HotArticlesVo;
import com.blog.pojo.vo.PageVo;
import com.blog.service.ArticlesService;
import io.swagger.annotations.Api;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 文章控制器
 *
 * @author a1387
 * @date 2023/02/16
 */
@RestController()
@RequestMapping("/articles")
@Validated
public class ArticleController {

    @Resource
    private ArticlesService articlesService;

    @GetMapping("/hotArticleList")
    public ResponseResult<List<HotArticlesVo>> queryPopularArticles() {
        return articlesService.queryPopularArticles();
    }

    @GetMapping("/articleList")
    public ResponseResult<List<PageVo>> queryListArticle( @Param("categoryId") @NotNull String categoryId, @Param("pageNum") @NotNull @Min(0) Integer pageNum, @Param("pageSize") @NotNull @Min(0) Integer pageSize) {
        return articlesService.queryListArticle(categoryId, pageNum, pageSize);
    }

    @GetMapping("/{id}")
    public ResponseResult<ArticleDetailVo> getArticleDetail( @PathVariable("id")  String id) {
        return articlesService.getArticleDetail(id);
    }

}

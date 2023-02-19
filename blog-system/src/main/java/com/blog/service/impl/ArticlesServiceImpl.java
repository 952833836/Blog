package com.blog.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.common.SystemConstants;
import com.blog.mapper.ArticlesMapper;
import com.blog.mapper.CategoryMapper;
import com.blog.pojo.ResponseResult;
import com.blog.pojo.entity.Articles;
import com.blog.pojo.vo.ArticlesVo;
import com.blog.pojo.vo.HotArticlesVo;
import com.blog.pojo.vo.PageVo;
import com.blog.service.ArticlesService;
import com.blog.service.CategoryService;
import com.blog.util.BeanCopyUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author a1387
 * @description 针对表【articles】的数据库操作Service实现
 * @createDate 2023-02-16 22:40:50
 */
@Service
public class ArticlesServiceImpl extends ServiceImpl<ArticlesMapper, Articles> implements ArticlesService {

    @Resource
    ArticlesMapper articlesMapper;

    @Resource
    CategoryMapper categoryMapper;

    @Override
    public ResponseResult<List<HotArticlesVo>> queryPopularArticles() {
        //查询热门文章
        LambdaQueryWrapper<Articles> wrapper =
                new LambdaQueryWrapper<>();
        //文章状态为发布
        wrapper.eq(Articles::getStatus, SystemConstants.ARTICLE_RELEASE)
                .orderByDesc(Articles::getViewCount);
        //获取前十条数据
        Page<Articles> articlesPage = articlesMapper.selectPage(new Page<>(1, 10), wrapper);
        List<Articles> articles = articlesPage.getRecords();
        if (articles.isEmpty()) {
            throw new RuntimeException("暂无文章");
        }
        List<HotArticlesVo> hotArticlesVos = BeanCopyUtil.copyBeanList(articles, HotArticlesVo.class);

        return ResponseResult.okResult(hotArticlesVos);
    }

    @Override
    public ResponseResult<List<PageVo>> queryListArticle(String categoryId, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Articles> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                //若categoryId!=null根据categoryId查询
                .eq(StringUtils.hasText(categoryId), Articles::getCategoryId, categoryId)
                //status=0
                .eq(Articles::getStatus, SystemConstants.ARTICLE_RELEASE)
                //对isTop降序排序
                .orderByDesc(Articles::getIsTop);
        //分页查询
        Page<Articles> page = new Page<>(pageNum, pageSize);
        Page<Articles> articlesPage = articlesMapper.selectPage(page, queryWrapper);
        List<Articles> collect = articlesPage.getRecords()
                .stream()
                //获取对应的分类标签名
                .map(e -> {
                    if(StringUtils.hasText(e.getCategoryId())){
                        return e.setCategoryName(categoryMapper.selectById(e.getCategoryId()).getName());
                    }
                    return e;
                })
                .collect(Collectors.toList());
        //封装查询结果
        List<ArticlesVo> list = BeanCopyUtil.copyBeanList(collect, ArticlesVo.class);
        if (list.isEmpty()) {
            throw new RuntimeException("暂无文章发表");
        }
        PageVo pageVo = new PageVo(list, articlesPage.getTotal());
        return ResponseResult.okResult(pageVo);
    }


}





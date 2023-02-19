package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.common.SystemConstants;
import com.blog.mapper.CategoryMapper;
import com.blog.pojo.ResponseResult;
import com.blog.pojo.entity.Articles;
import com.blog.pojo.entity.Category;
import com.blog.pojo.vo.CategoryVo;
import com.blog.service.ArticlesService;
import com.blog.service.CategoryService;
import com.blog.util.BeanCopyUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author a1387
 * @description 针对表【category】的数据库操作Service实现
 * @createDate 2023-02-18 15:32:02
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
        implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private ArticlesService articlesService;

    @Override
    public ResponseResult getCategory() {
        LambdaQueryWrapper<Articles> queryWrapper = new LambdaQueryWrapper<>();
        //查询articles表状态为ARTICLE_RELEASE的列
        queryWrapper.eq(Articles::getStatus, SystemConstants.ARTICLE_RELEASE);
        //查询所以status==0的文章
        Set<String> collect = articlesService.list(queryWrapper)
                .stream()
                .map(Articles::getCategoryId)
                //将流转换为set集合完成去重
                .collect(Collectors.toSet());
        //批量查询所以符合条件的Category
        List<Category> collect1 = categoryMapper.selectBatchIds(collect)
                .stream()
                //过滤所以status！=0的数据
                .filter(e -> e.getStatus().equals(SystemConstants.STATUS_NORMAL))
                .collect(Collectors.toList());
        //封装vo
        List<CategoryVo> categoryVos = BeanCopyUtil.copyBeanList(collect1, CategoryVo.class);
        return ResponseResult.okResult(categoryVos);
    }
}





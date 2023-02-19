package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.pojo.ResponseResult;
import com.blog.pojo.entity.Category;


/**
 * @author a1387
 * @description 针对表【category】的数据库操作Service
 * @createDate 2023-02-18 15:32:02
 */
public interface CategoryService extends IService<Category> {
    /**
     * 获取文章分类
     *
     * @return {@link ResponseResult}
     */
    ResponseResult getCategory();
}

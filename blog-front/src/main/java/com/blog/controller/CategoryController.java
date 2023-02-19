package com.blog.controller;

import com.blog.pojo.ResponseResult;
import com.blog.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 类别控制器
 *
 * @author a1387
 * @date 2023/02/19
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    CategoryService categoryService;

    @GetMapping("/getCategoryList")
    public ResponseResult getCategoryList() {
        return categoryService.getCategory();
    }
}

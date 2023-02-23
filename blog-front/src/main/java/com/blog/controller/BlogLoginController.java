package com.blog.controller;

import com.blog.pojo.ResponseResult;
import com.blog.pojo.entity.User;
import com.blog.pojo.vo.BlogUserLoginVo;
import com.blog.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 博客登录控制器
 *
 * @author a1387
 * @date 2023/02/20
 */
@RestController
@RequestMapping("/blogLogin")
@Validated
public class BlogLoginController {
    @Resource
    UserService userService;

    @PostMapping("/login")
    public ResponseResult<BlogUserLoginVo> responseResult(@RequestBody @NotNull User user) {
        return userService.login(user);
    }

    @PostMapping("/logOut")
    public ResponseResult<String> logOut() {
        return userService.logout();
    }
}

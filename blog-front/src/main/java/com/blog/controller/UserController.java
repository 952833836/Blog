package com.blog.controller;

import com.blog.pojo.ResponseResult;
import com.blog.pojo.entity.User;
import com.blog.pojo.util.BeanCopyUtil;
import com.blog.pojo.vo.UserInfo;
import com.blog.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 用户控制器
 *
 * @author a1387
 * @date 2023/02/23
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Resource
    UserService userService;

    @GetMapping("/userInfo")
    public ResponseResult getUserInfo(){
        return userService.getUserInfo();
    }

    @PutMapping
    public ResponseResult updateUserInformation(@Valid @RequestBody UserInfo userInfo){
        User user = BeanCopyUtil.copyBean(userInfo, User.class);
        return userService.updateUserInformation(user);
    }
}

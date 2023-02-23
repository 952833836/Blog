package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.pojo.ResponseResult;
import com.blog.pojo.entity.User;
import com.blog.pojo.vo.BlogUserLoginVo;

/**
 * @author a1387
 * @description 针对表【user(用户表)】的数据库操作Service
 * @createDate 2023-02-20 16:34:50
 */
public interface UserService extends IService<User> {
    /**
     * 登录
     *
     * @param user 用户
     * @return {@link ResponseResult}
     */
    ResponseResult<BlogUserLoginVo> login(User user);

    /**
     * 注销
     *
     * @return {@link ResponseResult}<{@link String}>
     */
    ResponseResult<String> logout();

    /**
     * 获取用户信息
     *
     * @return {@link ResponseResult}
     */
    ResponseResult getUserInfo();

    /**
     * 更新用户信息
     *
     * @param user 用户
     * @return {@link ResponseResult}
     */
    ResponseResult updateUserInformation(User user);
}

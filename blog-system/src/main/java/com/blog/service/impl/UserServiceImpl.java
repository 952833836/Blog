package com.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.common.SystemConstants;
import com.blog.mapper.UserMapper;
import com.blog.pojo.ResponseResult;
import com.blog.pojo.dto.LoginUser;
import com.blog.pojo.entity.User;
import com.blog.pojo.vo.BlogUserLoginVo;
import com.blog.pojo.vo.UserInfo;
import com.blog.service.UserService;
import com.blog.pojo.util.BeanCopyUtil;
import com.blog.pojo.util.JwtUtil;
import com.blog.pojo.util.RedisUtil;
import com.blog.pojo.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author a1387
 * @description 针对表【user(用户表)】的数据库操作Service实现
 * @createDate 2023-02-20 16:34:50
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Resource
    RedisUtil redisUtil;

    @Override
    public ResponseResult<BlogUserLoginVo> login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("账号密码错误");
        }
        LoginUser principal = (LoginUser) authenticate.getPrincipal();
        UserInfo userInfo = BeanCopyUtil.copyBean(principal.getUser(), UserInfo.class);
        redisUtil.setCacheObject(SystemConstants.BLOG_LOGIN + userInfo.getId(), principal);
        String token = JwtUtil.createJwt(userInfo.getId());
        BlogUserLoginVo blogUserLoginVo = new BlogUserLoginVo(token, userInfo);
        return ResponseResult.okResult(blogUserLoginVo);
    }

    @Override
    public ResponseResult<String> logout() {
        //从SecurityContextHolder中获取用户信息
        LoginUser principal = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //删除redis中保存的用户信息
        redisUtil.deleteObject(SystemConstants.BLOG_LOGIN + principal.getUser().getId());
        return ResponseResult.okResult(200, "已成功退出登录");
    }

    @Override
    public ResponseResult getUserInfo() {
        String userId = SecurityUtils.getUserId();
        UserInfo userInfo = BeanCopyUtil.copyBean(getById(userId), UserInfo.class);
        return ResponseResult.okResult(userInfo);
    }

    @Override
    public ResponseResult updateUserInformation(User user) {
        //将外链链接跟新到数据库和redis;
        LoginUser loginUser = SecurityUtils.getLoginUser();
        loginUser.setUser(user);
        redisUtil.setCacheObject(SystemConstants.BLOG_LOGIN+user.getId(),loginUser);
        updateById(user);
        return ResponseResult.okResult();
    }

}





package com.blog.handle.security;


import com.alibaba.fastjson.JSON;
import com.blog.enums.HttpResponseCode;
import com.blog.pojo.ResponseResult;
import com.blog.pojo.util.WebUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义认证失败
 *
 * @author a1387
 * @date 2023/02/21
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        //打印异常信息到控制台
        authException.printStackTrace();
        ResponseResult responseResult = null;
        //权限不足会爆BadCredentialsException，身份验证不通过爆InsufficientAuthenticationException
        if (authException instanceof BadCredentialsException) {
            responseResult = ResponseResult.errorResult(505, authException.getMessage());
        } else if (authException instanceof InsufficientAuthenticationException) {
            responseResult = ResponseResult.errorResult(HttpResponseCode.NEED_LOGIN);
        } else {
            responseResult = ResponseResult.errorResult(HttpResponseCode.SYSTEM_ERROR, "认证或授权失败");
        }

        WebUtils.renderString(response, JSON.toJSONString(responseResult));
    }

}

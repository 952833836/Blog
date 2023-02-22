package com.blog.handle.security;

import com.alibaba.fastjson.JSON;
import com.blog.enums.HttpResponseCode;
import com.blog.pojo.ResponseResult;
import com.blog.util.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义授权失败
 * 拒绝访问处理器
 *
 * @author a1387
 * @date 2023/02/21
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        accessDeniedException.printStackTrace();
        ResponseResult responseResult = ResponseResult.errorResult(HttpResponseCode.NO_OPERATOR_AUTH);
        WebUtils.renderString(response, JSON.toJSONString(responseResult));
    }
}

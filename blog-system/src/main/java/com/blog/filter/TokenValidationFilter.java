package com.blog.filter;

import com.alibaba.fastjson.JSON;
import com.blog.common.SystemConstants;
import com.blog.enums.HttpResponseCode;
import com.blog.pojo.ResponseResult;
import com.blog.pojo.dto.LoginUser;
import com.blog.pojo.util.JwtUtil;
import com.blog.pojo.util.RedisUtil;
import com.blog.pojo.util.WebUtils;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * 令牌验证过滤器
 *
 * @author a1387
 * @date 2023/02/21
 */
@Component
public class TokenValidationFilter extends OncePerRequestFilter {

    @Resource
    RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //从请求头中获取token，若不存在token则直接放行
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        //解析token获取用户id
        String userId = null;
        try {
            Claims claims = JwtUtil.parseJwt(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            String json = JSON.toJSONString(ResponseResult.errorResult(HttpResponseCode.TOKEN_ILLEGAL_OR_TIMEOUT));
            WebUtils.renderString(response, json);
            return;
        }
        //通过用户id从redis中获取用户信息
        LoginUser loginUser = (LoginUser) redisUtil.getCacheObject(SystemConstants.BLOG_LOGIN + userId);
        if (Objects.isNull(loginUser)) {
            String json = JSON.toJSONString(ResponseResult.errorResult(HttpResponseCode.NEED_LOGIN));
            WebUtils.renderString(response, json);
            return;
        }
        //将loginUser封装为UsernamePasswordAuthenticationToken对象，传入SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}

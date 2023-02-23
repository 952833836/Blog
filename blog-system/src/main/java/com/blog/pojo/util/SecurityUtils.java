package com.blog.pojo.util;

import com.blog.pojo.dto.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 安全工具类
 *
 * @author a1387
 * @date 2023/02/22
 */
public class SecurityUtils {
    /**
     * 获取用户
     **/
    public static LoginUser getLoginUser() {
        return (LoginUser) getAuthentication().getPrincipal();
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static Boolean isAdmin() {
        String id = getLoginUser().getUser().getId();
        return id != null && id.equals("1");
    }

    public static String getUserId() {
        return getLoginUser().getUser().getId();
    }
}


package com.blog.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户Vo
 *
 * @author a1387
 * @date 2023/02/20
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
public class BlogUserLoginVo {

    /**
     * 令牌
     */
    String token;

    /**
     * 用户信息
     */
    UserInfo userInfo;

}

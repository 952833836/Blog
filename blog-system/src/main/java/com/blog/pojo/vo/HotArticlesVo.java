package com.blog.pojo.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 热门文章Vo
 *
 * @author a1387
 * @date 2023/02/17
 */
@Data
@Accessors(chain = true)
public class HotArticlesVo {

    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 访问量
     */
    private Long viewCount;
}

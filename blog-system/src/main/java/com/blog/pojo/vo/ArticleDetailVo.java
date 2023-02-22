package com.blog.pojo.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 文章详细签证官
 *
 * @author a1387
 * @date 2023/02/19
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class ArticleDetailVo extends ArticlesVo {
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    private String id;
    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章摘要
     */
    private String summary;

    /**
     * 文章正文
     */
    private String content;

    /**
     * 所属类别id
     */
    private String categoryName;

    /**
     * 缩略图地址
     */
    private String thumbnail;

    /**
     * 访问量
     */
    private Long viewCount;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}

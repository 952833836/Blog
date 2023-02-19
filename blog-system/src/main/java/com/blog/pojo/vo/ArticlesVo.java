package com.blog.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 文章签证官
 *
 * @author a1387
 * @date 2023/02/19
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ArticlesVo implements Serializable {

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

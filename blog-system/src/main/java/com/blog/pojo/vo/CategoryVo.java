package com.blog.pojo.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 类别Vo
 *
 * @author a1387
 * @date 2023/02/18
 */

@Data
@Accessors(chain = true)
public class CategoryVo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    private String id;
    /**
     * 分类名
     */
    private String name;

}

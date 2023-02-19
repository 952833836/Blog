package com.blog.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 分页
 *
 * @author a1387
 * @date 2023/02/19
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
public class PageVo {

    private List rows;

    private Long total;
}

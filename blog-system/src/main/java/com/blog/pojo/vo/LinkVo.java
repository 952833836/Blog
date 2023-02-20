package com.blog.pojo.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * link
 *
 * @author a1387
 * @date 2023/02/20
 */
public class LinkVo implements Serializable {
    /**
     *
     */
    private String id;

    /**
     * 友链名称
     */
    private String name;

    /**
     * 友链地址
     */
    private String address;

    /**
     * 友链logo
     */
    private String logo;

    /**
     * 友链详情
     */
    private String description;

}

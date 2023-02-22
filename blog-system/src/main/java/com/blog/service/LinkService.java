package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.pojo.ResponseResult;
import com.blog.pojo.entity.Link;
import com.blog.pojo.vo.LinkVo;

import java.util.List;

/**
 * 友链
 *
 * @author a1387
 * @description 针对表【link】的数据库操作Service
 * @createDate 2023-02-20 15:35:31
 * @date 2023/02/20
 */
public interface LinkService extends IService<Link> {

    /**
     * 得到链接列表
     *
     * @return {@link ResponseResult}
     */
     ResponseResult<List<LinkVo>> getLinkList();
}

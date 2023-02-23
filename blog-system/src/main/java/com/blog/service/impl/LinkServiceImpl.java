package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.common.SystemConstants;
import com.blog.mapper.LinkMapper;
import com.blog.pojo.ResponseResult;
import com.blog.pojo.entity.Link;
import com.blog.pojo.vo.LinkVo;
import com.blog.service.LinkService;
import com.blog.pojo.util.BeanCopyUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author a1387
 * @description 针对表【link】的数据库操作Service实现
 * @createDate 2023-02-20 15:35:31
 */
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link>
        implements LinkService {

    @Override
    public ResponseResult<List<LinkVo>> getLinkList() {
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Link::getStatus, SystemConstants.APPROVED);
        List<Link> links = list(queryWrapper);
        if (links.isEmpty()) {
            throw new RuntimeException("赞无友链，敬请期待");
        }
        List<LinkVo> linkVos = BeanCopyUtil.copyBeanList(links, LinkVo.class);
        return ResponseResult.okResult(linkVos);
    }
}





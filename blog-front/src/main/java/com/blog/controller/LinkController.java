package com.blog.controller;

import com.blog.pojo.ResponseResult;
import com.blog.pojo.vo.LinkVo;
import com.blog.service.LinkService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 链接控制器
 *
 * @author a1387
 * @date 2023/02/20
 */
@RestController
@RequestMapping("links")
public class LinkController {

    @Resource
    LinkService linkService;

    @GetMapping("getAllLink")
    public ResponseResult<List<LinkVo>> getLinkList() {
        return linkService.getLinkList();
    }
}

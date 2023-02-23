package com.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.mapper.MenuMapper;
import com.blog.pojo.entity.Menu;
import com.blog.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author a1387
 * @description 针对表【menu(菜单权限表)】的数据库操作Service实现
 * @createDate 2023-02-23 12:58:53
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
        implements MenuService {

    @Resource
    MenuMapper menuMapper;

    @Override
    public List<String> getPermissions(String userId) {
        return menuMapper.getPermissions(userId);
    }
}





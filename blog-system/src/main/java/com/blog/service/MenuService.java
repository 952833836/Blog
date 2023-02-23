package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.pojo.entity.Menu;

import java.util.List;

/**
 * @author a1387
 * @description 针对表【menu(菜单权限表)】的数据库操作Service
 * @createDate 2023-02-23 12:58:53
 */
public interface MenuService extends IService<Menu> {
    /**
     * 获得权限
     *
     * @param userId 用户id
     * @return {@link List}<{@link String}>
     */
    List<String> getPermissions(String userId);
}

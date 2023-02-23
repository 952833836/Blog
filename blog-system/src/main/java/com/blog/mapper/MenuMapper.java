package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.pojo.entity.Menu;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author a1387
 * @description 针对表【menu(菜单权限表)】的数据库操作Mapper
 * @createDate 2023-02-23 12:58:53
 * @Entity com.blog.pojo.entity.Menu
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 获得权限
     *
     * @param userId 用户id
     * @return {@link List}<{@link String}>
     */
    @Select("SELECT perms FROM user_role ur LEFT JOIN role r ON ur.role_id=r.id LEFT JOIN role_menu rm ON r.id=rm.role_id LEFT JOIN menu m ON rm.menu_id=m.id WHERE ur.user_id=#{userId}")
    List<String> getPermissions(@Param("userId") String userId);
}





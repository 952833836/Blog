package com.blog.pojo.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.blog.pojo.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 登录用户
 *
 * @author a1387
 * @date 2023/02/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"authorities", "password", "username", "accountNonExpired", "accountNonLocked", "credentialsNonExpired", "enabled"})
public class LoginUser implements UserDetails {

    /**
     * 用户
     */
    private User user;

    /**
     * 权限列表
     */
    private List<String> permissions;

    @JSONField(serialize = false)
    private List<GrantedAuthority> grantedAuthorities;

    public LoginUser(User user, List<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (grantedAuthorities != null) {
            return grantedAuthorities;
        }
        return permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        Integer status = user.getStatus();
        return status == 0;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

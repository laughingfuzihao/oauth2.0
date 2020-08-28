package com.laughing.uaa.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Fu zihao
 * @version 1.0
 * @Description:
 * @date 20202020/8/7 9:28
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Component
public class User implements UserDetails {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String phone;
    /**
     * 账户权限
     */
    @TableField(exist = false)
    // 注解加载bean属性上，表示当前属性不是数据库的字段
    private List<Role> roles;
    /**
     * 账户过期
     */
    private boolean accountNonExpired;

    /**
     * 账户锁定
     *
     * @return
     */
    private boolean accountNonLocked;
    /**
     * 密码过期
     *
     * @return
     */

    public boolean credentialsNonExpired;
    /**
     * 账户可用
     *
     * @return
     */
    public boolean enabled;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * 返回用户角色
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return authorities;
    }

    /**
     * 账户过期
     *
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    /**
     * 账户锁定
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    /**
     * 密码过期
     *
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    /**
     * 账户可用
     *
     * @return
     */
    @Override
    public boolean isEnabled() {
        return enabled;
    }

}

package com.laughing.uaa.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.laughing.uaa.dao.Role;
import com.laughing.uaa.dao.User;
import com.laughing.uaa.dao.UserRoles;
import com.laughing.uaa.mapper.RoleMapper;
import com.laughing.uaa.mapper.UserMapper;
import com.laughing.uaa.mapper.UserRolesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Fu zihao
 * @version 1.0
 * @Description:
 * @date 20202020/8/28 12:46
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRolesMapper userRolesMapper;

    @Autowired
    private User user;

    @Autowired
    private UserRoles userRoles;

    public User findByUserName(String name) {
        // 查用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", name);
        user = userMapper.selectOne(wrapper);
        if (userMapper.selectOne(wrapper) == null) {
            // 用户不存在
            return null;

        } else {
            // 查关系表
            QueryWrapper<UserRoles> wrapper2 = new QueryWrapper<>();
            wrapper2.eq("user_id", user.getId());
            List<UserRoles> userRolesList = userRolesMapper.selectList(wrapper2);
            // 遍历关系表查权限
            List<Role> roles = new ArrayList<>();
            for (int i = 0; i < userRolesList.size(); i++) {
                Role role = roleMapper.selectById(userRolesList.get(i).getRoleId());
                roles.add(role);
            }
            user.setRoles(roles);
            return user;
        }
    }

    /**
     * 校验注册用户已存在
     * @param username
     * @return
     */
    public int checkRegisterUser(String username){
        // 查用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        user = userMapper.selectOne(wrapper);
        if (userMapper.selectOne(wrapper) == null) {
            // 用户不存在
            return 0;

        }else {
            return 1;
        }
    }
    /**
     * 校验手机号已被注册
     */
    public int checkRegisterPhone(String phone){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("phone", phone);
        user = userMapper.selectOne(wrapper);
        if (userMapper.selectOne(wrapper) == null) {
            // 用户不存在
            return 0;

        }else {
            return 1;
        }
    }

    /**
     * 用户注册
     * @param username
     * @return
     */
    public int registerUser(String username,String password,String phone){
        User user = new User();
        user.setUsername(username);
        user.setPhone(phone);
        user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        return userMapper.insert(user);
    }
    /**
     * 用户权限设置
     * @param
     * @return
     */
    public void userRole(String username){
        UserRoles userRoles = new UserRoles();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        userRoles.setRoleId(1); // 默认是admin
        userRoles.setUserId(userMapper.selectOne(wrapper).getId());
        userRolesMapper.insert(userRoles);
    }



}

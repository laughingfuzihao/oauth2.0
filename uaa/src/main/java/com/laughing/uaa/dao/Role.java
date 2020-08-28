package com.laughing.uaa.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Fu zihao
 * @version 1.0
 * @Description: 角色表
 * @date 20202020/8/7 13:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class Role {
    /**
     * 角色id
     */
    private int id;
    /**
     * 角色名称
     */
    private String role;
}

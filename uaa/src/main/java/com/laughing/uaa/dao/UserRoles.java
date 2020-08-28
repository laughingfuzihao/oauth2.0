package com.laughing.uaa.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Fu zihao
 * @version 1.0
 * @Description:
 * @date 20202020/8/7 14:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Component
public class UserRoles {
    private int roleId;
    private int userId;
}

package com.laughing.uaa.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Fu zihao
 * @version 1.0
 * @Description:
 * @date 20202020/8/10 12:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class PhoneCode {
    /**
     * id
     */
    private int id;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 验证码
     */
    private String code;
    /**
     * 发送时间
     */
    private Date time;
}

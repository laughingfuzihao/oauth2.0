package com.laughing.orderService.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Fu zihao
 * @version 1.0
 * @Description:
 * @date 20202020/8/31 9:36
 */
@RestController
public class OrderController {

    @GetMapping("/order")
    public String Order(){
        return "order订单服务访问！";

    }
}

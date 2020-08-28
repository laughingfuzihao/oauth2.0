package com.laughing.order.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Fu zihao
 * @version 1.0
 * @Description: 资源服务
 * @date 20202020/8/28 15:1
 */
@RestController
public class OrderController {

    @GetMapping("/order")
    // 拥有admin
  /*  @PreAuthorize("hasAnyAuthority('admin')")*/
    public String order(){
        return "获取order资源";
    }


}

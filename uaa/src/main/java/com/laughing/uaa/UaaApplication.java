package com.laughing.uaa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Fu zihao
 * @version 1.0
 * @Description:
 * @date 20202020/8/28 10:17
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.laughing.uaa.mapper")
public class UaaApplication {
    public static void main(String[] args) {
        SpringApplication.run(UaaApplication.class, args);
    }
}

package com.laughing.uaa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * @author Fu zihao
 * @version 1.0
 * @Description: Token配置
 * @date 20202020/8/28 11:05
 */

@Configuration
public class TokenConfig {

    /**
     * 令牌存储策略（InMemory内存方式）普通令牌
     * @return
     */
    @Bean
    public TokenStore tokenStore(){
        return new InMemoryTokenStore();
    }
}

package com.laughing.zuul.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author Fu zihao
 * @version 1.0
 * @Description: Token配置
 * @date 20202020/8/28 11:05
 */

@Configuration
public class TokenConfig {
    // 秘钥
    public static final String SIGNING_KEY = "uaa-d8c33b0bd908";

    /**
     * 令牌存储策略（InMemory内存方式）普通令牌
     *
     * @return
     */
/*    @Bean
    public TokenStore tokenStore(){
        return new InMemoryTokenStore();
    }
    */
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    /**
     * JWT秘钥算法
     * @return
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(SIGNING_KEY);
        return converter;

    }
}

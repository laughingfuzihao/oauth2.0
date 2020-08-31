package com.laughing.zuul.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;


/**
 * @author Fu zihao
 * @version 1.0
 * @Description:
 * @date 20202020/8/28 15:19
 */
@Configuration
public class ResourceConfig   {

    public static final String RESOURCE_ID = "res1";
    // 对称秘钥
    public static final String SIGNING_KEY = "uaa-d8c33b0bd908";


    /**
     * UAA访问配置
     */
    @Configuration
    @EnableResourceServer // 声明资源服务
    public class uaaConfig extends ResourceServerConfigurerAdapter {
        @Autowired
        private TokenStore tokenStore;
        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            resources.resourceId(RESOURCE_ID)
                    .tokenStore(tokenStore)
                    .stateless(true);
        }

        /**
         * 全部放行
         * @param http
         * @throws Exception
         */
        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/oauth2.0-uaa/**").permitAll()
                    .and().csrf().disable();
        }


    }

    /**
     * order资源访问配置
     */
    @Configuration
    @EnableResourceServer // 声明资源服务
    public class orderConfig extends ResourceServerConfigurerAdapter {
        @Autowired
        private TokenStore tokenStore;
        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            resources.resourceId(RESOURCE_ID)
                    .tokenStore(tokenStore)
                    .stateless(true);
        }

        /**
         * 全部放行
         * @param http
         * @throws Exception
         */
        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/oauth2.0-order/**").access("#oauth2.hasAnyScope('all')");
        }

    }

}

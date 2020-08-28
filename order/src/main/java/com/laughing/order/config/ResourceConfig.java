package com.laughing.order.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;


/**
 * @author Fu zihao
 * @version 1.0
 * @Description:
 * @date 20202020/8/28 15:19
 */
@Configuration
@EnableResourceServer // 声明资源服务
public class ResourceConfig extends ResourceServerConfigurerAdapter {

    public static final String RESOURCE_ID = "res1";
    // 对称秘钥
    public static final String SIGNING_KEY = "uaa-d8c33b0bd908";

    @Autowired
    private TokenStore tokenStore;

    /**
     * 资源配置
     *
     * @param resources
     * @throws Exception
     */
/*    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_ID)
                //验证令牌的服务
                .tokenServices(tokenServices())
                .stateless(true);

    }*/

    /**
     * WEB安全配置
     *
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**").access("#oauth2.hasAnyScope('all')")
                .and().csrf().disable()
                // 不用session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    /**
     * 令牌验证服务
     * 远程验证
     *
     * @return
     */
/*    @Bean
    public ResourceServerTokenServices tokenServices() {
        RemoteTokenServices tokenServices = new RemoteTokenServices();
        tokenServices.setCheckTokenEndpointUrl("http://localhost:8083/oauth/check_token");
        tokenServices.setClientId("client1");
        tokenServices.setClientSecret("123456");
        return tokenServices;

    }*/

    /**
     * 资源配置JWT模式
     * @param resources
     * @throws Exception
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_ID)
                //验证令牌的服务
                .tokenStore(tokenStore)
                .stateless(true);

    }

}

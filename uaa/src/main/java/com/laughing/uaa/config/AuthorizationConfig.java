package com.laughing.uaa.config;

import com.laughing.uaa.service.MyPasswordEncoderService;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @author Fu zihao
 * @version 1.0
 * @Description: OAuth2.0授权服务器配置
 * @date 2020/8/28 10:48
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private MyPasswordEncoderService myPasswordEncoder;

    // 令牌存储配置
    @Autowired
    private TokenStore tokenStore;
    // 客户端服务信息
    @Autowired
    private ClientDetailsService clientDetailsService;
    // 授权码服务
    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;
    // 认证管理器
    @Autowired
    private AuthenticationManager authenticationManager;


    // 授权码服务
    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new InMemoryAuthorizationCodeServices();
    }


    /**
     * 配置客户端信息 调用数据库查客户端信息
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                // clientid
                .withClient("client1")
                // secret
                .secret(myPasswordEncoder.encode("123456"))
                // 可访问的资源列表
                .resourceIds("res1")
                // 授权方式
                .authorizedGrantTypes("authorization_code", "password", "implicit", "refresh_token", "client_credentials")
                // 授权作用域
                .scopes("all")  // read
                .autoApprove(false)  // 跳转授权页面
                // 回调地址
                .redirectUris("http://laughing-blog.cn/");
    }


    // 令牌访问服务
    @Bean
    public AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        // 客户端信息服务
        tokenServices.setClientDetailsService(clientDetailsService);
        // 刷新令牌
        tokenServices.setSupportRefreshToken(true);
        // 令牌存储配置
        tokenServices.setTokenStore(tokenStore);
        // 令牌有效时间2小时
        tokenServices.setAccessTokenValiditySeconds(7200);
        // 令牌刷新时间3天
        tokenServices.setRefreshTokenValiditySeconds(259200);
        return tokenServices;
    }


    /**
     * 配置令牌访问端点（获取令牌的url）
     * 配置令牌服务
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        endpoints
                // 认证管理器 密码模式使用
                .authenticationManager(authenticationManager)
                // 授权码模式
                .authorizationCodeServices(authorizationCodeServices)
                // 令牌访问服务
                .tokenServices(tokenServices())
                // 运行提交方式
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);


    }

    /**
     * 令牌端点的安全约束
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {

        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                // 允许表单认证
                .allowFormAuthenticationForClients();

    }
}

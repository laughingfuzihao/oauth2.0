package com.laughing.uaa.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Fu zihao
 * @version 1.0
 * @Description:
 * @date 20202020/8/28 16:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OauthClientDetails implements ClientDetails {
    /**
     * 客户端ID
     */
    private String clientId;
    /**
     * 资源ID
     */
    private String resourceIds;
    /**
     * 客户端秘钥
     */
    private String clientSecret;
    /**
     * 作用域
     */
    private String scope;
    /**
     * 授权模式
     */
    private String authorizedGrantTypes;
    /**
     * 回调地址
     */
    private String webServerRedirectUri;

    private String authorities;
    /**
     * accessToken
     */
    private int accessTokenValidity;
    /**
     * refreshToken
     */
    private int refreshTokenValidity;
    private String additionalInformation;
    private boolean autoapprove;

    @Override
    public String getClientId() {
        return clientId;
    }

    @Override
    public boolean isSecretRequired() {
        return false;
    }

    @Override
    public boolean isScoped() {
        return false;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        Set<String> set = new HashSet<String>();
        set.add(webServerRedirectUri);
        return set;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return accessTokenValidity;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValidity;
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return autoapprove;
    }
}

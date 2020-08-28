package com.laughing.uaa.service;

import com.laughing.uaa.dao.OauthClientDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;

/**
 * @author Fu zihao
 * @version 1.0
 * @Description:
 * @date 20202020/8/28 16:43
 */
public class MyClientDetailsService implements ClientDetailsService {
    @Autowired
    private ClientService clientService;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        OauthClientDetails oauthClientDetails = clientService.getAllClientDetails(clientId);
        if(oauthClientDetails ==null){
            throw new NoSuchClientException("客户端不存在");
        }
        return oauthClientDetails;
    }
}

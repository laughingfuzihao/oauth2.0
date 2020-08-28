package com.laughing.uaa.service;

import com.laughing.uaa.dao.OauthClientDetails;
import com.laughing.uaa.mapper.OauthClientDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Fu zihao
 * @version 1.0
 * @Description:
 * @date 20202020/8/28 16:52
 */
@Service
public class ClientService {
    @Autowired
    private OauthClientDetailsMapper oauthClientDetailsMapper;

    /**
     * 查询ClientDetails
     * @param clientId
     * @return
     */
    public OauthClientDetails getAllClientDetails(String clientId){
       return oauthClientDetailsMapper.selectById(clientId);
    }
}

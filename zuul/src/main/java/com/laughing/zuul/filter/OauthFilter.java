package com.laughing.zuul.filter;

import com.alibaba.druid.support.json.JSONUtils;
import com.baomidou.mybatisplus.core.toolkit.EncryptUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.boot.actuate.endpoint.SecurityContext;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.stereotype.Component;

import java.security.Security;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Fu zihao
 * @version 1.0
 * @Description:
 * @date 20202020/8/31 10:31
 */
@Component
public class OauthFilter extends ZuulFilter {
    /**
     * 过滤器类型
     *
     * @return
     */
    @Override
    public String filterType() {
        // 前置过滤器
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 指定过滤顺序
     *
     * @return
     */
    @Override
    public int filterOrder() {
        // 数值越小 执行越前
        // return FilterConstants.PRE_DECORATION_FILTER_ORDER - 1;
        return 0;
    }

    /**
     * 是否开启
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 业务类型
     *
     * @return
     * @throws ZuulException
     */

    @Override
    public Object run() throws ZuulException {
        System.out.println("OauthFilter-过滤器执行-解析转发Token");
        RequestContext requestContext = RequestContext.getCurrentContext();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof OAuth2Authentication)) {
            return null;
        }
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
        Authentication userAuthentication = oAuth2Authentication.getUserAuthentication();
        // 获取用户身份信息
        String principal = userAuthentication.getName();
        // 获取用户权限信息
        List<String> authorities = new ArrayList<>();
        userAuthentication.getAuthorities().stream().forEach(c -> authorities.add(((GrantedAuthority) c).getAuthority()));
        // 其他参数
        OAuth2Request oAuth2Request = oAuth2Authentication.getOAuth2Request();
        Map<String, String> oAuth2RequestParameters = oAuth2Request.getRequestParameters();
        // json组装
        Map<String, Object> jsonToken = new HashMap<>(oAuth2RequestParameters);
        if (userAuthentication != null) {
            jsonToken.put("principal", principal);
            jsonToken.put("authorities", authorities);
        }

        // 放入header 转发给order服务
        requestContext.addZuulRequestHeader("json-token", JSONUtils.toJSONString(jsonToken));
        return null;

    }
}

package org.okboom.wukong.auth.service;

import org.okboom.wukong.auth.constant.CacheConstants;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;

/**
 * @author tookbra
 */
public class ClientDetailsService extends JdbcClientDetailsService {

    public ClientDetailsService(DataSource dataSource) {
        super(dataSource);
    }

    /**
     * 从缓存取
     * @param clientId
     * @return
     */
    @Override
    @Cacheable(value = CacheConstants.CLIENT_DETAILS_KEY, key = "#clientId", unless = "#result == null")
    public ClientDetails loadClientByClientId(String clientId) {
        return super.loadClientByClientId(clientId);
    }
}

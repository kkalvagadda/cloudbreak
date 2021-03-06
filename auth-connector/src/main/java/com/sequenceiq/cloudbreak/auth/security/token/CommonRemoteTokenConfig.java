package com.sequenceiq.cloudbreak.auth.security.token;

import javax.inject.Inject;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

import com.sequenceiq.cloudbreak.auth.altus.GrpcUmsClient;

@Configuration
public class CommonRemoteTokenConfig {

    @Inject
    private GrpcUmsClient umsClient;

    @Bean
    @ConditionalOnMissingBean(ResourceServerTokenServices.class)
    public ResourceServerTokenServices remoteTokenServices() {
        return new CommonCachedRemoteTokenService(umsClient);
    }
}

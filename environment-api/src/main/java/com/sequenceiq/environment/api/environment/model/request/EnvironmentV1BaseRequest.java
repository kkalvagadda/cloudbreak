package com.sequenceiq.environment.api.environment.model.request;

import java.util.HashSet;
import java.util.Set;

import com.sequenceiq.environment.api.environment.doc.EnvironmentModelDescription;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public abstract class EnvironmentV1BaseRequest {

    @ApiModelProperty(EnvironmentModelDescription.PROXY_CONFIGS_REQUEST)
    private Set<String> proxies = new HashSet<>();

    @ApiModelProperty(EnvironmentModelDescription.PROXY_CONFIGS_REQUEST)
    private Set<String> ldaps = new HashSet<>();

    public Set<String> getProxies() {
        return proxies;
    }

    public void setProxies(Set<String> proxies) {
        this.proxies = proxies == null ? new HashSet<>() : proxies;
    }

    public Set<String> getLdaps() {
        return ldaps;
    }

    public void setLdaps(Set<String> ldaps) {
        this.ldaps = ldaps == null ? new HashSet<>() : ldaps;
    }
}

package com.sequenceiq.environment.api.environment.responses;

import java.util.HashSet;
import java.util.Set;

import com.sequenceiq.environment.api.doc.ModelDescriptions.EnvironmentResponseModelDescription;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class DetailedEnvironmentResponse extends EnvironmentBaseResponse {

    @ApiModelProperty(EnvironmentResponseModelDescription.PROXY_CONFIGS)
    private Set<Long> proxies = new HashSet<>();

    @ApiModelProperty(EnvironmentResponseModelDescription.LDAP_CONFIGS)
    private Set<Long> ldaps = new HashSet<>();

    @ApiModelProperty(EnvironmentResponseModelDescription.RDS_CONFIGS)
    private Set<Long> databases = new HashSet<>();

    @ApiModelProperty(EnvironmentResponseModelDescription.KUBERNETES_CONFIGS)
    private Set<Long> kubernetes = new HashSet<>();

    @ApiModelProperty(EnvironmentResponseModelDescription.KERBEROS_CONFIGS)
    private Set<Long> kerberoses = new HashSet<>();

    @ApiModelProperty(EnvironmentResponseModelDescription.SDX_RESOURCES)
    private Set<SdxResourcesResponse> datalakeResourcesResponses;

    @ApiModelProperty(EnvironmentResponseModelDescription.WORKLOAD_CLUSTERS)
    private Set<Long> workloadClusters = new HashSet<>();

    @ApiModelProperty(EnvironmentResponseModelDescription.SDX_CLUSTERS)
    private Set<Long> sdxClusters = new HashSet<>();

    public Set<Long> getProxies() {
        return proxies;
    }

    public void setProxies(Set<Long> proxies) {
        this.proxies = proxies;
    }

    public Set<Long> getLdaps() {
        return ldaps;
    }

    public void setLdaps(Set<Long> ldaps) {
        this.ldaps = ldaps;
    }

    public Set<Long> getDatabases() {
        return databases;
    }

    public void setDatabases(Set<Long> databases) {
        this.databases = databases;
    }

    public Set<Long> getKubernetes() {
        return kubernetes;
    }

    public void setKubernetes(Set<Long> kubernetes) {
        this.kubernetes = kubernetes;
    }

    public Set<SdxResourcesResponse> getDatalakeResources() {
        return datalakeResourcesResponses;
    }

    public void setDatalakeResources(Set<SdxResourcesResponse> datalakeResources) {
        datalakeResourcesResponses = datalakeResources;
    }

    public Set<Long> getWorkloadClusters() {
        return workloadClusters;
    }

    public void setWorkloadClusters(Set<Long> workloadClusters) {
        this.workloadClusters = workloadClusters;
    }

    public Set<Long> getSdxClusters() {
        return sdxClusters;
    }

    public void setSdxClusters(Set<Long> sdxClusters) {
        this.sdxClusters = sdxClusters;
    }

    public Set<Long> getKerberoses() {
        return kerberoses;
    }

    public void setKerberoses(Set<Long> kerberoses) {
        this.kerberoses = kerberoses;
    }

}

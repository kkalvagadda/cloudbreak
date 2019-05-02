package com.sequenceiq.environment.api.environment.responses;

import java.util.Map;
import java.util.Set;

import com.sequenceiq.environment.api.doc.ModelDescriptions.ClusterModelDescription;
import com.sequenceiq.environment.api.doc.ModelDescriptions.DatalakeResourcesDescription;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class SdxResourcesResponse {

    @ApiModelProperty(ClusterModelDescription.AMBARI_URL)
    private String ambariUrl;

    @ApiModelProperty(ClusterModelDescription.LDAP_CONFIG_NAME)
    private String ldapName;

    @ApiModelProperty(ClusterModelDescription.RDSCONFIG_NAMES)
    private Set<String> databaseNames;

    @ApiModelProperty(ClusterModelDescription.KERBEROSCONFIG_NAME)
    private String kerberosName;

    @ApiModelProperty(DatalakeResourcesDescription.SERVICE_DESCRIPTORS)
    private Map<String, ServiceDescriptorResponse> serviceDescriptorMap;

    public String getAmbariUrl() {
        return ambariUrl;
    }

    public void setAmbariUrl(String ambariUrl) {
        this.ambariUrl = ambariUrl;
    }

    public String getLdapName() {
        return ldapName;
    }

    public void setLdapName(String ldapName) {
        this.ldapName = ldapName;
    }

    public Set<String> getDatabaseNames() {
        return databaseNames;
    }

    public void setDatabaseNames(Set<String> databaseNames) {
        this.databaseNames = databaseNames;
    }

    public String getKerberosName() {
        return kerberosName;
    }

    public void setKerberosName(String kerberosName) {
        this.kerberosName = kerberosName;
    }

    public Map<String, ServiceDescriptorResponse> getServiceDescriptorMap() {
        return serviceDescriptorMap;
    }

    public void setServiceDescriptorMap(Map<String, ServiceDescriptorResponse> serviceDescriptorMap) {
        this.serviceDescriptorMap = serviceDescriptorMap;
    }

}

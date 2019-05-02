package com.sequenceiq.environment.api.environment.responses;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sequenceiq.environment.api.doc.ModelDescriptions.KerberosConfigModelDescription;
import com.sequenceiq.environment.api.doc.ModelDescriptions.LdapConfigModelDescription;
import com.sequenceiq.environment.api.doc.ModelDescriptions.RDSConfigModelDescription;

import io.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class SdxPrerequisiteResponse {

    @NotNull
    @ApiModelProperty(LdapConfigModelDescription.RESPONSE)
    private Long ldap;

    @ApiModelProperty(RDSConfigModelDescription.RESPONSE)
    private Set<Long> databases = new HashSet<>();

    @NotNull
    @ApiModelProperty(KerberosConfigModelDescription.RESPONSE)
    private Long kerberos;

    public Long getLdap() {
        return ldap;
    }

    public void setLdap(Long ldap) {
        this.ldap = ldap;
    }

    public Set<Long> getDatabases() {
        return databases;
    }

    public void setDatabases(Set<Long> databases) {
        this.databases = databases;
    }

    public Long getKerberos() {
        return kerberos;
    }

    public void setKerberos(Long kerberos) {
        this.kerberos = kerberos;
    }

}

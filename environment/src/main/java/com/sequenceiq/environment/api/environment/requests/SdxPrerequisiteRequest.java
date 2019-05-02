package com.sequenceiq.environment.api.environment.requests;

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
public class SdxPrerequisiteRequest {

    @NotNull
    @ApiModelProperty(value = LdapConfigModelDescription.REQUEST, required = true)
    private Long ldap;

    @ApiModelProperty(value = RDSConfigModelDescription.REQUEST, required = true)
    private Set<Long> databases = new HashSet<>();

    @NotNull
    @ApiModelProperty(value = KerberosConfigModelDescription.REQUEST, required = true)
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
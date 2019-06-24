package com.sequenceiq.freeipa.api.v1.keytab.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sequenceiq.freeipa.api.v1.keytab.doc.KeytabModelDescription;
import com.sequenceiq.service.api.doc.ModelDescriptions;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("UserKeytabV1Request")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserKeytabRequest {

    @ApiModelProperty(KeytabModelDescription.ID)
    private Long id;

    @ApiModelProperty(value = ModelDescriptions.ENVIRONMENT_CRN, required = true)
    @NotNull
    private String environmentCrn;

    @ApiModelProperty(value = KeytabModelDescription.USER_NAME, required = true)
    @NotNull
    private String userName;

    @ApiModelProperty(value = KeytabModelDescription.USER_HOST, required = true)
    @NotNull
    private String hostName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnvironmentCrn() {
        return environmentCrn;
    }

    public void setEnvironmentCrn(String environmentCrn) {
        this.environmentCrn = environmentCrn;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String serviceName) {
        this.userName = serviceName;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

}

package com.sequenceiq.freeipa.api.v1.freeipa.user.model;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sequenceiq.freeipa.api.v1.freeipa.user.doc.UserModelDescriptions;
import com.sequenceiq.service.api.doc.ModelDescriptions;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("CreateUsersV1Request")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateUsersRequest {
    @NotNull
    @ApiModelProperty(value = ModelDescriptions.ENVIRONMENT_CRN, required = true)
    private String environmentCrn;

    @ApiModelProperty(value = UserModelDescriptions.USERCREATE_GROUPS)
    private Set<Group> groups = new HashSet<>();

    @ApiModelProperty(value = UserModelDescriptions.USERCREATE_USERS)
    private Set<User> users = new HashSet<>();

    public String getEnvironmentCrn() {
        return environmentCrn;
    }

    public void setEnvironmentCrn(String environmentCrn) {
        this.environmentCrn = environmentCrn;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public Set<User> getUsers() {
        return users;
    }
}

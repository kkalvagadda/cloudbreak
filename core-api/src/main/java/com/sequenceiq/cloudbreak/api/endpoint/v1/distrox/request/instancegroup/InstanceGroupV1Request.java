package com.sequenceiq.cloudbreak.api.endpoint.v1.distrox.request.instancegroup;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sequenceiq.cloudbreak.api.endpoint.v1.distrox.request.instancegroup.securitygroup.SecurityGroupV1Request;
import com.sequenceiq.cloudbreak.api.endpoint.v1.distrox.request.instancegroup.template.InstanceTemplateV1Request;
import com.sequenceiq.cloudbreak.api.endpoint.v4.stacks.base.InstanceGroupV4Base;
import com.sequenceiq.cloudbreak.api.endpoint.v4.stacks.base.RecoveryMode;
import com.sequenceiq.cloudbreak.doc.ModelDescriptions.HostGroupModelDescription;
import com.sequenceiq.cloudbreak.doc.ModelDescriptions.InstanceGroupModelDescription;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class InstanceGroupV1Request extends InstanceGroupV4Base {

    @NotNull
    @ApiModelProperty(InstanceGroupModelDescription.TEMPLATE)
    private InstanceTemplateV1Request template;

    @Valid
    @ApiModelProperty(InstanceGroupModelDescription.SECURITYGROUP)
    private SecurityGroupV1Request securityGroup;

    @ApiModelProperty(HostGroupModelDescription.RECIPE_NAMES)
    private Set<String> recipeNames = new HashSet<>();

    @ApiModelProperty(value = HostGroupModelDescription.RECOVERY_MODE, allowableValues = "MANUAL,AUTO")
    private RecoveryMode recoveryMode = RecoveryMode.MANUAL;

    public InstanceTemplateV1Request getTemplate() {
        return template;
    }

    public void setTemplate(InstanceTemplateV1Request template) {
        this.template = template;
    }

    public SecurityGroupV1Request getSecurityGroup() {
        return securityGroup;
    }

    public void setSecurityGroup(SecurityGroupV1Request securityGroup) {
        this.securityGroup = securityGroup;
    }

    public Set<String> getRecipeNames() {
        return recipeNames;
    }

    public void setRecipeNames(Set<String> recipeNames) {
        this.recipeNames = recipeNames;
    }

    public RecoveryMode getRecoveryMode() {
        return recoveryMode;
    }

    public void setRecoveryMode(RecoveryMode recoveryMode) {
        this.recoveryMode = recoveryMode;
    }
}

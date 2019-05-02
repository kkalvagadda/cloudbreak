package com.sequenceiq.environment.api.environment.requests;

import java.util.Set;

import com.sequenceiq.environment.api.doc.ModelDescriptions.EnvironmentNetworkDescription;
import com.sequenceiq.environment.api.environment.base.EnvironmentNetworkAwsParams;
import com.sequenceiq.environment.api.environment.base.EnvironmentNetworkAzureParams;

import io.swagger.annotations.ApiModelProperty;

public class EnvironmentNetworkRequest {

    @ApiModelProperty(value = EnvironmentNetworkDescription.SUBNET_IDS, required = true)
    private Set<String> subnetIds;

    @ApiModelProperty(value = EnvironmentNetworkDescription.AWS_SPECIFIC_PARAMETERS, required = true)
    private EnvironmentNetworkAwsParams aws;

    @ApiModelProperty(value = EnvironmentNetworkDescription.AZURE_SPECIFIC_PARAMETERS, required = true)
    private EnvironmentNetworkAzureParams azure;

    public Set<String> getSubnetIds() {
        return subnetIds;
    }

    public void setSubnetIds(Set<String> subnetIds) {
        this.subnetIds = subnetIds;
    }

    public EnvironmentNetworkAwsParams getAws() {
        return aws;
    }

    public void setAws(EnvironmentNetworkAwsParams aws) {
        this.aws = aws;
    }

    public EnvironmentNetworkAzureParams getAzure() {
        return azure;
    }

    public void setAzure(EnvironmentNetworkAzureParams azure) {
        this.azure = azure;
    }

}

package com.sequenceiq.cloudbreak.api.endpoint.v1.distrox.base.parameter.instancegroup;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sequenceiq.cloudbreak.api.endpoint.v4.common.mappable.CloudPlatform;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class AzureInstanceGroupV1Parameters extends InstanceGroupV1ParametersBase {

    @ApiModelProperty
    private AzureAvailabiltySetV1 availabilitySet;

    public AzureAvailabiltySetV1 getAvailabilitySet() {
        return availabilitySet;
    }

    public void setAvailabilitySet(AzureAvailabiltySetV1 availabilitySet) {
        this.availabilitySet = availabilitySet;
    }

    @Override
    public Map<String, Object> asMap() {
        Map<String, Object> map = super.asMap();
        if (availabilitySet != null) {
            putIfValueNotNull(map, "faultDomainCount", availabilitySet.getFaultDomainCount());
            putIfValueNotNull(map, "name", availabilitySet.getName());
            putIfValueNotNull(map, "updateDomainCount", availabilitySet.getUpdateDomainCount());
        }
        return map;
    }

    @Override
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public CloudPlatform getCloudPlatform() {
        return CloudPlatform.AZURE;
    }

    @Override
    public void parse(Map<String, Object> parameters) {
        super.parse(parameters);
        AzureAvailabiltySetV1 availabiltySet = new AzureAvailabiltySetV1();
        availabiltySet.setFaultDomainCount(getInt(parameters, "faultDomainCount"));
        availabiltySet.setName(getParameterOrNull(parameters, "name"));
        availabiltySet.setUpdateDomainCount(getInt(parameters, "updateDomainCount"));
        if (availabiltySet.getFaultDomainCount() != null
                || availabiltySet.getName() != null
                || availabiltySet.getUpdateDomainCount() != null) {
            availabilitySet = availabiltySet;
        }
    }
}

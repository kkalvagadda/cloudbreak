package com.sequenceiq.environment.api.environment.responses;

import com.sequenceiq.environment.api.doc.ModelDescriptions;
import com.sequenceiq.environment.api.environment.requests.EnvironmentNetworkRequest;

import io.swagger.annotations.ApiModelProperty;

public class EnvironmentNetworkResponse extends EnvironmentNetworkRequest {

    @ApiModelProperty(ModelDescriptions.ID)
    private Long id;

    @ApiModelProperty(value = ModelDescriptions.NAME, required = true)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

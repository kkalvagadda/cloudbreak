package com.sequenceiq.environment.api.environment.responses;

import java.util.Set;

import com.google.common.collect.Sets;
import com.sequenceiq.environment.api.GeneralCollectionV4Response;

import io.swagger.annotations.ApiModel;

@ApiModel
public class SimpleEnvironmentResponses extends GeneralCollectionV4Response<SimpleEnvironmentResponse> {

    public SimpleEnvironmentResponses(Set<SimpleEnvironmentResponse> responses) {
        super(responses);
    }

    public SimpleEnvironmentResponses() {
        super(Sets.newHashSet());
    }

}

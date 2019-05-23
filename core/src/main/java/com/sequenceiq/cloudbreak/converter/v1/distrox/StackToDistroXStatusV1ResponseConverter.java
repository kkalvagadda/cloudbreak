package com.sequenceiq.cloudbreak.converter.v1.distrox;

import org.springframework.stereotype.Component;

import com.sequenceiq.cloudbreak.api.endpoint.v1.distrox.response.DistroXStatusV1Response;
import com.sequenceiq.cloudbreak.converter.AbstractConversionServiceAwareConverter;
import com.sequenceiq.cloudbreak.domain.stack.Stack;

@Component
public class StackToDistroXStatusV1ResponseConverter
        extends AbstractConversionServiceAwareConverter<Stack, DistroXStatusV1Response> {
    @Override
    public DistroXStatusV1Response convert(Stack source) {
        return null;
    }
}

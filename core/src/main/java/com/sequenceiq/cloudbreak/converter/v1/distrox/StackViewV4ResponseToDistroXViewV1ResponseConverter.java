package com.sequenceiq.cloudbreak.converter.v1.distrox;

import org.springframework.stereotype.Component;

import com.sequenceiq.cloudbreak.api.endpoint.v1.distrox.response.DistroXViewV1Response;
import com.sequenceiq.cloudbreak.api.endpoint.v4.stacks.response.StackViewV4Response;
import com.sequenceiq.cloudbreak.converter.AbstractConversionServiceAwareConverter;

@Component
public class StackViewV4ResponseToDistroXViewV1ResponseConverter
        extends AbstractConversionServiceAwareConverter<StackViewV4Response, DistroXViewV1Response> {
    @Override
    public DistroXViewV1Response convert(StackViewV4Response source) {
        return null;
    }
}

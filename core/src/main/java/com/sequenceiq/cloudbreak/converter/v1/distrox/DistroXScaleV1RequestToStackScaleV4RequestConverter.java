package com.sequenceiq.cloudbreak.converter.v1.distrox;

import org.springframework.stereotype.Component;

import com.sequenceiq.cloudbreak.api.endpoint.v1.distrox.request.DistroXScaleV1Request;
import com.sequenceiq.cloudbreak.api.endpoint.v4.stacks.request.StackScaleV4Request;
import com.sequenceiq.cloudbreak.converter.AbstractConversionServiceAwareConverter;

@Component
public class DistroXScaleV1RequestToStackScaleV4RequestConverter
        extends AbstractConversionServiceAwareConverter<DistroXScaleV1Request, StackScaleV4Request> {
    @Override
    public StackScaleV4Request convert(DistroXScaleV1Request source) {
        return null;
    }
}

package com.sequenceiq.cloudbreak.converter.v1.distrox;

import org.springframework.stereotype.Component;

import com.sequenceiq.cloudbreak.api.endpoint.v1.distrox.request.DistroXV1Request;
import com.sequenceiq.cloudbreak.api.endpoint.v4.stacks.request.StackV4Request;
import com.sequenceiq.cloudbreak.converter.AbstractConversionServiceAwareConverter;

@Component
public class DistroXV1RequestToStackV4RequestConverter
        extends AbstractConversionServiceAwareConverter<DistroXV1Request, StackV4Request> {

    @Override
    public StackV4Request convert(DistroXV1Request source) {
        return null;
    }
}

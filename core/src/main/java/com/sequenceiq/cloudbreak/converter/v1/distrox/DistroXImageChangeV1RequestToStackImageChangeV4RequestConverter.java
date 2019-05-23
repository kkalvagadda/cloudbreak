package com.sequenceiq.cloudbreak.converter.v1.distrox;

import org.springframework.stereotype.Component;

import com.sequenceiq.cloudbreak.api.endpoint.v1.distrox.request.DistroXImageChangeV1Request;
import com.sequenceiq.cloudbreak.api.endpoint.v4.stacks.request.StackImageChangeV4Request;
import com.sequenceiq.cloudbreak.converter.AbstractConversionServiceAwareConverter;

@Component
public class DistroXImageChangeV1RequestToStackImageChangeV4RequestConverter
        extends AbstractConversionServiceAwareConverter<DistroXImageChangeV1Request, StackImageChangeV4Request> {
    @Override
    public StackImageChangeV4Request convert(DistroXImageChangeV1Request source) {
        return null;
    }
}

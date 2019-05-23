package com.sequenceiq.cloudbreak.converter.v1.distrox;

import org.springframework.stereotype.Component;

import com.sequenceiq.cloudbreak.api.endpoint.v1.distrox.request.UpdateDistroXV1Request;
import com.sequenceiq.cloudbreak.api.endpoint.v4.stacks.request.UpdateClusterV4Request;
import com.sequenceiq.cloudbreak.converter.AbstractConversionServiceAwareConverter;

@Component
public class UpdateDistroXV1RequestToUpdateClusterV4RequestConverter
        extends AbstractConversionServiceAwareConverter<UpdateDistroXV1Request, UpdateClusterV4Request> {
    @Override
    public UpdateClusterV4Request convert(UpdateDistroXV1Request source) {
        return null;
    }
}

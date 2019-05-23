package com.sequenceiq.cloudbreak.api.endpoint.v1.distrox.base.parameter.template;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sequenceiq.cloudbreak.api.endpoint.v1.distrox.base.parameter.EncryptionParametersV1Base;

import io.swagger.annotations.ApiModel;

@ApiModel
@JsonIgnoreProperties(ignoreUnknown = true)
public class AwsEncryptionV1Parameters extends EncryptionParametersV1Base {

}

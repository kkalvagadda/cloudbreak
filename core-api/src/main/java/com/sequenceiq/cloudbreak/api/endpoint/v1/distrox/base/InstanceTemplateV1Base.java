package com.sequenceiq.cloudbreak.api.endpoint.v1.distrox.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sequenceiq.cloudbreak.api.endpoint.v1.distrox.base.parameter.template.AwsInstanceTemplateV1Parameters;
import com.sequenceiq.cloudbreak.api.endpoint.v1.distrox.base.parameter.template.AzureInstanceTemplateV1Parameters;
import com.sequenceiq.cloudbreak.api.endpoint.v1.distrox.base.parameter.template.GcpInstanceTemplateV1Parameters;
import com.sequenceiq.cloudbreak.api.endpoint.v1.distrox.base.parameter.template.MockInstanceTemplateV1Parameters;
import com.sequenceiq.cloudbreak.api.endpoint.v1.distrox.base.parameter.template.OpenStackInstanceTemplateV1Parameters;
import com.sequenceiq.cloudbreak.api.endpoint.v1.distrox.base.parameter.template.YarnInstanceTemplateV1Parameters;
import com.sequenceiq.cloudbreak.api.endpoint.v4.JsonEntity;
import com.sequenceiq.cloudbreak.api.endpoint.v4.common.mappable.ProviderParametersBase;
import com.sequenceiq.cloudbreak.doc.ModelDescriptions.TemplateModelDescription;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class InstanceTemplateV1Base extends ProviderParametersBase implements JsonEntity {

    @ApiModelProperty(TemplateModelDescription.AWS_PARAMETERS)
    private AwsInstanceTemplateV1Parameters aws;

    @ApiModelProperty(TemplateModelDescription.AZURE_PARAMETERS)
    private AzureInstanceTemplateV1Parameters azure;

    @ApiModelProperty(TemplateModelDescription.GCP_PARAMETERS)
    private GcpInstanceTemplateV1Parameters gcp;

    @ApiModelProperty(TemplateModelDescription.OPENSTACK_PARAMETERS)
    private OpenStackInstanceTemplateV1Parameters openstack;

    @ApiModelProperty(TemplateModelDescription.YARN_PARAMETERS)
    private YarnInstanceTemplateV1Parameters yarn;

    @ApiModelProperty(TemplateModelDescription.YARN_PARAMETERS)
    private MockInstanceTemplateV1Parameters mock;

    @ApiModelProperty(TemplateModelDescription.INSTANCE_TYPE)
    private String instanceType;

    public String getInstanceType() {
        return instanceType;
    }

    public void setInstanceType(String instanceType) {
        this.instanceType = instanceType;
    }

    @Override
    public AwsInstanceTemplateV1Parameters createAws() {
        if (aws == null) {
            aws = new AwsInstanceTemplateV1Parameters();
        }
        return aws;
    }

    public AwsInstanceTemplateV1Parameters getAws() {
        return aws;
    }

    public void setAws(AwsInstanceTemplateV1Parameters aws) {
        this.aws = aws;
    }

    @Override
    public AzureInstanceTemplateV1Parameters createAzure() {
        if (azure == null) {
            azure = new AzureInstanceTemplateV1Parameters();
        }
        return azure;
    }

    public void setAzure(AzureInstanceTemplateV1Parameters azure) {
        this.azure = azure;
    }

    @Override
    public GcpInstanceTemplateV1Parameters createGcp() {
        if (gcp == null) {
            gcp = new GcpInstanceTemplateV1Parameters();
        }
        return gcp;
    }

    public void setGcp(GcpInstanceTemplateV1Parameters gcp) {
        this.gcp = gcp;
    }

    @Override
    public OpenStackInstanceTemplateV1Parameters createOpenstack() {
        if (openstack == null) {
            openstack = new OpenStackInstanceTemplateV1Parameters();
        }
        return openstack;
    }

    public void setOpenstack(OpenStackInstanceTemplateV1Parameters openstack) {
        this.openstack = openstack;
    }

    @Override
    public YarnInstanceTemplateV1Parameters createYarn() {
        if (yarn == null) {
            yarn = new YarnInstanceTemplateV1Parameters();
        }
        return yarn;
    }

    public void setYarn(YarnInstanceTemplateV1Parameters yarn) {
        this.yarn = yarn;
    }

    @Override
    public MockInstanceTemplateV1Parameters createMock() {
        if (mock == null) {
            mock = new MockInstanceTemplateV1Parameters();
        }
        return mock;
    }

    public void setMock(MockInstanceTemplateV1Parameters mock) {
        this.mock = mock;
    }

    public AzureInstanceTemplateV1Parameters getAzure() {
        return azure;
    }

    public GcpInstanceTemplateV1Parameters getGcp() {
        return gcp;
    }

    public OpenStackInstanceTemplateV1Parameters getOpenstack() {
        return openstack;
    }

    public YarnInstanceTemplateV1Parameters getYarn() {
        return yarn;
    }

    public MockInstanceTemplateV1Parameters getMock() {
        return mock;
    }
}

package com.sequenceiq.cloudbreak.api.endpoint.v1.distrox.base;

import com.sequenceiq.cloudbreak.api.endpoint.v1.distrox.base.parameter.network.AwsNetworkV1Parameters;
import com.sequenceiq.cloudbreak.api.endpoint.v1.distrox.base.parameter.network.AzureNetworkV1Parameters;
import com.sequenceiq.cloudbreak.api.endpoint.v1.distrox.base.parameter.network.GcpNetworkV1Parameters;
import com.sequenceiq.cloudbreak.api.endpoint.v1.distrox.base.parameter.network.MockNetworkV1Parameters;
import com.sequenceiq.cloudbreak.api.endpoint.v1.distrox.base.parameter.network.OpenStackNetworkV1Parameters;
import com.sequenceiq.cloudbreak.api.endpoint.v1.distrox.base.parameter.network.YarnNetworkV1Parameters;
import com.sequenceiq.cloudbreak.api.endpoint.v4.JsonEntity;
import com.sequenceiq.cloudbreak.api.endpoint.v4.common.mappable.ProviderParametersBase;
import com.sequenceiq.cloudbreak.doc.ModelDescriptions.NetworkModelDescription;

import io.swagger.annotations.ApiModelProperty;

public class NetworkV1Base extends ProviderParametersBase implements JsonEntity {

    @ApiModelProperty(NetworkModelDescription.AWS_PARAMETERS)
    private AwsNetworkV1Parameters aws;

    @ApiModelProperty(NetworkModelDescription.GCP_PARAMETERS)
    private GcpNetworkV1Parameters gcp;

    @ApiModelProperty(NetworkModelDescription.AZURE_PARAMETERS)
    private AzureNetworkV1Parameters azure;

    @ApiModelProperty(NetworkModelDescription.OPEN_STACK_PARAMETERS)
    private OpenStackNetworkV1Parameters openstack;

    @ApiModelProperty(hidden = true)
    private MockNetworkV1Parameters mock;

    @ApiModelProperty(hidden = true)
    private YarnNetworkV1Parameters yarn;

    @Override
    public MockNetworkV1Parameters createMock() {
        if (mock == null) {
            mock = new MockNetworkV1Parameters();
        }
        return mock;
    }

    public void setMock(MockNetworkV1Parameters mock) {
        this.mock = mock;
    }

    public AwsNetworkV1Parameters createAws() {
        if (aws == null) {
            aws = new AwsNetworkV1Parameters();
        }
        return aws;
    }

    public void setAws(AwsNetworkV1Parameters aws) {
        this.aws = aws;
    }

    public GcpNetworkV1Parameters createGcp() {
        if (gcp == null) {
            gcp = new GcpNetworkV1Parameters();
        }
        return gcp;
    }

    public void setGcp(GcpNetworkV1Parameters gcp) {
        this.gcp = gcp;
    }

    public AzureNetworkV1Parameters createAzure() {
        if (azure == null) {
            azure = new AzureNetworkV1Parameters();
        }
        return azure;
    }

    public void setAzure(AzureNetworkV1Parameters azure) {
        this.azure = azure;
    }

    public OpenStackNetworkV1Parameters createOpenstack() {
        if (openstack == null) {
            openstack = new OpenStackNetworkV1Parameters();
        }
        return openstack;
    }

    public void setOpenstack(OpenStackNetworkV1Parameters openstack) {
        this.openstack = openstack;
    }

    @Override
    public YarnNetworkV1Parameters createYarn() {
        if (yarn == null) {
            yarn = new YarnNetworkV1Parameters();
        }
        return yarn;
    }

    public void setYarn(YarnNetworkV1Parameters yarn) {
        this.yarn = yarn;
    }

    public AwsNetworkV1Parameters getAws() {
        return aws;
    }

    public GcpNetworkV1Parameters getGcp() {
        return gcp;
    }

    public AzureNetworkV1Parameters getAzure() {
        return azure;
    }

    public OpenStackNetworkV1Parameters getOpenstack() {
        return openstack;
    }

    public MockNetworkV1Parameters getMock() {
        return mock;
    }

    public YarnNetworkV1Parameters getYarn() {
        return yarn;
    }
}
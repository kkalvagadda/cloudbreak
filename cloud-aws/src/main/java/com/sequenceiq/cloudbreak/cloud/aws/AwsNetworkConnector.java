package com.sequenceiq.cloudbreak.cloud.aws;


import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.CreateSubnetRequest;
import com.amazonaws.services.ec2.model.CreateSubnetResult;
import com.amazonaws.services.ec2.model.CreateVpcRequest;
import com.amazonaws.services.ec2.model.CreateVpcResult;
import com.sequenceiq.cloudbreak.cloud.NetworkConnector;
import com.sequenceiq.cloudbreak.cloud.aws.view.AwsCredentialView;
import com.sequenceiq.cloudbreak.cloud.model.CloudCredential;
import com.sequenceiq.cloudbreak.cloud.model.CreatedCloudNetwork;
import com.sequenceiq.cloudbreak.cloud.model.Platform;
import com.sequenceiq.cloudbreak.cloud.model.Region;
import com.sequenceiq.cloudbreak.cloud.model.Variant;

@Service
public class AwsNetworkConnector implements NetworkConnector {

    private static final Logger LOGGER = LoggerFactory.getLogger(AwsNetworkConnector.class);

    @Inject
    private AwsClient awsClient;

    @Override
    public CreatedCloudNetwork createNetworkWithSubnets(String envName, CloudCredential cloudCredential, Region region, String networkCidr,
            Set<String> subnetCidrs) {
        AmazonEC2Client ec2Client = awsClient.createAccess(new AwsCredentialView(cloudCredential), region.value());
        String vpcId = createVpc(networkCidr, ec2Client);
        Set<String> subnetIds = subnetCidrs.stream()
                                .map(subnetCidr -> createSubnet(subnetCidr, vpcId, ec2Client))
                                .collect(Collectors.toSet());
        return new CreatedCloudNetwork(vpcId, subnetIds);
    }

    @Override
    public CreatedCloudNetwork deleteNetworkWithSubnets(CloudCredential cloudCredential) {
        return null;
    }

    @Override
    public Platform platform() {
        return AwsConstants.AWS_PLATFORM;
    }

    @Override
    public Variant variant() {
        return AwsConstants.AWS_VARIANT;
    }

    private String createVpc(String networkCidr, AmazonEC2Client ec2Client) {
        CreateVpcRequest newVPC = new CreateVpcRequest(networkCidr);
        CreateVpcResult createVpcResult = ec2Client.createVpc(newVPC);
        return createVpcResult.getVpc().getVpcId();
    }

    private String createSubnet(String subnetCidr, String vpcId, AmazonEC2Client ec2Client) {
        CreateSubnetRequest createSubnetRequest = new CreateSubnetRequest(vpcId, subnetCidr);
        CreateSubnetResult createSubnetResult = ec2Client.createSubnet(createSubnetRequest);
        return createSubnetResult.getSubnet().getSubnetId();
    }

}

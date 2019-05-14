package com.sequenceiq.cloudbreak.cloud.aws;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.CreateSubnetRequest;
import com.amazonaws.services.ec2.model.CreateSubnetResult;
import com.amazonaws.services.ec2.model.CreateVpcRequest;
import com.amazonaws.services.ec2.model.CreateVpcResult;
import com.amazonaws.services.ec2.model.Subnet;
import com.amazonaws.services.ec2.model.Vpc;
import com.sequenceiq.cloudbreak.cloud.aws.view.AwsCredentialView;
import com.sequenceiq.cloudbreak.cloud.model.CloudCredential;
import com.sequenceiq.cloudbreak.cloud.model.CreatedCloudNetwork;
import com.sequenceiq.cloudbreak.cloud.model.Platform;
import com.sequenceiq.cloudbreak.cloud.model.Region;
import com.sequenceiq.cloudbreak.cloud.model.Variant;

@RunWith(MockitoJUnitRunner.class)
public class AwsNetworkConnectorTest {

    public static final String ENV_NAME = "testEnv";

    public static final String VPC_ID = "newVpc";

    public static final String SUBNET_ID = "subnet-1";

    @InjectMocks
    private AwsNetworkConnector underTest;

    @Mock
    private AwsClient awsClient;

    @Test
    public void testPlatformShouldReturnAwsPlatform() {
        Platform actual = underTest.platform();

        Assert.assertEquals(AwsConstants.AWS_PLATFORM, actual);
    }

    @Test
    public void testVariantShouldReturnAwsPlatform() {
        Variant actual = underTest.variant();

        Assert.assertEquals(AwsConstants.AWS_VARIANT, actual);
    }

    @Test
    public void testCreateNetworkWithSubnetsShouldCreateTheNetworkAndSubnets() {
        CloudCredential cloudCredential = new CloudCredential(1L, "credential");
        Region region = Region.region("US_WEST_2");
        String networkCidr = "0.0.0.0/16";
        Set<String> subnetCidrs = new HashSet<>(Arrays.asList("1.1.1.1/8"));
        AmazonEC2Client ec2Client = mock(AmazonEC2Client.class);

        when(ec2Client.createVpc(any(CreateVpcRequest.class))).thenReturn(createVpcResult());
        when(ec2Client.createSubnet(any(CreateSubnetRequest.class))).thenReturn(createSubnetResult());
        when(awsClient.createAccess(any(AwsCredentialView.class), eq(region.value()))).thenReturn(ec2Client);

        CreatedCloudNetwork actual = underTest.createNetworkWithSubnets(ENV_NAME, cloudCredential, region, networkCidr, subnetCidrs);

        assertEquals(VPC_ID, actual.getNetworkId());
        assertTrue(actual.getSubnetIds().contains(SUBNET_ID));
        assertTrue(actual.getSubnetIds().size() == 1);
    }

    private CreateVpcResult createVpcResult() {
        Vpc vpc = new Vpc();
        vpc.setVpcId(VPC_ID);
        CreateVpcResult createVpcResult = new CreateVpcResult();
        createVpcResult.setVpc(vpc);
        return createVpcResult;
    }

    private CreateSubnetResult createSubnetResult() {
        Subnet subnet = new Subnet();
        subnet.setSubnetId(SUBNET_ID);
        CreateSubnetResult createSubnetResult = new CreateSubnetResult();
        createSubnetResult.setSubnet(subnet);
        return createSubnetResult;
    }
}
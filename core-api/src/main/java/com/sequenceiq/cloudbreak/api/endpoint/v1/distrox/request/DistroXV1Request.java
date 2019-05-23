package com.sequenceiq.cloudbreak.api.endpoint.v1.distrox.request;

import static com.sequenceiq.cloudbreak.doc.ModelDescriptions.StackModelDescription.PLACEMENT_SETTINGS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sequenceiq.cloudbreak.api.endpoint.v1.distrox.request.authentication.DistroXAuthenticationV1Request;
import com.sequenceiq.cloudbreak.api.endpoint.v1.distrox.request.cluster.DistroXClusterV1Request;
import com.sequenceiq.cloudbreak.api.endpoint.v1.distrox.request.cluster.sharedservice.SdxV1Request;
import com.sequenceiq.cloudbreak.api.endpoint.v1.distrox.request.customdomain.CustomDomainSettingsV1Request;
import com.sequenceiq.cloudbreak.api.endpoint.v1.distrox.request.environment.EnvironmentSettingsV1Request;
import com.sequenceiq.cloudbreak.api.endpoint.v1.distrox.request.environment.placement.PlacementSettingsV1Request;
import com.sequenceiq.cloudbreak.api.endpoint.v1.distrox.request.image.ImageSettingsV1Request;
import com.sequenceiq.cloudbreak.api.endpoint.v1.distrox.request.instancegroup.InstanceGroupV1Request;
import com.sequenceiq.cloudbreak.api.endpoint.v1.distrox.request.network.NetworkV1Request;
import com.sequenceiq.cloudbreak.api.endpoint.v1.distrox.request.tags.TagsV1Request;
import com.sequenceiq.cloudbreak.api.endpoint.v4.stacks.base.StackV4Base;
import com.sequenceiq.cloudbreak.doc.ModelDescriptions.ClusterModelDescription;
import com.sequenceiq.cloudbreak.doc.ModelDescriptions.StackModelDescription;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class DistroXV1Request extends StackV4Base {

    @Valid
    @NotNull
    @ApiModelProperty(value = StackModelDescription.GENERAL_SETTINGS, required = true)
    private EnvironmentSettingsV1Request environment;

    @ApiModelProperty(StackModelDescription.CUSTOM_DOMAIN_SETTINGS)
    private CustomDomainSettingsV1Request customDomain;

    @ApiModelProperty(StackModelDescription.TAGS)
    private TagsV1Request tags;

    @Valid
    @ApiModelProperty(PLACEMENT_SETTINGS)
    private PlacementSettingsV1Request placement;

    @NotNull
    @Valid
    @ApiModelProperty(value = StackModelDescription.INSTANCE_GROUPS, required = true)
    private List<InstanceGroupV1Request> instanceGroups = new ArrayList<>();

    @NotNull(message = "You should define authentication for stack!")
    @ApiModelProperty(StackModelDescription.AUTHENTICATION)
    private DistroXAuthenticationV1Request authentication;

    @Valid
    @ApiModelProperty(StackModelDescription.NETWORK)
    private NetworkV1Request network;

    @ApiModelProperty(StackModelDescription.IMAGE_SETTINGS)
    private ImageSettingsV1Request image;

    @Valid
    @ApiModelProperty(StackModelDescription.CLUSTER_REQUEST)
    private DistroXClusterV1Request cluster;

    @ApiModelProperty(ClusterModelDescription.SHARED_SERVICE_REQUEST)
    private SdxV1Request sdxService;

    @ApiModelProperty(StackModelDescription.INPUTS)
    private Map<String, Object> inputs = new HashMap<>();

    public EnvironmentSettingsV1Request getEnvironment() {
        return environment;
    }

    public void setEnvironment(EnvironmentSettingsV1Request environment) {
        this.environment = environment;
    }

    public CustomDomainSettingsV1Request getCustomDomain() {
        return customDomain;
    }

    public void setCustomDomain(CustomDomainSettingsV1Request customDomain) {
        this.customDomain = customDomain;
    }

    public TagsV1Request getTags() {
        return tags;
    }

    public void setTags(TagsV1Request tags) {
        this.tags = tags;
    }

    public List<InstanceGroupV1Request> getInstanceGroups() {
        return instanceGroups;
    }

    public void setInstanceGroups(List<InstanceGroupV1Request> instanceGroups) {
        this.instanceGroups = instanceGroups;
    }

    public DistroXAuthenticationV1Request getAuthentication() {
        return authentication;
    }

    public void setAuthentication(DistroXAuthenticationV1Request authentication) {
        this.authentication = authentication;
    }

    public NetworkV1Request getNetwork() {
        return network;
    }

    public void setNetwork(NetworkV1Request network) {
        this.network = network;
    }

    public ImageSettingsV1Request getImage() {
        return image;
    }

    public void setImage(ImageSettingsV1Request image) {
        this.image = image;
    }

    public DistroXClusterV1Request getCluster() {
        return cluster;
    }

    public void setCluster(DistroXClusterV1Request cluster) {
        this.cluster = cluster;
    }

    public PlacementSettingsV1Request getPlacement() {
        return placement;
    }

    public void setPlacement(PlacementSettingsV1Request placement) {
        this.placement = placement;
    }

    public Map<String, Object> getInputs() {
        return inputs;
    }

    public void setInputs(Map<String, Object> inputs) {
        this.inputs = inputs;
    }

    public SdxV1Request getSdxService() {
        return sdxService;
    }

    public void setSdxService(SdxV1Request sdxService) {
        this.sdxService = sdxService;
    }
}

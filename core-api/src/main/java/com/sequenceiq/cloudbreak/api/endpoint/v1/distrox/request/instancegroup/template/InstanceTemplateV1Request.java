package com.sequenceiq.cloudbreak.api.endpoint.v1.distrox.request.instancegroup.template;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sequenceiq.cloudbreak.api.endpoint.v1.distrox.request.instancegroup.template.volume.RootVolumeV1Request;
import com.sequenceiq.cloudbreak.api.endpoint.v1.distrox.request.instancegroup.template.volume.VolumeV1Request;
import com.sequenceiq.cloudbreak.api.endpoint.v4.stacks.base.InstanceTemplateV4Base;

import io.swagger.annotations.ApiModel;

@ApiModel
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class InstanceTemplateV1Request extends InstanceTemplateV4Base {

    private RootVolumeV1Request rootVolume;

    private VolumeV1Request ephemeralVolume;

    private Set<VolumeV1Request> attachedVolumes;

    public RootVolumeV1Request getRootVolume() {
        return rootVolume;
    }

    public void setRootVolume(RootVolumeV1Request rootVolume) {
        this.rootVolume = rootVolume;
    }

    public VolumeV1Request getEphemeralVolume() {
        return ephemeralVolume;
    }

    public void setEphemeralVolume(VolumeV1Request ephemeralVolume) {
        this.ephemeralVolume = ephemeralVolume;
    }

    public Set<VolumeV1Request> getAttachedVolumes() {
        if (attachedVolumes == null) {
            return new HashSet<>();
        }
        return attachedVolumes;
    }

    public void setAttachedVolumes(Set<VolumeV1Request> attachedVolumes) {
        this.attachedVolumes = attachedVolumes;
    }
}

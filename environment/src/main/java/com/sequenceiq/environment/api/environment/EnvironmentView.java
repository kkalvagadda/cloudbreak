package com.sequenceiq.environment.api.environment;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sequenceiq.environment.api.json.Json;
import com.sequenceiq.environment.api.json.JsonToString;

@Entity
@Table(name = "Environment")
@Where(clause = "archived = false")
public class EnvironmentView extends CompactView {

    @Column(nullable = false)
    private String cloudPlatform;

    @Convert(converter = JsonToString.class)
    @Column(columnDefinition = "TEXT", nullable = false)
    private Json regions;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Credential credential;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String locationDisplayName;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private Double latitude;

    @Column(columnDefinition = "boolean default false")
    private boolean archived;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "environment")
    private Set<Long> sdxResources = new HashSet<>();

    @OneToOne(mappedBy = "environment", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private BaseNetwork network;

    public Json getRegions() {
        return regions;
    }

    public void setRegions(Json regions) {
        this.regions = regions;
    }

    public Set<Region> getRegionSet() {
        return JsonUtil.jsonToType(regions.getValue(), new TypeReference<>() {
        });
    }

    public String getCloudPlatform() {
        return cloudPlatform;
    }

    public void setCloudPlatform(String cloudPlatform) {
        this.cloudPlatform = cloudPlatform;
    }

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocationDisplayName() {
        return locationDisplayName;
    }

    public void setLocationDisplayName(String locationDisplayName) {
        this.locationDisplayName = locationDisplayName;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public BaseNetwork getNetwork() {
        return network;
    }

    public void setNetwork(BaseNetwork network) {
        this.network = network;
    }

    public Set<Long> getSdxResources() {
        return sdxResources;
    }

    public void setSdxResources(Set<Long> sdxResources) {
        this.sdxResources = sdxResources;
    }

}

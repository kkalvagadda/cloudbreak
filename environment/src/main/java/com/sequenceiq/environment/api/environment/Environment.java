package com.sequenceiq.environment.api.environment;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.sequenceiq.cloudbreak.authorization.WorkspaceResource;
import com.sequenceiq.cloudbreak.domain.Credential;
import com.sequenceiq.cloudbreak.domain.KerberosConfig;
import com.sequenceiq.cloudbreak.domain.KubernetesConfig;
import com.sequenceiq.cloudbreak.domain.LdapConfig;
import com.sequenceiq.cloudbreak.domain.ProxyConfig;
import com.sequenceiq.cloudbreak.domain.RDSConfig;
import com.sequenceiq.cloudbreak.domain.stack.cluster.DatalakeResources;
import com.sequenceiq.cloudbreak.domain.view.ClusterApiView;
import com.sequenceiq.cloudbreak.domain.view.StackApiView;
import com.sequenceiq.cloudbreak.util.JsonUtil;
import com.sequenceiq.environment.api.ArchivableResource;
import com.sequenceiq.environment.api.json.Json;
import com.sequenceiq.environment.api.json.JsonToString;

@Entity
@Where(clause = "archived = false")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"workspace_id", "name"}))
public class Environment implements ArchivableResource {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "environment_generator")
    @SequenceGenerator(name = "environment_generator", sequenceName = "environment_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000000, columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Credential credential;

    @Column(nullable = false)
    private String cloudPlatform;

    @Convert(converter = JsonToString.class)
    @Column(columnDefinition = "TEXT", nullable = false)
    private Json regions;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String locationDisplayName;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private Double latitude;

    private boolean archived;

    private Long deletionTimestamp = -1L;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "env_ldap", joinColumns = @JoinColumn(name = "envid"), inverseJoinColumns = @JoinColumn(name = "ldapid"))
    private Set<Long> ldapConfigs = new HashSet<>();

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "env_proxy", joinColumns = @JoinColumn(name = "envid"), inverseJoinColumns = @JoinColumn(name = "proxyid"))
    private Set<Long> proxyConfigs = new HashSet<>();

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "env_rds", joinColumns = @JoinColumn(name = "envid"), inverseJoinColumns = @JoinColumn(name = "rdsid"))
    private Set<Long> rdsConfigs = new HashSet<>();

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "env_kubernetes", joinColumns = @JoinColumn(name = "envid"), inverseJoinColumns = @JoinColumn(name = "kubernetesid"))
    private Set<Long> kubernetesConfigs = new HashSet<>();

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "env_kdc", joinColumns = @JoinColumn(name = "envid"), inverseJoinColumns = @JoinColumn(name = "kdcid"))
    private Set<Long> kerberosConfigs = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "environment_id")
    @Where(clause = "terminated IS NULL")
    private Set<Long> stacks = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "environment_id")
    private Set<Long> sdxResources = new HashSet<>();

    @OneToOne(mappedBy = "environment", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private BaseNetwork network;

    public Environment() {
        try {
            regions = new Json(new HashSet<Region>());
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    @Override
    public void unsetRelationsToEntitiesToBeDeleted() {
        ldapConfigs = null;
        rdsConfigs = null;
        proxyConfigs = null;
        kubernetesConfigs = null;
        kerberosConfigs = null;
        sdxResources = null;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    public Json getRegions() {
        return regions;
    }

    public void setRegions(Set<Region> regions) {
        try {
            this.regions = new Json(regions);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public Set<Region> getRegionSet() {
        return JsonUtil.jsonToType(regions.getValue(), new TypeReference<>() {
        });
    }

    public Set<Long> getLdapConfigs() {
        return ldapConfigs;
    }

    public void setLdapConfigs(Set<Long> ldapConfigs) {
        this.ldapConfigs = ldapConfigs;
    }

    public Set<Long> getProxyConfigs() {
        return proxyConfigs;
    }

    public void setProxyConfigs(Set<Long> proxyConfigs) {
        this.proxyConfigs = proxyConfigs;
    }

    public Set<Long> getRdsConfigs() {
        return rdsConfigs;
    }

    public void setRdsConfigs(Set<Long> rdsConfigs) {
        this.rdsConfigs = rdsConfigs;
    }

    public Set<Long> getKubernetesConfigs() {
        return kubernetesConfigs;
    }

    public void setKubernetesConfigs(Set<Long> kubernetesConfigs) {
        this.kubernetesConfigs = kubernetesConfigs;
    }

    public Set<Long> getStacks() {
        return stacks;
    }

    public void setStacks(Set<Long> stacks) {
        this.stacks = stacks;
    }

    public Set<Long> getClusters() {
        return clusters;
    }

    public void setClusters(Set<Long> clusters) {
        this.clusters = clusters;
    }

    public Set<Long> getSdxResources() {
        return sdxResources;
    }

    public void setSdxResources(Set<Long> sdxResources) {
        this.sdxResources = sdxResources;
    }

    public WorkspaceResource getResource() {
        return WorkspaceResource.ENVIRONMENT;
    }

    public String getCloudPlatform() {
        return cloudPlatform;
    }

    public void setCloudPlatform(String cloudPlatform) {
        this.cloudPlatform = cloudPlatform;
    }

    public String getLocation() {
        return location;
    }

    public String getLocationDisplayName() {
        return locationDisplayName;
    }

    public void setLocationDisplayName(String locationDisplayName) {
        this.locationDisplayName = locationDisplayName;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public Set<Long> getKerberosConfigs() {
        return kerberosConfigs;
    }

    public void setKerberosConfigs(Set<Long> kerberosConfigs) {
        this.kerberosConfigs = kerberosConfigs;
    }

    public boolean isArchived() {
        return archived;
    }

    public Long getDeletionTimestamp() {
        return deletionTimestamp;
    }

    public void setDeletionTimestamp(Long deletionTimestamp) {
        this.deletionTimestamp = deletionTimestamp;
    }

    public BaseNetwork getNetwork() {
        return network;
    }

    public void setNetwork(BaseNetwork network) {
        this.network = network;
    }

}

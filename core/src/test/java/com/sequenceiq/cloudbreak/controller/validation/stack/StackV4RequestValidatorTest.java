package com.sequenceiq.cloudbreak.controller.validation.stack;

import static com.sequenceiq.cloudbreak.api.endpoint.v4.database.base.DatabaseType.HIVE;
import static com.sequenceiq.cloudbreak.api.endpoint.v4.database.base.DatabaseType.RANGER;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.LoggerFactory;

import com.sequenceiq.cloudbreak.api.endpoint.v4.database.base.DatabaseType;
import com.sequenceiq.cloudbreak.api.endpoint.v4.stacks.request.StackV4Request;
import com.sequenceiq.cloudbreak.api.endpoint.v4.stacks.request.cluster.ClusterV4Request;
import com.sequenceiq.cloudbreak.api.endpoint.v4.stacks.request.cluster.ambari.AmbariV4Request;
import com.sequenceiq.cloudbreak.api.endpoint.v4.stacks.request.environment.placement.PlacementSettingsV4Request;
import com.sequenceiq.cloudbreak.api.endpoint.v4.stacks.request.instancegroup.InstanceGroupV4Request;
import com.sequenceiq.cloudbreak.api.endpoint.v4.stacks.request.instancegroup.template.InstanceTemplateV4Request;
import com.sequenceiq.cloudbreak.api.endpoint.v4.stacks.request.instancegroup.template.volume.VolumeV4Request;
import com.sequenceiq.cloudbreak.cloud.model.CloudRegions;
import com.sequenceiq.cloudbreak.controller.validation.ValidationResult;
import com.sequenceiq.cloudbreak.controller.validation.ValidationResult.State;
import com.sequenceiq.cloudbreak.controller.validation.template.InstanceTemplateV4RequestValidator;
import com.sequenceiq.cloudbreak.domain.Blueprint;
import com.sequenceiq.cloudbreak.domain.RDSConfig;
import com.sequenceiq.cloudbreak.dto.credential.Credential;
import com.sequenceiq.cloudbreak.ldap.LdapConfigService;
import com.sequenceiq.cloudbreak.service.CloudbreakRestRequestThreadLocalService;
import com.sequenceiq.cloudbreak.service.blueprint.BlueprintService;
import com.sequenceiq.cloudbreak.service.environment.EnvironmentClientService;
import com.sequenceiq.cloudbreak.service.rdsconfig.RdsConfigService;

@RunWith(MockitoJUnitRunner.class)
public class StackV4RequestValidatorTest extends StackRequestValidatorTestBase {

    private static final Long WORKSPACE_ID = 1L;

    private static final String TEST_HIVE_RDS_NAME = "hive-rds";

    private static final String TEST_RANGER_RDS_NAME = "ranger-rds";

    private static final String TEST_LDAP_NAME = "ldap";

    private static final String TEST_BP_NAME = "testBpName";

    private static final String CREDENTIAL_NAME = "someCred";

    private static final String ENVIRONMENT_NAME = "someEnvironment";

    private static final String RDS_ERROR_MESSAGE_FORMAT = "For a Datalake cluster (since you have selected a datalake ready blueprint) "
            + "you should provide at least one %s rds/database configuration to the Cluster request";

    private static final String LACK_OF_LDAP_MESSAGE = "For a Datalake cluster (since you have selected a datalake ready blueprint) you should provide"
            + " an LDAP configuration or its name/id to the Cluster request";

    @Spy
    private final InstanceTemplateV4RequestValidator templateRequestValidator = new InstanceTemplateV4RequestValidator();

    @Mock
    private BlueprintService blueprintService;

    @Mock
    private CloudbreakRestRequestThreadLocalService restRequestThreadLocalService;

    @InjectMocks
    private StackV4RequestValidator underTest;

    @Mock
    private Blueprint blueprint;

    @Mock
    private RdsConfigService rdsConfigService;

    @Mock
    private EnvironmentClientService environmentClientService;

    @Mock
    private Credential credential;

    @Mock
    private CloudRegions cloudRegions;

    @Mock
    private LdapConfigService ldapConfigService;

    public StackV4RequestValidatorTest() {
        super(LoggerFactory.getLogger(StackV4RequestValidatorTest.class));
    }

    @Before
    public void setUp() {
        when(blueprintService.getByNameForWorkspaceId(anyString(), eq(WORKSPACE_ID))).thenReturn(blueprint);
        when(restRequestThreadLocalService.getRequestedWorkspaceId()).thenReturn(WORKSPACE_ID);
    }

    @Test
    public void testWithZeroRootVolumeSize() {
        assertNotNull(templateRequestValidator);
        StackV4Request stackRequest = stackRequestWithRootVolumeSize(0);
        ValidationResult validationResult = underTest.validate(stackRequest);
        assertEquals(State.ERROR, validationResult.getState());
    }

    @Test
    public void testWithNegativeRootVolumeSize() {
        StackV4Request stackRequest = stackRequestWithRootVolumeSize(-1);
        ValidationResult validationResult = underTest.validate(stackRequest);
        assertEquals(State.ERROR, validationResult.getState());
    }

    @Test
    public void testNullValueIsAllowedForRootVolumeSize() {
        StackV4Request stackRequest = stackRequestWithRootVolumeSize(null);
        ValidationResult validationResult = underTest.validate(stackRequest);
        assertEquals(State.VALID, validationResult.getState());
    }

    @Test
    public void testWithPositiveRootVolumeSize() {
        StackV4Request stackRequest = stackRequestWithRootVolumeSize(1);
        ValidationResult validationResult = underTest.validate(stackRequest);
        assertEquals(State.VALID, validationResult.getState());
    }

    @Test
    public void testWhenBlueprintIsSharedServiceReadyAndThereIsOnlyHiveRdsWithNameThenErrorComes() {
        when(blueprintService.isDatalakeBlueprint(any())).thenReturn(true);
        StackV4Request request = stackRequest();
        request.getCluster().setDatabases(Set.of(TEST_HIVE_RDS_NAME));
        when(rdsConfigService.getByNameForWorkspaceId(TEST_HIVE_RDS_NAME, WORKSPACE_ID)).thenReturn(rdsConfig(HIVE));
        when(blueprintService.isAmbariBlueprint(any(Blueprint.class))).thenReturn(true);

        ValidationResult validationResult = underTest.validate(request);

        assertEquals(2L, validationResult.getErrors().size());
        assertTrue(validationResult.getErrors().stream().anyMatch(s -> s.equals(String.format(RDS_ERROR_MESSAGE_FORMAT, "Ranger"))));
        assertTrue(validationResult.getErrors().stream().anyMatch(s -> s.equals(LACK_OF_LDAP_MESSAGE)));

        verify(blueprintService, times(1)).getByNameForWorkspaceId(TEST_BP_NAME, WORKSPACE_ID);
        verify(blueprintService, times(1)).getByNameForWorkspaceId(anyString(), anyLong());
        verify(rdsConfigService, times(1)).getByNameForWorkspaceId(TEST_HIVE_RDS_NAME, WORKSPACE_ID);
        verify(rdsConfigService, times(1)).getByNameForWorkspaceId(anyString(), anyLong());
    }

    @Test
    public void testWhenBlueprintIsSharedServiceReadyAndThereIsHiveRdsWithClouderaManager() {
        when(blueprintService.isDatalakeBlueprint(any())).thenReturn(true);
        when(blueprintService.isAmbariBlueprint(any(Blueprint.class))).thenReturn(false);
        StackV4Request request = stackRequest();
        request.getCluster().setDatabases(Set.of(TEST_HIVE_RDS_NAME));
        when(rdsConfigService.getByNameForWorkspaceId(TEST_HIVE_RDS_NAME, WORKSPACE_ID)).thenReturn(rdsConfig(HIVE));

        ValidationResult validationResult = underTest.validate(request);

        assertEquals(0L, validationResult.getErrors().size());
        assertFalse(validationResult.getErrors().stream().anyMatch(s -> s.equals(String.format(RDS_ERROR_MESSAGE_FORMAT, "Hive"))));
        assertFalse(validationResult.getErrors().stream().anyMatch(s -> s.equals(String.format(RDS_ERROR_MESSAGE_FORMAT, "Ranger"))));
        assertFalse(validationResult.getErrors().stream().anyMatch(s -> s.equals(LACK_OF_LDAP_MESSAGE)));

        verify(blueprintService, times(1)).getByNameForWorkspaceId(TEST_BP_NAME, WORKSPACE_ID);
        verify(blueprintService, times(1)).getByNameForWorkspaceId(anyString(), anyLong());
        verify(rdsConfigService, times(1)).getByNameForWorkspaceId(anyString(), anyLong());
    }

    @Test
    public void testWhenBlueprintIsSharedServiceReadyAndThereIsNoRdsWithClouderaManager() {
        when(blueprintService.isDatalakeBlueprint(any())).thenReturn(true);
        when(blueprintService.isAmbariBlueprint(any(Blueprint.class))).thenReturn(false);
        StackV4Request request = stackRequest();

        ValidationResult validationResult = underTest.validate(request);

        assertTrue(validationResult.getErrors().isEmpty());

        verify(blueprintService, times(1)).getByNameForWorkspaceId(TEST_BP_NAME, WORKSPACE_ID);
        verify(blueprintService, times(1)).getByNameForWorkspaceId(anyString(), anyLong());
        verify(rdsConfigService, times(0)).getByNameForWorkspaceId(anyString(), anyLong());
    }

    @Test
    public void testWhenBlueprintIsSharedServiceReadyAndThereIsOnlyRangerRdsWithNameThenErrorComes() {
        when(blueprintService.isDatalakeBlueprint(any())).thenReturn(true);
        when(blueprintService.isAmbariBlueprint(any(Blueprint.class))).thenReturn(true);
        StackV4Request request = stackRequest();
        request.getCluster().setDatabases(Set.of(TEST_RANGER_RDS_NAME));
        when(rdsConfigService.getByNameForWorkspaceId(TEST_RANGER_RDS_NAME, WORKSPACE_ID)).thenReturn(rdsConfig(RANGER));

        ValidationResult validationResult = underTest.validate(request);

        assertEquals(2L, validationResult.getErrors().size());
        assertTrue(validationResult.getErrors().stream().anyMatch(s -> s.equals(String.format(RDS_ERROR_MESSAGE_FORMAT, "Hive"))));
        assertTrue(validationResult.getErrors().stream().anyMatch(s -> s.equals(LACK_OF_LDAP_MESSAGE)));

        verify(blueprintService, times(1)).getByNameForWorkspaceId(TEST_BP_NAME, WORKSPACE_ID);
        verify(blueprintService, times(1)).getByNameForWorkspaceId(anyString(), anyLong());
        verify(rdsConfigService, times(1)).getByNameForWorkspaceId(TEST_RANGER_RDS_NAME, WORKSPACE_ID);
        verify(rdsConfigService, times(1)).getByNameForWorkspaceId(anyString(), anyLong());
    }

    @Test
    public void testWhenBlueprintIsSharedServiceReadyAndThereAreRangerAndHiveRdsWithNameButLdapDoesntThenErrorComes() {
        when(blueprintService.isDatalakeBlueprint(any())).thenReturn(true);
        when(blueprintService.isAmbariBlueprint(any(Blueprint.class))).thenReturn(true);
        StackV4Request request = stackRequest();
        request.getCluster().setDatabases(Set.of(TEST_RANGER_RDS_NAME, TEST_HIVE_RDS_NAME));
        when(rdsConfigService.getByNameForWorkspaceId(TEST_RANGER_RDS_NAME, WORKSPACE_ID)).thenReturn(rdsConfig(RANGER));
        when(rdsConfigService.getByNameForWorkspaceId(TEST_HIVE_RDS_NAME, WORKSPACE_ID)).thenReturn(rdsConfig(HIVE));
        ValidationResult validationResult = underTest.validate(request);

        assertEquals(1L, validationResult.getErrors().size());
        assertFalse(validationResult.getErrors().stream().anyMatch(s -> s.equals(String.format(RDS_ERROR_MESSAGE_FORMAT, "Hive"))));
        assertFalse(validationResult.getErrors().stream().anyMatch(s -> s.equals(String.format(RDS_ERROR_MESSAGE_FORMAT, "Ranger"))));
        assertTrue(validationResult.getErrors().stream().anyMatch(s -> s.equals(LACK_OF_LDAP_MESSAGE)));

        verify(blueprintService, times(1)).getByNameForWorkspaceId(TEST_BP_NAME, WORKSPACE_ID);
        verify(blueprintService, times(1)).getByNameForWorkspaceId(anyString(), anyLong());
        verify(rdsConfigService, times(1)).getByNameForWorkspaceId(TEST_RANGER_RDS_NAME, WORKSPACE_ID);
        verify(rdsConfigService, times(1)).getByNameForWorkspaceId(TEST_HIVE_RDS_NAME, WORKSPACE_ID);
        verify(rdsConfigService, times(2)).getByNameForWorkspaceId(anyString(), anyLong());
    }

    @Test
    public void testWhenBlueprintIsSharedServiceReadyAndThereAreRangerAndHiveRdsWithNameAndLdapWithNameThenEverythingIsFine() {
        when(blueprintService.isDatalakeBlueprint(any())).thenReturn(true);
        StackV4Request request = stackRequest();
        request.getCluster().setDatabases(Set.of(TEST_RANGER_RDS_NAME, TEST_HIVE_RDS_NAME));
        when(rdsConfigService.getByNameForWorkspaceId(TEST_RANGER_RDS_NAME, WORKSPACE_ID)).thenReturn(rdsConfig(RANGER));
        when(rdsConfigService.getByNameForWorkspaceId(TEST_HIVE_RDS_NAME, WORKSPACE_ID)).thenReturn(rdsConfig(HIVE));

        ValidationResult validationResult = underTest.validate(request);

        assertValidationErrorIsEmpty(validationResult.getErrors());

        verify(blueprintService, times(1)).getByNameForWorkspaceId(TEST_BP_NAME, WORKSPACE_ID);
        verify(blueprintService, times(1)).getByNameForWorkspaceId(anyString(), anyLong());
        verify(rdsConfigService, times(1)).getByNameForWorkspaceId(TEST_RANGER_RDS_NAME, WORKSPACE_ID);
        verify(rdsConfigService, times(1)).getByNameForWorkspaceId(TEST_HIVE_RDS_NAME, WORKSPACE_ID);
        verify(rdsConfigService, times(2)).getByNameForWorkspaceId(anyString(), anyLong());
    }

    @Test
    public void testWhenBlueprintIsSharedServiceReadyAndThereAreRangerAndHiveRdsWithBothIdAndLdapWithNameThenEverythingIsFine() {
        when(blueprintService.isDatalakeBlueprint(any())).thenReturn(true);
        StackV4Request request = stackRequest();
        request.getCluster().setDatabases(Set.of(TEST_RANGER_RDS_NAME, TEST_HIVE_RDS_NAME));
        when(rdsConfigService.getByNameForWorkspaceId(TEST_RANGER_RDS_NAME, WORKSPACE_ID)).thenReturn(rdsConfig(RANGER));
        when(rdsConfigService.getByNameForWorkspaceId(TEST_HIVE_RDS_NAME, WORKSPACE_ID)).thenReturn(rdsConfig(HIVE));

        ValidationResult validationResult = underTest.validate(request);

        assertValidationErrorIsEmpty(validationResult.getErrors());

        verify(blueprintService, times(1)).getByNameForWorkspaceId(TEST_BP_NAME, WORKSPACE_ID);
        verify(blueprintService, times(1)).getByNameForWorkspaceId(anyString(), anyLong());
        verify(rdsConfigService, times(1)).getByNameForWorkspaceId(TEST_RANGER_RDS_NAME, WORKSPACE_ID);
        verify(rdsConfigService, times(1)).getByNameForWorkspaceId(TEST_HIVE_RDS_NAME, WORKSPACE_ID);
    }

    @Test
    public void testWhenBlueprintIsSharedServiceReadyAndThereIsNoLdapAndRdsConfigGivenThenErrorComes() {
        when(blueprintService.isDatalakeBlueprint(any())).thenReturn(true);
        when(blueprintService.isAmbariBlueprint(any(Blueprint.class))).thenReturn(true);
        StackV4Request request = stackRequest();
        request.getCluster().setDatabases(Collections.emptySet());

        ValidationResult validationResult = underTest.validate(request);

        assertEquals(3L, validationResult.getErrors().size());

        verify(blueprintService, times(1)).getByNameForWorkspaceId(TEST_BP_NAME, WORKSPACE_ID);
        verify(blueprintService, times(1)).getByNameForWorkspaceId(anyString(), anyLong());
    }

    private StackV4Request stackRequest() {
        InstanceTemplateV4Request templateRequest = new InstanceTemplateV4Request();
        InstanceGroupV4Request instanceGroupRequest = getInstanceGroupV4Request(templateRequest);
        ClusterV4Request clusterRequest = getCluster();
        return getStackV4Request(Collections.singletonList(instanceGroupRequest), clusterRequest);
    }

    private StackV4Request stackRequestWithRootVolumeSize(Integer rootVolumeSize) {
        InstanceTemplateV4Request templateRequest = new InstanceTemplateV4Request();
        templateRequest.setRootVolume(getRootVolume(rootVolumeSize));
        InstanceGroupV4Request instanceGroupRequest = getInstanceGroupV4Request(templateRequest);
        ClusterV4Request clusterRequest = getCluster();
        return getStackV4Request(Collections.singletonList(instanceGroupRequest), clusterRequest);
    }

    private VolumeV4Request getRootVolume(Integer size) {
        VolumeV4Request root = new VolumeV4Request();
        root.setSize(size);
        return root;
    }

    private InstanceGroupV4Request getInstanceGroupV4Request(InstanceTemplateV4Request templateRequest) {
        InstanceGroupV4Request instanceGroupRequest = new InstanceGroupV4Request();
        instanceGroupRequest.setName("master");
        instanceGroupRequest.setTemplate(templateRequest);
        return instanceGroupRequest;
    }

    private ClusterV4Request getCluster() {
        ClusterV4Request clusterRequest = new ClusterV4Request();
        AmbariV4Request ambariV4Request = getAmbariV4Request();
        clusterRequest.setAmbari(ambariV4Request);
        clusterRequest.setBlueprintName(TEST_BP_NAME);
        return clusterRequest;
    }

    private AmbariV4Request getAmbariV4Request() {
        return new AmbariV4Request();
    }

    private StackV4Request getStackV4Request(List<InstanceGroupV4Request> instanceGroupRequests, ClusterV4Request clusterRequest) {
        StackV4Request stackRequest = new StackV4Request();
        stackRequest.setCluster(clusterRequest);
        stackRequest.setInstanceGroups(instanceGroupRequests);
        stackRequest.setEnvironmentCrn("envCrn");
        stackRequest.setPlacement(new PlacementSettingsV4Request());
        return stackRequest;
    }

    private RDSConfig rdsConfig(DatabaseType type) {
        RDSConfig rds = new RDSConfig();
        rds.setType(type.name());
        return rds;
    }

}
package com.sequenceiq.cloudbreak.cm;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;

import com.cloudera.api.swagger.ClouderaManagerResourceApi;
import com.cloudera.api.swagger.ClustersResourceApi;
import com.cloudera.api.swagger.client.ApiClient;
import com.cloudera.api.swagger.client.ApiException;
import com.cloudera.api.swagger.model.ApiCommand;
import com.cloudera.api.swagger.model.ApiConfig;
import com.cloudera.api.swagger.model.ApiConfigList;
import com.cloudera.api.swagger.model.ApiConfigureForKerberosArguments;
import com.sequenceiq.cloudbreak.client.HttpClientConfig;
import com.sequenceiq.cloudbreak.cm.client.ClouderaManagerClientFactory;
import com.sequenceiq.cloudbreak.cm.polling.ClouderaManagerPollingServiceProvider;
import com.sequenceiq.cloudbreak.domain.stack.Stack;
import com.sequenceiq.cloudbreak.domain.stack.cluster.Cluster;
import com.sequenceiq.cloudbreak.dto.KerberosConfig;
import com.sequenceiq.cloudbreak.service.CloudbreakException;
import com.sequenceiq.cloudbreak.template.kerberos.KerberosDetailService;
import com.sequenceiq.cloudbreak.type.KerberosType;

public class ClouderaManagerKerberosServiceTest {

    @Mock
    private ClouderaManagerPollingServiceProvider clouderaManagerPollingServiceProvider;

    @Mock
    private ApplicationContext applicationContext;

    @Mock
    private ClouderaManagerResourceApi clouderaManagerResourceApi;

    @Mock
    private ClouderaManagerModificationService modificationService;

    @Mock
    private ClustersResourceApi clustersResourceApi;

    @Mock
    private ClouderaManagerClientFactory clouderaManagerClientFactory;

    @Mock
    private ClouderaManagerClusterDecomissionService decommissionService;

    @Mock
    private KerberosDetailService kerberosDetailService;

    @InjectMocks
    private ClouderaManagerKerberosService underTest = new ClouderaManagerKerberosService();

    private Stack stack;

    private Cluster cluster;

    private ApiClient client;

    private HttpClientConfig clientConfig;

    @Before
    public void init() {
        stack = new Stack();
        cluster = new Cluster();
        cluster.setName("clusterName");
        stack.setCluster(cluster);
        client = new ApiClient();
        clientConfig = new HttpClientConfig("1.2.3.4", null, null, null);
        MockitoAnnotations.initMocks(this);
        when(clouderaManagerClientFactory.getClouderaManagerResourceApi(client)).thenReturn(clouderaManagerResourceApi);
        when(clouderaManagerClientFactory.getClustersResourceApi(client)).thenReturn(clustersResourceApi);
        when(applicationContext.getBean(eq(ClouderaManagerModificationService.class), eq(stack), eq(clientConfig))).thenReturn(modificationService);
        when(applicationContext.getBean(eq(ClouderaManagerClusterDecomissionService.class), eq(stack), eq(clientConfig))).thenReturn(decommissionService);
    }

    @Test
    public void testSetupKerberosWithAd() throws ApiException {
        KerberosConfig kerberosConfig = KerberosConfig.KerberosConfigBuilder.aKerberosConfig()
            .withType(KerberosType.ACTIVE_DIRECTORY)
            .withRealm("TESTREALM")
            .withUrl("kdcs")
            .withAdminUrl("adminhosts")
            .withContainerDn("container")
            .withPrincipal("principal")
            .withPassword("pw")
            .build();
        when(clouderaManagerResourceApi.importAdminCredentials("pw", "principal")).thenReturn(new ApiCommand().id(BigDecimal.ONE));
        when(kerberosDetailService.isAdJoinable(kerberosConfig)).thenReturn(Boolean.TRUE);
        underTest.setupKerberos(client, stack, kerberosConfig);

        ArgumentCaptor<ApiConfigList> apiConfigListArgumentCaptor = ArgumentCaptor.forClass(ApiConfigList.class);
        verify(clouderaManagerResourceApi).updateConfig(anyString(), apiConfigListArgumentCaptor.capture());
        Map<String, String> apiConfigMap =
                apiConfigListArgumentCaptor.getValue().getItems().stream().collect(Collectors.toMap(ApiConfig::getName, ApiConfig::getValue));
        assertEquals("Active Directory", apiConfigMap.get("kdc_type"));
        assertEquals(kerberosConfig.getRealm(), apiConfigMap.get("security_realm"));
        assertEquals(kerberosConfig.getUrl(), apiConfigMap.get("kdc_host"));
        assertEquals(kerberosConfig.getAdminUrl(), apiConfigMap.get("kdc_admin_host"));
        assertEquals(kerberosConfig.getContainerDn(), apiConfigMap.get("ad_kdc_domain"));
        assertEquals(5L, apiConfigMap.size());
        verify(clouderaManagerPollingServiceProvider).kerberosConfigurePollingService(stack, client, BigDecimal.ONE);
    }

    @Test
    public void testConfigureKerberosViaApi() throws CloudbreakException, ApiException {
        KerberosConfig kerberosConfig = KerberosConfig.KerberosConfigBuilder.aKerberosConfig()
            .withType(KerberosType.ACTIVE_DIRECTORY)
            .withRealm("TESTREALM")
            .withUrl("kdcs")
            .withAdminUrl("adminhosts")
            .withContainerDn("container")
            .withPrincipal("principal")
            .withPassword("pw")
            .build();

        when(clustersResourceApi.configureForKerberos(eq(cluster.getName()), any(ApiConfigureForKerberosArguments.class)))
                .thenReturn(new ApiCommand().id(BigDecimal.TEN));
        when(clouderaManagerResourceApi.generateCredentialsCommand()).thenReturn(new ApiCommand().id(BigDecimal.ZERO));
        when(clustersResourceApi.deployClientConfig(cluster.getName())).thenReturn(new ApiCommand().id(BigDecimal.valueOf(2L)));
        when(kerberosDetailService.isAdJoinable(kerberosConfig)).thenReturn(Boolean.TRUE);

        underTest.configureKerberosViaApi(client, clientConfig, stack, kerberosConfig);

        verify(modificationService).stopCluster();
        verify(clouderaManagerPollingServiceProvider).kerberosConfigurePollingService(stack, client, BigDecimal.TEN);
        verify(clouderaManagerPollingServiceProvider).kerberosConfigurePollingService(stack, client, BigDecimal.ZERO);
        verify(clustersResourceApi).deployClientConfig(cluster.getName());
        verify(modificationService).startCluster(anySet());
    }

    @Test
    public void testSetupKerberosWithoutKerberos() throws CloudbreakException, ApiException {
        when(kerberosDetailService.isAdJoinable(any())).thenReturn(Boolean.FALSE);

        underTest.setupKerberos(client, stack, null);

        verify(clouderaManagerResourceApi, never()).updateConfig(anyString(), any());
        verify(modificationService, never()).stopCluster();
        verify(clouderaManagerPollingServiceProvider, never()).kerberosConfigurePollingService(any(), any(), any());
        verify(modificationService, never()).startCluster(anySet());
    }

    @Test
    public void deleteCredentials() throws ApiException, CloudbreakException {
        when(clouderaManagerResourceApi.deleteCredentialsCommand("all")).thenReturn(new ApiCommand().id(BigDecimal.ZERO));

        underTest.deleteCredentials(client, clientConfig, stack);

        verify(modificationService).stopCluster();
        clouderaManagerPollingServiceProvider.kerberosConfigurePollingService(stack, client, BigDecimal.ZERO);
    }
}

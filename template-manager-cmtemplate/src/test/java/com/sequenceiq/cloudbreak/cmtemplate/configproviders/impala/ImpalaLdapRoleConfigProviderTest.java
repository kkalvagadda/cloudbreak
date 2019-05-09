package com.sequenceiq.cloudbreak.cmtemplate.configproviders.impala;

import static com.sequenceiq.cloudbreak.TestUtil.kerberosConfigFreeipa;
import static com.sequenceiq.cloudbreak.TestUtil.kerberosConfigMit;
import static com.sequenceiq.cloudbreak.TestUtil.ldapConfig;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cloudera.api.swagger.model.ApiClusterTemplateConfig;
import com.sequenceiq.cloudbreak.cmtemplate.CmTemplateProcessor;
import com.sequenceiq.cloudbreak.template.TemplatePreparationObject;
import com.sequenceiq.cloudbreak.template.TemplatePreparationObject.Builder;

public class ImpalaLdapRoleConfigProviderTest {

    private static final String BIND_DN = "admin-user";

    private static final String BIND_PASSWORD = "admin-password";

    private static final String IMPALAD = "IMPALAD";

    @Mock
    private CmTemplateProcessor templateProcessor;

    @InjectMocks
    private ImpalaLdapRoleConfigProvider underTest;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getRoleConfigWhenBadRole() {
        TemplatePreparationObject tpo = new Builder()
                .withLdapConfig(ldapConfig(), BIND_DN, BIND_PASSWORD)
                .build();

        List<ApiClusterTemplateConfig> result = underTest.getRoleConfig("DUMMY", null, tpo);
        Map<String, String> paramToValueMap =
                result.stream().filter(config -> config.getValue() != null)
                        .collect(Collectors.toMap(ApiClusterTemplateConfig::getName, ApiClusterTemplateConfig::getValue));
        assertThat(paramToValueMap).isEmpty();
        Map<String, String> paramToVariableMap =
                result.stream().filter(config -> config.getVariable() != null)
                        .collect(Collectors.toMap(ApiClusterTemplateConfig::getName, ApiClusterTemplateConfig::getVariable));
        assertThat(paramToVariableMap).isEmpty();
    }

    @Test
    public void getRoleConfigWhenImpaladAndNoKerberos() {
        TemplatePreparationObject tpo = new Builder()
                .withLdapConfig(ldapConfig(), BIND_DN, BIND_PASSWORD)
                .build();

        List<ApiClusterTemplateConfig> result = underTest.getRoleConfig(IMPALAD, null, tpo);
        Map<String, String> paramToValueMap =
                result.stream().filter(config -> config.getValue() != null)
                        .collect(Collectors.toMap(ApiClusterTemplateConfig::getName, ApiClusterTemplateConfig::getValue));
        assertThat(paramToValueMap).isEmpty();
        Map<String, String> paramToVariableMap =
                result.stream().filter(config -> config.getVariable() != null)
                        .collect(Collectors.toMap(ApiClusterTemplateConfig::getName, ApiClusterTemplateConfig::getVariable));
        assertThat(paramToVariableMap).isEmpty();
    }

    @Test
    public void getRoleConfigWhenImpaladAndKerberosFreeIpa() {
        TemplatePreparationObject tpo = new Builder()
                .withLdapConfig(ldapConfig(), BIND_DN, BIND_PASSWORD)
                .withKerberosConfig(kerberosConfigFreeipa())
                .build();

        List<ApiClusterTemplateConfig> result = underTest.getRoleConfig(IMPALAD, null, tpo);
        Map<String, String> paramToValueMap =
                result.stream().filter(config -> config.getValue() != null)
                        .collect(Collectors.toMap(ApiClusterTemplateConfig::getName, ApiClusterTemplateConfig::getValue));
        assertThat(paramToValueMap).containsOnly(
                new SimpleEntry<>("impalad_ldap_ca_certificate", "/etc/ipa/ca.crt")
        );
        Map<String, String> paramToVariableMap =
                result.stream().filter(config -> config.getVariable() != null)
                        .collect(Collectors.toMap(ApiClusterTemplateConfig::getName, ApiClusterTemplateConfig::getVariable));
        assertThat(paramToVariableMap).isEmpty();
    }

    @Test
    public void getRoleConfigWhenImpaladAndKerberosMit() {
        TemplatePreparationObject tpo = new Builder()
                .withLdapConfig(ldapConfig(), BIND_DN, BIND_PASSWORD)
                .withKerberosConfig(kerberosConfigMit())
                .build();

        List<ApiClusterTemplateConfig> result = underTest.getRoleConfig(IMPALAD, null, tpo);
        Map<String, String> paramToValueMap =
                result.stream().filter(config -> config.getValue() != null)
                        .collect(Collectors.toMap(ApiClusterTemplateConfig::getName, ApiClusterTemplateConfig::getValue));
        assertThat(paramToValueMap).isEmpty();
        Map<String, String> paramToVariableMap =
                result.stream().filter(config -> config.getVariable() != null)
                        .collect(Collectors.toMap(ApiClusterTemplateConfig::getName, ApiClusterTemplateConfig::getVariable));
        assertThat(paramToVariableMap).isEmpty();
    }

    @Test
    public void getServiceType() {
        assertThat(underTest.getServiceType()).isEqualTo("IMPALA");
    }

    @Test
    public void getRoleTypes() {
        assertThat(underTest.getRoleTypes()).containsOnly(IMPALAD);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void isConfigurationNeededTrue() {
        when(templateProcessor.isRoleTypePresentInService(anyString(), any(List.class))).thenReturn(true);

        TemplatePreparationObject tpo = new Builder().withLdapConfig(ldapConfig(), BIND_DN, BIND_PASSWORD).build();

        boolean result = underTest.isConfigurationNeeded(templateProcessor, tpo);
        assertThat(result).isTrue();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void isConfigurationNeededFalseWhenNoImpalaOnCluster() {
        when(templateProcessor.isRoleTypePresentInService(anyString(), any(List.class))).thenReturn(false);

        TemplatePreparationObject tpo = new Builder().withLdapConfig(ldapConfig(), BIND_DN, BIND_PASSWORD).build();

        boolean result = underTest.isConfigurationNeeded(templateProcessor, tpo);
        assertThat(result).isFalse();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void isConfigurationNeededFalseWhenNoLdap() {
        when(templateProcessor.isRoleTypePresentInService(anyString(), any(List.class))).thenReturn(true);

        TemplatePreparationObject tpo = new Builder().build();

        boolean result = underTest.isConfigurationNeeded(templateProcessor, tpo);
        assertThat(result).isFalse();
    }

}

package com.sequenceiq.cloudbreak.cmtemplate.configproviders.impala;

import static com.sequenceiq.cloudbreak.cmtemplate.configproviders.ConfigUtils.config;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.cloudera.api.swagger.model.ApiClusterTemplateConfig;
import com.sequenceiq.cloudbreak.cmtemplate.CmTemplateProcessor;
import com.sequenceiq.cloudbreak.cmtemplate.configproviders.AbstractRoleConfigConfigProvider;
import com.sequenceiq.cloudbreak.domain.KerberosConfig;
import com.sequenceiq.cloudbreak.template.TemplatePreparationObject;
import com.sequenceiq.cloudbreak.template.views.HostgroupView;
import com.sequenceiq.cloudbreak.type.KerberosType;

@Component
public class ImpalaLdapRoleConfigProvider extends AbstractRoleConfigConfigProvider {

    @Override
    protected List<ApiClusterTemplateConfig> getRoleConfig(String roleType, HostgroupView hostGroupView, TemplatePreparationObject source) {
        switch (roleType) {
            case ImpalaRoles.ROLE_IMPALAD:
                Optional<KerberosConfig> kerberosConfig = source.getKerberosConfig();
                if (kerberosConfig.isPresent() && kerberosConfig.get().getType() == KerberosType.FREEIPA) {
                    return List.of(
                            config("impalad_ldap_ca_certificate", "/etc/ipa/ca.crt")
                    );
                } else {
                    return List.of();
                }
            default:
                return List.of();
        }
    }

    @Override
    public String getServiceType() {
        return ImpalaRoles.SERVICE_IMPALA;
    }

    @Override
    public List<String> getRoleTypes() {
        return Collections.singletonList(ImpalaRoles.ROLE_IMPALAD);
    }

    @Override
    public boolean isConfigurationNeeded(CmTemplateProcessor cmTemplateProcessor, TemplatePreparationObject source) {
        return source.getLdapConfig().isPresent() && cmTemplateProcessor.isRoleTypePresentInService(getServiceType(), getRoleTypes());
    }

}

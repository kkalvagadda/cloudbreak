package com.sequenceiq.cloudbreak.cmtemplate.configproviders.yarn;

import static com.sequenceiq.cloudbreak.cmtemplate.configproviders.ConfigUtils.config;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

import com.cloudera.api.swagger.model.ApiClusterTemplateConfig;
import com.sequenceiq.cloudbreak.api.endpoint.v4.ExposedService;
import com.sequenceiq.cloudbreak.cmtemplate.CmTemplateComponentConfigProvider;
import com.sequenceiq.cloudbreak.cmtemplate.CmTemplateProcessor;
import com.sequenceiq.cloudbreak.cmtemplate.configproviders.ConfigUtils;
import com.sequenceiq.cloudbreak.template.TemplatePreparationObject;

@Component
public class YarnConfigProvider implements CmTemplateComponentConfigProvider {

    private static final String HADOOP_PROXYUSER_KNOX_GROUPS = "hadoop.proxyuser.knox.groups";

    private static final String HADOOP_PROXYUSER_KNOX_HOSTS = "hadoop.proxyuser.knox.hosts";

    private static final String YARN_CORE_SITE_SAFETY_VALVE = "yarn_core_site_safety_valve";

    @Override
    public List<ApiClusterTemplateConfig> getServiceConfigs(CmTemplateProcessor templateProcessor, TemplatePreparationObject templatePreparationObject) {
        return List.of(
                config(YARN_CORE_SITE_SAFETY_VALVE,
                        ConfigUtils.getSafetyValveProperty(HADOOP_PROXYUSER_KNOX_GROUPS, "*")
                                + ConfigUtils.getSafetyValveProperty(HADOOP_PROXYUSER_KNOX_HOSTS, "*")));
    }

    @Override
    public String getServiceType() {
        return YarnRoles.YARN;
    }

    @Override
    public List<String> getRoleTypes() {
        return List.of(YarnRoles.YARN);
    }

    @Override
    public boolean isConfigurationNeeded(CmTemplateProcessor cmTemplateProcessor, TemplatePreparationObject source) {
        return Objects.nonNull(source.getGatewayView())
                && Objects.nonNull(source.getGatewayView().getExposedServices())
                && source.getGatewayView().getExposedServices().getValue().contains(ExposedService.RESOURCEMANAGER_WEB.getKnoxService());
    }

}

package com.sequenceiq.cloudbreak.cmtemplate.configproviders.ranger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cloudera.api.swagger.model.ApiClusterTemplateConfig;
import com.cloudera.api.swagger.model.ApiClusterTemplateVariable;
import com.sequenceiq.cloudbreak.cmtemplate.CmTemplateComponentConfigProvider;
import com.sequenceiq.cloudbreak.cmtemplate.CmTemplateProcessor;
import com.sequenceiq.cloudbreak.template.TemplatePreparationObject;
import com.sequenceiq.cloudbreak.template.views.LdapView;

@Component
public class RangerLdapConfigProvider implements CmTemplateComponentConfigProvider {

    private static final String RANGER_RANGER_AUTHENTICATION_METHOD = "ranger-ranger.authentication.method";

    private static final String RANGER_RANGER_LDAP_AD_DOMAIN = "ranger-ranger.ldap.ad.domain";

    private static final String RANGER_RANGER_LDAP_AD_URL = "ranger-ranger.ldap.ad.url";

    private static final String RANGER_RANGER_LDAP_AD_BIND_DN = "ranger-ranger.ldap.ad.bind.dn";

    private static final String RANGER_RANGER_LDAP_AD_BIND_PASSWORD = "ranger-ranger_ldap_ad_bind_password";

    private static final String RANGER_RANGER_LDAP_AD_BASE_DN = "ranger-ranger.ldap.ad.base.dn";

    private static final String RANGER_RANGER_LDAP_AD_USER_SEARCHFILTER = "ranger-ranger.ldap.ad.user.searchfilter";

    private static final String RANGER_RANGER_LDAP_URL = "ranger-ranger.ldap.url";

    private static final String RANGER_RANGER_LDAP_BIND_DN = "ranger-ranger.ldap.bind.dn";

    private static final String RANGER_RANGER_LDAP_BIND_PASSWORD = "ranger-ranger_ldap_bind_password";

    private static final String RANGER_RANGER_LDAP_BASE_DN = "ranger-ranger.ldap.base.dn";

    private static final String RANGER_RANGER_LDAP_USER_SEARCHFILTER = "ranger-ranger.ldap.user.searchfilter";

    private static final String RANGER_RANGER_LDAP_USER_DNPATTERN = "ranger-ranger.ldap.user.dnpattern";

    private static final String RANGER_RANGER_LDAP_GROUP_SEARCHBASE = "ranger-ranger.ldap.group.searchbase";

    private static final String RANGER_RANGER_LDAP_GROUP_ROLEATTRIBUTE = "ranger-ranger.ldap.group.roleattribute";

    private static final String RANGER_RANGER_USERSYNC_LDAP_URL = "ranger-ranger.usersync.ldap.url";

    private static final String RANGER_RANGER_USERSYNC_LDAP_BINDDN = "ranger-ranger.usersync.ldap.binddn";

    private static final String RANGER_RANGER_USERSYNC_LDAP_LDAPBINDPASSWORD = "ranger-ranger_usersync_ldap_ldapbindpassword";

    private static final String RANGER_RANGER_USERSYNC_LDAP_USER_NAMEATTRIBUTE = "ranger-ranger.usersync.ldap.user.nameattribute";

    private static final String RANGER_RANGER_USERSYNC_LDAP_USER_SEARCHBASE = "ranger-ranger.usersync.ldap.user.searchbase";

    private static final String RANGER_RANGER_USERSYNC_LDAP_USER_OBJECTCLASS = "ranger-ranger.usersync.ldap.user.objectclass";

    private static final String RANGER_RANGER_USERSYNC_GROUP_MEMBERATTRIBUTENAME = "ranger-ranger.usersync.group.memberattributename";

    private static final String RANGER_RANGER_USERSYNC_GROUP_NAMEATTRIBUTE = "ranger-ranger.usersync.group.nameattribute";

    private static final String RANGER_RANGER_USERSYNC_GROUP_OBJECTCLASS = "ranger-ranger.usersync.group.objectclass";

    private static final String RANGER_RANGER_USERSYNC_GROUP_SEARCHBASE = "ranger-ranger.usersync.group.searchbase";

    private static final String RANGER_RANGER_USERSYNC_GROUP_SEARCHFILTER = "ranger-ranger.usersync.group.searchfilter";

    private static final String RANGER_RANGER_USERSYNC_GROUP_BASED_ROLE_ASSIGNMENT_RULES = "ranger-ranger.usersync.group.based.role.assignment.rules";

    @Override
    public List<ApiClusterTemplateConfig> getServiceConfigs(TemplatePreparationObject templatePreparationObject) {
        List<ApiClusterTemplateConfig> result = new ArrayList<>();
        result.add(new ApiClusterTemplateConfig().name("ranger.authentication.method").variable(RANGER_RANGER_AUTHENTICATION_METHOD));
        result.add(new ApiClusterTemplateConfig().name("ranger.ldap.ad.domain").variable(RANGER_RANGER_LDAP_AD_DOMAIN));
        result.add(new ApiClusterTemplateConfig().name("ranger.ldap.ad.url").variable(RANGER_RANGER_LDAP_AD_URL));
        result.add(new ApiClusterTemplateConfig().name("ranger.ldap.ad.bind.dn").variable(RANGER_RANGER_LDAP_AD_BIND_DN));
        result.add(new ApiClusterTemplateConfig().name("ranger_ldap_ad_bind_password").variable(RANGER_RANGER_LDAP_AD_BIND_PASSWORD));
        result.add(new ApiClusterTemplateConfig().name("ranger.ldap.ad.base.dn").variable(RANGER_RANGER_LDAP_AD_BASE_DN));
        result.add(new ApiClusterTemplateConfig().name("ranger.ldap.ad.user.searchfilter").variable(RANGER_RANGER_LDAP_AD_USER_SEARCHFILTER));
        result.add(new ApiClusterTemplateConfig().name("ranger.ldap.url").variable(RANGER_RANGER_LDAP_URL));
        result.add(new ApiClusterTemplateConfig().name("ranger.ldap.bind.dn").variable(RANGER_RANGER_LDAP_BIND_DN));
        result.add(new ApiClusterTemplateConfig().name("ranger_ldap_bind_password").variable(RANGER_RANGER_LDAP_BIND_PASSWORD));
        result.add(new ApiClusterTemplateConfig().name("ranger.ldap.base.dn").variable(RANGER_RANGER_LDAP_BASE_DN));
        result.add(new ApiClusterTemplateConfig().name("ranger.ldap.user.searchfilter").variable(RANGER_RANGER_LDAP_USER_SEARCHFILTER));
        result.add(new ApiClusterTemplateConfig().name("ranger.ldap.user.dnpattern").variable(RANGER_RANGER_LDAP_USER_DNPATTERN));
        result.add(new ApiClusterTemplateConfig().name("ranger.ldap.group.searchbase").variable(RANGER_RANGER_LDAP_GROUP_SEARCHBASE));
        result.add(new ApiClusterTemplateConfig().name("ranger.ldap.group.roleattribute").variable(RANGER_RANGER_LDAP_GROUP_ROLEATTRIBUTE));
        // TODO Add ranger.ldap.group.searchfilter cfg if needed

        result.add(new ApiClusterTemplateConfig().name("ranger.usersync.enabled").value(Boolean.TRUE.toString()));
        result.add(new ApiClusterTemplateConfig().name("ranger.usersync.source.impl.class")
                .value("org.apache.ranger.ldapusersync.process.LdapUserGroupBuilder"));
        result.add(new ApiClusterTemplateConfig().name("ranger.usersync.ldap.url").variable(RANGER_RANGER_USERSYNC_LDAP_URL));
        result.add(new ApiClusterTemplateConfig().name("ranger.usersync.ldap.binddn").variable(RANGER_RANGER_USERSYNC_LDAP_BINDDN));
        result.add(new ApiClusterTemplateConfig().name("ranger_usersync_ldap_ldapbindpassword").variable(RANGER_RANGER_USERSYNC_LDAP_LDAPBINDPASSWORD));
        result.add(new ApiClusterTemplateConfig().name("ranger.usersync.ldap.user.nameattribute").variable(RANGER_RANGER_USERSYNC_LDAP_USER_NAMEATTRIBUTE));
        result.add(new ApiClusterTemplateConfig().name("ranger.usersync.ldap.user.searchbase").variable(RANGER_RANGER_USERSYNC_LDAP_USER_SEARCHBASE));
        result.add(new ApiClusterTemplateConfig().name("ranger.usersync.ldap.user.objectclass").variable(RANGER_RANGER_USERSYNC_LDAP_USER_OBJECTCLASS));
        result.add(new ApiClusterTemplateConfig().name("ranger.usersync.ldap.deltasync").value(Boolean.FALSE.toString()));
        result.add(new ApiClusterTemplateConfig().name("ranger.usersync.group.searchenabled").value(Boolean.TRUE.toString()));
        result.add(new ApiClusterTemplateConfig().name("ranger.usersync.group.memberattributename")
                .variable(RANGER_RANGER_USERSYNC_GROUP_MEMBERATTRIBUTENAME));
        result.add(new ApiClusterTemplateConfig().name("ranger.usersync.group.nameattribute").variable(RANGER_RANGER_USERSYNC_GROUP_NAMEATTRIBUTE));
        result.add(new ApiClusterTemplateConfig().name("ranger.usersync.group.objectclass").variable(RANGER_RANGER_USERSYNC_GROUP_OBJECTCLASS));
        result.add(new ApiClusterTemplateConfig().name("ranger.usersync.group.searchbase").variable(RANGER_RANGER_USERSYNC_GROUP_SEARCHBASE));
        result.add(new ApiClusterTemplateConfig().name("ranger.usersync.group.searchfilter").variable(RANGER_RANGER_USERSYNC_GROUP_SEARCHFILTER));
        result.add(new ApiClusterTemplateConfig().name("ranger.usersync.group.based.role.assignment.rules")
                .variable(RANGER_RANGER_USERSYNC_GROUP_BASED_ROLE_ASSIGNMENT_RULES));
        // TODO Add ranger.usersync.ldap.searchBase cfg if needed
        // TODO Add ranger.usersync.ldap.user.searchfilter cfg if needed
        // TODO Add ranger.usersync.ldap.user.nameattribute cfg if needed
        // TODO Add ranger.usersync.ldap.user.groupnameattribute cfg if needed
        return result;
    }

    @Override
    public List<ApiClusterTemplateVariable> getServiceConfigVariables(TemplatePreparationObject source) {
        List<ApiClusterTemplateVariable> result = new ArrayList<>();
        LdapView ldapView = source.getLdapConfig().get();
        result.add(new ApiClusterTemplateVariable().name(RANGER_RANGER_AUTHENTICATION_METHOD).value(ldapView.getDirectoryType().toString()));
        result.add(new ApiClusterTemplateVariable().name(RANGER_RANGER_LDAP_AD_DOMAIN).value(" "));
        result.add(new ApiClusterTemplateVariable().name(RANGER_RANGER_LDAP_AD_URL).value(ldapView.getConnectionURL()));
        result.add(new ApiClusterTemplateVariable().name(RANGER_RANGER_LDAP_AD_BIND_DN).value(ldapView.getBindDn()));
        result.add(new ApiClusterTemplateVariable().name(RANGER_RANGER_LDAP_AD_BIND_PASSWORD).value(ldapView.getBindPassword()));
        result.add(new ApiClusterTemplateVariable().name(RANGER_RANGER_LDAP_AD_BASE_DN).value(ldapView.getUserSearchBase()));
        result.add(new ApiClusterTemplateVariable().name(RANGER_RANGER_LDAP_AD_USER_SEARCHFILTER).value("(cn={0})"));
        result.add(new ApiClusterTemplateVariable().name(RANGER_RANGER_LDAP_URL).value(ldapView.getConnectionURL()));
        result.add(new ApiClusterTemplateVariable().name(RANGER_RANGER_LDAP_BIND_DN).value(ldapView.getBindDn()));
        result.add(new ApiClusterTemplateVariable().name(RANGER_RANGER_LDAP_BIND_PASSWORD).value(ldapView.getBindPassword()));
        result.add(new ApiClusterTemplateVariable().name(RANGER_RANGER_LDAP_BASE_DN).value(ldapView.getUserSearchBase()));
        result.add(new ApiClusterTemplateVariable().name(RANGER_RANGER_LDAP_USER_SEARCHFILTER).value("(cn={0})"));
        result.add(new ApiClusterTemplateVariable().name(RANGER_RANGER_LDAP_USER_DNPATTERN).value(ldapView.getUserDnPattern()));
        result.add(new ApiClusterTemplateVariable().name(RANGER_RANGER_LDAP_GROUP_SEARCHBASE).value(ldapView.getGroupSearchBase()));
        result.add(new ApiClusterTemplateVariable().name(RANGER_RANGER_LDAP_GROUP_ROLEATTRIBUTE).value(ldapView.getGroupNameAttribute()));
        // TODO Add ranger.ldap.group.searchfilter var if needed

        result.add(new ApiClusterTemplateVariable().name(RANGER_RANGER_USERSYNC_LDAP_URL).value(ldapView.getConnectionURL()));
        result.add(new ApiClusterTemplateVariable().name(RANGER_RANGER_USERSYNC_LDAP_BINDDN).value(ldapView.getBindDn()));
        result.add(new ApiClusterTemplateVariable().name(RANGER_RANGER_USERSYNC_LDAP_LDAPBINDPASSWORD).value(ldapView.getBindPassword()));
        result.add(new ApiClusterTemplateVariable().name(RANGER_RANGER_USERSYNC_LDAP_USER_NAMEATTRIBUTE).value(ldapView.getUserNameAttribute()));
        result.add(new ApiClusterTemplateVariable().name(RANGER_RANGER_USERSYNC_LDAP_USER_SEARCHBASE).value(ldapView.getUserSearchBase()));
        result.add(new ApiClusterTemplateVariable().name(RANGER_RANGER_USERSYNC_LDAP_USER_OBJECTCLASS).value(ldapView.getUserObjectClass()));
        result.add(new ApiClusterTemplateVariable().name(RANGER_RANGER_USERSYNC_GROUP_MEMBERATTRIBUTENAME).value(ldapView.getGroupMemberAttribute()));
        result.add(new ApiClusterTemplateVariable().name(RANGER_RANGER_USERSYNC_GROUP_NAMEATTRIBUTE).value(ldapView.getGroupNameAttribute()));
        result.add(new ApiClusterTemplateVariable().name(RANGER_RANGER_USERSYNC_GROUP_OBJECTCLASS).value(ldapView.getGroupObjectClass()));
        result.add(new ApiClusterTemplateVariable().name(RANGER_RANGER_USERSYNC_GROUP_SEARCHBASE).value(ldapView.getGroupSearchBase()));
        result.add(new ApiClusterTemplateVariable().name(RANGER_RANGER_USERSYNC_GROUP_SEARCHFILTER).value(" "));
        result.add(new ApiClusterTemplateVariable().name(RANGER_RANGER_USERSYNC_GROUP_BASED_ROLE_ASSIGNMENT_RULES)
                .value(String.format("&ROLE_SYS_ADMIN:g:%s", ldapView.getAdminGroup())));
        // TODO Add ranger.usersync.ldap.searchBase var if needed
        // TODO Add ranger.usersync.ldap.user.searchfilter var if needed
        // TODO Add ranger.usersync.ldap.user.nameattribute var if needed
        // TODO Add ranger.usersync.ldap.user.groupnameattribute var if needed
        return result;
    }

    @Override
    public String getServiceType() {
        return "RANGER";
    }

    @Override
    public List<String> getRoleTypes() {
        return Arrays.asList("RANGER_ADMIN", "RANGER_USERSYNC");
    }

    @Override
    public boolean isConfigurationNeeded(CmTemplateProcessor cmTemplateProcessor, TemplatePreparationObject source) {
        return source.getLdapConfig().isPresent() && cmTemplateProcessor.isRoleTypePresentInService(getServiceType(), getRoleTypes());
    }

}

package com.sequenceiq.environment.api.doc;

public class ModelDescriptions {

    public static final String ID = "id of the resource";
    public static final String NAME = "name of the resource";
    public static final String DESCRIPTION = "description of the resource";

    private ModelDescriptions() {
    }

    public static class ClusterModelDescription {
        public static final String RDSCONFIG_NAMES = "RDS configuration names for the cluster";
        public static final String LDAP_CONFIG_NAME = "LDAP config name for the cluster";
        public static final String KERBEROSCONFIG_NAME = "Kerberos config name for the cluster";
        public static final String AMBARI_URL = "Ambari url";
    }

    public static class ConnectorModelDescription {
        public static final String REGIONS = "regions";
        public static final String REGION_DISPLAYNAMES = "regions with displayNames";
    }

    public static class LdapConfigModelDescription {
        public static final String REQUEST = "LDAP config request";
        public static final String RESPONSE = "LDAP config response";
    }

    public static class RDSConfigModelDescription {
        public static final String REQUEST = "RDS config request";
        public static final String RESPONSE = "RDS config response";
    }

    public static class KerberosConfigModelDescription {
        public static final String REQUEST = "Kerberos config request";
        public static final String RESPONSE = "Kerberos config response";
    }

    public static class EnvironmentRequestModelDescription {
        public static final String CREDENTIAL_NAME = "Name of the credential of the environment. If the name is given, "
                + "the detailed credential is ignored in the request.";
        public static final String CREDENTIAL = "If credentialName is not specified, the credential is used to create the new credential for the environment.";
        public static final String PROXY_CONFIGS = "Name of the proxy configurations to be attached to the environment.";
        public static final String RDS_CONFIGS = "Name of the RDS configurations to be attached to the environment.";
        public static final String KUBERNETES_CONFIGS = "Name of the Kubernetes configurations to be attached to the environment.";
        public static final String LDAP_CONFIGS = "Name of the LDAP configurations to be attached to the environment.";
        public static final String REGIONS = "Regions of the environment.";
        public static final String LOCATION = "Location of the environment.";
        public static final String LONGITUDE = "Location longitude of the environment.";
        public static final String LATITUDE = "Location latitude of the environment.";
        public static final String KERBEROS_CONFIGS = "Name of Kerberos configs to be attached to the environment.";
        public static final String LOCATION_DISPLAY_NAME = "Display name of the location of the environment.";
        public static final String NETWORK = "Network related specifics of the environment.";
    }

    public static class EnvironmentResponseModelDescription {
        public static final String CREDENTIAL_NAME = "Name of the credential of the environment.";
        public static final String CREDENTIAL = "Credential of the environment.";
        public static final String PROXY_CONFIGS = "Proxy configurations in the environment.";
        public static final String RDS_CONFIGS = "RDS configurations in the environment.";
        public static final String KUBERNETES_CONFIGS = "Kubernetes configurations in the environment.";
        public static final String LDAP_CONFIGS = "LDAP configurations in the environment.";
        public static final String REGIONS = "Regions of the environment.";
        public static final String CLOUD_PLATFORM = "Cloud platform of the environment.";
        public static final String LOCATION = "Location of the environment.";
        public static final String WORKLOAD_CLUSTERS = "Workload clusters created in the environment.";
        public static final String WORKLOAD_CLUSTER_NAMES = "Names of the workload clusters created in the environment.";
        public static final String SDX_CLUSTERS = "Datalake clusters created in the environment.";
        public static final String SDX_CLUSTER_NAMES = "Names of the datalake clusters created in the environment.";
        public static final String SDX_RESOURCES_NAMES = "Datalake cluster resources registered to the environment.";
        public static final String SDX_RESOURCES = "Datalake cluster resources registered to the environment.";
        public static final String KERBEROS_CONFIGS = "Kerberos configs in the environment.";
        public static final String NETWORK = "Network related specifics of the environment.";
    }

    public static class DatalakeResourcesDescription {
        public static final String SERVICE_DESCRIPTORS = "Descriptors of the sdx services";
        public static final String SERVICE_NAME = "Name of the datalake service";
        public static final String BLUEPRINT_PARAMS = "Bluepirnt parameters from the datalake services";
        public static final String COMPONENT_HOSTS = "Component hosts of the datalake services";
    }

    public static class EnvironmentNetworkDescription {
        public static final String SUBNET_IDS = "Subnet ids of the specified networks";
        public static final String AWS_SPECIFIC_PARAMETERS = "Subnet ids of the specified networks";
        public static final String AZURE_SPECIFIC_PARAMETERS = "Subnet ids of the specified networks";
        public static final String AWS_VPC_ID = "Subnet ids of the specified networks";
        public static final String AZURE_RESOURCE_GROUP_NAME = "Subnet ids of the specified networks";
        public static final String AZURE_NETWORK_ID = "Subnet ids of the specified networks";
        public static final String AZURE_NO_PUBLIC_IP = "Subnet ids of the specified networks";
        public static final String AZURE_NO_FIREWALL_RULES = "Subnet ids of the specified networks";
    }

}


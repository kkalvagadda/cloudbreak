package com.sequenceiq.environment.api.doc;

public class OperationDescriptions {

    private OperationDescriptions() {
    }

    public static class SecretResponseModelDescription {
        public static final String ENGINE_PATH = "Engine path of the secret.";
        public static final String SECRET_PATH = "Path of the secret.";
    }

    public static class EnvironmentOpDescription {
        public static final String CREATE = "Create an environment.";
        public static final String GET = "Get an environment.";
        public static final String ATTACH_RESOURCES = "Attach resources to an environment.";
        public static final String DETACH_RESOURCES = "Detach resources from an environment.";
        public static final String LIST = "List all environments in the workspace.";
        public static final String DELETE = "Delete an environment. Only possible if no cluster is running in the environment.";
        public static final String CHANGE_CREDENTIAL = "Changes the credential of the environment and the clusters in the environment.";
        public static final String REGISTER_EXTERNAL_SDX = "Register external datalake";
        public static final String EDIT = "Edit and environment. Location, regions and description can be changed.";
    }

    public static class UtilityOpDescription {
        public static final String SDX_PREREQUISITES = "returns SDX prerequisites";
    }

}

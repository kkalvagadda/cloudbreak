package com.sequenceiq.environment.api.environment.requests;

public interface CredentialAwareEnvRequest {

    String getCredentialName();

    void setCredentialName(String credentialName);

    Long getCredential();

    void setCredential(Long credential);

}

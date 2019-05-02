package com.sequenceiq.environment.api.environment.requests;

import com.sequenceiq.environment.api.doc.ModelDescriptions.EnvironmentRequestModelDescription;

import io.swagger.annotations.ApiModelProperty;

public class EnvironmentChangeCredentialRequest implements CredentialAwareEnvRequest {

    @ApiModelProperty(EnvironmentRequestModelDescription.CREDENTIAL_NAME)
    private String credentialName;

    @ApiModelProperty(EnvironmentRequestModelDescription.CREDENTIAL)
    private Long credential;

    @Override
    public String getCredentialName() {
        return credentialName;
    }

    @Override
    public void setCredentialName(String credentialName) {
        this.credentialName = credentialName;
    }

    @Override
    public Long getCredential() {
        return credential;
    }

    @Override
    public void setCredential(Long credential) {
        this.credential = credential;
    }

}

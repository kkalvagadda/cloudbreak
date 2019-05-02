package com.sequenceiq.environment.api.environment;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Controller;

import com.sequenceiq.environment.api.environment.requests.SdxPrerequisiteRequest;
import com.sequenceiq.environment.api.environment.requests.EnvironmentAttachRequest;
import com.sequenceiq.environment.api.environment.requests.EnvironmentChangeCredentialRequest;
import com.sequenceiq.environment.api.environment.requests.EnvironmentDetachRequest;
import com.sequenceiq.environment.api.environment.requests.EnvironmentEditRequest;
import com.sequenceiq.environment.api.environment.requests.EnvironmentRequest;
import com.sequenceiq.environment.api.environment.requests.RegisterSdxRequest;
import com.sequenceiq.environment.api.environment.responses.SdxPrerequisiteResponse;
import com.sequenceiq.environment.api.environment.responses.DetailedEnvironmentResponse;
import com.sequenceiq.environment.api.environment.responses.SimpleEnvironmentResponse;
import com.sequenceiq.environment.api.environment.responses.SimpleEnvironmentResponses;
import com.sequenceiq.environment.api.environment.responses.WelcomeResponse;

@Controller
public class EnvironmentController implements EnvironmentEndpoint {

    @Override
    public WelcomeResponse welcome() {
        return null;
    }

    @Override
    public DetailedEnvironmentResponse post(@Valid EnvironmentRequest request) {
        return null;
    }

    @Override
    public DetailedEnvironmentResponse get(String environmentName) {
        return null;
    }

    @Override
    public SimpleEnvironmentResponse delete(String environmentName) {
        return null;
    }

    @Override
    public DetailedEnvironmentResponse edit(String environmentName, @NotNull EnvironmentEditRequest request) {
        return null;
    }

    @Override
    public SimpleEnvironmentResponses list(Long workspaceId) {
        return null;
    }

    @Override
    public DetailedEnvironmentResponse attach(String environmentName, @Valid EnvironmentAttachRequest request) {
        return null;
    }

    @Override
    public DetailedEnvironmentResponse detach(String environmentName, @Valid EnvironmentDetachRequest request) {
        return null;
    }

    @Override
    public DetailedEnvironmentResponse changeCredential(String environmentName, @Valid EnvironmentChangeCredentialRequest request) {
        return null;
    }

    @Override
    public DetailedEnvironmentResponse registerExternalSdx(String environmentName, @Valid RegisterSdxRequest request) {
        return null;
    }

    @Override
    public SdxPrerequisiteResponse registerSdxPrerequisite(String environmentName, @Valid SdxPrerequisiteRequest sdxPrerequisiteRequest) {
        return null;
    }

}

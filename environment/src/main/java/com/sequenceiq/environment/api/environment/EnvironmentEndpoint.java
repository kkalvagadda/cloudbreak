package com.sequenceiq.environment.api.environment;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.sequenceiq.environment.api.doc.Notes;
import com.sequenceiq.environment.api.doc.OperationDescriptions.EnvironmentOpDescription;
import com.sequenceiq.environment.api.doc.OperationDescriptions.UtilityOpDescription;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/env")
@Consumes(APPLICATION_JSON)
@Api(value = "/env", protocols = "http,https")
public interface EnvironmentEndpoint {

    @GET
    @Path("welcome")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "welcome", produces = APPLICATION_JSON, nickname = "getWelcomeMessage")
    WelcomeResponse welcome();

    @POST
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = EnvironmentOpDescription.CREATE, produces = APPLICATION_JSON, notes = Notes.ENVIRONMENT_NOTES,
            nickname = "createEnvironment")
    DetailedEnvironmentResponse post(@Valid EnvironmentRequest request);

    @GET
    @Path("/{name}")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = EnvironmentOpDescription.GET, produces = APPLICATION_JSON, notes = Notes.ENVIRONMENT_NOTES,
            nickname = "getEnvironment")
    DetailedEnvironmentResponse get(@PathParam("name") String environmentName);

    @DELETE
    @Path("/{name}")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = EnvironmentOpDescription.DELETE, produces = APPLICATION_JSON, notes = Notes.ENVIRONMENT_NOTES,
            nickname = "deleteEnvironment")
    SimpleEnvironmentResponse delete(@PathParam("name") String environmentName);

    @PUT
    @Path("/{name}")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = EnvironmentOpDescription.EDIT, produces = APPLICATION_JSON, notes = Notes.ENVIRONMENT_NOTES,
            nickname = "editEnvironment")
    DetailedEnvironmentResponse edit(@PathParam("name") String environmentName,
            @NotNull EnvironmentEditRequest request);

    @GET
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = EnvironmentOpDescription.LIST, produces = APPLICATION_JSON, notes = Notes.ENVIRONMENT_NOTES,
            nickname = "listEnvironment")
    SimpleEnvironmentResponses list(@PathParam("workspaceId") Long workspaceId);

    @PUT
    @Path("/{name}/attach")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = EnvironmentOpDescription.ATTACH_RESOURCES, produces = APPLICATION_JSON, notes = Notes.ENVIRONMENT_NOTES,
            nickname = "attachResourcesToEnvironment")
    DetailedEnvironmentResponse attach(@PathParam("name") String environmentName,
            @Valid EnvironmentAttachRequest request);

    @PUT
    @Path("/{name}/detach")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = EnvironmentOpDescription.DETACH_RESOURCES, produces = APPLICATION_JSON, notes = Notes.ENVIRONMENT_NOTES,
            nickname = "detachResourcesFromEnvironment")
    DetailedEnvironmentResponse detach(@PathParam("name") String environmentName,
            @Valid EnvironmentDetachRequest request);

    @PUT
    @Path("/{name}/change_credential")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = EnvironmentOpDescription.CHANGE_CREDENTIAL, produces = APPLICATION_JSON, notes = Notes.ENVIRONMENT_NOTES,
            nickname = "changeCredentialInEnvironment")
    DetailedEnvironmentResponse changeCredential(@PathParam("name") String environmentName, @Valid EnvironmentChangeCredentialRequest request);

    @PUT
    @Path("/{name}/register_sdx")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = EnvironmentOpDescription.REGISTER_EXTERNAL_SDX, produces = APPLICATION_JSON, notes = Notes.ENVIRONMENT_NOTES,
            nickname = "registerExternalSdx")
    DetailedEnvironmentResponse registerExternalSdx(@PathParam("name") String environmentName, @Valid RegisterSdxRequest request);

    @POST
    @Path("/{name}/register_sdx_prerequisites")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = UtilityOpDescription.SDX_PREREQUISITES, produces = APPLICATION_JSON,
            nickname = "registerSdxPrerequisites")
    SdxPrerequisiteResponse registerSdxPrerequisite(@PathParam("name") String environmentName, @Valid SdxPrerequisiteRequest sdxPrerequisiteRequest);

}

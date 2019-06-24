package com.sequenceiq.freeipa.api.v1.keytab;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sequenceiq.freeipa.api.v1.keytab.doc.KeytabModelNotes;
import com.sequenceiq.freeipa.api.v1.keytab.model.ServiceKeytabRequest;
import com.sequenceiq.freeipa.api.v1.keytab.model.ServiceKeytabResponse;
import com.sequenceiq.freeipa.api.v1.keytab.model.UserKeytabRequest;
import com.sequenceiq.freeipa.api.v1.keytab.model.UserKeytabResponse;
import com.sequenceiq.freeipa.api.v1.keytab.doc.KeytabOperationsDescription;
import com.sequenceiq.service.api.doc.ContentType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/v1/kerberosmgmt")
@Consumes(MediaType.APPLICATION_JSON)
@Api(value = "/v1/kerberosmgmt", protocols = "http,https")
public interface KerberosMgmtV1Endpoint {

    @GET
    @Path("servicekeytab")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = KeytabOperationsDescription.DESCRIBE_SERVICE_CREATE, produces = ContentType.JSON, notes = KeytabModelNotes.GET_KEYTAB_NOTES, nickname = "getServiceKeytabV1")
    ServiceKeytabResponse getServiceKeytab(@Valid ServiceKeytabRequest request);

    @DELETE
    @Path("servicekeytab")
    @ApiOperation(value = KeytabOperationsDescription.DESCRIBE_SERVICE_DELETE, notes = KeytabModelNotes.DELETE_KEYTAB_NOTES, nickname = "deleteServiceKeytabV1" +
            "")
    void deleteServiceKeytab(@Valid ServiceKeytabRequest request);

    @GET
    @Path("userkeytab")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = KeytabOperationsDescription.DESCRIBE_USER_CREATE, produces = ContentType.JSON, notes = KeytabModelNotes.GET_KEYTAB_NOTES, nickname = "getUserKeytabV1")
    UserKeytabResponse getUserKeytab(@Valid UserKeytabRequest request);

    @DELETE
    @Path("userkeytab")
    @ApiOperation(value = KeytabOperationsDescription.DESCRIBE_USER_DELETE, notes = KeytabModelNotes.DELETE_KEYTAB_NOTES, nickname = "deleteUserKeytabV1")
    void deleteUserKeytab(@Valid UserKeytabRequest request);
}

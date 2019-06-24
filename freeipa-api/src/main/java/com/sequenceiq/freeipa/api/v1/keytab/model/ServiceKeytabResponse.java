package com.sequenceiq.freeipa.api.v1.keytab.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sequenceiq.freeipa.api.v1.keytab.doc.KeytabModelDescription;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("ServiceKeytabV1Response")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceKeytabResponse {

    @ApiModelProperty(KeytabModelDescription.ID)
    private Long id;

    @ApiModelProperty (KeytabModelDescription.PRICIPAL)
    private String servicePrincial;

    @ApiModelProperty (KeytabModelDescription.KEYTAB)
    private String keytab;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServicePrincial() {
        return servicePrincial;
    }

    public void setServicePrincial(String servicePrincial) {
        this.servicePrincial = servicePrincial;
    }

    public String getKeytab() {
        return keytab;
    }

    public void setKeytab(String keytab) {
        this.keytab = keytab;
    }
}

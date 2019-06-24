package com.sequenceiq.freeipa.api.v1.keytab.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sequenceiq.freeipa.api.v1.keytab.doc.KeytabModelDescription;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("UserKeytabV1Response")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserKeytabResponse {

    @ApiModelProperty(KeytabModelDescription.ID)
    private Long id;

    @ApiModelProperty (KeytabModelDescription.PRICIPAL)
    private String userPrincial;

    @ApiModelProperty (KeytabModelDescription.KEYTAB)
    private String keytab;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserPrincial() {
        return userPrincial;
    }

    public void setUserPrincial(String servicePrincial) {
        this.userPrincial = servicePrincial;
    }

    public String getKeytab() {
        return keytab;
    }

    public void setKeytab(String keytab) {
        this.keytab = keytab;
    }
}

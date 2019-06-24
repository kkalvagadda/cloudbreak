package com.sequenceiq.freeipa.kerberosmgmt.v1;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;

import com.sequenceiq.freeipa.api.v1.keytab.KerberosMgmtV1Endpoint;
import com.sequenceiq.freeipa.api.v1.keytab.model.ServiceKeytabResponse;
import com.sequenceiq.freeipa.api.v1.keytab.model.ServiceKeytabRequest;
import com.sequenceiq.freeipa.api.v1.keytab.model.UserKeytabRequest;
import com.sequenceiq.freeipa.api.v1.keytab.model.UserKeytabResponse;
import com.sequenceiq.freeipa.util.CrnService;


@Controller
@Transactional(Transactional.TxType.NEVER)
public class KerberosMgmtV1Controller implements KerberosMgmtV1Endpoint {
    @Inject
    private KerberosMgmtV1Service kerberosMgmtV1Service;

    @Inject
    private CrnService crnService;

    public ServiceKeytabResponse getServiceKeytab(@Valid ServiceKeytabRequest request) {
        return kerberosMgmtV1Service.getServiceKeytab(request);
    }

    public void deleteServiceKeytab(@Valid ServiceKeytabRequest request) {
        kerberosMgmtV1Service.deletServiceKeytab(request);
    }

    public UserKeytabResponse getUserKeytab(@Valid UserKeytabRequest request) {
        // TODO
        return null;
    }

    public void deleteUserKeytab(@Valid UserKeytabRequest request) {
        // TODO
        return;
    }
}

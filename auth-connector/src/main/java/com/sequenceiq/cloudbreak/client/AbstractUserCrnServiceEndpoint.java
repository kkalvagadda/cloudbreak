package com.sequenceiq.cloudbreak.client;

import java.util.Collections;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import org.glassfish.jersey.client.proxy.WebResourceFactory;

import com.sequenceiq.cloudbreak.auth.security.token.CrnTokenExtractor;

public abstract class AbstractUserCrnServiceEndpoint {
    private static final Form EMPTY_FORM = new Form();

    private WebTarget webTarget;

    private String crn;

    protected AbstractUserCrnServiceEndpoint(WebTarget webTarget, String crn) {
        this.webTarget = webTarget;
        this.crn = crn;
    }

    protected <E> E getEndpoint(Class<E> clazz) {
        MultivaluedMap<String, Object> headers = new MultivaluedHashMap<>();
        headers.add(CrnTokenExtractor.CRN_HEADER, crn);
        return newEndpoint(clazz, headers);
    }

    private <C> C newEndpoint(Class<C> resourceInterface, MultivaluedMap<String, Object> headers) {
        return WebResourceFactory.newResource(resourceInterface, webTarget, false, headers, Collections.emptyList(), EMPTY_FORM);
    }
}

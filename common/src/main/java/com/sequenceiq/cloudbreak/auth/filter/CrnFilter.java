package com.sequenceiq.cloudbreak.auth.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.sequenceiq.cloudbreak.auth.ThreadBasedUserCrnProvider;

public class CrnFilter extends OncePerRequestFilter {

    private final ThreadBasedUserCrnProvider threadBasedUserCrnProvider;

    public CrnFilter(ThreadBasedUserCrnProvider threadBasedUserCrnProvider) {
        this.threadBasedUserCrnProvider = threadBasedUserCrnProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String userCrn = request.getHeader("x-cdp-actor-crn");
        threadBasedUserCrnProvider.setUserCrn(userCrn);
        filterChain.doFilter(request, response);
        threadBasedUserCrnProvider.removeUserCrn();
    }
}

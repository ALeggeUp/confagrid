/*
 * JwtFilter.java
 *
 * Copyright (C) 2017 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultClaims;

public class JwtFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtFilter.class);

    public static final String ATTRIBUTE_CLAIMS = "claims";
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String SECRET_KEY = "secretkey";

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader(HEADER_AUTHORIZATION);
        Claims claims = null;
        SecurityContextHolder.getContext().setAuthentication(null);
        if (authHeader == null || !authHeader.startsWith(BEARER_PREFIX)) {
            LOGGER.info("Missing or invalid Authorization header.");
            claims = new DefaultClaims().setSubject("anonymous");
        } else {
            try {
                final String token = authHeader.substring(BEARER_PREFIX.length());
                claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
            } catch(final JwtException e) {
                LOGGER.warn("Unable to get claims from bearer token", e);
                claims = new DefaultClaims().setSubject("anonymous");
            }
        }

        if (claims != null) {
            request.setAttribute(ATTRIBUTE_CLAIMS, claims);
        }
        chain.doFilter(request, response);
    }
}

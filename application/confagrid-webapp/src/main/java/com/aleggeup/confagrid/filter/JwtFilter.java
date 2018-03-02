/*
 * JwtFilter.java
 *
 * Copyright (C) 2017-2018 [ A Legge Up ]
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
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultClaims;

public class JwtFilter extends OncePerRequestFilter {

    public static final String ATTRIBUTE_CLAIMS = "claims";
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String HEADER_CLAIMS = "X-Claims";
    public static final String HEADER_CLAIMS_SUBJECT = "X-Claims-Subject";
    public static final String SECRET_KEY = "secretkey";
    public static final String SUBJECT_ANONYMOUS = "anonymous";
    public static final String JWT_TOKEN = "X-JWT-Token";
    public static final String JWT_ATTRIBUTE = "x-jwt-attribute";
    public static final String ANONYMOUS_USER_ID = "b5a2918e-bd3f-48f5-8437-daf40fd3f323";

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtFilter.class);

    @Override
    protected void doFilterInternal(@NonNull final HttpServletRequest request,
        @NonNull final HttpServletResponse response, @NonNull final FilterChain chain)
        throws ServletException, IOException {

        Claims claims = null;
        String token = "";

        if (!RequestMethod.OPTIONS.toString().equals(request.getMethod())) {

            final String authHeader = request.getHeader(HEADER_AUTHORIZATION);

            if (authHeader == null || !authHeader.startsWith(BEARER_PREFIX)) {
                LOGGER.info("Missing or invalid Authorization header.");
                claims = new DefaultClaims().setSubject(SUBJECT_ANONYMOUS);
            } else {
                try {
                    token = authHeader.substring(BEARER_PREFIX.length());
                    claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
                } catch (final JwtException e) {
                    LOGGER.warn("Unable to get claims from bearer token: {}", e.getMessage());
                }

                if (claims == null || claims.getSubject() == null) {
                    claims = new DefaultClaims().setSubject(SUBJECT_ANONYMOUS);
                }
            }

            request.setAttribute(JWT_ATTRIBUTE, claims);
            response.setHeader(JWT_TOKEN, token);
            response.setHeader(HEADER_CLAIMS_SUBJECT, claims.getSubject());
            response.setHeader(HEADER_CLAIMS, claims.toString());
        }

        chain.doFilter(request, response);
    }
}

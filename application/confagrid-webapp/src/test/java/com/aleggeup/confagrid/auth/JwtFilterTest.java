/*
 * JwtFilterTest.java
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.auth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.bind.annotation.RequestMethod;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;

@RunWith(MockitoJUnitRunner.class)
public class JwtFilterTest {

    private static final String HEADER_NOT_BEARER = "NOT BEARER";
    private static final String HEADER_BAD_BEARER = JwtFilter.BEARER_PREFIX + "GARBAGE";
    private static final String HEADER_EMPTY_CREDENTIALS = JwtFilter.BEARER_PREFIX +
        "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6IiIsImlhdCI6MH0.nEBg6Bj7PLGALFT2rs4wDfhvUfA91b7XLNog029crUA";
    private static final String HEADER_VALID_CREDENTIALS = JwtFilter.BEARER_PREFIX +
        "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ2YWxpZCIsInJvbGVzIjoiIiwiaWF0IjowfQ.lzdLJW9bdkXZLc57_YcXm0cJL87ZFSrPLEOD1-nYR8Q";
    private static final String VALID_CREDENTIALS_SUBJECT = "valid";

    @Mock
    private HttpServletRequest mockHttpServletRequest;

    @Mock
    private HttpServletResponse mockHttpServletResponse;

    @Mock
    private FilterChain mockFilterChain;

    private JwtFilter jwtFilter;

    @Before
    public void setUp() {
        jwtFilter = new JwtFilter();
    }

    @After
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(mockHttpServletRequest, mockHttpServletResponse, mockFilterChain);
    }

    @Test
    public void testNullAuthorizationHeader() throws ServletException, IOException {
        jwtFilter.doFilterInternal(mockHttpServletRequest, mockHttpServletResponse, mockFilterChain);

        Mockito.verify(mockHttpServletRequest).getMethod();
        Mockito.verify(mockHttpServletRequest).setAttribute(JwtFilter.JWT_ATTRIBUTE,
            new DefaultClaims().setSubject(JwtFilter.SUBJECT_ANONYMOUS));
        Mockito.verify(mockHttpServletResponse).setHeader(JwtFilter.JWT_TOKEN, "");
        Mockito.verify(mockHttpServletRequest).getHeader(JwtFilter.HEADER_AUTHORIZATION);
        Mockito.verify(mockHttpServletResponse).setHeader(JwtFilter.HEADER_CLAIMS_SUBJECT, JwtFilter.SUBJECT_ANONYMOUS);
        Mockito.verify(mockFilterChain).doFilter(mockHttpServletRequest, mockHttpServletResponse);
        Mockito.verify(mockHttpServletResponse, Mockito.times(4)).setHeader(ArgumentMatchers.anyString(),
            ArgumentMatchers.any());
    }

    @Test
    public void testNotBearerAuthorizationHeader() throws ServletException, IOException {
        Mockito.when(mockHttpServletRequest.getHeader(JwtFilter.HEADER_AUTHORIZATION)).thenReturn(HEADER_NOT_BEARER);

        jwtFilter.doFilterInternal(mockHttpServletRequest, mockHttpServletResponse, mockFilterChain);

        Mockito.verify(mockHttpServletRequest).getMethod();
        Mockito.verify(mockHttpServletRequest).setAttribute(JwtFilter.JWT_ATTRIBUTE,
            new DefaultClaims().setSubject(JwtFilter.SUBJECT_ANONYMOUS));
        Mockito.verify(mockHttpServletResponse).setHeader(JwtFilter.JWT_TOKEN, "");
        Mockito.verify(mockHttpServletRequest).getHeader(JwtFilter.HEADER_AUTHORIZATION);
        Mockito.verify(mockHttpServletResponse).setHeader(JwtFilter.HEADER_CLAIMS_SUBJECT, JwtFilter.SUBJECT_ANONYMOUS);
        Mockito.verify(mockFilterChain).doFilter(mockHttpServletRequest, mockHttpServletResponse);
        Mockito.verify(mockHttpServletResponse, Mockito.times(4)).setHeader(ArgumentMatchers.anyString(),
            ArgumentMatchers.any());
    }

    @Test
    public void testBadBearerAuthorizationHeader() throws ServletException, IOException {
        Mockito.when(mockHttpServletRequest.getMethod()).thenReturn(RequestMethod.POST.name());
        Mockito.when(mockHttpServletRequest.getHeader(JwtFilter.HEADER_AUTHORIZATION)).thenReturn(HEADER_BAD_BEARER);

        jwtFilter.doFilterInternal(mockHttpServletRequest, mockHttpServletResponse, mockFilterChain);

        Mockito.verify(mockHttpServletRequest).getMethod();
        Mockito.verify(mockHttpServletRequest).setAttribute(JwtFilter.JWT_ATTRIBUTE,
            new DefaultClaims().setSubject(JwtFilter.SUBJECT_ANONYMOUS));
        Mockito.verify(mockHttpServletRequest).getHeader(JwtFilter.HEADER_AUTHORIZATION);
        Mockito.verify(mockFilterChain).doFilter(mockHttpServletRequest, mockHttpServletResponse);

        Mockito.verify(mockHttpServletResponse).setHeader(JwtFilter.HEADER_CLAIMS_SUBJECT, JwtFilter.SUBJECT_ANONYMOUS);
        Mockito.verify(mockHttpServletResponse).setHeader(JwtFilter.JWT_TOKEN, "GARBAGE");
        Mockito.verify(mockHttpServletResponse, Mockito.times(4)).setHeader(ArgumentMatchers.anyString(),
            ArgumentMatchers.any());
    }

    @Test
    public void testEmptyCredentials() throws ServletException, IOException {
        Mockito.when(mockHttpServletRequest.getHeader(JwtFilter.HEADER_AUTHORIZATION))
            .thenReturn(HEADER_EMPTY_CREDENTIALS);

        jwtFilter.doFilterInternal(mockHttpServletRequest, mockHttpServletResponse, mockFilterChain);

        Mockito.verify(mockHttpServletRequest).getMethod();
        Mockito.verify(mockHttpServletRequest).setAttribute(JwtFilter.JWT_ATTRIBUTE,
            new DefaultClaims().setSubject(JwtFilter.SUBJECT_ANONYMOUS));
        Mockito.verify(mockHttpServletResponse).setHeader(JwtFilter.JWT_TOKEN,
            "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6IiIsImlhdCI6MH0.nEBg6Bj7PLGALFT2rs4wDfhvUfA91b7XLNog029crUA");
        Mockito.verify(mockHttpServletRequest).getHeader(JwtFilter.HEADER_AUTHORIZATION);
        Mockito.verify(mockHttpServletResponse).setHeader(JwtFilter.HEADER_CLAIMS_SUBJECT, JwtFilter.SUBJECT_ANONYMOUS);
        Mockito.verify(mockFilterChain).doFilter(mockHttpServletRequest, mockHttpServletResponse);
        Mockito.verify(mockHttpServletResponse, Mockito.times(4)).setHeader(ArgumentMatchers.anyString(),
            ArgumentMatchers.any());
    }

    @Test
    public void testNormalCredentials() throws ServletException, IOException {
        Mockito.when(mockHttpServletRequest.getHeader(JwtFilter.HEADER_AUTHORIZATION))
            .thenReturn(HEADER_VALID_CREDENTIALS);

        jwtFilter.doFilterInternal(mockHttpServletRequest, mockHttpServletResponse, mockFilterChain);
        final Claims expectedClaims = new DefaultClaims().setSubject(VALID_CREDENTIALS_SUBJECT);
        expectedClaims.put("roles", "");
        expectedClaims.put("iat", 0);

        Mockito.verify(mockHttpServletRequest).getMethod();
        Mockito.verify(mockHttpServletRequest).setAttribute(JwtFilter.JWT_ATTRIBUTE, expectedClaims);
        Mockito.verify(mockHttpServletResponse).setHeader(JwtFilter.JWT_TOKEN,
            "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ2YWxpZCIsInJvbGVzIjoiIiwiaWF0IjowfQ.lzdLJW9bdkXZLc57_YcXm0cJL87ZFSrPLEOD1-nYR8Q");
        Mockito.verify(mockHttpServletRequest).getHeader(JwtFilter.HEADER_AUTHORIZATION);
        Mockito.verify(mockHttpServletResponse).setHeader(JwtFilter.HEADER_CLAIMS_SUBJECT, VALID_CREDENTIALS_SUBJECT);
        Mockito.verify(mockFilterChain).doFilter(mockHttpServletRequest, mockHttpServletResponse);
        Mockito.verify(mockHttpServletResponse, Mockito.times(4)).setHeader(ArgumentMatchers.anyString(),
            ArgumentMatchers.any());
    }
}

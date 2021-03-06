/*
 * UserControllerTest.java
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.user;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.aleggeup.confagrid.auth.AuthenticationService;
import com.aleggeup.confagrid.auth.InvalidLoginException;
import com.aleggeup.confagrid.auth.JwtFilter;
import com.aleggeup.confagrid.model.mex.LoginRequest;
import com.aleggeup.confagrid.model.mex.LoginResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String ROLE = "role";

    @Mock
    private UserService mockUserService;

    @Mock
    private AuthenticationService mockAuthenticationService;

    @Mock
    private HttpServletRequest mockHttpServletRequest;

    @Mock
    private HttpServletResponse mockHttpServletResponse;

    @Mock
    private UserEntity mockUserEntity;

    private UserController userController;

    @Before
    public void setUp() {
        userController = new UserController(mockUserService, mockAuthenticationService);
    }

    @After
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(mockUserService);
    }

    @Ignore("Null is not allowed")
    @Test(expected = InvalidLoginException.class)
    public void testNullLogin() {
        userController.login(null);
    }

    @Test(expected = InvalidLoginException.class)
    public void testNullName() {
        final LoginRequest loginRequest = new LoginRequest();
        loginRequest.setName(null);
        userController.login(loginRequest);
    }

    @Test
    public void testNullPassword() {
        final LoginRequest loginRequest = new LoginRequest();
        loginRequest.setName(USERNAME);
        loginRequest.setPassword(null);

        boolean exceptionThrown = false;
        try {
            userController.login(loginRequest);
        } catch (final InvalidLoginException e) {
            exceptionThrown = true;
        }

        assertTrue(exceptionThrown);

        Mockito.verify(mockUserService).containsName(USERNAME);
    }

    @Test
    public void testEmptyPassword() {
        final LoginRequest loginRequest = new LoginRequest();
        loginRequest.setName(USERNAME);
        loginRequest.setPassword("");

        boolean exceptionThrown = false;
        try {
            userController.login(loginRequest);
        } catch (final InvalidLoginException e) {
            exceptionThrown = true;
        }

        assertTrue(exceptionThrown);
        Mockito.verify(mockUserService).containsName(USERNAME);
    }

    @Test
    public void testNoKeyMatch() {
        Mockito.when(mockUserService.containsName(USERNAME)).thenReturn(false);

        final LoginRequest loginRequest = new LoginRequest();
        loginRequest.setName(USERNAME);
        loginRequest.setPassword(PASSWORD);

        boolean exceptionThrown = false;
        try {
            userController.login(loginRequest);
        } catch (final InvalidLoginException e) {
            exceptionThrown = true;
        }

        assertTrue(exceptionThrown);
        Mockito.verify(mockUserService).containsName(USERNAME);
    }

    @Test
    public void testPasswordNoMatch() {

        Mockito.when(mockUserService.containsName(USERNAME)).thenReturn(true);
        Mockito.when(mockAuthenticationService.userPasswordCheck(USERNAME, PASSWORD)).thenReturn(false);

        final LoginRequest loginRequest = new LoginRequest();
        loginRequest.setName(USERNAME);
        loginRequest.setPassword(PASSWORD);

        boolean exceptionThrown = false;
        try {
            userController.login(loginRequest);
        } catch (final InvalidLoginException e) {
            exceptionThrown = true;
        }

        assertTrue(exceptionThrown);

        Mockito.verify(mockUserService).containsName(USERNAME);
        Mockito.verify(mockAuthenticationService).userPasswordCheck(USERNAME, PASSWORD);
    }

    @Test
    public void testPasswordMatch() {
        Mockito.when(mockUserService.containsName(USERNAME)).thenReturn(true);
        Mockito.when(mockAuthenticationService.userPasswordCheck(USERNAME, PASSWORD)).thenReturn(true);
        Mockito.when(mockUserEntity.getId()).thenReturn(UUID.randomUUID());
        Mockito.when(mockUserEntity.getName()).thenReturn(USERNAME);
        Mockito.when(mockAuthenticationService.authenticationToken(USERNAME, PASSWORD)).thenReturn("");

        Mockito.when(mockUserService.findByName(USERNAME)).thenReturn(mockUserEntity);

        final LoginRequest loginRequest = new LoginRequest();
        loginRequest.setName(USERNAME);
        loginRequest.setPassword(PASSWORD);

        boolean exceptionThrown = false;
        try {
            userController.login(loginRequest);
        } catch (final InvalidLoginException e) {
            exceptionThrown = true;
        }

        assertFalse(exceptionThrown);

        Mockito.verify(mockUserService).containsName(USERNAME);
        Mockito.verify(mockAuthenticationService).userPasswordCheck(USERNAME, PASSWORD);
        Mockito.verify(mockAuthenticationService).authenticationToken(USERNAME, PASSWORD);
        Mockito.verify(mockUserEntity).getId();
        Mockito.verify(mockUserEntity).getName();
        Mockito.verify(mockUserService).findByName(USERNAME);
    }

    @Test
    public void testValidUserWithEmptyPassword() {
        Mockito.when(mockUserService.containsName(USERNAME)).thenReturn(true);

        final LoginRequest loginRequest = new LoginRequest();
        loginRequest.setName(USERNAME);
        loginRequest.setPassword("");

        boolean exceptionThrown = false;
        try {
            userController.login(loginRequest);
        } catch (final InvalidLoginException e) {
            exceptionThrown = true;
        }

        assertTrue(exceptionThrown);

        Mockito.verify(mockUserService).containsName(USERNAME);
    }

    @Test
    public void testValidUserWithNullPassword() {
        Mockito.when(mockUserService.containsName(USERNAME)).thenReturn(true);

        final LoginRequest loginRequest = new LoginRequest();
        loginRequest.setName(USERNAME);
        loginRequest.setPassword(null);

        boolean exceptionThrown = false;
        try {
            userController.login(loginRequest);
        } catch (final InvalidLoginException e) {
            exceptionThrown = true;
        }

        assertTrue(exceptionThrown);

        Mockito.verify(mockUserService).containsName(USERNAME);
    }

    @Test
    public void testNullClaims() {
        final boolean containsRole;
        Mockito.when(mockHttpServletRequest.getAttribute(JwtFilter.ATTRIBUTE_CLAIMS)).thenReturn(null);
        containsRole = userController.claimContainsRole(ROLE, mockHttpServletRequest);
        assertFalse(containsRole);
        Mockito.verify(mockHttpServletRequest).getAttribute(JwtFilter.ATTRIBUTE_CLAIMS);
    }

    @Test
    public void testEmptyClaims() {
        final boolean containsRole;
        final Claims emptyClaims = Jwts.claims();
        Mockito.when(mockHttpServletRequest.getAttribute(JwtFilter.ATTRIBUTE_CLAIMS)).thenReturn(emptyClaims);
        containsRole = userController.claimContainsRole(ROLE, mockHttpServletRequest);
        assertFalse(containsRole);
        Mockito.verify(mockHttpServletRequest).getAttribute(JwtFilter.ATTRIBUTE_CLAIMS);
    }

    @Test
    public void testClaimsWithoutRoles() {
        final boolean containsRole;
        final Claims claims = Jwts.claims().setSubject("test");
        Mockito.when(mockHttpServletRequest.getAttribute(JwtFilter.ATTRIBUTE_CLAIMS)).thenReturn(claims);
        containsRole = userController.claimContainsRole(ROLE, mockHttpServletRequest);
        assertFalse(containsRole);
        Mockito.verify(mockHttpServletRequest).getAttribute(JwtFilter.ATTRIBUTE_CLAIMS);
    }

    @Test
    public void testClaimsWithEmptyRoles() {
        final boolean containsRole;
        final Map<String, Object> map = new HashMap<>();
        map.put("roles", new ArrayList<>());
        final Claims claims = Jwts.claims(map);
        Mockito.when(mockHttpServletRequest.getAttribute(JwtFilter.ATTRIBUTE_CLAIMS)).thenReturn(claims);
        containsRole = userController.claimContainsRole(ROLE, mockHttpServletRequest);
        assertFalse(containsRole);
        Mockito.verify(mockHttpServletRequest).getAttribute(JwtFilter.ATTRIBUTE_CLAIMS);
    }

    @Test
    public void testClaimsWithRoles() {
        final boolean containsRole;
        final List<LinkedHashMap<String, String>> roles = new ArrayList<>();
        final LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(ROLE, ROLE);
        roles.add(linkedHashMap);
        final Map<String, Object> map = new HashMap<>();
        map.put("roles", roles);
        final Claims claims = Jwts.claims(map);
        Mockito.when(mockHttpServletRequest.getAttribute(JwtFilter.ATTRIBUTE_CLAIMS)).thenReturn(claims);
        containsRole = userController.claimContainsRole(ROLE, mockHttpServletRequest);
        assertTrue(containsRole);
        Mockito.verify(mockHttpServletRequest).getAttribute(JwtFilter.ATTRIBUTE_CLAIMS);
    }

    @Test
    public void testCheckAnonymousHeader() {
        Mockito.when(mockHttpServletResponse.getHeader(JwtFilter.HEADER_CLAIMS_SUBJECT))
            .thenReturn(JwtFilter.SUBJECT_ANONYMOUS);

        final LoginResponse loginResponse = userController.check(mockHttpServletResponse);

        assertNotNull(loginResponse);

        Mockito.verify(mockHttpServletResponse, Mockito.times(2)).getHeader(JwtFilter.HEADER_CLAIMS_SUBJECT);
        Mockito.verify(mockAuthenticationService).anonymousToken();
    }

    @Test
    public void testCheckNotAnonymousHeader() {
        Mockito.when(mockHttpServletResponse.getHeader(JwtFilter.HEADER_CLAIMS_SUBJECT)).thenReturn("not anonymous");
        Mockito.when(mockHttpServletResponse.getHeader(JwtFilter.JWT_TOKEN)).thenReturn("token");

        final LoginResponse loginResponse = userController.check(mockHttpServletResponse);

        assertNotNull(loginResponse);

        Mockito.verify(mockHttpServletResponse, Mockito.times(2)).getHeader(JwtFilter.HEADER_CLAIMS_SUBJECT);
        Mockito.verify(mockHttpServletResponse).getHeader(JwtFilter.JWT_TOKEN);
    }
}

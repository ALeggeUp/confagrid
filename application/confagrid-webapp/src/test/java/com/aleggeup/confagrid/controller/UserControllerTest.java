/*
 * UserControllerTest.java
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.aleggeup.confagrid.controller.exception.InvalidLoginException;
import com.aleggeup.confagrid.filter.JwtFilter;
import com.aleggeup.confagrid.model.User;
import com.aleggeup.confagrid.model.mex.LoginRequest;
import com.aleggeup.confagrid.service.UserService;

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
    private HttpServletRequest mockHttpServletRequest;

    private UserController userController;

    @Before
    public void setUp() {
        userController = new UserController(mockUserService);
    }

    @After
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(mockUserService);
    }

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

        Mockito.verify(mockUserService).containsKey(USERNAME);
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
        Mockito.verify(mockUserService).containsKey(USERNAME);
    }

    @Test
    public void testNoKeyMatch() {
        Mockito.when(mockUserService.containsKey(USERNAME)).thenReturn(false);

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
        Mockito.verify(mockUserService).containsKey(USERNAME);
    }

    @Test
    public void testPasswordNoMatch() {
        Mockito.when(mockUserService.containsKey(USERNAME)).thenReturn(true);

        final User user = new User(USERNAME, PASSWORD, null);
        Mockito.when(mockUserService.getById(USERNAME)).thenReturn(user);
        Mockito.when(mockUserService.matches(PASSWORD, PASSWORD)).thenReturn(false);

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

        Mockito.verify(mockUserService).containsKey(USERNAME);
        Mockito.verify(mockUserService).getById(USERNAME);
        Mockito.verify(mockUserService).matches(PASSWORD, PASSWORD);
    }

    @Test
    public void testPasswordMatch() {
        Mockito.when(mockUserService.containsKey(USERNAME)).thenReturn(true);

        final User user = new User(USERNAME, PASSWORD, null);
        Mockito.when(mockUserService.getById(USERNAME)).thenReturn(user);
        Mockito.when(mockUserService.matches(PASSWORD, PASSWORD)).thenReturn(true);

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

        Mockito.verify(mockUserService).containsKey(USERNAME);
        Mockito.verify(mockUserService).getById(USERNAME);
        Mockito.verify(mockUserService).matches(PASSWORD, PASSWORD);
    }

    @Test
    public void testValidUserWithEmptyPassword() {
        Mockito.when(mockUserService.containsKey(USERNAME)).thenReturn(true);

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

        Mockito.verify(mockUserService).containsKey(USERNAME);
    }

    @Test
    public void testValidUserWithNullPassword() {
        Mockito.when(mockUserService.containsKey(USERNAME)).thenReturn(true);

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

        Mockito.verify(mockUserService).containsKey(USERNAME);
    }

    @Test
    public void testNullClaims() {
        boolean exceptionThrown = false;
        boolean containsRole = true;
        Mockito.when(mockHttpServletRequest.getAttribute(JwtFilter.ATTRIBUTE_CLAIMS)).thenReturn(null);
        try {
            containsRole = userController.claimContainsRole(ROLE, mockHttpServletRequest);
        } catch (final ServletException e) {
            exceptionThrown = true;
        }
        assertFalse(containsRole);
        assertFalse(exceptionThrown);
        Mockito.verify(mockHttpServletRequest).getAttribute(JwtFilter.ATTRIBUTE_CLAIMS);
    }

    @Test
    public void testEmptyClaims() throws ServletException {
        boolean containsRole = true;
        final Claims emptyClaims = Jwts.claims();
        Mockito.when(mockHttpServletRequest.getAttribute(JwtFilter.ATTRIBUTE_CLAIMS)).thenReturn(emptyClaims);
        containsRole = userController.claimContainsRole(ROLE, mockHttpServletRequest);
        assertFalse(containsRole);
        Mockito.verify(mockHttpServletRequest).getAttribute(JwtFilter.ATTRIBUTE_CLAIMS);
    }

    @Test
    public void testClaimsWithoutRoles() throws ServletException {
        boolean containsRole = true;
        final Claims claims = Jwts.claims().setSubject("test");
        Mockito.when(mockHttpServletRequest.getAttribute(JwtFilter.ATTRIBUTE_CLAIMS)).thenReturn(claims);
        containsRole = userController.claimContainsRole(ROLE, mockHttpServletRequest);
        assertFalse(containsRole);
        Mockito.verify(mockHttpServletRequest).getAttribute(JwtFilter.ATTRIBUTE_CLAIMS);
    }

    @Test
    public void testClaimsWithEmptyRoles() throws ServletException {
        boolean containsRole = true;
        final List<LinkedHashMap<String, String>> roles = new ArrayList<>();
        final Map<String, Object> map = new HashMap<>();
        map.put("roles", roles);
        final Claims claims = Jwts.claims(map);
        Mockito.when(mockHttpServletRequest.getAttribute(JwtFilter.ATTRIBUTE_CLAIMS)).thenReturn(claims);
        containsRole = userController.claimContainsRole(ROLE, mockHttpServletRequest);
        assertFalse(containsRole);
        Mockito.verify(mockHttpServletRequest).getAttribute(JwtFilter.ATTRIBUTE_CLAIMS);
    }

    @Test
    public void testClaimsWithRoles() throws ServletException {
        boolean containsRole = true;
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
}
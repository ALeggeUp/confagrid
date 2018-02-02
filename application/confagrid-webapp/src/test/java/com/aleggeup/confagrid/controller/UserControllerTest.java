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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.aleggeup.confagrid.controller.exception.InvalidLoginException;
import com.aleggeup.confagrid.model.User;
import com.aleggeup.confagrid.model.mex.LoginRequest;
import com.aleggeup.confagrid.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    @Mock
    private UserService mockUserService;

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
}

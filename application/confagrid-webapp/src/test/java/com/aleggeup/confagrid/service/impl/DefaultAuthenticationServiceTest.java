/*
 * DefaultAuthenticationService.java
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.service.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.aleggeup.confagrid.model.User;
import com.aleggeup.confagrid.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class DefaultAuthenticationServiceTest {

    private static final String VALID_USERNAME = "user";

    private static final String VALID_PASSWORD = "password";

    private static final String INVALID_PASSWORD = "invalid_password";

    @Mock
    private UserService mockUserService;

    private DefaultAuthenticationService defaultAuthenticationService;

    @Before
    public void setUp() {
        defaultAuthenticationService = new DefaultAuthenticationService(mockUserService);
    }

    @After
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(mockUserService);
    }

    @Test
    public void testUserPasswordCheck() {
        final User user = new User();
        user.setPassword(VALID_PASSWORD);

        Mockito.when(mockUserService.getById(VALID_USERNAME)).thenReturn(user);
        Mockito.when(mockUserService.matches(VALID_PASSWORD, VALID_PASSWORD)).thenReturn(true);

        Assert.assertTrue(defaultAuthenticationService.userPasswordCheck(VALID_USERNAME, VALID_PASSWORD));

        Mockito.verify(mockUserService).getById(VALID_USERNAME);
        Mockito.verify(mockUserService).matches(VALID_PASSWORD, VALID_PASSWORD);
    }

    @Test
    public void testUserCheckInvalidPassword() {
        final User user = new User();
        user.setPassword(VALID_PASSWORD);

        Mockito.when(mockUserService.getById(VALID_USERNAME)).thenReturn(user);
        Mockito.when(mockUserService.matches(INVALID_PASSWORD, VALID_PASSWORD)).thenReturn(false);

        Assert.assertFalse(defaultAuthenticationService.userPasswordCheck(VALID_USERNAME, INVALID_PASSWORD));

        Mockito.verify(mockUserService).getById(VALID_USERNAME);
        Mockito.verify(mockUserService).matches(INVALID_PASSWORD, VALID_PASSWORD);
    }

    @Test
    public void testUserCheckUserNotFound() {
        Mockito.when(mockUserService.getById(VALID_USERNAME)).thenReturn(null);

        Assert.assertFalse(defaultAuthenticationService.userPasswordCheck(VALID_USERNAME, VALID_PASSWORD));

        Mockito.verify(mockUserService).getById(VALID_USERNAME);
    }
}

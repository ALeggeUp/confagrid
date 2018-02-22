/*
 * DefaultAuthenticationService.java
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.service.impl;

import java.util.Collections;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.aleggeup.confagrid.model.User;
import com.aleggeup.confagrid.service.DateTimeService;
import com.aleggeup.confagrid.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class DefaultAuthenticationServiceTest {

    private static final String VALID_USERNAME = "user";

    private static final String VALID_PASSWORD = "password";

    private static final String INVALID_PASSWORD = "invalid_password";

    @Mock
    private UserService mockUserService;

    @Mock
    private DateTimeService mockDateTimeService;

    private DefaultAuthenticationService defaultAuthenticationService;

    @Before
    public void setUp() {
        defaultAuthenticationService = new DefaultAuthenticationService(mockUserService, mockDateTimeService);
    }

    @After
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(mockUserService);
    }

    @Test
    public void testUserPasswordCheck() {
        final User user = new User();
        user.setPassword(VALID_PASSWORD);

        Mockito.when(mockUserService.findByName(VALID_USERNAME)).thenReturn(user);
        Mockito.when(mockUserService.matches(VALID_PASSWORD, VALID_PASSWORD)).thenReturn(true);

        Assert.assertTrue(defaultAuthenticationService.userPasswordCheck(VALID_USERNAME, VALID_PASSWORD));

        Mockito.verify(mockUserService).findByName(VALID_USERNAME);
        Mockito.verify(mockUserService).matches(VALID_PASSWORD, VALID_PASSWORD);
    }

    @Test
    public void testUserCheckInvalidPassword() {
        final User user = new User();
        user.setPassword(VALID_PASSWORD);

        Mockito.when(mockUserService.findByName(VALID_USERNAME)).thenReturn(user);
        Mockito.when(mockUserService.matches(INVALID_PASSWORD, VALID_PASSWORD)).thenReturn(false);

        Assert.assertFalse(defaultAuthenticationService.userPasswordCheck(VALID_USERNAME, INVALID_PASSWORD));

        Mockito.verify(mockUserService).findByName(VALID_USERNAME);
        Mockito.verify(mockUserService).matches(INVALID_PASSWORD, VALID_PASSWORD);
    }

    @Test
    public void testUserCheckUserNotFound() {
        Mockito.when(mockUserService.findByName(VALID_USERNAME)).thenReturn(null);

        Assert.assertFalse(defaultAuthenticationService.userPasswordCheck(VALID_USERNAME, VALID_PASSWORD));

        Mockito.verify(mockUserService).findByName(VALID_USERNAME);
    }

    @Ignore("Remove until difference in time on build machine is figured out.")
    @Test
    public void testAuthenticationToken() {
        final User user = new User();
        user.setName(VALID_USERNAME);
        user.setPassword(VALID_PASSWORD);
        user.setRoles(Collections.emptySet());
        final DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        final DateTime now = formatter.parseDateTime("1985-10-26 01:24:00").withZone(DateTimeZone.UTC);
        final Date expiry = new Date(1_000_000_000L);

        Mockito.when(mockUserService.findByName(VALID_USERNAME)).thenReturn(user);
        Mockito.when(mockDateTimeService.now()).thenReturn(now);
        Mockito.when(mockDateTimeService.plusDaysAsDate(now, 1)).thenReturn(expiry);

        final String response = defaultAuthenticationService.authenticationToken(VALID_USERNAME, VALID_PASSWORD);

        Assert.assertEquals(
            "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwicm9sZXMiOltdLCJpYXQiOjQ5OTE2NjY0MCwiZXhwIjoxMDAwMDAwfQ.e14TR4Umu8UUz3GrYyXHQTkFickDFV1Vpgb8JaCz8EY",
            response);

        Mockito.verify(mockUserService).findByName(VALID_USERNAME);
        Mockito.verify(mockDateTimeService).now();
        Mockito.verify(mockDateTimeService).plusDaysAsDate(now, 1);
    }

    @Ignore("Remove until difference in time on build machine is figured out.")
    @Test
    public void testAnonymousAuthenticationToken() {
        final DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        final DateTime now = formatter.parseDateTime("1985-10-26 01:24:00").withZone(DateTimeZone.UTC);
        final Date expiry = new Date(1_000_000_000L);

        Mockito.when(mockDateTimeService.now()).thenReturn(now);
        Mockito.when(mockDateTimeService.plusDaysAsDate(now, 1)).thenReturn(expiry);

        final String response = defaultAuthenticationService.anonymousToken();

        Assert.assertEquals(
            "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbm9ueW1vdXMiLCJpYXQiOjQ5OTE2NjY0MCwiZXhwIjoxMDAwMDAwfQ.c-kEHbZBxg4sTiweFgQrHtMJSSwiaavqHRsWhw0Uvwc",
            response);

        Mockito.verify(mockDateTimeService).now();
        Mockito.verify(mockDateTimeService).plusDaysAsDate(now, 1);
    }
}

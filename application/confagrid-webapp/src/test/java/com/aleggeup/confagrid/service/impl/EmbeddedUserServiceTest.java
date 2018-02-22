/*
 * EmbeddedUserServiceTest.java
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.security.SecureRandom;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.aleggeup.confagrid.model.User;
import com.aleggeup.confagrid.repository.UserRepository;
import com.aleggeup.confagrid.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class EmbeddedUserServiceTest {

    private static final UUID RANDOM_UUID = UUID.randomUUID();
    private static final User TEST_USER = new User("test", "test", null);
    private static final Long COUNT = 10L;
    private static final String UNENCRYPTED_PASSWORD = "password";
    private static final String ENCRYPTED_PASSWORD = "$2a$12$OiHAOiHAOiHAOiHAOiHAOek2Bn3t./pRbHo5UYYCAx.Htp/SiLvay";

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private SecureRandom mockSecureRandom;

    private UserService userService;

    @Before
    public void setUp() {
        userService = new EmbeddedUserService(mockUserRepository, mockSecureRandom);
    }

    @After
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(mockUserRepository, mockSecureRandom);
    }

    @Test
    public void testSave() {
        userService.save(TEST_USER);

        Mockito.verify(mockUserRepository).save(TEST_USER);
    }

    @Test
    public void testGetById() {
        Mockito.when(mockUserRepository.findOne(RANDOM_UUID)).thenReturn(TEST_USER);
        final User user = userService.getById(RANDOM_UUID);

        assertEquals(TEST_USER, user);

        Mockito.verify(mockUserRepository).findOne(RANDOM_UUID);
    }

    @Test
    public void testContainsKey() {
        Mockito.when(mockUserRepository.exists(RANDOM_UUID)).thenReturn(true);

        assertTrue(userService.containsKey(RANDOM_UUID));

        Mockito.verify(mockUserRepository).exists(RANDOM_UUID);
    }

    @Test
    public void testEncrypt() {
        Mockito.doAnswer((Answer<Void>) mock -> {
            final byte[] bytes = mock.getArgument(0);
            for (int i = 0; i < bytes.length; ++i) {
                bytes[i] = 0x42;
            }

            return null;
        }).when(mockSecureRandom).nextBytes(ArgumentMatchers.any());

        assertEquals(ENCRYPTED_PASSWORD, userService.encrypt(UNENCRYPTED_PASSWORD));

        Mockito.verify(mockSecureRandom).nextBytes(ArgumentMatchers.any());
    }

    @Test
    public void testMatches() {
        assertTrue(userService.matches(UNENCRYPTED_PASSWORD, ENCRYPTED_PASSWORD));
    }

    @Test
    public void testCount() {
        Mockito.when(mockUserRepository.count()).thenReturn(COUNT);

        assertEquals(COUNT, Long.valueOf(userService.count()));

        Mockito.verify(mockUserRepository).count();
    }
}

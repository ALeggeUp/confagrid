/*
 * UserRepositoryInitializerTest.java
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.repository.init;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.aleggeup.confagrid.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserRepositoryInitializerTest {

    @Mock
    private UserService mockUserService;

    private UserRepositoryInitializer userRepositoryInitializer;

    @Before
    public void setUp() {
        userRepositoryInitializer = new UserRepositoryInitializer(mockUserService);
    }

    @After
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(mockUserService);
    }

    @Test
    public void testAlreadyInitialized() {
        Mockito.when(mockUserService.count()).thenReturn(1L);
        userRepositoryInitializer.postConstruct();
        Mockito.verify(mockUserService).count();
    }

    @Test
    public void testInitialize() {
        Mockito.when(mockUserService.count()).thenReturn(0L);
        userRepositoryInitializer.postConstruct();
        Mockito.verify(mockUserService).count();
        Mockito.verify(mockUserService, Mockito.times(2)).save(ArgumentMatchers.any());
        Mockito.verify(mockUserService, Mockito.times(2)).encrypt(ArgumentMatchers.anyString());
    }
}

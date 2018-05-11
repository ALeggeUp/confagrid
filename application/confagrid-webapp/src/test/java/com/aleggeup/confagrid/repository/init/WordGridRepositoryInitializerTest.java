/*
 * WordGridRepositoryInitializerTest.java
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

import com.aleggeup.confagrid.content.WordGridRepository;
import com.aleggeup.confagrid.content.WordGridRepositoryInitializer;
import com.aleggeup.confagrid.model.WordGrid;
import com.aleggeup.confagrid.user.UserRepository;
import com.aleggeup.confagrid.user.UserRepositoryInitializer;

@RunWith(MockitoJUnitRunner.class)
public class WordGridRepositoryInitializerTest {

    @Mock
    private WordGridRepository mockWordGridRepository;

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private UserRepositoryInitializer mockUserRepositoryInitializer;

    private WordGridRepositoryInitializer wordGridRepositoryInitializer;

    @Before
    public void setUp() {
        wordGridRepositoryInitializer = new WordGridRepositoryInitializer(mockWordGridRepository, mockUserRepository,
            mockUserRepositoryInitializer);
    }

    @After
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(mockWordGridRepository);
    }

    @Test
    public void testAlreadyInitialized() {
        Mockito.when(mockWordGridRepository.count()).thenReturn(1L);
        wordGridRepositoryInitializer.postConstruct();
        Mockito.verify(mockWordGridRepository).count();
    }

    @Test
    public void testInitialize() {
        Mockito.when(mockWordGridRepository.count()).thenReturn(0L);
        wordGridRepositoryInitializer.postConstruct();
        Mockito.verify(mockWordGridRepository).count();
        Mockito.verify(mockWordGridRepository, Mockito.times(2)).save(ArgumentMatchers.any(WordGrid.class));
    }
}

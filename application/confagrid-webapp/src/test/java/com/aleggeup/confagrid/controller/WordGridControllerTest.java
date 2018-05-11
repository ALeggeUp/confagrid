/*
 * WordGridController.java
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.aleggeup.confagrid.content.WordGridController;
import com.aleggeup.confagrid.content.WordGridService;
import com.aleggeup.confagrid.model.WordGrid;

@RunWith(MockitoJUnitRunner.class)
public class WordGridControllerTest {

    @Mock
    private WordGridService mockWordGridService;

    private WordGridController wordGridController;

    @Before
    public void setUp() {
        wordGridController = new WordGridController(mockWordGridService);
    }

    @After
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(mockWordGridService);
    }

    @Test
    public void testGetAllEmpty() {
        final List<WordGrid> list = new ArrayList<>();
        Mockito.when(mockWordGridService.findAll()).thenReturn(list);
        final List<WordGrid> allwordGrids = wordGridController.allWordGrids();
        assertEquals(0, allwordGrids.size());
        Mockito.verify(mockWordGridService).findAll();
    }

    @Test
    public void testGetAll() {
        final List<WordGrid> list = new ArrayList<>();
        list.add(new WordGrid());
        Mockito.when(mockWordGridService.findAll()).thenReturn(list);
        final List<WordGrid> allwordGrids = wordGridController.allWordGrids();
        assertEquals(1, allwordGrids.size());
        Mockito.verify(mockWordGridService).findAll();
    }

    @Test
    public void testCreate() {
        final WordGrid wordGrid = new WordGrid();
        final List<WordGrid> result = wordGridController.create(wordGrid);
        assertEquals(1, result.size());
        Mockito.verify(mockWordGridService).save(wordGrid);
    }
}

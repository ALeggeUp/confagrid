/*
 * ExperimentalControllerTest.java
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.controller;

import static org.junit.Assert.assertNotNull;

import java.io.UnsupportedEncodingException;

import javax.xml.transform.TransformerException;

import org.apache.batik.svggen.SVGGraphics2DIOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.aleggeup.confagrid.service.WordGridGenerationService;

@RunWith(MockitoJUnitRunner.class)
public class ExperimentalControllerTest {

    @Mock
    private WordGridGenerationService mockWordGridGenerationService;

    private ExperimentalController experimentalController;

    @Before
    public void setUp() {
        experimentalController = new ExperimentalController(mockWordGridGenerationService);
    }

    @After
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(mockWordGridGenerationService);
    }

    @Test
    public void test() throws SVGGraphics2DIOException, UnsupportedEncodingException, TransformerException {
        Mockito.when(mockWordGridGenerationService.svgTest()).thenReturn("");
        assertNotNull(experimentalController.svgTest());
        Mockito.verify(mockWordGridGenerationService).svgTest();
    }
}

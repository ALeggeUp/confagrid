/*
 * DirectedAcyclicWordGraphTest.java
 *
 * Copyright (C) 2017 [ A Legge Up ] <stephen@aleggeup.com>
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.words;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DirectedAcyclicWordGraphTest {

    @Test
    public void simpleDawgTest() {
        final DirectedAcyclicWordGraph dawg = new DirectedAcyclicWordGraph(new WordList());
        assertEquals(0, dawg.getVerticies());
        assertEquals(0, dawg.getEdges());
    }
}

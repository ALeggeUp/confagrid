/*
 * WordTest.java
 *
 * Copyright (C) 2017 [ A Legge Up ] <stephen@aleggeup.com>
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.words;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class WordTest
{
    private static final String WORD = "word";

    @Test
    public void simpleWordTest() {
        final Word word = new Word(WORD);
        assertEquals(WORD, word.getWord());
    }
}

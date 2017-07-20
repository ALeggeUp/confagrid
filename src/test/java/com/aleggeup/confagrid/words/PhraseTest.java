/*
 * PhraseTest.java
 *
 * Copyright (C) 2017 [ A Legge Up ] <stephen@aleggeup.com>
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.words;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PhraseTest
{
    private static final String TEST_PHRASE_1 = "This is a test phrase";

    @Test
    public void simplePhraseTest() {
        final Phrase phrase = new Phrase(TEST_PHRASE_1);
        assertEquals(5, phrase.getWords().size());
    }
}

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
        assertEquals(0, dawg.getVerticiesCount());
        assertEquals(0, dawg.getEdgesCount());
    }

    @Test
    public void adjacentVerticiesTest() {
        final WordList wordList = new WordList();
        wordList.addWordsFromPhrase(WordListTest.PHRASE_1);
        wordList.addWordsFromPhrase(WordListTest.PHRASE_2);
        wordList.addWordsFromPhrase(WordListTest.PHRASE_3);

        final DirectedAcyclicWordGraph dawg = new DirectedAcyclicWordGraph(wordList);
        dawg.addEdgesFromPhrase(WordListTest.PHRASE_1);
        dawg.addEdgesFromPhrase(WordListTest.PHRASE_2);
        dawg.addEdgesFromPhrase(WordListTest.PHRASE_3);
    }
}

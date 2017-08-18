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
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DirectedAcyclicWordGraphTest {

    @Test
    public void simpleDawgTest() {
        final DirectedAcyclicWordGraph dawg = new DirectedAcyclicWordGraph(new WordList());
        assertEquals(0, dawg.getVerticiesCount());
        assertEquals(0, dawg.getEdgesCount());
    }

    @Test
    public void addEdgesFromPhraseTest() {
        final WordList wordList = new WordList();
        wordList.addWordsFromPhrase(WordListTest.PHRASE_1);
        wordList.addWordsFromPhrase(WordListTest.PHRASE_2);
        wordList.addWordsFromPhrase(WordListTest.PHRASE_3);

        final DirectedAcyclicWordGraph dawg = new DirectedAcyclicWordGraph(wordList);
        dawg.addEdgesFromPhrase(WordListTest.PHRASE_1);
        dawg.addEdgesFromPhrase(WordListTest.PHRASE_2);
        dawg.addEdgesFromPhrase(WordListTest.PHRASE_3);

        assertEquals(7, dawg.getEdgesCount());
        assertTrue(dawg.hasOrder());
    }

    @Test
    public void addEdgesFromPhraseTest2() {
        final String[] strings = new String[] {
                "IT IS FIVE OCLOCK",
                "IT IS ALMOST FIVE OCLOCK",
                "IT IS A QUARTER TO FIVE",
                "IT IS HALF PAST FIVE",
                "IT IS FIVE THIRTY"
        };

        final WordList wordList = createWordList(strings);
        final DirectedAcyclicWordGraph dawg = createDawg(wordList, strings);

        assertTrue(dawg.hasOrder());
    }

    @Test
    public void oneForOnePhraseToWordlist() {
        final String string = "ONE TWO THREE FOUR FIVE";

        final WordList wordList = new WordList();
        wordList.addWordsFromPhrase(new Phrase(string));

        final DirectedAcyclicWordGraph dawg = new DirectedAcyclicWordGraph(wordList);
        dawg.addEdgesFromPhrase(new Phrase(string));

        assertTrue(dawg.hasOrder());
        assertEquals(4, dawg.getEdgesCount());
        assertEquals(5, dawg.getGroups().length);
    }

    @Test
    public void oneDuplicateWord() {
        final String string = "ONE TWO THREE FOUR FIVE FIVE";

        final WordList wordList = new WordList();
        wordList.addWordsFromPhrase(new Phrase(string));

        final DirectedAcyclicWordGraph dawg = new DirectedAcyclicWordGraph(wordList);
        dawg.addEdgesFromPhrase(new Phrase(string));

        assertTrue(dawg.hasOrder());
        assertEquals(5, dawg.getEdgesCount());
        assertEquals(6, dawg.getGroups().length);
    }

    @Test
    public void doubleWordsTest() {
        final String[] strings = new String[] {
                "IT IS FIVE TO FIVE",
                "IT IS TWENTY FIVE TO FIVE"
        };

        final WordList wordList = createWordList(strings);
        final DirectedAcyclicWordGraph dawg = createDawg(wordList, strings);

        assertTrue(dawg.hasOrder());
        assertEquals(6, dawg.getEdgesCount());
        assertEquals(6, dawg.getGroups().length);
    }

    private static WordList createWordList(final String[] strings) {
        final WordList wordList = new WordList();

        for (int i = 0; i < strings.length; ++i) {
            wordList.addWordsFromPhrase(new Phrase(strings[i]));
        }

        return wordList;
    }

    private static DirectedAcyclicWordGraph createDawg(final WordList wordList, final String[] strings) {

        final DirectedAcyclicWordGraph dawg = new DirectedAcyclicWordGraph(wordList);
        for (int i = 0; i < strings.length; ++i) {
            dawg.addEdgesFromPhrase(new Phrase(strings[i]));
        }

        return dawg;
    }
}

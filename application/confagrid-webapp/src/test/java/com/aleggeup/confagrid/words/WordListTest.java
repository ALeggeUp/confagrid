/*
 * WordListTest.java
 *
 * Copyright (C) 2017 [ A Legge Up ] <stephen@aleggeup.com>
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.words;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;

public class WordListTest
{
    private static final String PHRASE_STRING_1 = "IT IS ONE OCLOCK";
    private static final String PHRASE_STRING_2 = "IT IS TWO OCLOCK";
    private static final String PHRASE_STRING_3 = "IT IS THREE OCLOCK";

    private static final Word WORD_IS = new Word("IS", 0);
    private static final Word WORD_IT = new Word("IT", 0);
    private static final Word WORD_OCLOCK = new Word("OCLOCK", 0);
    private static final Word WORD_ONE = new Word("ONE", 0);
    private static final Word WORD_THREE = new Word("THREE", 0);
    private static final Word WORD_TWO = new Word("TWO", 0);

    protected static final Phrase PHRASE_1 = new Phrase(PHRASE_STRING_1);
    protected static final Phrase PHRASE_2 = new Phrase(PHRASE_STRING_2);
    protected static final Phrase PHRASE_3 = new Phrase(PHRASE_STRING_3);

    @Test
    public void simpleWordListTest() {
        final WordList wordList = new WordList();
        wordList.addWordsFromPhrase(PHRASE_1);
        wordList.addWordsFromPhrase(PHRASE_2);
        wordList.addWordsFromPhrase(PHRASE_3);
        wordList.addWordsFromPhrase(PHRASE_3);
        wordList.addWordsFromPhrase(PHRASE_2);
        wordList.addWordsFromPhrase(PHRASE_1);

        final Map<Word, Integer> wordIndex = wordList.getWords();
        assertTrue(wordIndex.containsKey(WORD_IS));
        assertTrue(wordIndex.containsKey(WORD_IT));
        assertTrue(wordIndex.containsKey(WORD_OCLOCK));
        assertTrue(wordIndex.containsKey(WORD_ONE));
        assertTrue(wordIndex.containsKey(WORD_THREE));
        assertTrue(wordIndex.containsKey(WORD_TWO));
        assertEquals(6, wordIndex.size());
    }

    @Test
    public void reverseLookupTest() {
        final WordList wordList = new WordList();
        wordList.addWordsFromPhrase(PHRASE_1);
        wordList.addWordsFromPhrase(PHRASE_2);
        wordList.addWordsFromPhrase(PHRASE_3);
        wordList.addWordsFromPhrase(PHRASE_3);
        wordList.addWordsFromPhrase(PHRASE_2);
        wordList.addWordsFromPhrase(PHRASE_1);

        final Word[] lookup = wordList.getReverseLookup();
        assertEquals(6, lookup.length);
        assertEquals(0, lookup[0].compareTo(WORD_IT));
        assertEquals(0, lookup[1].compareTo(WORD_IS));
        assertEquals(0, lookup[2].compareTo(WORD_ONE));
        assertEquals(0, lookup[3].compareTo(WORD_OCLOCK));
        assertEquals(0, lookup[4].compareTo(WORD_TWO));
        assertEquals(0, lookup[5].compareTo(WORD_THREE));
    }
}

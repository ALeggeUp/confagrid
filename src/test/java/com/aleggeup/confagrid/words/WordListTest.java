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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class WordListTest
{
    private static final String PHRASE_STRING_1 = "IT IS ONE OCLOCK";
    private static final String PHRASE_STRING_2 = "IT IS TWO OCLOCK";
    private static final String PHRASE_STRING_3 = "IT IS THREE OCLOCK";

    private static final Phrase PHRASE_1 = new Phrase(PHRASE_STRING_1);
    private static final Phrase PHRASE_2 = new Phrase(PHRASE_STRING_2);
    private static final Phrase PHRASE_3 = new Phrase(PHRASE_STRING_3);

    @Test
    public void simpleWordListTest() {
        final WordList wordList = new WordList();
        wordList.addWordsFromPhrase(PHRASE_1);
        wordList.addWordsFromPhrase(PHRASE_2);
        wordList.addWordsFromPhrase(PHRASE_3);
        wordList.addWordsFromPhrase(PHRASE_3);
        wordList.addWordsFromPhrase(PHRASE_2);
        wordList.addWordsFromPhrase(PHRASE_1);

        // final List<String> words = new ArrayList<>(Arrays.asList("IT", "IS", "THREE", "TWO", "ONE", "OCLOCK"));
        final List<String> words = new ArrayList<>(Arrays.asList("ONE", "OCLOCK", "IT", "IS", "TWO", "THREE"));
        assertEquals(words, wordList.getWords());
    }
}

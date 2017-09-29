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
    public void addEdgesFromPhraseTest3() {
        final String[] strings = new String[] {
                "IT IS ONE OCLOCK",
                "IT IS TWO OCLOCK",
                "IT IS THREE OCLOCK",
                "IT IS FOUR OCLOCK",
                "IT IS FIVE OCLOCK",
                "IT IS SIX OCLOCK",
                "IT IS SEVEN OCLOCK",
                "IT IS EIGHT OCLOCK",
                "IT IS NINE OCLOCK",
                "IT IS TEN OCLOCK",
                "IT IS ELEVEN OCLOCK",
                "IT IS TWELVE OCLOCK",
                "IT IS ALMOST ONE OCLOCK",
                "IT IS ALMOST TWO OCLOCK",
                "IT IS ALMOST THREE OCLOCK",
                "IT IS ALMOST FOUR OCLOCK",
                "IT IS ALMOST FIVE OCLOCK",
                "IT IS ALMOST SIX OCLOCK",
                "IT IS ALMOST SEVEN OCLOCK",
                "IT IS ALMOST EIGHT OCLOCK",
                "IT IS ALMOST NINE OCLOCK",
                "IT IS ALMOST TEN OCLOCK",
                "IT IS ALMOST ELEVEN OCLOCK",
                "IT IS ALMOST TWELVE OCLOCK",
                "IT IS A QUARTER TO ONE",
                "IT IS A QUARTER TO TWO",
                "IT IS A QUARTER TO THREE",
                "IT IS A QUARTER TO FOUR",
                "IT IS A QUARTER TO FIVE",
                "IT IS A QUARTER TO SIX",
                "IT IS A QUARTER TO SEVEN",
                "IT IS A QUARTER TO EIGHT",
                "IT IS A QUARTER TO NINE",
                "IT IS A QUARTER TO TEN",
                "IT IS A QUARTER TO ELEVEN",
                "IT IS A QUARTER TO TWELVE",
                "IT IS A QUARTER AFTER ONE",
                "IT IS A QUARTER AFTER TWO",
                "IT IS A QUARTER AFTER THREE",
                "IT IS A QUARTER AFTER FOUR",
                "IT IS A QUARTER AFTER FIVE",
                "IT IS A QUARTER AFTER SIX",
                "IT IS A QUARTER AFTER SEVEN",
                "IT IS A QUARTER AFTER EIGHT",
                "IT IS A QUARTER AFTER NINE",
                "IT IS A QUARTER AFTER TEN",
                "IT IS A QUARTER AFTER ELEVEN",
                "IT IS A QUARTER AFTER TWELVE",
                "IT IS HALF PAST ONE",
                "IT IS HALF PAST TWO",
                "IT IS HALF PAST THREE",
                "IT IS HALF PAST FOUR",
                "IT IS HALF PAST FIVE",
                "IT IS HALF PAST SIX",
                "IT IS HALF PAST SEVEN",
                "IT IS HALF PAST EIGHT",
                "IT IS HALF PAST NINE",
                "IT IS HALF PAST TEN",
                "IT IS HALF PAST ELEVEN",
                "IT IS HALF PAST TWELVE",
                "IT IS TEN TO ONE",
                "IT IS TEN TO TWO",
                "IT IS TEN TO THREE",
                "IT IS TEN TO FOUR",
                "IT IS TEN TO FIVE",
                "IT IS TEN TO SIX",
                "IT IS TEN TO SEVEN",
                "IT IS TEN TO EIGHT",
                "IT IS TEN TO NINE",
                "IT IS TEN TO TEN",
                "IT IS TEN TO ELEVEN",
                "IT IS TEN TO TWELVE",
                "IT IS FIVE TO ONE",
                "IT IS FIVE TO TWO",
                "IT IS FIVE TO THREE",
                "IT IS FIVE TO FOUR",
                "IT IS FIVE TO FIVE",
                "IT IS FIVE TO SIX",
                "IT IS FIVE TO SEVEN",
                "IT IS FIVE TO EIGHT",
                "IT IS FIVE TO NINE",
                "IT IS FIVE TO TEN",
                "IT IS FIVE TO ELEVEN",
                "IT IS FIVE TO TWELVE"
        };

        final WordList wordList = createWordList(strings);
        final DirectedAcyclicWordGraph dawg = createDawg(wordList, strings);

        assertTrue(dawg.hasOrder());
        dawg.getGroups();
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

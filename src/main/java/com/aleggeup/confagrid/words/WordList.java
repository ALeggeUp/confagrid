/*
 * WordList.java
 *
 * Copyright (C) 2017 [ A Legge Up ] <stephen@aleggeup.com>
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.words;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class WordList
{
    private final Map<String, Integer> wordIndex = new TreeMap<>();
    private final List<String> words = new ArrayList<>();

    public WordList() {
    }

    protected void addWordsFromPhrase(final Phrase phrase) {
        final Set<Word> wordsFromPhrase = phrase.getWords();

        int lastWordIndex = -1;
        for (final Word word : wordsFromPhrase) {
            if (!words.contains(word.getWord()) && lastWordIndex > -1) {
                words.add(++lastWordIndex, word.getWord());
            } else if (!words.contains(word.getWord())) {
                words.add(word.getWord());
                lastWordIndex = words.indexOf(word.getWord());
            } else {
                lastWordIndex = words.indexOf(word.getWord());
            }
        }
    }

    public List<String> getWords() {
        return words;
    }
}

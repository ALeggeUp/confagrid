/*
 * WordList.java
 *
 * Copyright (C) 2017 [ A Legge Up ] <stephen@aleggeup.com>
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.words;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class WordList
{
    private final Map<Word, Integer> wordIndex = new TreeMap<>();

    public WordList() {
    }

    protected void addWordsFromPhrase(final Phrase phrase) {
        final Set<Word> wordsFromPhrase = phrase.getWords();

        for (final Word word : wordsFromPhrase) {
            if (!wordIndex.containsKey(word)) {
                wordIndex.put(word, wordIndex.size());
            }
        }
    }

    protected Word[] getReverseLookup() {
        final Word[] wordArray = new Word[wordIndex.size()];

        for (final Entry<Word, Integer> entry : wordIndex.entrySet()) {
            wordArray[entry.getValue()] = entry.getKey();
        }

        return wordArray;
    }

    public Map<Word, Integer> getWords() {
        return wordIndex;
    }
}

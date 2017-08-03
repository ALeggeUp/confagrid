/*
 * Phrase.java
 *
 * Copyright (C) 2017 [ A Legge Up ] <stephen@aleggeup.com>
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.words;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Phrase
{
    private final Set<Word> words = new LinkedHashSet<>();
    private final Map<String, Integer> dups = new TreeMap<>();

    public Phrase(final String phrase) {
        final String[] phraseWords = phrase.toUpperCase().split("\\s");
        for (final String phraseWord : phraseWords) {
            if (phraseWord.trim().length() > 0) {
                if (!dups.containsKey(phraseWord)) {
                    dups.put(phraseWord, 0);
                    words.add(new Word(phraseWord));
                } else {
                    final int dupCount = dups.get(phraseWord).intValue();
                    dups.replace(phraseWord, Integer.valueOf(dupCount + 1));
                    words.add(new Word(phraseWord, dupCount + 1));
                }
            }
        }
    }

    protected Iterator<Word> iterator() {
        return words.iterator();
    }

    public Set<Word> getWords() {
        return words;
    }

    public boolean hasDups(final String phraseWord) {
        return dups.containsKey(phraseWord) && getDupValue(phraseWord) > 0;
    }

    public int getDupValue(final String phraseWord) {
        return dups.get(phraseWord).intValue();
    }

    @Override
    public String toString() {
        return words.isEmpty() ? "" : "Phrase [words=" + words + "]";
    }
}

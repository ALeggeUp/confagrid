/*
 * Word.java
 *
 * Copyright (C) 2017 [ A Legge Up ] <stephen@aleggeup.com>
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.words;

public class Word implements Comparable<Word> {

    private final String word;
    private final int occurrence;

    public Word(final String word) {
        this(word, 0);
    }

    public Word(final String word, final int occurrence) {
        this.word = word;
        this.occurrence = occurrence;
    }

    public String getWord() {
        return word;
    }

    public int getOccurrence() {
        return occurrence;
    }

    @Override
    public int compareTo(final Word o) {
        if (!o.getWord().equals(word)) {
            return o.getWord().compareTo(word);
        } else {
            return Integer.compare(o.getOccurrence(), occurrence);
        }
    }
}

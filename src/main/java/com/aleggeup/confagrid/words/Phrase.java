/*
 * Phrase.java
 *
 * Copyright (C) 2017 [ A Legge Up ] <stephen@aleggeup.com>
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.words;

import java.util.ArrayList;
import java.util.List;

public class Phrase
{
    private final List<Word> words = new ArrayList<>();

    public Phrase(final String phrase) {
        final String[] phraseWords = phrase.toUpperCase().split("\\s");
        for (final String phraseWord : phraseWords) {
            words.add(new Word(phraseWord));
        }
    }

    public List<Word> getWords() {
        return words;
    }
}

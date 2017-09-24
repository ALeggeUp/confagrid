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
            return word.compareTo(o.getWord());
        } else {
            return Integer.compare(occurrence, o.getOccurrence());
        }
    }

    @Override
    public String toString() {
        return word + " [" + occurrence + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + occurrence;
        result = prime * result + ((word == null) ? 0 : word.hashCode());

        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Word)) {
            return false;
        }
        Word other = (Word) obj;
        if (occurrence != other.occurrence) {
            return false;
        }
        if (word == null) {
            if (other.word != null) {
                return false;
            }
        } else if (!word.equals(other.word)) {
            return false;
        }
        return true;
    }
}

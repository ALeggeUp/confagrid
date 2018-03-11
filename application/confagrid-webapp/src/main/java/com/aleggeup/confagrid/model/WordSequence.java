/*
 * WordSequence.java
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class WordSequence implements Serializable {

    @EmbeddedId
    private WordSequenceId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("phraseId")
    @JsonIgnoreProperties("words")
    private Phrase phrase;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("wordId")
    private Word word;

    @Column
    private Integer sequence;

    private WordSequence() {
    }

    public WordSequence(final Phrase phrase, final Word word, final int sequence) {
        id = new WordSequenceId(phrase, word);
        this.phrase = phrase;
        this.word = word;
        this.sequence = sequence;
    }

    public WordSequenceId getId() {
        return id;
    }

    public Phrase getPhrase() {
        return phrase;
    }

    public void setPhrase(final Phrase phrase) {
        this.phrase = phrase;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(final Word word) {
        this.word = word;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(final int sequence) {
        this.sequence = sequence;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final WordSequence that = (WordSequence) o;

        return new EqualsBuilder()
            .append(phrase, that.phrase)
            .append(word, that.word)
            .append(sequence, that.sequence)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(phrase)
            .append(word)
            .append(sequence)
            .toHashCode();
    }

    @Override
    public String toString() {
        return "WordSequence{" + "phrase=" + phrase + ", word=" + word + ", sequence=" + sequence + '}';
    }
}

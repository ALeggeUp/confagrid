/*
 * PhraseWordSequence.java
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
import javax.persistence.OrderBy;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "PhraseWordSequence")
public class PhraseWordSequence implements Serializable {

    @EmbeddedId
    private PhraseWordSequenceId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("phraseId")
    @JsonIgnoreProperties("words")
    private Phrase phrase;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("wordSequenceId")
    @OrderBy("sequence")
    private WordSequence wordSequence;

    @Column
    private Integer sequenceOrder;

    private PhraseWordSequence() {
    }

    public PhraseWordSequence(final Phrase phrase, final WordSequence wordSequence) {
        id = new PhraseWordSequenceId(phrase, wordSequence);
        this.phrase = phrase;
        this.wordSequence = wordSequence;
        sequenceOrder = wordSequence.getSequence();
    }

    public PhraseWordSequenceId getId() {
        return id;
    }

    public Phrase getPhrase() {
        return phrase;
    }

    public void setPhrase(final Phrase phrase) {
        this.phrase = phrase;
    }

    public WordSequence getWordSequence() {
        return wordSequence;
    }

    public void setWordSequence(final WordSequence wordSequence) {
        this.wordSequence = wordSequence;
    }

    public Integer getSequenceOrder() {
        return sequenceOrder;
    }

    public void setSequenceOrder(final Integer sequenceOrder) {
        this.sequenceOrder = sequenceOrder;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final PhraseWordSequence that = (PhraseWordSequence) o;

        return new EqualsBuilder()
            .append(phrase, that.phrase)
            .append(wordSequence, that.wordSequence)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(phrase)
            .append(wordSequence)
            .toHashCode();
    }

    @Override
    public String toString() {
        return "PhraseWordSequence{" + "phrase=" + phrase + ", wordSequence=" + wordSequence + '}';
    }
}

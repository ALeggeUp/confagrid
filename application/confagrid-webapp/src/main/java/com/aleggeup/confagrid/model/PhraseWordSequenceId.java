/*
 * PhraseWordSequenceId.java
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Embeddable
public class PhraseWordSequenceId implements Serializable {

    @Column
    private UUID phraseId;

    @Column
    private UUID wordSequenceId;

    private PhraseWordSequenceId() {
    }

    public PhraseWordSequenceId(final Phrase phrase, final WordSequence wordSequence) {
        phraseId = phrase.getId();
        wordSequenceId = wordSequence.getId();
    }

    public UUID getWordSequenceId() {
        return wordSequenceId;
    }

    public void setWordSequenceId(final UUID wordId) {
        wordSequenceId = wordSequenceId;
    }

    public UUID getPhraseId() {
        return phraseId;
    }

    public void setPhraseId(final UUID phraseId) {
        this.phraseId = phraseId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final PhraseWordSequenceId that = (PhraseWordSequenceId) o;

        return new EqualsBuilder()
            .append(wordSequenceId, that.wordSequenceId)
            .append(phraseId, that.phraseId)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(wordSequenceId)
            .append(phraseId)
            .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("phraseId", phraseId)
            .append("wordSequenceId", wordSequenceId)
            .toString();
    }
}

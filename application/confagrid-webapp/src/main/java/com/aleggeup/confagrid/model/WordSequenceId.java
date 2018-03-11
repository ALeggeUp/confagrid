/*
 * WordSequenceId.java
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

@Embeddable
public class WordSequenceId implements Serializable {

    @Column
    private UUID phraseId;

    @Column
    private UUID wordId;

    private WordSequenceId() {
    }

    public WordSequenceId(final Phrase phrase, final Word word) {
        phraseId = phrase.getId();
        wordId = word.getId();
    }

    public UUID getWordId() {
        return wordId;
    }

    public void setWordId(final UUID wordId) {
        this.wordId = wordId;
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

        final WordSequenceId that = (WordSequenceId) o;

        return new EqualsBuilder()
            .append(wordId, that.wordId)
            .append(phraseId, that.phraseId)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(wordId)
            .append(phraseId)
            .toHashCode();
    }
}

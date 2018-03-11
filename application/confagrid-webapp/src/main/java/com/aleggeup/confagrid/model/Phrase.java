/*
 * Phrase.java
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.model;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
public class Phrase {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column
    private String raw;

    @OneToMany(mappedBy = "phrase", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("sequence")
    private List<WordSequence> words;

    private Phrase() {
    }

    public Phrase(final String raw) {
        id = UUID.randomUUID();
        setRaw(raw);
        words = new LinkedList<>();
    }

    public Phrase(final String raw, final List<WordSequence> words) {
        id = UUID.randomUUID();
        setRaw(raw);
        this.words = words;
    }

    public UUID getId() {
        return id;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(final String raw) {
        this.raw = raw.trim().toUpperCase();
    }

    public List<WordSequence> getWords() {
        return words;
    }

    public void setWords(final List<WordSequence> words) {
        this.words = words;
    }

    public void addWord(final WordSequence word) {
        words.add(word);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Phrase phrase = (Phrase) o;

        return new EqualsBuilder()
            .append(raw, phrase.raw)
            .append(words.size(), phrase.words.size())
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(raw)
            .append(words.size())
            .toHashCode();
    }

    @Override
    public String toString() {
        return String.format("Phrase{id='%s', raw='%s', wordcount=%d'}", id, raw, words.size());
    }
}

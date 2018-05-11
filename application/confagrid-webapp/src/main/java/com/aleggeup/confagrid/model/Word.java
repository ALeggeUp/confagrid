/*
 * Word.java
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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.GenericGenerator;

@Entity(name = "Word")
public class Word implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column
    private String text;

    @Column
    private Integer occurrence;

    public Word() {
    }

    public Word(final String text) {
        this(text, 0);
    }

    public Word(final String text, final int occurrence) {
        this.text = text;
        this.occurrence = occurrence;
    }

    public UUID getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    public int getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(final int occurrence) {
        this.occurrence = occurrence;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Word word = (Word) o;

        return new EqualsBuilder()
            .append(text, word.text)
            .append(occurrence, word.occurrence)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(text)
            .append(occurrence)
            .toHashCode();
    }

    @Override
    public String toString() {
        return String
            .format("Word{id='%s', text='%s', occurrence=%d}",
                id,
                text,
                occurrence);
    }
}

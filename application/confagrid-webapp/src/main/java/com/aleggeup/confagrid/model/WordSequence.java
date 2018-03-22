package com.aleggeup.confagrid.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class WordSequence implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Word word;

    @Column
    private Integer sequence;

    private WordSequence() {
    }

    public WordSequence(final Word word, final int sequence) {
        this.word = word;
        this.sequence = sequence;
    }

    public UUID getId() {
        return id;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(final Word word) {
        this.word = word;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(final Integer sequence) {
        this.sequence = sequence;
    }
}

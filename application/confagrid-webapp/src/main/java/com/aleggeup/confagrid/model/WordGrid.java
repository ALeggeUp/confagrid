/*
 * WordGrid.java
 *
 * Copyright (C) 2017 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.model;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class WordGrid {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH
    }, fetch = FetchType.EAGER, targetEntity = User.class)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_USER"))
    private User creator;

    @Column
    private String title;

    @Column
    private Integer dimensionWidth;

    @Column
    private Integer dimensionHeight;

    @Column
    private String description;

    public WordGrid() {
    }

    public WordGrid(final String title, final User creator, final Integer dimensionWidth, final Integer dimensionHeight,
        final String description) {
        this.title = title;
        this.creator = creator;
        this.dimensionWidth = dimensionWidth;
        this.dimensionHeight = dimensionHeight;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(final User creator) {
        this.creator = creator;
    }

    public int getDimensionWidth() {
        return dimensionWidth;
    }

    public void setDimensionWidth(final Integer dimensionWidth) {
        this.dimensionWidth = dimensionWidth;
    }

    public int getDimensionHeight() {
        return dimensionHeight;
    }

    public void setDimensionHeight(final Integer dimensionHeight) {
        this.dimensionHeight = dimensionHeight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "WordGrid [id=" + id + ", title=" + title + ", creator=" + creator + ", dimensionWidth=" + dimensionWidth
            + ", dimensionHeight=" + dimensionHeight + ", description=" + description + "]";
    }
}

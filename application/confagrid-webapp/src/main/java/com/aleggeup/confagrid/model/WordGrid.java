/*
 * WordGrid.java
 *
 * Copyright (C) 2017 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WordGrid {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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

    public WordGrid(final String title, final Integer dimensionWidth, final Integer dimensionHeight,
        final String description) {
        this.title = title;
        this.dimensionWidth = dimensionWidth;
        this.dimensionHeight = dimensionHeight;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
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
        return "WordGrid [id=" + id + ", title=" + title + ", dimensionWidth=" + dimensionWidth +
            ", dimensionHeight=" + dimensionHeight + ", description=" + description + "]";
    }
}

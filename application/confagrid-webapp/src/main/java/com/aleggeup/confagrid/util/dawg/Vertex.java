/*
 * Vertex.java
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.util.dawg;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.aleggeup.confagrid.model.Word;

public class Vertex {

    protected enum VertexType {
        ROOT,
        NORMAL,
        FINAL
    }

    private final Word word;
    private VertexType vertexType;

    private final Map<Word, Vertex> children = new LinkedHashMap<>();
    private final Set<Vertex> parents = new HashSet<>();

    private int count;
    private final int index;
    private int depth = 0;

    public Vertex(final int index, final Word word, final VertexType vertexType) {
        this.word = word;
        this.vertexType = vertexType;
        this.index = index;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(final int depth) {
        this.depth = depth;
    }

    public Vertex getChild(final Word word) {
        return children.get(word);
    }

    public Vertex replaceChild(final Word word, final Vertex vertex) {
        if (vertex.getDepth() < depth + 1) {
            vertex.setDepth(depth + 1);
        }
        children.replace(word, vertex);
        return vertex;
    }

    public Set<Vertex> getParents() {
        return parents;
    }

    public Vertex addCount(final int delta) {
        count += delta;
        return this;
    }

    public Set<Word> childrenKeys() {
        return children.keySet();
    }

    public Word getWord() {
        return word;
    }

    public int getCount() {
        return count;
    }

    public Vertex setState(final VertexType vertexType) {
        this.vertexType = vertexType;
        return this;
    }

    public Vertex addChild(final Word word, final Vertex vertex) {
        vertex.setDepth(depth + 1);
        children.put(word, vertex);
        return children.get(word);
    }

    public Vertex addParent(final Vertex vertex) {
        if (vertex.getDepth() + 1 > depth) {
            depth = vertex.getDepth() + 1;
        }
        parents.add(vertex);
        return vertex;
    }

    public VertexType getVertexType() {
        return vertexType;
    }

    public void setVertexType(final VertexType vertexType) {
        this.vertexType = vertexType;
    }

    public Map<Word, Vertex> getChildren() {
        return children;
    }

    public void setCount(final int count) {
        this.count = count;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Vertex vertex = (Vertex) o;

        return new EqualsBuilder()
            .append(word, vertex.word)
            .append(index, vertex.index)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(word)
            .append(index)
            .toHashCode();
    }

    @Override
    public String toString() {
        return "Vertex{" + "index=" + index + ", word=" + word + ", depth=" + depth + '}';
    }
}

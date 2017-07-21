/*
 * DirectedAcyclicWordGraph.java
 *
 * Copyright (C) 2017 [ A Legge Up ] <stephen@aleggeup.com>
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.words;

import java.util.ArrayList;
import java.util.List;

public class DirectedAcyclicWordGraph {

    private final WordList wordList;
    private final int verticies;
    private final int edges;
    private final List<List<Integer>> adjacentVerticies;

    public DirectedAcyclicWordGraph(final WordList wordList) {
        this.wordList = wordList;
        verticies = wordList.size();
        edges = 0;
        adjacentVerticies = new ArrayList<List<Integer>>();

        for (int i = 0; i < verticies; ++i) {
            adjacentVerticies.add(new ArrayList<Integer>());
        }
    }

    protected void addEdgesFromPhrase(final Phrase phrase) {
    }

    public int getVerticies() {
        return verticies;
    }

    public int getEdges() {
        return edges;
    }
}

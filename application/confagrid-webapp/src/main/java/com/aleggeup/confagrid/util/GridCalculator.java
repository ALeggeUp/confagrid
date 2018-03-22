/*
 * GridCalculator.java
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.util;

import java.util.ArrayList;
import java.util.List;

import com.aleggeup.confagrid.model.WordGrid;
import com.aleggeup.confagrid.model.WordGridCellModel;
import com.aleggeup.confagrid.util.dawg.DirectedAcyclicWordGraph;

public class GridCalculator {

    private final WordGrid wordGrid;

    private final List<List<Integer>> adjacentVerticies;

    private final DirectedAcyclicWordGraph directedAcyclicWordGraph;

    public GridCalculator(final WordGrid wordGrid) {
        this.wordGrid = wordGrid;
        adjacentVerticies = new ArrayList<>();

        directedAcyclicWordGraph = new DirectedAcyclicWordGraph(wordGrid);
    }

    public List<WordGridCellModel> asCharacters() {
        return directedAcyclicWordGraph.asCharacters();
    }
}

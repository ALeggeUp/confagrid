/*
 * DirectedAcyclicWordGraph.java
 *
 * Copyright (C) 2017 [ A Legge Up ] <stephen@aleggeup.com>
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.words;

public class DirectedAcyclicWordGraph
{
    private final int verticies;
    private final int edges;
    
    public DirectedAcyclicWordGraph() {
        verticies = 0;
        edges = 0;
    }

    public int getVerticies() {
        return verticies;
    }

    public int getEdges() {
        return edges;
    }
}

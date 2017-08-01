/*
 * DirectedAcyclicWordGraph.java
 *
 * Copyright (C) 2017 [ A Legge Up ] <stephen@aleggeup.com>
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.words;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

public class DirectedAcyclicWordGraph {

    private final WordList wordList;
    private final int verticiesCount;
    private final List<List<Integer>> adjacentVerticies;
    private final int[] indegrees;
    private final int[] ranks;

    private int edgesCount;

    public DirectedAcyclicWordGraph(final WordList wordList) {
        this.wordList = wordList;
        verticiesCount = wordList.size();
        edgesCount = 0;
        adjacentVerticies = new ArrayList<List<Integer>>();
        indegrees = new int[verticiesCount];
        ranks = new int[verticiesCount];

        for (int i = 0; i < verticiesCount; ++i) {
            adjacentVerticies.add(new ArrayList<Integer>());
        }
    }

    protected void addEdgesFromPhrase(final Phrase phrase) {
        Word tail = null;
        for (final Iterator<Word> iterator = phrase.iterator(); iterator.hasNext();) {
            final Word word = iterator.next();
            if (tail == null) {
                tail = word;
            } else {
                addEdge(tail, word);
                tail = word;
            }
        }
    }

    protected void addEdge(final Word tail, final Word head) {
        final int tailId = wordList.getWordId(tail);
        final int headId = wordList.getWordId(head);
        if (!adjacentVerticies.get(tailId).contains(Integer.valueOf(headId))) {
            adjacentVerticies.get(tailId).add(Integer.valueOf(headId));
            ++indegrees[headId];
            ++edgesCount;
        }
    }

    protected boolean hasOrder() {
        final Deque<Integer> queue = new ArrayDeque<>();
        final Deque<Integer> order = new ArrayDeque<>();
        final int[] indegreesDiff = new int[verticiesCount];

        for (int i = 0; i < verticiesCount; ++i) {
            indegreesDiff[i] = indegrees[i];
        }

        for (int i = 0; i < verticiesCount; ++i) {
            if (indegreesDiff[i] == 0) {
                queue.push(i);
            }
        }

        int count = 0;
        while (!queue.isEmpty()) {
            final int value = queue.pop().intValue();
            order.push(value);

            for (final Integer v : adjacentVerticies.get(value)) {
                indegreesDiff[v.intValue()]--;
                if (indegreesDiff[v.intValue()] == 0) {
                    queue.push(v);
                }
            }
        }

        return count == verticiesCount;
    }

    protected int[] getGroups() {
        final Deque<Integer> queue = new ArrayDeque<>();
        final Deque<Integer> order = new ArrayDeque<>();
        final int[] indegreesDiff = new int[verticiesCount];
        final int[] groups = new int[verticiesCount];

        for (int i = 0; i < verticiesCount; ++i) {
            indegreesDiff[i] = indegrees[i];
        }

        for (int i = 0; i < verticiesCount; ++i) {
            if (indegreesDiff[i] == 0) {
                queue.push(i);
            }
        }

        int count = 0;
        int group = 0;
        while (!queue.isEmpty()) {
            final int value = queue.pop().intValue();
            order.push(value);
            ranks[value] = count++;
            groups[value] = group;

            boolean newGroup = false;
            for (final Integer v : adjacentVerticies.get(value)) {
                indegreesDiff[v.intValue()]--;
                if (indegreesDiff[v.intValue()] == 0) {
                    newGroup = true;
                    queue.push(v);
                }
            }

            if (newGroup) {
                group++;
            }
        }

        return groups;
    }

    public int getVerticiesCount() {
        return verticiesCount;
    }

    public int getEdgesCount() {
        return edgesCount;
    }
}

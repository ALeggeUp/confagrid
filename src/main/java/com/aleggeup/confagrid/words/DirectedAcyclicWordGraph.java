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
import java.util.Map;
import java.util.TreeMap;

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
        adjacentVerticies = new ArrayList<>();
        indegrees = new int[verticiesCount];
        ranks = new int[verticiesCount];

        for (int i = 0; i < verticiesCount; ++i) {
            adjacentVerticies.add(new ArrayList<Integer>());
        }
    }

    public void addEdgesFromPhrase(final Phrase phrase) {
        Word tail = null;
        final Map<String, Integer> count = new TreeMap<>();
        for (final Iterator<Word> iterator = phrase.iterator(); iterator.hasNext();) {
            Word word = iterator.next();
            int occurrences = wordList.getDupList().get(word.getWord()).intValue();
            if (occurrences > 0 && !phrase.hasDups(word)) {
                word = new Word(word.getWord(), occurrences);
            } else if (phrase.dupCount(word) < occurrences) {
                count.put(word.getWord(), count.getOrDefault(word.getWord(), -1).intValue() + 1);
                word = new Word(word.getWord(), occurrences - phrase.dupCount(word) + count.get(word.getWord()).intValue());
            }

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
            ++count;

            for (final Integer v : adjacentVerticies.get(value)) {
                indegreesDiff[v.intValue()]--;
                if (indegreesDiff[v.intValue()] == 0) {
                    queue.push(v);
                }
            }
        }

        return count == verticiesCount;
    }

    public int[] getGroups() {
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

        for (int i = 0; i < verticiesCount; ++i) {
            System.out.println(String.format("%d: %d -- %s", i, groups[i], wordList.getReverseLookup()[i].toString()));
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

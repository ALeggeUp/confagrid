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
import java.util.concurrent.atomic.AtomicIntegerArray;

public class DirectedAcyclicWordGraph {

    private final WordList wordList;
    private final List<List<Integer>> adjacentVerticies;
    private int verticiesCount;
    private AtomicIntegerArray indegrees;

    private int edgesCount;

    public DirectedAcyclicWordGraph(final WordList wordList) {
        this.wordList = wordList;
        verticiesCount = wordList.size();
        edgesCount = 0;
        adjacentVerticies = new ArrayList<>();
        indegrees = new AtomicIntegerArray(verticiesCount);

        for (int i = 0; i < verticiesCount; ++i) {
            adjacentVerticies.add(new ArrayList<>());
        }
    }

    public void addEdgesFromPhrase(final Phrase phrase) {
        Word tail = null;
        for (final Iterator<Word> iterator = phrase.iterator(); iterator.hasNext();) {
            final Word word = iterator.next();

            if (tail == null) {
                tail = word;
            } else {
                tail = safeAddEdge(tail, word);
            }
        }
    }

    private Word safeAddEdge(final Word tail, final Word head) {
        final int tailId = wordList.getWordId(tail);
        int headId = wordList.getWordId(head);
        final int maxTries = 5;

        for (int i = 0; i < maxTries; ++i) {
            if (safeAddEdge(tailId, headId)) {
                return wordList.getWord(headId);
            } else {
                final Word nextWord = new Word(head.getWord(), head.getOccurrence() + 1 + i);
                if (wordList.getWordId(nextWord) >= 0) {
                    headId = wordList.getWordId(nextWord);
                } else {
                    ++verticiesCount;
                    adjacentVerticies.add(new ArrayList<>());
                    indegrees = resizeAndCopy(indegrees);
                    wordList.addWord(nextWord);
                    headId = wordList.getWordId(nextWord);
                }
            }
        }

        return null;
    }

    private AtomicIntegerArray resizeAndCopy(final AtomicIntegerArray array) {
        final int srcLen = array.length();
        final int[] copy = new int[srcLen + 1];
        for (int i = 0; i < srcLen; ++i) {
            copy[i] = array.get(i);
        }

        return new AtomicIntegerArray(copy);
    }

    private boolean safeAddEdge(final int tailId, final int headId) {
        if (!adjacentVerticies.get(tailId).contains(Integer.valueOf(headId))) {
            adjacentVerticies.get(tailId).add(Integer.valueOf(headId));
            indegrees.incrementAndGet(headId);
            ++edgesCount;
            if (!hasOrder()) {
                adjacentVerticies.get(tailId).remove(Integer.valueOf(headId));
                indegrees.decrementAndGet(headId);
                --edgesCount;

                return false;
            }
        }

        return true;
    }

    protected boolean hasOrder() {
        final Deque<Integer> queue = new ArrayDeque<>();
        final Deque<Integer> order = new ArrayDeque<>();
        final int[] indegreesDiff = new int[verticiesCount];

        for (int i = 0; i < verticiesCount; ++i) {
            indegreesDiff[i] = indegrees.get(i);
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
            indegreesDiff[i] = indegrees.get(i);
        }

        for (int i = 0; i < verticiesCount; ++i) {
            if (indegreesDiff[i] == 0) {
                queue.push(i);
            }
        }

        int group = 0;
        while (!queue.isEmpty()) {
            final int value = queue.pop().intValue();
            order.push(value);
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

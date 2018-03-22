/*
 * DirectedAcyclicWordGraph.java
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.util.dawg;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aleggeup.confagrid.model.Phrase;
import com.aleggeup.confagrid.model.PhraseWordSequence;
import com.aleggeup.confagrid.model.Word;
import com.aleggeup.confagrid.model.WordGrid;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class DirectedAcyclicWordGraph {

    private static final Logger LOGGER = LoggerFactory.getLogger(DirectedAcyclicWordGraph.class);

    protected final Vertex ROOT;

    private final Deque<Vertex> stack = new ArrayDeque<>();
    final Multimap<Word, Vertex> registry = HashMultimap.create();

    private final WordGrid wordGrid;

    private int stateIndex = 1;

    public DirectedAcyclicWordGraph(final WordGrid wordGrid) {
        LOGGER.info("DirectedAcyclicWordGraph Start");
        ROOT = new Vertex(0, new Word("", 0), Vertex.VertexType.ROOT);
        this.wordGrid = wordGrid;

        final List<List<PhraseWordSequence>> phrases = getPhrases();

        phrases.stream()
            .map(this::addPhrase)
            .forEach(vertex -> {
                enqueue(vertex);
                minimize();
            });

        enqueue(ROOT);
        minimize();
        recursiveLog(ROOT, 0);
        final Map<Integer, List<Vertex>> groupsByDepth = groupByDepth(ROOT);
        groupsByDepth.forEach((integer, vertices) -> {
            LOGGER.info("DEPTH : " + integer);
            vertices.stream().distinct().forEach(vertex -> LOGGER.info(vertex.toString()));
        });
    }

    public void enqueue(final Vertex vertex) {
        LOGGER.info("enque vertex: " + vertex);
        stack.push(vertex);
        vertex.childrenKeys().forEach(word -> {
            final Vertex child = vertex.getChild(word);
            if (!Vertex.VertexType.FINAL.equals(child.getVertexType())) {
                enqueue(vertex.getChild(word));
            }
        });
    }

    public void minimize() {
        while (stack.size() > 0) {
            final Vertex vertex = stack.pop();
            Vertex match = null;
            for (final Vertex candidate : registry.get(vertex.getWord())) {
                if (vertex.getWord().equals(candidate.getWord())) {
                    if (candidate.childrenKeys().containsAll(vertex.childrenKeys())) {
                        LOGGER.info("candidate match: " + candidate);
                        match = candidate;
                        break;
                    } else {
                        LOGGER.info("CANDIDATE WORD MATCH BUT CHILDREN DON'T: " + vertex + " -- " + candidate);
                        LOGGER.info("CANDIDATE WORD MATCH BUT CHILDREN DON'T: " + vertex.childrenKeys() + " -- "
                            + candidate.childrenKeys());
                    }
                }
            }
            final Vertex replace = match;
            if (replace == null) {
                LOGGER.info("Add vertex to registry: " + vertex);
                registry.put(vertex.getWord(), vertex);
            } else {
                if (!vertex.equals(replace)) {
                    LOGGER.info("replace: " + vertex + " with " + replace);
                    vertex.getParents().forEach(parent -> {
                        parent.replaceChild(vertex.getWord(), replace);
                    });
                }
            }
        }
    }

    public Map<Integer, List<Vertex>> groupByDepth(final Vertex vertex) {
        final Map<Integer, List<Vertex>> groups = new HashMap<>();
        groupBy(groups, vertex);
        LOGGER.info(groups.toString());
        return groups;
    }

    public void groupBy(final Map<Integer, List<Vertex>> groups, final Vertex vertex) {
        final List<Vertex> vertices;
        if (groups.containsKey(vertex.getDepth())) {
            vertices = groups.get(vertex.getDepth());
        } else {
            vertices = new ArrayList<>();
        }
        vertices.add(vertex);
        groups.putIfAbsent(vertex.getDepth(), vertices);
        vertex.childrenKeys().forEach(word -> {
            groupBy(groups, vertex.getChild(word));
        });
    }

    public void recursiveLog(final Vertex state, final int depth) {
        final String pad = StringUtils.repeat("  ", depth);
        if (state.childrenKeys().size() > 0) {
            LOGGER.info(pad + "children of: " + state.toString());
        }
        state.childrenKeys().forEach(word -> {
            LOGGER.info(pad + "  -> " + state.getChild(word));
            recursiveLog(state.getChild(word), depth + 1);
        });
    }

    private List<List<PhraseWordSequence>> getPhrases() {
        return wordGrid.getPhrases()
            .stream()
            .sorted(Comparator.comparing(Phrase::getRaw))
            .map(phrase -> {
                LOGGER.info(phrase.getRaw());
                return phrase.getWords();
            })
            .collect(Collectors.toList());
    }

    private Vertex addPhrase(final List<PhraseWordSequence> phraseWordSequences) {
        final Pair<Vertex, List<PhraseWordSequence>> nodeAndSuffix = computeDeepestCommonNodeAndSuffix(ROOT,
            phraseWordSequences);
        return addSuffix(nodeAndSuffix.getLeft(), nodeAndSuffix.getRight());
    }

    public Pair<Vertex, List<PhraseWordSequence>> computeDeepestCommonNodeAndSuffix(final Vertex vertex,
        final List<PhraseWordSequence> phraseWordSequences) {
        final Word head = phraseWordSequences.get(0).getWordSequence().getWord();
        final Vertex child = vertex.getChild(head);
        if (child != null) {
            child.addCount(1);
            return computeDeepestCommonNodeAndSuffix(child, phraseWordSequences.subList(1, phraseWordSequences.size()));
        } else {
            return Pair.of(vertex, phraseWordSequences);
        }
    }

    public Vertex addSuffix(final Vertex vertex, final List<PhraseWordSequence> phraseWordSequences) {
        LOGGER.info("ADD SUFFIX: " + vertex.getWord().getText());
        Vertex lastState = vertex;
        for (final PhraseWordSequence phraseWordSequence : phraseWordSequences) {
            final Vertex child = lastState
                .addChild(phraseWordSequence.getWordSequence().getWord(),
                    new Vertex(stateIndex++, phraseWordSequence.getWordSequence().getWord(),
                        Vertex.VertexType.NORMAL))
                .addCount(1);
            child.addParent(lastState);
            lastState = child;
        }
        lastState.setState(Vertex.VertexType.FINAL);

        return lastState;
    }
}

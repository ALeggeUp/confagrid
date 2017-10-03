/*
 * WordGridInitializer.java
 *
 * Copyright (C) 2017 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.data;

import java.util.Iterator;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aleggeup.confagrid.model.WordGrid;

@Component
public class WordGridInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WordGridInitializer.class);

    private final WordGridRepository wordGridRepository;

    @Autowired
    public WordGridInitializer(final WordGridRepository wordGridRepository) {
        this.wordGridRepository = wordGridRepository;
    }

    @PostConstruct
    public void postContruct() {
        if (this.wordGridRepository.count() == 0) {
            initialize();
        } else {
            findAll();
        }
    }

    private void initialize() {
        LOGGER.info("-> Initializing WordGrid list with seed data");
        this.wordGridRepository.save(new WordGrid("title 1", 16, 12, "description 1"));
        this.wordGridRepository.save(new WordGrid("title 2", 12, 16, "description 2"));
    }

    private void findAll() {
        for (final Iterator<WordGrid> iterator = this.wordGridRepository.findAll().iterator(); iterator.hasNext();) {
            final WordGrid wordGrid = iterator.next();
            LOGGER.info(wordGrid.toString());
        }
    }
}

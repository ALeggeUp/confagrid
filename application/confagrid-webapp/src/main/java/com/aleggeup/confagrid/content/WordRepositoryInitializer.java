/*
 * WordRepositoryInitializer.java
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.content;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WordRepositoryInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WordRepositoryInitializer.class);

    private final WordRepository wordRepository;

    @Autowired
    public WordRepositoryInitializer(final WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @PostConstruct
    public void postConstruct() {
        if (wordRepository.count() == 0) {
            LOGGER.info("Word Repository is Being Initialized.");
            initialize();
        } else {
            LOGGER.info("Word Repository is Already Initialized.");
        }
    }

    private void initialize() {
    }
}

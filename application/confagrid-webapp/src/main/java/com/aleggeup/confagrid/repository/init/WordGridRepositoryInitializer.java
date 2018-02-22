/*
 * WordGridInitializer.java
 *
 * Copyright (C) 2017 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.repository.init;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aleggeup.confagrid.model.WordGrid;
import com.aleggeup.confagrid.repository.UserRepository;
import com.aleggeup.confagrid.repository.WordGridRepository;

@Component
public class WordGridRepositoryInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WordGridRepositoryInitializer.class);

    private final WordGridRepository wordGridRepository;

    private final UserRepository userRepository;

    @Autowired
    public WordGridRepositoryInitializer(final WordGridRepository wordGridRepository,
        final UserRepository userRepository) {
        this.wordGridRepository = wordGridRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void postConstruct() {
        if (wordGridRepository.count() == 0) {
            LOGGER.info("WordGrid Repository is Being Initialized.");
            initialize();
        } else {
            LOGGER.info("WordGrid Repository is Already Initialized.");
        }
    }

    private void initialize() {
        LOGGER.info("-> Initializing WordGrid list with seed data");
        wordGridRepository.save(new WordGrid("title 1", userRepository.findByName("admin"), 16, 12, "description 1"));
        wordGridRepository.save(new WordGrid("title 2", userRepository.findByName("user"), 12, 16, "description 2"));
    }
}

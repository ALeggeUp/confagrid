/*
 * PhraseRepositoryInitializer.java
 *
 * Copyright (C) 2018 [ A Legge Up ]
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

import com.aleggeup.confagrid.service.PhraseService;

@Component
public class PhraseRepositoryInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(PhraseRepositoryInitializer.class);

    private final PhraseService phraseService;

    @Autowired
    public PhraseRepositoryInitializer(final PhraseService phraseService) {
        this.phraseService = phraseService;
    }

    @PostConstruct
    public void postConstruct() {
        if (phraseService.count() == 0) {
            LOGGER.info("Phrase Repository is Being Initialized.");
            initialize();
        } else {
            LOGGER.info("Phrase Repository is Already Initialized.");
        }
    }

    private void initialize() {
        phraseService.phraseFromText("brought to you by ALEGGEUP");
        phraseService.phraseFromText("IT IS ALMOST ONE OCLOCK");
        phraseService.phraseFromText("IT IS ALMOST TWO OCLOCK");
        phraseService.phraseFromText("IT IS ALMOST THREE OCLOCK");
        phraseService.phraseFromText("IT IS ALMOST FOUR OCLOCK");
        phraseService.phraseFromText("IT IS ALMOST FIVE OCLOCK");
        phraseService.phraseFromText("IT IS ALMOST SIX OCLOCK");
        phraseService.phraseFromText("IT IS ALMOST SEVEN OCLOCK");
        phraseService.phraseFromText("THIS IS ALMOST ONE OTHER TEST");
        phraseService.phraseFromText("IT IS TIME FOR ANOTHER TEST WITH TEN");
    }
}

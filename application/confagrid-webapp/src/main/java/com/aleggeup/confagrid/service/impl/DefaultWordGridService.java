/*
 * DefaultWordGridService.java
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.service.impl;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aleggeup.confagrid.model.Phrase;
import com.aleggeup.confagrid.model.WordGrid;
import com.aleggeup.confagrid.repository.WordGridRepository;
import com.aleggeup.confagrid.service.PhraseService;
import com.aleggeup.confagrid.service.WordGridService;
import com.aleggeup.confagrid.util.GridCalculator;

@Service
public class DefaultWordGridService implements WordGridService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultWordService.class);

    private final WordGridRepository wordGridRepository;

    private final PhraseService phraseService;

    @Autowired
    public DefaultWordGridService(final WordGridRepository wordGridRepository, final PhraseService phraseService) {
        this.wordGridRepository = wordGridRepository;
        this.phraseService = phraseService;
    }

    @Override
    public WordGrid save(final WordGrid wordGrid) {
        return wordGridRepository.save(wordGrid);
    }

    @Override
    public WordGrid findOne(final UUID id) {
        return wordGridRepository.findOne(id);
    }

    @Override
    public List<WordGrid> findAll() {
        return wordGridRepository.findAll();
    }

    @Override
    public void update(final UUID wordGridId, final UUID phraseId) {
        final WordGrid wordGrid = wordGridRepository.findOne(wordGridId);
        final Phrase phrase = phraseService.findOne(phraseId);
        wordGrid.addPhrase(phrase);
        wordGridRepository.saveAndFlush(wordGrid);
    }

    @Override
    public void calculate(final WordGrid wordGrid) {
        final GridCalculator gridCalculator = new GridCalculator(wordGrid);
    }
}

/*
 * DefaultWordService.java
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aleggeup.confagrid.model.Word;
import com.aleggeup.confagrid.repository.WordRepository;
import com.aleggeup.confagrid.service.WordService;

@Service
public class DefaultWordService implements WordService {

    private final WordRepository wordRepository;

    @Autowired
    public DefaultWordService(final WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @Override
    public void save(final Word word) {
        wordRepository.save(word);
    }
}

/*
 * PhraseController.java
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aleggeup.confagrid.model.Phrase;
import com.aleggeup.confagrid.repository.PhraseRepository;

@Controller
@RequestMapping("/api/v1")
public class PhraseController {

    private final PhraseRepository phraseRepository;

    @Autowired
    public PhraseController(final PhraseRepository phraseRepository) {
        this.phraseRepository = phraseRepository;
    }

    @RequestMapping("phrases")
    @ResponseBody
    public List<Phrase> allWordGridPhrases() {
        return phraseRepository.findAll();
    }
}

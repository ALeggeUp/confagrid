/*
 * PhraseController.java
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.content;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aleggeup.confagrid.model.Phrase;
import com.aleggeup.confagrid.model.mex.PhraseCreateRequest;

@Controller
@RequestMapping("/api/v1")
public class PhraseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PhraseController.class);

    private static final String PATH_PART_PHRASES = "phrases";
    private static final String PATH_ACTION_CREATE = "create";

    private final PhraseService phraseService;

    @Autowired
    public PhraseController(final PhraseService phraseService) {
        this.phraseService = phraseService;
    }

    @RequestMapping(value = PATH_PART_PHRASES, method = RequestMethod.GET)
    @ResponseBody
    public List<Phrase> allPhrases() {
        return phraseService.findAll();
    }

    @RequestMapping(value = PATH_PART_PHRASES + "/" + PATH_ACTION_CREATE, method = RequestMethod.PUT)
    @ResponseBody
    public Phrase create(@RequestBody final PhraseCreateRequest request) {
        LOGGER.info("create -> " + request.getPhrase());
        return phraseService.phraseFromText(request.getPhrase());
    }
}

/*
 * WordController.java
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

import com.aleggeup.confagrid.model.Word;
import com.aleggeup.confagrid.repository.WordRepository;

@Controller
@RequestMapping("/api/v1")
public class WordController {

    private final WordRepository wordRepository;

    @Autowired
    public WordController(final WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @RequestMapping("words")
    @ResponseBody
    public List<Word> allWords() {
        return wordRepository.findAll();
    }
}

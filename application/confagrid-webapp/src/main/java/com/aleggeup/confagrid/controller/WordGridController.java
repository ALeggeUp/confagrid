/*
 * WordGridController.java
 *
 * Copyright (C) 2017 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aleggeup.confagrid.model.WordGrid;
import com.aleggeup.confagrid.repository.WordGridRepository;

@Controller
@RequestMapping("/api/v1")
public class WordGridController {

    private final WordGridRepository wordGridRepository;

    @Autowired
    public WordGridController(final WordGridRepository wordGridRepository) {
        this.wordGridRepository = wordGridRepository;
    }

    @RequestMapping("word-grids")
    @ResponseBody
    public List<WordGrid> allWordGrids() {
        final List<WordGrid> wordGrids = new ArrayList<>();

        for (final WordGrid wordGrid : wordGridRepository.findAll()) {
            wordGrids.add(wordGrid);
        }

        return wordGrids;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/word-grids")
    @ResponseBody
    public List<WordGrid> create(@RequestBody final WordGrid wordGrid) {
        final List<WordGrid> wordGrids = new ArrayList<>();
        final WordGrid savedWordGrid = wordGridRepository.save(wordGrid);
        wordGrids.add(savedWordGrid);

        return wordGrids;
    }
}

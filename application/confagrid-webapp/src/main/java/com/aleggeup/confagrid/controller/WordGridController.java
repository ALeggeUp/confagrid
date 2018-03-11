/*
 * WordGridController.java
 *
 * Copyright (C) 2017-2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aleggeup.confagrid.model.WordGrid;
import com.aleggeup.confagrid.model.mex.WordGridContentResponse;
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
        return wordGridRepository.findAll();
    }

    @RequestMapping(value = "word-grids", method = RequestMethod.POST)
    @ResponseBody
    public List<WordGrid> create(@RequestBody final WordGrid wordGrid) {
        final WordGrid savedWordGrid = wordGridRepository.save(wordGrid);

        return Arrays.asList(savedWordGrid);
    }

    @RequestMapping(value = "word-grid/{id}", method = RequestMethod.GET)
    @ResponseBody
    public WordGrid getWordGrid(@PathVariable("id") final String uuid) {
        return wordGridRepository.findOne(UUID.fromString(uuid));
    }

    @RequestMapping(value = "word-grid/content", method = RequestMethod.GET)
    @ResponseBody
    public WordGridContentResponse getResponse() {
        return new WordGridContentResponse().withGridWidth(16).withGridHeight(12).withCells(Collections.emptyList());
    }
}

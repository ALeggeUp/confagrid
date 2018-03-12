/*
 * WordGridController.java
 *
 * Copyright (C) 2017-2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.controller;

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
import com.aleggeup.confagrid.model.mex.WordGridUpdateRequest;
import com.aleggeup.confagrid.service.WordGridService;

@Controller
@RequestMapping("/api/v1")
public class WordGridController {

    private static final int DEFAULT_GRID_WIDTH = 16;
    private static final int DEFAULT_GRID_HEIGHT = 12;

    private final WordGridService wordGridService;

    @Autowired
    public WordGridController(final WordGridService wordGridService) {
        this.wordGridService = wordGridService;
    }

    @RequestMapping("word-grids")
    @ResponseBody
    public List<WordGrid> allWordGrids() {
        return wordGridService.findAll();
    }

    @RequestMapping(value = "word-grids", method = RequestMethod.POST)
    @ResponseBody
    public List<WordGrid> create(@RequestBody final WordGrid wordGrid) {
        final WordGrid savedWordGrid = wordGridService.save(wordGrid);

        return Collections.singletonList(savedWordGrid);
    }

    @RequestMapping(value = "word-grid/{id}", method = RequestMethod.GET)
    @ResponseBody
    public WordGrid getWordGrid(@PathVariable("id") final String uuid) {
        return wordGridService.findOne(UUID.fromString(uuid));
    }

    @RequestMapping(value = "word-grid/content", method = RequestMethod.GET)
    @ResponseBody
    public WordGridContentResponse getResponse() {
        return new WordGridContentResponse().withGridWidth(DEFAULT_GRID_WIDTH).withGridHeight(DEFAULT_GRID_HEIGHT)
            .withCells(Collections.emptyList());
    }

    @RequestMapping(value = "word-grid/{id}/update", method = RequestMethod.PUT)
    @ResponseBody
    public WordGridContentResponse update(@PathVariable("id") final String uuid,
        @RequestBody final WordGridUpdateRequest request) {
        wordGridService.update(UUID.fromString(uuid), UUID.fromString(request.getPhrase()));
        return new WordGridContentResponse().withGridWidth(DEFAULT_GRID_WIDTH).withGridHeight(DEFAULT_GRID_HEIGHT)
            .withCells(Collections.emptyList());
    }
}

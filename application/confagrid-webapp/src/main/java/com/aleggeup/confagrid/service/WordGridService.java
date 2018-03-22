/*
 * WordGridService.java
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.service;

import java.util.List;
import java.util.UUID;

import com.aleggeup.confagrid.model.WordGrid;

public interface WordGridService {

    WordGrid save(WordGrid wordGrid);

    WordGrid findOne(UUID id);

    List<WordGrid> findAll();

    void update(UUID wordGridId, UUID phraseId);

    void calculate(WordGrid wordGrid);
}

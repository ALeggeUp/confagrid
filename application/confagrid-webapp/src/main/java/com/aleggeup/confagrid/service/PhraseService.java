/*
 * PhraseService.java
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.service;

import com.aleggeup.confagrid.model.Phrase;

public interface PhraseService {

    void save(Phrase phrase);

    Phrase phraseFromText(String text);

    long count();
}

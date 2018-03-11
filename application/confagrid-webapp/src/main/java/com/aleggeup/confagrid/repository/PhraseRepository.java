/*
 * PhraseRepository.java
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.aleggeup.confagrid.model.Phrase;

public interface PhraseRepository extends JpaRepository<Phrase, UUID>, QueryDslPredicateExecutor<Phrase> {
}

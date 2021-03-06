/*
 * WordGridRepository.java
 *
 * Copyright (C) 2017 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.content;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.aleggeup.confagrid.model.WordGrid;

public interface WordGridRepository extends JpaRepository<WordGrid, UUID>, QueryDslPredicateExecutor<WordGrid> {
}

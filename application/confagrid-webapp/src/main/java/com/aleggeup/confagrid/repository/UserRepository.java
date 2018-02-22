/*
 * UserRepository.java
 *
 * Copyright (C) 2017 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.aleggeup.confagrid.model.User;

@Transactional
public interface UserRepository extends CrudRepository<User, UUID> {

    User findByName(String name);
}

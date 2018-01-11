/*
 * UserRepository.java
 *
 * Copyright (C) 2017 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.repository;

import org.springframework.data.repository.CrudRepository;

import com.aleggeup.confagrid.model.User;

public interface UserRepository extends CrudRepository<User, String> {
}

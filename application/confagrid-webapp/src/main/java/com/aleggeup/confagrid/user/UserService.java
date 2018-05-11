/*
 * UserService.java
 *
 * Copyright (C) 2017 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.user;

import java.util.UUID;

public interface UserService {

    void save(UserEntity userEntity);

    UserEntity getById(UUID key);

    boolean containsKey(UUID key);

    boolean containsName(String name);

    UserEntity findByName(String name);

    String encrypt(String raw);

    boolean matches(String raw, String encoded);

    long count();
}

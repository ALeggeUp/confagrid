/*
 * EmbeddedUserService.java
 *
 * Copyright (C) 2017 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.user;

import java.security.SecureRandom;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmbeddedUserService implements UserService {

    private static final int BCRYPT_ROUNDS = 12;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public EmbeddedUserService(final UserRepository userRepository, final SecureRandom secureRandom) {
        this.userRepository = userRepository;
        passwordEncoder = new BCryptPasswordEncoder(BCRYPT_ROUNDS, secureRandom);
    }

    @Override
    public void save(final UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    @Override
    public UserEntity getById(final UUID key) {
        return userRepository.findOne(key);
    }

    @Override
    public boolean containsKey(final UUID key) {
        return userRepository.exists(key);
    }

    @Override
    public boolean containsName(final String name) {
        return findByName(name) != null;
    }

    @Override
    public UserEntity findByName(final String name) {
        return userRepository.findByName(name);
    }

    @Override
    public String encrypt(final String raw) {
        return passwordEncoder.encode(raw);
    }

    @Override
    public boolean matches(final String raw, final String encoded) {
        return passwordEncoder.matches(raw, encoded);
    }

    @Override
    public long count() {
        return userRepository.count();
    }
}

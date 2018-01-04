/*
 * EmbeddedUserService.java
 *
 * Copyright (C) 2017 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.service;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.aleggeup.confagrid.repository.UserRepository;
import com.aleggeup.confagrid.repository.entity.User;

@Service
public class EmbeddedUserService implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public EmbeddedUserService(final UserRepository userRepository, final SecureRandom secureRandom) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder(10, secureRandom);
    }

    @Override
    public void save(final User user) {
        userRepository.save(user);
    }

    @Override
    public User getById(final String key) {
        return userRepository.findOne(key);
    }

    @Override
    public boolean containsKey(final String key) {
        return userRepository.exists(key);
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

/*
 * UserRepositoryInitializer.java
 *
 * Copyright (C) 2017 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.user;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryInitializer {

    private static final Logger LOG = LoggerFactory.getLogger(UserRepositoryInitializer.class);

    private static final String ROLE_USER = "user";
    private static final String ROLE_ADMIN = "admin";

    private final UserService userService;

    @Autowired
    public UserRepositoryInitializer(final UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void postConstruct() {
        if (userService.count() == 0) {
            LOG.info("UserEntity Repository is Being Initialized.");
            initialize();
        } else {
            LOG.info("UserEntity Repository is Already Initialized.");
        }
    }

    private Set<RoleEntity> roles(final String... roleStrings) {
        final Set<RoleEntity> roleEntities = new HashSet<>();

        for (final String roleName : roleStrings) {
            final RoleEntity roleEntity = new RoleEntity(roleName);
            roleEntities.add(roleEntity);
        }

        return roleEntities;
    }

    private void initialize() {
        userService.save(new UserEntity("admin", userService.encrypt("admin"), roles(ROLE_USER, ROLE_ADMIN)));
        userService.save(new UserEntity("user", userService.encrypt("password"), roles(ROLE_USER)));
    }
}

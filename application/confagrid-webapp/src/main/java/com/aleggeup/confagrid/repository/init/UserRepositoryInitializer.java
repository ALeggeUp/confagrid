/*
 * UserRepositoryInitializer.java
 *
 * Copyright (C) 2017 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.repository.init;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aleggeup.confagrid.model.Role;
import com.aleggeup.confagrid.model.User;
import com.aleggeup.confagrid.service.UserService;

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
            LOG.info("User Repository is Being Initialized.");
            initialize();
        } else {
            LOG.info("User Repository is Already Initialized.");
        }
    }

    private Set<Role> roles(final String... roleStrings) {
        final Set<Role> roles = new HashSet<>();

        for (final String roleName : roleStrings) {
            final Role role = new Role(roleName);
            roles.add(role);
        }

        return roles;
    }

    private void initialize() {
        userService.save(new User("admin", userService.encrypt("admin"), roles(ROLE_USER, ROLE_ADMIN)));
        userService.save(new User("user", userService.encrypt("password"), roles(ROLE_USER)));
    }
}

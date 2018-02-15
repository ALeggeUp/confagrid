/*
 * DefaultAuthenticationService.java
 *
 * Copyright (C) 2017-2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aleggeup.confagrid.filter.JwtFilter;
import com.aleggeup.confagrid.model.User;
import com.aleggeup.confagrid.service.AuthenticationService;
import com.aleggeup.confagrid.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class DefaultAuthenticationService implements AuthenticationService {

    private static final long TOKEN_INTERVAL = 1000L * 60 * 60 * 24;

    private final UserService userService;

    @Autowired
    public DefaultAuthenticationService(final UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean userPasswordCheck(final String username, final String password) {
        final User user = userService.getById(username);

        return user != null && userService.matches(password, user.getPassword());
    }

    @Override
    public String authenticationToken(final String username, final String password) {
        final User user = userService.getById(username);

        return Jwts.builder().setSubject(user.getName())
            .claim("roles", user.getRoles())
            .setIssuedAt(new Date())
            .setExpiration(new Date(new Date().getTime() + TOKEN_INTERVAL))
            .signWith(SignatureAlgorithm.HS256, JwtFilter.SECRET_KEY)
            .compact();
    }

    @Override
    public String anonymousToken() {
        return Jwts.builder().setSubject("anonymous")
            .setIssuedAt(new Date())
            .setExpiration(new Date(new Date().getTime() + TOKEN_INTERVAL))
            .signWith(SignatureAlgorithm.HS256, JwtFilter.SECRET_KEY)
            .compact();
    }
}

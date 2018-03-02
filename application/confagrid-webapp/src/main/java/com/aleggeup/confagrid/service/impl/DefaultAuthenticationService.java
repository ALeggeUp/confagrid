/*
 * DefaultAuthenticationService.java
 *
 * Copyright (C) 2017-2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.service.impl;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aleggeup.confagrid.filter.JwtFilter;
import com.aleggeup.confagrid.model.User;
import com.aleggeup.confagrid.service.AuthenticationService;
import com.aleggeup.confagrid.service.DateTimeService;
import com.aleggeup.confagrid.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class DefaultAuthenticationService implements AuthenticationService {

    private static final int TOKEN_INTERVAL_DAYS = 1;

    private final UserService userService;

    private final DateTimeService dateTimeService;

    @Autowired
    public DefaultAuthenticationService(final UserService userService, final DateTimeService dateTimeService) {
        this.userService = userService;
        this.dateTimeService = dateTimeService;
    }

    @Override
    public boolean userPasswordCheck(final String username, final String password) {
        final User user = userService.findByName(username);

        return user != null && userService.matches(password, user.getPassword());
    }

    @Override
    public String authenticationToken(final String username, final String password) {
        final User user = userService.findByName(username);
        final DateTime now = dateTimeService.now();

        return Jwts.builder()
            .setSubject(user.getName())
            .setId(user.getId().toString())
            .claim("roles", user.getRoles())
            .setIssuedAt(now.toLocalDateTime().toDate())
            .setExpiration(dateTimeService.plusDaysAsDate(now, TOKEN_INTERVAL_DAYS))
            .signWith(SignatureAlgorithm.HS256, JwtFilter.SECRET_KEY)
            .compact();
    }

    @Override
    public String anonymousToken() {
        final DateTime now = dateTimeService.now();

        return Jwts.builder()
            .setSubject("anonymous")
            .setId("b5a2918e-bd3f-48f5-8437-daf40fd3f323")
            .setIssuedAt(now.toLocalDateTime().toDate())
            .setExpiration(dateTimeService.plusDaysAsDate(now, TOKEN_INTERVAL_DAYS))
            .signWith(SignatureAlgorithm.HS256, JwtFilter.SECRET_KEY)
            .compact();
    }
}

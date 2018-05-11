/*
 * DefaultAuthenticationService.java
 *
 * Copyright (C) 2017-2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.auth;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aleggeup.confagrid.time.DateTimeService;
import com.aleggeup.confagrid.user.UserEntity;
import com.aleggeup.confagrid.user.UserService;

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
        final UserEntity userEntity = userService.findByName(username);

        return userEntity != null && userService.matches(password, userEntity.getPassword());
    }

    @Override
    public String authenticationToken(final String username, final String password) {
        final UserEntity userEntity = userService.findByName(username);
        final DateTime now = dateTimeService.now();

        return Jwts.builder()
            .setSubject(userEntity.getName())
            .setId(userEntity.getId().toString())
            .claim("roles", userEntity.getRoleEntities())
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

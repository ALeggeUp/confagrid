/*
 * UserController.java
 *
 * Copyright (C) 2017-2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.user;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aleggeup.confagrid.auth.AuthenticationService;
import com.aleggeup.confagrid.auth.InvalidLoginException;
import com.aleggeup.confagrid.auth.JwtFilter;
import com.aleggeup.confagrid.model.mex.LoginRequest;
import com.aleggeup.confagrid.model.mex.LoginResponse;

import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private final AuthenticationService authenticationService;

    @Autowired
    UserController(final UserService userService, final AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public LoginResponse login(@NonNull @RequestBody final LoginRequest login) throws InvalidLoginException {

        if (login.getName() == null || !userService.containsName(login.getName())
            || login.getPassword() == null || login.getPassword().isEmpty()) {
            throw new InvalidLoginException();
        }

        if (!authenticationService.userPasswordCheck(login.getName(), login.getPassword())) {
            throw new InvalidLoginException();
        }

        final UserEntity userEntity = userService.findByName(login.getName());

        return new LoginResponse(userEntity.getId().toString(), userEntity.getName(),
            authenticationService.authenticationToken(login.getName(), login.getPassword()));
    }

    @RequestMapping(value = "check", method = RequestMethod.GET)
    public LoginResponse check(final HttpServletResponse response) {
        if (JwtFilter.SUBJECT_ANONYMOUS.equals(response.getHeader(JwtFilter.HEADER_CLAIMS_SUBJECT))) {
            return new LoginResponse(JwtFilter.ANONYMOUS_USER_ID, response.getHeader(JwtFilter.HEADER_CLAIMS_SUBJECT),
                authenticationService.anonymousToken());
        }

        return new LoginResponse(response.getHeader(JwtFilter.HEADER_CLAIMS_USER_ID),
            response.getHeader(JwtFilter.HEADER_CLAIMS_SUBJECT),
            response.getHeader(JwtFilter.JWT_TOKEN));
    }

    @RequestMapping(value = "role/{role}", method = RequestMethod.GET)
    public Boolean claimContainsRole(@PathVariable final String role,
        final HttpServletRequest request) {

        final Claims claims = (Claims) request.getAttribute(JwtFilter.ATTRIBUTE_CLAIMS);

        if (claims == null || claims.isEmpty()) {
            return false;
        }

        return getRoles(claims).stream().anyMatch(map -> map.containsValue(role));
    }

    @SuppressWarnings("unchecked")
    private List<LinkedHashMap<String, String>> getRoles(final Claims claims) {
        final Object roles = claims.get("roles");
        if (roles != null) {
            return (List<LinkedHashMap<String, String>>) roles;
        } else {
            return Collections.EMPTY_LIST;
        }
    }
}

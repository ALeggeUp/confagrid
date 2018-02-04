/*
 * UserController.java
 *
 * Copyright (C) 2017 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.controller;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aleggeup.confagrid.controller.exception.InvalidLoginException;
import com.aleggeup.confagrid.filter.JwtFilter;
import com.aleggeup.confagrid.model.User;
import com.aleggeup.confagrid.model.mex.LoginRequest;
import com.aleggeup.confagrid.model.mex.LoginResponse;
import com.aleggeup.confagrid.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public LoginResponse login(@RequestBody final LoginRequest login) throws InvalidLoginException {

        if (login == null || login.getName() == null || !userService.containsKey(login.getName()) ||
                login.getPassword() == null || login.getPassword().isEmpty()) {
            throw new InvalidLoginException();
        }

        final User user = userService.getById(login.getName());
        if (!userService.matches(login.getPassword(), user.getPassword())) {
            throw new InvalidLoginException();
        }

        return new LoginResponse(Jwts.builder().setSubject(login.getName())
            .claim("roles", user.getRoles())
            .setIssuedAt(new Date())
            .signWith(SignatureAlgorithm.HS256, JwtFilter.SECRET_KEY)
            .compact());
    }

    @RequestMapping(value = "role/{role}", method = RequestMethod.GET)
    public Boolean claimContainsRole(@PathVariable final String role,
            final HttpServletRequest request) throws ServletException {

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
            return (List<LinkedHashMap<String, String>>)roles;
        } else {
            return Collections.EMPTY_LIST;
        }
    }
}

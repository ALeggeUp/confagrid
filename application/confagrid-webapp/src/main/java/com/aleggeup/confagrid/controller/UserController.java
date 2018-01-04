/*
 * UserController.java
 *
 * Copyright (C) 2017 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aleggeup.confagrid.controller.exception.InvalidLoginException;
import com.aleggeup.confagrid.model.LoginResponse;
import com.aleggeup.confagrid.model.UserLogin;
import com.aleggeup.confagrid.repository.entity.User;
import com.aleggeup.confagrid.service.UserService;

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
    public LoginResponse login(@RequestBody final UserLogin login) throws InvalidLoginException {

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
            .signWith(SignatureAlgorithm.HS256, "secretkey")
            .compact());
    }
}

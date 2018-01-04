/*
 * ApiController.java
 *
 * Copyright (C) 2017 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.controller;

import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/api")
public class ApiController {

    @RequestMapping(value = "role/{role}", method = RequestMethod.GET)
    public Boolean login(@PathVariable final String role,
            final HttpServletRequest request) throws ServletException {
        final Claims claims = (Claims) request.getAttribute("claims");

        if (claims == null || claims.isEmpty()) {
            return false;
        }

        return getRoles(claims).stream().anyMatch(map -> map.containsValue(role));
    }
    
    @SuppressWarnings("unchecked")
    private List<LinkedHashMap<String, String>> getRoles(final Claims claims) {
        return (List<LinkedHashMap<String, String>>)claims.get("roles");
    }
}

/*
 * ApiController.java
 *
 * Copyright (C) 2017 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.controller;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.TransformerException;

import org.apache.batik.svggen.SVGGraphics2DIOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aleggeup.confagrid.filter.JwtFilter;
import com.aleggeup.confagrid.service.WordGridGenerationService;

import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final WordGridGenerationService wordGridGenerationService;

    @Autowired
    public ApiController(final WordGridGenerationService wordGridGenerationService) {
        this.wordGridGenerationService = wordGridGenerationService;
    }

    @RequestMapping(value = "role/{role}", method = RequestMethod.GET)
    public Boolean login(@PathVariable final String role,
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

    @RequestMapping(value = "test", method = RequestMethod.GET, produces = "image/svg+xml")
    public @ResponseBody String test() throws SVGGraphics2DIOException, UnsupportedEncodingException, TransformerException {
        return wordGridGenerationService.test();
    }
}
/*
 * RouteController.java
 *
 * Copyright (C) 2017-2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouteController {

    protected static final String DEFAULT_ROUTE = "forward:/index.html";

    @RequestMapping({
        "/login",
        "/create",
        "/gallery",
        "/intro",
        "/more"
    })
    public String router() {
        return DEFAULT_ROUTE;
    }
}

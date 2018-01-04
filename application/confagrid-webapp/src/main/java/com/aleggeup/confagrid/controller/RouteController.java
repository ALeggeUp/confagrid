/*
 * RouteController.java
 *
 * Copyright (C) 2017 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouteController {

    @RequestMapping({
        "/login",
        "/create",
        "/existing",
        "/intro",
        "/more"
    })
    public String router() {
        return "forward:/index.html";
    }
}

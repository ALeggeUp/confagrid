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

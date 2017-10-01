package com.aleggeup.confagrid.router;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouteController {

    @RequestMapping({
        "/create",
        "/existing",
        "/intro",
        "/more"
    })
    public String router() {
        return "forward:/index.html";
    }
}

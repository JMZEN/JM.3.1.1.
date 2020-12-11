package io.zenbydef.usertracker.ui.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PathController {
    @RequestMapping("/login")
    public String list() {
        return "pages/login/login-page";
    }

    @RequestMapping("/denied")
    public String accessDeniedPage() {
        return "pages/status/access-denied";
    }
}

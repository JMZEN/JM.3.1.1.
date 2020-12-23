package io.zenbydef.usertracker.ui.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserControllerView {
    @GetMapping(value = "login")
    public String loginPage() {
        return "login";
    }

    @GetMapping(value = "admin")
    public String mainAdminPage() {
        return "admin";
    }

    @GetMapping(value = "user")
    public String mainUserPage() {
        return "user";
    }
}
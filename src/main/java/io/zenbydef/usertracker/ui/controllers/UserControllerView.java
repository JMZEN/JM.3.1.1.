package io.zenbydef.usertracker.ui.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
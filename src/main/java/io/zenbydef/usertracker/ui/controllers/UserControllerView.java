package io.zenbydef.usertracker.ui.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserControllerView {
    @RequestMapping(value = "admin")
    public ModelAndView mainAdminPage() {
        return new ModelAndView("admin");
    }

    @RequestMapping(value = "user")
    public ModelAndView mainUserPage() {
        return new ModelAndView("user");
    }
}
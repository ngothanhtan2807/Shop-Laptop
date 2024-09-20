package com.devpro.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AdminLoginController {
    @GetMapping(value = {"/login"})
    public String login(final ModelMap model,
                        final HttpServletRequest request,
                        final HttpServletResponse response) {
        return "login";
    }
}

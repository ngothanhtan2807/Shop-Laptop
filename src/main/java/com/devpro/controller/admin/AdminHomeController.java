package com.devpro.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AdminHomeController {

    @GetMapping(value = {"/admin/home", "/admin"})
    public String index(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response) {
        return "admin/AdminHome";
    }
}

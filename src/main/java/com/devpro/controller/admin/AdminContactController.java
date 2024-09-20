package com.devpro.controller.admin;

import com.devpro.repositories.ContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AdminContactController {
    @Autowired
    ContactRepo contactRepo;

    @GetMapping("/admin/contact")
    public String listCategory(final ModelMap model, final HttpServletRequest request,
                               final HttpServletResponse response) {
        model.addAttribute("contacts", contactRepo.findAll());
        return "admin/contact/list-contact";
    }
}

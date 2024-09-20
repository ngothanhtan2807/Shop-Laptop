package com.devpro.controller.users;

import com.devpro.entities.User;
import com.devpro.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SignupController {

    @Autowired
    UserService userService;

    @GetMapping("/signup")
    public String index(final ModelMap model, final HttpServletRequest request,
                        final HttpServletResponse response)
            throws Exception {
        model.addAttribute("user", new User());
        return "users/UserSignUp";
    }

    @PostMapping("/save-guestUser")
    public String saveGuestUser(@ModelAttribute("user") User user, final ModelMap model,
                                final HttpServletRequest request,
                                final HttpServletResponse response) throws Exception {
        model.addAttribute("user", new User());
        userService.saveGuestUser(user);
        return "redirect:/login";
    }

}

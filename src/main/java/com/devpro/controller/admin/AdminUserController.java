package com.devpro.controller.admin;

import com.devpro.entities.AjaxResponse;
import com.devpro.entities.User;
import com.devpro.repositories.RoleRepo;
import com.devpro.repositories.UserRepo;
import com.devpro.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class AdminUserController {
    @Autowired
    public UserRepo userRepo;
    @Autowired
    public RoleRepo roleRepo;
    @Autowired
    UserService userService;

    @GetMapping("/admin/list-user")
    public String listUser(final ModelMap model, final HttpServletRequest request,
                           final HttpServletResponse response) throws Exception {
        model.addAttribute("users", userRepo.findAll());
        return "admin/user/list-user";
    }

    @GetMapping("/admin/add-user")
    public String addUser(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response) {
        model.addAttribute("role", roleRepo.findAll());
        model.addAttribute("user", new User());
        return "admin/user/add-user";
    }

    @GetMapping("/admin/edit-user/{id}")
    public String editUser(@PathVariable("id") int id, final ModelMap model, final HttpServletRequest request,
                           final HttpServletResponse response) throws Exception {
        model.addAttribute("role", roleRepo.findAll());
        model.addAttribute("user", userService.findUserById(id));
        return "admin/user/add-user";
    }

    @PostMapping("/admin/save-user")
    public String saveUser(@RequestParam("images") MultipartFile[] images, @ModelAttribute("user") User user,
                           final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
            throws Exception {
        userService.saveUser(images, user, request);

        return "redirect:/admin/list-user";
    }

    @PostMapping("/admin/list-user/delete-user-with-ajax/{id}")
    public ResponseEntity<AjaxResponse> subscribe(@ModelAttribute("user") User user,
                                                  @PathVariable("id") int id, final ModelMap model,
                                                  final HttpServletRequest request,
                                                  final HttpServletResponse response) {

        User users = userService.findUserById(id);
        users.setUpdatedDate(java.time.LocalDateTime.now());
        users.setStatus(false);
        userRepo.save(users);

        return ResponseEntity.ok(new AjaxResponse(200, "Success"));
    }
}

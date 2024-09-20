package com.devpro.controller.admin;

import com.devpro.entities.AjaxResponse;
import com.devpro.entities.Category;
import com.devpro.repositories.CategoryRepo;
import com.devpro.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AdminCategoryController {

    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    CategoryService categoryService;

    @GetMapping("/admin/list-category")
    public String listCategory(final ModelMap model,
                               final HttpServletRequest request,
                               final HttpServletResponse response) {
        model.addAttribute("categories", categoryRepo.findAll());
        return "admin/category/list-category";
    }

    @GetMapping("/admin/add-category")
    public String addCategory(final ModelMap model,
                              final HttpServletRequest request,
                              final HttpServletResponse response) {
        model.addAttribute("categories", categoryRepo.findAll());
        model.addAttribute("category", new Category());
        return "admin/category/add-category";
    }

    @GetMapping("/admin/edit-category/{seo}")
    public String editCategory(@PathVariable("seo") String seo,
                               final ModelMap model,
                               final HttpServletRequest request,
                               final HttpServletResponse response) {
        model.addAttribute("categories", categoryRepo.findAll());
        model.addAttribute("category", categoryService.findCategoryBySeo(seo));
        return "admin/category/add-category";
    }

    @PostMapping("/admin/save-category")
    public String saveCategory(@ModelAttribute("category") Category category,
                               final ModelMap model,
                               final HttpServletRequest request,
                               final HttpServletResponse response){
        categoryService.saveCategory(category);

        return "redirect:/admin/list-category";
    }

    @PostMapping("/admin/list-category/delete-category-with-ajax/{seo}")
    public ResponseEntity<AjaxResponse> subscribe(@ModelAttribute("category") Category category,
                                                  @RequestBody Category data,
                                                  @PathVariable("seo") String seo,
                                                  final ModelMap model,
                                                  final HttpServletRequest request,
                                                  final HttpServletResponse response) {

        Category categories = categoryService.findCategoryBySeo(seo);

        categories.setStatus(false);
        categoryRepo.save(categories);

        return ResponseEntity.ok(new AjaxResponse(401, data));
    }

}

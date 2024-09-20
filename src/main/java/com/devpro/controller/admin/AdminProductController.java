package com.devpro.controller.admin;

import com.devpro.common.ProductSearch;
import com.devpro.entities.AjaxResponse;
import com.devpro.entities.Product;
import com.devpro.repositories.CategoryRepo;
import com.devpro.repositories.ProductRepo;
import com.devpro.services.ProductService;
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
public class AdminProductController {

    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    ProductService productService;
    @Autowired
    ProductRepo productRepo;

    @GetMapping("/admin/list-product")
    public String listProduct(final ModelMap model, final HttpServletRequest request,
                              final HttpServletResponse response) {
        model.addAttribute("products", productRepo.findAll());
        return "admin/product/list-product";
    }

    @GetMapping("/admin/add-product")
    public String addProduct(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response) {
        model.addAttribute("categories", categoryRepo.findAll());
        model.addAttribute("product", new Product());
        return "admin/product/add-product";
    }

    @GetMapping("/admin/edit-product/{seo}")
    public String editProduct(@PathVariable("seo") String seo,
                              final ModelMap model,
                              final HttpServletRequest request,
                              final HttpServletResponse response) {

        model.addAttribute("categories", categoryRepo.findAll());
        ProductSearch productSearch = new ProductSearch();
        productSearch.setSeoProduct(seo);
        model.addAttribute("product", productService.search(productSearch).get(0));
        return "admin/product/add-product";
    }

    @PostMapping("/admin/save-product")
    public String saveProduct(@RequestParam("images") MultipartFile[] images,
                              @ModelAttribute("product") Product product,
                              final ModelMap model,
                              final HttpServletRequest request,
                              final HttpServletResponse response) throws Exception {

        productService.saveProduct(images, product, request);

        return "redirect:/admin/list-product";
    }

    @PostMapping("/admin/list-product/delete-product-with-ajax/{seo}")
    public ResponseEntity<AjaxResponse> subscribe(@PathVariable("seo") String seo,
                                                  final ModelMap model,
                                                  final HttpServletRequest request,
                                                  final HttpServletResponse response) {

        ProductSearch productSearch = new ProductSearch();
        productSearch.setSeoProduct(seo);
        Product products = productService.search(productSearch).get(0);

        products.setUpdatedDate(java.time.LocalDateTime.now());

        products.setPriceVN(products.getPriceVN());
        products.setStatus(false);
        productRepo.save(products);

        return ResponseEntity.ok(new AjaxResponse(200, "SUCCESS"));
    }
}
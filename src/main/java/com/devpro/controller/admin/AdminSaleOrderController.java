package com.devpro.controller.admin;

import com.devpro.entities.AjaxResponse;
import com.devpro.entities.SaleOrder;
import com.devpro.repositories.ProductRepo;
import com.devpro.repositories.SaleOrderProductsRepo;
import com.devpro.repositories.SaleOrderRepo;
import com.devpro.services.ProductService;
import com.devpro.services.SaleOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AdminSaleOrderController {
    @Autowired
    ProductService productService;
    @Autowired
    SaleOrderService saleOrderService;
    @Autowired
    public ProductRepo productRepo;
    @Autowired
    public SaleOrderRepo saleOrderRepo;
    @Autowired
    public SaleOrderProductsRepo saleOrderProductsRepo;

    @GetMapping("/admin/list-order")
    public String listOrder(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response) {
        model.addAttribute("saleOrders", saleOrderRepo.findAll());
        return "admin/sale-order/list-order";
    }

    @GetMapping("/admin/view-order/{id}")
    public String viewListProductOrder(final ModelMap model, @PathVariable("id") int id,
                                       final HttpServletRequest request, final HttpServletResponse response) {
        model.addAttribute("saleOrder", saleOrderService.findSaleOrderById(id));
        model.addAttribute("saleOrderProduct", saleOrderService.findOrderProductByOrderId(id));
        return "admin/sale-order/view-order";
    }

    @PostMapping("/admin/list-order/delete-saleOrder-with-ajax/{id}")
    public ResponseEntity<AjaxResponse> subscribe(@PathVariable("id") int id, final ModelMap model,
                                                  final HttpServletRequest request, final HttpServletResponse response) {

        SaleOrder saleOrders = saleOrderService.findSaleOrderById(id);

        saleOrders.setStatus(false);
        saleOrderRepo.save(saleOrders);

        return ResponseEntity.ok(new AjaxResponse(200, "SUCCESS"));
    }
}

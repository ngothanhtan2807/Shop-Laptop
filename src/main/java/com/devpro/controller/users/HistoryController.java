package com.devpro.controller.users;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.devpro.services.SaleOrderService;
import com.devpro.services.UserService;

@Controller
public class HistoryController extends BaseController {
    @Autowired
    SaleOrderService saleOrderService;
    @Autowired
    UserService userService;

    @GetMapping("/history/{id}")
    public String history(@PathVariable("id") String id, final ModelMap model, final HttpServletRequest request,
                          final HttpServletResponse response) {
        int userId = Integer.parseInt(id);

        model.addAttribute("saleOrders", saleOrderService.findSaleOrderByUserId(userId));
        return "users/history";
    }

    @GetMapping("/order/view/{code}")
    public String saleOrderProduct(@PathVariable("code") String code, final ModelMap model,
                                   final HttpServletRequest request,
                                   final HttpServletResponse response) {

        model.addAttribute("saleOrderProducts", saleOrderService.findSaleOrderProductByCode(code));
        model.addAttribute("saleOrders", saleOrderService.findSaleOrderByCode(code));
        return "users/orderView";
    }
}

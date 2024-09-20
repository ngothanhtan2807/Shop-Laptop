package com.devpro.controller.users;

import com.devpro.entities.AjaxResponse;
import com.devpro.entities.Cart;
import com.devpro.entities.CartItem;
import com.devpro.entities.Product;
import com.devpro.entities.SaleOrder;
import com.devpro.entities.SaleOrderProducts;
import com.devpro.entities.User;
import com.devpro.repositories.ProductRepo;
import com.devpro.services.SaleOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

@Controller
@RequiredArgsConstructor
public class CartController extends BaseController {

    private final SaleOrderService saleOrderService;

    @PostMapping("/cart/finish")
    public String finish(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
            throws Exception {
        HttpSession httpSession = request.getSession();
        String customerName = null;
        String customerAddress = null;
        String customerPhone = null;
        String customerEmail = null;

        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null) {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof org.springframework.security.core.userdetails.UserDetails) {
                customerPhone = ((User) principal).getPhone();
                customerName = ((User) principal).getName();
                customerAddress = ((User) principal).getAddress();
                customerEmail = ((User) principal).getEmail();

                model.addAttribute("customerName", ((User) principal).getName());
                model.addAttribute("customerAddress", ((User) principal).getAddress());
                model.addAttribute("customerPhone", ((User) principal).getPhone());
                model.addAttribute("customerEmail", ((User) principal).getEmail());
            } else {

                customerPhone = request.getParameter("customerPhone");
                customerAddress = request.getParameter("customerAddress");
                customerName = request.getParameter("customerName");
                customerEmail = request.getParameter("customerEmail");

                model.addAttribute("customerName", request.getParameter("customerName"));
                model.addAttribute("customerAddress", request.getParameter("customerAddress"));
                model.addAttribute("customerPhone", request.getParameter("customerPhone"));
                model.addAttribute("customerEmail", request.getParameter("customerEmail"));
            }
        }

        SaleOrder saleOrder = new SaleOrder();
        Cart cart = (Cart) httpSession.getAttribute("GIO_HANG");
        List<CartItem> cartItems = cart.getCartItems();

        BigDecimal sum = new BigDecimal(0);
        String sumVN = null;
        for (CartItem item : cartItems) {
            SaleOrderProducts saleOrderProducts = new SaleOrderProducts();
            saleOrderProducts.setProduct(productRepo.getOne(item.getProductId()));
            saleOrderProducts.setQuantity(item.getQuantity());
            saleOrder.addSaleOrderProducts(saleOrderProducts);
            for (int i = 1; i <= item.getQuantity(); i++) {
                sum = sum.add(saleOrderProducts.getProduct().getPrice());
            }
            Locale locale = new Locale("vi", "VN");
            NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
            sumVN = fmt.format(sum);
        }
        model.addAttribute("quantityCart", cartItems.size());
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("sumVN", sumVN);
        saleOrderService.saveOrderProduct(customerAddress, customerName, customerPhone, customerEmail, httpSession);
        return "/users/finish";
    }

    @GetMapping("/cart/check-out")
    public String index(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
            throws IOException {
        return "/users/checkout";
    }

    @PostMapping("/cart/check-out/delete-product-cart-with-ajax/{productId}")
    public ResponseEntity<AjaxResponse> subscribe(@RequestBody CartItem data, @PathVariable("productId") int productId,
                                                  final ModelMap model, final HttpServletRequest request, final HttpServletResponse response) {

        HttpSession httpSession = request.getSession();
        Cart cart;
        if (httpSession.getAttribute("GIO_HANG") != null) {
            cart = (Cart) httpSession.getAttribute("GIO_HANG");
        } else {
            cart = new Cart();
            httpSession.setAttribute("GIO_HANG", cart);
        }

        List<CartItem> cartItems = cart.getCartItems();

        for (int a = 0; a < cartItems.size(); a++) {
            if (cartItems.get(a).getProductId() == productId) {
                cartItems.remove(a);
            }
        }

        return ResponseEntity.ok(new AjaxResponse(200, "Success"));
    }

    @PostMapping("/cart/mua-hang")
    public ResponseEntity<AjaxResponse> muaHang(@RequestBody CartItem data, final ModelMap model,
                                                final HttpServletRequest request, final HttpServletResponse response) {
        HttpSession httpSession = request.getSession();

        Cart cart;

        if (httpSession.getAttribute("GIO_HANG") != null) {
            cart = (Cart) httpSession.getAttribute("GIO_HANG");
        } else {
            cart = new Cart();
            httpSession.setAttribute("GIO_HANG", cart);
        }

        List<CartItem> cartItems = cart.getCartItems();
        boolean isExists = false;
        int quantity = 0;
        for (CartItem item : cartItems) {
            if (item.getProductId() == data.getProductId()) {
                isExists = true;
                item.setQuantity(item.getQuantity() + data.getQuantity());
            }
        }
        if (!isExists) {

            Product product = productRepo.getOne(data.getProductId());
            data.setProductName(product.getTitle());
            data.setUnitPrice(product.getPrice());
            cart.getCartItems().add(data);
        }
        for (CartItem item : cartItems) {
            quantity += item.getQuantity();
        }

        httpSession.setAttribute("SL_SP_GIO_HANG", quantity);

        return ResponseEntity.ok(new AjaxResponse(200, String.valueOf(quantity)));
    }

}

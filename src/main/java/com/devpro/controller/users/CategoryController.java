package com.devpro.controller.users;

import com.devpro.common.ProductSearch;
import com.devpro.entities.Product;
import com.devpro.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CategoryController extends BaseController {

    @Autowired
    ProductService productService;

    @GetMapping("/product/category/{seo}")
    public String getFootBySimplePathWithPathVariable(@PathVariable("seo") String seo, final ModelMap model,
                                                      final HttpServletRequest request, final HttpServletResponse response) {

        ProductSearch productSearch = new ProductSearch();
        productSearch.setSeoCategory(seo);

        List<Product> product = productService.search(productSearch);
        int numberOfPage = product.size() / productSearch.SIZE_ITEMS_ON_PAGE + 1;
        ArrayList numberOP = new ArrayList();
        for (int i = 1; i < numberOfPage + 1; i++) {
            numberOP.add(i);
        }
        productSearch.parseRequest(request);

        model.addAttribute("numberOP", numberOP);
        model.addAttribute("numberOfPage", numberOfPage);
        // lấy sản phẩm từ category.
        model.addAttribute("products", productService.search(productSearch));

        return "users/UserHome";
    }

}
package com.devpro.controller.users;

import com.devpro.common.ProductSearch;
import com.devpro.entities.Product;
import com.devpro.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController extends BaseController {
    @Autowired
    ProductService productService;

    @GetMapping(value = {"/home", "/index", "/"})
    public String index(final ModelMap model, @Param("keyword") String keyword, final HttpServletRequest request,

                        final HttpServletResponse response) throws Exception {

        ProductSearch productSearch = new ProductSearch();
        String strPage = request.getParameter("page");
        String price = request.getParameter("price");
        String sort = request.getParameter("sort");
        keyword = request.getParameter("keyword");
        productSearch.setStatusProduct(1);
        if (sort != null) {
            productSearch.setSort(sort);
        }
        if (price != null) {
            productSearch.setTypePrice(price);
        }
        if (keyword != null) {
            productSearch.setKeyword(keyword);
        }
        List<Product> product = productService.search(productSearch);

        int numberOfPage = product.size() / productSearch.SIZE_ITEMS_ON_PAGE + 1;

        ArrayList numberOP = new ArrayList();
        for (int i = 1; i < numberOfPage + 1; i++) {
            numberOP.add(i);
        }

        productSearch.parseRequest(request);
        model.addAttribute("numberOP", numberOP);
        model.addAttribute("numberOfPage", numberOfPage);
        model.addAttribute("products", productService.search(productSearch));
        return "users/UserHome";
    }
}

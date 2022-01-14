package com.m2ilyon.mongoshop.controller;

import com.m2ilyon.mongoshop.dto.CartItem;
import com.m2ilyon.mongoshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProductController {

    private final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/product/{slug}", method = RequestMethod.GET)
    public String getAllProducts(Model model, @PathVariable String slug) {
        model.addAttribute("product", productService.getProductBySlug(slug));
        model.addAttribute("cartItem", new CartItem());
        return "products/product";
    }
}

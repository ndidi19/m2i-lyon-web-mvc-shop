package com.m2ilyon.mongoshop.controller;

import com.m2ilyon.mongoshop.dto.CreateProductDto;
import com.m2ilyon.mongoshop.dto.UpdateProductDto;
import com.m2ilyon.mongoshop.model.Product;
import com.m2ilyon.mongoshop.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    private final ProductService productService;

    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String getAllProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products/products";
    }

    @GetMapping("/products/new")
    public String createProductForm(Model model) {
        model.addAttribute("createProductDto", new CreateProductDto());
        return "products/create_product";
    }

    @PostMapping("/products")
    public String createProduct(@ModelAttribute("createProductDto") CreateProductDto productDto) {
        productService.createProduct(productDto);
        return "redirect:/products";
    }

    @GetMapping("/products/edit/{id}")
    public String updateProductForm(@PathVariable String id, Model model) {
        Product product = productService.getProductById(id);
        log.info("Retrieved product with id : " + id);
        UpdateProductDto updateProductDto = new UpdateProductDto(product.getId(), product.getDescription(),
                product.getUnitPriceWithoutTax(), product.getVat());
        log.info("Send product info to form");
        model.addAttribute("updateProductDto", updateProductDto);
        return "products/edit_product";
    }

    @PostMapping("/products/edit/{id}")
    public String updateProduct(@PathVariable String id, @ModelAttribute("updateProductDto") UpdateProductDto updateProductDto) {
        productService.updateProduct(id, updateProductDto);
        return "redirect:/products";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable String id) {
        productService.deleteProductById(id);
        return "redirect:/products";
    }
}

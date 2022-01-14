package com.m2ilyon.mongoshop.service;

import com.m2ilyon.mongoshop.dto.CreateProductDto;
import com.m2ilyon.mongoshop.dto.UpdateProductDto;
import com.m2ilyon.mongoshop.model.Product;

import java.util.List;

public interface ProductService {

    public List<Product> getAllProducts();

    public Product createProduct(CreateProductDto productDto);

    public Product getProductById(String id);

    public Product updateProduct(String id, UpdateProductDto updateProductDto);

    void deleteProductById(String id);

    public Product getProductBySlug(String slug);
}

package com.m2ilyon.mongoshop.service.impl;

import com.m2ilyon.mongoshop.dto.CreateProductDto;
import com.m2ilyon.mongoshop.dto.UpdateProductDto;
import com.m2ilyon.mongoshop.model.Product;
import com.m2ilyon.mongoshop.repository.ProductRepository;
import com.m2ilyon.mongoshop.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(CreateProductDto productDto) {
        Product product = new Product(
                productDto.getName(),
                productDto.getDescription(),
                productDto.getUnitPriceWithoutTax(),
                productDto.getVat()
        );
        product.setSlug(product.getName().trim()
                .replaceAll(" ", "-")
                .replaceAll("\'","")
                .toLowerCase()
        );
        product.setUnitPriceWithTax(calculatePriceWithTax(product));
        return productRepository.insert(product);
    }

    @Override
    public Product getProductById(String id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Product updateProduct(String id, UpdateProductDto updateProductDto) {
        Product retrievedProduct = productRepository.findById(id).get();
        retrievedProduct.setDescription(updateProductDto.getDescription());
        retrievedProduct.setUnitPriceWithoutTax(updateProductDto.getUnitPriceWithoutTax());
        retrievedProduct.setVat(updateProductDto.getVat());
        retrievedProduct.setUnitPriceWithTax(calculatePriceWithTax(retrievedProduct));
        return productRepository.save(retrievedProduct);
    }

    @Override
    public void deleteProductById(String id) {
        Product retrievedProduct = productRepository.findById(id).get();
        productRepository.delete(retrievedProduct);
    }

    @Override
    public Product getProductBySlug(String slug) {
        return productRepository.findBySlug(slug);
    }

    private BigDecimal calculatePriceWithTax(Product product) {
        BigDecimal taxAmount = product.getUnitPriceWithoutTax()
                .multiply(product.getVat())
                .divide(BigDecimal.valueOf(100));
        return product.getUnitPriceWithoutTax().add(taxAmount);
    }

}

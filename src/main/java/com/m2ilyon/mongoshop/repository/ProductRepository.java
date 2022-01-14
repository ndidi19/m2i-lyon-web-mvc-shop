package com.m2ilyon.mongoshop.repository;

import com.m2ilyon.mongoshop.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {

    Product findBySlug(String slug);
}

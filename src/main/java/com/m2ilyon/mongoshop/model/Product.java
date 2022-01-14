package com.m2ilyon.mongoshop.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Document
public class Product {

    @Id
    private String id;
    private String slug;
    private String name;
    private String description;
    private BigDecimal unitPriceWithoutTax;
    private BigDecimal vat;
    private BigDecimal unitPriceWithTax;

    public Product(String name, String description, BigDecimal unitPriceWithoutTax, BigDecimal vat) {
        this.name = name;
        this.description = description;
        this.unitPriceWithoutTax = unitPriceWithoutTax;
        this.vat = vat;
    }

    public Product(String slug, String name, String description, BigDecimal unitPriceWithoutTax, BigDecimal vat, BigDecimal unitPriceWithTax) {
        this.slug = slug;
        this.name = name;
        this.description = description;
        this.unitPriceWithoutTax = unitPriceWithoutTax;
        this.vat = vat;
        this.unitPriceWithTax = unitPriceWithTax;
    }
}

package com.m2ilyon.mongoshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductDto {

    private String name;
    private String description;
    private BigDecimal unitPriceWithoutTax;
    private BigDecimal vat;
}

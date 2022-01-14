package com.m2ilyon.mongoshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductDto {

    private String id;
    private String description;
    private BigDecimal unitPriceWithoutTax;
    private BigDecimal vat;
}

package com.m2ilyon.mongoshop.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document
public class Role {

    @Id
    private String id;
    private String role;

    public Role(String role) {
        this.role = role;
    }
}

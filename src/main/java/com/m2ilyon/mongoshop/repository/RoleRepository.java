package com.m2ilyon.mongoshop.repository;

import com.m2ilyon.mongoshop.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {

    Role findByRole(String role);
}

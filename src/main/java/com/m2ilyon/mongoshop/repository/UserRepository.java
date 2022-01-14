package com.m2ilyon.mongoshop.repository;

import com.m2ilyon.mongoshop.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);
}

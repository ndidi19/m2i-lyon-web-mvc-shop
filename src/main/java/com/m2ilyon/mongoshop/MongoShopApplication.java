package com.m2ilyon.mongoshop;

import com.m2ilyon.mongoshop.dto.CreateProductDto;
import com.m2ilyon.mongoshop.model.Role;
import com.m2ilyon.mongoshop.model.User;
import com.m2ilyon.mongoshop.repository.ProductRepository;
import com.m2ilyon.mongoshop.repository.RoleRepository;
import com.m2ilyon.mongoshop.repository.UserRepository;
import com.m2ilyon.mongoshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;

@SpringBootApplication
public class MongoShopApplication implements CommandLineRunner {

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	private final ProductRepository productRepository;

	private final RoleRepository roleRepository;

	private final UserRepository userRepository;

	private final ProductService productService;

	public MongoShopApplication(ProductRepository productRepository, RoleRepository roleRepository,
								UserRepository userRepository, ProductService productService) {
		this.productRepository = productRepository;
		this.roleRepository = roleRepository;
		this.userRepository = userRepository;
		this.productService = productService;
	}

	public static void main(String[] args) {
		SpringApplication.run(MongoShopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		CreateProductDto product1 = new CreateProductDto("Jordan 5", "Brand new Jordan 5", new BigDecimal("99.00"),
				new BigDecimal("20.00")
		);
		CreateProductDto product2 = new CreateProductDto("SUPRA Skytop", "Supra Skytop 2022", new BigDecimal("79.00"),
				new BigDecimal("19.60")
		);
		CreateProductDto product3 = new CreateProductDto("Levi's 501", "Levi's 501 grey", new BigDecimal("129.00"),
				new BigDecimal("20.00")
		);
		userRepository.deleteAll();
		roleRepository.deleteAll();
		Role role1 = new Role("ROLE_ADMIN");
		Role role2 = new Role("ROLE_USER");
		roleRepository.save(role1);
		roleRepository.save(role2);
		User u = new User();
		u.setFirstname("John");
		u.setLastname("Doe");
		u.setEmail("john.doe@gmail.com");
		u.setPassword(passwordEncoder.encode("password"));
		Role roleUser = roleRepository.findByRole("ROLE_USER");
		u.setRoles(new HashSet<>(Arrays.asList(roleUser)));
		User u2 = new User();
		u2.setFirstname("All");
		u2.setLastname("Smith");
		u2.setEmail("allan.smith@gmail.com");
		u2.setPassword(passwordEncoder.encode("admin"));
		Role roleAdmin = roleRepository.findByRole("ROLE_ADMIN");
		u2.setRoles(new HashSet<>(Arrays.asList(roleAdmin)));
		userRepository.save(u);
		userRepository.save(u2);
		productRepository.deleteAll();
		productService.createProduct(product1);
		productService.createProduct(product2);
		productService.createProduct(product3);
	}
}

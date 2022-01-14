package com.m2ilyon.mongoshop.service.impl;

import com.m2ilyon.mongoshop.dto.CreateUserDto;
import com.m2ilyon.mongoshop.exception.UserAlreadyExistsException;
import com.m2ilyon.mongoshop.model.Role;
import com.m2ilyon.mongoshop.model.User;
import com.m2ilyon.mongoshop.repository.RoleRepository;
import com.m2ilyon.mongoshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public User saveUser(CreateUserDto userDto) {
        User retrievedUser = userRepository.findByEmail(userDto.getEmail());
        if (retrievedUser != null)
            throw new UserAlreadyExistsException("User already exists for given email " + userDto.getEmail(), userDto);
        User u = new User();
        u.setFirstname(userDto.getFirstname());
        u.setLastname(userDto.getLastname());
        u.setEmail(userDto.getEmail());
        u.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepository.findByRole("ROLE_USER");
        u.setRoles(new HashSet<>(Arrays.asList(role)));
        return userRepository.save(u);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User u = userRepository.findByEmail(email);
        if (u == null) {
            throw new UsernameNotFoundException("Invalid email and/or password");
        }
        List<GrantedAuthority> authorities = getUserAuthorities(u.getRoles());
        return buildUserForAuthentication(u, authorities);
    }

    private UserDetails buildUserForAuthentication(User u, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(u.getEmail(), u.getPassword(), authorities);
    }

    private List<GrantedAuthority> getUserAuthorities(Set<Role> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        userRoles.forEach(
                (role) -> roles.add(new SimpleGrantedAuthority(role.getRole()))
        );
        return new ArrayList<>(roles);
    }
}

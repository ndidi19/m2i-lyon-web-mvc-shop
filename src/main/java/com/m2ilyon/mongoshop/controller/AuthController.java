package com.m2ilyon.mongoshop.controller;

import com.m2ilyon.mongoshop.dto.CreateUserDto;
import com.m2ilyon.mongoshop.exception.UserAlreadyExistsException;
import com.m2ilyon.mongoshop.service.impl.UserDetailsServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthController {

    private final UserDetailsServiceImpl userDetailsService;

    public AuthController(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login/login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        CreateUserDto createUserDto = new CreateUserDto();
        model.addAttribute("createUserDto", createUserDto);
        return "register/register";
    }

    @PostMapping("/register")
    public String createUser(Model model, @ModelAttribute("createUserDto") CreateUserDto createUserDto) {
        userDetailsService.saveUser(createUserDto);
        return "redirect:/register?success";
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ModelAndView handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("createUserDto", ex.getUserDto());
        modelAndView.addObject("error", ex.getMessage());

        modelAndView.setViewName("register/register");
        return modelAndView;
    }
}

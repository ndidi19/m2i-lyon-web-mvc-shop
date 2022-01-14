package com.m2ilyon.mongoshop.controller;

import com.m2ilyon.mongoshop.dto.Cart;
import com.m2ilyon.mongoshop.dto.CartItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("shoppingCart")
public class CartController {

    @PostMapping("/cart/add/{id}")
    public String addProductToCart(@ModelAttribute("cartItem") CartItem cartItem,
                                   @ModelAttribute("shoppingCart") Cart cart, @PathVariable String id) {
        cartItem.setProductId(id);
        cart.add(cartItem);
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String showCart(@ModelAttribute("shoppingCart") Cart cart, Model model) {
        model.addAttribute("cart", cart);
        return "cart/cart";
    }

    @ModelAttribute("shoppingCart")
    public Cart shoppingCart() {
        return new Cart();
    }

}

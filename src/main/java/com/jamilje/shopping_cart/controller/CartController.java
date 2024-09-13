package com.jamilje.shopping_cart.controller;

import com.jamilje.shopping_cart.entity.Cart;
import com.jamilje.shopping_cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> findCartById(@PathVariable Long cartId) {
        return new ResponseEntity<>(cartService.findCartById(cartId), HttpStatus.OK);
    }

    @PutMapping("add/{cartId}")
    public ResponseEntity<Cart> addItemToCart(@PathVariable Long cartId,
                                              @RequestParam Long itemId ,
                                              @RequestParam Integer quantity) {
        Cart cart = cartService.addItemToCart(cartId, itemId, quantity);
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    @DeleteMapping("delete/{cartId}")
    public ResponseEntity<Cart> deleteItemFromCart(@PathVariable Long cartId, @RequestParam Long itemId) {
        Cart cart = cartService.deleteItemFromCart(cartId, itemId);
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    @DeleteMapping("deleteAll/{cartId}")
    public ResponseEntity<Cart> deleteAllItemsFromCart(@PathVariable Long cartId) {
        Cart cart = cartService.deleteAllItemsFromCart(cartId);
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }
}

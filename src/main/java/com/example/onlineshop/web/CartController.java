package com.example.onlineshop.web;

import com.example.onlineshop.domain.Cart;
import com.example.onlineshop.service.CartService;
import com.example.onlineshop.transfer.cart.AddProductsToCartRequest;
import com.example.onlineshop.transfer.cart.CartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> addProductsToCart(@PathVariable long userId, @Valid @RequestBody AddProductsToCartRequest request) {
        cartService.addProductsToCart(userId, request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<CartResponse> getCart(@PathVariable long userId) {
        CartResponse cart = cartService.getCart(userId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
}

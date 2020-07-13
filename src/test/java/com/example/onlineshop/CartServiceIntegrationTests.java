package com.example.onlineshop;

import com.example.onlineshop.service.CartService;
import com.example.onlineshop.steps.UserTestSteps;
import com.example.onlineshop.transfer.cart.AddProductsToCartRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CartServiceIntegrationTests {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserTestSteps userTestSteps;


    public void addProductsToCart_whenNewUser_thenCreateCartForUser() {



        AddProductsToCartRequest request = new AddProductsToCartRequest();
        //add product ids

        cartService.addProductsToCart(, request);
    }
}

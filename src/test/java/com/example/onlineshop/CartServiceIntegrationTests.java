package com.example.onlineshop;

import com.example.onlineshop.domain.Product;
import com.example.onlineshop.domain.User;
import com.example.onlineshop.service.CartService;
import com.example.onlineshop.steps.ProductTestSteps;
import com.example.onlineshop.steps.UserTestSteps;
import com.example.onlineshop.transfer.cart.AddProductsToCartRequest;
import com.example.onlineshop.transfer.cart.CartResponse;
import com.example.onlineshop.transfer.product.ProductResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
public class CartServiceIntegrationTests {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserTestSteps userTestSteps;

    @Autowired
    private ProductTestSteps productTestSteps;

    @Test
    public void addProductsToCart_whenNewUser_thenCreateCartForUser() {
        User user = userTestSteps.createUser();

        ProductResponse product = productTestSteps.createProduct();
        AddProductsToCartRequest request = new AddProductsToCartRequest();
        request.setProductIds(Collections.singletonList(product.getId()));

        cartService.addProductsToCart(user.getId(), request);

        CartResponse cartResponse = cartService.getCart(user.getId());

        assertThat(cartResponse, notNullValue());
        assertThat(cartResponse.getId(), is(user.getId()));
        assertThat(cartResponse.getProducts(), notNullValue());
        assertThat(cartResponse.getProducts(), hasSize(1));
        assertThat(cartResponse.getProducts().get(0), notNullValue());
        assertThat(cartResponse.getProducts().get(0).getId(), is(product.getId()));
        assertThat(cartResponse.getProducts().get(0).getName(), is(product.getName()));
        assertThat(cartResponse.getProducts().get(0).getPrice(), is(product.getPrice()));
        assertThat(cartResponse.getProducts().get(0).getImageUrl(), is(product.getImageUrl()));
    }
}

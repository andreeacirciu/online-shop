package com.example.onlineshop.unittests;


import com.example.onlineshop.domain.Cart;
import com.example.onlineshop.domain.Product;
import com.example.onlineshop.domain.User;
import com.example.onlineshop.domain.UserRole;
import com.example.onlineshop.persistence.CartRepository;
import com.example.onlineshop.service.CartService;
import com.example.onlineshop.service.ProductService;
import com.example.onlineshop.service.UserService;
import com.example.onlineshop.transfer.cart.AddProductsToCartRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CartServiceUnitTests {

    private CartService cartService;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private UserService userService;

    @Mock
    private ProductService productService;

    @BeforeEach
    public void setup() {
        cartService = new CartService(cartRepository, userService, productService);
    }

    @Test
    public void addProductsToCart_whenNewUser_thenNoErrorsIsThrown(){


        when(cartRepository.findById(anyLong())).thenReturn(Optional.empty());

        User user = new User();
        user.setId(1);
        user.setRole(UserRole.CUSTOMER.name());
        user.setFirstName("Test First Name");
        user.setLastName("Test Last Name");

        when(userService.getUser(anyLong())).thenReturn(user);

        Product product = new Product();
        product.setId(2);

        when(productService.getProduct(anyLong())).thenReturn(product);

        when(cartRepository.save(any(Cart.class))).thenReturn(null);


        AddProductsToCartRequest request = new AddProductsToCartRequest();
        request.setProductIds(Collections.singletonList(product.getId()));

        cartService.addProductsToCart(user.getId(), request);

        verify(cartRepository).findById(anyLong());
        verify(userService).getUser(anyLong());
        verify(productService).getProduct(anyLong());
        verify(cartRepository).save(any(Cart.class));
    }
}

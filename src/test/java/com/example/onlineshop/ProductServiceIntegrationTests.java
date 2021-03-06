package com.example.onlineshop;

import com.example.onlineshop.domain.Product;
import com.example.onlineshop.exception.ResourceNotFoundException;
import com.example.onlineshop.service.ProductService;
import com.example.onlineshop.steps.ProductTestSteps;
import com.example.onlineshop.transfer.product.GetProductRequest;
import com.example.onlineshop.transfer.product.ProductResponse;
import com.example.onlineshop.transfer.product.SaveProductRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.validation.ConstraintViolationException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@SpringBootTest
class ProductServiceIntegrationTests {

    //field injection
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductTestSteps productTestSteps;

    @Test
    void createProduct_whenValidRequest_ThenReturnCreatedProduct() {

        productTestSteps.createProduct();
    }

    @Test
    void getProduct_whenExistingProduct_thenReturnProduct() {

        ProductResponse product = productTestSteps.createProduct();

        Product response = productService.getProduct(product.getId());

        assertThat(response, notNullValue());
        assertThat(response.getId(), is(product.getId()));
        assertThat(response.getName(), is(product.getName()));
        assertThat(response.getPrice(), is(product.getPrice()));
        assertThat(response.getQuantity(), is(product.getQuantity()));
        assertThat(response.getDescription(), is(product.getDescription()));
        assertThat(response.getImageUrl(), is(product.getImageUrl()));
    }


    @Test
    void getProduct_whenNonExistingProduct_thenThrowResourceNotFoundException() {
        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> productService.getProduct(0));
    }

    @Test
    void updateProduct_whenValidRequest_thenReturnUpdatedProduct() {
        ProductResponse product = productTestSteps.createProduct();

        SaveProductRequest request = new SaveProductRequest();
        request.setName(product.getName() + "Updated");
        request.setPrice(product.getPrice() + 10);
        request.setQuantity(product.getQuantity() + 10);

        ProductResponse updatedProduct = productService.updateProduct(product.getId(), request);

        assertThat(updatedProduct, notNullValue());
        assertThat(updatedProduct.getId(), is(product.getId()));
        assertThat(updatedProduct.getName(), is(request.getName()));
        assertThat(updatedProduct.getPrice(), is(request.getPrice()));
        assertThat(updatedProduct.getQuantity(), is(request.getQuantity()));
    }

    @Test
    void deleteProduct_whenExistingProduct_thenProductDoesNotExistAnymore() {
        ProductResponse product = productTestSteps.createProduct();

        productService.deleteProduct(product.getId());

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> productService.getProduct(product.getId()));
    }


    @Test
    void createProduct_whenMissingMandatoryProperties_thenThrowException() {
        SaveProductRequest request = new SaveProductRequest();

        try {
            productService.createProduct(request);
        } catch (Exception e) {
            assertThat("Unexpected exception thrown", e instanceof ConstraintViolationException);
        }
    }

    @Test
    void getProducts_whenOneExistingProduct_thenReturnPageOfOneProduct() {
        ProductResponse product = productTestSteps.createProduct();
        Page<ProductResponse> productsPage = productService.getProducts(new GetProductRequest(), PageRequest.of(0, 1000));

        assertThat(productsPage, notNullValue());
        assertThat(productsPage.getTotalElements(), greaterThanOrEqualTo(1L));
        assertThat(productsPage.getContent().get(0).getId(), is(product.getId()));
    }

}

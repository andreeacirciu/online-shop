package com.example.onlineshop.service;

import com.example.onlineshop.domain.Product;
import com.example.onlineshop.persistence.ProductRepository;
import com.example.onlineshop.transfer.SaveProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //spring bean - obiectele carora li s-a cedat controlul si su ajuns in Io Container
public class ProductService {

    //IoC (Inversion of Control)
    private final ProductRepository productRepository; //nu am facut instantiere aici, am inversat controlul

    //Dependency Injection
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(SaveProductRequest request) {
        // todo: replace with logger
        System.out.println("Creating product: " + request);

        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setImageUrl(request.getImageUrl());

       return productRepository.save(product);
    }
}

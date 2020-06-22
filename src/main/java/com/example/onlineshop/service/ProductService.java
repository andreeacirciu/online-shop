package com.example.onlineshop.service;

import com.example.onlineshop.domain.Product;
import com.example.onlineshop.exception.ResourceNotFoundException;
import com.example.onlineshop.persistence.ProductRepository;
import com.example.onlineshop.transfer.SaveProductRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service //spring bean - obiectele carora li s-a cedat controlul si su ajuns in Io Container
public class ProductService {

    private static final Logger LOOGER = LoggerFactory.getLogger(ProductService.class);

    //IoC (Inversion of Control)
    private final ProductRepository productRepository; //nu am facut instantiere aici, am inversat controlul

    //Dependency Injection
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(SaveProductRequest request) {

        LOOGER.info("Creating product {}", request);

        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setImageUrl(request.getImageUrl());

       return productRepository.save(product);
    }

    public Product getProduct(long id){
        LOOGER.info("Retrieving product {}", id);

//        Optional<Product> productOptional = productRepository.findById(id);
//
//        if(productOptional.isPresent()){
//            return productOptional.get();
//        }else{
//            throw new ResourceNotFoundException("Product " + id + " not found.");
//        }

        //egal cu ce e mai sus
        return productRepository.findById(id)
                //lambda expression
                .orElseThrow( () -> new ResourceNotFoundException("Product " + id + " not found."));
    }
}

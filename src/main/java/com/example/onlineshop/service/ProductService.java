package com.example.onlineshop.service;

import com.example.onlineshop.domain.Product;
import com.example.onlineshop.exception.ResourceNotFoundException;
import com.example.onlineshop.persistence.ProductRepository;
import com.example.onlineshop.transfer.product.GetProductRequest;
import com.example.onlineshop.transfer.product.SaveProductRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public Product getProduct(long id) {
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
                .orElseThrow(() -> new ResourceNotFoundException("Product " + id + " not found."));
    }

    public Page<Product> getProducts(GetProductRequest request, Pageable pageable) {
//        if (request.getPartialName() != null && request.getMinimumQuantity() != null) {
//            return productRepository.findByNameContainingAndQuantityGreaterThanEqual(
//                    request.getPartialName(), request.getMinimumQuantity(), pageable);
//
//        } else if (request.getPartialName() != null) {
//            return productRepository.findByNameContaining(request.getPartialName(), pageable);
//
//        } else {
//            return productRepository.findAll(pageable);
//        }
      
        return productRepository.findByOptionalCriteria(request.getPartialName(), request.getMinimumQuantity(), pageable);
    }


    public Product updateProduct(long id, SaveProductRequest request) {
        LOOGER.info("Updating product {} : {}", id, request);

        Product product = getProduct(id);

        BeanUtils.copyProperties(request, product);

        return productRepository.save(product);
    }

    public void deleteProduct(long id) {
        LOOGER.info("Deleting product {}", id);
        productRepository.deleteById(id);
    }
}

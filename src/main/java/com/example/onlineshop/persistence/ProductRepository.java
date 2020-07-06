package com.example.onlineshop.persistence;

import com.example.onlineshop.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByNameContaining(String partialName, Pageable pageable);

    Page<Product> findByNameContainingAndQuantityGreaterThanEqual(String partialName, int minimumQuantity, Pageable pageable);

    //filtrare produse - toate dintr-un singur query
    //acesta inlocuieste if else din Service
    //JPQL syntax - java persistence query languages
    // @Query(value = "SELECT * FROM product", nativeQuery = true) //se poate si asa dar folosim mai jos JPQL
    @Query(value = "SELECT product FROM Product product WHERE " +
            "( :partialName IS null OR product.name LIKE %:partialName%) AND " +
            "(:minimumQuantity IS null OR product.quantity >= :minimumQuantity)")

    Page<Product> findByOptionalCriteria(String partialName, Integer minimumQuantity, Pageable pageable);

}

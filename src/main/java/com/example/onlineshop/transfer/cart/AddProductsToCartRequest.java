package com.example.onlineshop.transfer.cart;

import java.util.List;

public class AddProductsToCartRequest {

    private List<Long> productIds;

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }
}

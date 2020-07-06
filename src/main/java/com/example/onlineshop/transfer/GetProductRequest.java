package com.example.onlineshop.transfer;

public class GetProductRequest {

    //DTO
    private String partialName;
    private Integer minimumQuantity;

    public String getPartialName() {
        return partialName;
    }

    public void setPartialName(String partialName) {
        this.partialName = partialName;
    }

    public Integer getMinimumQuantity() {
        return minimumQuantity;
    }

    public void setMinimumQuantity(Integer minimumQuantity) {
        this.minimumQuantity = minimumQuantity;
    }

    @Override
    public String toString() {
        return "GetProductRequest{" +
                "partialName='" + partialName + '\'' +
                ", minimumQuantity=" + minimumQuantity +
                '}';
    }
}

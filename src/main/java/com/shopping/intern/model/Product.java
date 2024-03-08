package com.shopping.intern.model;

import java.io.File;

public class Product {

    private String productId;

    private String productName;

    private File productImage;

    private double productPrice;

    private String productFromPrice;

    private String productToPrice;

    private String isSales;

    private String description;

    private String createdAt;

    private String updatedAt;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public File getProductImage() {
        return productImage;
    }

    public void setProductImage(File productImage) {
        this.productImage = productImage;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getIsSales() {
        return isSales;
    }

    public void setIsSales(String isSales) {
        this.isSales = isSales;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getProductFromPrice() {
        return productFromPrice;
    }

    public void setProductFromPrice(String productFromPrice) {
        this.productFromPrice = productFromPrice;
    }

    public String getProductToPrice() {
        return productToPrice;
    }

    public void setProductToPrice(String productToPrice) {
        this.productToPrice = productToPrice;
    }

    public String cutDownDescriptionLength() {
        if (description.length() > 40) {
            return description.substring(0, 25) + "...";
        }

        return description;
    }

    public int getProductIndex() {
        try {
            return Integer.parseInt(productId.substring(1));
        } catch (Exception e) {
            return 0;
        }
    }
}

package com.shopping.intern.model;

import java.io.File;
import java.util.Date;

public class Product {

    private long productId;

    private String productUid;

    private String productName;

    private File productImage;

    private double productPrice;

    private String productFromPrice;

    private String productToPrice;

    private byte isSales = -1;

    private String description;

    private Date createdAt;

    private Date updatedAt;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductUid() {
        return productUid;
    }

    public void setProductUid(String productUid) {
        this.productUid = productUid;
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

    public byte getIsSales() {
        return isSales;
    }

    public void setIsSales(byte isSales) {
        this.isSales = isSales;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
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
}

package com.shopping.intern.repository.product;

import java.util.List;

import com.shopping.intern.model.Product;

public interface IProductRepository {

    List<Product> findAll(boolean paginate, int currentPage, int perPage, Product productForm);

    void insert(Product product);

    Product findById(String productId);

    Product findByName(String productName);

    Product findByNameWithException(String productName, String productId);

    void update(Product product);

    void deleteById(String productId);

    Product find(String value, String column, String productId);
}

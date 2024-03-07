package com.shopping.intern.repository.Product;

import java.util.List;

import com.shopping.intern.model.Product;

public interface IProductRepository {

    List<Product> findAll(boolean paginate, int currentPage, int perPage, Product productForm);

    void insert(Product product);

    Product findById(long productId);

    Product findByName(String productName);

    void update(Product product);

    void deleteById(long productId);
}

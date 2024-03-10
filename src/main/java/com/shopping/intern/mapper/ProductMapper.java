package com.shopping.intern.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shopping.intern.model.Product;

@Mapper
public interface ProductMapper {

    List<Product> findAll(boolean paginate, int currentPage, int perPage, Product productForm);

    void insert(Product product);

    Product findById(String productId);

    Product findByName(String productName, String productId);

    void update(Product product);

    void deleteById(String productId);
}

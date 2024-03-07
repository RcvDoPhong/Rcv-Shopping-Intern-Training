package com.shopping.intern.service.Product;

import java.util.List;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.shopping.intern.model.Product;

public interface IProductService {

    List<Product> paginate(int currentPage, int perPage, Product productForm);

    List<Product> findAll(Product productForm);

    void deleteProduct(long productId);

    // ResponseEntity<String> deleteUser(long productId);
}

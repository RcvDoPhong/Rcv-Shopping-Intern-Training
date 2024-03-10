package com.shopping.intern.service.product;

import java.util.List;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.shopping.intern.model.Product;

public interface IProductService {

    List<Product> paginate(int currentPage, int perPage, Product productForm);

    List<Product> findAll(Product productForm);

    Product getProduct(String productId);

    ResponseEntity<String> getProductAjax(String productId);

    void deleteProduct(String productId);

    ResponseEntity<String> handleCreateUpdate(Product productForm, String message, String typeProcess);

    // ResponseEntity<String> deleteUser(long productId);
}

package com.shopping.intern.service.Product;

import java.util.List;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shopping.intern.model.Product;
import com.shopping.intern.repository.Product.IProductRepository;

@Service
public class ProductService implements IProductService {

    private final IProductRepository productRepo;

    public ProductService(IProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> paginate(int currentPage, int perPage, Product productForm) {
        return this.productRepo.findAll(true, currentPage, perPage, productForm);
    }

    public List<Product> findAll(Product productForm) {
        return this.productRepo.findAll(false, 0, 0, productForm);
    }

    public void deleteProduct(long productId) {
        this.productRepo.deleteById(productId);
    }

    // public ResponseEntity<String> deleteProduct(long productId) {
    //     JSONObject response = new JSONObject();
    //     response.put("productId", productId);

    //     return new ResponseEntity<>(response.toString(), HttpStatus.OK);
    // }
}

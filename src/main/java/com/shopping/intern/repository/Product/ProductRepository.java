package com.shopping.intern.repository.Product;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import com.shopping.intern.mapper.ProductMapper;
import com.shopping.intern.model.Product;

@Repository
@MapperScan("com.shopping.intern.mapper")
public class ProductRepository implements IProductRepository {

    private final ProductMapper productMapper;

    public ProductRepository(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public List<Product> findAll(boolean paginate, int currentPage, int perPage, Product productForm) {
        return this.productMapper.findAll(paginate, currentPage, perPage, productForm);
    }

    public void insert(Product product) {
        this.productMapper.insert(product);
    }

    public Product findById(String productId) {
        return this.productMapper.findById(productId);
    }

    public Product findByName(String productName) {
        return this.productMapper.findByName(productName, null);
    }

    public Product findByNameWithException(String productName, String productId) {
        return this.productMapper.findByName(productName, productId);
    }

    public void update(Product product) {
        this.productMapper.update(product);
    }

    public void deleteById(String productId) {
        this.productMapper.deleteById(productId);
    }

    public Product find(String value, String column, String productId) {
        switch (column) {
            case "product_id":
                return this.findById(productId);

            case "product_name":
                if (productId == null) {
                    return this.findByName(value);
                }
                return this.findByNameWithException(value, productId);
        
            default:
                return null;
        }
    }
}

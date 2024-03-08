package com.shopping.intern.service.Product;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shopping.intern.component.CustomValidation;
import com.shopping.intern.model.Product;
import com.shopping.intern.repository.Product.IProductRepository;
import com.shopping.intern.request.ProductCreateUpdateRequest;

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

    public Product getProduct(String productId) {
        return this.productRepo.findById(productId);
    }

    public void deleteProduct(String productId) {
        this.productRepo.deleteById(productId);
    }

    public String getCurrentTimestamp() {
        Date date = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp timestamp = new Timestamp(date.getTime());

        return timeFormat.format(timestamp);
    }

    public void handleStoreUser(Product productForm) {
        String currentTime = getCurrentTimestamp();

        productForm.setCreatedAt(currentTime);
        productForm.setUpdatedAt(currentTime);
        this.productRepo.insert(productForm);
    }

    public void handleUpdateUser(Product productForm) {
        String currentTime = getCurrentTimestamp();
        productForm.setUpdatedAt(currentTime);
        this.productRepo.update(productForm);
    }

    public ResponseEntity<String> handleCreateUpdate(Product productForm, String message, String typeProcess) {
        JSONObject response = new JSONObject();

        CustomValidation validate = new CustomValidation();
        ProductCreateUpdateRequest validateMap = new ProductCreateUpdateRequest();
        validate.setProductRepo(productRepo);

        boolean validateProductNameFail = validate.validateSingleField(validateMap.getValidateMap(),
                productForm.getProductName(), "name", response, productForm.getProductId());

        boolean validateProductPriceFail = validate.validateSingleField(validateMap.getValidateMap(),
                String.valueOf(productForm.getProductPrice()), "price", response,
                productForm.getProductId());

        boolean validateDescriptionFail = validate.validateSingleField(validateMap.getValidateMap(),
                productForm.getDescription(), "description", response, productForm.getProductId());

        boolean validateIsSalesFail = validate.validateSingleField(validateMap.getValidateMap(),
                String.valueOf(productForm.getIsSales()), "status", response,
                productForm.getProductId());

        if (validateProductNameFail || validateProductPriceFail || validateDescriptionFail || validateIsSalesFail) {
            JSONObject error = new JSONObject();
            error.put("error", response);
            System.out.println(error.toString());
            return new ResponseEntity<>(error.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if (typeProcess.equals("create")) {
            handleStoreUser(productForm);
        } else {
            handleUpdateUser(productForm);
        }

        response.put("message", message);
        response.put("url", "/user/products/");

        return new ResponseEntity<>(response.toString(), HttpStatus.OK);
    }
}

package com.shopping.intern.service.product;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shopping.intern.component.CustomValidation;
import com.shopping.intern.model.Product;
import com.shopping.intern.repository.product.IProductRepository;
import com.shopping.intern.request.ProductCreateUpdateRequest;

@Service
public class ProductService implements IProductService {

    private final IProductRepository productRepo;

    private String defaultImageFileName = "product_placeholder.png";

    private Random random = new Random();

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

    public ResponseEntity<String> getProductAjax(String productId) {
        Product product = this.getProduct(productId);
        JSONObject response = new JSONObject();
        
        product = product != null ? product : new Product();
        HttpStatus status = product != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        
        response.put("productId", product.getProductId());
        response.put("productName", product.getProductName());
        response.put("productImage", product.getProductImage());
        response.put("productPrice", product.getProductPrice());

        response.put("errors", "Product doesn't exists!");
        response.put("url", "/user/products/");
        
        return new ResponseEntity<>(response.toString(), status);
    }

    public void handleStoreUser(Product productForm, CustomValidation validate) {
        String currentTime = getCurrentTimestamp();

        String imageName = productForm.getProductImage();
        if (productForm.getUploadImage() != null) {
            imageName = formatProductImageName(productForm);
            handleStoreImage(productForm, imageName, validate);
        }

        productForm.setProductImage(imageName);
        productForm.setCreatedAt(currentTime);
        productForm.setUpdatedAt(currentTime);
        this.productRepo.insert(productForm);
    }

    public void handleUpdateUser(Product productForm, CustomValidation validate) {
        String currentTime = getCurrentTimestamp();
        Product product = this.getProduct(productForm.getProductId());
        String imageName = product.getProductImage();

        if (productForm.getUploadImage() != null) {
            if (product.getProductImage().equals(defaultImageFileName)) {
                imageName = formatProductImageName(productForm);
            }
            handleStoreImage(productForm, imageName, validate);
            productForm.setProductImage(imageName);
        } else {
            productForm.setProductImage(product.getProductImage());
        }
        productForm.setUpdatedAt(currentTime);
        this.productRepo.update(productForm);
    }

    public ResponseEntity<String> handleCreateUpdate(Product productForm, String message, String typeProcess) {
        JSONObject response = new JSONObject();
        JSONObject error = new JSONObject();

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

        boolean validateImageFail = validate.validateImage(productForm.getUploadImage(),
                productForm.getUploadImageFileName(), response);

        if (validateProductNameFail || validateProductPriceFail || validateDescriptionFail || validateIsSalesFail
                || validateImageFail) {
            error.put("error", response);
            return new ResponseEntity<>(error.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if (typeProcess.equals("create")) {
            handleStoreUser(productForm, validate);
        } else {
            handleUpdateUser(productForm, validate);
        }

        response.put("message", message);
        response.put("url", "/user/products/");

        return new ResponseEntity<>(response.toString(), HttpStatus.OK);
    }

    public void handleStoreImage(Product productForm, String fileName, CustomValidation validate) {
        validate.handleStoreImage(productForm.getUploadImage(), fileName, "/products/");
    }

    public String formatProductImageName(Product product) {
        Date date = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
        Timestamp timestamp = new Timestamp(date.getTime());

        String newProductName = product.getProductName().replace(" ", "_");

        int randomNumber = getRandomNumberUsingNextInt(100000, 999999);
        String timeSave = timeFormat.format(timestamp);

        String[] imgFileNameSplitString = product.getUploadImageFileName().split("\\.");
        int stringLength = imgFileNameSplitString.length;
        String extension = imgFileNameSplitString[stringLength - 1];

        return String.format("%s_%s_%s.%s", newProductName, randomNumber, timeSave, extension);
    }

    public int getRandomNumberUsingNextInt(int min, int max) {
        return random.nextInt(max - min) + min;
    }
}

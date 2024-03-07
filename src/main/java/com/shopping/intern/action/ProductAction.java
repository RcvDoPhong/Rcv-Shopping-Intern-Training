package com.shopping.intern.action;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.tiles.annotation.TilesDefinition;
import org.apache.struts2.tiles.annotation.TilesDefinitions;
import org.springframework.http.ResponseEntity;

import com.opensymphony.xwork2.ActionSupport;
import com.shopping.intern.model.Product;
import com.shopping.intern.service.Product.IProductService;

@Namespace("/user/products")
@Results({
        @Result(name = "index", type = "tiles", location = "products"),
        @Result(name = "create", type = "tiles", location = "createProduct"),
        @Result(type = "json")
})
@TilesDefinitions({
        @TilesDefinition(name = "products", extend = "masterLayout"),
        @TilesDefinition(name = "createProduct", extend = "masterLayout")
})
public class ProductAction extends ActionSupport {

    private HttpServletRequest request = ServletActionContext.getRequest();

    private String requestUrl = "products";

    private StringBuilder searchURL = new StringBuilder();

    private Product productForm;

    private int page;

    private int totalPage;

    private int perPage = 1;

    private long productId;

    private String productName;

    private byte status;

    private String fromPrice;

    private String toPrice;

    private byte isSales = -1;

    private String changePageAction = "/user/products/";

    private String[] statusList = { "Stop production", "On Sales", "Out of stock" };

    private ResponseEntity<String> jsonResponseEntity;

    private List<Product> productListPaginate;

    private final IProductService productService;

    private File file;

    public ProductAction(IProductService productService) {
        this.productService = productService;
    }

    public List<Product> getProductListPaginate() {
        return productListPaginate;
    }

    public void setProductListPaginate(List<Product> productListPaginate) {
        this.productListPaginate = productListPaginate;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public String getChangePageAction() {
        return changePageAction;
    }

    public String[] getStatusList() {
        return statusList;
    }

    public void setStatusList(String[] statusList) {
        this.statusList = statusList;
    }

    public Product getProductForm() {
        return productForm;
    }

    public void setProductForm(Product productForm) {
        this.productForm = productForm;
    }

    public StringBuilder getSearchURL() {
        return searchURL;
    }

    public void setSearchURL(StringBuilder searchURL) {
        this.searchURL = searchURL;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public String getFromPrice() {
        return fromPrice;
    }

    public void setFromPrice(String fromPrice) {
        this.fromPrice = fromPrice;
    }

    public String getToPrice() {
        return toPrice;
    }

    public void setToPrice(String toPrice) {
        this.toPrice = toPrice;
    }

    public byte getIsSales() {
        return isSales;
    }

    public void setIsSales(byte isSales) {
        this.isSales = isSales;
    }

    public ResponseEntity<String> getJsonResponseEntity() {
        return jsonResponseEntity;
    }

    public void setJsonResponseEntity(ResponseEntity<String> jsonResponseEntity) {
        this.jsonResponseEntity = jsonResponseEntity;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void handleSearchUrl() {
        Product tempProduct = new Product();

        if (productName != null) {
            tempProduct.setProductName(productName.equals("") ? null : productName);
            searchURL.append("productName=" + productName + "&");
        }

        if (fromPrice != null) {
            tempProduct.setProductFromPrice(fromPrice.equals("") ? null : fromPrice);
            searchURL.append("fromPrice=" + fromPrice + "&");
        }

        if (toPrice != null) {
            tempProduct.setProductToPrice(toPrice.equals("") ? null : toPrice);
            searchURL.append("toPrice=" + toPrice + "&");
        }

        if (isSales > -1) {
            tempProduct.setIsSales(isSales);
            searchURL.append("isSales=" + isSales + "&");
        }

        setProductForm(tempProduct);
    }

    @Action("")
    public String index() {
        handleSearchUrl();

        String pageGet = request.getParameter("page");
        setPage(pageGet == null ? 1 : Integer.parseInt(pageGet));

        int totalProducts = this.productService.findAll(productForm).size();
        setTotalPage((int) Math.ceil((double) totalProducts / perPage));

        int currentPage = (page - 1) * perPage;
        setProductListPaginate(this.productService.paginate(currentPage, perPage, productForm));

        return "index";
    }

    @Action("create")
    public String create() {
        return "create";
    }

    @Override
    @Action(value = "store", interceptorRefs = {
        @InterceptorRef(value = "basicStack"),
        @InterceptorRef(value = "fileUpload", params = {"allowedTypes", "image/jpeg,image/gif,image/png"})
    })
    public String execute() {
        // File localFile = new File("D:\\", "test.png");
        // FileUtils.copyFile(productForm.getProductImage(), localFile);
        System.out.println("Upload file " + file);
        System.out.println(productForm.getProductName());
        System.out.println(productForm.getDescription());

        return SUCCESS;
    }

    @Action("delete")
    public String delete() {
        this.productService.deleteProduct(productId);
        return SUCCESS;
    }
}

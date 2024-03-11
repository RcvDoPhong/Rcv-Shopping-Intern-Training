package com.shopping.intern.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.tiles.annotation.TilesDefinition;
import org.apache.struts2.tiles.annotation.TilesDefinitions;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.opensymphony.xwork2.ActionSupport;
import com.shopping.intern.model.Product;
import com.shopping.intern.service.product.IProductService;

@Namespace("/user/products")
@Results({
        @Result(name = "input", type = "tiles", location = "products"),
        @Result(name = "redirectProductList", type = "redirectAction", params = { "actionName", "" }),
        @Result(name = "create", type = "tiles", location = "createProduct"),
        @Result(name = "update", type = "tiles", location = "updateProduct"),
        @Result(type = "json")
})
@TilesDefinitions({
        @TilesDefinition(name = "products", extend = "masterLayout"),
        @TilesDefinition(name = "createProduct", extend = "masterLayout"),
        @TilesDefinition(name = "updateProduct", extend = "masterLayout")
})
@InterceptorRefs({
    @InterceptorRef(value = "authSecureStack")
})
public class ProductAction extends ActionSupport {

	private HttpServletRequest request = ServletActionContext.getRequest();

    private String requestUrl = "products";

    private StringBuilder searchURL = new StringBuilder();

    private Product productForm;

    private int page;

    private int totalPage;

    private int perPage = 10;

    private String productId;

    private String productName;

    private byte status;

    private String fromPrice;

    private String toPrice;

    private String isSales;

    private String changePageAction = "/user/products/";

    private String[] statusList = { "Stop production", "On Sales", "Out of stock" };

    private ResponseEntity<String> jsonResponse;

    private List<Product> productListPaginate;

    private final IProductService productService;

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

    public String getIsSales() {
        return isSales;
    }

    public void setIsSales(String isSales) {
        this.isSales = isSales;
    }

    public ResponseEntity<String> getJsonResponse() {
        return jsonResponse;
    }

    public void setJsonResponse(ResponseEntity<String> jsonResponse) {
        this.jsonResponse = jsonResponse;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

        if (isSales != null) {
            tempProduct.setIsSales(isSales.equals("") ? null : isSales);
            searchURL.append("isSales=" + isSales + "&");
        }

        setProductForm(tempProduct);
    }

    public void setupMessageResponse(String message, String productId) {
        JSONObject response = new JSONObject();
        Product productInfo = this.productService.getProduct(productId);
        response.put("message", String.format(message, productInfo.getProductName()));
        setJsonResponse(new ResponseEntity<>(response.toString(), HttpStatus.OK));
    }

    @Action(value = "", interceptorRefs = {
            @InterceptorRef(value = "store", params = { "operationMode", "RETRIEVE" })
    })
    @Override
    public String execute() {
        handleSearchUrl();

        String pageGet = request.getParameter("page");
        setPage(pageGet == null ? 1 : Integer.parseInt(pageGet));

        int totalProducts = this.productService.findAll(productForm).size();
        setTotalPage((int) Math.ceil((double) totalProducts / perPage));

        int currentPage = (page - 1) * perPage;
        setProductListPaginate(this.productService.paginate(currentPage, perPage, productForm));

        return "input";
    }

    @Action("getProduct")
    public String productAjax() {
        setProductId(request.getParameter("productId"));
        setJsonResponse(this.productService.getProductAjax(productId));

        return SUCCESS;
    }

    /* Create product view */
    @Action("create")
    public String create() {
        return "create";
    }

    /* Store record in Database action */
    @Action(value = "store", interceptorRefs = {
        @InterceptorRef(value = "defaultStack")
    })
    public String store() {
        String message = "Create new product successfully";
        setJsonResponse(this.productService.handleCreateUpdate(productForm, message, "create"));
        return SUCCESS;
    }

    /* Edit existed Product value */
    @Action(value = "edit", interceptorRefs = {
            @InterceptorRef(value = "store", params = { "operationMode", "STORE" }),
            @InterceptorRef(value = "defaultStack")
    })
    public String edit() {
        setProductId(request.getParameter("productId"));
        // If Product ID not found => redirect
        if (productId == null) {
            addActionError("Product ID is invalid");
            return "redirectProductList";
        }
        
        Product product = this.productService.getProduct(productId);
        if (product == null) {
        	addActionError("Product ID is invalid");
            return "redirectProductList";
        }
        setProductForm(product);

        return "update";
    }

    @Action(value = "update", interceptorRefs = {
        @InterceptorRef(value = "defaultStack")
    })
    public String update() {
        String message = "Update product's Info successfully";
        setJsonResponse(this.productService.handleCreateUpdate(productForm, message, "update"));
        return SUCCESS;
    }

    @Action("delete")
    public String delete() {
        setupMessageResponse("Delete %s successfully", productId);
        this.productService.deleteProduct(productId);
        return SUCCESS;
    }
}

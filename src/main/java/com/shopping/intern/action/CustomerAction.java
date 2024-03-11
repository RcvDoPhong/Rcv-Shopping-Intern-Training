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
import org.springframework.http.ResponseEntity;

import com.opensymphony.xwork2.ActionSupport;
import com.shopping.intern.model.Customer;
import com.shopping.intern.service.customer.ICustomerService;

@Namespace("/user/customers")
@Results({
        @Result(name = "index", type = "tiles", location = "customers"),
        @Result(type = "json")
})
@TilesDefinitions({
        @TilesDefinition(name = "customers", extend = "masterLayout")
})
@InterceptorRefs({
        @InterceptorRef(value = "authSecureStack")
})
public class CustomerAction extends ActionSupport {

    private HttpServletRequest request = ServletActionContext.getRequest();

    private String requestUrl = "customers";

    private final ICustomerService customerService;

    private Customer customerForm;
    
    private long customerId;

	private int page;

    private int totalPage;

    private int perPage = 10;

    private List<Customer> customerList;

    private ResponseEntity<String> jsonResponse;

    private StringBuilder searchURL = new StringBuilder();

    private String customerName;

    private String email;

    private String isActive;

    private String address;

    private String[] statusList = { "Non-Active", "Active" };

    public CustomerAction(ICustomerService customerService) {
        this.customerService = customerService;
    }

    public Customer getCustomerForm() {
        return customerForm;
    }

    public void setCustomerForm(Customer customerForm) {
        this.customerForm = customerForm;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
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

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String[] getStatusList() {
        return statusList;
    }

    public void setStatusList(String[] statusList) {
        this.statusList = statusList;
    }

    public ResponseEntity<String> getJsonResponse() {
        return jsonResponse;
    }

    public void setJsonResponse(ResponseEntity<String> jsonResponse) {
        this.jsonResponse = jsonResponse;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public StringBuilder getSearchURL() {
        return searchURL;
    }

    public void setSearchURL(StringBuilder searchURL) {
        this.searchURL = searchURL;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }
    
    public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

    public void handleSearchUrl() {
        Customer tempCustomerValue = new Customer();

        if (customerName != null) {
            tempCustomerValue.setCustomerName(customerName.equals("") ? null : customerName);
            searchURL.append("customerName=" + customerName + "&");
        }

        if (email != null) {
            tempCustomerValue.setEmail(email.equals("") ? null : email);
            searchURL.append("email=" + email + "&");
        }

        if (isActive != null) {
            tempCustomerValue.setIsActive(isActive.equals("") ? null : isActive);
            searchURL.append("isActive=" + isActive + "&");
        }

        if (address != null) {
            tempCustomerValue.setAddress(address.equals("") ? null : address);
            searchURL.append("address=" + address + "&");
        }

        setCustomerForm(tempCustomerValue);
    }

    @Action("")
    public String index() {
        handleSearchUrl();
        String pageGet = request.getParameter("page");
        setPage(pageGet == null ? 1 : Integer.parseInt(pageGet));

        int totalProducts = this.customerService.findAll(customerForm).size();
        setTotalPage((int) Math.ceil((double) totalProducts / perPage));

        int currentPage = (page - 1) * perPage;
        setCustomerList(this.customerService.paginate(currentPage, perPage, customerForm));
        return "index";
    }

    @Action("getCustomer")
    public String get() {
    	setCustomerId(Long.parseLong(request.getParameter("customerId")));
        setJsonResponse(this.customerService.getCustomerAjax(customerId));

        return SUCCESS;
    }

    @Action(value = "create", interceptorRefs = {
            @InterceptorRef(value = "defaultStack")
    })
    public String store() {
        String message = "Create new customer successfully";
        setJsonResponse(this.customerService.handleCreateUpdate(customerForm, message, "create"));

        return SUCCESS;
    }

    @Action(value = "update", interceptorRefs = {
            @InterceptorRef(value = "defaultStack")
    })
    public String update() {
        String message = "Update customer's info successfully";
        setJsonResponse(this.customerService.handleCreateUpdate(customerForm, message, "update"));

        return SUCCESS;
    }
}

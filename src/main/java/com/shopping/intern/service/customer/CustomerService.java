package com.shopping.intern.service.customer;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.apache.struts2.json.annotations.JSON;
import org.json.HTTP;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shopping.intern.component.CustomValidation;
import com.shopping.intern.model.Customer;
import com.shopping.intern.repository.customer.ICustomerRepository;
import com.shopping.intern.request.CustomerCreateUpdateRequest;

@Service
public class CustomerService implements ICustomerService {

    private final ICustomerRepository customerRepo;

    public CustomerService(ICustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    public String getCurrentTimestamp() {
        Date date = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp timestamp = new Timestamp(date.getTime());

        return timeFormat.format(timestamp);
    }

    public List<Customer> paginate(int currentPage, int perPage, Customer customerForm) {
        return this.customerRepo.findAll(true, currentPage, perPage, customerForm);
    }

    public List<Customer> findAll(Customer customerForm) {
        return this.customerRepo.findAll(false, 0, 0, customerForm);
    }

    public Customer getCustomer(long customerId) {
        return this.customerRepo.findById(customerId);
    }

    public void handleCreateNewCustomer(Customer customerForm) {
        customerForm.setCreatedAt(getCurrentTimestamp());
        customerForm.setUpdatedAt(getCurrentTimestamp());
        this.customerRepo.insert(customerForm);
    }

    public void handleUpdateCustomer(Customer customerForm) {
        Customer customer = this.getCustomer(customerForm.getCustomerId());

        customerForm.setIsActive(customer.getIsActive());
        customerForm.setUpdatedAt(getCurrentTimestamp());
        this.customerRepo.update(customerForm);
    }

    public ResponseEntity<String> getCustomerAjax(long customerId) {
        JSONObject response = new JSONObject();

        Customer customer = this.getCustomer(customerId);

        response.put("customerId", customer.getCustomerId());
        response.put("name", customer.getCustomerName());
        response.put("email", customer.getEmail());
        response.put("address", customer.getAddress());
        response.put("phone", customer.getTelNum());
        response.put("isActive", customer.getIsActive());

        return new ResponseEntity<>(response.toString(), HttpStatus.OK);
    }

    public ResponseEntity<String> handleCreateUpdate(Customer customerForm, String message, String processType) {
        JSONObject response = new JSONObject();
        JSONObject error = new JSONObject();

        CustomValidation validate = new CustomValidation();
        validate.setCustomerRepo(customerRepo);

        CustomerCreateUpdateRequest customerMap = new CustomerCreateUpdateRequest();

        String customerIdString = String.valueOf(customerForm.getCustomerId());
        boolean validateNameFail = validate.validateSingleField(customerMap.getValidateMap(),
                customerForm.getCustomerName(), "name", response, customerIdString);

        boolean validateEmailFail = validate.validateSingleField(customerMap.getValidateMap(),
                customerForm.getEmail(), "email", response, customerIdString);

        boolean validateTelNumFail = validate.validateSingleField(customerMap.getValidateMap(),
                customerForm.getTelNum(), "phone", response, customerIdString);

        boolean validateAddressFail = validate.validateSingleField(customerMap.getValidateMap(),
                customerForm.getAddress(), "address", response, customerIdString);

        boolean validateIsActivesFail = validate.validateSingleField(customerMap.getValidateMap(),
                customerForm.getIsActive(), "status", response, customerIdString);

        if (validateNameFail || validateEmailFail || validateTelNumFail || validateAddressFail
                || validateIsActivesFail) {
            error.put("error", response);
            return new ResponseEntity<>(error.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if (processType.equals("create")) {
            handleCreateNewCustomer(customerForm);
        } else {
            handleUpdateCustomer(customerForm);
        }

        response.put("message", message);
        return new ResponseEntity<>(response.toString(), HttpStatus.OK);
    }
}

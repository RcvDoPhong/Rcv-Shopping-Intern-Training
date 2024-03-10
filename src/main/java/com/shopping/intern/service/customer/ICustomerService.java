package com.shopping.intern.service.customer;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.shopping.intern.model.Customer;

public interface ICustomerService {
    List<Customer> paginate(int currentPage, int perPage, Customer customerForm);

    List<Customer> findAll(Customer customerForm);

    ResponseEntity<String> handleCreateUpdate(Customer productForm, String message, String processType);

    ResponseEntity<String> getCustomerAjax(long customerId);
}

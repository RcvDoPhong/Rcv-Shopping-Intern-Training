package com.shopping.intern.repository.customer;

import java.util.List;

import com.shopping.intern.model.Customer;

public interface ICustomerRepository {
    List<Customer> findAll(boolean paginate, int currentPage, int perPage, Customer customerForm);

    Customer findById(long customerId);

    Customer findByEmail(String email);

    Customer findByEmailWithException(String email, long customerId);

    void insert(Customer customer);

    void update(Customer customer);

    void lockById(long customerId);

    Customer findByName(String name);

    Customer findByNameWithException(String name, long customerId);
    
    Customer findByTelNum(String name);

    Customer findByTelNumWithException(String name, long customerId);

    Customer find(String value, String column, long id);
}

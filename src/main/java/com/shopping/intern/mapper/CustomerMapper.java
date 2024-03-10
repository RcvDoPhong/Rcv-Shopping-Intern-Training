package com.shopping.intern.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shopping.intern.model.Customer;

@Mapper
public interface CustomerMapper {

    List<Customer> findAll(boolean paginate, int currentPage, int perPage, Customer customerForm);

    Customer findById(long customerId);

    Customer findByEmail(String email, long customerId);

    boolean existByEmail(String email);

    void insert(Customer user);

    void update(Customer user);

    void lockById(long customerId);

    Customer findByName(String name, long customerId);
}

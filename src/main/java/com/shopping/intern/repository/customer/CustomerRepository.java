package com.shopping.intern.repository.customer;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import com.shopping.intern.mapper.CustomerMapper;
import com.shopping.intern.model.Customer;

@Repository
@MapperScan("com.shopping.intern.mapper")
public class CustomerRepository implements ICustomerRepository {

    private final CustomerMapper customerMapper;

    public CustomerRepository(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    public List<Customer> findAll(boolean paginate, int currentPage, int perPage, Customer customerForm) {
        return this.customerMapper.findAll(paginate, currentPage, perPage, customerForm);
    }

    public Customer findById(long customerId) {
        return this.customerMapper.findById(customerId);
    }

    public Customer findByEmail(String email) {
        return this.customerMapper.findByEmail(email, 0);
    }

    public Customer findByEmailWithException(String email, long customerId) {
        return this.customerMapper.findByEmail(email, customerId);
    }

    public void insert(Customer customer) {
        this.customerMapper.insert(customer);
    }

    public void update(Customer customer) {
        this.customerMapper.update(customer);
    }

    public void lockById(long customerId) {
        this.customerMapper.lockById(customerId);
    }

    public Customer findByName(String name) {
        return this.customerMapper.findByName(name, 0);
    }

    public Customer findByNameWithException(String name, long customerId) {
        return this.customerMapper.findByName(name, customerId);
    }
    
    public Customer findByTelNum(String telNum) {
        return this.customerMapper.findByTelNum(telNum, 0);
    }

    public Customer findByTelNumWithException(String telNum, long customerId) {
        return this.customerMapper.findByTelNum(telNum, customerId);
    }

    public Customer find(String value, String column, long customerId) {
        switch (column) {
            case "customer_id":
                return this.findById(customerId);

            case "customer_name":
                if (customerId == 0) {
                    return this.findByName(value);
                }
                return this.findByNameWithException(value, customerId);

            case "email":
                if (customerId == 0) {
                    return this.findByEmail(value);
                }
                return this.findByEmailWithException(value, customerId);
                
            case "tel_num":
                if (customerId == 0) {
                    return this.findByTelNum(value);
                }
                return this.findByTelNumWithException(value, customerId);

            default:
                return null;
        }
    }

}

package com.shopping.intern;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.shopping.intern.mapper.EmployeeMapper;
import com.shopping.intern.mapper.UserMapper;
import com.shopping.intern.model.Employee;
import com.shopping.intern.model.User;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
// @SpringBootApplication
@MapperScan("com.shopping.intern.mapper")
public class ShoppingIntern {
    public static void main(String[] args) {
        SpringApplication.run(ShoppingIntern.class, args);
    }

    private final UserMapper userMapper;

    public ShoppingIntern(UserMapper userMapper)  {
        this.userMapper = userMapper;
    }

    @Bean
    CommandLineRunner sampleCommandLineRunner() {
        return args -> {
            // Employee employee = new Employee("Mario 10", "Bros 32", "mario_bros_22@email.com");
            // Employee employee = this.employeeMapper.findById(1);
            // List<Employee> employee = this.employeeMapper.findAll();
            // employee.setId(1);
            // employee.setFirstName("Luigi");
            // employee.setLastName("Bros 2");
            // employee.setEmailAddress("luigi_bros@email.com");
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
            System.out.println("Password: " + encoder.encode("password"));
        };
    }
}

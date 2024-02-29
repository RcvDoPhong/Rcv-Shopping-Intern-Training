package com.shopping.intern;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ShoppingIntern extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ShoppingIntern.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ShoppingIntern.class, args);
    }

    // private final UserMapper userMapper;

    // public ShoppingIntern(UserMapper userMapper)  {
    //     this.userMapper = userMapper;
    // }

    // @Bean
    // CommandLineRunner sampleCommandLineRunner() {
    //     return args -> {
    //         // Employee employee = new Employee("Mario 10", "Bros 32", "mario_bros_22@email.com");
    //         // Employee employee = this.employeeMapper.findById(1);
    //         // List<Employee> employee = this.employeeMapper.findAll();
    //         // employee.setId(1);
    //         // employee.setFirstName("Luigi");
    //         // employee.setLastName("Bros 2");
    //         // employee.setEmailAddress("luigi_bros@email.com");
    //         BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
    //         System.out.println("Password: " + encoder.encode("password"));
    //     };
    // }
}

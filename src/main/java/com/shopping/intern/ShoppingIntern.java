package com.shopping.intern;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.shopping.intern.model.User;
import com.shopping.intern.service.user.IUserService;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ShoppingIntern extends SpringBootServletInitializer implements CommandLineRunner {

    @Autowired
    private final IUserService userService;

    public ShoppingIntern(IUserService userService) {
        this.userService = userService;
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ShoppingIntern.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ShoppingIntern.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        userService.handleFirstRunProject();
    }
}

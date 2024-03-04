package com.shopping.intern.service;

import java.util.ArrayList;
import java.util.List;

import com.shopping.intern.model.User;
import com.shopping.intern.request.UserLoginRequest;

public interface IUserService {
    ArrayList<User> findAll();

    User findById(long userId);

    User findByEmail(String email);

    // boolean existByEmail(String email);

    void insert(User user);

    void update(User user);

    void deleteById(long userId);

    // boolean loginUser(String email);

    boolean checkLogin(UserLoginRequest userLoginRequest);

    void storeTempValueLoginFail(UserLoginRequest userLoginRequest);
}

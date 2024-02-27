package com.shopping.intern.repository.User;

import java.util.List;

import com.shopping.intern.model.User;
import com.shopping.intern.request.UserLoginRequest;

public interface IUserRepository {

    List<User> findAll();

    User findById(long userId);

    User findByEmail(String email);

    void insert(User user);

    void update(User user);

    void deleteById(long userId);

    boolean loginUser(String email);
}

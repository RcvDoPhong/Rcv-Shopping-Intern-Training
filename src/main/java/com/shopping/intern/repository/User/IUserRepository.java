package com.shopping.intern.repository.User;

import java.util.ArrayList;
import java.util.List;

import com.shopping.intern.model.User;
import com.shopping.intern.request.UserLoginRequest;

public interface IUserRepository {

    ArrayList<User> findAll(boolean paginate, int currentPage);

    User findById(long userId);

    User findByEmail(String email);

    void insert(User user);

    void update(User user);

    void deleteById(long userId);
}

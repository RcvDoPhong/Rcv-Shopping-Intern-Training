package com.shopping.intern.repository.User;

import java.util.List;

import com.shopping.intern.model.User;
import com.shopping.intern.request.UserLoginRequest;

public interface IUserRepository {

    List<User> findAll(boolean paginate, int currentPage, int perPage);

    User findById(long userId);

    User findByEmail(String email);

    void insert(User user);

    void update(User user);

    void lockById(long userId);

    void deleteById(long userId);

    User findByName(String name);
}

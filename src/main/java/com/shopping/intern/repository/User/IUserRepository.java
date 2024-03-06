package com.shopping.intern.repository.User;

import java.util.List;

import com.shopping.intern.model.User;
import com.shopping.intern.request.UserLoginRequest;

public interface IUserRepository {

    List<User> findAll(boolean paginate, int currentPage, int perPage, User userSearchForm);

    User findById(long userId);

    User findByEmail(String email);

    User findByEmailWithException(String email, long userId);

    void insert(User user);

    void update(User user);

    void lockById(long userId);

    void deleteById(long userId);

    User findByName(String name);

    User find(String value, String column, long userId);
}

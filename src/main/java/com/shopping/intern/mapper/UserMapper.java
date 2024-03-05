package com.shopping.intern.mapper;


import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.shopping.intern.model.User;

@Mapper
public interface UserMapper {

    ArrayList<User> findAll(boolean paginate, int currentPage, int perPage);

    User findById(long userId);

    User findByEmail(String email);

    boolean existByEmail(String email);

    void insert(User user);

    void update(User user);

    void lockById(long userId);

    void deleteById(long userId);

    User findByName(String name);
}

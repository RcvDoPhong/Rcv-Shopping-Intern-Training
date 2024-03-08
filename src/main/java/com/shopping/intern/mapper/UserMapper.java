package com.shopping.intern.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shopping.intern.model.User;

@Mapper
public interface UserMapper {

    List<User> findAll(boolean paginate, int currentPage, int perPage, User userSearchForm);

    User findById(long userId);

    User findByEmail(String email, long id);

    boolean existByEmail(String email);

    void insert(User user);

    void update(User user);

    void lockById(long userId);

    void deleteById(long userId);

    User findByName(String name, long id);
}

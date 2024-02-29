package com.shopping.intern.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shopping.intern.model.User;

@Mapper
public interface UserMapper {

    List<User> findAll();

    User findById(long userId);

    User findByEmail(String email);

    boolean existByEmail(String email);

    void insert(User user);

    void update(User user);

    void deleteById(long userId);
}

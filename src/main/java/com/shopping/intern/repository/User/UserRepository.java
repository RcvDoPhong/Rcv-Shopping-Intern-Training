package com.shopping.intern.repository.User;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import com.shopping.intern.mapper.UserMapper;
import com.shopping.intern.model.User;
import com.shopping.intern.service.UserService;

@Repository
@MapperScan("com.shopping.intern.mapper")
public class UserRepository implements IUserRepository {

    private final UserMapper userMapper;

    public UserRepository(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public ArrayList<User> findAll(boolean paginate, int currentPage) {
        return this.userMapper.findAll(paginate, currentPage);
    }

    public User findById(long userId) {
        return this.userMapper.findById(userId);
    }

    public User findByEmail(String email) {
        return this.userMapper.findByEmail(email);
    }

    public void insert(User user) {
        this.userMapper.insert(user);
    }

    public void update(User user) {
        this.userMapper.update(user);
    }

    public void deleteById(long userId) {
        this.userMapper.deleteById(userId);
    }
}

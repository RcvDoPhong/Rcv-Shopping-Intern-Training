package com.shopping.intern.repository.User;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import com.shopping.intern.mapper.UserMapper;
import com.shopping.intern.model.User;
import com.shopping.intern.service.User.UserService;

@Repository
@MapperScan("com.shopping.intern.mapper")
public class UserRepository implements IUserRepository {

    private final UserMapper userMapper;

    public UserRepository(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<User> findAll(boolean paginate, int currentPage, int perPage, User userSearchForm) {
        return this.userMapper.findAll(paginate, currentPage, perPage, userSearchForm);
    }

    public User findById(long userId) {
        return this.userMapper.findById(userId);
    }

    public User findByEmailWithException(String email, long userId) {
        return this.userMapper.findByEmail(email, userId);
    }

    public User findByEmail(String email) {
        return this.userMapper.findByEmail(email, 0);
    }

    public void insert(User user) {
        this.userMapper.insert(user);
    }

    public void update(User user) {
        this.userMapper.update(user);
    }

    public void lockById(long userId) {
        this.userMapper.lockById(userId);
    }

    public void deleteById(long userId) {
        this.userMapper.deleteById(userId);
    }

    public User findByName(String name) {
        return this.userMapper.findByName(name);
    }

    public User find(String value, String column, long userId) {
        User user = null;
        switch (column) {
            case "userId":
                return this.findById(Integer.parseInt(value));

            case "name":
                return this.findByName(value);

            case "email":
                return this.findByEmailWithException(value, userId);
        }

        return user;
    }
}

package com.shopping.intern.repository.User;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.opensymphony.xwork2.ActionContext;
import com.shopping.intern.mapper.UserMapper;
import com.shopping.intern.model.User;
import com.shopping.intern.request.UserLoginRequest;

@Repository
@MapperScan("com.shopping.intern.mapper")
public class UserRepository implements IUserRepository {

    private final UserMapper userMapper;

    public UserRepository(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<User> findAll() {
        return this.userMapper.findAll();
    }

    public User findById(long userId) {
        return this.userMapper.findById(userId);
    }

    public User findByEmail(String email) {
        return this.userMapper.findByEmail(email);
    }

    public boolean loginUser(String email) {
        return this.userMapper.existByEmail(email);
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

    public boolean checkLogin(UserLoginRequest userLoginRequest) {
        User user = this.findByEmail(userLoginRequest.getEmail());
        if (user != null) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
            if (encoder.matches(userLoginRequest.getPassword(), user.getPassword())) {
                this.handleAfterLogin(user);
                return true;
            }
        }
        this.storeTempValueLoginFail(userLoginRequest);
        return false;
    }

    public void handleAfterLogin(User user) {
        Map<String, Object> session = ActionContext.getContext().getSession();
        session.put("userSession", user);
    }

    public void storeTempValueLoginFail(UserLoginRequest userLoginRequest) {
        Map<String, Object> session = ActionContext.getContext().getSession();
        session.put("email", userLoginRequest.getEmail());
    }
}

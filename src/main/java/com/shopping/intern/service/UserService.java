package com.shopping.intern.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;
import com.shopping.intern.mapper.UserMapper;
import com.shopping.intern.model.User;
import com.shopping.intern.repository.User.IUserRepository;
import com.shopping.intern.request.UserLoginRequest;

@Service
public class UserService implements IUserService {
    private final IUserRepository userRepo;

    public UserService(IUserRepository userRepo) {
        this.userRepo = userRepo;
    }

    // public boolean existByEmail(String email) {
    //     return this.userRepo.existByEmail(email);
    // }

    public ArrayList<User> paginate(int currentPage) {
        return this.userRepo.findAll(true, currentPage);
    }

    public ArrayList<User> findAll() {
        return this.userRepo.findAll(false, 0);
    }

    public User findById(long userId) {
        return this.userRepo.findById(userId);
    }

    public User findByEmail(String email) {
        return this.userRepo.findByEmail(email);
    }

    public void insert(User user) {
        this.userRepo.insert(user);
    }

    public void update(User user) {
        this.userRepo.update(user);
    }

    public void deleteById(long userId) {
        this.userRepo.deleteById(userId);
    }

    public boolean checkLogin(UserLoginRequest userLoginRequest) {
        User user = this.findByEmail(userLoginRequest.getEmail());
        if (user != null) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
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

    // public boolean loginUser(String email) {
    //     return this.userMapper.existByEmail(email);
    // }
}

package com.shopping.intern.service.User;

import java.util.List;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;

import com.shopping.intern.model.User;
import com.shopping.intern.request.UserLoginRequest;

public interface IUserService {

    List<User> paginate(int currentPage, int perPage);

    List<User> findAll();

    User findById(long userId);

    User findByEmail(String email);

    // boolean existByEmail(String email);

    void insert(User user);

    void update(User user);

    void deleteById(long userId);

    void lockById(long userId);

    // boolean loginUser(String email);

    boolean checkLogin(UserLoginRequest userLoginRequest);

    void storeTempValueLoginFail(UserLoginRequest userLoginRequest);

    JSONObject validate(User userRequest);
}

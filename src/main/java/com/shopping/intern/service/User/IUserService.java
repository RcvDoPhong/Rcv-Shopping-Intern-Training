package com.shopping.intern.service.User;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.shopping.intern.model.User;
import com.shopping.intern.request.UserLoginRequest;

public interface IUserService {

    List<User> paginate(int currentPage, int perPage, User userSearchForm);

    List<User> findAll(User userSearchForm);

    User get(long userId);

    ResponseEntity<String> getUser(long userId);

    void deleteById(long userId);

    void lockById(long userId);

    boolean checkLogin(UserLoginRequest userLoginRequest);

    void storeTempValueLoginFail(UserLoginRequest userLoginRequest);

    ResponseEntity<String> validate(User userRequest, String message, String createType);
}

package com.shopping.intern.service.user;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.shopping.intern.model.User;
import com.shopping.intern.request.UserLoginRequest;

public interface IUserService {

    List<User> paginate(int currentPage, int perPage, User userSearchForm);

    List<User> findAll(User userSearchForm);

    User get(long userId);

    void handleFirstRunProject();

    ResponseEntity<String> getUser(long userId);

    void deleteById(long userId);

    void lockById(long userId);

    ResponseEntity<String> validateLogin(UserLoginRequest userLoginRequest);

    ResponseEntity<String> validate(User userRequest, String message, String createType);
}

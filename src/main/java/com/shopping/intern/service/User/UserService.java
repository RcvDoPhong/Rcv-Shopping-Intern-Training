package com.shopping.intern.service.User;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.catalina.connector.Response;
import org.json.JSONObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.opensymphony.xwork2.ActionContext;
import com.shopping.intern.mapper.UserMapper;
import com.shopping.intern.model.User;
import com.shopping.intern.repository.User.IUserRepository;
import com.shopping.intern.request.UserCreateUpdateRequest;
import com.shopping.intern.request.UserLoginRequest;

@Service
public class UserService implements IUserService {
    private final IUserRepository userRepo;

    private byte DELETE = 1;

    private byte DISABLED = 0;

    public UserService(IUserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public byte getDELETE() {
        return DELETE;
    }

    public void setDELETE(byte dELETE) {
        DELETE = dELETE;
    }

    public byte getDISABLED() {
        return DISABLED;
    }

    public void setDISABLED(byte dISABLED) {
        DISABLED = dISABLED;
    }

    // public boolean existByEmail(String email) {
    // return this.userRepo.existByEmail(email);
    // }

    public List<User> paginate(int currentPage, int perPage) {
        return this.userRepo.findAll(true, currentPage, perPage);
    }

    public List<User> findAll() {
        return this.userRepo.findAll(false, 0, 0);
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

    public void lockById(long userId) {
        this.userRepo.lockById(userId);
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

    public ResponseEntity<String> validate(UserCreateUpdateRequest userRequest) {
        JSONObject response = new JSONObject();
        boolean validateFail = false;
        Pattern emailValidation = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                Pattern.CASE_INSENSITIVE);

        // Map<String, Object> userValidate =
        // this.checkEmptyOrShortField(userRequest.getEmail(), 6);
        if (userRequest.getUserName() == null) {
            validateFail = true;
            response.put("name", "Name is empty");
        } else if (userRequest.getUserName().length() <= 6) {
            validateFail = true;
            response.put("name", "Name is shorter than 6 characters");
        }

        if (!emailValidation.matcher(userRequest.getEmail()).matches()) {
            validateFail = true;
            response.put("email", "Email is invalid");
        }

        if (userRequest.getGroupId() < 1) {
            validateFail = true;
            response.put("groupId", "Group is invalid");
        }

        if (userRequest.getIsActive() < 0) {
            validateFail = true;
            response.put("isActive", "Status is invalid");
        }

        if (validateFail) {
            JSONObject error = new JSONObject();
            error.put("error", response);
            return new ResponseEntity<String>(error.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        response.put("name", userRequest.getUserName());
        response.put("email", userRequest.getEmail());
        response.put("groupId", userRequest.getGroupId());
        response.put("isActive", userRequest.getIsActive());

        return new ResponseEntity<String>(response.toString(), HttpStatus.OK);

        // User user = this.userRepo.findByName(userRequest.getUserName());

        // if (user != null) {

        // }
    }
}

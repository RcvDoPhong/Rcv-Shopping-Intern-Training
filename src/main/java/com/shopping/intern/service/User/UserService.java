package com.shopping.intern.service.User;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;
import com.shopping.intern.component.CustomValidation;

import com.shopping.intern.model.User;
import com.shopping.intern.repository.User.IUserRepository;
import com.shopping.intern.request.UserCreateUpdateRequest;
import com.shopping.intern.request.UserLoginRequest;

@Service
public class UserService implements IUserService {
    private final IUserRepository userRepo;

    private byte deleteState = 1;

    private String disabledState = "0";

    private String defaultPwd = "password";

    public String getDefaultPwd() {
        return defaultPwd;
    }

    public void setDefaultPwd(String defaultPwd) {
        this.defaultPwd = defaultPwd;
    }

    public UserService(IUserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public byte getDeleteState() {
        return deleteState;
    }

    public void setDeleteState(byte deleteState) {
        this.deleteState = deleteState;
    }

    public String getDisabledState() {
        return disabledState;
    }

    public void setDisabledState(String disabledState) {
        this.disabledState = disabledState;
    }

    public List<User> paginate(int currentPage, int perPage, User userSearchForm) {
        return this.userRepo.findAll(true, currentPage, perPage, userSearchForm);
    }

    public List<User> findAll(User userSearchForm) {
        return this.userRepo.findAll(false, 0, 0, userSearchForm);
    }

    public User get(long userId) {
        return this.userRepo.findById(userId);
    }

    public String getCurrentTimestamp() {
        Date date = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp timestamp = new Timestamp(date.getTime());

        return timeFormat.format(timestamp);
    }

    public ResponseEntity<String> getUser(long userId) {
        JSONObject response = new JSONObject();
        JSONObject responseUser = new JSONObject();

        User userGet = this.get(userId);
        responseUser.put("userId", userGet.getUserId());
        responseUser.put("name", userGet.getUserName());
        responseUser.put("email", userGet.getEmail());
        responseUser.put("group", userGet.getGroupRole());
        responseUser.put("status", userGet.getIsActive());

        response.put("data", responseUser);

        return new ResponseEntity<String>(response.toString(), HttpStatus.OK);
    }

    public void deleteById(long userId) {
        this.userRepo.deleteById(userId);
    }

    public void lockById(long userId) {
        this.userRepo.lockById(userId);
    }

    public boolean checkLogin(UserLoginRequest userLoginRequest) {
        User user = this.userRepo.findByEmail(userLoginRequest.getEmail());
        if (user != null) {
            if (user.getIsActive().equals(disabledState)) {
                return false;
            }
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
            if (encoder.matches(userLoginRequest.getPassword(), user.getPassword())) {
                this.handleAfterLogin(user);
                return true;
            }
            this.storeTempValueLoginFail(userLoginRequest);
        }
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

    public void handleStoreUser(User userRequest) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        String passwordEncode = encoder.encode(defaultPwd);

        String currentTime = getCurrentTimestamp();

        userRequest.setCreatedAt(currentTime);
        userRequest.setUpdatedAt(currentTime);
        userRequest.setPassword(passwordEncode);
        this.userRepo.insert(userRequest);
    }

    public void handleUpdateUser(User userRequest) {

        String currentTime = getCurrentTimestamp();

        userRequest.setUpdatedAt(currentTime);
        this.userRepo.update(userRequest);
    }

    public ResponseEntity<String> validate(User userRequest, String message, String createType) {
        JSONObject response = new JSONObject();
        UserCreateUpdateRequest validateMap = new UserCreateUpdateRequest();
        CustomValidation validate = new CustomValidation();
        validate.setUserRepo(userRepo);

        boolean validateEmailFail = validate.validateSingleField(validateMap.getValidateMap(),
                userRequest.getEmail(), "email",
                response, String.valueOf(userRequest.getUserId()));

        boolean validateNameFail = validate.validateSingleField(validateMap.getValidateMap(),
                userRequest.getUserName(), "name",
                response, String.valueOf(userRequest.getUserId()));

        boolean validateGroupFail = validate.validateSingleField(validateMap.getValidateMap(),
                String.valueOf(userRequest.getGroupRole()), "group", response, String.valueOf(userRequest.getUserId()));

        boolean validateStatusFail = validate.validateSingleField(validateMap.getValidateMap(),
                String.valueOf(userRequest.getIsActive()), "status", response, String.valueOf(userRequest.getUserId()));

        if (validateEmailFail || validateNameFail || validateGroupFail || validateStatusFail) {
            JSONObject error = new JSONObject();
            error.put("error", response);
            return new ResponseEntity<>(error.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if (createType.equals("create")) {
            handleStoreUser(userRequest);
        } else {
            handleUpdateUser(userRequest);
        }

        response.put("message", message);

        return new ResponseEntity<>(response.toString(), HttpStatus.OK);
    }
}

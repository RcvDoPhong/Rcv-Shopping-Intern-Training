package com.shopping.intern.service.User;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.catalina.connector.Response;
import org.apache.struts2.json.annotations.JSON;
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

    private int EMPTY_VALUE = 0;

    private String emailValidateRegrex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

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

    public List<User> paginate(int currentPage, int perPage, User userSearchForm) {
        return this.userRepo.findAll(true, currentPage, perPage, userSearchForm);
    }

    public List<User> findAll() {
        return this.userRepo.findAll(false, 0, 0, null);
    }

    public User get(long userId) {
        return this.userRepo.findById(userId);
    }

    public ResponseEntity<String> getUser(long userId) {
        JSONObject response = new JSONObject();
        JSONObject responseUser = new JSONObject();

        User userGet = this.get(userId);
        responseUser.put("userId", userGet.getUserId());
        responseUser.put("name", userGet.getUserName());
        responseUser.put("email", userGet.getEmail());
        responseUser.put("group", userGet.getGroupId());
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
        String passwordEncode = encoder.encode("password");
        userRequest.setPassword(passwordEncode);
        userRequest.setUpdatedBy(1);
        this.userRepo.insert(userRequest);
    }

    public void handleUpdateUser(User userRequest) {
        userRequest.setUpdatedBy(1);
        this.userRepo.update(userRequest);
    }

    public ResponseEntity<String> validate(User userRequest, String message, String createType) {
        JSONObject response = new JSONObject();
        UserCreateUpdateRequest validateMap = new UserCreateUpdateRequest();

        boolean validateEmailFail = validateSingleField(validateMap.getValidateMap(),
                userRequest.getEmail(), "email",
                response, userRequest.getUserId());
        boolean validateNameFail = validateSingleField(validateMap.getValidateMap(),
                userRequest.getUserName(), "name",
                response, userRequest.getUserId());
        boolean validateGroupFail = validateSingleField(validateMap.getValidateMap(),
                String.valueOf(userRequest.getGroupId()), "group", response, userRequest.getUserId());
        boolean validateStatusFail = validateSingleField(validateMap.getValidateMap(),
                String.valueOf(userRequest.getIsActive()), "status", response, userRequest.getUserId());

        if (validateEmailFail || validateNameFail || validateGroupFail || validateStatusFail) {
            JSONObject error = new JSONObject();
            error.put("error", response);
            return new ResponseEntity<String>(error.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if (createType.equals("create")) {
            handleStoreUser(userRequest);
        } else {
            handleUpdateUser(userRequest);
        }

        response.put("message", message);

        return new ResponseEntity<String>(response.toString(), HttpStatus.OK);
    }

    public boolean validateSingleField(
            Map<String, String> validateMap,
            String value,
            String field,
            JSONObject response,
            long userId) {
        String[] conditions = validateMap.get(field).split("\\|");
        StringBuilder message = new StringBuilder();
        String fieldUppercase = field.substring(0, 1).toUpperCase() + field.substring(1);

        boolean validateFail = false;
        for (String rule : conditions) {
            String[] condition = rule.split(":");
            System.out.println(condition[0]);
            switch (condition[0]) {
                case "required":
                    try {
                        int newValue = Integer.parseInt(value);
                        if (newValue < EMPTY_VALUE) {
                            validateFail = true;
                        }
                    } catch (Exception e) {
                        if (String.valueOf(value).equals("")) {
                            validateFail = true;
                        }

                    }
                    if (validateFail) {
                        message.append(fieldUppercase + " is empty" + "<br>");
                    }
                    break;

                case "unique":
                    String column = condition[1];
                    User user = this.userRepo.find(String.valueOf(value), column, userId);

                    if (user != null) {
                        validateFail = true;
                        message.append(fieldUppercase + " is already exists" + "<br>"); // need to optimize
                    }
                    break;

                case "email":
                    String emailValue = String.valueOf(value);
                    Pattern emailValidation = Pattern.compile(emailValidateRegrex, Pattern.CASE_INSENSITIVE);
                    if (!emailValidation.matcher(emailValue).matches()) {
                        validateFail = true;
                        message.append(fieldUppercase + " is invalid" + "<br>"); // need to optimize
                    }
                    break;

                case "min":
                    boolean validateMinFail = validateNumber(condition, String.valueOf(value), "min");
                    if (validateMinFail) {
                        validateFail = true;
                        String messageGet = condition[3];
                        message.append(fieldUppercase + " " + messageGet + "<br>"); // need to optimize
                        System.out.println(message.toString());
                    }
                    break;

                case "max":
                    boolean validateMaxFail = validateNumber(condition, String.valueOf(value), "max");
                    if (validateMaxFail) {
                        validateFail = true;
                        String messageGet = condition[3];
                        message.append(fieldUppercase + " " + messageGet + "<br>"); // need to optimize
                    }
                    break;

                default:
                    break;
            }
        }

        if (validateFail) {
            System.out.println(message.toString());
            response.put(field, message.toString());
        }

        return validateFail;
    }

    public boolean validateNumber(String[] condition, String value, String compareType) {
        String dataType = condition[1];
        int dataLength = Integer.parseInt(condition[2]);

        boolean validateFail = false;
        if (compareType.equals("min")) {
            if ((dataType.equals("String") && value.length() < dataLength)
                    || (dataType.equals("int") && Integer.parseInt(value) < dataLength))
                validateFail = true;
        } else if (compareType.equals("max")) {
            if ((dataType.equals("String") && value.length() > dataLength)
                    || (dataType.equals("int") && Integer.parseInt(value) > dataLength))
                validateFail = true;
        }

        return validateFail;
    }
}

package com.shopping.intern.component;

import java.util.Map;
import java.util.regex.Pattern;

import org.json.JSONObject;

import com.shopping.intern.model.User;
import com.shopping.intern.repository.User.IUserRepository;

public class CustomValidation {

    private int EMPTY_VALUE = 0;

    private IUserRepository userRepo;

    private String emailValidateRegrex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

    public CustomValidation() {
    }

    public CustomValidation(IUserRepository userRepo) {
        this.userRepo = userRepo;
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
            String[] condition = rule.split(":"); // "min:String:6 => "min", "String", "6", "is too short"
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
                    String table = condition[2];
                    Object obj = null;
                    if (table.equals("users")) {
                        obj = this.userRepo.find(String.valueOf(value), column, userId);
                    }

                    if (table.equals("products")) {
                        //
                    }

                    if (obj != null) {
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
                        String limitMin = condition[2];
                        message.append(fieldUppercase + " is shorter than " + limitMin + " characters<br>"); // need to
                                                                                                             // optimize
                    }
                    break;

                case "max":
                    boolean validateMaxFail = validateNumber(condition, String.valueOf(value), "max");
                    if (validateMaxFail) {
                        validateFail = true;
                        String limitMax = condition[2];
                        message.append(fieldUppercase + " is more than " + limitMax + " characters<br>"); // need to
                                                                                                          // optimize
                    }
                    break;

                default:
                    break;
            }
        }

        if (validateFail) {
            response.put(field, message.toString());
        }

        return validateFail;
    }

    public boolean validateNumber(String[] condition, String value, String compareType) {
        String dataType = condition[1];
        int dataLength = Integer.parseInt(condition[2]);

        boolean validateFail = false;
        switch (compareType) {
            case "min":
                if ((dataType.equals("String") && value.length() < dataLength)
                        || (dataType.equals("int") && Integer.parseInt(value) < dataLength))
                    validateFail = true;
                break;

            case "max":
                if ((dataType.equals("String") && value.length() > dataLength)
                        || (dataType.equals("int") && Integer.parseInt(value) > dataLength))
                    validateFail = true;
                break;

            default:
                break;
        }

        return validateFail;
    }
}

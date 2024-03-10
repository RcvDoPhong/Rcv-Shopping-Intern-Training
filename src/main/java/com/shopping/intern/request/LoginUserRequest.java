package com.shopping.intern.request;

import java.util.HashMap;
import java.util.Map;

public class LoginUserRequest {

    private Map<String, String> validateMap = new HashMap<>();

    public Map<String, String> getValidateMap() {
        return validateMap;
    }

    public void setValidateMap(Map<String, String> validateMap) {
        this.validateMap = validateMap;
    }

    public LoginUserRequest() {
        this.validateMap.put("email", "required|email");
        this.validateMap.put("password", "required|min:String:6");
    }
}

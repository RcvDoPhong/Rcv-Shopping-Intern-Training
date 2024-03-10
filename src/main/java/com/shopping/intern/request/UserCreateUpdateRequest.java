package com.shopping.intern.request;

import java.util.HashMap;
import java.util.Map;

public class UserCreateUpdateRequest {

    private Map<String, String> validateMap = new HashMap<>();

    public Map<String, String> getValidateMap() {
        return validateMap;
    }

    public void setValidateMap(Map<String, String> validateMap) {
        this.validateMap = validateMap;
    }

    public UserCreateUpdateRequest() {
        this.validateMap.put("name", "required|min:String:6|max:String:100|unique:users:user_name");
        this.validateMap.put("email", "required|email|unique:users:email");
        this.validateMap.put("group", "required");
        this.validateMap.put("status", "required");
    }
}

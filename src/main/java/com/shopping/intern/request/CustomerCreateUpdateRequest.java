package com.shopping.intern.request;

import java.util.HashMap;
import java.util.Map;

public class CustomerCreateUpdateRequest {
    private Map<String, String> validateMap = new HashMap<>();

    public Map<String, String> getValidateMap() {
        return validateMap;
    }

    public void setValidateMap(Map<String, String> validateMap) {
        this.validateMap = validateMap;
    }

    public CustomerCreateUpdateRequest() {
        this.validateMap.put("name", "required|min:String:6");
        this.validateMap.put("email", "required|email|unique:customers:email");
        this.validateMap.put("phone", "required|phone");
        this.validateMap.put("address", "required");
        this.validateMap.put("status", "required");
    }
}

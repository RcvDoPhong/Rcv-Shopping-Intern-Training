package com.shopping.intern.request;

import java.util.HashMap;
import java.util.Map;

public class UserCreateUpdateRequest {
    private String userName;

    private String email;

    private String groupRole = "";

    private long isActive = -1;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGroupRole() {
        return groupRole;
    }

    public void setGroupRole(String groupRole) {
        this.groupRole = groupRole;
    }

    public long getIsActive() {
        return isActive;
    }

    public void setIsActive(long isActive) {
        this.isActive = isActive;
    }
}

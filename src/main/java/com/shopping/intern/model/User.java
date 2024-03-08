package com.shopping.intern.model;

public class User {

    // @Autowired
    // private UserMapper userMapper;

    private int userId;

    private String userName;

    private String email;

    private String password;

    private String rememberToken = null;

    private String verifyEmail = null;

    private String isActive;

    private byte isDelete = 0;

    private String groupRole;

    private String lastLoginAt = null;

    private String lastLoginIp = null;

    private String createdAt = null;

    private String updatedAt = null;

    public User() {
    }

    // User's ID
    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    // User's Email
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // User's Password
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // User's Username
    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    // User's rememberToken
    public String getRememberToken() {
        return this.rememberToken;
    }

    public void setRememberToken(String rememberToken) {
        this.rememberToken = rememberToken;
    }

    // User's verify Email
    public String getVerifyEmail() {
        return this.verifyEmail;
    }

    public void setVerifyEmail(String verifyEmail) {
        this.verifyEmail = verifyEmail;
    }

    // User's Active state
    public String getIsActive() {
        return this.isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    // User's Delete state
    public byte getIsDelete() {
        return this.isDelete;
    }

    public void setIsDelete(byte isDelete) {
        this.isDelete = isDelete;
    }

    // User's Group ID
    public String getGroupRole() {
        return this.groupRole;
    }

    public void setGroupRole(String groupRole) {
        this.groupRole = groupRole;
    }

    // User's Last Login Info
    public String getLastLoginAt() {
        return this.lastLoginAt;
    }

    public void setLastLoginAt(String lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }

    public String getLastLoginIp() {
        return this.lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    // User's Created & Updated at Timestamp
    public String getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isActiveStatus() {
        return getIsActive().equals(1);
    }

    // public List<User> selectAll() {
    // return this.userMapper.findAllUser();
    // }

    // public User findUserById(int userId) {
    // return this.userMapper.findUserById(userId);
    // }
}

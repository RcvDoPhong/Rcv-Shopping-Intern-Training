package com.shopping.intern.model;

import java.sql.Date;

public class User {

    // @Autowired
    // private UserMapper userMapper;

    private int userId;

    private String userName;

    private String email;

    private String password;

    private String rememberToken = null;

    private String verifyEmail = null;

    private byte isActive = 1;

    private byte isDelete = 0;

    private int groupId;

    private String groupName;

    private String lastLoginAt = null;

    private String lastLoginIp = null;

    private Date createdAt = null;

    private Date updatedAt = null;

    private int updatedBy;

    private String updatedByUser;

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

    public void setUserName(String user_name) {
        this.userName = user_name;
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
    public byte getIsActive() {
        return this.isActive;
    }

    public void setIsActive(byte isActive) {
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
    public int getGroupId() {
        return this.groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    // User's Group Name
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String group_name) {
        this.groupName = group_name;
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
    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    // User's creator/updated by someone
    public int getUpdatedBy() {
        return this.updatedBy;
    }

    public void setUpdatedBy(int updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedByUser() {
        return updatedByUser;
    }

    public void setUpdatedByUser(String updated_by_user) {
        this.updatedByUser = updated_by_user;
    }

    public boolean isActiveStatus() {
        return getIsActive() == 1;
    }

    // public List<User> selectAll() {
    // return this.userMapper.findAllUser();
    // }

    // public User findUserById(int userId) {
    // return this.userMapper.findUserById(userId);
    // }
}

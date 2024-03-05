package com.shopping.intern.model;

import java.util.Date;

public class Group {
    private long groupId;

    private String groupName;

    private byte isDelete;

    private Date createdAt;

    private Date updatedAt;

    private Date updatedBy;

    private String updatedByUser;

    // Group ID
    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    // Group Name
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    // Group's isDelete state
    public byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(byte isDelete) {
        this.isDelete = isDelete;
    }

    // Groups's creation Timestamp
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    // Group's update Timestamp
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    // Group's update by admin
    public Date getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Date updatedBy) {
        this.updatedBy = updatedBy;
    }

    // Group's update by Admin (display name)
    public String getUpdatedByUser() {
        return updatedByUser;
    }

    public void setUpdatedByUser(String updatedByUser) {
        this.updatedByUser = updatedByUser;
    }
}

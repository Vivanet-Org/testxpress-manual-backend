package com.siliconstack.applications.dto;

import java.util.Date;

public class TEApplicationsDTO {

    private int appid;
    private String appName;
    private String appDescription;
    private boolean isDeleted;
    private int projectID;
    private int platformID;
    private int createdBy;
    private Date createdOn;
    private int updatedBy;
    private Date updatedOn;

    public TEApplicationsDTO() {
    }

    public TEApplicationsDTO(int appid, String appName, String appDescription, boolean isDeleted, int projectID, int platformID, int createdBy, Date createdOn, int updatedBy, Date updatedOn) {
        this.appid = appid;
        this.appName = appName;
        this.appDescription = appDescription;
        this.isDeleted = isDeleted;
        this.projectID = projectID;
        this.platformID = platformID;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.updatedBy = updatedBy;
        this.updatedOn = updatedOn;
    }

    public int getAppid() {
        return appid;
    }

    public void setAppid(int appid) {
        this.appid = appid;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppDescription() {
        return appDescription;
    }

    public void setAppDescription(String appDescription) {
        this.appDescription = appDescription;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public int getPlatformID() {
        return platformID;
    }

    public void setPlatformID(int platformID) {
        this.platformID = platformID;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public int getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(int updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }
}

package com.siliconstack.project.dto;

import java.util.Date;

public class TEProjectDTO {

    private int projectid;
    private String projectName;
    private String projectDescription;
    private int createdBy;
    private Date createdOn;
    private int updatedBy;
    private Date updatedOn;
    private boolean deleted;

    public TEProjectDTO() {
    }

    public TEProjectDTO(int projectid, String projectName, String projectDescription, int createdBy, Date createdOn,
                        int updatedBy, Date updatedOn, boolean deleted) {
        super();
        this.projectid = projectid;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.updatedBy = updatedBy;
        this.updatedOn = updatedOn;
        this.deleted = deleted;
    }

    public int getProjectid() {
        return projectid;
    }

    public void setProjectid(int projectid) {
        this.projectid = projectid;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

}
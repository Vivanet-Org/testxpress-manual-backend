package com.siliconstack.project.model;

import com.siliconstack.application.model.TEApplication;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "te_projects")
public class TEProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "projectid")
    private int projectid;

    @Column(name = "projectname")
    private String projectName;

    @Column(name = "projectdescription")
    private String projectDescription;

    @Column(name = "isdeleted")
    private boolean isDeleted;

    @Column(name = "createdby")
    private int createdBy;

    @Column(name = "createdon", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdOn;

    @Column(name = "updatedby")
    private int updatedBy;

    @Column(name = "updatedon", nullable = false, updatable = true)
    @CreationTimestamp
    private Date updatedOn;


    public TEProject() {
    }

    public TEProject(int projectid, String projectName, String projectDescription, boolean isDeleted, int createdBy,
                     Date createdOn, int updatedBy, Date updatedOn) {
        super();
        this.projectid = projectid;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.isDeleted = isDeleted;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.updatedBy = updatedBy;
        this.updatedOn = updatedOn;
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

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
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
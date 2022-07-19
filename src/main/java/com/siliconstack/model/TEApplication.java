package com.siliconstack.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "te_applications")

public class TEApplication {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long appid;
	
	@Column(name = "appname")
	private String appName;
	
	@Column(name = "appdescription")
	private String appDescription;
	
	@Column(name = "isdeleted")
	private boolean isDeleted;
	
	@Column(name = "projectid")
	private int projectID;
	
	@Column(name = "platformid")
	private int platformID;
	
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
	
	public TEApplication() {
    }

	public TEApplication(long appid, String appName, String appDescription, boolean isDeleted, int projectID,
			int platformID, int createdBy, Date createdOn, int updatedBy, Date updatedOn) {
		super();
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

	public long getAppid() {
		return appid;
	}

	public void setAppid(long appid) {
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

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
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

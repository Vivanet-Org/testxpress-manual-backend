package com.siliconstack.dto;

import java.util.Date;

public class TEApplicationDTO {
	
	private long appid;
    private String appName;
    private String appDescription;
    private int projectID;
    private int platformID;
    private int createdBy;
    private Date createdOn;
    private int updatedBy;
    private Date updatedOn;
    private boolean isDeleted;
    
    public TEApplicationDTO() {
    }

	public TEApplicationDTO(long appid, String appName, String appDescription, int projectID, int platformID,
			int createdBy, Date createdOn, int updatedBy, Date updatedOn, boolean isDeleted) {
		super();
		this.appid = appid;
		this.appName = appName;
		this.appDescription = appDescription;
		this.projectID = projectID;
		this.platformID = platformID;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
		this.isDeleted = isDeleted;
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

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
    
}

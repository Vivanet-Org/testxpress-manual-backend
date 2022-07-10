package com.siliconstack.dto;

import java.util.Date;

public class TEProjectDTO {

	private int projectid;
    private String projectname;
    private String projectdescription;
    private int createdby;
    private Date createdon;
    private int updatedby;
    private Date updatedon;
    private boolean isdeleted;

    public TEProjectDTO() {
    }
    
	public TEProjectDTO(int projectid, String projectname, String projectdescription, int createdby, Date createdon,
			int updatedby, Date updatedon, boolean isdeleted) {
		super();
		this.projectid = projectid;
		this.projectname = projectname;
		this.projectdescription = projectdescription;
		this.createdby = createdby;
		this.createdon = createdon;
		this.updatedby = updatedby;
		this.updatedon = updatedon;
		this.isdeleted = isdeleted;
	}

	public int getProjectid() {
		return projectid;
	}

	public void setProjectid(int projectid) {
		this.projectid = projectid;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getProjectdescription() {
		return projectdescription;
	}

	public void setProjectdescription(String projectdescription) {
		this.projectdescription = projectdescription;
	}

	public int getCreatedby() {
		return createdby;
	}

	public void setCreatedby(int createdby) {
		this.createdby = createdby;
	}

	public Date getCreatedon() {
		return createdon;
	}

	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}

	public int getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(int updatedby) {
		this.updatedby = updatedby;
	}

	public Date getUpdatedon() {
		return updatedon;
	}

	public void setUpdatedon(Date updatedon) {
		this.updatedon = updatedon;
	}

	public boolean isIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

}

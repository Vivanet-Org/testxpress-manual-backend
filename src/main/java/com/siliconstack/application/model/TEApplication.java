package com.siliconstack.application.model;

import com.siliconstack.project.model.TEProject;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "te_applications")
public class TEApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appId")
    private int appid;

    @Column(name = "appName")
    private String appName;

    @Column(name = "appDescription")
    private String appDescription;

    @Column(name = "isdeleted")
    private boolean isDeleted;

 
    @Column(name= "projectid")
	private int projectId;
 
    @Column(name = "platformID")
    private int platformID;

    @Column(name = "createdBy")
    private int createdBy;

    @Column(name = "createdOn", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdOn;

    @Column(name = "updatedBy")
    private int updatedBy;

    @Column(name = "updatedOn", nullable = false, updatable = true)
    @CreationTimestamp
    private Date updatedOn;

}

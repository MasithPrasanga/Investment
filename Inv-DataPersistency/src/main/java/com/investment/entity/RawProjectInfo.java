package com.investment.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "rawdata")
public class RawProjectInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	@Column(name = "project_name", unique = true, nullable = false)
	private String projectName;


	@Column(name = "admin_status", nullable = false)
	private String adminStatus;
	
    @Column(name="submited_date", nullable=false)
	private Date date;
	
	public RawProjectInfo() {
		super();
	}
	
	public RawProjectInfo(String projectName, String adminStatus, Date date) {
		super();
		this.projectName = projectName;
		this.adminStatus = adminStatus;
		this.date = date;
	}
	
	public RawProjectInfo(int id, String projectName, String adminStatus, Date date) {
		super();
		this.id = id;
		this.projectName = projectName;
		this.adminStatus = adminStatus;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getAdminStatus() {
		return adminStatus;
	}

	public void setAdminStatus(String adminStatus) {
		this.adminStatus = adminStatus;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "RawData [id=" + id + ", projectName=" + projectName + ", adminStatus=" + adminStatus + ", date=" + date
				+ "]";
	}

}

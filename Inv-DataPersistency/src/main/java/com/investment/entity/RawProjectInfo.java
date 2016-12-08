package com.investment.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "raw_project_info")
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
	private Date submitedDate;
    
    @Column(name="approved_date")
	private Date approvedDate;
    
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private CoreUser coreUser = null;
    
    @OneToOne(mappedBy = "rawProjectInfo",fetch = FetchType.EAGER)
    private ProcessedProjectInfo processedProjectInfo = null;
    
    @OneToMany(mappedBy = "rawData", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<BusinessUpload> businessUpload;
	
	public RawProjectInfo() {
		super();
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

	public Date getSubmitedDate() {
		return submitedDate;
	}

	public void setSubmitedDate(Date submitedDate) {
		this.submitedDate = submitedDate;
	}

	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	public CoreUser getCoreUser() {
		return coreUser;
	}

	public void setCoreUser(CoreUser coreUser) {
		this.coreUser = coreUser;
	}

	public List<BusinessUpload> getBusinessUpload() {
		return businessUpload;
	}

	public void setBusinessUpload(List<BusinessUpload> businessUpload) {
		this.businessUpload = businessUpload;
	}

	public ProcessedProjectInfo getProcessedProjectInfo() {
		return processedProjectInfo;
	}

	public void setProcessedProjectInfo(ProcessedProjectInfo processedProjectInfo) {
		this.processedProjectInfo = processedProjectInfo;
	}
	
}

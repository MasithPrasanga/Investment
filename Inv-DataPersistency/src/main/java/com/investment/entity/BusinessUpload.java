package com.investment.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "business_upload")
public class BusinessUpload implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	@Column(name = "submited_date", nullable = false)
	private Date date;
	
	@Column(name = "url", unique = true, nullable = false)
	private String url;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "raw_project_info_id")
	private RawProjectInfo rawData = null;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private CoreUser coreUser = null;

	public BusinessUpload() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public RawProjectInfo getRawData() {
		return rawData;
	}

	public void setRawData(RawProjectInfo rawData) {
		this.rawData = rawData;
	}
	
	public CoreUser getCoreUser() {
		return coreUser;
	}

	public void setCoreUser(CoreUser coreUser) {
		this.coreUser = coreUser;
	}

	@Override
	public String toString() {
		return "Upload [id=" + id + ", date=" + date + ", url=" + url + "]";
	}

}


























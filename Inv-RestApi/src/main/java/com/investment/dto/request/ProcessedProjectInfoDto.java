package com.investment.dto.request;

import java.io.Serializable;

import com.investment.dto.response.root.RootResponse;

public class ProcessedProjectInfoDto extends RootResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String projectName;

	private int sharePrice;
	
	private String imageUrl;
	
	private String videoUrl;
	
	private int fullAmmount;

	private int noOfShares;

	private int mininumAmmount;
	
	private String adminStatus;

	private int userid;
	
	private int typeid;

	private int currencyid;

	private int categoryid;

	private int customertypeid;
	
	private int rawProjectInfoId;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public int getSharePrice() {
		return sharePrice;
	}

	public void setSharePrice(int sharePrice) {
		this.sharePrice = sharePrice;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public int getFullAmmount() {
		return fullAmmount;
	}

	public void setFullAmmount(int fullAmmount) {
		this.fullAmmount = fullAmmount;
	}

	public int getNoOfShares() {
		return noOfShares;
	}

	public void setNoOfShares(int noOfShares) {
		this.noOfShares = noOfShares;
	}

	public int getMininumAmmount() {
		return mininumAmmount;
	}

	public void setMininumAmmount(int mininumAmmount) {
		this.mininumAmmount = mininumAmmount;
	}

	public String getAdminStatus() {
		return adminStatus;
	}

	public void setAdminStatus(String adminStatus) {
		this.adminStatus = adminStatus;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getTypeid() {
		return typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}

	public int getCurrencyid() {
		return currencyid;
	}

	public void setCurrencyid(int currencyid) {
		this.currencyid = currencyid;
	}

	public int getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}

	public int getCustomertypeid() {
		return customertypeid;
	}

	public void setCustomertypeid(int customertypeid) {
		this.customertypeid = customertypeid;
	}
	
	public int getRawProjectInfoId() {
		return rawProjectInfoId;
	}

	public void setRawProjectInfoId(int rawProjectInfoId) {
		this.rawProjectInfoId = rawProjectInfoId;
	}

	@Override
	public String toString() {
		return "ProcessedProjectInfoDto [projectName=" + projectName + ", sharePrice=" + sharePrice + ", imageUrl="
				+ imageUrl + ", videoUrl=" + videoUrl + ", fullAmmount=" + fullAmmount + ", noOfShares=" + noOfShares
				+ ", mininumAmmount=" + mininumAmmount + ", adminStatus=" + adminStatus + ", userid=" + userid
				+ ", typeid=" + typeid + ", currencyid=" + currencyid + ", categoryid=" + categoryid
				+ ", customertypeid=" + customertypeid + "]";
	}
	
}

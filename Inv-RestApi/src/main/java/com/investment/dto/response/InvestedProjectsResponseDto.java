package com.investment.dto.response;

import java.io.Serializable;
import java.util.Date;

public class InvestedProjectsResponseDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String projectName;
	private int singleSharePrice;
	private String imageUrl;
	private String videoUrl;
	private int fullAmmountCanInvest;
	private int noOfSharesCanBuy;
	private int mininumAmmountCanInvest;
	private String type = null;
	private String currency;
	private String category;
	private String customerType;
	private Date investedDate;
	private Integer investedAmouont;
	private Integer investedNoOfShares;
	private Integer investedPrecentage;
	private CoreUserResponseDto entrepreneur;
	
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public int getSingleSharePrice() {
		return singleSharePrice;
	}
	public void setSingleSharePrice(int singleSharePrice) {
		this.singleSharePrice = singleSharePrice;
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
	public int getFullAmmountCanInvest() {
		return fullAmmountCanInvest;
	}
	public void setFullAmmountCanInvest(int fullAmmountCanInvest) {
		this.fullAmmountCanInvest = fullAmmountCanInvest;
	}
	public int getNoOfSharesCanBuy() {
		return noOfSharesCanBuy;
	}
	public void setNoOfSharesCanBuy(int noOfSharesCanBuy) {
		this.noOfSharesCanBuy = noOfSharesCanBuy;
	}
	public int getMininumAmmountCanInvest() {
		return mininumAmmountCanInvest;
	}
	public void setMininumAmmountCanInvest(int mininumAmmountCanInvest) {
		this.mininumAmmountCanInvest = mininumAmmountCanInvest;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public Date getInvestedDate() {
		return investedDate;
	}
	public void setInvestedDate(Date investedDate) {
		this.investedDate = investedDate;
	}
	public Integer getInvestedAmouont() {
		return investedAmouont;
	}
	public void setInvestedAmouont(Integer investedAmouont) {
		this.investedAmouont = investedAmouont;
	}
	public Integer getInvestedNoOfShares() {
		return investedNoOfShares;
	}
	public void setInvestedNoOfShares(Integer investedNoOfShares) {
		this.investedNoOfShares = investedNoOfShares;
	}
	public Integer getInvestedPrecentage() {
		return investedPrecentage;
	}
	public void setInvestedPrecentage(Integer investedPrecentage) {
		this.investedPrecentage = investedPrecentage;
	}
	public CoreUserResponseDto getEntrepreneur() {
		return entrepreneur;
	}
	public void setEntrepreneur(CoreUserResponseDto entrepreneur) {
		this.entrepreneur = entrepreneur;
	}
}





package com.investment.dto.response;

import java.io.Serializable;

import com.investment.entity.CoreUser;
import com.investment.entity.metadata.Category;
import com.investment.entity.metadata.Currency;
import com.investment.entity.metadata.CustomerType;
import com.investment.entity.metadata.Type;

public class ProssedProjectInfoResponseDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;

	private String projectName;

	private int sharePrice;
	
	private String imageUrl;
	
	private String videoUrl;
	
	private int fullAmmount;
	
	private int noOfShares;
	
	private int mininumAmmount;
	
	private Type type = null;
	
	private Currency currency;
	
	private Category category;
	
	private CustomerType customerType;
	
	private CoreUser coreUser;

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

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public CustomerType getCustomerType() {
		return customerType;
	}

	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}

	public CoreUser getCoreUser() {
		return coreUser;
	}

	public void setCoreUser(CoreUser coreUser) {
		this.coreUser = coreUser;
	}
	
}






